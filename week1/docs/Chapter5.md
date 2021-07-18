

# 연관관계 매핑 기초

## 객체와 테이블의 차이

**객체 : 참조를 사용해 관계를 맺음**

**테이블 : 외래 키를 사용해 관계를 맺음**

(계속 나오는 부분) 

### 방향

- 방향에는 단방향과 양방향이 있다. 팀과 회원 이라는 관계가 있을 경우에 팀 → 회원의 관계만 가지는 것을 단방향이고 팀 → 회원, 회원 → 팀 두가지 모두를 가지는 경우를 양방향이라한다. 실제로 방향이라는 개념은 객체에만 존재하며 테이블의 경우는 항상 양방향이다.

### 다중성

- 일대다, 다대일, 일대일, 다대다 관계를 의미함

### 연관관계의 주인

- 객체의 연관관계에서 중요한 역할을 한다. 연관관계의 주인은 보통 외래키를 가지고 있는 쪽이 주인이며 CRUD가 모두 가능한 반면 반대쪽은 읽기만 가능하다.

---

## 단방향 연관관계

- 단방향 연관관계를 이해하는데 가장 먼저 알아야하는것은 N:1 관계이다.

☝️회원과 팀이있다

☝️회원은 하나의 팀에만 소속

☝️회원과 팀은 다대일 관계

→ 하나의 팀에 여러 회원이 소속 ⇒ 회원과 팀 N : 1

### 객체의 기준

이때 회원과 팀을 단방향으로 설정시 회원 → 팀 이라는 연관관계를 설정했다 가정해보자

- 회원은 팀을 알수있지만 팀은 회원을 알수없다.

(member.getTeam O , team.getMembers X) 객체 그래프 탐색

### 테이블 기준

- 테이블에는 방향이라는 개념이 없기때문에 Join을 통해 양쪽에서 서로 조회가 가능하다.

### 객체를 매핑하기 위한 어노테이션

@ManyToOne : 다대일 관계를 매핑할때 사용한다.

- 속성값으로 optional, fetch, cascade, targetEntity 를 가짐

@JoinColumn(name = "TEAM_ID") : 외래키를 매핑할떄 사용

- 속성값으로 name, referencedColumnName, foreignKey + Column의 속성

---

## 양방향 연관관계

- 양방향 연관관계는 단방향 연관관계 반대편에도 연관관계를 똑같이 맺어주면 된다.
- 즉 @ManyToOne을 했다면 반대쪽에는 @OneToMany를 설정해주면 된다.

```java
@ManyToOne
@JoinColumn(name = "TEAM_ID)
prviate Team team;

@OneToMany(mappedBy = "team")
private List<Member> members = new ArrayList<Member>();
 // Null 포인터 참조 오류를 방지하기 위해 초기화를 해준다
```

양방향 연관관계를 맺는다 해서 여기서 끝나는것이 아니다. 만약 setTeam을 하면 Member에만 팀이 저장되고 Team에는 Member가 저장되지 않는다. 이게 정말 완벽한 양방향 연관관계가 맞을까? 이를 해결하기위해 set시점에 Team에 Member를 추가해주는 연관관계 편의 메소드를 작성한다.

➕ JPA를 사용하지 않는 경우와 사용하는 두 경우 모두 테스트 코드를 작성하여 비교해 보자.