# Spring Boot Pagination Practice Project

이 프로젝트는 Spring Boot에서 게시물 데이터를 효율적으로 검색하고 페이지네이션하는 방법을 학습하기 위한 예제 프로젝트입니다.

## 프로젝트 소개

이 프로젝트는 게시물 검색 시스템을 구현하면서 다음과 같은 주요 기술들의 실제 활용법을 학습할 수 있도록 구성되었습니다.

- JPA Specification을 활용한 동적 쿼리 구현
- QueryDSL을 사용한 타입 세이프한 쿼리 작성
- Spring Data JPA의 페이지네이션 기능 활용
- 효율적인 검색 기능 구현

## 주요 기능

이 프로젝트에서는 다음과 같은 기능들을 구현하고 있습니다.

- 다양한 조건을 활용한 게시물 검색
  - 제목, 작성자, 카테고리 기반 검색
  - 작성일 범위 검색
  - 좋아요 수 기반 필터링
- 검색 결과 정렬
- 페이지 단위 조회

## 기술 스택

- Java 17
- Spring Boot 3.4.1
- Spring Data JPA
- QueryDSL 5.0.0
- PostgreSQL
- Lombok
- Gradle

## 학습 포인트

이 프로젝트를 통해 다음과 같은 내용들을 학습할 수 있습니다.

1. JPA Specification과 QueryDSL의 차이점과 각각의 장단점
2. 효율적인 동적 쿼리 작성 방법
3. Spring Data JPA의 페이지네이션 구현 방법
4. 데이터베이스 쿼리 최적화 기법

## 프로젝트 구조

프로젝트는 계층형 아키텍처를 따르고 있습니다.

- Controller: API 엔드포인트 정의
- Service: 비즈니스 로직 처리
- Repository: 데이터 접근 계층
  - JPA Specification
  - QueryDSL Repository

## 시작하기

### 필수 조건

- Java 17
- PostgreSQL

### 데이터베이스 설정

`application.yml` 파일을 추가한 이후 데이터베이스 연결 정보를 설정합니다.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/pagination_practice
    username: your_username
    password: your_password
    driver-class-name: org.postgresql.Driver
```

### 빌드 및 실행

```bash
./gradlew clean build
./gradlew bootRun
```

## API 엔드포인트

프로젝트는 두 가지 검색 API를 제공합니다:

- `GET /api/posts/specification`: JPA Specification을 사용한 검색
- `GET /api/posts/querydsl`: QueryDSL을 사용한 검색

각 API는 동일한 검색 기능을 제공하지만, 다른 구현 방식을 사용합니다.
