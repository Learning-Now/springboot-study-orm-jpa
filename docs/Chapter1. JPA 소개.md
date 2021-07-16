# JPA 소개
## JPA(Java Persistence API)란
애플리케이션의 데이터를 객체지향 관점으로 바라보고 다룰 수 있게 해주는 자바 진영의 ORM 기술표준인 객체지향 기술 표준이다.
> ### ORM(Object-Relational Mapping)
객체와 관계형 데이터베이스 간의 차이를 중간에서 해결하도록 매핑해주는 것
SQL 작성없이 객체를 데이터베이스에 직접 저장할 수 있게 도와주는 기술로 애플리케이션과 JDBC 사이에서 동작한다.

## JPA를 사용하는 이유
1. CRUD SQL을 작성할 필요가 없다.
2. 조회된 결과를 객체로 매핑하는 작업도 대부분 자동으로 처리해준다.
3. 애플리케이션을 SQL이 아닌 객체 중심으로 개발하여 생산성과 유지보수가 좋아지고, 테스트를 작성하기도 편리하다.
4. 데이터베이스를 변경할 때 코드를 거의 수정하지 않고 손쉽게 변경할 수 있다.

## SQL을 직접 다룰 때 발생하는 문제점
1. 진정한 의미의 계층 분할이 어렵다.
2. 엔터티를 신뢰할 수 없다.
3. SQL에 의존적인 개발을 피하기 어렵다.

## JPA와 문제 해결
JPA를 사용하면 객체를 데이터베이스에 저장하고 관리할 때, 개발자가 직접 SQL을 작성하는 것이 아니라 JPA가 제공하는 API를 사용하면 된다. 그러면 JPA가 개발자 대신 적절한 SQL을 생성해서 데이터베이스에 전달한다.
- 저장 기능
```java
entityManager.persist(member)
```
- 조회 기능
```java
String memberId = "jeongyun";
Member member = entityManager.find(Member.class, memberId);	// 조회
```
- 수정 기능
```java
Member member = entityManager.find(Member.class, memberId);
member.setName("jeongyuneo");	// 수정
```
- 연관된 객체 조회
```java
Member member = entityManager.find(Member.class, memberId);
Team team = member.getTeam();	// 연관된 객체 조회
```

## 패러다임의 불일치
객체지향 프로그래밍은 추상화, 캡슐화, 정보은닉, 상속, 다형성 등 시스템의 복잡성을 제어할 수 있는 다양한 장치들을 제공한다.
객체와 관계형 데이터베이스는 지향하는 목적이 서로 다르므로 둘의 기능과 표현 방법도 다르다. 이것을 객체와 관계형 데이터베이스의 패러다임 불일치 문제라 한다. 따라서 객체 구조를 테이블 구조에 저장하는 데는 한계가 있다.
- 상속
  JPA는 상속과 관련된 패러다임의 불일치 문제를 개발자 대신 해결해준다.
- 연관관계
  **객체**는 참조를 사용해서 다른 객체와 연관관계를 가지고 **참조에 접근해서 연관된 객체를 조회**한다.
  반면 **테이블**은 **외래 키**를 사용해서 다른 테이블과 연관관계를 가지고 **조인을 사용해서 연관된 테이블을 조회**한다.

## 객체 그래프 탐색
객체에서 다른 객체를 조회할 때 참조를 사용해서 연관된 객체를 찾는 것을 객체 그래프 탐색이라고 한다.
**SQL을 직접 다루면 처음 실행하는 SQL에 따라 객체 그래프를 어디까지 탐색할 수 있는지 정해진다.** 이것은 객체지향 개발자에게는 큰 제약이다.
JPA를 사용하면 객체 그래프를 마음껏 탐색할 수 있다. JPA는 연관된 객체를 사용하는 시점에 적절한 SELECT SQL을 실행하기 때문에 연관된 객체를 신뢰하고 조회할 수 있다. 이 기능은 실제 객체를 사용하는 시점까지 데이터베이스 조회를 미룬다고 해서 **지연 로딩**이라고 한다.

## 비교
데이터베이스는 기본 키 값으로 각 로우(row)를 구분하지만 객체는 동일성 비교와 동등성 비교라는 두 가지 비교 방법이 있다.

- 동일성 비교 : ```==```, 객체 인스턴스의 주소 값 비교
- 동등성 비교 : ```equals()```, 메소드를 사용해 객체 내부의 값 비교

ex) 조회한 회원 비교
관계형 데이터베이스 관점
```java
String memberId = "100";
Member member1 = memberDao.getMember(memberId);
Member member2 = memberDao.getMember(memberId);

member1 == member2;	// 다르다.
```
member1과 member2는 같은 데이터베이스 로우에서 조회했지만, 객체 측면에서 볼 때 둘은 다른 인스턴스기 때문에 동일성 비교시 false가 반환된다.
JPA 적용
```java
String memberId = "100";
Member member1 = emtityManager.fine(Member.class, memberId);
Member member2 = emtityManager.fine(Member.class, memberId);

member1 == member2;	// 같다.
```
JPA는 같은 트랜잭션일 때 같은 객체가 조회되는 것을 보장하기 때문에 동일성 비교에서 true가 반환된다.