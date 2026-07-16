# D2 Java — 상품별 판매 통계 정렬

- 권장 제한시간: 35분
- 풀이 파일: `D2Solution.java`
- 목표 시간복잡도: `O(n + k log k)`
  - `n`: 전체 판매 기록 수
  - `k`: 서로 다른 상품명 수

## 문제

상품 판매 기록 목록 `sales`가 주어진다. 유효한 판매 기록만 상품별로 집계하고, 정해진 우선순위에 따라 정렬하는 `summarizeSales` 메서드를 구현하라.

```java
public static List<ProductSummary> summarizeSales(List<Sale> sales) {
    // 구현
}
```

## 입력 모델

```java
class Sale {
    String productName;
    int quantity;
    long unitPrice;
    String status;
}
```

각 판매 기록의 매출액은 다음과 같다.

```text
quantity * unitPrice
```

곱셈 결과는 `long`으로 계산한다.

## 출력 모델

```java
class ProductSummary {
    String productName;
    int totalQuantity;
    long totalRevenue;
    int saleCount;
}
```

## 유효한 판매 기록

다음 조건을 모두 만족하는 기록만 집계한다.

1. `Sale` 객체가 `null`이 아니다.
2. `status`가 정확히 `COMPLETED` 또는 `SHIPPED`다.
3. `quantity`가 0보다 크다.
4. `unitPrice`가 0보다 크다.
5. `productName`이 `null`이 아니다.
6. `productName.trim()`의 결과가 빈 문자열이 아니다.

## 집계 규칙

1. `productName` 앞뒤의 공백을 제거한 값을 상품명으로 사용한다.
2. 공백을 제거한 상품명이 같으면 같은 상품으로 집계한다.
3. 상품별로 다음 값을 계산한다.
   - `totalQuantity`: 유효한 판매 수량의 합
   - `totalRevenue`: 모든 `quantity * unitPrice`의 합
   - `saleCount`: 유효한 판매 기록의 개수
4. 대소문자가 다르면 서로 다른 상품으로 취급한다.
   - `Tire`와 `tire`는 다른 상품이다.

## 정렬 규칙

결과를 다음 우선순위로 정렬한다.

1. `totalRevenue` 내림차순
2. 매출액이 같으면 `totalQuantity` 내림차순
3. 매출액과 수량이 모두 같으면 `productName` 오름차순

## 그 밖의 조건

1. `sales`가 `null`이거나 비어 있으면 빈 목록을 반환한다.
2. 원본 `sales` 목록과 원본 `Sale` 객체를 변경하지 않는다.
3. 결과에 유효한 판매 기록이 없는 상품은 포함하지 않는다.

## 예제 입력

```java
List<Sale> sales = List.of(
    new Sale("Bumper", 2, 500, "COMPLETED"),
    new Sale("Door", 1, 1500, "SHIPPED"),
    new Sale(" Bumper ", 1, 700, "SHIPPED"),
    new Sale("Tire", 4, 400, "COMPLETED"),
    new Sale("Window", 2, 750, "COMPLETED"),
    new Sale("Lamp", 1, 1500, "COMPLETED"),
    new Sale("Mirror", 10, 1000, "CANCELED"),
    new Sale("Tire", 0, 9999, "COMPLETED"),
    new Sale("  ", 5, 1000, "SHIPPED")
);
```

## 기대 결과

```text
Bumper 3 1700 2
Tire   4 1600 1
Window 2 1500 1
Door   1 1500 1
Lamp   1 1500 1
```

각 줄의 값은 다음 순서다.

```text
productName totalQuantity totalRevenue saleCount
```

정렬 이유:

- `Bumper`의 총매출은 `2 * 500 + 1 * 700 = 1700`이다.
- `Window`, `Door`, `Lamp`의 총매출은 모두 `1500`이다.
- `Window`의 총수량이 `2`이므로 총수량이 `1`인 두 상품보다 먼저 온다.
- `Door`와 `Lamp`는 총매출과 총수량이 같으므로 상품명 오름차순으로 정렬한다.

## 추가 테스트

다음 상황을 직접 테스트한다.

- `sales == null`
- 빈 목록
- 모든 판매 기록이 무효
- 같은 상품이 여러 번 등장
- 총매출이 같은 두 상품
- 총매출과 총수량이 모두 같은 두 상품
- 상품명 앞뒤에 공백이 있는 경우

## 풀이 후 기록

- 사용 시간:
- 시간복잡도:
- 공간복잡도:
- 사용한 자료구조와 선택 이유:
- 가장 오래 고민한 부분:
