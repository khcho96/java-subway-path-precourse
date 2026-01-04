package subway.domain;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class RouteRepository {

    private static final WeightedMultigraph<Station, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
    private static final WeightedMultigraph<Station, DefaultWeightedEdge> timeGraph = new WeightedMultigraph(DefaultWeightedEdge.class);

    public static void addStation(Station station) {
        distanceGraph.addVertex(station);
        timeGraph.addVertex(station);
    }

    public static void addRoute(Station start, Station end, int distance, int time) {
        distanceGraph.setEdgeWeight(distanceGraph.addEdge(start, end), distance);
        timeGraph.setEdgeWeight(timeGraph.addEdge(start, end), time);
    }
}
