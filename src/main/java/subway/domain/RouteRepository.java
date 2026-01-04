package subway.domain;

import java.util.List;
import java.util.stream.Collectors;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.constant.ErrorMessage;

public class RouteRepository {

    private static final WeightedMultigraph<Station, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(
            DefaultWeightedEdge.class);
    private static final WeightedMultigraph<Station, DefaultWeightedEdge> timeGraph = new WeightedMultigraph(
            DefaultWeightedEdge.class);
    private static final DijkstraShortestPath minDistancePath = new DijkstraShortestPath(distanceGraph);
    private static final DijkstraShortestPath minTimePath = new DijkstraShortestPath(timeGraph);

    public static void addStation(Station station) {
        distanceGraph.addVertex(station);
        timeGraph.addVertex(station);
    }

    public static void addRoute(Station start, Station end, int distance, int time) {
        distanceGraph.setEdgeWeight(distanceGraph.addEdge(start, end), distance);
        timeGraph.setEdgeWeight(timeGraph.addEdge(start, end), time);
    }

    public static Result getMinDistanceRoute(Station startStation, Station endStation) {
        GraphPath path = minDistancePath.getPath(startStation, endStation);
        if (path == null) {
            throw new IllegalArgumentException(ErrorMessage.NO_CONNECTED_STATIONS.getErrorMessage());
        }

        List<Station> stations = path.getVertexList();
        List<String> stationNames = stations.stream()
                .map(Station::getName)
                .collect(Collectors.toList());
        int misDistance = (int) minDistancePath.getPath(startStation, endStation).getWeight();
        int timeSum = getTime(stations);

        return new Result(stationNames, misDistance, timeSum);
    }

    private static int getTime(List<Station> stations) {
        int timeSum = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            Station firstStation = stations.get(i);
            Station secondStation = stations.get(i + 1);

            timeSum += (int) minTimePath.getPath(firstStation, secondStation).getWeight();
        }
        return timeSum;
    }

    public static Result getMinTimeRoute(Station startStation, Station endStation) {
        GraphPath path = minTimePath.getPath(startStation, endStation);
        if (path == null) {
            throw new IllegalArgumentException(ErrorMessage.NO_CONNECTED_STATIONS.getErrorMessage());
        }

        List<Station> stations = path.getVertexList();
        List<String> stationNames = stations.stream()
                .map(Station::getName)
                .collect(Collectors.toList());
        int distanceSum = getDistance(stations);
        int minTime = (int) minTimePath.getPath(startStation, endStation).getWeight();

        return new Result(stationNames, distanceSum, minTime);
    }

    private static int getDistance(List<Station> stations) {
        int distanceSum = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            Station firstStation = stations.get(i);
            Station secondStation = stations.get(i + 1);

            distanceSum += (int) minDistancePath.getPath(firstStation, secondStation).getWeight();
        }
        return distanceSum;
    }
}
