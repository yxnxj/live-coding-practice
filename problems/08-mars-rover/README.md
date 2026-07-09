# 08 Mars Rover

## Type

State / Command handling

## Source

- Common Mars Rover Kata variant

## Problem

화성 탐사 로버가 직사각형 격자 위에 있다. 로버는 현재 위치와 방향을 가지고, 명령 문자열을 순서대로 실행한다. 명령 실행이 끝나면 최종 위치와 방향을 반환해야 한다.

이 문제는 상태 변경, 명령 파싱, 방향 회전, 좌표 이동을 작게 나누는 연습이다.

## Input Model

- 격자 크기. 예: width 5, height 5.
- 초기 위치. 예: `(1, 2)`.
- 초기 방향. `N`, `E`, `S`, `W` 중 하나.
- 명령 문자열. 예: `LMLMLMLMM`.

## Commands

- `L`: 왼쪽으로 90도 회전한다.
- `R`: 오른쪽으로 90도 회전한다.
- `M`: 현재 방향으로 한 칸 전진한다.

방향별 이동:

```text
N -> y + 1
E -> x + 1
S -> y - 1
W -> x - 1
```

## Boundary Rule

기본 규칙에서는 로버가 격자 밖으로 나가는 명령을 만나면 오류로 처리한다.

확장 규칙으로 wrapping을 선택할 수 있다.

- `x < 0`이면 `width - 1`로 이동한다.
- `x >= width`이면 `0`으로 이동한다.
- `y < 0`이면 `height - 1`로 이동한다.
- `y >= height`이면 `0`으로 이동한다.

프로젝트에서는 기본 규칙과 확장 규칙 중 하나를 명확히 선택하고 테스트에 적는다.

## Obstacle Extension

장애물 좌표 목록이 주어질 수 있다.

- 로버가 다음 칸으로 이동하려는데 장애물이 있으면 이동하지 않는다.
- 명령 실행을 중단하고 현재 위치와 장애물 감지 상태를 반환한다.
- 회전 명령은 장애물의 영향을 받지 않는다.

## Example

입력:

```text
grid: 5x5
start: 1 2 N
commands: LMLMLMLMM
```

출력:

```text
1 3 N
```

입력:

```text
grid: 5x5
start: 3 3 E
commands: MMRMMRMRRM
```

출력:

```text
5 1 E
```

## Suggested Test Cases

- 왼쪽 회전 네 번.
- 오른쪽 회전 네 번.
- 각 방향으로 한 칸 이동.
- 여러 명령을 순서대로 실행.
- 격자 밖 이동 오류 또는 wrapping.
- 장애물 감지 확장.

## Done Criteria

- 방향 전환과 좌표 이동 규칙이 분리되어 있다.
- 명령 문자열 파싱 오류를 처리한다.
- 기본 규칙과 선택한 확장 규칙을 테스트로 설명한다.
