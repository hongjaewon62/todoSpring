# 음성인식 기반 TODO 앱

**이 TODO 앱은 React와 MUI, 음성인식 라이브러리를 사용하여 구현한 할 일 앱 백엔드입니다.**

**텍스트 입력 외에도 음성 인식 기능을 통해 할 일을 추가할 수 있습니다.**

## 기능 설명

- **할 일 CRUD(생성, 조회, 수정, 삭제)**

- **완료 / 미완료 상태 변경**

- **정렬 기능**
  - 전체 / 최신순 / 오래된순 / 완료 / 미완료 기준으로 필터링


## 빌드 및 실행 방법
### DB 백업 파일
[todo_hongjaewon.sql](https://github.com/user-attachments/files/21948354/todo_hongjaewon.sql)

1. git clone https://github.com/hongjaewon62/todoSpring.git
2. DB 백업 파일 설치
3. application.properties 설정
4. 복사한 프로젝트 실행

- application.properties 설정 예시
  - spring.application.name=todoSpring
  - server.servlet.encoding.charset=UTF-8
  - server.servlet.encoding.force=true
  - spring.datasource.url=jdbc:mysql://localhost:3306/todo?useSSL=false&serverTimezone=Asia/Seoul
  - spring.datasource.username=root
  - spring.datasource.password=1234
  - spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  - spring.jpa.hibernate.ddl-auto=update
  - spring.jpa.show-sql=true
  - spring.jackson.serialization.indent_output=true


## 주력으로 사용한 라이브러리
- **Spring Data JPA** : CRUD 구현 간소화를 위해 사용

- **MySQL Driver** : MySQL 연동을 위해 사용

- **Lombok** : DTO, Entity 코드 간결화를 위해 사용

## API 명세
|Method|URL|설명|
|------|---|---|
|GET|/api/todoList|할 일 전체 조회|
|GET|/api/todoList/{id}|할 일 ID로 조회|
|POST|/api/todoList|할 일 생성|
|PUT|/api/todoList/{id}|할 일 수정|
|PUT|/api/todoList/{id}/completed|할 일 완료 / 미완료 변경|
|DELETE|/api/todoList/{id}|할 일 삭제|

## 테스트 케이스
SpringBoot Test에서 작성

src/test/java/에서 실행 가능

## 추가적인 업무 API
정렬 API 작성

## 데이터베이스
Mysql 8.0.11 버전 사용

## 프론트 리포지토리
https://github.com/hongjaewon62/TODO
