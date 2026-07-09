# 09 Log Aggregator

## Type

Parsing / Aggregation

## Source

- Custom practice problem

## Problem

서비스 로그 라인을 파싱해서 사용자별 행동 수를 집계한다. 로그는 CSV에 가까운 단순 형식이지만, 일부 라인은 깨져 있을 수 있다. 프로그램은 기간 필터를 적용하고, 정상 라인만 집계하며, 잘못된 라인을 어떻게 처리했는지 알 수 있게 해야 한다.

목표는 파싱, 검증, 필터링, 집계 책임을 분리하는 것이다.

## Input Format

각 로그 라인은 다음 형식이다.

```text
timestamp,userId,action
```

예시:

```text
2026-07-01T10:00:00Z,u1,login
2026-07-01T10:05:00Z,u1,view_product
2026-07-01T10:07:00Z,u2,login
```

## Rules

- `timestamp`는 ISO-8601 UTC 문자열이다.
- `userId`는 비어 있으면 안 된다.
- `action`은 비어 있으면 안 된다.
- 기간 필터는 시작 시각 이상, 종료 시각 미만으로 적용한다.
- 잘못된 라인은 집계에서 제외한다.
- 결과는 사용자별 action count map이다.

## Output Model

결과는 다음 정보를 포함한다.

- 사용자별 action count.
- skip된 라인 목록 또는 skip 개수.
- skip 사유. 예: 필드 개수 오류, timestamp 파싱 실패, 빈 userId, 빈 action.

예시 출력 모델:

```text
counts:
  u1:
    login: 1
    view_product: 1
  u2:
    login: 1
errors:
  - line 4: invalid timestamp
```

## Sorting

기본 반환 타입이 map이면 정렬은 필수는 아니다. 문자열 출력까지 구현한다면 다음 순서를 따른다.

- userId 오름차순
- action 이름 오름차순
- error는 입력 라인 순서 유지

## Extension Rules

기본 구현 후 다음 요구사항을 추가할 수 있다.

- 특정 action만 필터링한다.
- bot userId 목록은 제외한다.
- 시간대별 count를 추가한다.
- 같은 userId의 연속 중복 action은 하나로 본다.

## Suggested Test Cases

- 정상 라인 하나.
- 같은 사용자의 여러 action.
- 여러 사용자 집계.
- 기간 시작 경계 포함.
- 기간 종료 경계 제외.
- 잘못된 timestamp.
- 필드 개수가 부족하거나 많은 라인.
- 빈 userId 또는 빈 action.

## Done Criteria

- 파싱 결과와 집계 결과가 분리되어 있다.
- 잘못된 라인을 조용히 무시하지 않고 결과에 남긴다.
- 기간 경계 테스트가 있다.

## IntelliJ / Run

IntelliJ에서 이 문제를 풀 때는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.

이 폴더 기준으로 테스트를 실행한다.

```bash
sh run-tests.sh
```

Gradle import가 실패하면 Gradle JVM을 JDK 21로 맞춘다.
