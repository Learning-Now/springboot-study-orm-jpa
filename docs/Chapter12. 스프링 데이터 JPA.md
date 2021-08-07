# 스프링 데이터 JPA
## 스프링 데이터 JPA
스프링 데이터 JPA는 스프링 프레임워크에서 JPA를 편리하게 사용할 수 있도록 지원하는 프로젝트다. 이 프로젝트는 데이터 접근 계층ㅇ을 개발할 때 반복되는 CRUD 문제를 세련된 방법으로 해결한다.

스프링 데이터 JPA는 CRUD를 처리하기 위한 공통 인터페이스를 제공한다. 그리고 레포지토리를 개발할 때 인터페이스만 작성하면 실행 시점에 스프링 데이터 JPA가 구현 객체를 동적으로 생성해서 주입해준다. 따라서 **데이터 접근 계층을 개발할 때 구현 클래스 없이 인터페이스만 작성해도 된다.** CRUD를 처리하기 위한 공통 메소드는 스프링 데이터 JPA가 제공하는 ```org.springframework.data.jpa.repository.JpaRepository``` 인터페이스에 있다.

## 공통 인터페이스 기능
스프링 데이터 JPA는 간단한 CRUD 기능을 공통으로 처리하는 JpaRepository 인터페이스를 제공한다. 스프링 데이터 JPA를 사용하는 가장 단순한 방법은 이 인터페이스를 상속받는 것이다. 그리고 제네릭에 엔터티 클래스와 엔터티 클래스가 사용하는 식별자 타입을 지정하면 된다.
```java
// JpaRepository를 사용하는 인터페이스
public interface MemberRepository extends JpaRepository<Member, Long> {
}
```

JpaRepository 인터페이스를 상속받으면 사용할 수 있는 주요 메소드를 알아보자.
(T는 엔터티, ID는 엔터티의 식별자 타입, S는 엔터티와 그 자식 타입)

- save(S) : 새로운 엔터티를 저장하고 이미 있는 엔터티는 수정
- delete(T) : 엔터티 하나를 삭제. 내부에서 ```EntityManager.remove()```를 호출
- findOne(ID) : 엔터티 하나를 조회. 내부에서 ```EntityManager.find()```를 호출
- getOne(ID) : 엔터티를 프록시로 조회. 내부에서 ```EntityManager.getReference()```를 호출
- findAll(...) : 모든 엔터티를 조회. 정령(Sort)이나 페이징(Pageable) 조건을 파라미터로 제공할 수 있다.

save(S) 메소드는 엔터티에 식별자 값이 없으면(null이면) 새로운 엔터티로 판단해서 ```EntityManager.persist()```를 호출하고 식별자 값이 있으면 이미 있는 엔터티로 판단해서 ```EntityManager.merge()```를 호출한다.

## 쿼리 메소드 기능
쿼리 메소드 기능은 스프링 데이터 JPA가 제공하는 기능으로, 대표적으로 메소드 이름만으로 쿼리를 생성하는 기능이 있다. 이는 인터페이스에 메소드만 선언하면 해당 메소드의 이름으로 적절한 JPQL 쿼리를 생성해서 실행한다.

스프링 데이터 JPA가 제공하는 쿼리 메소드 기능은 크게 3가지가 있다.
- 메소드 이름으로 쿼리 생성
- 메소드 이름으로 JPA NamedQuery 호출
- @Query 어노테이션을 사용해 레포지토리 인터페이스에 쿼리 직접 정의

### 메소드 이름으로 쿼리 생성
인터페이스에 정의한 메소드를 실행하면 스프링 데이터 JPA는 메소드 이름ㅇ르 분석해서 JPQL을 생성하고 실행한다. 이때 스프링 데이터 JPA 공식 문서가 제공하는 표를 참고해 정해진 규칙에 따라 메소드 이름을 지어야 한다.

![스프링 데이터 JPA 쿼리 생성 기능](https://images.velog.io/images/minide/post/099ce088-f67a-4bc9-a488-e949ba453d24/%20Supported%20keywords%20inside%20method%20names%20Keyword%20Sample.png)

이 기능은 엔터티의 필드명이 변경되면 인터페이스에 정의한 메소드 이름도 변경해야 한다. 그렇지 않으면 애플리케이션 시작 시점에 오류가 발생한다.

### JPA NamedQuery
스프링 데이터 JPA는 메소드 이름으로 JPA Named 쿼리를 호출하는 기능을 제공한다.
JPA Named 쿼리는 이름 그대로 쿼리에 이름을 부여해서 사용하는 방법인데, 어노테이션이나 XML에 쿼리를 정의할 수 있다. 그리고 같은 방법으로 Named 네이티브 쿼리도 지원한다.

```java
// @NamedQuery 어노테이션으로 Named 쿼리 정의
@Entity
@NamedQuery(
  name="Member.finByUsername",
  query="select m from Member m where m.username = :username")
public class Member {
...
}

// orm.xml의 XML 사용
<named-query name="Member.findByUsername">
  <query><CDATA> [
    select m
    from Member m
    where m.username = :username
  ]></query>
</named-query>
```
이렇게 정의한 Named 쿼리르 JPA에서 직접 호출하려면 아래 코드처럼 작성해야 한다.
```java
// JPA를 직접 사용해서 Named 쿼리 호출
public class MemberRepository {
  public List<Member> findByUsername(String username) {
  ...
    List<Member> resultList = em.createNamedQuery("Member.findByUsernmae", Member.class)
    .setParameter("username", "회원1")
    .getResultList();
```
스프링 데이터 JPA를 사용하면 아래와 같이 메소드 이름만으로 Named 쿼리를 호출할 수 있다.
```Java
public interface MemberRepository extends JpaRepository<Member, Long> {	// 여기 선언한 Member 도메인 클래스
  List<Member> findByUsername(@Param("username") String username);
}
```
스프링 데이터 JPA는 선언한 "도메인 클래스 + .(점) + 메소드 이름"으로 Named 쿼리를 찾아서 실행한다.

### @Query, 레포지토리 메소드에 쿼리 정의
레포지토리 메소드에 직접 쿼리를 작성하려면 ```@Query``` 어노테이션을 사용한다. 이 방법은 실행할 메소드에 정적 쿼리를 직접 작성하므로 이름 없는 Named 쿼리라 할 수 있다. 또한 JPA Named 쿼리처럼 애플리케이션 실행 시점에 문법 오류를 발견할 수 있는 장점이 있다.

```java
public interface MemberRepository extends JpaRepository<Member, Long> {
  @Query("select m from Member m where m.username = ?1")
  Member findByUsername(String username);
}
```
네이티브 SQL을 사용하려면 ```@Query``` 어노테이션에 ```nativeQuery = true```를 설정한다.
스프링 데이터 JPA가 지원하는 파라미터 바인딩을 사용하면 JPQL은 위치 기반 파라미터를 1부터 시작하지만 네이티브 SQL은 0부터 시작한다.

### 파라미터 바인딩
스프링 데이터 JPA는 위치 기반 파라미터 바인딩과 이름 기반 파라미터 바인딩을 모두 지원한다.

기본값은 위치 기반인데 파라미터 순서로 바인딩한다. 이름 기반 파라미터 바인딩을 사용하려면 ```@Param``` 어노테이션을 사용하면 된다. 코드 가독성과 유지보수를 위해 이름 기반 파라미터 바인딩을 사용하는 것이 좋다.

### 반환 타입
스프링 데이터 JPA는 유연한 반환 타입을 지원하는데 결과가 한 건 이상이면 컬렉션 인터페이스를 사용하고, 단건이면 반환 타입을 지정한다.

```java
List<Member> findByName(String name);	// 컬렉션
Member findByEmail(String email);	// 단건
```

만약 조회 결과가 없으면 컬렉션은 빈 컬렉션을, 단건은 null을 반환한다. 그리고 단건을 기대하고 반환 타입을 지정했는데 결과가 2건 이상 조회되면 ```javax.persistence.NonUniqueResultException``` 예외가 발생한다.

## 사용자 정의 레포지토리 구현
스프링 데이터 JPA는 레포지토리를 개발하면 인터페이스만 정의하고 구현체는 만들지 않는다. 하지만 메소드를 직접 구현해야 할 때도 있다. 하지만 레포지토리를 직접 구현하면 공통 인터페이스가 제공하는 기능까지 모두 구현해야 한다. 스프링 데이터 JPA는 이런 문제를 우회해서 필요한 메소드만 구현할 수 있는 방법을 제공한다.

먼저 직접 구현할 메소드를 위한 사용자 정의 인터페이스를 작성해야 한다. 이때 인터페이스 이름은 자유롭게 적는다.

```java
public interface MemberRepositoryCustom {
  public List<Member> findMemberCustom();
}
```

다음으로 아래와 같이 사용자 정의 인터페이스를 구현한 클래스를 작성한다. 이때 클래스 이름을 짓는 규칙이 있는데 **레포지토리 인터페이스 이름 + Impl**로 지어야 한다. 이렇게 하면 스프링 데이터 JPA가 사용자 정의 구현 클래스로 인식한다.

```java
public class MemberRepositoryImpl implements MemberRepositoryCustom {
  @Override
  public List<Member> findMemberCustom() {
    ...	// 사용자 정의 구현
  }
}
```

마지막으로 레포지토리 인터페이스에서 사용자 정의 인터페이스를 상속받으면 된다.

```java
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
```

***
**참고**
[Spring Data JPA - Reference Documentation](https://docs.spring.io/spring-data/jpa/docs/2.5.3/reference/html/#jpa.repositories)