# 고급 주제와 성능 최적화
## 예외 처리
### JPA 표준 예외 정리
JPA 표준 예외들은 ```javax.persistence.PersistenceException```의 자식 클래스다.

#### 트랜잭션 롤백을 표시하는 예외
- javax.persistence.EntityExistsException : EntityManager.persist(...) 호출 시 이미 같은 엔터티가 있으면 발생
- javax.persistence.EntityNoFoundException : EntityManager.getReference(...)를 호출했는데 실제 사용 시 엔터티가 존재하지 않으면 발생
  refresh(...), lock(...)에서도 발생
- javax.persistence.OptimisticLockException : 낙관적 락 충돌 시 발생
- javax.persistence.PessimisticLockException : 비관적 락 충돌 시 발생
- javax.persistence.RollbackException : EntityTrasaction.commit() 실패 시 발생
  롤백이 표시되어 있는 트랜잭션 커밋 시에도 발생
- javax.persistence.TransactionRequiredException : 트랜잭션이 필요할 때 트랜잭션이 없으면 발생
  트랜잭션 없이 엔터티를 변경할 때 주로 발생

트랜잭션 롤백을 표시하는 예외는 심각한 예외이므로 복구해선 안 된다.

#### 트랜잭션 롤백을 표시하지 않는 예외
- javax.persistence.NoResultException : Query.getSingleResult() 호출 시 결과가 하나도 없을 때 발생
- javax.persistence.NonUniqueResultException : Query.getSingleResult()호출 시 결과가 둘 이상일 때 발생
- javax.persistence.LockTimeoutException : 비관적 락에서 시간 초과 시 발생
- javax.persistence.QueryTimeoutException : 쿼리 실행 시간 초과 시 발생

### 트랜잭션 롤백 시 주의사항
트랜잭션을 롤백하는 것은 데이터베이스의 반영사항만 롤백하는 것이지 수정한 자바 객체까지 원상태로 복구해주지 않는다. 따라서 트랜잭션이 롤백된 영속성 컨텍스트를 그대로 사용하는 것은 위험하다. 새로운 영속성 컨텍스트를 생성해서 사용하거나 ```EntityManager.clear()```를 호출해 영속성 컨텍스트를 초기화한 다음에 사용해야 한다.

스프링 프레임워크는 이런 문제를 예방하기 위해 영속성 컨텍스트의 범위에 따라 다른 방법을 사용한다.

- 트랜잭션당 영속성 컨텍스트 전략(기본 전략) : 문제 발생 시 트랜잭션 AOP 종료 시점에 트랜잭션을 롤백하면서 영속성 컨텍스트도 함께 종료

이는 영속성 컨텍스트의 범위를 트랜잭션 범위보다 넓게 사용해서 여러 트랜잭션이 하나의 영속성 컨텍스트를 사용할 때 문제가 발생한다. 이때는 트랜잭션을 롤백해서 영속성 컨텍스트에 이상해 발생해도 다른 트랜잭션에서 해당 영속성 컨텍스트를 그대로 사용하는 문제가 있다. 스프링 프레임워크는 영속성 컨텍스트의 범위를 트랜잭션의 범위보다 넓게 설정하면 트랜잭션 롤백시 영속성 컨텍스트를 초기화해서 잘못된 영속성 컨텍스트를 사용하는 문제를 예방한다.

## 엔터티 비교
같은 영속성 컨텍스트에서 엔터티를 조회하면 항상 같은 엔터티 인스턴스를 반환한다. 이는 단순히 동등성 비교가 아니라 정말 주소값이 같은 인스턴스를 반환한다.

### 영속성 컨텍스트가 같을 때 엔터티 비교
영속성 컨텍스트가 같으면 엔터티를 비교할 때 다음 3가지 조건을 모두 만족한다.

- 동일성(identical) : ```==``` 비교가 같음
- 동등성(equinalent) : ```equals()``` 비교가 같음
- 데이터베이스 동등성 : ```@Id```인 데이터베이스 식별자가 같음

### 영속성 컨텍스트가 다를 때 엔터티 비교
영속성 컨텍스트가 다를 때 엔터티 비교는 다음과 같다.

- 동일성(identical) : ```==``` 비교가 실패
- 동등성(equinalent) : ```equals()``` 비교가 만족. 단, ```equals()```를 구현해야 함(보통 비즈니스 키로 구현)
- 데이터베이스 동등성 : ```@Id```인 데이터베이스 식별자가 같음

**엔터티를 비교할 때는 비즈니스 키를 활용한 동등성 비교**를 권장한다.

## 프록시 심화 주제
### 영속성 컨텍스트와 프록시
```java
@Test
public void 영속성컨텍스트와_프록시() {

  Member newMember = new Member("member1", "회원1");
  em.persist(newMember);
  em.flush();
  em.clear();
  
  Member refMember = em.getReference(Member.class, "member1");
  Member findMember = em.find(Member.class, "member1");
  
  System.out.println("refMember Type = " + refMember.getClass());
  System.out.println("findMember Type = " + findMember.getClass());
  
  Assert.assertTrue(refMember == findMember);	// 성공
```

출력 결과
```
refMember Type = class jpabook.advanced.Member_$$_jvst843_0
findMember Type = class jpabook.advanced.Member_$$_jvst843_0
```
영속성 컨텍스트는 프록시로 조회된 엔터티에 대해서 같은 엔터티를 찾는 요청이 오면 원본 엔터티가 아닌 처음 조회된 프록시를 반환한다. 따라서 **프록시로 조회해도 영속성 컨텍스트는 영속 엔터티의 동일성을 보장**한다.

```java
@Test
public void 영속성컨텍스트와_프록시() {

  Member newMember = new Member("member1", "회원1");
  em.persist(newMember);
  em.flush();
  em.clear();
  
  Member findMember = em.find(Member.class, "member1");
  Member refMember = em.getReference(Member.class, "member1");
  
  System.out.println("refMember Type = " + refMember.getClass());
  System.out.println("findMember Type = " + findMember.getClass());
  
  Assert.assertTrue(refMember == findMember);
```

출력 결과
```
refMember Type = class jpabook.advanced.Member
findMember Type = class jpabook.advanced.Member
```

원본 엔터티를 먼저 조회하면 영속성 컨텍스트는 원본 엔터티를 이미 데이터베이스에서 조회했으므로 프록시를 반환할 이유가 없다. 따라서 ```em.getReference()```를 호출해도 프록시가 아닌 원본을 반환한다. 이 경우에도 영속성 컨텍스트는 자신이 관리하는 영속 엔터티의 동일성을 보장한다.

### 프록시 타입 비교
프록시는 원본 엔터티를 상속 받아서 만들어지므로 프록시로 조회한 엔터티의 타입을 비교할 때는 ```==``` 비교 대신 ```instanceof```를 사용해야 한다.

### 프록시 동등성 비교
엔터티의 동등성을 비교하려면 비즈니스 키를 사용해서 ```equals()``` 메소드를 오버라이딩하고 비교하면 된다. 하지만 엔터티의 비교 대상이 원본 엔터티면 문제가 없지만 프록시면 문제가 발생할 수 있다.

프록시의 동등성을 비교할 때는 다음 사항을 주의해야 한다.

- 프록시의 타입 비교는 ```==``` 비교 대신에 ```instanceof```를 사용해야 한다.
- 프록시의 멤버변수에 직접 접근하면 안 되고 대신에 접근자 메소드를 사용해야 한다.

### 상속관계와 프록시
**프록시를 부모 타입으로 조회하면 부모의 타입을 기반으로 프록시가 생성되는 문제가 발생한다.**

- ```instanceof``` 연산을 사용할 수 없다.
- 하위 타입으로 다운캐스팅 할 수 없다.

#### 상속관계에서 발생하는 프록시 문제를 해결하는 방법

1. JPQL로 대상 직접 조회
2. 프록시 벗기기
3. 기능을 위한 별도의 인터페이스 제공
4. 비지터 패턴 사용

## 성능 최적화
### N+1 문제
N+1 문제는 JPA로 애플리케이션을 개발할 때 성능상 가장 주의해야 하는 문제다.

#### 즉시 로딩과 N+1
처음 실행한 SQL의 결과 수만큼 추가로 SQL을 실행하는 것을 N+1문제라고 한다. 즉시 로딩은 JPQL을 실행할 때 N+1 문제가 발생할 수 있다.

#### 지연 로딩과 N+1
지연 로딩으로 설정하면 JPQL에서는 N+1 문제가 발생하지 않는다. 하지만 모든 인스턴스에 대해 연관된 컬렉션을 사용할 때 연관된 컬렉션을 초기화하는 수만큼 SQL이 실행될 수 있다. 이것도 결구 N+1 문제다.

즉, N+1 문제는 즉시 로딩과 지연 로딩일 때 모두 발생할 수 있다.

#### N+1 문제를 피할 수 있는 방법
1. 페치 조인 사용
2. 하이버네이트 @BatchSize
3. 하이버네이트 @Fetch(FetchMode.SUBSELECT)