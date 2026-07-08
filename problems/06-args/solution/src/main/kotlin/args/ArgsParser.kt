package args

class ArgsParser {
    fun parseBoolean(flag: String, args: List<String>): Boolean {
        return args.contains("-$flag")
    }
}
