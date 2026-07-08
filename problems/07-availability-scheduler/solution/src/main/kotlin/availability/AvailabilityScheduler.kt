package availability

data class TimeRange(val start: Int, val end: Int)

class AvailabilityScheduler {
    fun availableStarts(openHours: TimeRange, duration: Int, reservations: List<TimeRange>): List<Int> {
        return if (reservations.isEmpty() && openHours.end - openHours.start >= duration) {
            listOf(openHours.start)
        } else {
            emptyList()
        }
    }
}
