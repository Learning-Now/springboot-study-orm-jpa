
# 스프링 데이터 JPA

대부분의 데이터 접근 계층은 CRUD를 반복해서 개발하게 된다. 

자바의 객체지향을 배울때 반복되는 코드는 지양하라 배웠는데 반복되는 코드? 

지금 부터 CRUD를 작성할때 중복을 줄이는 방법을 알아보자.

- 제네릭과 상속을 사용해 공통 부분을 처리하는 부모 클래스를 작성하라 (GenericDAO)

스프링 데이터 JPA는  CRUD를 처리하기 위해 공통 인터페이스를 제공한다.

→ 레포지토리 개발시 인터페이스만 작성ㅇ하면 구현 객체를 동적으로 생성 및 주입 시켜줌

(org.springframework.data.jpa.repository.JpaRepository)

```java
public interface MemberRepository extends JpaRepository<Member, Long> {

}
```

- 스프링 데이터 JPA는 스프링 데이터 프로젝트의 하위 프로젝트이며 JPA, 몽고DB 등의 접근을 추상화해 개발자의 편의를 제공

---

### JpaRepository 인터페이스의 계층 구조

![](https://images.velog.io/images/donglee99/post/6206584f-1d99-4bf0-8452-dc54da010ea8/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-08-05%20%EC%98%A4%ED%9B%84%209.54.20.png)

- 윗부분에 스프링 데이터 모듈이 존재 하며 그안에 스프링 데이터 프로젝트가 공통적을 사용하는 인터페이스가 들어있다.

**스프링 데이터 JPA가 지원하는 주요 기능**

- save(S) : 엔티티 저장, 수정
- delete(T) : 엔티티 삭제 EntityManager.remove()호출
- findOne(ID) : 하나의 엔티티 조회
- getOne(ID) : 엔티티 프록시 조회
- findAll(...) : 모든 엔티티 조회

---

## 쿼리 메소드 기능

스프링 데이터 JPA는 마법같은 기능을 제공한다. 바로 메소드 이름만으로 쿼리를 작성해주는 기능이다.

- 메소드 이름으로 쿼리생성
- 메소드 이름으로 JPA NamedQuery 호출
- @Query 어노테이션을 사용해 레포지토리 인터페이스에 쿼리 직접 호출

### 메소드 이름으로 쿼리 생성

```java
public interface MemberRepository extends Repository<Member, Long> {
	List<Member> findByEmailAndName (String email, String name);
}
```

→

```java
select m from Member m where m.email = ?1 and m.name = ?2
```

- JPA는 이름을 분석해 JPQL을 생성하고 실행한다.