# 트러블슈팅 및 핵심 개념 정리

프로젝트 개발 과정에서 발생한 오류 해결 과정과 사용된 주요 개념을 정리한 문서입니다.  
_(※ 개발 진행 상황에 따라 지속적으로 업데이트 예정)_

---

## 1. 설치 및 환경 설정 오류

### 1-1. Java 21 버전에 따른 호환성 문제

- **문제 상황**: 기존 Java 26 버전 사용으로 인해 이전 프로젝트 실행 시 호환성 오류 발생
- **해결 방법**:
  1. JDK 21 재설치 및 시스템 환경변수(`JAVA_HOME`) 재설정
  2. STS(Eclipse) 실행 환경 JRE를 Java 21로 변경

### 1-2. Lombok 미인증/미설치 오류

- **문제 상황**: Lombok 라이브러리가 STS에 적용되지 않아 이전 프로젝트 및 어노테이션 인식 오류 발생
- **해결 방법**:
  1. `lombok.jar` 재다운로드 후 Terminal/CMD에서 `java -jar lombok.jar` 실행하여 STS 연동 설치
  2. STS 재시작 후 프로젝트 우클릭 → `Maven` → `Update Project (Alt + F5)` 진행

### 1-3. DBeaver Oracle SQL 테이블 미생성 오류

- **문제 상황**: `articles` 관련 테이블 및 SQL 스크립트가 데이터베이스에 생성되지 않아 접근 불가
- **해결 방법**: DBeaver에서 데이터베이스 연결 확인 후 `articles` 관련 DDL/DML SQL 스크립트 재작성 및 실행

---

## 2. 개발 및 코드 작성 오류

### 2-1. Oracle DB 접속 패스워드 오입력

- **문제 상황**: `application.yml` 파일 내 데이터베이스 비밀번호 오타로 인해 DB Connection Fail 발생
- **해결 방법**: `application.yml` 설정 정보 수정
  ```yaml
  spring:
    datasource:
      username: SPRING_BOOT
      password: SPRING_BOOT_1234
  ```
