import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class D6Solution {

    static class Reservation {
        final int start;
        final int end;

        Reservation(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof Reservation)) {
                return false;
            }

            Reservation other = (Reservation) object;
            return start == other.start && end == other.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    public static List<Reservation> organizeReservations(
        List<Reservation> reservations
    ) {
        reservations.sort((a, b) ->{
            return a.start - b.start;
        });

        List<Reservation> results = new ArrayList<>();

        for(Reservation r: reservations){
            if(results.size() <= 0) {
                results.add(r);
                continue;
            }

            Reservation comparison = results.getLast();
            if (isDuplicate(comparison, r)){
                results.removeLast();
                results.add(createOrganizedReservation(comparison, r));
            }else {
                results.add(r);
            }
        }

        return results;
    }

    public static boolean isDuplicate(Reservation comparison, Reservation input){
        if (comparison.end >= input.start) return true;
        else return false;
    }

    public static Reservation createOrganizedReservation(Reservation a, Reservation b){
        int start = a.start < b.start ? a.start : b.start;
        int end = a.end > b.end ? a.end : b.end;

        return new Reservation(start, end);
    }


    public static void main(String[] args) {
        List<Reservation> reservations = new ArrayList<>(List.of(
            new Reservation(660, 720),
            new Reservation(540, 600),
            new Reservation(720, 750),
            new Reservation(480, 510),
            new Reservation(570, 630)
        ));
        List<Reservation> result = organizeReservations(reservations);

        assertEquals(
            List.of(
                new Reservation(480, 510),
                new Reservation(540, 630),
                new Reservation(660, 750)
            ),
            result,
            "겹침과 맞닿은 경계 처리"
        );

        assertEquals(
            organizeReservations(new ArrayList<>(List.of(
                new Reservation(150, 200),
                new Reservation(100, 300),
                new Reservation(200, 250)
            ))),
            List.of(new Reservation(100, 300)),
            "포함된 예약"
        );

        assertEquals(
            List.of(
                new Reservation(100, 200),
                new Reservation(201, 300),
                new Reservation(400, 500)
            ),
            organizeReservations(new ArrayList<>(List.of(
                new Reservation(400, 500),
                new Reservation(201, 300),
                new Reservation(100, 200)
            ))),
            "서로 떨어진 예약"
        );

        System.out.println("✅ 모든 테스트 통과");
    }

    private static void assertEquals(
        List<Reservation> expected,
        List<Reservation> actual,
        String testName
    ) {
        if (!expected.equals(actual)) {
            throw new AssertionError(
                testName + ": expected=" + expected + ", actual=" + actual
            );
        }
    }
}
