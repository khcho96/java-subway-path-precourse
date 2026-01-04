package subway.command.main.impl;

import java.util.Scanner;
import subway.command.Command;
import subway.command.featureA.QueryMenuCommandRegistry;
import subway.command.featureA.QueryMenuOption;
import subway.util.Retry;
import subway.view.InputView;

public class QueryCommand implements Command {

    private final QueryMenuCommandRegistry stationRegistry;
    private final Scanner scanner;

    public QueryCommand(QueryMenuCommandRegistry stationRegistry, Scanner scanner) {
        this.stationRegistry = stationRegistry;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        QueryMenuOption option = getOption();

        if (option.equals(QueryMenuOption.BACK)) {
            return;
        }

        stationRegistry.execute(option);
    }

    private QueryMenuOption getOption() {
        return Retry.retryUntilSuccess(() -> {
            String selection = InputView.readQueryMenuSelection(scanner);
            return QueryMenuOption.from(selection);
        });
    }
}
