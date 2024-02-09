### 구현 방향

---

* 회원 가입 시, 핸드폰번호와 비밀번호가 있다
  * 핸드폰번호를 userId 값으로 사용하려고 한다
  * 그러나 핸드폰번호는 개인정보기에 저장시에는 비밀번호와 똑같이 단방향 암호화로 저장한다
* 회원 가입과 로그인을 할 경우는, permit을 해서 SecurityContextHoler를 가지고 있지 않아도 되게 설정
* 로그인을 하면, DB 해당 record에 발급한 JWT token로 업데이트
* 반대로, 로그아웃을 하면 DB 해당 record에 token을 빈공백으로 update