# Log Aggregator Kata

로그 라인을 파싱하고 사용자별 행동 수를 집계하는 연습이다.

## 문제 요약

이 문제는 원본 카타가 아니라 라이브 코딩 대비용 커스텀 문제다.

- 로그 라인은 `timestamp,userId,action` 형식이다.
- 잘못된 라인을 처리하는 정책을 정한다.
- 특정 기간 필터링 후 사용자별 action count를 반환한다.
- 파싱, 필터링, 집계 책임을 분리한다.

## 테스트 실행

```bash
./gradlew runKataTests
```

IntelliJ IDEA에서는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.
