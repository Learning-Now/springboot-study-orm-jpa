# 컬렉션과 부가 기능
## 컬렉션
JPA는 다음 경우에 자바에서 기본으로 제공하는 Collection, List, Set, Map 컬렉션을 지원한다.
- ```@OneToMany```, ```@ManyToMany```를 사용해서 일대다나 다대다 엔터티 관계를 매핑할 때
- ```@ElementCollection```을 사용해서 값 타입을 하나 이상 보관할 때

자바 컬렉션 인터페이스 특징
- Collection : 자바가 제공하는 최상위 컬렉션. 하이버네이트는 중복을 허용하고 순서를 보장하지 않는다고 가정
- Set : 중복을 허용하지 않는 컬렉션. 순서를 보장하지 않음
- List : 순서가 있는 컬렉션. 순서를 보장하고 중복을 허용
- Map : Key, Value 구조로 되어 있는 특수한 컬렉션

### JPA와 컬렉션
하이버네이트는 엔터티를 영속 상태로 만들 때 컬렉션 필드를 하이버네이트에서 준비한 컬레션으로 감싸서 사용한다.

```java
@Entity
public class Team {

  @Id
  private String id;
  
  @OneToMany
  @JoinColumn
  private Collection<Member> members = new ArrayList<Member>();
  ...
}
```
Team은 members 컬렉션을 필드로 가지고 있다. 이때 Team을 영속 상태로 만들면 ArrayList 타입이었던 컬렉션이 엔터티를 영속 상태로 만든 직후 하이버네이트가 제공하는 PersistentBag 타입으로 변경된다.

하이버네이트는 컬렉션을 효율적으로 관리하기 위해 엔터티를 영속 상태로 만들 때 원본 컬렉션을 감싸고 있는 **내장 컬렉션(래퍼 컬렉션)**을 생성해서 이 내장 컬렉션을 사용하도록 참조를 변경한다.

하이버네이트는 이런 특징 때문에 **컬렉션을 사용할 때 즉시 초기화**해서 사용하는 것을 권장한다.

- 인터페이스와 컬렉션 래퍼
```java
//org.hibernate.collection.internal.PersistentBag
@OneToMany
Collection<Member> collection = new ArrayList<Member>();	// 중복 허용, 순서 보관X

//org.hibernate.collection.internal.PersistentBag
@OneToMany
List<Member> list = new ArrayList<Member>();	// 중복 허용, 순서 보관X

//org.hibernate.collection.internal.PersistentSet
@OneToMany
Set<Member> set = new HashSet<Member<();	// 중복 허용X, 순서 보관X

//org.hibernate.collection.internal.PersistentList
@OneToMany
@OrderColumn
List<Member> orderColumnList = new ArrayList<Member>();		// 중복 허용, 순서 보관
```

### Collection, List
Collection, List는 엔터티를 추가할 때 중복된 엔터티가 있는지 비교하지 않고 단순히 저장만 하면 된다. 따라서 엔터티를 추가해도 지연 로딩된 컬렉션을 초기화하지 않는다.

### Set
Set은 엔터티를 추가할 때 중복된 엔터티가 있는지 비교해야 한다. 따라서 엔터티를 추가할 때 지연 로딩된 컬렉션을 초기화한다.

### List + @OrderColumn
List 인터페이스에 ```@OrderColumn```을 추가하면 순서가 있는 특수한 컬렉션으로 인식한다. 순서가 있다는 것은 데이터베이스에 순서 값을 저장해서 조회할 때 사용한다는 의미다.

순서가 있는 컬렉션은 데이터베이스에 순서 값도 함께 관리한다.

#### @OrderColumn의 단점
- 매핑하는 엔터티와 사용하는 엔터티가 달라 값을 UPDATE 하는 SQL이 추가로 발생
- List 변경 시 연관된 많은 위치 값을 변경해야 함
- 중간에 값이 없으면 조회한 List에는 null이 보관되어 컬렉션 순회 시 NullPointerException 발생

```@OrderColumn```은 실무에서 사용하기에 위와 같은 단점이 많다. 따라서 ```@OrderColumn```을 매핑하지 말고 개발자가 직접 값을 관리하거나 ```@OrderBy```를 사용하는 것을 권장한다.

### @OrderBy
```@OrderColumn```이 데이터베이스에 순서용 컬럼을 매핑해서 관리했다면 ```@OrderBy```는 데이터베이스의 ORDER BY절을 사용해서 컬렉션을 정렬하므로 순서용 컬럼을 매핑하지 않아도 된다. 그리고 ```@OrderBy```는 모든 컬렉션에 사용할 수 있다.

```@OrderBy```의 값은 엔터티의 필드를 대상으로 한다.

## @Converter
컨버터(converter)를 사용하면 엔터티의 데이터를 변환해서 데이터베이스에 저장할 수 있다.

- convertToDatabaseColumn() : 엔터티의 데이터를 데이터베이스 컬럼에 저장할 데이터로 변환
- convertToEntityAttribute() : 데이터베이스에서 조회한 컬럼 데이터를 엔터티의 데이터로 변환

### 글로벌 설정
모든 Boolean 타입에 컨버터를 적용하려면 ```@Converter(autoApply = true)``` 옵션을 적용하면 된다.

이렇게 글로벌 설정을 하면 ```@Converter```를 지정하지 않아도 모든 Boolean 타입에 대해 자동으로 컨버터가 적용된다.

## 리스너
JPA 리스너 기능을 사용하면 엔터티의 생명주기에 따른 이벤트를 처리할 수 있다.

### 이벤트 종류
![리스너 시점](https://images.velog.io/images/minide/post/2c476e52-dfb0-4254-80c6-17cf2b9fc0f0/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-08-19%20%EC%98%A4%ED%9B%84%208.09.41.png)

1. PostLoad : 엔터티가 영속성 컨텍스트에 조회된 직후 refresh를 호출한 후(2차 캐시에 저장되어 있어도 호출됨)
2. PrePersist : ```persist()``` 메소드를 호출해서 엔터티를 영속성 컨텍스트에 관리하기 직전에 호출됨. 식별자 생성 전략을 사용한 경우 엔터티에 식별자는 존재하지 않음. 새로운 인스턴스를 ```merge```할 때도 수행됨
3. PreUpdate : ```flush```나 ```commit```을 호출해서 엔터티를 데이터베이스에 수정하기 직전에 호출됨
4. PreRemove : ```remove()``` 메소드를 호출해서 엔터티를 영속성 컨텍스트에서 삭제하기 직전에 호출됨. 또한 삭제 명령어로 영속성 전이가 일어날 때도 호출됨. ```orphanRemoval```에 대해서는 ```flush```나 ```commit``` 시에 호출됨
5. PostPersist : ```flush```나 ```commit```을 호출해서 엔터티를 데이터베이스에 저장한 직후 호출됨. 식별자가 항상 존재. 참고로 식별자 생성 전략이 ```IDENTITY```면 식별자를 생성하기 위해 ```persist()```를 호출하면서 데이터베이스에 해당 엔터티를 저장하므로 이때는 ```persist()```를 호출한 직후에 바로 ```Postpersist```가 호출됨.
6. PostUpdate : ```flush```나 ```commit```을 호출해서 엔터티를 데이터베이스에 수정한 직후에 호출됨.
7. PostRemove : ```flush```나 ```commit```을 호출해서 엔터티를 데이터베이스에 삭제한 직후에 호출됨.

### 이벤트 적용 위치
이벤트는 엔터티에서 직접 받거나 별도의 리스너를 등록해서 받을 수 있다.

#### 엔터티에 직접 적용
엔터티에 이벤트가 발생할 때마다 어노테이션으로 지정한 메소드가 실행된다.

#### 별도의 리스너 등록
리스너는 대상 엔터티를 파라미터로 받을 수 있다. 반환 타입은 void로 설정해야 한다.

#### 기본 리스너 사용
모든 엔터티의 이벤트를 처리하려면 META-INF/orm.xml에 기본 리스너로 등록하면 된다.

여러 리스너를 등록했을 때 이벤트 호출 순서는 아래와 같다.

1. 기본 리스너
2. 부모 클래스 리스너
3. 리스너
4. 엔터티


이벤트를 잘 활용하면 대부분의 엔터티에 공통으로 적용하는 등록 일자, 수정 일자 처리와 해당 엔터티를 누가 등록하고 수정했는지에 대한 기록을 리스너 하나로 처리할 수 있다.

## 엔터티 그래프
엔터티를 조회할 때 연관된 엔터티들을 함께 조회하려면 글로벌 fetch 옵션을 ```FetchType.EAGER```로 설정한다.
또는 ```select o from Order o join fetch o.member```처럼 JPQL에서 페치 조인을 사용하면 된다.

일반적으로 글로벌 fetch 옵션은 ```FetchType.LAZY```를 사용하고, 엔터티를 조회할 때 연관된 엔터티를 함께 조회할 필요가 있으면 JPQL의 페치 조인을 사용한다.

페치 조인을 사용하면 같은 JPQL을 중복해서 작성하는 경우가 많아 엔터티 그래프 기능을 사용해 엔터티를 조회하는 시점에 함께 조회할 연관된 엔터티를 선택한다.

따라서 JPQL은 데이터를 조회하는 기능만 수행하면 되고 연관된 엔터티를 함께 조회하는 기능은 엔터티 그래프를 사용하면 된다.

**엔터티 그래프 기능은 엔터티 조회시점에 연관된 엔터티들을 함께 조회하는 기능**이다.

### Named 엔터티 그래프
Named 엔터티 그래프는 ```@NamedEntityGraph```로 정의한다.

- name : 엔터티 그래프의 이름 정의
- attributeNodes : 함께 조회할 속성 선택. 이때 ```@NamedAttributeNode```를 사용하고 그 값으로 함께 조회할 속성을 선택하면 됨.

### em.find()에서 엔터티 그래프 사용
Named 엔터티 그래프를 사용하려면 정의한 엔터티 그래프를 ```em.getEntityGraph("Order.withMember")```를 통해 찾아오면 된다. 엔터티 그래프는 JPA의 힌트 기능을 사용해서 동작하는데 힌트 키로 ```javax.persistence.fetchgraph```를 사용하고 힌트의 값으로 찾아온 엔터티 그래프를 사용하면 된다.

### 동적 엔터티 그래프
엔터티 그래프를 동적으로 구성하려면 ```createEntityGraph()``` 메소드를 사용하면 된다.