---

# REST API

REST는 Representational State Transfer의 약자로 

자원(URI), 행위(HTTP METHOD), 표현으로 이루어져있다.

➕URI (인터넷에서 자원을 나타내는 유일한 주소)

**특성**

1. Uniform (유니폼 인터페이스)
    - URI로 지정한 리소스에 대한 조작을 통일, 한적정 인터페이스로 수행하게 하는 아키텍쳐
2. Stateless(무상태성)
    - 상태가 존재 하지 않는다. 즉 상태정보를 저장, 관리 X 세션 정보나 쿠키를 별도로 저장 X 따라서 API요청만 처리하면 된다 → 자유도 up , 구현 단순
3. Cacheable(캐시 가능)
    - REST는 HTTP의 기존 웹표준을 그대로 사용하기때문에 HTTP가 가진 캐싱 기능 적용 가능
4. Self-descriptiveness
    - RESTAPI 메시지만 보고도 구조 유추 가능
5. Client - Server
    - 서버와 클라이언트의 구조와 역할이 확실히 구분 되어 서로간의 의존성 줄어들게 할수있다
6. 계층형 구조
    - 다중 계층으로 구성되어 있다.

---

# REST API 컨벤션

- **URI는 정보의 자원을 표현해야함**
- **자원에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE) 로 표현하고 URI에 행위를 표현하면안됨.**

1. URI는 정보의 자원을 표현해야함 (리소스명 동사를 지양하고 명사를 사용하라)

```java
 GET /members/delete/id  X

	@DeleteMapping
	/members/id O
```

1. 슬래시 구분자(/)는 계층관계 사용시에만 사용

```java
http://study-orm-jpa/week1 
```

1. (-) 는 URI의 가독성 높이는데 사용, ( _ ) 사용 X, 소문자만 사용, 확장자명 사용 X

1. 리소스 간의 관계 표현방법

```java
/리소스명/리소스 ID/관계가 있는 다른 리소스명

@GetMapping(/members/memberId/team)
```

---

# Reference

[https://meetup.toast.com/posts/92](https://meetup.toast.com/posts/92)