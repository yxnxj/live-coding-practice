# D6 Java — 예약 시간 정리

- 제한시간: 30분
- 풀이 파일: `D6Solution.java`

## 메서드 형태

```java
public static List<Reservation> organizeReservations(
    List<Reservation> reservations
) {
    // 구현
}
```

## 입력 모델

```java
class Reservation {
    int start;
    int end;
}
```

`start`와 `end`는 같은 날의 자정부터 지난 분을 의미한다.

```text
09:00 → 540
10:30 → 630
```

## 문제

순서가 정해지지 않은 예약 시간 목록 `reservations`가 주어진다. 서로 겹치거나 경계가 맞닿은 예약들을 하나의 예약으로 합치고, 시작 시간이 빠른 순서로 정리한 새 목록을 반환하라.

## 입력 조건

정상 입력의 모든 예약은 다음 조건을 만족한다.

1. `0 <= start < end <= 1440`이다.
2. 예약 객체는 `null`이 아니다.
3. 목록의 길이는 최대 `100,000`이다.
4. 입력 목록은 시작 시간 순서로 정렬되어 있지 않을 수 있다.
5. `reservations`는 `null`이 아닌 수정 가능한 `List`이며, 비어 있을 수 있다.

개별 예약의 유효성은 검사하지 않아도 된다.

## 결합 규칙

두 예약의 시간이 겹치거나 경계가 맞닿으면 하나로 합친다.

```text
[540, 600]과 [570, 630] → [540, 630]
[660, 720]과 [720, 750] → [660, 750]
```

한 예약이 다른 예약 안에 완전히 포함되는 경우도 하나로 합친다.

```text
[100, 300]과 [150, 200] → [100, 300]
```

서로 떨어져 있으면 별도의 예약으로 유지한다.

```text
[100, 200]과 [201, 300] → 별도 유지
```

## 반환 규칙

1. 정리된 예약들을 시작 시간 오름차순으로 담은 새 `List`를 반환한다.
2. 입력 목록의 순서는 변경해도 된다.
3. `reservations`가 비어 있으면 빈 목록을 반환한다.

## 예제

```java
List<Reservation> reservations = new ArrayList<>(List.of(
    new Reservation(660, 720),
    new Reservation(540, 600),
    new Reservation(720, 750),
    new Reservation(480, 510),
    new Reservation(570, 630)
));
```

기대 결과:

```java
List.of(
    new Reservation(480, 510),
    new Reservation(540, 630),
    new Reservation(660, 750)
)
```

## 추가로 확인할 조건

- 입력이 이미 정리되어 있는 경우
- 입력 순서가 뒤섞인 경우
- 모든 예약이 하나로 합쳐지는 경우
- 다른 예약 안에 완전히 포함된 경우
- 경계가 맞닿은 경우
- 서로 겹치지 않는 경우
- 예약이 하나뿐인 경우
- 빈 목록

## 풀이 후 기록

- 사용 시간:
- 처음 떠올린 방법:
- 최종적으로 선택한 방법과 이유:
- 시간복잡도:
- 공간복잡도:
