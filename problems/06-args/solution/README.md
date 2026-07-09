# Args Kata

스키마에 따라 커맨드라인 인자를 파싱하는 연습이다.

## 문제 요약

- flag는 한 글자이며 `-`로 시작한다.
- schema는 flag 이름과 값 타입을 정의한다.
- boolean, integer, string 값을 지원한다.
- 누락된 flag는 타입별 기본값을 반환한다.
- 잘못된 인자는 구체적인 에러 메시지로 설명한다.
- 확장 과제로 string list, integer list를 지원한다.

## 테스트 실행

```bash
./gradlew runKataTests
```

IntelliJ IDEA에서는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.
