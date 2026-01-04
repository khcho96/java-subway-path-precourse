package subway.command.main;

import java.util.EnumMap;
import java.util.Scanner;
import subway.command.Command;
import subway.command.featureA.QueryMenuCommandRegistry;
import subway.command.main.impl.QueryCommand;

public class MainMenuCommandRegistry {

    private final EnumMap<MainMenuOption, Command> commands;

    private MainMenuCommandRegistry(EnumMap<MainMenuOption, Command> commands) {
        this.commands = commands;
    }

    public static MainMenuCommandRegistry from(QueryMenuCommandRegistry featureARegistry, Scanner scanner) {
        EnumMap<MainMenuOption, Command> map = new EnumMap<>(MainMenuOption.class);
        map.put(MainMenuOption.A, new QueryCommand(featureARegistry, scanner)); // 기능 선택 커맨드 -> 레지스트리 전달
        return new MainMenuCommandRegistry(map);
    }

    public void execute(MainMenuOption option) {
        commands.get(option).execute();
    }
}
