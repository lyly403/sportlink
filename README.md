# 프로젝트 개발환경

## IDE
- IntelliJ IDEA Community

## 기술 스택
- Spring Boot 3.2.5
- JDK 17
- MySQL / driver ==> 버전 8.0.37
- Spring Data JPA
- Thymeleaf
- JavaScript

  ## DB본인 컴퓨터에 설치된 DB적용시
- application.yml 파일에 주석처리로 변경사항 적용 안내
- DB버전 및 DB이름 통일 필요할듯 논의 후 결정 (현파일 데이터 베이스이름 : sportslink )

### 마리아DB로 변경하려면 다음과 같이 데이터베이스 URL과 드라이버 클래스 이름을 변경
```
spring:
datasource:
driver-class-name: org.mariadb.jdbc.Driver
url: jdbc:mariadb://localhost:3306/sportslink?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
username: root
password: 1234
thymeleaf:
cache: false
jpa:
database-platform: org.hibernate.dialect.MariaDBDialect
open-in-view: false
show-sql: true
hibernate:
ddl-auto: update
```ate
      ```
