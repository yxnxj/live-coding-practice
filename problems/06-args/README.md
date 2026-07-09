# 06 Args

## Type

Parser / TDD

## Source

- https://codingdojo.org/kata/Args/

## Problem

커맨드라인 프로그램은 문자열 인자 목록을 받는다. 인자는 flag와 value로 구성된다. 프로그램은 먼저 schema를 정의하고, 실제 인자 목록을 schema에 맞게 파싱해야 한다.

목표는 스키마 해석, 인자 파싱, 값 조회, 오류 처리를 분리해서 구현하는 것이다.

## Input Example

```text
-l -p 8080 -d /usr/logs
```

위 인자 목록은 다음 의미를 가진다.

- `-l`: boolean flag
- `-p 8080`: integer flag
- `-d /usr/logs`: string flag

## Schema

schema는 프로그램이 허용하는 flag 이름과 타입을 정의한다.

기본 타입은 다음과 같다.

- boolean
- integer
- string

예시 schema:

```text
l:boolean,p:integer,d:string
```

이 schema에서 `l`, `p`, `d`만 허용된다.

## Parsing Rules

- flag는 `-`로 시작하고 이름은 한 글자다.
- boolean flag는 값이 없다. flag가 존재하면 `true`, 없으면 `false`다.
- integer flag는 다음 token을 정수로 파싱한다.
- string flag는 다음 token을 문자열 값으로 사용한다.
- schema에 없는 flag가 나오면 오류다.
- 값이 필요한 flag 뒤에 값이 없으면 오류다.
- integer flag의 값이 정수가 아니면 오류다.
- flag 순서는 결과에 영향을 주지 않아야 한다.

## Default Values

schema에 정의되어 있지만 실제 인자 목록에 없는 flag는 기본값을 반환한다.

- boolean: `false`
- integer: `0`
- string: `""`

## Error Reporting

오류는 무엇이 잘못되었는지 알 수 있어야 한다.

구분하면 좋은 오류:

- 알 수 없는 flag
- 값 누락
- 잘못된 integer 값
- schema 자체가 잘못된 경우

## Extension

기본 기능이 끝나면 list 타입을 추가할 수 있다.

```text
-g this,is,a,list -d 1,2,-3,5
```

- string list: comma로 나눈 문자열 목록
- integer list: comma로 나눈 정수 목록

## Suggested Test Cases

- boolean flag가 있을 때와 없을 때.
- integer와 string 값 파싱.
- flag 순서가 바뀐 경우.
- 누락된 flag의 기본값.
- 음수 integer 값.
- 알 수 없는 flag.
- 값이 필요한 flag의 값 누락.
- integer 파싱 실패.

## Done Criteria

- 타입별 옵션 테스트가 있다.
- 스키마 파싱과 인자 파싱 책임이 분리되어 있다.
- 오류 메시지가 디버깅 가능한 수준으로 구체적이다.
