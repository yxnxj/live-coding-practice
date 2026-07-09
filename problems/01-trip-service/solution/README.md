# 01 Trip Service

## Type

Legacy testing / Refactoring

## Source

- https://github.com/sandromancuso/trip-service-kata

## Problem

여행자를 위한 소셜 네트워크 서비스가 있다. 사용자는 친구를 가질 수 있고, 여러 여행을 등록할 수 있다. 서비스의 핵심 기능은 "특정 사용자의 여행 목록을 조회하는 것"이다.

현재 코드는 오래된 레거시 코드라고 가정한다. `TripService`는 로그인 세션과 여행 데이터 저장소에 직접 의존하고 있어서 단위 테스트가 어렵다. 목표는 기능을 바꾸지 않고 기존 동작을 테스트로 고정한 뒤, 테스트 가능한 구조로 리팩터링하는 것이다.

## Business Rules

- 로그인하지 않은 사용자가 여행 목록을 조회하려고 하면 `UserNotLoggedInException`을 던진다.
- 로그인한 사용자는 친구의 여행 목록만 볼 수 있다.
- 조회 대상 사용자가 로그인 사용자와 친구 관계가 아니면 빈 목록을 반환한다.
- 조회 대상 사용자가 로그인 사용자와 친구 관계이면 조회 대상 사용자의 여행 목록을 반환한다.
- 친구 관계 판단은 조회 대상 사용자의 친구 목록에 로그인 사용자가 포함되어 있는지로 결정한다.

## Constraints

- `TripService`의 public API를 바꾸지 않는다.
  - 생성자 시그니처를 바꾸지 않는다.
  - `getTripsByUser(user: User): List<Trip>` 시그니처를 바꾸지 않는다.
- `TripService`에 필드 상태를 추가하지 않는다.
  - 원본 카타에서 `TripService`는 stateless service로 취급한다.
  - 상태를 추가하면 멀티스레드 환경에서 문제가 생길 수 있다.
- `TripService`의 테스트는 실제 `UserSession`과 실제 `TripDAO`를 호출하지 않아야 한다.
  - 이 둘은 HTTP session, database 같은 외부 리소스에 의존한다고 가정한다.
  - 테스트는 `TripService`의 판단 로직만 검증해야 한다.
- 테스트로 보호되지 않은 프로덕션 코드는 손으로 고치지 않는다.
  - 꼭 필요한 구조 변경은 IDE의 안전한 리팩터링 기능을 사용한다고 가정한다.

## Suggested Test Cases

- 로그인 사용자가 없으면 예외를 던진다.
- 로그인 사용자가 있지만 조회 대상과 친구가 아니면 빈 목록을 반환한다.
- 로그인 사용자가 조회 대상의 친구이면 조회 대상의 여행 목록을 반환한다.
- 조회 대상에게 친구가 여러 명 있어도 로그인 사용자가 포함되어 있으면 여행 목록을 반환한다.

## Done Criteria

- 기존 동작을 설명하는 테스트가 있다.
- 실제 `UserSession`과 `TripDAO`를 호출하지 않는 `TripService` 단위 테스트가 있다.
- public API와 stateless 성격을 유지한다.
- 최종 코드에서 로그인 확인, 친구 여부 판단, 여행 목록 조회 흐름을 설명할 수 있다.

## IntelliJ / Run

IntelliJ에서 이 문제를 풀 때는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.

이 폴더 기준으로 테스트를 실행한다.

```bash
sh run-tests.sh
```

Gradle import가 실패하면 Gradle JVM을 JDK 21로 맞춘다.
