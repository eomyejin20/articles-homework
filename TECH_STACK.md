# 주요 기술 및 핵심 개념 정리

---

## 1. Bean Container

- **개념 및 역할**
  - 메모리 최적화의 자동화
  - 객체 생성, 파이프라인 등 인스턴스를 별도의 공간에 보관
  - Spring이 객체(Bean)를 생성해 Bean Container에 보관하고, 필요한 곳에 DI로 넣어주는 역할.

- **@ (에노테이션) 역할**
  - Spring이 객체를 관리해줌.
  - **@Controller**: 클라이언트 요청을 받는 End-Point(URL)를 생성한다.
  - **@RestController**: @Controller + @ResponseBody(클래스 내 모든 메서드)다. 반환 객체가 JSON이다.
  - **@Service**: 비즈니스 로직과 트랜잭션(Commit/Rollback)을 제어한다.
  - **@Mapper (@Repository)**: 저장소로, DB 제어(DML)를 담당한다. @Mapper는 Spring @Respository 를 한 번 감싼 애노테이션으로 mabatis가 익명의 클래스를 만들어서 데이터베이스에 접근하도록 한다.
  - **@Component**: Service가 Service를 호출할 때, \*\*\*\*계층이 명확하지 않은 일반 클래스를 Bean으로 등록할 때 쓰인다.원래는 @Component 만 Spring이 객체 관리→ 다른 에노테이션들이 Component를 상속받고 있음. @Controller, @Service, @Repository의 기반이 되는 에노테이션이다. (예: ApiResponse 같은 공통 유틸)
  - **@Data**: @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 한 번에 만들어줌.
  - **@NoArgsConstructor**: 기본생성자를 자동으로 생성한다.
  - **@AllArgsConstructor**: 모든 멤버변수들을 파라미터로 가지는 생성자를 자동으로 생성한다.

---

## 2. HTTP 요청 매핑

- **@GetMapping**: GET 요청, 조회
- **@PostMapping**: POST 요청, 등록
- **@PutMapping**: PUT 요청, 수정
- **@DeleteMapping**: DELETE 요청, 삭제

---

## 3. MultipartFile

- 업로드된 파일을 받는 인터페이스이다. 파일 정보를 읽고 저장한다.
- 파일은 JSON으로 받을 수 없고, form-data로 받는다.

---

## 4. 요청/응답 데이터 처리

- **사용 위치**: Controller에서 사용한다.

- **어노테이션 상세**
  - **@RequestBody**: HTTP 요청 Body의 JSON을 객체로 변환해 받는다. (예: createNewMember의 RegistMembersVO) . 파일 업로드와 관계가 있다.
    - _@RequestBody가 없다면 **@ModelAttribute(생략 가능)** → JSON 빼고 전부 (form-data, urlencoded, 쿼리스트링) 받음._
    - _클라이언트가 컨트롤러로 전송한 파라미터(폼파라미터, 쿼리스트링파라미터)를 자동으로 받아오는 역할이다. (예: 파일 업로드를 위해 form-data 형식으로 주는RegistArticleVO, ModifyArticleVO)_
  - **@ResponseBody**: 메서드가 반환한 객체를 JSON으로 변환해 응답 Body에 담는다. (예: ArticlesController). 파일 업로드와 관계 없음.
  - **@PathVariable**: URL 경로의 {articleId} 같은 값을 파라미터로 받는다. (예: /articles/{articleId})
  - **@RequestParam**: 쿼리스트링이나 폼 파라미터 하나를 받는다. (예: exitMember의 password)

---

## 5. 파라미터 유효성 검사

- **@Valid**: 요청 객체(VO)에 선언된 검증 규칙을 실행한다. @NotBlank, @Email, @Pattern, @Size가 자주 사용된다. @PathVariable처럼 객체가 아닌 단순 타입에는 @Valid를 쓰지 않고, 검증 에노테이션을 파라미터 앞에 바로 붙인다.
- **BindingResult**: 검증 결과를 담는 객체이다. 반드시 @Valid 파라미터 바로 뒤에 선언해야 하며, hasErrors()로 실패 여부를, getFieldErrors()로 오류 목록을 확인한다.

---

## 6. 설정값 주입

- **@Value**: application.yml의 설정값을 변수에 바인딩한다. (예: @Value("${app.multipart.store-path}")) 저장 경로를 바꿀 때 yml만 수정하면 된다.

---

## 7. 세션

- **HttpSession**: 서버가 사용자별 상태를 관리하기 위해 Java Servlet에서 제공하는 세션 관리 객체이다. Http: 상태를 기억하지 못하는 Stateless Protocol이므로 이전 요청의 사용자 상태를 기억하지 못한다.
- **Session**: 여러 HTTP 요청 사이에서 사용자의 상태를 유지하기 위한 저장 공간이다.
- **인증 식별**: 클라이언트는 세션 ID(JSESSIONID Cookie)를 통해 자신의 세션을 식별한다.
- **주요 메서드 및 동작**
  - `setAttribute("__LOGIN_USER__", member)`: 로그인 정보 저장
  - `getAttribute("__LOGIN_USER__")`: 로그인 정보 조회
  - `invalidate()`: 세션 만료(로그아웃)
