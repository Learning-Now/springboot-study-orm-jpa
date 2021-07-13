---

# 2장 ~ 3장 JPA시작, 영속성 관리

> 개인적으로 중요하다고 생각하는 부분만 정리하겠다.

## 데이터 베이스 방언이란?

- 데이터 베이스 방언이란 각 데이터 베이스마다 쓰이는 종속적인 문법이라고 생각하면 된다.

    ex) 데이터 타입 MySql(VARCHAR) vs Oracle(VARCHAR2)

💁 위처럼 각 데이터 베이스마다 각자의 문법이 있다. 그렇다면 개발자는 데이터 베이스가 변경될 경우 이를 하나        

      하나 다 수정해야한다. 이는 엄청난 비용이 발생하게 될것이다.

하지만 JPA 는 친절하게 이를 해결해준다. 그 이유는 JPA가 데이터 베이스에 종속적이지 않기 때문이다.

- 개발자는 그저 JPA문법에 맞게 사용하면 되고 각 데이터 베이스의 방언 처리를 자동으로 해준다 (물론 선배 개발자들이 미리 매핑을 해놓은 것이겠지..)

    ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5605740e-f14d-41c4-a42f-06a059d16266/_2021-07-13__9.47.22.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5605740e-f14d-41c4-a42f-06a059d16266/_2021-07-13__9.47.22.png)

---

## 엔티티 매니저 설정

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/97c6030b-abe1-42b6-8243-0855d01e90f1/_2021-07-13__9.49.59.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/97c6030b-abe1-42b6-8243-0855d01e90f1/_2021-07-13__9.49.59.png)

JPA 는 persistence.xml을 통해 필요한 설정 정보를 관리한다. 따라서 JPA 를 시작하기 위해서는 persistence.xml의 설정 정보를 통해 엔티티 매니저 팩토리를 생성해야 한다.

### 엔티티 매니저 팩토리 생성

```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook")
```

- 다음 코드로 엔티티 매니저 팩토리 생성이 가능하다.
- 엔티티 매니저 팩토리는 설정 정보를 읽고 JPA를 동작시키기 위한 객체를 만들고 구현체에 따라서 커넥션 풀도 생성하게 된다 따라서 비용이 크므로 전체에서 딱 한번만 생성하고 공유하게 된다.

### 엔티티 매니저 생성

```java
EntityManager em = emf.createEntityManager();
```

- 엔티티 매니저는 JPA의 대부분의 기능을 담당하며 데이터 베이스에 CRUD가 가능하다. 엔티티 매니저는 직접적으로 데이터 베이스와 통신하며 스레드간에 공유하거나 재사용이 불가능하다.

➕ JPA는 항상 한 트랜잭션 안에서 데이터를 변경해야 한다.

---