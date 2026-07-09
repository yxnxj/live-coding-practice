# Bowling or Game of Life Kata

Bowling 점수 계산이나 Game of Life 상태 전이 중 하나를 선택해 푸는 연습이다.

현재 스타터는 Bowling 기준으로 시작한다.

## Bowling 문제 요약

- 유효한 roll sequence가 주어졌다고 가정하고 최종 점수를 계산한다.
- roll 유효성, frame 수 유효성, 중간 frame 점수는 검사하지 않는다.
- spare는 10점에 다음 roll 점수를 더한다.
- strike는 10점에 다음 두 roll 점수를 더한다.
- 10번째 frame의 spare/strike는 보너스 roll로 최종 frame 점수를 계산한다.

## Game of Life 문제 요약

- 유한한 2차원 grid에서 다음 세대를 계산한다.
- 살아있는 cell은 이웃이 2개 미만이면 죽고, 3개 초과면 죽는다.
- 살아있는 cell은 이웃이 2개 또는 3개면 살아남는다.
- 죽은 cell은 이웃이 정확히 3개면 살아난다.

## 테스트 실행

```bash
./gradlew runKataTests
```

IntelliJ IDEA에서는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.
