# 02 Theatrical Players

## Type

Refactoring

## Practice Goal

- 긴 함수를 작은 계산 단계로 분리한다.
- 계산 로직과 출력 로직을 나눈다.
- 새로운 출력 형식이나 공연 타입 추가에 대응한다.

## Source

- https://github.com/emilybache/Theatrical-Players-Refactoring-Kata

## 원본 문제 요약

Martin Fowler의 `Refactoring` 2판 1장에 나오는 예제를 기반으로 한다. 시작 코드는 공연 청구서를 텍스트로 출력하는 기능을 가지고 있고, 리팩터링은 보통 다음 변경 요구를 더 쉽게 수용하기 위해 진행한다.

- 기존 plain text 청구서 출력 외에 HTML 청구서 출력이 필요하다.
- 극단은 `tragedy`, `comedy` 외에도 `history`, `pastoral` 같은 새로운 공연 타입을 추가하려 한다.
- 리팩터링 전에는 기존 출력 결과를 테스트로 고정해야 한다.
- 원본 저장소는 approval testing 방식으로 기존 출력 변경을 감지하는 접근을 제안한다.

## Done Criteria

- 기존 출력 결과가 테스트로 고정되어 있다.
- 계산과 렌더링의 책임이 분리되어 있다.
- HTML 출력 또는 새로운 공연 타입 추가 요구에 대응할 수 있다.
- 과한 추상화를 하지 않은 이유를 설명할 수 있다.
