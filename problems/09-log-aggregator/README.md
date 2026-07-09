# 09 Log Aggregator

## Type

Parsing / Aggregation

## Source

- Custom practice problem
- 유명 카타 원본이 아니라 로그 파싱/집계 라이브 코딩을 대비해 만든 연습 문제다.

## Practice Goal

- 로그 라인을 파싱해 사용자별 행동 수를 집계한다.
- 잘못된 라인을 처리하는 정책을 명확히 한다.
- 필터링과 집계 책임을 분리한다.

## Suggested Requirements

- 로그 라인은 `timestamp,userId,action` 형식이다.
- 특정 기간에 해당하는 로그만 집계한다.
- 사용자별 action count를 반환한다.
- 잘못된 라인은 skip하거나 오류 목록에 포함한다.

## Done Criteria

- 정상 라인, 잘못된 라인, 기간 경계 테스트가 있다.
- 파싱 결과와 집계 결과가 분리되어 있다.
