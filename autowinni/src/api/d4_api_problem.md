# D4 선택형 Java API — 차량 등록과 단건 조회

- 권장 제한시간: 40분
- 기술 기준: Java, Spring MVC 또는 Spring Boot
- 풀이 시작 파일: `VehicleController.java`

이 문제는 오늘의 Java 알고리즘 문제를 대체하지 않는 선택형 보너스다. 알고리즘 세 문제와 복습을 마친 뒤 시간이 남을 때 진행한다.

## 문제

차량을 메모리에 등록하고 VIN으로 한 대를 조회할 수 있는 REST API를 구현하라.

데이터베이스는 사용하지 않는다. 애플리케이션이 실행되는 동안 등록된 차량만 메모리에 보관한다.

## 차량 데이터

차량은 다음 값을 가진다.

| 필드 | JSON 타입 | 조건 |
| --- | --- | --- |
| `vin` | 문자열 | 앞뒤 공백을 제거한 결과가 비어 있지 않아야 함 |
| `maker` | 문자열 | 앞뒤 공백을 제거한 결과가 비어 있지 않아야 함 |
| `price` | 숫자 | `0`보다 큰 정수 |

저장할 때 `vin`과 `maker`의 앞뒤 공백을 제거한다. 대소문자는 변경하지 않으며 VIN 비교는 대소문자를 구분한다.

## API 1 — 차량 등록

```http
POST /api/vehicles
Content-Type: application/json
```

요청 본문:

```json
{
  "vin": " KMH-100 ",
  "maker": " Hyundai ",
  "price": 15000
}
```

### 등록 성공

- HTTP 상태: `201 Created`
- `Location` 헤더: `/api/vehicles/KMH-100`
- 응답 본문에는 공백이 제거된 저장 결과를 담는다.

```json
{
  "vin": "KMH-100",
  "maker": "Hyundai",
  "price": 15000
}
```

### 잘못된 요청

요청 본문이 없거나 차량 데이터 조건을 만족하지 않으면 다음과 같이 응답한다.

- HTTP 상태: `400 Bad Request`

```json
{
  "code": "INVALID_REQUEST",
  "message": "Invalid vehicle request"
}
```

### VIN 중복

이미 등록된 VIN과 정확히 같은 VIN을 다시 등록하면 다음과 같이 응답한다.

- HTTP 상태: `409 Conflict`

```json
{
  "code": "DUPLICATE_VIN",
  "message": "Vehicle already exists: KMH-100"
}
```

중복 요청으로 기존 차량 데이터를 덮어쓰면 안 된다.

## API 2 — 차량 단건 조회

```http
GET /api/vehicles/{vin}
```

### 조회 성공

VIN이 존재하면 다음과 같이 응답한다.

- HTTP 상태: `200 OK`
- 응답 본문: 등록할 때 저장한 차량 데이터

```json
{
  "vin": "KMH-100",
  "maker": "Hyundai",
  "price": 15000
}
```

### 차량 없음

VIN이 존재하지 않으면 다음과 같이 응답한다.

- HTTP 상태: `404 Not Found`

```json
{
  "code": "VEHICLE_NOT_FOUND",
  "message": "Vehicle not found: UNKNOWN"
}
```

## 구현 조건

1. HTTP 요청과 응답을 담당하는 클래스와 차량 저장·조회를 담당하는 클래스를 분리한다.
2. 의존성은 생성자를 통해 전달한다.
3. 차량은 VIN을 키로 사용하는 메모리 저장소에 보관한다.
4. 등록 성공, 잘못된 요청, 중복 VIN, 조회 성공, 차량 없음에 맞는 HTTP 상태를 반환한다.
5. 오류 응답도 위 형식의 JSON 본문을 반환한다.
6. Lombok을 사용하지 않는다.

DTO는 일반 Java 클래스로 작성한다. 연습 환경에서 지원한다면 `record`를 사용해도 된다.

## 제외 범위

다음 항목은 구현하지 않아도 된다.

- 데이터베이스와 JPA
- 인증과 인가
- 수정과 삭제 API
- 페이지네이션
- 동시성 제어
- Swagger 또는 OpenAPI 설정
- 전역 예외 처리

## 직접 확인할 요청

1. 정상 차량 등록
2. 등록한 차량 조회
3. 같은 VIN을 다시 등록
4. 존재하지 않는 VIN 조회
5. `vin`이 `null`, 빈 문자열 또는 공백뿐인 요청
6. `maker`가 `null`, 빈 문자열 또는 공백뿐인 요청
7. `price`가 `0` 또는 음수인 요청
8. JSON 요청 본문이 없는 경우
9. 중복 등록 실패 후 기존 차량 데이터가 유지되는지 확인

## 풀이 후 기록

- 사용 시간:
- 만든 클래스와 각 책임:
- 각 상황에 사용한 HTTP 상태 코드:
- 입력 검증을 둔 위치와 이유:
- 실제 서비스라면 추가할 항목:
