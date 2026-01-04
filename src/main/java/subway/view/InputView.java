package subway.view;

import java.util.Scanner;

public class InputView {

    public static String readMainMenuSelection(Scanner scanner) {
        System.out.println("\n## 메인 화면\n"
                + "1. 경로 조회\n"
                + "Q. 종료\n"
                + "\n"
                + "## 원하는 기능을 선택하세요.");
        return scanner.nextLine();
    }

    public static String readQueryMenuSelection(Scanner scanner) {
        System.out.println("\n## 경로 기준\n"
                + "1. 최단 거리\n"
                + "2. 최소 시간\n"
                + "B. 돌아가기\n"
                + "\n"
                + "## 원하는 기능을 선택하세요.");
        return scanner.nextLine();
    }

    public static String readStartStation(Scanner scanner) {
        System.out.println("\n## 출발역을 입력하세요.");
        return scanner.nextLine();
    }

    public static String readEndStation(Scanner scanner) {
        System.out.println("\n## 도착역을 입력하세요.");
        return scanner.nextLine();
    }
}
