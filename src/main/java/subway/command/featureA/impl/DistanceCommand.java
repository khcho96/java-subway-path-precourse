package subway.command.featureA.impl;

import java.util.Scanner;
import subway.command.Command;
import subway.service.SubwayService;

public class DistanceCommand implements Command {

    private final SubwayService service;
    private final Scanner scanner;

    public DistanceCommand(SubwayService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public void execute() {

    }
}
