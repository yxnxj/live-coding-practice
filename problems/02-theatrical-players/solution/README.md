# 02 Theatrical Players

## Type

Refactoring

## Source

- https://github.com/emilybache/Theatrical-Players-Refactoring-Kata
- Based on the statement example from Martin Fowler's Refactoring, 2nd Edition.

## Problem

극단이 공연 청구서를 만든다. 고객은 여러 공연을 관람하고, 각 공연에는 관객 수와 공연 장르가 있다. 현재 코드는 청구 내역을 plain text 문자열로 만들어 반환한다.

기능은 이미 동작하지만 코드가 한 함수에 많이 몰려 있다. 금액 계산, 적립 포인트 계산, 출력 포맷 생성이 섞여 있어서 새로운 요구사항을 추가하기 어렵다. 목표는 출력 결과를 바꾸지 않고 리팩터링해서 다음 변경을 쉽게 수용하는 것이다.

## Domain

- 공연(`play`)은 이름과 타입을 가진다.
- 현재 지원 타입은 `tragedy`, `comedy`다.
- 청구서(`invoice`)는 고객명과 공연 목록을 가진다.
- 각 공연 목록 항목에는 공연 id와 관객 수가 들어 있다.
- 결과에는 공연별 청구 금액, 총액, 적립 포인트가 포함된다.

## Pricing Rules

### Tragedy

- 기본 금액은 40000이다.
- 관객 수가 30명을 초과하면 초과 인원 1명당 1000을 추가한다.

### Comedy

- 기본 금액은 30000이다.
- 관객 수가 20명을 초과하면 10000을 추가하고, 초과 인원 1명당 500을 추가한다.
- 관객 수와 관계없이 관객 1명당 300을 추가한다.

## Volume Credit Rules

- 모든 공연은 `max(audience - 30, 0)`만큼 기본 포인트를 적립한다.
- `comedy` 공연은 추가로 `floor(audience / 5)`만큼 포인트를 적립한다.

## Required Change Requests

리팩터링 후 다음 요구사항을 구현하기 쉬워야 한다.

- 기존 plain text 출력 외에 HTML 출력이 필요하다.
- `history`, `pastoral` 같은 새로운 공연 타입이 추가될 수 있다.
- 가격 정책과 포인트 정책이 타입별로 달라질 수 있다.

## Constraints

- 리팩터링 전후 plain text 출력은 같아야 한다.
- 첫 단계에서는 기능 추가보다 characterization test를 먼저 만든다.
- 계산 로직과 렌더링 로직을 분리한다.
- 과한 추상화보다 다음 변경을 수용할 수 있는 작은 구조를 우선한다.

## Suggested Test Cases

- tragedy만 포함된 청구서 출력.
- comedy만 포함된 청구서 출력.
- 여러 공연이 섞인 청구서 출력.
- 30명, 31명, 20명, 21명 경계값.
- 총액과 적립 포인트 계산.

## Done Criteria

- 기존 출력 결과가 테스트로 고정되어 있다.
- 금액 계산, 포인트 계산, 출력 생성 책임이 분리되어 있다.
- HTML 출력 또는 새 공연 타입 추가 시 변경 위치를 설명할 수 있다.

## IntelliJ / Run

IntelliJ에서 이 문제를 풀 때는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.

이 폴더 기준으로 테스트를 실행한다.

```bash
sh run-tests.sh
```

Gradle import가 실패하면 Gradle JVM을 JDK 21로 맞춘다.
