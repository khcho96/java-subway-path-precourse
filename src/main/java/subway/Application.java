package subway;

import java.io.IOException;
import java.util.Scanner;
import subway.command.featureA.QueryMenuCommandRegistry;
import subway.command.main.MainMenuCommandRegistry;
import subway.controller.SubwayController;
import subway.service.SubwayService;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        SubwayService service = new SubwayService();
        QueryMenuCommandRegistry queryMenuCommandRegistry = QueryMenuCommandRegistry.from(service, scanner);

        MainMenuCommandRegistry mainRegistry = MainMenuCommandRegistry.from(queryMenuCommandRegistry, scanner);

        SubwayController subwayController = new SubwayController(mainRegistry, service, scanner);
        try {
            subwayController.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
