# 항해 사전 spring-study

## 1. Use-Case

![img](./images/use-cases.png)

- why?

회원이 서비스를 이용하게될 때 직접 경험하는 행동이 무엇인지를 생각하여 연결을 했다.


## 2. 테이블 스키마

![img](./images/table-schema-ver-1.png)

HTTP 프로토콜은 상태를 유지하지 않는다. 

로그인 상태를 유지하려면, JWT(액세스토큰)을 사용하여 구현이 필요하다.

1차적으로는 비회원으로도 Posts(게시글)을 작성할 수 있도록 하고, 2차에서는 유저 로그인 기능을 추가해야될거같다.


## 3. API 명세서

[api-dog 명세서](https://www.apidog.com/apidoc/shared-4d19564a-0cf9-4cc5-8d35-b642dd1eb7a0)
