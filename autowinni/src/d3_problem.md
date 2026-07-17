# D3 Java — 가장 많이 주문된 상품 찾기

- 권장 제한시간: 30분
- 풀이 파일: `D3Solution.java`
- 목표 시간복잡도: 평균 `O(n + k)`
- 목표 공간복잡도: `O(k)`
  - `n`: 전체 주문 항목 수
  - `k`: 서로 다른 상품명 수

## 메서드 형태

```java
public static String findMostOrderedProduct(List<OrderItem> items) {
    // 구현
}
```

## 문제

주문 항목 목록 `items`가 주어진다. 유효한 주문 항목의 수량을 상품별로 합산하고, 총 주문 수량이 가장 많은 상품명을 반환하라.

## 입력 모델

```java
class OrderItem {
    String productName;
    int quantity;
    String status;
}
```

## 유효한 주문 항목

다음 조건을 모두 만족하는 항목만 집계한다.

1. `OrderItem` 객체가 `null`이 아니다.
2. `status`가 정확히 `COMPLETED` 또는 `SHIPPED`다.
3. `quantity`가 0보다 크다.
4. `productName`이 `null`이 아니다.
5. `productName.trim()`의 결과가 빈 문자열이 아니다.

## 집계 규칙

1. `productName` 앞뒤 공백을 제거한 값을 상품명으로 사용한다.
2. 공백을 제거한 상품명이 같으면 같은 상품으로 집계한다.
3. 대소문자가 다르면 다른 상품으로 취급한다.
   - `Tire`와 `tire`는 서로 다른 상품이다.
4. 상품별로 모든 유효한 `quantity`를 합산한다.

## 반환 규칙

1. 총 주문 수량이 가장 큰 상품명을 반환한다.
2. 총 주문 수량이 같은 상품이 여러 개면 상품명 오름차순으로 가장 앞선 상품을 반환한다.
3. `items`가 `null`이거나 비어 있으면 빈 문자열 `""`을 반환한다.
4. 유효한 주문 항목이 하나도 없으면 빈 문자열 `""`을 반환한다.
5. 원본 목록과 원본 `OrderItem` 객체를 변경하지 않는다.

## 예제 입력

```java
List<OrderItem> items = List.of(
    new OrderItem("Tire", 2, "COMPLETED"),
    new OrderItem("Door", 3, "SHIPPED"),
    new OrderItem(" Tire ", 2, "SHIPPED"),
    new OrderItem("Bumper", 4, "COMPLETED"),
    new OrderItem("Mirror", 10, "CANCELED"),
    new OrderItem("Door", 0, "COMPLETED"),
    new OrderItem("  ", 100, "SHIPPED")
);
```

상품별 유효 주문 수량:

```text
Tire:   4
Door:   3
Bumper: 4
```

`Tire`와 `Bumper`의 총수량이 같으므로 상품명 오름차순으로 앞선 값을 반환한다.

## 기대 결과

```java
"Bumper"
```

## 추가 테스트

- `items == null`
- 빈 목록
- 모든 항목이 무효
- 같은 상품이 여러 번 등장
- 상품명 앞뒤에 공백이 있는 항목
- 최댓값이 하나인 경우
- 최댓값이 여러 개인 경우
- 목록 안에 `null` 항목이 있는 경우

## 풀이 후 기록

- 사용 시간:
- 시간복잡도:
- 공간복잡도:
- 사용한 자료구조와 선택 이유:
- 동률을 처리한 방법:
