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
        TODO("Implement the checkout rules described in README.md")
    }
}
