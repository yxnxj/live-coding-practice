# 10 Bowling or Game of Life

## Type

Rules / State transition

## Source

- https://codingdojo.org/kata/Bowling/
- https://codingdojo.org/kata/GameOfLife/

## Choose One

이 날은 둘 중 하나를 선택해서 푼다.

- Bowling: 점수 규칙을 TDD로 점진 구현한다.
- Game of Life: 현재 grid에서 다음 세대를 계산한다.

시간이 부족하면 Bowling을 추천한다. 상태 전이와 자료구조 설계까지 연습하고 싶으면 Game of Life를 선택한다.

## Bowling Problem

American Ten-Pin Bowling 한 게임의 최종 점수를 계산한다. 입력은 이미 유효한 roll sequence라고 가정한다.

### Bowling Scope

이 카타에서는 다음을 하지 않는다.

- roll 값이 유효한지 검사하지 않는다.
- roll 수가 유효한지 검사하지 않는다.
- 중간 frame별 점수를 출력하지 않는다.
- 플레이어 여러 명을 처리하지 않는다.

### Bowling Rules

- 한 게임은 10 frame이다.
- 각 frame에서 최대 두 번 공을 던진다.
- 한 frame의 기본 점수는 그 frame에서 쓰러뜨린 핀 수의 합이다.
- 두 번 안에 10핀을 모두 쓰러뜨리면 spare다.
- 첫 번째 roll에서 10핀을 모두 쓰러뜨리면 strike다.
- spare 점수는 10점에 다음 roll 점수를 더한다.
- strike 점수는 10점에 다음 두 roll 점수를 더한다.
- 10번째 frame에서 spare나 strike가 나오면 보너스 roll을 던진다.
- 최종 점수는 10개 frame 점수의 합이다.

### Bowling Examples

Gutter game:

```text
rolls: 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
score: 0
```

All ones:

```text
rolls: 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
score: 20
```

One spare:

```text
rolls: 5 5 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
score: 16
```

One strike:

```text
rolls: 10 3 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
score: 24
```

Perfect game:

```text
rolls: 10 10 10 10 10 10 10 10 10 10 10 10
score: 300
```

## Game of Life Problem

Conway's Game of Life의 다음 세대를 계산한다. 무한 격자 또는 충분히 큰 유한 격자를 사용할 수 있다. 이 저장소에서는 구현을 단순하게 하기 위해 살아있는 cell 좌표 집합을 입력으로 받는 방식을 권장한다.

### Cell Rules

각 cell은 alive 또는 dead 상태다. 다음 세대는 동시에 계산된다.

- 살아있는 cell의 살아있는 이웃이 2개 미만이면 죽는다.
- 살아있는 cell의 살아있는 이웃이 2개 또는 3개면 살아남는다.
- 살아있는 cell의 살아있는 이웃이 3개 초과면 죽는다.
- 죽은 cell의 살아있는 이웃이 정확히 3개면 살아난다.

이웃은 가로, 세로, 대각선을 포함한 8칸이다.

### Game of Life Examples

Block은 그대로 유지된다.

```text
OO
OO
```

Blinker는 방향이 바뀐다.

```text
...
OOO
...
```

다음 세대:

```text
.O.
.O.
.O.
```

## Suggested Test Cases

Bowling:

- gutter game.
- 모든 roll이 1점.
- spare 보너스.
- strike 보너스.
- perfect game.

Game of Life:

- 외로움으로 죽는 cell.
- 과밀로 죽는 cell.
- 2개 또는 3개 이웃으로 생존.
- 정확히 3개 이웃으로 탄생.
- block still life.
- blinker oscillator.

## Done Criteria

- 선택한 문제의 핵심 규칙이 테스트로 표현되어 있다.
- 계산 로직과 입력 표현이 분리되어 있다.
- 시간 제한 안에서 끝낸 범위와 남은 확장을 설명할 수 있다.

## IntelliJ / Run

IntelliJ에서 이 문제를 풀 때는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.

이 폴더 기준으로 테스트를 실행한다.

```bash
sh run-tests.sh
```

Gradle import가 실패하면 Gradle JVM을 JDK 21로 맞춘다.
