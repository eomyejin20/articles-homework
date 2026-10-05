# 게시판 REST API 프로젝트

회원 가입/로그인, 게시글, 댓글, 첨부파일 다운로드 기능을 제공하는 Spring Boot 기반 REST API 서버입니다.

## 기술 스택

- Java, Spring Boot (Spring MVC)
- Lombok
- Jakarta Bean Validation
- HttpSession 기반 로그인 처리

## 주요 기능

### 1. 회원 (Members)

| 기능      | Method | URL               | 설명                                                           |
| --------- | ------ | ----------------- | -------------------------------------------------------------- |
| 회원 가입 | POST   | `/members`        | JSON(`@RequestBody`)으로 가입 정보 전달, 가입된 회원 정보 반환 |
| 로그인    | GET    | `/members/login`  | 로그인 성공 시 세션(`__LOGIN_USER__`)에 회원 정보 저장         |
| 로그아웃  | GET    | `/members/logout` | 세션 만료 처리 및 로그아웃 상태 업데이트 (로그인 필요)         |
| 회원 탈퇴 | DELETE | `/members/delete` | 비밀번호(`password`) 확인 후 탈퇴 (로그인 필요)                |

### 2. 게시글 (Articles)

| 기능             | Method | URL                               | 설명                                             |
| ---------------- | ------ | --------------------------------- | ------------------------------------------------ |
| 게시글 목록 조회 | GET    | `/articles`                       | 전체 게시글 목록 반환                            |
| 게시글 상세 조회 | GET    | `/articles/{articleId}`           | 게시글 1건 조회                                  |
| 게시글 등록      | POST   | `/articles`                       | 작성자 이메일은 세션에서 자동 설정 (로그인 필요) |
| 게시글 수정      | PUT    | `/articles/{articleId}`           | 작성자 이메일은 세션에서 자동 설정 (로그인 필요) |
| 게시글 삭제      | DELETE | `/articles/{articleId}`           | 게시글 삭제                                      |
| 게시글 추천      | PUT    | `/articles/recommend/{articleId}` | 추천 수 증가 후 결과 반환                        |

### 3. 댓글 (Replies)

| 기능           | Method | URL                                                 | 설명                      |
| -------------- | ------ | --------------------------------------------------- | ------------------------- |
| 댓글 목록 조회 | GET    | `/articles/{articleId}/replies`                     | 게시글의 전체 댓글 조회   |
| 댓글 등록      | POST   | `/articles/{articleId}/replies`                     | 로그인 필요               |
| 댓글 수정      | PUT    | `/articles/{articleId}/replies/{replyId}`           | 로그인 필요               |
| 댓글 삭제      | DELETE | `/articles/{articleId}/replies/{replyId}`           | 댓글 삭제                 |
| 댓글 추천      | PUT    | `/articles/{articleId}/replies/recommend/{replyId}` | 추천 수 증가 후 결과 반환 |

### 4. 첨부파일 (Files)

| 기능          | Method | URL                                             | 설명                                   |
| ------------- | ------ | ----------------------------------------------- | -------------------------------------- |
| 파일 다운로드 | GET    | `/filesets/{fileSetId}/files/download/{fileId}` | 저장된 파일을 원본 파일명으로 다운로드 |

## 구조

articles-homework
├─ src/main/java/com/ktdsuniversity/edu
│ ├─ articles # 게시글 도메인 관련
│ │ ├─ dao
│ │ │ └─ ArticlesDao.java # 게시글 DB 접근 인터페이스
│ │ ├─ service
│ │ │ ├─ ArticlesService.java # 게시글 비즈니스 로직 인터페이스
│ │ │ └─ ArticlesServiceImpl.java # 게시글 비즈니스 로직 구현체
│ │ ├─ vo
│ │ │ ├─ request
│ │ │ │ ├─ ModifyArticleVO.java # 게시글 수정 요청 데이터
│ │ │ │ └─ RegistArticleVO.java # 게시글 등록 요청 데이터
│ │ │ └─ response
│ │ │ ├─ ArticleListVO.java # 게시글 목록 응답 데이터
│ │ │ └─ ArticlesVO.java # 게시글 상세 응답 데이터
│ │ └─ web
│ │ └─ ArticlesController.java # 게시글 REST API 컨트롤러
│ │
│ ├─ commons # 공통 모듈
│ │ ├─ crypto
│ │ │ ├─ encrypt.hash
│ │ │ │ └─ SHA.java # SHA 해시 암호화 유틸
│ │ │ ├─ AES.java # AES 대칭키 암호화 유틸
│ │ │ └─ Test.java # 암호화 테스트
│ │ └─ util
│ │ └─ ApiResponse.java # API 공통 응답 규격 객체
│ │
│ ├─ files # 첨부파일 도메인 관련
│ │ ├─ components
│ │ │ └─ MultipartHandler.java # 파일 업로드/저장 처리 핸들러
│ │ ├─ dao
│ │ │ └─ FilesDao.java # 파일 DB 접근 인터페이스
│ │ ├─ service
│ │ │ ├─ FilesService.java # 파일 서비스 인터페이스
│ │ │ └─ FilesServiceImpl.java # 파일 서비스 구현체 (다운로드 등)
│ │ ├─ vo
│ │ │ ├─ request
│ │ │ │ ├─ RequestFileSetVO.java # 파일셋 등록 요청 데이터
│ │ │ │ └─ RequestFileVO.java # 단일 파일 등록 요청 데이터
│ │ │ └─ response
│ │ │ ├─ FileSetVO.java # 파일셋 응답 데이터
│ │ │ └─ FilesVO.java # 파일 메타데이터 응답 데이터
│ │ └─ web
│ │ └─ FilesController.java # 파일 다운로드 REST API 컨트롤러
│ │
│ ├─ members # 회원 도메인 관련
│ │ ├─ dao
│ │ │ └─ MembersDao.java # 회원 DB 접근 인터페이스
│ │ ├─ service
│ │ │ ├─ MembersService.java # 회원 서비스 인터페이스
│ │ │ └─ MembersServiceImpl.java # 회원 서비스 구현체 (가입/로그인)
│ │ ├─ vo
│ │ │ ├─ request
│ │ │ │ ├─ LoginMemberVO.java # 로그인 요청 데이터
│ │ │ │ └─ RegistMembersVO.java # 회원가입 요청 데이터
│ │ │ └─ response
│ │ │ └─ MembersVO.java # 회원 정보 응답 데이터
│ │ └─ web
│ │ └─ MembersController.java # 회원가입/로그인 REST API 컨트롤러
│ │
│ ├─ replies # 댓글 도메인 관련
│ │ ├─ dao
│ │ │ └─ RepliesDao.java # 댓글 DB 접근 인터페이스
│ │ ├─ service
│ │ │ ├─ RepliesService.java # 댓글 서비스 인터페이스
│ │ │ └─ RepliesServiceImpl.java # 댓글 서비스 구현체
│ │ ├─ vo
│ │ │ ├─ request
│ │ │ │ ├─ ModifyReplyVO.java # 댓글 수정 요청 데이터
│ │ │ │ └─ RegistReplyVO.java # 댓글 등록 요청 데이터
│ │ │ └─ response
│ │ │ ├─ RepliesVO.java # 댓글 상세 응답 데이터
│ │ │ └─ ReplyListVO.java # 댓글 목록 응답 데이터
│ │ └─ web
│ │ └─ RepliesController.java # 댓글 REST API 컨트롤러
│ │
│ ├─ ArticlesApplication.java # Spring Boot 메인 실행 파일
│ └─ ServletInitializer.java # WAR 배포용 서블릿 초기화 설정
│
└─ src/main/resources # 리소스 및 설정
├─ com/ktdsuniversity/edu # MyBatis SQL Mapper XML 위치
│ ├─ articles/dao/mapper
│ │ └─ ArticlesDaoMapper.xml # 게시글 SQL 쿼리 매퍼
│ ├─ files/dao/mapper
│ │ └─ FilesDaoMapper.xml # 파일 SQL 쿼리 매퍼
│ ├─ members/dao/mapper
│ │ └─ membersDaoMapper.xml # 회원 SQL 쿼리 매퍼
│ └─ replies/dao/mapper
│ └─ RepliesDaoMapper.xml # 댓글 SQL 쿼리 매퍼
└─ config # 환경 설정 디렉토리
├─ mybatis-config.xml # MyBatis 환경 설정
└─ application.yml # DB 접속 및 파일 경로 설정
