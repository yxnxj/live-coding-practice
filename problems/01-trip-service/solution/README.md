# Trip Service Kata

레거시 코드 리팩터링을 연습하기 위한 작은 Kotlin 스타터 프로젝트다.

프로덕션 코드는 의도적으로 테스트하기 불편한 의존성을 가지고 있다.

- `TripService`가 `UserSession.getInstance()`를 직접 호출한다.
- `TripService`가 `TripDAO.findTripsByUser(user)`를 직접 호출한다.
- seam을 찾기 전에는 의미 있는 테스트를 작성하기 어렵다.

## 목표

기존 동작을 테스트로 고정한 뒤, 안전하게 리팩터링한다.

## 비즈니스 규칙

여행자를 위한 소셜 네트워크 서비스가 있다고 가정한다.

- 콘텐츠를 보려면 로그인해야 한다.
- 다른 사용자의 여행 목록을 보려면 그 사용자와 친구여야 한다.
- 로그인하지 않았다면 예외를 던진다.

## 기존 동작

- 로그인한 사용자가 없으면 `TripService#getTripsByUser`는 `UserNotLoggedInException`을 던진다.
- 로그인한 사용자가 조회 대상 사용자의 친구라면, 조회 대상 사용자의 여행 목록을 반환한다.
- 로그인한 사용자가 조회 대상 사용자의 친구가 아니라면, 빈 목록을 반환한다.

## 카타 제약

- 테스트로 보호되지 않은 프로덕션 코드는 수동으로 바꾸지 않는다.
- `TripService`의 public interface를 바꾸지 않는다.
  - 생성자를 바꾸지 않는다.
  - `getTripsByUser`의 시그니처를 바꾸지 않는다.
- `TripService`에 상태를 추가하지 않는다.
- `TripService` 단위 테스트는 실제 `UserSession`과 실제 `TripDAO`를 호출하지 않는다.
- seam이 필요하다면 public API를 바꾸지 않는 방식으로 만든다.

## 테스트 실행

```bash
sh run-tests.sh
```

IntelliJ IDEA에서는 `solution/` 폴더를 Gradle 프로젝트로 열면 된다. 선언 위치 찾기, import resolution, 소스 루트 인식이 제대로 동작하려면 `build.gradle.kts` 기준으로 프로젝트를 import한다.

이 프로젝트는 Gradle Wrapper와 JDK 21 기준으로 동작하도록 맞춰져 있다. IntelliJ에서 Gradle import가 실패하면 아래 설정을 확인한다.

- `Settings > Build, Execution, Deployment > Build Tools > Gradle`
- `Use Gradle from`: `gradle-wrapper.properties`
- `Gradle JVM`: JDK 21

터미널에서는 Gradle이 설치되어 있다면 아래 명령도 사용할 수 있다.

```bash
./gradlew runKataTests
```

Gradle이나 Kotlin compiler가 로컬에 없다면 IntelliJ의 Gradle import를 먼저 사용한다.

현재 테스트 파일은 의도적으로 아주 작게 시작한다. 프로덕션 코드를 바꾸기 전에 테스트를 먼저 확장한다.

## 추천 진행 순서

1. 세 가지 기존 동작에 대한 characterization test를 추가한다.
2. 테스트가 실패하거나 비어 있는 상태에서는 동작을 바꾸지 않는다.
3. public API를 유지하면서 로그인한 사용자 조회 부분에 seam을 만든다.
4. public API를 유지하면서 여행 목록 조회 부분에 seam을 만든다.
5. 친구 여부 확인 로직을 단순화한다.
6. 선택한 방식과 트레이드오프를 `../notes.md`에 기록한다.
