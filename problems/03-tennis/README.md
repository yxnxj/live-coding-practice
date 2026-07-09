# 03 Tennis

## Type

Branch cleanup / Domain rules

## Source

- https://codingdojo.org/kata/Tennis/

## Problem

테니스 한 게임의 점수를 문자열로 표현하는 프로그램을 만든다. 두 플레이어가 있고, 각 플레이어가 포인트를 얻을 때마다 현재 점수 이름을 반환해야 한다.

세트나 매치 전체가 아니라 "한 게임"만 다룬다. 타이브레이크, 서브권, 실격 같은 규칙은 다루지 않는다.

## Score Names

일반 점수 이름은 다음 순서를 따른다.

```text
0 -> Love
1 -> Fifteen
2 -> Thirty
3 -> Forty
```

두 플레이어가 같은 점수이고 점수가 3점 미만이면 `Love-All`, `Fifteen-All`, `Thirty-All`처럼 표현한다.

두 플레이어가 모두 3점 이상이고 점수가 같으면 `Deuce`다.

## Winning Rules

- 한 플레이어가 최소 4점을 얻고, 상대보다 2점 이상 앞서면 그 플레이어가 승리한다.
- 승리 상태는 `Win for player1`, `Win for player2`처럼 표현한다.
- 두 플레이어가 모두 3점 이상이고 점수 차이가 1점이면 앞선 플레이어가 advantage를 가진다.
- advantage 상태는 `Advantage player1`, `Advantage player2`처럼 표현한다.
- advantage가 없는 플레이어가 다음 포인트를 얻으면 다시 `Deuce`가 된다.

## Normal Score Formatting

두 플레이어가 모두 3점 이하이고 동점이 아니면 `Player1Score-Player2Score` 형태로 표현한다.

예시:

```text
0:0 -> Love-All
1:0 -> Fifteen-Love
2:1 -> Thirty-Fifteen
3:2 -> Forty-Thirty
3:3 -> Deuce
4:3 -> Advantage player1
4:4 -> Deuce
5:3 -> Win for player1
```

## Constraints

- 구현은 점수 규칙을 읽기 좋게 드러내야 한다.
- 긴 조건문을 그대로 두기보다 규칙에 이름을 붙인다.
- 작은 문제이므로 불필요한 클래스 계층을 만들지 않는다.

## Suggested Test Cases

- 모든 동점 상태.
- 일반 점수 조합.
- Deuce 진입.
- player1/player2 advantage.
- player1/player2 win.
- advantage 후 다시 deuce가 되는 경우.

## Done Criteria

- 듀스, 어드밴티지, 승리 조건 테스트가 있다.
- 점수 표현 규칙이 코드에서 명확하게 드러난다.
- 조건문이 많은 초기 구현을 더 설명력 있는 구조로 정리했다.
