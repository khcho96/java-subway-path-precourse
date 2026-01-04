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
        System.out.println("");
        return scanner.nextLine();
    }

    public static String readStation(Scanner scanner) {
        System.out.println("");
        return scanner.nextLine();
    }
}
