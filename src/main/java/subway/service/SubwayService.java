package subway.service;

import java.util.List;
import subway.domain.RouteRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class SubwayService {

    public void setStations(List<String> stations, List<List<String>> stationPairs, List<List<Integer>> weightPairs) {
        for (String stationName : stations) {
            Station station = Station.from(stationName);
            StationRepository.addStation(station);
            RouteRepository.addStation(station);
        }

        for (int i = 0; i < stationPairs.size(); i++) {
            Station start = StationRepository.getStation(stationPairs.get(i).get(0));
            Station end = StationRepository.getStation(stationPairs.get(i).get(1));
            int distance = weightPairs.get(i).get(0);
            int time = weightPairs.get(i).get(1);

            RouteRepository.addRoute(start, end, distance, time);
        }
    }

    public Station getStation(String station) {
        return StationRepository.getStation(station);
    }
}
