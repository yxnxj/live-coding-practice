package cartdiscount

enum class Category {
    BOOK,
    FOOD,
    OTHER,
}

enum class Coupon {
    WELCOME10,
    AMOUNT5000,
}

enum class Membership {
    NORMAL,
    VIP,
}

data class Discount(
    val name: String,
    val amount: Int,
)

data class DiscountResult(
    val discounts: List<Discount>,
    val totalAmount: Int,
)

