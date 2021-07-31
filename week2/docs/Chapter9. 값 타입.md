# 값 타입
## JPA의 데이터 타입 분류
1. 엔터티 타입
- @Entity로 정의하는 객체
- 데이터가 변해도 식별자로 지속해서 추적 가능
2. 값 타입
- int, Integer, String처럼 단순히 값으로 사용하는 자바 기본 타입이나 객체
- 식별자가 없고 값만 있으므로 변경시 추적 불가

## 값 타입 분류
1. 기본값 타입
- 자바 기본 타입(int, double)
- 래퍼 클래스(Integer, Long)
- String
2. 임베디드 타입(복합 값 타입)
3. 컬렉션 값 타입

## 기본값 타입
기본값 타입은 생명주기를 엔터티의 의존한다. 예를 들어 회원을 삭제하면 이름, 나이 필드도 함께 삭제된다.
**기본값 타입은 절대 공유하면 안된다.** 회원 이름 변경시 다른 회원의 이름도 바뀌면 안된다.

기본 타입은 항상 값을 복사한다.
Integer같은 래퍼 클래스나 String같은 특수한 클래스는 공유 가능한 객체이지만 변경하지 않는다.

## 임베디드 타입(복합 값 타입)
임베디드 타입은 새로운 값 타입을 직접 정의해서 사용하는 것이다.
임베디드 타입은 엔터티의 비슷한 정보들을 하나로 묶어 새로운 타입으로 정의한다.

예를 들어 회원 엔터티에 주소 도시, 주소 번지, 주소 우편번호가 있다면 이러한 데이터는 객체지향적이지 않으며 응집력이 떨어진다. 대신 이 세 필드를 묶어 "주소"라는 새로운 타입으로 만들어준다면 코드가 훨씬 명확해 질 수 있다.

값 타입을 정의하는 곳에는 **@Embeddable**을, 값 타입을 사용하는 곳에는 **@Embedded**를 표시한다.

\+ 임베디드 타입은 기본 생성자가 필수다.

### 임베디드 타입과 테이블 매핑
임베디드 타입은 엔터티의 값일 뿐이다. 따라서 값이 속한 엔터티의 테이블에 매핑한다.

### @AttributeOverride: 속성 재정의
임베디드 타입에 정의한 매핑정보를 재정의하려면 엔터티에 @AttributeOverride 어노테이션을 사용하면 된다.

예를 들어 ```Address``` 값 타입으로 집 주소와 회사 주소를 선언한다고 하면 테이블에 매핑하는 컬럼명이 중복되는 문제가 발생한다. 이런 경우 @AttributeOverrides를 사용해 매핑정보를 재정의해야 한다.

```java
@Embedded
@AttributeOverrides({
    @AttributeOverride(name="city", column=@Column(name="COMOPANY_CITY")),
    @AttributeOverride(name="street", column=@Column(name="COMPANY_STREET")),
    @AttributeOverride(name="zipcode", column=@Column(nmae="COMPANY_ZIPCODE"))
})
Address companyAddress;
```

### 임베디드 타입과 null
임베디드 타입이 null이면 매핑한 컬럼 값은 모두 null이 된다.

## 값 타입과 불변 객체
임베디드 타입 같은 값 타입을 여러 엔터티에서 공유하면 위험하다. 한 객체의 정보를 변경했을 때 다른 객체의 정보도 함께 변경되는 부작용을 막기 위해 값 타입을 복사해서 사용해야 한다.

```java
member1.setHomeAddress(new Address("OldCity"));
Address address = member1.getHomeAddress();

// 값 타입 공유
address.setCity("NewCity");	// 회원1의 address 값을 공유해서 사용 -> 바람직하지 않음
member2.setHomeAddress(address);

// 값 타입 복사
Address newAddress = address.clone();

newAddress.setCity("NewCity");
member2.setHomeAddress(newAddress);
```

자바의 기본 타입은 값을 대입하면 값을 복사해서 전달한다. 하지만 **임베디드 타입처럼 직접 정의한 값타입은 객체 타입이므로 객체를 대입할 때 항상 참조 값을 전달**한다. 따라서 객체를 대입할 때마다 인스턴스를 복사해서 대입해야 한다.
문제는 복사하지 않고 원본의 참조 값을 직접 넘기는 것을 막을 방법이 없다. 따라서 이를 해결할 수 있는 가장 단순한 방법은 객체의 값을 수정하지 못하게 막는 것이다.

### 불변 객체
한 번 만들면 절대 변경할 수 없는 객체를 불변 객체라 한다. 객체를 불변하게 만들면 값을 수정할 수 없으므로 부작용을 원천 차단할 수 있다. 따라서 **값 타입은 될 수 있으면 불변 객체로 설계해야 한다.**