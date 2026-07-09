package cartdiscount

data class CartItem(val name: String, val category: String, val unitPrice: Int, val quantity: Int)
data class CheckoutResult(val subtotal: Int, val discounts: List<String>, val shippingFee: Int, val total: Int)

class CheckoutService {
    fun checkout(items: List<CartItem>): CheckoutResult {
        val subtotal = items.sumOf { it.unitPrice * it.quantity }
        return CheckoutResult(subtotal = subtotal, discounts = emptyList(), shippingFee = 3000, total = subtotal + 3000)
    }
}
