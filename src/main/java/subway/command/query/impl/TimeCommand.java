package subway.command.query.impl;

import java.util.Scanner;
import subway.command.Command;
import subway.constant.ErrorMessage;
import subway.domain.Result;
import subway.domain.Station;
import subway.service.SubwayService;
import subway.util.InputParser;
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
        Station startStation = getStartStation();
        Station endStation = getEndStation(startStation);

        Result result = getResult(startStation, endStation);

        OutputView.printRoute(result);
    }

    private Station getStartStation() {
        String stationName = InputParser.parseStation(InputView.readStartStation(scanner));
        return service.getStation(stationName);
    }

    private Station getEndStation(Station startStation) {
        String stationName = InputParser.parseStation(InputView.readEndStation(scanner));
        if (startStation.equals(service.getStation(stationName))) {
            throw new IllegalArgumentException(ErrorMessage.SAME_START_END_STATION.getErrorMessage());
        }
        return service.getStation(stationName);
    }

    private Result getResult(Station startStation, Station endStation) {
        return service.calculateMinTimeRoute(startStation, endStation);
    }
}
