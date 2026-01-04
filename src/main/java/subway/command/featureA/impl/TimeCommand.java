package subway.command.featureA.impl;

import java.util.Scanner;
import subway.command.Command;
import subway.constant.ErrorMessage;
import subway.domain.Result;
import subway.domain.Station;
import subway.service.SubwayService;
import subway.util.InputParser;
import subway.util.Retry;
import subway.view.InputView;
import subway.view.OutputView;

public class TimeCommand implements Command {

    private final SubwayService service;
    private final Scanner scanner;

    public TimeCommand(SubwayService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        Station startStation = Retry.retryUntilSuccess(() -> {
            String stationName = InputParser.parseStation(InputView.readStartStation(scanner));
            return service.getStation(stationName);
        });

        Station endStation = Retry.retryUntilSuccess(() -> {
            String stationName = InputParser.parseStation(InputView.readEndStation(scanner));
            if (startStation.equals(service.getStation(stationName))) {
                throw new IllegalArgumentException(ErrorMessage.SAME_START_END_STATION.getErrorMessage());
            }
            return service.getStation(stationName);
        });

        Result result = getResult(startStation, endStation);

        OutputView.printRoute(result);
    }

    private Result getResult(Station startStation, Station endStation) {
        try {
            return service.calculateMinTimeRoute(startStation, endStation);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.NO_CONNECTED_STATIONS.getErrorMessage());
        }
    }
}
