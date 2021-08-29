
# 컬렉션과 부가 기능

- 컬렉션 ( 다양한 컬렉션과 특징을 설명 )
- 컨버터 ( 엔티티의 데이터를 변환해 데이터 베이스에 저장 )
- 리스너 ( 엔티티에서 발생한 이벤트를 처리 )
- 엔티티 그래프 ( 엔티티를 조회시 연관된 엔티티를 함께 조회 )

---

## 컬렉션

JPA는 자바에서 제공하는 Collection, List, Set, Map을 지원한다.

[컬렉션 프레임 워크 (JCF ( Java Collection Framework ))](https://www.notion.so/JCF-Java-Collection-Framework-4934869fc3714eb1b85a0dd2e9bf251e) 컬렉션의 내용은 이부분을 참조

---

### JPA와 컬렉션

```java
//Team라는 객체 안에
...
@OneToMany
@JoinColumn
private Collection<Member> members = new ArrayList<Member>();
```

- 하이버네이트는 엔티티를 영속 상태로 만들 때 컬렉션 필드를 하이버네이트에서 준비한 컬렉션으로 감싼다.

---

💁 위의 컬렉션을 영속성 컨텍스트에 넣기 전과 후의 차이점을 본다면 다음과 같은 차이점을 볼 수 있다.

**before**

class java.util.ArrayList

**After**

class org.hibernate.collection.internal.PersistentBag

☝️ 이는 엔티티를 효율적으로 관리하기 위해 엔티티를 영속상태로 만들때 원본 컬렉션을 내장 컬렉션을 생성해  이 내장 컬렉션을 사용하도록 참조를 변경하게 된다. (래퍼 컬렉션이라고도 부름)

→ 이러한 특성때문에 사용시 바로 초기화를 해 사용하는 것을 권장한다.

---

### **내장컬렉션**

- PersistentBag (Collection, List, 중복 가능, 순서 X)
- PersistentSet (Set, 중복 불가, 순서 X)
- PersistentList (List + @OrderColumn, 중복 허용, 순서 보장)

---

→ **Collection, List 특징**

- Collection과 List는 엔티티 추가시 중복 엔티티를 비교하지 않고 저장만한다. 따라서 엔티티를 추가해도 지연로딩된 엔티티가 초기화 되지 않는다.

→ **Set 특징**

- Set은 엔티티를 추가할때 중복이 있는지 검사해야되기 때문에 엔티티를 추가할때 지연 로딩된 컬렉션 초기화

→ **List + @OrderColumn 특징**

- 이는 List에 @OrderColumn을 추가해 특수한 컬렉션으로 만들어 데이터베이스에 순서값을 저장해 조회할때 사용할수있게 해준다.

    ```java
    //Board 엔티티 
    ...
    @OneToMany(mappedBy = "board")
    @OrderColumn(name = "POSTIOTN")
    private List<Comment> comments = new ArrayList<Comment>();
    ```

    ‼️ 이 방법을 사용하게 되면 Board단에서 이를 지정해줬지만 board.comments 컬렉션의 값이

    결국 Comment 에 POSITION이 매핑되게 된다.

    **단점** 

    1. @OrderColumn을 Board에서 매핑 해  Comment에서 Position의 역할을 알기 힘들다.

        → Comment를 Insert시  Position 값이 저장 X

        → 추가로 Update쿼리 발생

    2. 연관된 많은 위치 값 변경 댓글 2 삭제시 댓글 3,4를 하나씩 줄이는 Update문 
    3. 중간 값이 빌경우 Null값이 들어가게 된다.

---

## @OrderBy

@OrderColumn의 사용은 실무에서 거의 하지 않고 @OrderBy를 사용한다. 이는 데이터 베이스의 ORDER BY절을 사용해 컬렉션을 정렬한다. 따라서 @OrderColumn이 필요 없다.

```java
@OneToMany(mappedBy = "team")
@OrderBy("username desc, id asc")
private Set<Member> members = new HashSet<Member>();
```

→ username desc, id asc를 사용해  Member의 username 필드로 내림차순 정렬 후 id 로 오름차순 정렬을 한다.

---

## @Converter

컨버터를 사용하면 엔티티의 데이터를 변환해 데이터 베이스에 저장을 할수 있다.

**ex)** 

회원의 VIP여부를 자바 boolean타입을 사용하고 싶다 하자. JPA 를 사용하면 방언마다 다르긴 하지만 데이터 베이스에 0 또는 1로 저장된다. 이때 0,1 이 아닌 Y,N으로 저장하고 싶을때 사용한다.

```java
@Convert(converter = BooleanToYNConverter.class)
private boolean vip;
```

- 이때 컨버터 클래스는 @Converter 어노테이션을 사용하고 AttributeConverter인터페이스를 구현해야한다.

---

## 리스너

사용자가 언제 어떤 삭제를 요청했는지 로그로 남겨야 하는 요구사항이 있을시 애플리케이션 삭제로직을 하나 하나 찾아서 로그를 남기는 것은 비효율 적이다. 이때 JPA의 리스너 기능을 사용한다면 효율적으로 코드를 작성할수 있다.

![](https://images.velog.io/images/donglee99/post/c7b5c319-2aae-460e-9b24-1abc0f43cbbe/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202021-08-09%20%EC%98%A4%ED%9B%84%209.41.05.png)

### 이벤트 적용 위치

- 엔티티에 직접 적용

```java
@PrePersist
public void prePersist() {
	System.out.println("Duck.prePersist id =" + id);
}
```

- 별도의 리스너 등록

```java
@Entity
@EntityListeners(DuckListener.class)
public class Duck{
}
```

- 기본 리스너 사용

```java
<?xml version="1.0" encoding="UTF-8"?>
<entity-mappings ...>

	<persistence-unit-metadata>
		<persistence-unit-defaults>
			<entity-listener class="jpabook.jpashop.domain.test.listener.DefaultListener" />
		</entity-listeners>
	</persistence-unit-defaults>
</persistence-unti-metadata>
</entity-mapping>
```

**호출순서**

1. 기본 리스너
2. 부모 클래스 리스너
3. 리스너
4. 엔티티

---

## 엔티티 그래프

엔티티 조회시 연관된 데이터를 조회할때는 즉시로딩을 사용하거나 페치조인을 사용하면 된다.

만약 조회를 하는데 조회할 엔티티가 다 다른경우 JPQL 을 매번 다른 걸 사용해야한다. 이는  JPQL이 조회 뿐만 아니라 연관된 엔티티를 함께 조회하는 기능도 제공해 발생하는 문제다. 엔티티 그래프 기능을 사용하면 조회 시점에 연관된 엔티티를 선택할수 있게 된다.

엔티티 그래프 기능은 엔티티 조회시점에 연관된 엔티티를 함께 조회하는 기능으로  정적인 Named 엔티티 그래프와 동적으로 정의하는 엔티티 그래프가 있다.

### Named 엔티티 그래프

```java
@NamedEntityGraph(name = "Order.withMember", attributeNodes = {
	@namedAttributeNode("member")
})
@Entity
@Table(name = "ORDERS")
```

- Named 엔티티 그래프는 @NamedEntityGraph로 정의함
- name : 엔티티 그래프의 이름을 정의
- attributeNodes 함께 조회할 속성 선택 @NamedAttributeNode를 사용하고 그 값으로 함께 조회할 속성을 선택

```java
EntityGraph graph = em.getEntityGraph("Order.withMember");

Map hints = new HashMap();
hints.put("javax.persistence.fetchgraph", graph);

Order order = em.find(Order.class, orderId, hints);
```

- 엔티티 그래프를 사용하기 위해서는 정의한 엔티티 그래프를 em.getEntityGraph("Order.withMember")를 통해 찾아오면 된다.

```java
select o.*, m.*
from
	ORDERS o
inner join
		Member m
			on o.MEMBER_ID=m.MEMBER_ID
where
	o.ORDER_ID=?
```

## subgraph

order → order item → item 까지 함께 조회를 하는 방법을 알아보자

```java
@NamedEntityGraph(name = "Order.withAll", attributeNodes = {
	@NamedAttributeNode("member"),
	@NamedAttributeNode(value = "orderItems", subgraph = "orderItems")
	},
	subgraphs = @NamedSubgraph(name = "orderItems", attributeNodes = {
		@NamedAttributeNode("item")
	})
)
```

## 동적 엔티티 그래프

엔티티 그래프를 동적으로 구성하기 위해서는 createEntityGraph() 메소드를 사용하면 된다.

```java
EntityGraph<Order> graph = em.createEntityGraph(Order.class);
graph.addAttributeNodes("member");

Map hints = new HashMap();
hints.put{"javax.persistence.fetchgraph", graph);

Order order = em.find(Order.class, orderId, hints);
```