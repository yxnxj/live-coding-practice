# Availability Scheduler Kata

영업시간과 예약 목록을 바탕으로 가능한 시작 시간을 계산하는 연습이다.

## 문제 요약

이 문제는 원본 카타가 아니라 라이브 코딩 대비용 커스텀 문제다.

- 영업시간은 시작 시각과 종료 시각으로 주어진다.
- 기존 예약 목록이 주어진다.
- 요청 duration을 만족하는 가능한 시작 시각 목록을 반환한다.
- 예약 겹침, 딱 붙는 예약, 영업시간 경계값을 다룬다.

## 테스트 실행

```bash
./gradlew runKataTests
```

IntelliJ IDEA에서는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.
