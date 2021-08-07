# 객체지향 쿼리 언어
## 객체지향 쿼리
ORM을 사용하면 데이터베이스 테이블이 아닌 엔터티 객체를 대상으로 개발하기 때문에 검색도 테이블이 아닌 엔터티 객체를 대상으로 하는 방법이 필요하다. 이를 해결하기 위해 만들어진 것이 **JPQL**이다.

SQL이 데이터베이스 테이블을 대상으로 하는 데이터 중심의 쿼리라면 **JPQL은 엔터티 객체를 대상으로 하는 객체지향 쿼리**다.
JPQL을 사용하면 JPA는 이 JPQL을 분석한 다음 적절한 SQL을 마늘어 데이터베이스를 조회한다. 그리고 조회한 결과를 엔터티 객체를 생성해서 반환한다.

JPQL을 한마디로 정의하면 객체지향 SQL이다.

### JPA 공식 지원 기능
- **<span style=color:red>JPQL(Java Persistence Query Language)</span>**
- Criterai 쿼리(Criteria Query) : JPQL을 편하게 작성하도록 도와주는 API, 빌더 클래스 모음
- 네이티브 SQL(Native SQL) : JPA에서 JPQL 대신 직접 SQL을 사용할 수 있다.
- QueryDSL : Criteria 쿼리처럼 JPQL을 편하게 작성하도록 도와주는 빌더 클래스 모음, 비표준 오픈소스 프레임워크다.
- JDBC 직접 사용, MyBatis 같은 SQL 매퍼 프레임워크 사용 : 필요하면 JDBC를 직접 사용할 수 있다.

## JPQL
JPQL은 **엔터티 객체를 조회하는 객체지향 쿼리**다. JPQL은 **SQL을 추상화해서 특정 데이터베이스에 의존하지 않는다.** 그리고 데이터베이스 방언만 변경하면 JPQL을 수정하지 않아도 데이터베이스를 변경할 수 있다.

JPQL은 엔터티 직접 조회, 묵시적 조인, 다형성 지원으로 **SQL보다 간결**하다.

### JPQL의 특징
- JPQL은 객체지향 쿼리 언어다. 따라서 테이블을 대상으로 쿼리하는 것이 아니라 엔터티 객체를 대상으로 쿼리한다.
- JPQL은 SQL을 추상화해서 특정 데이터베이스 SQL에 의존하지 않는다.
- JPQL은 결국 SQL로 변환된다.

### JPQL의 사용
회원이름이 kim인 엔터티 조회
```java
String jpql = "select m from Member as m where m.username = 'kim'";
List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
```
실제 실행된 SQL
```sql
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

### 기본 문법과 쿼리 API
JPQL은 SQL과 비슷하게 SELECT, UPDATE, DELETE 문을 사용할 수 있다. 엔터티 저장 시에는 ```EntityManager.persist()``` 메소드를 사용하면 되므로 INSERT 문은 없다.
```
select_문 ::=
  select_절
  from_절
  [where_절]
  [groupby_절]
  [having_절]
  [orderby_절]
  
update_문 ::= update_절 [where_절]
delete_ans ::= delete_절 [where_절]
```
전체 구조를 보면 SQL과 비슷한 것을 알 수 있다.

#### SELECT 문
```
SELECT m FROM Member AS m where m.username = 'Hello'
```
- 대소문자 구분 : 엔터티와 속성은 대소문자를 구분하고, JPQL 키워드는 대소문자 구분하지 않음
- 엔터티 이름 : JPQL 사용시 ```Member```는 클래스 명이 아니라 엔터티 명
- 별칭은 필수 : AS로 엔터티의 별칭을 지어줌(AS는 생략 가능)

#### TypedQuery, Query
작성한 JPQL을 실행시키기 위해 만드는 **쿼리 객체**다.

- TypedQuery : 반환 타입 명확하게 지정
- Query : 반환 타입 명확하게 지정 불가

```em.createQuery()```의 두 번째 파라미터에 반환할 타입을 지정하면 TypedQuery로 반환하고 지정하지 않으면 Query를 반환한다.
Query객체는 조회 대상이 둘 이상이면 Object[], 조회 대상이 하나면 Object를 반환한다.

#### 결과 조회
- query.getResultList() : 결과를 컬렉션으로 반환(결과가 없으면 빈 컬렉션 반환)
- query.getSingleResult() : 결과가 정확히 하나일 때 사용
  \- 결과 없으면 ```javax.persistence.NoResultException``` 예외 발생
  \- 결과 1개보다 많으면 ```javax.persistence.NonUniqueResultException``` 예외 발생

### 파라미터 바인딩
JDBC는 위치 기준 파라미터 바인딩만 지원하지만 JPQL은 이름 기준 파라미터 바인딩도 지원한다.
- 이름 기준 파라미터(Named parameters) : 파라미터를 이름으로 구분(:name, name은 파라미터명)
```java
String usernameParam = "jeongyun";

TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.username = :usernmae", Member.class);

query.serParameter("username", usernameParam);
List<Member> resultList = query.getResultList();
```
\+ JPQL API는 대부분 메소드 체인 방식으로 설계되어 있어 다음과 같이 연속해서 작성 가능
```java
List<Member> members = em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class)
  .serParameter("username", usernameParam)
  .getResultList();
```
- 위치 기준 파라미터(Positional parameters) : 파라미터를 위치로 구분(?n, n은 1부터 시작)
```java
List<Member> members = em.createQuery("SELECT m FROM Member m where m.username = ?1", Member.class)
  .setParameter(1, usernameParam)
  .getResultList();
```
**<span style=color:red>이름 기준 파라미터 바인딩 방식</span>을 사용하는 것이 더 명확**하다.

### 프로젝션
프로젝션(projection)은 SELECT 절에 조회할 대상을 지정하는 것이다.
```SELECT {프로젝션 대상} FROM]```으로 대상을 선택한다.
- 엔터티 프로젝션
  **조회한 엔터티는 영속성 컨텍스트에서 관리**된다.
- 임베디드 타입 프로젝션
  임베디드 타입은 조회의 시작점이 될 수 없다.
  **임베디드 타입은 엔터티 타입이 아닌 값 타입이다. 따라서 직접 조회한 임베디드 타입은 영속성 컨텍스트에서 관리되지 않는다.**
- 스칼라 타입 프로젝션
  기본 데이터 타입들을 스칼라 타입이라 한다.
  중복 데이터를 제거하려면 ```DISTINCT```를 사용한다.

### 페이징 API
페이징 처리용 SQL을 작성하는 것은 반복적이고, SQL마다 문법이 달라 데이터베이스를 변경하려고 하면 코드를 수정하는 데에 어려움을 겪을 수 있다.
JPA는 페이징을 다음 두 API로 추상화했다.
- setFirstResult(int startPosition) : 조회 시작 위치(0부터 시작)
- setMaxResults(int maxResult) : 조회할 데이터 수

### JPQL 조인
#### 내부 조인
내부 조인은 ```INNER JOIN```을 사용한다.(```INNER```는 생략 가능)
```java
String teamName = "teamA";
String query = "SELECT m FROM Member m INNER JOIN m.team t" + "WHERE t.name = :teamName";

List<Member> members = em.createQuery(query, Member.class)
  .setParameter("teamName", teamName)
  .getResultList();
```
JPQL 내부 조인 구문은 SQL 조인과 약간 다른 것을 알 수 있다. JPQL 조인의 가장 큰 특징은 **연관 필드**를 사용한다는 것이다.
연관 필드는 다른 엔터티와 연관관계를 가지기 위해 사용하는 필드를 말한다.

```java
SELECT m FROM Member m JOIN Team t	// 잘못된 JPQL 조인, 오류
```

#### 외부 조인
외부 조인은 기능상 SQL의 외부 조인과 같다. ```OUTER```는 생략 가능해서 보통 ```LEFT JOIN```으로 사용한다.
```java
SELET m FROM Member m LEFT [OUTER] JOIN m.team t
```

#### 컬렉션 조인
일대다 관계나 다대다 관계처럼 컬렉션을 사용하는 곳에 조인하는 것을 컬렉션 조인이라 한다.
- 회원 -> 팀 : 다대일 조인, **단일 값 연관 필드(m.team)를 사용**한다.
- 팀 -> 회원 : 일대다 조인, **컬렉션 값 연관 필드(m.members)를 사용**한다.

#### 세타 조인
WHERE 절을 사용해 전혀 관계없는 엔터티도 조인할 수 있다.
**세타 조인은 내부 조인만 지원**한다.

#### JOIN ON 절
JOIN 시 ON 절을 사용하면 조인 대상을 필터링하고 조인할 수 있다. 내부 조인의 ON 절은 WHERE 절을 사용할 때와 결과가 같으므로 보통 ON 절은 외부 조인에서만 사용한다.

#### 페치 조인
페치 조인은 **JPQL에서 성능 최적화를 위해 제공**하는 기능으로, **<span style=color:red>실무에서 정말정말정말 중요하다.</span>** 이것은 연관된 엔터티나 컬렉션을 한 번에 같이 조회하는 기능인데 ```join fetch``` 명령어로 사용할 수 있다.
```
페치 조인 ::= [ LEFT [OUTER] | INNER ] JOIN FETCH 조인경로
```

#### 엔터티 페치 조인
```
select m from Member m join fetch m.team
```
페치 조인을 사용해 회원 엔터티를 조회하면서 연관된 팀 엔터티도 함게 조회하는 경우를 보면 ```join fetch```를 사용했다. 이렇게 하면 **연관된 엔터티나 컬렉션을 함께 조회**하는데 여기서는 회원과 팀을 함께 조회한다.

일반적인 JPQL 조인과는 다르게 페치 조인은 별칭을 사용할 수 없다.
(하이버네이트는 페치 조인에도 별칭을 허용)

실행된 SQL
```
SELECT
  M.*, T.*
FROM MEMBER M
INNER JOIN TEAM T ON M.TEAM_ID=T.ID
```
페치 조인을 사용하면 아래와 같이 SQL 조인을 시도한다.

![엔터티 페치 조인 시도](https://images.velog.io/images/minide/post/96f8a613-30a6-449f-a575-1f12c342f5bd/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-08-06%20%EC%98%A4%EC%A0%84%2011.19.38.png)

SQL에서 조인 결과는 아래와 같다.

![엔터티 페치 조인 결과 테이블](https://images.velog.io/images/minide/post/88d1c099-b736-4949-994d-819ed6fe5a4c/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-08-06%20%EC%98%A4%EC%A0%84%2011.19.43.png)

엔터티 페치 조인 JPQL에서 ```select m```으로 회원 엔터티만 선택했는데 실행된 SQL을 보면 회원과 연관된 팀도 함께 조회된 것을 확인할 수 있다.

![엔터티 페치 조인 결과 객체](https://images.velog.io/images/minide/post/a2744421-a1bb-4cac-b72b-4c4d4a7204f8/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-08-06%20%EC%98%A4%EC%A0%84%2011.19.48.png)


위 그림을 보면 회원과 팀 객체가 객체 그래프를 유지하면서 조회된 것을 확인할 수 있다.

페치 조인 사용 예시를 보자.

```java
String jpql = "select m from Member m join fetch m.team;
 
List<Member> members = em.createQuery(jpql, Member.class)
   .getResultList();
 
for (Member meber : members) {
  // 페치 조인으로 회원과 팀을 함께 조회해서 지연 로딩 발생 안함
  System.out.pringln("username = " +  member.getUsername() + ", teamname = " + member.getTeam().name());
```
```java
// 출력 결과
username = 회원1, teamname = 팀A
username = 회원2, teamname = 팀A
username = 회원3, teamname = 팀B
```

#### 컬렉션 페치 조인
일대다 관계인 컬렉션 페치 조인
```
select t
from Team t join fetch t.members
where t.name = '팀A'
```
실행된 SQL
```
SELECT
  T.*, M.*
FROM TEAM T
INNER JOIN MEMBER M ON T.ID=M.TEAM_ID
WHERE T.NAME = '팀A'
```
컬렉션을 페치 조인한 JPQL에서 ```select t```로 팀만 선택했는데 실행된 SQL을 보면 연관된 회원도 함께 조회한 것을 알 수 있다.

![컬렉션 페치 조인 시도](https://images.velog.io/images/minide/post/0a5222b8-dd77-4f99-a81d-c19d24beb031/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-08-06%20%EC%98%A4%EC%A0%84%2011.47.12.png)

위 그림을 보면 TEAM 테이블에서 '팀A'는 하나지만 MEMBER 테이블과 조인하면서 결과가 증가해서 아래 조인 결과 테이블을 보면 같은 '팀A'가 2건 조회되었다.

![컬렉션 페치 조인 결과 테이블](https://images.velog.io/images/minide/post/c2669a55-b20b-48d2-97b7-1345ad3106da/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-08-06%20%EC%98%A4%EC%A0%84%2011.47.16.png)

따라서 아래 그림의 컬렉션 페치 조인 결과 객체에서 teams 결과 예제를 보면 주소가 0x100으로 같은 '팀A'를 2건 가지게 된다.

![컬렉션 페치 조인 결과 객체](https://images.velog.io/images/minide/post/8c83bc45-5efe-422b-9554-74f05dc859b3/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-08-06%20%EC%98%A4%EC%A0%84%2011.47.22.png)

컬렉션 페치 조인 사용 예시를 보자.
```java
String jpql = "select t from Team t join fetch t.members where t.name = '팀A'";
List<Team> teams = em.createQuery(jpql, Team.class).getResultList();

for (Team team : teams) {
  System.out.println("teamname = " + team.getName() + ", team = " + team);
  
  for (Member member : team.getMembers()) {
    // 페치 조인으로 팀과 회원을 함께 조회해서 지연 로딩 발생 안 함
    System.out.println("-> username = " + member.getUsername() + ", member = ", member);
```

```java
// 출력 결과
teamname = 팀A, team = Team@0x100
-> username = 회원1, member = Member@0x200
-> username = 회원2, member = Member@0x300
teamname = 팀A, team = Team@0x100
-> username = 회원1, member = Member@0x200
-> username = 회원2, member = Member@0x300
```
출력 결과를 보면 같은 '팀A'가 2건 조회된 것을 확인할 수 있다.

#### 페치 조인과 DISTINCT
SQL의 ```DISTINCT```는 중복된 결과를 제거하는 명령어다. JPQL의 ```DISTINCT``` 명령어는 SQL에 ```DISTINCT```를 추가하는 것은 물론이고 애플리케이션에서 한 번 더 중복을 제거한다.

```java
select distinct t
from Team t join fetch t.members
where t.name = '팀A'
```
이렇게 작성하면 SQL에 ```DISTINCT```가 적용된다.

다음으로 애플리케이션에서 ```distinct``` 명령어를 보고 중복된 데이터를 걸러낸다.
```select distinct t```는 팀 엔터티의 중복을 제거하라는 것이므로 중복된 팀A는 하나만 조회된다.

#### 페치 조인 vs 일반 조인
일반 조인 실행 시 연관된 엔터티를 함께 조회하지 않는다. 반면 페치 조인을 사용하면 **연관된 엔터티도 함께 조인(즉시 로딩)**한다. 페치 조인은 객체 그래프를 SQL 한 번에 조회하는 개념이다.

#### 페치 조인의 특징과 한계
- 페치 조인 대상에는 별칭을 줄 수 없다.
  하이버네이트는 가능하지만 가급적 사용하지 않는다.
- 둘 이상의 컬렉션은 페치 조인 할 수 없다.
- 컬렉션을 페치 조인하면 페이징 API를 사용할 수 없다.
    - 일대일, 다대일 같은 단일 값 연관 필드들은 페치 조인해도 페이징 가능
    - 하이버네이트는 경고 로그를 남기고 메모리에서 페이징(매우 위험)
- 연관된 엔터티들을 SQL 한 번으로 조회 -> 성능 최적화
- 엔터티에 직접 적용하는 글로벌 로딩 전략보다 우선함
  (@OneToMany(fetch = FetchType.LAZY) // 글로벌 로딩 전략)
- 실무에서 글로벌 로딩 전략은 모두 지연 로딩
- 최적화가 필요한 곳은 페치 조인 적용

#### 정리
- 모든 것을 페치 조인으로 해결할 수는 없다.
- 페치 조인은 객체 그래프를 유지할 때 사용하면 효과적이다.
- 여러 테이블을 조인해서 엔터티가 가진 모양이 아닌 전혀 다른 결과를 내야 한다면, 페치 조인보다는 일반 조인을 사용하고 필요한 데이터들만 조회해서 DTO로 반환하는 것이 효과적이다.

### 경로 표현식
경로 표현식은 .(점)을 찍어 객체 그래프를 탐색하는 것이다.

#### 용어 정리
- 상태 필드(state field) : 단순히 값을 저장하기 위한 필드(필드 or 프로퍼티)
- 연관 필드(association field) : 연관관계를 위한 필드, 임베디드 타입 포함(필드 or 프로퍼티)
    - 단일 값 연관 필드 : @ManyToOne, @OneToOne, 대상이 **엔터티**
    - 컬렉션 값 연관 필드 : @OneToMany, @ManyToMany, 대상이 **컬렉션**

#### 특징
- 상태 필드 경로 : 경로 탐색의 끝이다. 더는 탐색할 수 없다.
- 단일 값 연관 경로 : **묵시적으로 내부 조인**이 일어난다. 단일 값 연관 경로는 계속 탐색할 수 있다.
- 컬렉션 값 연관 경로 : **묵시적으로 내부 조인**이 일어난다. 더는 탐색할 수 없다. 단 FROM 절에서 조인을 통해 별칭을 얻으면 별칭으로 탐색할 수 있다.
> 단일 값 연관 필드로 경로 탐색을 하면 SQL에서 내부 조인이 일어나는데 이것을 **묵시적 조인**이라 한다.
**묵시적 조인은 모두 내부 조인**이고, 외부 조인은 명시적으로 JOIN 키워드를 사용해야 한다.

#### 경로 탐색을 사용한 묵시적 조인 시 주의사항
- **항상 내부 조인**이다.
- 컬렉션은 경로 탐색의 끝이다. 컬렉션에서 경로 탐색을 하려면 명시적으로 조인해서 별칭을 얻어야 한다.
- 경로 탐색은 주로 SELECT, WHERE 절(다른 곳에서도 사용됨)에서 사용하지만 묵시적 조인으로 인해 SQL의 FROM 절에 영향을 준다.

묵시적 조인은 조인이 일어나는 상황을 한눈에 파악하기 어렵다는 단점이 있다. 따라서 단순하고 성능에 이슈가 없으면 크게 문제가 안되지만 성능이 중요하면 분석하기 쉽도록 명시적 조인을 사용하는 것이 좋다.

### 서브 쿼리
JPQL도 SQL처럼 서브 쿼리를 지원하나 WHERE, HAVING 절에서만 사용할 수 있고 SELECT, FROM 절에서는 사용할 수 없다.

## Criteria
Criteria는 JPQL을 생성하는 빌더 클래스다. Criteria의 장점은 **문자가 아닌 프로그래밍 코드로 JPQL을 작성**할 수 있다는 점이다.

### 장점
- 컴파일 시점에 오류를 발견할 수 있다.
- IDE를 사용하면 코드 자동완성을 지원한다.
- 동적 쿼리를 작성하기 편하다.

### Criteria 예시
```java
// Criteria 사용 준비
CriteriaBuilder cb = em.getCriteriaBuilder();
CriteriaQuery<Member> query = cb.createQuery(Member.class);

// 루트 클래스(조회를 시작할 클래스)
Root<Member> m = query.from(Member.class);

// 쿼리 생성
CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
List<Member> resultList = em.createQuery(cq).getResultList();
```

예제를 보면 쿼리가 문자가 아닌 코드로 작성한 것을 확인할 수 있다. 아쉬운 점은 필드 명을 문자로 작성했는데, 이 부분도 코드로 작성하고 싶으면 메타 모델(MetaModel)로 사용하면 된다.

```java
// 메타 모델 사용 전 -> 사용 후
m.get("username") -> m.get(Member_.username)
```

Criteria는 많은 장점을 가지고 있지만 복잡하고 장황하다. 따라서 사용하기 불편할 뿐만 아니라 Criteria로 작성한 코드도 한눈에 들어오지 않는 단점이 있다.

## QueryDSL
QueryDSL도 Criteria처럼 JPQL 빌더 역할을 한다.

QueryDSL의 장점은 **코드 기반이면서 단순하고 사용하기 쉽다.** 그리고 JPQL과 비슷해서 한눈에 들어온다.

### QueryDSL 예시
```java
// 준비
JPAQuery query = new JPAQuery(em);
QMember member = QMember.member;

// 쿼리, 결과조회
List<Member> members = query.from(member)
  .where(member.username.eq("kim"))
  .list(member);
```

## 네이티브 SQL
네이티브 SQL은 JPQ에서 SQL을 직접 사용할 수 있는 기능이다.

JPQL을 사용해도 특정 데이터베이스에 의존하는 기능을 사용해야 할 때가 있는데, 이런 기능들은 표준화되어 있지 않으므로 JPQL에서 사용할 수 없다. 혹은 SQL은 지원하지만 JPQL이 지원하지 않는 기능도 있다. 이때는 네이티브 SQL을 사용하면 된다.

네이티브 SQL의 단점은 특정 데이터베이스에 의존하는 SQL을 작성해야 한다는 것이다. 따라서 데이터베이스를 변경하면 네이티브 SQL도 수정해야 한다.

#### 네이티브 SQL 예시
```java
String sql = "SELECT ID, AGE, TEAM_ID, NAME FROM MEMBER WHERE NAME = 'kim'";
List<Member> resultList = em.createNativeQuery(sql, Member.class).getResultList();
```
네이티브 SQL은 ```em.createNativeQuery()```를 사용하고 나머지 API는 JPQL과 같다.

## JDBC 직접 사용, 마이바티스 같은 SQL 매퍼 프레임워크 사용
이런 일은 드물지만 JDBC 커넥션에 직접 접근하고 싶은 경우 JPA는 JDBC 커넥션을 획득하는 API를 제공하지 않아 JPA 구현체가 제공하는 방법을 사용해야 한다.

하이버네이트에서 직접 JDBC Connection을 획득하는 방법은 아래와 같다.
```java
Session session = entityManager.unwrap(Session.class);
session.doWork(new Work()) {
  @Override
  public void execute(Connection connection) throw SQLException {
    // work...
  }
});
```

**JDBC나 마이바티스를 JPA와 함께 사용하면 영속성 컨텍스트를 적절한 시점에 강제로 플러시해야 한다.** JDBC를 직접 사용하든 마이바티스 같은 SQL 매퍼와 사용하든 모두 JPA를 우회해서 데이터베이스에 접근한다. 문제는 JPA를 우회하는 SQL에 대해서는 JPA가 전혀 인식하지 못한다는 점이다. 최악의 경우 영속성 컨텍스트와 데이터베이스를 불일치 상태로 만들어 데이터 무결성을 훼손할 수 있다.

이런 이슈를 해결하는 방법은 **JPA를 우회해서 SQL을 실행하기 직전에 영속성 컨텍스트를 수동으로 플러시해서 데이터베이스와 영속성 컨텍스트를 동기화**하면 된다.

## 객체지향 쿼리 심화
### 벌크 연산
엔터티를 수정하려면 영속성 컨텍스트의 변경 감지 기능이나 병합을 사용하고, 삭제하려면 ```EntityManager.remove()``` 메소드를 사용한다. 하지만 수백개 이상의 엔터티를 이 방법으로 하나씩 처리하기에는 시간이 너무 오래 걸리므로 여러 건을 한 번에 수정하거나 삭제하는 벌크 연산을 사용한다.

#### 벌크 연산 예시
```java
// UPDATE 벌크 연산
STring qlString = 
  "update Product p " +
  "set p.price = p.price * 1.1 " +
  "where p.stockAmount < :stockAmount";
  
int resultCount = em.createQuery(qlString)
  .setParameter("stockAmount", 10)
  .executeUpdate();
  
// DELETE 벌크 연산
String qlString = "delete from Product p " +
  "where p.price < :price";
  
int resultCount = em.createQuery(qlString)
  .setParameter("price", 100)
  .executeUpdate();
```
벌크 연산은 ```executeUpdate()``` 메소드를 사용하고, 이 메소드는 벌크 연산으로 영향을 받은 엔터티 건수를 반환한다.

#### 벌크 연산의 주의점
벌크 연산은 영속성 컨텍스트를 무시하고 데이터베이스에 직접 쿼리한다. 이런 문제를 해결하는 방법을 알아보자.
- em.refresh() 사용
  벌크 연산을 수행한 직후에 정확한 엔터티를 사용해야 한다면 ```em.refresh(엔터티)```를 사용해서 데이터베이스에서 엔터티를 다시 조회
- 벌크 연산 먼저 실행
  가장 실용적인 해결책은 벌크 연산을 가장 먼저 실행하는 것이다. 이 방법은 JPA와 JDBC를 함께 사용할 때도 유용하다.
- 벌크 연산 수행 후 영속성 컨텍스트 초기화
  벌크 연산을 수행한 직후에 바로 영속성 컨텍스트를 초기화해서 영속성 컨텍스트에 남아 있는 엔터티를 제거하는 것도 좋은 방법이다.

벌크 연산은 영속성 컨텍스트와 2차 캐시를 무시하고 데이터베이스에 직접 시행하므로 영속성 컨텍스트와 데이터베이스 간에 데이터 차이가 발생할 수 있으므로 주의해서 사용해야 한다. **가능하면 벌크 연산을 가장 먼저 수행**하는 것이 좋고 상황에 따라 영속성 컨텍스트를 초기화하는 것도 필요하다.

### 영속성 컨텍스트와 JPQL
#### 쿼리 후 영속 상태인 것과 아닌 것
JPQL의 조회 대상은 엔터티, 임베디드 타입, 값 타입 같이 다양한 종류가 있다. JPQL로 엔터티를 조회하면 영속성 컨텍스트에서 관리되지만 엔터티가 아닌 영속성 컨텍스트에서 관리되지 않는다.
즉, **조회한 엔터티만 영속성 컨텍스트가 관리**한다.

#### JPQL로 조회한 엔터티와 영속성 컨텍스트
JPQL로 데이터베이스에서 조회한 엔터티가 영속성 컨텍스트에 이미 있으면 JPQL로 데이터베이스에서 조회한 결과를 버리고 대신 영속성 컨텍스트에 있던 엔터티를 반환한다. 이때 식별자 값을 사용해서 비교한다.

이를 정리하면 다음과 같다.


- JPQL로 조회한 엔터티는 영속 상태다.
- 영속성 컨텍스트에 이미 존재하는 엔터티가 있으면 기존 엔터티를 반환한다.


그런데 왜 데이터베이스에 새로 조회한 엔터티를 버리고 영속성 컨텍스트에 있는 기존 엔터티를 반환하는 것일까?
JPQL로 조회한 새로운 엔터티를 영속성 컨텍스트에 하나 더 추가하거나 기존 엔터티를 새로 검색한 엔터티로 대체하면 아래와 같은 문제가 발생한다.

1. 새로운 엔터리를 영속성 컨텍스트에 하나 더 추가한다.
   : 영속성 컨텍스트는 기본 키 값을 기준으로 엔터리를 관리하므로 같은 기본 키 값을 가진 엔터티를 등록할 수 없다.
2. 기존 엔터티를 새로 검색한 엔터티로 대체한다.
   : 이 방법을 사용하면 영속성 컨텍스트에 수정 중인 데이터가 사라질 수 있으므로 위험하다.

**영속성 컨텍스트는 영속 상태인 엔터티의 동일성을 보장**한다. ```em.find()```로 조회하든 JPQL을 사용하든 영속성 컨텍스트가 같으면 동일한 엔터티를 반환한다.

#### find() vs JPQL
```em.find()``` 메소드는 엔터티를 영속성 컨텍스트에서 먼저 찾고 없으면 데이터베이스에서 찾는다. 이는 해당 엔터티가 영속성 컨텍스트에 있으면 메모리에서 바로 찾으므로 성능상 이점이 있다.(그래서 **1차 캐시**라 부름)
반면 **JPQL은 항상 데이터베이스에 SQL을 실행해서 결과를 조회**한다.

JPQL의 특징을 정리해보자.
- JPQL은 항상 데이터베이스를 조회한다.
- JPQL로 조회한 엔터티는 영속 상태다.
- 영속성 컨텍스트에 이미 존재하는 엔터티가 있으면 기존 엔터티를 반환한다.

### JPQL과 플러시 모드
플러시는 영속성 컨텍스트의 변경 내역을 데이터베이스에 동기화하는 것이다. JPA는 플러시가 일어날 때 영속성 컨텍스트에 등록, 수정, 삭제한 엔터티를 찾아 INSERT, UPDATE, DELETE SQL을 만들어 데이터베이스에 반영한다.

플러시를 호출하려면 ```em.flush()``` 메소드를 직접 사용해도 되지만 보통 플러시 모드에 따라 커밋하기 직전이나 쿼리 실행 직전에 자동으로 플러시가 호출된다.

```
em.setFlushMode(FlushModeType.AUTO);	// 커밋 또는 쿼리 실행 시 플러시(기본값)
em.setFlushMode(FlushModeType.COMIT);	// 커밋 시에만 플러시(성능 최적화를 위해 꼭 필요할 때만 사용)
```