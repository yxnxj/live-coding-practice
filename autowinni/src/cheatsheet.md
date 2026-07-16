# Java 코딩 테스트 직전 복습

## 여러 조건을 직접 비교하는 Comparator

여러 정렬 조건의 오름차순과 내림차순이 섞여 있을 때는 람다에서 조건을 순서대로 비교하면 동작을 이해하기 쉽다.

```java
results.sort((a, b) -> {
    if (a.totalRevenue != b.totalRevenue) {
        return Long.compare(b.totalRevenue, a.totalRevenue);
    }

    if (a.totalQuantity != b.totalQuantity) {
        return Integer.compare(b.totalQuantity, a.totalQuantity);
    }

    return a.productName.compareTo(b.productName);
});
```

위 Comparator의 정렬 순서:

1. `totalRevenue` 내림차순
2. 매출이 같으면 `totalQuantity` 내림차순
3. 매출과 수량이 같으면 `productName` 오름차순

## Comparator 반환값

```text
음수: a를 b보다 앞에 배치
0:   두 값을 같은 순서로 취급
양수: a를 b보다 뒤에 배치
```

## 숫자 비교

오름차순은 `a, b` 순서로 비교한다.

```java
Integer.compare(a.totalQuantity, b.totalQuantity);
Long.compare(a.totalRevenue, b.totalRevenue);
```

내림차순은 인자 순서를 반대로 둔다.

```java
Integer.compare(b.totalQuantity, a.totalQuantity);
Long.compare(b.totalRevenue, a.totalRevenue);
```

다음처럼 뺄셈으로 비교하지 않는 편이 안전하다.

```java
// 피하기
return (int) (b.totalRevenue - a.totalRevenue);
```

값의 차이가 크면 오버플로가 발생하거나 `long`을 `int`로 변환하면서 값이 손실될 수 있다.

## 문자열 비교

문자열 오름차순:

```java
return a.productName.compareTo(b.productName);
```

문자열 내림차순:

```java
return b.productName.compareTo(a.productName);
```

## reversed() 사용 시 주의

```java
Comparator.comparingLong(
    (ProductSummary a) -> a.totalRevenue
).reversed()
.thenComparingInt(a -> a.totalQuantity)
.reversed();
```

마지막 `reversed()`는 바로 앞의 수량 조건만 뒤집는 것이 아니라 지금까지 조합한 Comparator 전체를 뒤집는다.

특정 조건만 내림차순으로 만들려면 그 조건용 Comparator를 별도로 뒤집는다.

```java
Comparator.comparingLong(
    (ProductSummary a) -> a.totalRevenue
).reversed()
.thenComparing(
    Comparator.comparingInt(
        (ProductSummary a) -> a.totalQuantity
    ).reversed()
)
.thenComparing(a -> a.productName);
```

정렬 조건이 복잡하거나 `reversed()`의 적용 범위가 헷갈리면 첫 번째 수동 비교 방식을 사용한다.
