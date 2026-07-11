package cartdiscount

enum class Category {
    BOOK,
    FOOD,
    OTHER,
}

enum class Membership {
    NORMAL,
    VIP,
}

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

data class Discount(
    val name: String,
    val amount: Int,
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

        val shippingFee = calculateShippingFee(subtotal)

        return CheckoutResult(
            subtotal = subtotal,
            discounts = emptyList(),
            shippingFee = shippingFee,
            total = subtotal + shippingFee,
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
