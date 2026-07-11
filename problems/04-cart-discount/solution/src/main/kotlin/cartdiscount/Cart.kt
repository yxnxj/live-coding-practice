package cartdiscount

data class CartItem(
    val name: String,
    val category: Category,
    val unitPrice: Int,
    val quantity: Int,
)

data class CheckoutRequest(
    val items: List<CartItem>,
    val couponCode: String? = null,
    val membership: Membership = Membership.NORMAL,
)

data class CheckoutResult(
    val subtotal: Int,
    val discounts: List<Discount>,
    val shippingFee: Int,
    val total: Int,
)

class CheckoutService {
    fun checkout(request: CheckoutRequest): CheckoutResult {
        val subtotal = calculateSubtotal(request.items)

        // 카테고리 할인: 가장 처음 할인 적용
        val categoryDiscountResult = calculateCategoryDiscount(request.items)
        var total = subtotal - categoryDiscountResult.totalAmount
        val discounts: MutableList<Discount> = categoryDiscountResult.discounts.toMutableList()

        //쿠폰 할인: 카테고리 할인 적용 이후 가격으로 계산
        request.couponCode?.let {
            val couponDiscountResult = calculateCouponDiscount(it, total)
            total -= couponDiscountResult.totalAmount
            discounts.addAll(couponDiscountResult.discounts)
        }

        //멤버십 할인: 쿠폰 할인 적용 이후 가격으로 계산

        //배송비 계산 : 할인 적용 이후 가격으로 계산
        val shippingFee = calculateShippingFee(total)

        total += shippingFee
        return CheckoutResult(
            subtotal = subtotal,
            discounts = discounts,
            shippingFee = shippingFee,
            total = total,
        )
    }

    fun calculateSubtotal(items: List<CartItem>): Int {
        var subtotal = 0
        items.forEach { it ->
            if (it.unitPrice < 0 || it.quantity <= 0) {
                throw IllegalArgumentException("Invalid item input: ${it.name}")
            }

            subtotal += it.unitPrice * it.quantity
        }

        return subtotal
    }

    fun calculateShippingFee(subtotal: Int): Int {
        return if (subtotal >= 30_000) 0 else 3_000
    }
}
