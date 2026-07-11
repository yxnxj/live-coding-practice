# 04 Cart Discount

## Type

Domain logic

## Source

- Custom practice problem created for this repository. It is not copied from a published kata.

## Problem

온라인 쇼핑몰의 장바구니 결제 금액을 계산한다. 장바구니에는 여러 상품이 들어 있고, 상품별 카테고리, 단가, 수량이 있다. 시스템은 상품 금액 합계, 할인 내역, 배송비, 최종 결제 금액을 계산해야 한다.

목표는 할인 규칙의 적용 순서를 분명하게 표현하고, 새로운 할인 정책이 추가되어도 변경 범위가 작도록 구현하는 것이다.

## Input Model

상품은 다음 정보를 가진다.

- `name`: 상품명
- `category`: 카테고리
- `unitPrice`: 단가
- `quantity`: 수량

계산 요청은 다음 정보를 가진다.

- 장바구니 상품 목록
- 쿠폰 코드. 없을 수 있다.
- 회원 등급. 예: `NORMAL`, `VIP`

## Required Rules

### Subtotal

- 상품별 금액은 `unitPrice * quantity`다.
- 장바구니 원금은 모든 상품별 금액의 합이다.
- 수량이 0 이하이거나 가격이 음수인 상품은 잘못된 입력으로 처리한다.

### Category Discount

- `BOOK` 카테고리는 해당 카테고리 상품 금액의 10%를 할인한다.
- `FOOD` 카테고리는 해당 카테고리 상품 금액의 5%를 할인한다.
- 카테고리 할인이 없는 상품은 할인하지 않는다.

### Coupon Discount

- `WELCOME10`: 카테고리 할인 적용 후 금액의 10%를 할인한다.
- `AMOUNT5000`: 카테고리 할인 적용 후 금액이 50000 이상이면 5000을 할인한다.
- 알 수 없는 쿠폰 코드는 잘못된 입력으로 처리한다.

### Membership Discount

- `VIP` 회원은 쿠폰 할인 적용 후 금액의 3%를 추가 할인한다.
- `NORMAL` 회원은 추가 할인이 없다.

### Shipping

- 할인 적용 후 상품 금액이 30000 이상이면 배송비는 0이다.
- 그렇지 않으면 배송비는 3000이다.
- 최종 결제 금액은 `할인 적용 후 상품 금액 + 배송비`다.

## Discount Order

할인은 반드시 아래 순서로 적용한다.

1. 카테고리 할인
2. 쿠폰 할인
3. 회원 등급 할인
4. 배송비 계산

각 할인은 이전 단계의 결과 금액을 기준으로 계산한다.

퍼센트 할인 금액에 소수점이 생기면 소수점 이하는 버린다. 예를 들어
`33333`원의 10% 할인액은 `3333`원이다. 모든 금액은 원 단위 정수로 계산한다.

## Output Model

결과에는 다음 정보가 포함되어야 한다.

- `subtotal`
- 적용된 할인 목록. 각 항목은 이름과 금액을 가진다.
- `shippingFee`
- `total`

할인 목록의 이름은 테스트에 정의된 `BOOK_CATEGORY`, `FOOD_CATEGORY`,
`WELCOME10`, `AMOUNT5000`, `VIP_MEMBERSHIP`을 사용하고, 실제로 금액이
차감된 할인만 적용 순서대로 포함한다.

## Starter API

시작 코드는 필요한 입력과 출력 타입만 제공하며 `CheckoutService.checkout`은
의도적으로 구현되어 있지 않다. 테스트를 하나씩 통과시키며 구현한다.

```kotlin
fun checkout(request: CheckoutRequest): CheckoutResult
```

## Suggested Test Cases

- 할인 없는 장바구니.
- 카테고리 할인만 적용되는 경우.
- 쿠폰 할인과 카테고리 할인이 함께 적용되는 경우.
- VIP 추가 할인이 적용되는 경우.
- 무료배송 경계값 29999, 30000.
- 잘못된 쿠폰 코드와 잘못된 상품 입력.

## Done Criteria

- 할인 적용 순서가 테스트로 고정되어 있다.
- 최종 금액뿐 아니라 할인 내역도 검증한다.
- 새 할인 정책을 어디에 추가할지 설명할 수 있다.

## IntelliJ / Run

IntelliJ에서 이 문제를 풀 때는 이 `solution/` 폴더를 Gradle 프로젝트로 연다.

이 폴더 기준으로 테스트를 실행한다.

```bash
sh run-tests.sh
```

Gradle import가 실패하면 Gradle JVM을 JDK 21로 맞춘다.
