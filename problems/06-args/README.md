# 06 Args

## Type

Parser / TDD

## Practice Goal

- 커맨드라인 인자를 스키마에 따라 파싱한다.
- boolean, string, number 옵션을 지원한다.
- 잘못된 플래그와 누락된 값을 명확히 처리한다.

## Source

- https://codingdojo.org/kata/Args/

## 원본 문제 요약

커맨드라인 인자는 flag와 value로 구성된다.

- flag는 한 글자이며 `-`로 시작한다.
- 각 flag는 값이 없거나 하나의 값을 가진다.
- parser는 먼저 schema를 입력받는다.
- schema는 프로그램이 기대하는 flag 이름, 값 개수, 값 타입을 정의한다.
- 실제 인자 목록을 schema와 비교해 검증한다.
- 프로그램은 flag 이름으로 값을 조회할 수 있고, 값은 schema에 맞는 타입으로 반환된다.

예시:

```text
-l -p 8080 -d /usr/logs
```

- `l`: boolean flag. 있으면 `true`, 없으면 `false`
- `p`: integer flag. 예시 값은 `8080`
- `d`: string flag. 예시 값은 `/usr/logs`

schema에 있는 flag가 인자에 없으면 기본값을 반환한다.

- boolean: `false`
- number: `0`
- string: `""`

인자가 schema와 맞지 않으면 무엇이 잘못됐는지 설명하는 좋은 에러 메시지를 제공한다.

확장 과제로 string list, integer list를 지원할 수 있다.

```text
-g this,is,a,list -d 1,2,-3,5
```

## Done Criteria

- 타입별 옵션 테스트가 있다.
- 에러 케이스가 테스트로 표현되어 있다.
- 스키마와 파싱 책임이 분리되어 있다.
- 음수 정수, 인자 순서 변경, 누락된 flag 기본값을 테스트한다.
