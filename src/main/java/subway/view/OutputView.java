package subway.view;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import subway.domain.Result;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final Locale KOREA = Locale.KOREA;
    private static final DateTimeFormatter DATETIME_FMT =
            DateTimeFormatter.ofPattern("M월 dd일 E요일 HH:mm", KOREA);
    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("M월 dd일 E요일", KOREA);
    private static final DateTimeFormatter TIME_FMT =
            DateTimeFormatter.ofPattern("HH:mm", KOREA);

    private OutputView() {
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void printRoute(Result result) {
        System.out.println("\n## 조회 결과");
        System.out.println("[INFO] ---");
        System.out.printf("[INFO] 총 거리: %dkm\n", result.getDistance());
        System.out.printf("[INFO] 총 소요 시간: %d분\n", result.getTime());
        System.out.println("[INFO] ---");
        for (String station : result.getStations()) {
            System.out.println("[INFO] " + station);
        }
    }
}
