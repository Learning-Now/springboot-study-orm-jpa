
# 값 타입

JPA의 데이터 타입은 크게 2가지로 나뉜다. (엔티티 타입/ 값 타입)

- @Entity로 정의하는 객체 (엔티티 타입)
- int, integer, String 처럼 단순히 값으로 사용하는 자바 기본 타입이나 객체를 말한다. (값 타입)

**값 타입**

→ 3가지로 또 나뉨

1. 기본 값 타입 - 자바 기본 타입 (int, double), 래퍼 클래스(Integer), String
2. 임베디드 타입 - 복합 값 타입
3. 컬렉션 값 타입

---

## 입베디드 값 타입 ( 복합 값 타입 )

새로운 값 타입을 직접 정의할때 임베디드 타입을 사용한다. → 직접 정의한 값타입

```java

private Long id;
private String name;
@Temporal(TemporalType.Date) java.util.Date startDate;
@Temporal(TemporalType.Date) java.util.Date endDate;
private String city;
private String street;
private String zipcode;
```

위 코드에서는 엔티티가 이름, 근무 시작일, 근무 종료일, 주소도시, 주소 번지, 주소 우편번호를 가진다.

→ 단순한 정보를 풀어놓은 것. (근무 시작일과 주소는 아무런 관계가 없음)

- 이를 임베디드 값타입으로 정의해 **이름, 근무 기간, 집주소** 로 정의 가능

### 멤버 Entity

```java
private Long id;
private String name;

@Embedded Period workPeriod; //근무 기간
@Embedded Address homeAddress; //집 주소
```

### 임베디드 타입으로 선언한 객체

```java
@Embeddable
public class Period {
	@Temporal(TemporalType.DATE) java.util.Date startDate;
	@Temporal(TemporalType.DATE) java.util.Date endDate;
}

@Embeddable
public class Address {
	@Column(name="city") //매핑할 컬럼 정의 가능
	private String city;
	private String street;
	private String zipcode;
}
```

- 임베디드 타입을 사용하면 코드의 중복을 줄일수있고 좀더 응집력 있게 코드를 짤 수 있다.

### 임베디드 타입 사용시 필요한 어노테이션

- @Embeddable: 값 타입을 저장하는 곳에 표시
- @Embedded: 값 타입을 사용하는 곳에 표시

‼️ 임베디드 타입을 포함한 모든 값타입은 엔티티의 생명주기에 의존 (임베디드 타입의 관계를 UML로 표현 하면 컴포지션 관계가 됨)

---

## @AttributeOverride: 속성 재정의

임베디드 타입에 정의한 매핑정보를 재정의하고 싶다면 @AttributeOverride를 사용하면 된다.

### IF 회원에게 주소가 하나더 필요할시?

```java
@Embedded Address homeAddress;
@Embedded Address companyAddress;

->
@Embedded Address homeAddress;
@AttributeOverrides({
	@AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")), 
	@AttributeOverride(name="street", column = @Column(name = "COMPANY_STREET")),
	@AttributeOverride(name="zipcode", column = @Column(name = "COMPANY_ZIPCODE"))
})
Address companyAddress;
```

- 위 어노테이션을 너무 많이 사용하개 된다면 엔티티 코드가 지저분해진다. 다행히도 임베디드 타입을 중복해서 사용하는 경우는 드물다.

---

### ➕ 임베디드 타입과 null

- 임베디드 타입이 null이면 매핑한 컬럼 값은 모두 null (member.setAddress(null) ⇒ address의 모든 속성 값은 null)

### ➕ 값 타입과 불변 객체

- 임베디드 값타입을 여러 엔티티에서 공유하면 위험 하므로 복사해서 사용하자.

    **값 타입 복사**

    - 값 타입의 실제 인스턴스 값을 공유하는 것은 위험하므로 값을 복사해서 사용해야 한다.

    ```java
    member1.setHomeAddress(new Address("OldCity");
    Address address = member1.getHomeAddress();

    //회원 1의 address 값을 복사해 새로운 newAddress 값을 생성
    Address newAddress = address.clone();

    newAddress.setCity("NewCity");
    member2.setHomeAddress(newAddress);
    ```

    - address는 객체 값이므로 대입시 참조값을 전달해 값타입과 다르게 인스턴스를 공유하게 된다.
    - 따라서 clone을 사용해 복사를 하자.

    **불변객체**

    - 값 타입은 부작용 걱정없이 사용해야 하므로 객체를 불변하게 만들어 수정할 일을 없애준다.