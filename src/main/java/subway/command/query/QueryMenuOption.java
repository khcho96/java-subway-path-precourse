package subway.command.query;

import java.util.Arrays;
import subway.constant.ErrorMessage;

public enum QueryMenuOption {
    A("1"),
    B("2"),
    BACK("B");

    private final String command;

    QueryMenuOption(String command) {
        this.command = command;
    }

    public static QueryMenuOption from(String command) {
        String normalized = command.trim();
        return Arrays.stream(values())
                .filter(opt -> opt.command.equals(normalized))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.FORMAT_ERROR.getErrorMessage()));
    }
}
