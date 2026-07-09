# 01 여행 서비스

## 문제 유형

레거시 코드 테스트 / 리팩터링

## 연습 목표

- 테스트하기 어려운 레거시 코드에 characterization test를 추가한다.
- 전역 상태, static dependency, 외부 세션 의존성을 분리한다.
- 기능 변경 없이 테스트 가능한 구조로 이동한다.
- 테스트와 프로덕션 코드가 최종적으로 비즈니스 규칙을 명확히 설명하게 만든다.

## 원본

- https://github.com/sandromancuso/trip-service-kata

## 문제 배경

여행자를 위한 소셜 네트워크 서비스가 있다고 가정한다.

- 콘텐츠를 보려면 로그인해야 한다.
- 다른 사용자의 여행 목록을 보려면 그 사용자와 친구여야 한다.
- 로그인하지 않았다면 예외를 던진다.

## 연습 규칙

- 목표는 `TripService` 클래스에 대한 테스트를 작성해 충분히 동작을 고정한 뒤 리팩터링하는 것이다.
- 테스트로 보호되지 않은 프로덕션 코드는 수동으로 바꾸지 않는다.
- `TripService`를 테스트하기 위해 꼭 필요한 변경은 IDE의 자동 리팩터링을 이용해 수행한다.
- 리팩터링이 끝났을 때 테스트와 프로덕션 코드는 비즈니스 규칙을 분명하게 드러내야 한다.

## 제약 사항

- `TripService`의 public interface를 바꾸지 않는다.
  - 생성자를 바꾸지 않는다.
  - 메서드 시그니처를 바꾸지 않는다.
- `TripService`에 상태를 추가하지 않는다.
  - 원본 카타에서 `TripService`는 stateless로 간주한다.
  - 상태를 추가하면 멀티스레드 환경에서 문제가 생길 수 있다.
- `TripService` 단위 테스트는 실제 `UserSession`과 실제 `TripDAO`를 호출하지 않아야 한다.
  - 실제 collaborator는 HTTP session, database 같은 외부 리소스에 의존한다고 가정한다.
  - 단위 테스트는 `TripService`의 판단 로직에만 집중해야 한다.

## 로컬 스타터 프로젝트

스타터 프로젝트는 `solution/` 아래에 있다.

```text
solution/
  README.md
  run-tests.sh
  src/main/kotlin/tripservicekata/...
  src/test/kotlin/tripservicekata/TripServiceTest.kt
```

아래 명령으로 테스트를 실행한다.

```bash
cd live-coding-practice/problems/01-trip-service/solution
sh run-tests.sh
```

## 완료 기준

- 기존 동작을 깨지 않는 테스트가 있다.
- `TripService` 단위 테스트가 실제 `UserSession`과 실제 `TripDAO`를 호출하지 않는다.
- `TripService`의 public interface와 stateless 성격을 유지한다.
- 리팩터링 의도를 말로 설명할 수 있다.
