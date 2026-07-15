// Online Java - IDE, Code Editor, Compiler

// Online Java is a quick and easy tool that helps you to build, compile, test your programs online.

// Write your Java code here
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class Main {
	private static String STATUS_PAID = "PAID";
	private static String STATUS_SHIPPED = "SHIPPED";
	private static String STATUS_CANCELED = "CANCELED";

	public static void main(String[] args) {
		List<Order> orders = List.of(
			new Order("O-100", "Kim", 3000, "PAID"),
			new Order("O-101", "Lee", 5000, "SHIPPED"),
			new Order("O-102", "Kim", 2000, "CANCELED"),
			new Order("O-103", "Park", 5000, "PAID"),
			new Order("O-100", "Kim", 9000, "PAID"),
			new Order("O-104", "Kim", 2000, "SHIPPED"),
			new Order("O-105", "  ", 10000, "PAID"),
			new Order("O-106", "Lee", -1000, "PAID")
		);


		for(CustomerSummary summary: summarizeOrders(orders)){
			System.out.println(summary.customerName + " " + summary.totalAmount + " " + summary.orderCount);
		}
	}

	public static List<CustomerSummary> summarizeOrders(List<Order> orders) {
		if (orders == null || orders.isEmpty()) return new ArrayList<>();

		Set<String> checkedOrderIds = new HashSet<>();
		HashMap<String, CustomerSummary> summaries = new HashMap<>();

		for(Order order : orders){
			if (!STATUS_PAID.equals(order.status)
				&& !STATUS_SHIPPED.equals(order.status)) {
				continue;
			}
			if(order.amount <= 0) continue;
			if(order.customerName == null || order.customerName.trim().isEmpty()) continue;

			if (!checkedOrderIds.add(order.orderId)) {
				continue;
			}
			CustomerSummary summary =
				summaries.getOrDefault(order.customerName,
					new CustomerSummary(order.customerName, 0, 0));

			summary.totalAmount += order.amount;
			summary.orderCount += 1;
			summaries.put(order.customerName, summary);
			checkedOrderIds.add(order.orderId);
		}

		return summaries.values().stream()
			.sorted(
				Comparator.comparing((CustomerSummary s) -> s.totalAmount).reversed()
				.thenComparing(s -> s.customerName)
			).toList();
	}
}
