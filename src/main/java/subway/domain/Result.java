package subway.domain;

import java.util.List;

public class Result {

    private final List<String> stations;
    private final int weight;

    public Result(List<String> stations, int weight) {
        this.stations = stations;
        this.weight = weight;
    }

    public List<String> getStations() {
        return stations;
    }

    public int getWeight() {
        return weight;
    }
}
