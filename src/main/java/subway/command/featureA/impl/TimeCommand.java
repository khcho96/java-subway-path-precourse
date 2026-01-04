package subway.command.featureA.impl;

import java.util.Scanner;
import subway.command.Command;
import subway.service.SubwayService;

public class TimeCommand implements Command {

    private final SubwayService service;
    private final Scanner scanner;

    public TimeCommand(SubwayService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public void execute() {

    }
}
