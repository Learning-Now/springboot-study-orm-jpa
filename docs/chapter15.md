
# 고급 주제와 성능 최적화

이번 장에서는 JPA의 고급 주제들과 JPA의 성능을 최적화 하는 방안을 알아볼 것이다.

---

## 1. 예외처리

JPA의 표준 예외 들은 **javax.persistence.PersistenceException**의 자식 클래스 이다. 

이외는 RuntimeException의 자식 이며 크게 2가지로 나눌수 있다.

1. 트랜잭션 롤백을 표시하는 예외
2. 트랜잭션 롤백을 표시하지 않는 예외

‼️ 트랜잭션 롤백을 표시하는 예외는 심각한 예외이므로 복구해선 안된다.

### 트랜잭션 롤백을 표시하는 예외

- javax.persistence.EntityExistsExcepiton ( Persist 호출시 이미 같은 엔티티 존재시 )
- javax.persistence.EntityNotFoundExcepiton ( 실제 사용 엔티티가 존재 하지 않을때 )
- javax.persistence.OptimisticLockException ( 낙관적 락 충돌 시 발생 )
- javax.persistence.PessimisticLockException ( 비관적 락 충돌시 발생 )
- javax.persistence.RollbackException ( commit 실패시 발생, 롤백 표시인 트랜잭션 커밋 시도 시 )
- javax.persistence.TransactionRequiredException ( 트랜잭션이 없을때 )

### 트랜잭션 롤백을 표시하지 않는 예외

- javax.persistence.NoResultException ( getSingleResult 호출시 결과가 없을 때 )
- javax.persistence.NonUniqueResultException ( getSingleResult() 호출시 결과가 둘이상일 때 )
- javax.persistence.LockTimeoutException ( 비관적 락 시간 초과 시 )
- javax.persistence.QueryTimeoutException ( 쿼리 실행 시간 초과 시 발생 )

### 스프링 프레임 워크의 JPA 예외 변환

서비스 계층에서 DAO에 직접 의존하는 것은 좋은 설계라고 할수 없다. 서비스 계층에서 JPA의 예외를 직접 사용시 JPA에 의존하게 된다. 따라서 이러한 문제를 해결하기 위해 접근 계층의 예외를 추상화해 개발자에게 제공한다.

### 트랜잭션 롤백시 주의사항

트랜잭션을 롤백하는 것은 데이터 베이스의 반영사항만 롤백 하는것이지 수정한 자바 객체까지 롤백해주지는 않는다. 따라서 객체는 수정된 채로 영속성 컨텍스트에 남아있게 된다. 때문에 영속성 컨텍스트를 그대로 사용하는 것은 위험하다. 

→ 새롭게 영속성 컨텍스트를 생성하거나, clear를 호출해 초기화 해라

기본 전략인 트랜잭션당 영속성 컨텍스트 (트랜잭션 생명주기 = 영속성 컨텍스트 생명주기) 전략은 문제 발생시 트랜잭션을 롤백하면서 영속성 컨텍스트도 종료 되기 때문에 문제가 발생하지 않는다.

OSIV일때가 문제다 영속성 컨텍스트의 범위가 트랜잭션보다 넓기 때문에 트랜잭션 롤백시 영속성 컨텍스트를 초기화해 잘못된 영속성 컨텍스트를 사용하는 문제를 예방해야 한다.

---

## 2. 엔티티 비교

같은 영속성 컨텍스트에서 엔티티를 조회하면 항상 같은 엔티티를 반환한다. ( 동등성equals 비교 수준이 아니라 주소값이 같은 인스턴스를 반환 )

```java
Member member1 = em.find(Member.class, "1L");
Member member2 = em.find(Member.class, "1L");

assertTrue(member1 == member2); // 둘은 같은 인스턴스이다.
```