
# SQLGrammarException: could not prepare statement

## 문제 

JPA Entity를 선언했을때 발생.

## 해결

1. Spring Boot 2.5 이상버전에서 data.sql 을 먼저 읽게 변경되어 발생하는 문제라고함.

```xml
spring.jpa.defer-datasource-initialization: true 
```
설정으로 해결 가능

2. Entity name이 예약어로 되어 있는경우 문제 발생.