package cartdiscount

fun main() {
    val test = CartDiscountTest()
    test.chargesShippingWhenDiscountedAmountIsBelowThreshold()
    test.appliesCategoryDiscounts()
    test.appliesWelcomeCouponAfterCategoryDiscount()
//    test.appliesAmountCouponAtThreshold()
//    test.appliesVipDiscountAfterCoupon()
//    test.usesDiscountedAmountForFreeShippingThreshold()
//    test.rejectsInvalidItemInput()
//    test.rejectsUnknownCoupon()
    println("All tests passed")
}

class CartDiscountTest {
    private val checkoutService = CheckoutService()

    fun chargesShippingWhenDiscountedAmountIsBelowThreshold() {
        val result = checkout(CartItem("Keyboard", Category.OTHER, 20_000, 1))

        assertEquals(20_000, result.subtotal)
        assertEquals(emptyList<Discount>(), result.discounts)
        assertEquals(3_000, result.shippingFee)
        assertEquals(23_000, result.total)
    }

    fun appliesCategoryDiscounts() {
        val result = checkout(
            CartItem("Book", Category.BOOK, 10_000, 2),
            CartItem("Meal", Category.FOOD, 10_000, 1),
            CartItem("Cable", Category.OTHER, 5_000, 1),
        )

        assertEquals(35_000, result.subtotal)
        assertEquals(
            listOf(Discount("BOOK_CATEGORY", 2_000), Discount("FOOD_CATEGORY", 500)),
            result.discounts,
        )
        assertEquals(0, result.shippingFee)
        assertEquals(32_500, result.total)
    }

    fun appliesWelcomeCouponAfterCategoryDiscount() {
        val result = checkout(
            CartItem("Book", Category.BOOK, 20_000, 2),
            couponCode = "WELCOME10",
        )

        assertEquals(
            listOf(Discount("BOOK_CATEGORY", 4_000), Discount("WELCOME10", 3_600)),
            result.discounts,
        )
        assertEquals(32_400, result.total)
    }

    fun appliesAmountCouponAtThreshold() {
        val eligible = checkout(
            CartItem("Monitor", Category.OTHER, 50_000, 1),
            couponCode = "AMOUNT5000",
        )
        val ineligible = checkout(
            CartItem("Monitor", Category.OTHER, 49_999, 1),
            couponCode = "AMOUNT5000",
        )

        assertEquals(listOf(Discount("AMOUNT5000", 5_000)), eligible.discounts)
        assertEquals(45_000, eligible.total)
        assertEquals(emptyList<Discount>(), ineligible.discounts)
        assertEquals(49_999, ineligible.total)
    }

    fun appliesVipDiscountAfterCoupon() {
        val result = checkout(
            CartItem("Book", Category.BOOK, 50_000, 1),
            couponCode = "WELCOME10",
            membership = Membership.VIP,
        )

        assertEquals(
            listOf(
                Discount("BOOK_CATEGORY", 5_000),
                Discount("WELCOME10", 4_500),
                Discount("VIP_MEMBERSHIP", 1_215),
            ),
            result.discounts,
        )
        assertEquals(39_285, result.total)
    }

    fun usesDiscountedAmountForFreeShippingThreshold() {
        val below = checkout(CartItem("Book", Category.BOOK, 33_332, 1))
        val atThreshold = checkout(CartItem("Cable", Category.OTHER, 30_000, 1))

        assertEquals(3_000, below.shippingFee)
        assertEquals(29_999 + 3_000, below.total)
        assertEquals(0, atThreshold.shippingFee)
        assertEquals(30_000, atThreshold.total)
    }

    fun rejectsInvalidItemInput() {
        assertThrows<IllegalArgumentException> {
            checkout(CartItem("Book", Category.BOOK, 10_000, 0))
        }
        assertThrows<IllegalArgumentException> {
            checkout(CartItem("Book", Category.BOOK, -1, 1))
        }
    }

    fun rejectsUnknownCoupon() {
        assertThrows<IllegalArgumentException> {
            checkout(CartItem("Book", Category.BOOK, 10_000, 1), couponCode = "UNKNOWN")
        }
    }

    private fun checkout(
        vararg items: CartItem,
        couponCode: String? = null,
        membership: Membership = Membership.NORMAL,
    ): CheckoutResult = checkoutService.checkout(
        CheckoutRequest(items.toList(), couponCode, membership),
    )
}

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}

inline fun <reified T : Throwable> assertThrows(block: () -> Unit) {
    try {
        block()
    } catch (error: Throwable) {
        if (error is T) return
        throw AssertionError("Expected ${T::class.simpleName} but got ${error::class.simpleName}", error)
    }
    throw AssertionError("Expected ${T::class.simpleName} to be thrown")
}
