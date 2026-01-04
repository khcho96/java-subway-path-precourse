package subway.domain;

import java.util.ArrayList;
import java.util.List;
import subway.constant.ErrorMessage;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static Station getStation(String stationName) {
        return stations.stream()
                .filter(station -> station.getName().equals(stationName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NO_EXIST_STATION.getErrorMessage()));
    }

    public static void deleteAll() {
        stations.clear();
    }
}
