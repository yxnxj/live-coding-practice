# Tennis Kata

테니스 점수 규칙의 조건 분기를 읽기 좋은 코드로 정리하는 연습이다.

## 문제 요약

- 기본 점수는 `love`, `15`, `30`, `40`이다.
- 양쪽이 40이면 `deuce`다.
- `deuce`에서 한쪽이 이기면 `advantage`다.
- advantage를 가진 플레이어가 다시 이기면 게임에서 승리한다.
- 최소 4포인트 이상, 상대보다 2포인트 이상 앞서면 게임에서 승리한다.

## 테스트 실행

```bash
./gradlew runKataTests
```

IntelliJ IDEA에서는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.
