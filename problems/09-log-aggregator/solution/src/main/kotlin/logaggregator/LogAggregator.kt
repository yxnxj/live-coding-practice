package logaggregator

data class LogEntry(val timestamp: String, val userId: String, val action: String)

class LogAggregator {
    fun countByUser(entries: List<LogEntry>): Map<String, Int> {
        return entries.groupingBy { it.userId }.eachCount()
    }
}
