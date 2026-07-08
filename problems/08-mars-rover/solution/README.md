# Mars Rover Kata

명령 처리, 방향 전환, 좌표 이동을 모델링하는 연습이다.

## 문제 요약

이 문제는 대표적인 Mars Rover 요구사항을 따르는 연습 문제다.

- 로버는 `(x, y)` 위치와 `N`, `E`, `S`, `W` 방향을 가진다.
- `L`은 왼쪽으로 90도 회전한다.
- `R`은 오른쪽으로 90도 회전한다.
- `M`은 현재 방향으로 한 칸 이동한다.
- 확장 요구로 격자 경계, wrapping, 장애물 감지를 추가할 수 있다.

## 테스트 실행

```bash
./gradlew runKataTests
```

IntelliJ IDEA에서는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.
