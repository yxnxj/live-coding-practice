import java.util.*;


public class D4Solution {

    public static List<String> processCommands(List<String> commands) {
        // TODO: 여기에 풀이를 작성하세요.
        if (commands == null || commands.size() <= 0) return new ArrayList<>();

        Deque<String> pendingTasks = new ArrayDeque<String>();
        List<String> results = new ArrayList<>();

        for(String command: commands){
            if (!command.equals("RUN") ){
                pendingTasks.add(command.substring(4));
                continue;
            }

            if (pendingTasks.size() > 0){
                String task = pendingTasks.pollFirst();
                results.add(task);
            }
        }

        return results;
    }

    public static void main(String[] args) {
        assertEquals(
            List.of("JOB-100", "JOB-200", "JOB-300"),
            processCommands(List.of(
                "ADD JOB-100",
                "ADD JOB-200",
                "RUN",
                "ADD JOB-300",
                "RUN",
                "RUN",
                "RUN"
            )),
            "작업 추가와 실행이 섞인 경우"
        );

        assertEquals(
            List.of("A", "B", "C"),
            processCommands(List.of(
                "RUN",
                "ADD A",
                "ADD B",
                "ADD C",
                "RUN",
                "RUN",
                "RUN",
                "RUN"
            )),
            "빈 상태의 RUN과 연속 실행"
        );

        assertEquals(
            List.of("A", "A"),
            processCommands(List.of(
                "ADD A",
                "ADD A",
                "RUN",
                "RUN"
            )),
            "같은 작업 ID가 여러 번 추가된 경우"
        );

        assertEquals(
            List.of("A", "B"),
            processCommands(List.of(
                "ADD A",
                "ADD B",
                new String("RUN"),
                "ADD C",
                "RUN"
            )),
            "RUN 한 번에 작업 하나만 실행"
        );

        assertEquals(
            List.of(),
            processCommands(List.of(
                "ADD A",
                "ADD B"
            )),
            "실행 명령이 없는 경우"
        );

        assertEquals(
            List.of(),
            processCommands(null),
            "null 입력"
        );

        assertEquals(
            List.of(),
            processCommands(List.of()),
            "빈 목록"
        );

        List<String> original = new ArrayList<>(List.of(
            "ADD ORIGINAL",
            "RUN"
        ));
        List<String> snapshot = new ArrayList<>(original);

        processCommands(original);
        assertEquals(snapshot, original, "원본 목록 보존");

        System.out.println("✅ 모든 테스트 통과");
    }

    private static void assertEquals(
        List<String> expected,
        List<String> actual,
        String testName
    ) {
        if (!expected.equals(actual)) {
            throw new AssertionError(
                testName + ": expected=" + expected + ", actual=" + actual
            );
        }
    }
}
