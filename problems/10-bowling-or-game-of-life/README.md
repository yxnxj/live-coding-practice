# 10 Bowling or Game of Life

## Type

Rules / State transition

## Practice Goal

- Bowling: 점수 규칙을 TDD로 점진 구현한다.
- Game of Life: 현재 상태에서 다음 상태를 계산한다.

## Source

- https://codingdojo.org/kata/Bowling/
- https://codingdojo.org/kata/GameOfLife/

## Bowling 원본 문제 요약

American Ten-Pin Bowling 한 게임의 유효한 roll sequence가 주어졌을 때 최종 점수를 계산한다.

이 카타에서는 문제를 가볍게 유지하기 위해 다음은 하지 않는다.

- roll 값이 유효한지 검사하지 않는다.
- roll 수와 frame 수가 맞는지 검사하지 않는다.
- 중간 frame 점수는 제공하지 않는다.

핵심 규칙:

- 한 게임은 10 frame이다.
- 각 frame에서 최대 두 번 공을 던진다.
- 두 번 안에 모든 핀을 쓰러뜨리지 못하면 그 frame 점수는 두 roll의 합이다.
- spare는 10점에 다음 roll 점수를 더한다.
- strike는 10점에 다음 두 roll 점수를 더한다.
- 10번째 frame에서 spare나 strike가 나오면 보너스 roll을 던진다.
- 게임 점수는 모든 frame 점수의 합이다.

대표 테스트:

- 모든 roll이 strike인 perfect game은 300점이다.
- 매 frame `9-`이면 90점이다.
- 매 frame `5/`이고 마지막 보너스가 `5`이면 150점이다.

## Game of Life 원본 문제 요약

임의의 시작 grid가 주어졌을 때 Conway's Game of Life의 다음 세대를 계산한다.

- grid는 유한하며, grid 밖에는 생명이 존재하지 않는다.
- 살아있는 cell의 이웃이 2개 미만이면 죽는다.
- 살아있는 cell의 이웃이 3개 초과면 죽는다.
- 살아있는 cell의 이웃이 2개 또는 3개면 살아남는다.
- 죽은 cell의 이웃이 정확히 3개면 살아난다.

## Done Criteria

- 핵심 규칙이 테스트로 표현되어 있다.
- 경계값과 예외 케이스를 설명할 수 있다.
- 시간 제한 안에서 완료 가능한 범위를 정했다.
