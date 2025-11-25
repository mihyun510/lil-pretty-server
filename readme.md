# 조금 예뻐도 돼?(Diet & Date Web)

> Java Spring Boot 서버: 인증(JWT, OAuth2), 식단/레시피, 데이트 코스, KaKao Map 데이터 수집, 물 섭취 챌린지 기능을 제공하는 REST API 서버

---

## 개요

이 저장소는 Diet & Date 웹 서비스의 백엔드 서버입니다. 기술 스택으로는 Java, Spring Boot, Spring Security, JPA(hibernate), MySQL을 사용하며 인증은 OAuth2(카카오, 네이버)와 JWT 기반입니다. 관리자가 KaKao Map API로 수집한 즐겨찾기 데이터를 로컬 DB에 저장하여 프론트엔드에 제공합니다.

---

## 핵심 기능

* 인증/인가

  * 일반 로그인 (아이디/비밀번호) + JWT 발급
  * OAuth2 로그인 (Kakao) -> 회원 식별 후 JWT 발급
  * 회원가입(일반 회원가입 화면에서 받은 User 정보를 user 테이블에 Insert)
* 다이어트(Diet)

  * 식단 마스터 목록 조회
  * 식단 디테일 조회
  * 추천 식단 파일 생성 및 다운로드 (DB 조회 후 Java로 파일 생성 제공)
  * 오늘의 추천 식단 API
* 데이트(Date)

  * 가격대 필터로 데이트 유형 조회
  * 데이트 상세(리뷰 수 기반 순위, 리뷰 개수, 사용자 이미지 등 포함)
  * 데이트 코스 리스트 (가격/위치 포함)
* 붓기맵(Map)

  * KaKao Map API 연동: 데이터 수집 스크립트/스케줄러 제공
  * 수집 데이터 DB 적재 및 조회 API
* 챌린지(Challenge)

  * 사용자의 하루 물 섭취량 CRUD API
  * 캘린더 조회(일별 기록)

---

## 기술 스택

* Java 17+
* Spring Boot 3.x
* Spring Security (OAuth2 Client, JWT)
* Spring Data JPA (Hibernate)
* MySQL 8+
* Maven
* Lombok
* Map API: Kakao REST API

---

## 프로젝트 구조

```
src/
└─ main/
   ├─ java/
   │  └─ com/lil/pretty/
   │     ├─ config/
   │     │  ├─ exception/              # 예외 처리 관련 클래스
   │     │  ├─ security/               # Spring Security, JWT 설정
   │     │  └─ FileStorageConfig.java  # 파일 저장 설정
   │     │
   │     ├─ domain/
   │     │  ├─ common/
   │     │  │  ├─ controller/          # 공통 컨트롤러
   │     │  │  ├─ dto/                 # 공통 DTO
   │     │  │  ├─ model/               # 공통 엔티티
   │     │  │  └─ service/             # 공통 서비스 로직
   │     │  ├─ commoncode/             # 공통 코드 관리
   │     │  ├─ date/                   # 데이트존 관련 도메인
   │     │  ├─ diet/                   # 다이어트존 관련 도메인
   │     │  ├─ kakaomap/               # 카카오맵 API 연동 및 데이터 관리
   │     │  ├─ swellingmap/            # 붓기맵(챌린지) 관련 기능
   │     │  └─ user/                   # 사용자 / 인증 / 회원가입 관련 기능
   │     │
   │     └─ LilPrettyApplication.java  # Spring Boot 메인 클래스
   │
   └─ resources/
      ├─ application.yml               # 환경 설정
      ├─ static/                       # 정적 리소스(css, js)
      └─ templates/                    # 템플릿 (Thymeleaf 등)
```

---

## 인증 흐름

### 일반 로그인

1. 클라이언트 POST `/auth/login` {username, password}
2. 서버에서 인증 후 JWT(access token)를 발급하고 응답
3. 클라이언트는 Authorization: Bearer {token}으로 요청

### OAuth2 (Kakao)

* Spring Security OAuth2 Client 사용
* 프론트에서 OAuth 인증을 시작 -> 콜백을 백엔드로 받음
* 백엔드에서 제공자(access token)로 사용자 정보 획득
* 기존 사용자면 user 정보 리턴, 신규면 users 테이블에 insert 후 JWT 발급

### JWT 처리

* Access Token: 짧은 만료 시간(예: 15분)
* Refresh Token: 선택적으로 사용(긴 만료 시간)
* 토큰 저장: 웹에서는 httpOnly cookie 권장
* Spring Security filter로 JWT 검증

---

## 주요 API 엔드포인트
* `POST /auth/login` -> 로그인
* `POST /auth/save/user`  -> 회원가입
* `POST /api/date/detail/getDateDtlItems` -> 데이트존 목록 조회
* `POST /api/date/detail/getDateDtlReviews` -> 데이트존 리뷰 조회
* `POST /api/date/detail/getDateDtlCourse` -> 데이트존 상세 코스 조회
* `POST /api/date/detail/saveDateDtlItems` -> 데이트존 리뷰 저장
* `POST /api/date/master/dateItems` -> 데이트존 메인 목록 조회
* `POST /api/diet/detail/getMealDtlItems` -> 다이어트존 식단 상세 조
* `POST /api/diet/detail/getMealFavoriteItems` -> 다이어트존 식단 즐겨찾기 목록 조회
* `POST /api/diet/main/mealRecItemse` -> 다이어트존 추천 식단 목록 조회
* `POST /api//diet/master/mealsItems` -> 다이어트존 식단 목록 조회
* `POST /api/diet/master/saveMealFavorite` -> 다이어트존 식단 즐겨찾기 저장
* `POST /api/file/upload` -> 파일 업로드드
* `POST /api/file/download/${folder}/${fileName}` -> 파일 다운로드
* `POST /api/swellingMap/challenge/getWaterDailyItem` -> 하루 물 섭취량 조회
* `POST /api/swellingMap/challenge/getWaterDailyItem` -> 하루 물 섭취량 조회
* `GET /api/swellingMap/challenge/saveWaterDailyItem` -> 하루 물 섭취량 저장
---

## KaKao Map 데이터 수집(요약)

* `https://dapi.kakao.com/v2/local/search/keyword.json`에서 REST API 호출(장소 검색/상세) - 키워드 검색
* Admin API 에서 특정 장소 id 수집
* 수집 시 중복 체크(카카오 place id 기반) 후 DB insert/update

---

## 보안 권장사항

* JWT 비밀키는 환경변수/시크릿 매니저 사용
* Refresh token을 사용하면 무분별한 재로그인 방지 가능
* 민감한 데이터(비밀번호)는 BCrypt로 해시
* CORS 정책을 엄격히 설정

---

## 향후 확장 아이디어

* 챗봇 기능 추가
* client / admin 권한 별 도메인 추가

---
