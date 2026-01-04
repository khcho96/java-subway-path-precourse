package subway.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import subway.command.main.MainMenuCommandRegistry;
import subway.command.main.MainMenuOption;
import subway.service.SubwayService;
import subway.util.Retry;
import subway.view.InputView;

public class SubwayController {

    private final MainMenuCommandRegistry registry;
    private final SubwayService service;
    private final Scanner scanner;

    public SubwayController(MainMenuCommandRegistry registry, SubwayService service, Scanner scanner) {
        this.registry = registry;
        this.service = service;
        this.scanner = scanner;
    }

    public void run() throws IOException {
        registerFileInfo();

        while (true) {
            MainMenuOption option = readOption();

            if (option.equals(MainMenuOption.QUIT)) {
                return;
            }

            registry.execute(option);
        }
    }

    private void registerFileInfo() throws IOException {
        List<String> stations = Arrays.asList("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
        List<List<String>> stationPairs = Arrays.asList(
                Arrays.asList("교대역", "강남역"), Arrays.asList("강남역", "역삼역"),
                Arrays.asList("교대역", "남부터미널역"), Arrays.asList("남부터미널역", "양재역"), Arrays.asList("양재역", "매봉역"),
                Arrays.asList("강남역", "양재역"), Arrays.asList("양재역", "양재시민의숲역")
        );
        List<List<Integer>> weightPairs = Arrays.asList(
                Arrays.asList(2,3), Arrays.asList(2,3),
                Arrays.asList(3,2), Arrays.asList(6,5), Arrays.asList(1,1),
                Arrays.asList(2,8), Arrays.asList(10,3)
        );
        service.setStations(stations, stationPairs, weightPairs);
    }

    private MainMenuOption readOption() {
        return Retry.retryUntilSuccess(() -> {
            String selection = InputView.readMainMenuSelection(scanner);
            return MainMenuOption.from(selection);
        });
    }
}
