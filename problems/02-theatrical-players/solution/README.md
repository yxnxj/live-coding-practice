# Theatrical Players Kata

긴 함수에서 계산 로직과 출력 로직을 분리하는 리팩터링 연습이다.

## 문제 요약

- 기존 plain text 청구서 출력을 테스트로 고정한다.
- HTML 청구서 출력 요구에 대응할 수 있게 구조를 개선한다.
- `tragedy`, `comedy` 외에 새로운 공연 타입이 추가될 수 있음을 고려한다.

## 테스트 실행

```bash
./gradlew runKataTests
```

IntelliJ IDEA에서는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.
