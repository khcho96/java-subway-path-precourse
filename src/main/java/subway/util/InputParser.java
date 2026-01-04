package subway.util;

import subway.command.featureA.QueryMenuOption;
import subway.command.main.MainMenuOption;

public final class InputParser {

    private static final String DELIMITER = ",";
    private static final String FIRST_DELIMITER = ",";
    private static final String SECOND_DELIMITER = "-";

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
