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

fun calculateCouponDiscount(couponCode: String?, total: Int): DiscountResult {
    var amount = 0

    when (couponCode) {
        Coupon.WELCOME10.name -> {
            amount = (total * 0.1).toInt()
        }
        Coupon.AMOUNT5000.name -> {
            if (total >= 50_000) {
                amount = 5_000
            }
        }
        else -> { throw IllegalArgumentException("Invalid coupon code: $couponCode")
        }
    }

    return if (amount > 0) DiscountResult(
        discounts = listOf(Discount(couponCode, amount)),
        totalAmount = amount,
    ) else {
        DiscountResult(
            discounts = emptyList(),
            totalAmount = 0,
        )
    }


}

fun calculateMembershipDiscount(membership: Membership, total: Int): DiscountResult {
    var amount = 0
    val suffixDiscountName = "_MEMBERSHIP"

    when (membership) {
        Membership.VIP -> {
            amount = (total * 0.03).toInt()
        }
        else -> { // No discount for NORMAL membership
        }
    }

    return if (amount > 0) DiscountResult(
        discounts = listOf(Discount(membership.name+suffixDiscountName, amount)),
        totalAmount = amount,
    ) else {
        DiscountResult(
            discounts = emptyList(),
            totalAmount = 0,
        )
    }
}
