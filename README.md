# Live Coding Practice

라이브 코딩 테스트 대비 기록 저장소.

## Project Guideline

- 모든 문제 풀이는 Kotlin을 기본 언어로 작성한다.
- Java 스타터가 있는 카타도 이 저장소에서는 Kotlin 버전으로 옮겨서 푼다.
- 테스트 프레임워크가 준비되어 있지 않은 문제는 우선 표준 라이브러리 기반의 작은 테스트 러너로 시작한다.

목표는 문제를 많이 푸는 것보다, 낯선 코드와 요구사항을 만났을 때 다음 흐름을 안정적으로 반복하는 것이다.

1. 요구사항을 다시 말한다.
2. 현재 동작이나 핵심 규칙을 테스트로 고정한다.
3. 작은 단위로 구현하거나 리팩터링한다.
4. 판단의 이유와 대안을 말로 설명한다.
5. 시간이 부족할 때 남길 수 있는 안전한 상태를 만든다.

## Progress

| Date | Problem | Type | Time | Result | Retry |
|---|---|---|---:|---|---|
|  | 01 Trip Service | Legacy testing / Refactoring | 1h | done | 0 |
|  | 02 Theatrical Players | Refactoring |  |  |  |
|  | 03 Tennis | Branch cleanup / Domain rules |  |  |  |
|  | 04 Cart Discount | Domain logic |  |  |  |
|  | 05 Bank OCR | Parsing / Edge cases |  |  |  |
|  | 06 Args | Parser / TDD |  |  |  |
|  | 07 Availability Scheduler | Intervals / Date-time logic |  |  |  |
|  | 08 Mars Rover | State / Command handling |  |  |  |
|  | 09 Log Aggregator | Parsing / Aggregation |  |  |  |
|  | 10 Bowling or Game of Life | Rules / State transition |  |  |  |

## Two Week Routine

| Day | Focus | Problem |
|---:|---|---|
| 1 | Gilded Rose 회고, 설명 연습 | Already done |
| 2 | 테스트하기 어려운 레거시 코드 | 01 Trip Service |
| 3 | 긴 함수 분해와 출력 분리 | 02 Theatrical Players |
| 4 | 조건 분기 정리 | 03 Tennis |
| 5 | 실전 도메인 규칙 구현 | 04 Cart Discount |
| 6 | 문자열 파싱과 예외 케이스 | 05 Bank OCR |
| 7 | 45분 재풀이와 회고 | Week 1 review |
| 8 | 스키마 기반 파서 | 06 Args |
| 9 | 시간 구간 계산 | 07 Availability Scheduler |
| 10 | 명령 처리와 상태 모델링 | 08 Mars Rover |
| 11 | 로그 파싱과 집계 | 09 Log Aggregator |
| 12 | 규칙 계산 또는 상태 전이 | 10 Bowling or Game of Life |
| 13 | 모의 라이브 코딩 1회 | Random retry |
| 14 | 모의 라이브 코딩 1회, 말하기 훈련 | Random retry |

## Recording Rules

- 각 문제는 `problems/<number-name>/` 아래에 기록한다.
- 문제 설명은 `README.md`, 풀이 회고는 `notes.md`, 실제 코드는 `solution/`에 둔다.
- 매 풀이마다 실제 소요 시간, 첫 테스트까지 걸린 시간, 가장 좋은 결정, 가장 아쉬운 결정을 남긴다.
- 풀이가 끝난 뒤 면접관에게 설명할 2-3문장을 반드시 적는다.

## Commit Message Examples

```text
test: characterize trip service behavior
refactor: extract user session dependency
feat: add cart discount priority rule
test: cover invalid bank ocr account numbers
docs: record mars rover retry notes
```
