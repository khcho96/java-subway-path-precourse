package subway.service;

import java.util.List;
import subway.domain.Result;
import subway.domain.RouteRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class SubwayService {

    public void setStations(List<String> stations, List<List<String>> stationPairs, List<List<Integer>> weightPairs) {
        setStations(stations);
        setRoutes(stationPairs, weightPairs);
    }

    private static void setStations(List<String> stations) {
        for (String stationName : stations) {
            Station station = Station.from(stationName);
            StationRepository.addStation(station);
            RouteRepository.addStation(station);
        }
    }

    private static void setRoutes(List<List<String>> stationPairs, List<List<Integer>> weightPairs) {
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

    public Result calculateMinDistanceRoute(Station startStation, Station endStation) {
        return RouteRepository.getMinDistanceRoute(startStation, endStation);
    }

    public Result calculateMinTimeRoute(Station startStation, Station endStation) {
        return RouteRepository.getMinTimeRoute(startStation, endStation);
    }
}
