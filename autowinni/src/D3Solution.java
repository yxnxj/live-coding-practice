import java.util.*;

import javax.swing.*;

public class D3Solution {

    private static final String STATUS_COMPLETED = "COMPLETED";
    private static final String STATUS_SHIPPED = "SHIPPED";

    static class OrderItem {
        String productName;
        int quantity;
        String status;

        OrderItem(String productName, int quantity, String status) {
            this.productName = productName;
            this.quantity = quantity;
            this.status = status;
        }
    }

    public static String findMostOrderedProduct(List<OrderItem> items) {
        // TODO: 여기에 풀이를 작성하세요.
        if (items == null || items.isEmpty()) return "";
        HashMap<String, OrderItem> summary = new HashMap<>();

        for(OrderItem item: items){
            // 1. `OrderItem` 객체가 `null`이 아니다.
            if (item == null) continue;

            // 2. `status`가 정확히 `COMPLETED` 또는 `SHIPPED`다.
            if (!item.status.equals(STATUS_COMPLETED) && !item.status.equals(STATUS_SHIPPED)) continue;

            // 3. `quantity`가 0보다 크다.
            if (item.quantity <= 0) continue;

            // 4. `productName`이 `null`이 아니다.
            // 5. `productName.trim()`의 결과가 빈 문자열이 아니다.
            if (item.productName == null || item.productName.trim().length() <= 0) continue;

            String productName = item.productName.trim();

            OrderItem v = summary.getOrDefault(productName, new OrderItem(productName, 0, ""));
            v.quantity += item.quantity;
            summary.put(productName, v);
        }

        if (summary.isEmpty()) return "";

        List<OrderItem> results = new ArrayList<>(summary.values());
        results.sort((a, b) ->{
            if (b.quantity != a.quantity) {
                return b.quantity - a.quantity;
            } else {
                return a.productName.compareTo(b.productName);
            }
        });


        return results.getFirst().productName;
    }

    public static void main(String[] args) {
        List<OrderItem> items = List.of(
            new OrderItem("Tire", 2, "COMPLETED"),
            new OrderItem("Door", 3, "SHIPPED"),
            new OrderItem(" Tire ", 2, "SHIPPED"),
            new OrderItem("Bumper", 4, "COMPLETED"),
            new OrderItem("Mirror", 10, "CANCELED"),
            new OrderItem("Door", 0, "COMPLETED"),
            new OrderItem("  ", 100, "SHIPPED")
        );

        assertEquals(
            "Bumper",
            findMostOrderedProduct(items),
            "총수량 동률 처리"
        );

        assertEquals(
            "Tire",
            findMostOrderedProduct(List.of(
                new OrderItem("Tire", 5, "COMPLETED"),
                new OrderItem("Door", 2, "SHIPPED")
            )),
            "최댓값이 하나인 경우"
        );

        assertEquals(
            "",
            findMostOrderedProduct(null),
            "null 입력"
        );

        assertEquals(
            "",
            findMostOrderedProduct(List.of()),
            "빈 목록"
        );

        List<OrderItem> invalidItems = new ArrayList<>();
        invalidItems.add(null);
        invalidItems.add(new OrderItem("Mirror", 10, "CANCELED"));
        invalidItems.add(new OrderItem("  ", 10, "COMPLETED"));

        assertEquals(
            "",
            findMostOrderedProduct(invalidItems),
            "유효한 항목이 없는 경우"
        );

        System.out.println("✅ 모든 테스트 통과");
    }

    private static void assertEquals(
        String expected,
        String actual,
        String testName
    ) {
        if (!expected.equals(actual)) {
            throw new AssertionError(
                testName + ": expected=" + expected + ", actual=" + actual
            );
        }
    }
}
