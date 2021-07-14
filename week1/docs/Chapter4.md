
# 4장 엔티티 매핑

## 대표 어노테이션 정리

- 객체와 테이블 매핑 : @Entity, @Table
- 기본 키 매핑 : @Id
- 필드와 컬럼 매핑 : @Column
- 연관관계 매핑 : @ManyToOne, @OneToOne, @JoinColumn

---

## @Entity

- @Entity 어노테이션을 반드시 붙여야  JPA가 관리하는 엔티티라고 부를 수 있다.

### 속성 정리

- name (엔티티 이름을 정의 / 설정 X 시 클래스 이름 그대로 사용)

‼️ **주의 사항**

1. 기본 생성자 필수 (파라미터 없는 public or protected)
2. final, enum, interface, inner 클래스에는 사용 불가

## @Table

- 엔티티와 매핑할 테이블을 지정 (생략시 엔티티 이름을 테이블 이름으로 사용)

### 속성 정리

- name (매핑 할 테이블 이름)
- catalog ( catalog 기능이 있는 데이터 베이스에서 catalog 매핑 )
- schema ( schema 기능이 있는 데이터 베이스에서 schema 매핑 )
- uniqueConstraints ( 유니크 제약조건 )

## ➕ [hibernate.hbm2ddl.auto](http://hibernate.hbm2ddl.auto) 속성

- create  : 기존 테이블 삭제 후 재생성 (DROP + CREATE)
- create-drop : 기존 테이블 삭제 후 재생성 후 삭제 (DROP + CREATE + DROP)
- update : 변경 사항만 적용
- validate : 매핑 정보의 자이가 있다면 경고를 남기고 실행 X
- none : 자동 생성 기능을 사용하고 싶지 않다면 아무것도 적지 않으면 된다 (none은 잘못된 옵션 값)

---

## 기본키 매핑

 

- 직접 매핑 : 기본키를 애플리케이션에 직접 할당
- 자동 생성 : 대리키로 할당
    1. IDENTITIY
    2. SEQUENCE
    3. TABLE

### IDENTITY

- 기본키 생성을 데이터베이스에 위임하는 전략 ⇒ 기본키를 알기위해서는 DB 에 거쳐야함 ( em.persist시 바로 insert문이 나가게 됨 따라서 쓰기 지연 동작 X)

```java
CREATE TABLE BOARD (
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	DATA VARCHAR(255)
);

```

**사용법**

```java
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	...

}
```

### SEQUENCE

- 데이터 베이스 시퀀스는 유일한 값을 순서대로 생성하는 데이터베이스 오브젝트다

```java
CREATE TABLE BOARD (
	ID BIGINT NOT NULL PRIMARY KEY,
	DATA VARCHAR(255)
);

// 시퀀스 생성
CREATE SEQUENCE BOARD_SEQ START WITH 1 INCREMENT BY 1;
```

**사용법**

```java
@Entity
@SequenceGenerator( name = "BOARD_SEQ_GENERATOR", 
sequenceName = "BOARD_SEQ", initialValue = 1,
 allocationSize = 1 )
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SQEUENCE, 
									generator = "BOARD_SEQGENERATOR")
	private Long Id;
	...

}
```

- IDENTITY와 다르게 em.persist호출시 데이터 베이스 시퀀스를 사용해 식별자를 조회후 식별자를 엔티티에 할당후 영속성 컨텍스트에 저장한다.

### TABLE

- 키 전용 테이블을 하나 만들고 이름과 값으로 사용할 컬럼을 만들어 데이터 베이스 시퀀스를 흉내낸다.

---

## 필드와 컬럼 매핑

- @Column
- @Enumerated : 자바의 enum 타입 매핑
- @Temporal : 날짜 타입 매핑
- @LOB : BLOB, CLOB타입 매핑
- @Transient : 특정 필드를 데이터베이스에 매핑하지 않는다
- @Access : JPA가 엔티티에 접근하는 방식을 지정

### Column

**주로 사용하는 속성**

1. name 
2. nullable
3. unique
4. columnDefinition
5. length
6. precision, scale

### Enumerated

- EnumType.ORDINAL ( 정의된 순서대로 저장 0, 1, 2 사용을 지양하자.. 값이 수정될시 중복의 위험이 있다.)
- EnumType.STRING (사용 사용 이름 그대로 저장)