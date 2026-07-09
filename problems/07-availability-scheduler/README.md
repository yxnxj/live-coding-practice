# 07 Availability Scheduler

## Type

Intervals / Date-time logic

## Source

- Custom practice problem
- 유명 카타 원본이 아니라 예약/시간 구간 계산 라이브 코딩을 대비해 만든 연습 문제다.

## Practice Goal

- 영업시간과 기존 예약을 기준으로 가능한 시작 시간을 계산한다.
- 예약 시간 겹침과 경계값을 안정적으로 처리한다.
- 점심시간, 휴무일 같은 제약 추가에 대비한다.

## Suggested Requirements

- 영업시간은 시작 시각과 종료 시각으로 주어진다.
- 기존 예약은 시작 시각과 종료 시각으로 주어진다.
- 요청 duration을 만족하는 가능한 시작 시각 목록을 반환한다.
- 결과는 시간 오름차순으로 정렬한다.

## Done Criteria

- 겹치는 예약, 딱 붙은 예약, 영업시간 경계 테스트가 있다.
- interval overlap 규칙이 이름 있는 함수로 드러난다.
