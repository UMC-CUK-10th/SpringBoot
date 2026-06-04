# UMC 10th SpringBoot

UMC CUK 10th SpringBoot 백엔드 애플리케이션 개발 프로젝트입니다.

---

## DB 실행 (Docker)
만약 로컬 환경에 MySQL이 설치되어 있지 않다면, 인프라 폴더로 이동해 Docker Compose를 실행합니다.
```bash
cd infra
docker compose up -d
```
> [!NOTE]
> * 데이터베이스 종류: **MySQL 8.0**
> * 포트: **3306**
> * 데이터베이스명: **umc10th**

---

## Environment Variables (IntelliJ 실행 기준)
IntelliJ → Run → Edit Configurations → Environment variables 에 아래 값 그대로 추가:

---

## OAuth2 Redirect URL 설정
카카오 로그인을 정상 연동하기 위한 백엔드 리다이렉트 주소 설정입니다.

* **로컬 테스트 / 백엔드 구동**: `KAKAO_REDIRECT_URI=http://localhost:8080/login/oauth2/code/kakao`
  * 카카오 디벨로퍼스 콘솔의 **[카카오 로그인] > [Redirect URI]** 및 **[플랫폼] > [Web]** 설정에도 동일하게 `http://localhost:8080/login/oauth2/code/kakao` 및 `http://localhost:8080`이 등록되어 있어야 합니다.

---

## 주요 테스트 및 API 엔드포인트
* **카카오 OAuth2 로그인 시작 주소**:
  `http://localhost:8080/oauth2/authorization/kakao`
* **API Swagger 문서 주소**:
  `http://localhost:8080/swagger-ui/index.html`
* **로컬 DB 연결성 확인 테스트 API**:
  `http://localhost:8080/api/v1/auth/test-db`
