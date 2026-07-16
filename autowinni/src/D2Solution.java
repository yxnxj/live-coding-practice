import java.util.*;
import java.util.stream.Stream;

public class D2Solution {

    private static final String STATUS_COMPLETED = "COMPLETED";
    private static final String STATUS_SHIPPED = "SHIPPED";

    static class Sale {
        String productName;
        int quantity;
        long unitPrice;
        String status;

        Sale(String productName, int quantity, long unitPrice, String status) {
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.status = status;
        }
    }

    static class ProductSummary {
        /**
         *  - `totalQuantity`: 유효한 판매 수량의 합
            - `totalRevenue`: 모든 `quantity * unitPrice`의 합
            - `saleCount`: 유효한 판매 기록의 개수
         */
        String productName;
        int totalQuantity;
        long totalRevenue;
        int saleCount;

        ProductSummary(
            String productName,
            int totalQuantity,
            long totalRevenue,
            int saleCount
        ) {
            this.productName = productName;
            this.totalQuantity = totalQuantity;
            this.totalRevenue = totalRevenue;
            this.saleCount = saleCount;
        }
    }

    public static List<ProductSummary> summarizeSales(List<Sale> sales) {
        HashMap<String, ProductSummary> summaries = new HashMap<>();

        // 1. `Sale` 객체가 `null`이 아니다.
        if (sales == null) return new ArrayList<>();

        for(Sale sale: sales){

            // 2. `status`가 정확히 `COMPLETED` 또는 `SHIPPED`다.
            if (!Objects.equals(sale.status, "COMPLETED") && !Objects.equals(sale.status, "SHIPPED")) continue;

            // 3. `quantity`가 0보다 크다.
            // 4. `unitPrice`가 0보다 크다.
            if (sale.quantity <= 0 || sale.unitPrice <= 0) continue;

            // 5. `productName`이 `null`이 아니다.
            // 6. `productName.trim()`의 결과가 빈 문자열이 아니다.
            if (sale.productName == null || sale.productName.trim().length() <= 0) continue;


            String productName = sale.productName.trim();
            if (summaries.containsKey(productName)){
                ProductSummary summary = summaries.get(productName);
                summary.totalQuantity += sale.quantity;
                summary.totalRevenue += sale.quantity * sale.unitPrice;
                summary.saleCount += 1;
            } else {
                ProductSummary summary = new ProductSummary(productName, sale.quantity, sale.quantity * sale.unitPrice, 1);
                summaries.put(sale.productName, summary);
            }
        }


        List<ProductSummary> results = new ArrayList<>(summaries.values());
        results.sort((a, b) -> {
            if (a.totalRevenue != b.totalRevenue){
                return Long.compare(b.totalRevenue, a.totalRevenue);
            } else if (a.totalQuantity != b.totalQuantity){
                return Integer.compare(b.totalQuantity, a.totalQuantity);
            } else {
                return a.productName.compareTo(b.productName);
            }
        });

        return results;
    }

    public static void main(String[] args) {
        List<Sale> sales = List.of(
            new Sale("Bumper", 2, 500, "COMPLETED"),
            new Sale("Door", 1, 1500, "SHIPPED"),
            new Sale(" Bumper ", 1, 700, "SHIPPED"),
            new Sale("Tire", 4, 400, "COMPLETED"),
            new Sale("Window", 2, 750, "COMPLETED"),
            new Sale("Lamp", 1, 1500, "COMPLETED"),
            new Sale("Mirror", 10, 1000, "CANCELED"),
            new Sale("Tire", 0, 9999, "COMPLETED"),
            new Sale("  ", 5, 1000, "SHIPPED")
        );

        List<String> expected = List.of(
            "Bumper 3 1700 2",
            "Tire 4 1600 1",
            "Window 2 1500 1",
            "Door 1 1500 1",
            "Lamp 1 1500 1"
        );

        List<String> actual = toLines(summarizeSales(sales));

        if (!expected.equals(actual)) {
            throw new AssertionError(
                "expected=" + expected + System.lineSeparator()
                    + "actual=" + actual
            );
        }

        if (!summarizeSales(null).isEmpty()) {
            throw new AssertionError("null 입력은 빈 목록이어야 합니다.");
        }

        if (!summarizeSales(List.of()).isEmpty()) {
            throw new AssertionError("빈 입력은 빈 목록이어야 합니다.");
        }

        System.out.println("✅ 모든 테스트 통과");
    }

    private static List<String> toLines(List<ProductSummary> summaries) {
        List<String> lines = new ArrayList<>();

        for (ProductSummary summary : summaries) {
            lines.add(
                summary.productName + " "
                    + summary.totalQuantity + " "
                    + summary.totalRevenue + " "
                    + summary.saleCount
            );
        }

        return lines;
    }
}
