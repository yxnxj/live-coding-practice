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

data class DiscountResult(
    val discounts: List<Discount>,
    val totalAmount: Int,
)

class CheckoutService {
    fun checkout(request: CheckoutRequest): CheckoutResult {
        val subtotal = calculateSubtotal(request.items)
        val categoryDiscountResult = calculateCategoryDiscount(request.items)

        val shippingFee = calculateShippingFee(subtotal)

        return CheckoutResult(
            subtotal = subtotal,
            discounts = categoryDiscountResult.discounts,
            shippingFee = shippingFee,
            total = subtotal + shippingFee- categoryDiscountResult.totalAmount,
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

    fun calculateCategoryDiscount(items: List<CartItem>): DiscountResult {
        val suffixDiscountName = "_CATEGORY"
        val discounts = mutableListOf<Discount>()
        var totalAmount = 0

        items.forEach { it ->
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

    fun calculateShippingFee(subtotal: Int): Int {
        return if (subtotal >= 30_000) 0 else 3_000
    }
}
