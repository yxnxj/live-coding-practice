package availability

fun main() {
    AvailabilitySchedulerTest().returnsOpeningTimeWhenDayIsEmpty()
    println("All tests passed")
}

class AvailabilitySchedulerTest {
    fun returnsOpeningTimeWhenDayIsEmpty() {
        val actual = AvailabilityScheduler().availableStarts(TimeRange(9 * 60, 18 * 60), 30, emptyList())

        assertEquals(listOf(9 * 60), actual)
    }
}

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}
