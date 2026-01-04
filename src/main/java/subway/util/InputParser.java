package subway.util;

import subway.command.query.QueryMenuOption;
import subway.command.main.MainMenuOption;

public final class InputParser {

    private InputParser() {
    }

    public static String parseStation(String rawInput) {
        return rawInput.strip();
    }

    public static MainMenuOption parseMainMenuOption(String rawInput) {
        return MainMenuOption.from(rawInput.strip());
    }

    public static QueryMenuOption parseQueryMenuOption(String rawInput) {
        return QueryMenuOption.from(rawInput.strip());
    }
}
