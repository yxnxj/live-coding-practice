# Java 문제 — 고객별 유효 주문 집계

- 제한시간: 35분
- 구현 대상: `Main.summarizeOrders(List<Order> orders)`

## 요구사항

1. `status`가 `PAID` 또는 `SHIPPED`인 주문만 집계한다.
2. `amount`가 0 이하인 주문은 제외한다.
3. `customerName`이 `null`이거나 공백뿐인 주문은 제외한다.
4. 동일한 `orderId`가 여러 번 나오면 최초 주문 한 건만 인정한다.
5. 고객별 유효 주문의 총금액과 주문 개수를 계산한다.
6. 총금액 내림차순으로 정렬한다.
7. 총금액이 같으면 고객명 오름차순으로 정렬한다.
8. 입력이 `null`이거나 비어 있으면 빈 목록을 반환한다.
9. 원본 입력 목록을 변경하지 않는다.

## 메서드 형태

```java
public static List<CustomerSummary> summarizeOrders(List<Order> orders) {
    // 구현
}
```

## 예제 입력

```java
List<Order> orders = List.of(
    new Order("O-100", "Kim", 3000, "PAID"),
    new Order("O-101", "Lee", 5000, "SHIPPED"),
    new Order("O-102", "Kim", 2000, "CANCELED"),
    new Order("O-103", "Park", 5000, "PAID"),
    new Order("O-100", "Kim", 9000, "PAID"),
    new Order("O-104", "Kim", 2000, "SHIPPED"),
    new Order("O-105", "  ", 10000, "PAID"),
    new Order("O-106", "Lee", -1000, "PAID")
);
```

## 기대 결과

```text
Kim  5000  2
Lee  5000  1
Park 5000  1
```

세 고객의 총금액이 같으므로 고객명 오름차순으로 정렬한다.

## 완료 후 기록

- 시간복잡도:
- 공간복잡도:
- 사용한 자료구조와 선택 이유:
- 직접 테스트한 엣지케이스:
