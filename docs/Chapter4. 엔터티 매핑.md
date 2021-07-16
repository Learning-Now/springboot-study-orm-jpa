# 엔터티 매핑
## 대표 어노테이션
- 객체와 테이블 매핑 : @Entity, @Table
- 기본 키 매핑 : @Id
- 필드와 컬럼 매핑 : @Column
- 연관관계 매핑 : @ManyToOne, @JoinColumn

## @Entity
@Entity가 붙은 클래스는 JPA가 관리하고 엔터티라 한다. JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수이다.

@Entity 적용 시 주의사항
- **기본 생성자 필수**(파라미터가 없는 public 또는 protected 생성자)
- final 클래스, enum, interface, inner 클래스에는 사용할 수 없음
- 저장할 필드에 final을 사용하면 안됨

## @Table
@Table은 엔터티와 매핑할 테이블을 지정한다. 생략하면 매핑한 엔터티 이름을 테이블 이름으로 사용한다.

## 데이터베이스 스키마 자동 생성
JPA는 데이터베이스 스키마를 자동으로 생성하는 기능을 지원한다. JPA는 매핑정보와 데이터베이스 방언을 사용해 데이터베이스 스키마를 생성한다.

hibernate.hbm2ddl.auto 속성
- create : 기존 테이블을 삭제하고 새로 생성(DROP + CREATE)
- create-drop : create 속성에 추가로 애플리케이션을 종료할 때 생성한 DDL을 제거(DROP + CREATE + DROP)
- update : 데이터베이스 테이블과 엔터티 매핑정보를 비교해서 변경 사항만 수정
- validate : 데이터베이스 테이블과 엔터티 매핑정보를 비교해서 차이가 있으면 경고를 남기고 애플리케이션을 실행하지 않는다. 이 설정은 DDL을 수정하지 않는다.
- none(아무것도 적지 않음) : 자동 생성 기능을 사용하지 않음(none은 잘못된 옵션 값)

## 기본 키 매핑
- 직접 할당 : 기본 키를 애플리케이션에 직접 할당
- 자동 생성 : 대리 키 사용 방식
    - IDENTITY : 기본 키 생성을 데이터베이스에 위임
    - SEQUENCE : 데이터베이스 시퀀스를 사용해서 기본 키를 할당
    - TABLE : 키 생성 테이블을 사용

## 필드와 컬럼 매핑
- @Column : 컬럼을 매핑
- @Enumerated : 자바의 enum 타입을 매핑
- @Temporal : 날짜 타입을 매핑
- @Lob : BLOB, CLOB 타입을 매핑
- @Transient : 특정 필드를 데이터베이스에 매핑하지 않음
  \+ 기타
- @Access : JPA가 엔터티에 접근하는 방식을 지정

### @Column의 속성 정리
@Column은 가장 많이 사용되는 어노테이션이다. 이 중 name, nullable이 주로 사용되고 나머지는 잘 사용되지 않는다.
![](https://images.velog.io/images/minide/post/f5f51692-11c9-4e88-9b52-fb93235d2836/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-07-17%20%EC%98%A4%EC%A0%84%2012.24.22.png)