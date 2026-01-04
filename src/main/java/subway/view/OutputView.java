package subway.view;

import subway.domain.Result;

public class OutputView {

    private OutputView() {
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void printRoute(Result result) {
        System.out.println("\n## 조회 결과");
        System.out.println("[INFO] ---");
        System.out.printf("[INFO] 총 거리: %dkm\n", result.distance());
        System.out.printf("[INFO] 총 소요 시간: %d분\n", result.time());
        System.out.println("[INFO] ---");
        for (String station : result.stations()) {
            System.out.println("[INFO] " + station);
        }
    }
}
