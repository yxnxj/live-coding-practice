package cartdiscount

fun main() {
    CartDiscountTest().calculatesSubtotalAndShippingFee()
    println("All tests passed")
}

class CartDiscountTest {
    fun calculatesSubtotalAndShippingFee() {
        val result = CheckoutService().checkout(listOf(CartItem("Book", "book", 10_000, 2)))

        assertEquals(20_000, result.subtotal)
        assertEquals(23_000, result.total)
    }
}

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}
