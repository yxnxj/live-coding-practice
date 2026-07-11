package cartdiscount

typealias DiscountHandler = (CheckoutRequest, Int) -> DiscountResult

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
    private fun createDiscountHandler(): List<DiscountHandler>{
        val handler = mutableListOf<DiscountHandler>()
        handler.add(::calculateCategoryDiscount)
        handler.add(::calculateCouponDiscount)
        handler.add(::calculateMembershipDiscount)

        return handler
    }

    private fun calculateCategoryDiscount(request: CheckoutRequest, total: Int): DiscountResult {
        val suffixDiscountName = "_CATEGORY"
        val discounts = mutableListOf<Discount>()
        var totalAmount = 0

        request.items.forEach { it ->
            val subtotal = it.unitPrice * it.quantity
            var name = ""
            var amount = 0

            when (it.category) {
                Category.BOOK -> {
                    amount = (subtotal * 0.1).toInt()
                    name = "BOOK$suffixDiscountName"

                }
                Category.FOOD -> {
                    amount = (subtotal * 0.05).toInt()
                    name = "FOOD$suffixDiscountName"
                }
                else -> { // No discount for OTHER category
                }
            }

            if (amount != 0){
                discounts.add(Discount(name, amount))
                totalAmount += amount
            }
        }

        return DiscountResult(
            discounts = discounts,
            totalAmount = totalAmount,
        )
    }

    private fun calculateCouponDiscount(request: CheckoutRequest, total: Int): DiscountResult {
        var amount = 0

        when (request.couponCode) {
            Coupon.WELCOME10.name -> {
                amount = (total * 0.1).toInt()
            }
            Coupon.AMOUNT5000.name -> {
                if (total >= 50_000) {
                    amount = 5_000
                }
            }
            null -> {}
            else -> { throw IllegalArgumentException("Invalid coupon code: $request.couponCode")
            }
        }

        return if (amount > 0 && request.couponCode != null) DiscountResult(
            discounts = listOf(Discount(request.couponCode, amount)),
            totalAmount = amount,
        ) else {
            DiscountResult(
                discounts = emptyList(),
                totalAmount = 0,
            )
        }


    }

    private fun calculateMembershipDiscount(request: CheckoutRequest, total: Int): DiscountResult {
        var amount = 0
        val suffixDiscountName = "_MEMBERSHIP"

        when (request.membership) {
            Membership.VIP -> {
                amount = (total * 0.03).toInt()
            }

            else -> { // No discount for NORMAL membership
            }
        }

        return if (amount > 0) DiscountResult(
            discounts = listOf(Discount(request.membership.name + suffixDiscountName, amount)),
            totalAmount = amount,
        ) else {
            DiscountResult(
                discounts = emptyList(),
                totalAmount = 0,
            )
        }
    }

    val discountHandlers = createDiscountHandler()

    fun checkout(request: CheckoutRequest): CheckoutResult {
        val subtotal = calculateSubtotal(request.items)
        var total = subtotal

        val discounts: MutableList<Discount> = mutableListOf()

        for (handler in discountHandlers) {
            val discountResult = handler(request, total)
            total -= discountResult.totalAmount
            discounts.addAll(discountResult.discounts)
        }

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
