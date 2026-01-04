package subway.domain;

import java.util.List;

public class Result {

    private final List<String> stations;
    private final int distance;
    private final int time;

    public Result(List<String> stations, int distance, int time) {
        this.stations = stations;
        this.distance = distance;
        this.time = time;
    }

    public List<String> getStations() {
        return stations;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
