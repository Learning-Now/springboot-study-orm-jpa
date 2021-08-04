
# 객체지향 쿼리 언어

JPA는 복잡한 검색 조건을 사용해 엔티티 객체를 조회할 수 있는 다양한 쿼리 기술을 지원한다.

- JPQL
- Criteria
- QueryDSL
- 네이티브 SQL
- 객체지향 쿼리 심화

---

## 객체지향 쿼리 소개

EntityManager.find() 메소드를 사용하면 식별자로 엔티티 조회가 가능하다. 이 기능은 가장 단순한 검색 방법으로 애플리케이션 개발에 사용하기에는 부족하다. 

모든 회원 엔티티를 메모리에 올려둔채 검색하는 방법은 너무 터무니 없는 방법이므로 이를 보완하기 위해 ORM을 사용해 **테이블을 베이스**가 아닌 **엔티티를 기준**으로 검색이 가능하다.

**JPQL** 은 이를 해결하기 위해 만들어졌고 다음과 같은 특징이 있다. 

- 테이블이 아닌 객체를 대상으로 검색하는 객체지향 쿼리
- SQL을 추상화 해서 특정 데이터베이스 SQL에 의존하지 않는다.

🖍 **JPA가 공식적으로 지원하는 기능**

- JPQL
- Criteria 쿼리
- 네이티브 SQL
- QueryDSL
- JDBC직접 사용

---

# JPQL

- 객체지향 쿼리언어, 객체 대상
- 특정 SQL의존 X
- 결국 SQL로 변환

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/edf8d5c0-9aee-44a3-bd04-565932bf3c77/스크린샷_2021-05-07_오전_10.31.32.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/edf8d5c0-9aee-44a3-bd04-565932bf3c77/스크린샷_2021-05-07_오전_10.31.32.png)

## JPQL 사용 예시

```java
String jpql = "select m from Member as m where m.username = 'kim'";
List<Member> resultList = 
		em.createQuery(jpql, Member.class).getResultList();
```

실제 실행된 Sql

```java
select
	member.id as id,
	member.age as age,
	member.team_id as team,
	member.name as name
from
	Member member
where
	member.name='kim'
```

## 기본 문법과 쿼리 API

JPQL도  SQL처럼 Select, Update, Delete를 사용할수 있다. ( Insert는 따로 존재하지 않는다. persist로 대체)

```java
//JPQL문법

select_문 : : =
	select_절
	from_절
	[where_절]
	[groupby_절]
	[having_절]
	[orderby_절]

update_문 :: = update_절 [where_절]
delete_문 :: = delete_절 [where_절]
```

다음을 보면 SQL문과 유사한걸 알수있다.

### SELECT 문

```java
SELECT m FROM Member AS m where m.username = 'Hello'
```

- 대소문자 구분 (엔티티와 속성은 대소문자를 구분한다)
- 엔티티 이름 (Member는 클래스명이 아니라 엔티티 명이다)
- 별칭은 필수 (Member AS m 을 보면 Member에 m 이라는 별칭을 줌)

### TypeQuery, Query

반환 타입이 명확하게 지정 and 하나 = TypeQuery

여러개 or 불명확 = Query ( 반환 타입이 2개이상 ⇒  Object [] 반환 / 1개 ⇒ Object)

### 파라미터 바인딩

JDBC는 위치 기준 파라미터 바인딩을 지원하지만 JPQL은 이름 기준 파라미터 바인딩도 지원한다. (이게 무슨 말인지는 다음을 보면 알수 있다)

```java
String usernameParam = "User1";

TypedQuery<Member> query =
		em.createQuery("SELECT m FROM Member m where m.username = :username",
Member.class);

query.setParameter("username", usernameParam);
List<Member> resultList = query.getResultList();
```

- :username 이라는 이름을 기준 파라미터를 정의하고 set 을 통해 파리미터에 값을 바인딩해주는 것을 볼수 있다. 이때 만약 위치 기준이였다면 위치값을 기준으로 바인딩을 해준다.

    ‼️ **이름을 기준으로 바인딩하는것이 더 명확하다.**

## 페치 조인(Fetch Join)

[페치조인](https://www.notion.so/9656adcfb89c44f491a048b2c11a74bf)

## 경로 표현식

.(점)을 찍어 객체 그래프를 탐색하는것을 경로 표현식이라 한다.

**용어정리**

- 상태필드 : 단순히 값을 저장하기 위한 필드
- 연관필드 : 연관관계를 위한 필드, 임베디드 타입 포함
    - 단일 값 연관 : @ManyToOne, @OneToOne
    - 컬렉션 값 연관 : @OneToMany, @ManyToMany

**특징**

- 상태 필드 경로 : 경로 탐색의 끝
- 단일 값 연관 경로 : 묵시적으로 내부 조인이 일어난다. 단일 값 연관 경로는 계속 탐색할 수 있다.
- 컬렉션 값 연관 경로 : 묵시적으로 내부 조인이 일어나며 더는 탐색 불가능

‼️ 경로 탐색을 사용한 묵시적 조인 주의사항

- 항상 내부조인
- 컬렉션은 경로탐색의 끝 경로 탐색을 위해서는 별도의 별칭을 얻어야함
- 내부 조인보다는 명시적 조인을 사용하자

➕프로젝션의 대상은 엔티티(영속성 컨텍스트가 관리), 엠비디드 타입(영속성 컨텍스트가 관리 X), 스칼라타입(숫자, 문자, 기본 데이터 타입)

➕ New를 통한 객체 변환 ( 패키지명을 포함한 전체 클래스명을 입력해야함, 순서와 타입이 일치하는 생성자 필요)

```java
TypedQuery<UserDTO> query = 
		em.createQuery("SELECT new jpabook.jpql.UserDTO(m.username, m.age)
		FROM Member m", UserDTO.class);
```

---

## Criteria

criteria 는 JPQL을 생성하는 빌더 클래스이다. 빌더 클래스의 장점은 **프로그래밍 코드로 JPQL을 작성할 수 있다는 점이다.**

☝️ 만약 JPQL에 select m from mememem m 같은 오타가 있을 경우 컴파일이 성공하고 배포가 가능하다 하지만 쿼리가 실행되는 시점에 오류가 나게 된다. 이는 실제 운영되는 서비스라면 큰 문제이다

**장점**

- 컴파일 시점에 오류 발견 가능
- IDE를 사용하면 코드 자동완성 기능 지원
- 동적 쿼리 작성 유리

**EX)**

```java
//Criteria 사용 준비
CriteriaVBuilder cb = em.getCriteriaBuilder();
CriteriaQuery<Member> query = cb.createQuery(Member.class);

//루트 클래스
Root<Member> m = query.from(Member.class);

//쿼리 생성
CriteriaQuery<Member> cq = 
		query.select(m).where(cb.equal(m.get("username"). "kim"));
List<Member> resultList = em.createQuery(cq).getResultList();
```

다음 코드를 보면 JPQL을 코드로 매핑하면서 작성한것을 볼수 있다. 하지만 아직도 "username" 부분은 문자열로 작성을 했다. 이를 코드로 작성하고 싶다면 메타 모델로 바꾸면 된다.

```java
m.get("username") -> m.get(Member_.username)
```

🤔 Criteria는 코드로 작성할수 있기때문에 동적 쿼리를 작성할때 유용하지만 코드가 너무 복잡하다....

---

## QueryDSL

Criteria와 같이 JPQL빌더 역할을 한다. QueryDSL의 가장 큰 장점은 코드 기반이며 단순하고 사용하기 쉽다는 점이다. 

EX)

```java
//준비
JPAQuery query = new JPAQuery(em);
QMember member = QMember.member;

//쿼리, 결과 조회
List<Member> members = 
	query.from(member)
		.where(member.username.eq("kim"))
		.list(member);
```

---

# Reference

[https://catsbi.oopy.io/137e5070-40d5-4799-a283-a747fb7a0f2d](https://www.notion.so/137e507040d54799a283a747fb7a0f2d)