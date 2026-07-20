public class D5Solution {

    public static String summarizeRuns(String text) {
        // TODO: 여기에 풀이를 작성하세요.
        return "";
    }

    public static void main(String[] args) {
        assertEquals(
            "a3b2c4d1a2",
            summarizeRuns("aaabbccccdaa"),
            "여러 구간"
        );

        assertEquals(
            "a1b1a1b1",
            summarizeRuns("abab"),
            "모든 문자가 한 번씩 등장"
        );

        assertEquals(
            "z1",
            summarizeRuns("z"),
            "문자 하나"
        );

        assertEquals(
            "a12",
            summarizeRuns("aaaaaaaaaaaa"),
            "구간 길이가 10 이상"
        );

        assertEquals(
            "a2b2a2",
            summarizeRuns("aabbaa"),
            "처음과 마지막 문자가 같은 경우"
        );

        assertEquals("", summarizeRuns(""), "빈 문자열");

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
