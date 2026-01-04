package subway.command.main;

import java.util.Arrays;
import subway.constant.ErrorMessage;

public enum MainMenuOption {
    A("1"),
    QUIT("Q");

    private final String command;

    MainMenuOption(String command) {
        this.command = command;
    }

    public static MainMenuOption from(String command) {
        String normalized = command.trim();
        return Arrays.stream(values())
                .filter(opt -> opt.command.equals(normalized))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.FORMAT_ERROR.getErrorMessage()));
    }
}
