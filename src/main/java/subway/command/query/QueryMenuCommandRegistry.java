package subway.command.query;

import java.util.EnumMap;
import java.util.Scanner;
import subway.command.Command;
import subway.command.query.impl.DistanceCommand;
import subway.command.query.impl.TimeCommand;
import subway.service.SubwayService;

public class QueryMenuCommandRegistry {

    private final EnumMap<QueryMenuOption, Command> commands;

    private QueryMenuCommandRegistry(EnumMap<QueryMenuOption, Command> commands) {
        this.commands = commands;
    }

    public static QueryMenuCommandRegistry from(SubwayService service, Scanner scanner) {
        EnumMap<QueryMenuOption, Command> map = new EnumMap<>(QueryMenuOption.class);
        map.put(QueryMenuOption.A, new DistanceCommand(service, scanner));
        map.put(QueryMenuOption.B, new TimeCommand(service, scanner));
        return new QueryMenuCommandRegistry(map);
    }

    public void execute(QueryMenuOption option) {
        commands.get(option).execute();
    }
}
