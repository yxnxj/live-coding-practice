# 07 Availability Scheduler

## Type

Intervals / Date-time logic

## Source

- Custom practice problem

## Problem

예약 가능한 시작 시각 목록을 계산한다. 하루의 영업시간, 기존 예약 목록, 요청된 예약 길이가 주어졌을 때, 새로운 예약을 시작할 수 있는 모든 시각을 반환해야 한다.

라이브 코딩에서 자주 나오는 interval overlap, 경계값, 정렬 문제를 연습하기 위한 문제다.

## Input Model

- `businessHours`: 영업 시작 시각과 종료 시각.
- `reservations`: 기존 예약 목록. 각 예약은 시작 시각과 종료 시각을 가진다.
- `durationMinutes`: 새 예약에 필요한 시간.
- `slotStepMinutes`: 가능한 시작 시각 간격. 예: 30분.

시각은 같은 날짜 안의 `HH:mm` 문자열 또는 분 단위 정수로 표현해도 된다.

## Rules

- 새 예약은 영업 시작 시각보다 빠르게 시작할 수 없다.
- 새 예약의 종료 시각은 영업 종료 시각보다 늦을 수 없다.
- 기존 예약과 시간이 겹치면 안 된다.
- 종료 시각과 시작 시각이 정확히 맞닿는 것은 겹침이 아니다.
  - 예: 기존 예약 `10:00-11:00`, 새 예약 `11:00-11:30`은 가능하다.
- 결과는 시간 오름차순으로 정렬한다.
- 같은 시작 시각은 한 번만 반환한다.

## Overlap Definition

두 구간 `[startA, endA)`, `[startB, endB)`가 겹치는 조건은 다음과 같다.

```text
startA < endB && startB < endA
```

끝점은 포함하지 않는 half-open interval로 생각한다.

## Example

영업시간:

```text
09:00-12:00
```

기존 예약:

```text
09:30-10:00
10:30-11:00
```

요청 길이:

```text
30 minutes
```

slot 간격:

```text
30 minutes
```

가능한 시작 시각:

```text
09:00
10:00
11:00
11:30
```

## Extension Rules

기본 구현 후 다음 요구사항을 추가할 수 있다.

- 점심시간은 예약할 수 없다.
- 특정 날짜는 휴무일이다.
- 예약 사이에 10분 buffer가 필요하다.
- 여러 명의 캘린더를 합쳐 공통 가능 시간을 찾는다.

## Suggested Test Cases

- 예약이 없는 날.
- 기존 예약과 정확히 맞닿는 경우.
- 기존 예약과 1분이라도 겹치는 경우.
- 영업 시작/종료 경계.
- duration이 영업시간보다 긴 경우.
- 예약 목록이 정렬되어 있지 않은 경우.

## Done Criteria

- interval overlap 규칙이 이름 있는 함수로 드러난다.
- 경계값 테스트가 있다.
- 시간 표현과 가능 시간 계산 책임이 분리되어 있다.
