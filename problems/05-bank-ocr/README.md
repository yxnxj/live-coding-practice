# 05 Bank OCR

## Type

Parsing / Edge cases

## Practice Goal

- 고정 폭 문자열 입력을 숫자로 파싱한다.
- 잘못 읽힌 계좌번호와 checksum 실패를 구분한다.
- 입력 형식과 도메인 검증을 분리한다.

## Source

- https://codingdojo.org/kata/BankOCR/

## 원본 문제 요약

은행 지점에서 보내온 문서 스캔 결과를 읽어 계좌번호를 복원한다. 입력 파일에는 여러 entry가 있고, 각 entry는 4줄로 구성된다.

- 한 entry는 4줄이다.
- 각 줄은 27자다.
- 앞의 3줄은 pipe(`|`)와 underscore(`_`)로 표현된 9자리 계좌번호다.
- 4번째 줄은 빈 줄이다.
- 각 숫자는 3x3 문자 셀로 표현된다.

### User Story 1

스캔된 3x3 숫자들을 실제 9자리 계좌번호 문자열로 파싱한다.

### User Story 2

계좌번호 checksum을 검증한다.

```text
(d1 + 2*d2 + 3*d3 + ... + 9*d9) mod 11 = 0
```

여기서 `d1`은 가장 오른쪽 자리, `d9`는 가장 왼쪽 자리다.

### User Story 3

파싱 결과를 한 줄에 하나씩 출력한다.

- 읽을 수 없는 숫자는 `?`로 표시한다.
- checksum이 틀리면 `ERR`를 붙인다.
- 읽을 수 없는 숫자가 있으면 `ILL`을 붙인다.

### User Story 4

`ERR` 또는 `ILL`인 계좌번호에 대해 pipe/underscore 하나가 빠졌거나 잘못 추가된 경우를 고려해 가능한 올바른 번호를 추론한다.

- 유효한 후보가 하나면 그 번호를 사용한다.
- 후보가 여러 개면 `AMB`로 표시한다.
- 여전히 알 수 없으면 `ILL`로 표시한다.

## Done Criteria

- 정상 계좌번호, 읽을 수 없는 숫자, checksum 실패 테스트가 있다.
- 파싱 단계와 검증 단계가 분리되어 있다.
- 애매한 후보(`AMB`)와 보정 불가(`ILL`)를 구분할 수 있다.
