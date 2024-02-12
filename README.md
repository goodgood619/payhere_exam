### 구현 방향 (회원 가입, 로그인, 로그아웃)

---

* 회원 가입 시, 핸드폰번호와 비밀번호가 있다
  * 핸드폰번호를 userId 값으로 사용하려고 한다
  * 그러나 핸드폰번호는 개인정보기에 저장시에는 비밀번호와 똑같이 단방향 암호화로 저장한다
* 회원 가입과 로그인을 할 경우는, permit을 해서 SecurityContextHoler를 가지고 있지 않아도 되게 설정
* 로그인을 하면, DB 해당 record에 발급한 JWT token로 업데이트
* 반대로, 로그아웃을 하면 DB 해당 record에 token을 빈공백으로 update

### 구현 방향 (상품)

---

* 상품의 부분 수정은 client에서 data를 입력받는 경우만 처리 하므로
* 모든 필드들을 nullable 하게 허용하고, 실제로 data가 있는 케이스만 update
* 초성 검색을 위해, 상품 저장시 초성 필드를 별도로 저장할 예정


### 문제 해결

---

* Mac M1 Docker Setting
  * https://velog.io/@sujeongim/%EC%98%A4%EB%A5%98-%EC%B2%9C%EA%B5%AD-Docker%ED%8E%B8-Mac-M1-no-matching-manifest-for-linuxarm64v8
* mysql UTF8 설정
  * https://m.blog.naver.com/PostView.naver?blogId=playhoos&logNo=221509276474&proxyReferer=
* cursor-based-pagination 서치
  * https://jojoldu.tistory.com/528

### DDL 작성

---

```mysql
     create table product (
         original_price decimal(38,2),
         price decimal(38,2),
         expiration_date datetime(6),
         id bigint not null auto_increment,
         bar_code varchar(255),
         category varchar(255),
         description varchar(255),
         name varchar(255),
         name_chosung varchar(255),
         size enum ('SMALL','LARGE'),
         primary key (id)
     ) engine=InnoDB;
 
     create table users (
         created_at datetime(6),
         updated_at datetime(6),
         id varchar(255) not null,
         token varchar(255), # access_token 저장용
         user_password varchar(255),
         primary key (id)
   ) engine=InnoDB;
```