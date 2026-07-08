# Bank OCR Kata

고정 폭 ASCII 숫자를 계좌번호로 파싱하고 검증하는 연습이다.

## 문제 요약

- 입력 entry는 4줄이고, 각 줄은 27자다.
- 앞의 3줄은 3x3 문자 셀 9개로 구성된 계좌번호다.
- 4번째 줄은 빈 줄이다.
- 계좌번호 checksum을 검증한다.
- 읽을 수 없는 숫자는 `?`, checksum 실패는 `ERR`, 읽을 수 없는 계좌번호는 `ILL`로 표시한다.
- pipe/underscore 하나 차이로 고칠 수 있는 후보를 찾아 `AMB` 여부를 판단한다.

## 테스트 실행

```bash
./gradlew runKataTests
```

IntelliJ IDEA에서는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.
