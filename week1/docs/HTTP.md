---

# HTTP 메소드 정리

## HTTP 란?

웹상에서 클라이언트와 서버간의 요청/ 응답을 주고 받을 수 이쓴ㄴ 프로토콜이다. 클라이언트가 HTTP 프로토콜을 이용해 서버로 요청을 보내면 서버는 이를 매핑해 알맞은 응답을 클라이언트에게 제공한다. 

## HTTP 메소드

- HTTP 메소드는 서버가 요청을 수행하기 위해 해야하는 행동을 표시하는 용도로 사용되고 대표적으로 Post와 Get이 있다.

![](https://images.velog.io/images/donglee99/post/f56cc85c-04c9-4095-b2ab-8a0133c403a1/HTTP_image1.png)

## GET

- 서버로부터의 정보를 조회하기 위한 용도의 메소드 이다.
- 전송할때마다 데이터를 Body 말고 URL끝에 ?와 함께 이름과 쌍을 이루어 전송하게 된다.
- 불필요한 요청을 제한하기 위한 요청이 캐시가 될수 있다.

## POST

- 리소스를 생성/ 변경하는 용도로 설계가 되어있다.
- 전송할 데이터를 HTTP Body에 담아서 전송을 한다.
- 대용량 데이터 전송이 가능
- 직접적인 노출이 되지는 않아 GET보다는 안전하다고 생각할수 있지만 요청 내용 확인이 가능해 역시 암호화를 해야한다.
- POST로 전송시 헤더의 Content-Type에 요청 데이터의 타입 표시 해야함.

### GET VS POST

Get 은 Idempotent / Post 는 Non-Idempotent

idempotent(연산을 여러번 적용해도 같은 값)

즉 GET은 여러번 요청을 해도 동일한 값이 돌아와야함

 
POST는 반대

---

## Reference

[https://hongsii.github.io/2017/08/02/what-is-the-difference-get-and-post/](https://hongsii.github.io/2017/08/02/what-is-the-difference-get-and-post/)

[https://javaplant.tistory.com/18](https://javaplant.tistory.com/18)