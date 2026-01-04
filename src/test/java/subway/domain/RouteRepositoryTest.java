package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import subway.constant.ErrorMessage;

class RouteRepositoryTest {

    @BeforeAll
    static void setUpAll() {
        List<String> stations = Arrays.asList("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
        List<List<String>> stationPairs = Arrays.asList(
                Arrays.asList("교대역", "강남역"), Arrays.asList("강남역", "역삼역"),
                Arrays.asList("교대역", "남부터미널역"), Arrays.asList("남부터미널역", "양재역"), Arrays.asList("양재역", "매봉역"),
                Arrays.asList("강남역", "양재역"), Arrays.asList("양재역", "양재시민의숲역")
        );
        List<List<Integer>> weightPairs = Arrays.asList(
                Arrays.asList(2, 3), Arrays.asList(2, 3),
                Arrays.asList(3, 2), Arrays.asList(6, 5), Arrays.asList(1, 1),
                Arrays.asList(2, 8), Arrays.asList(10, 3)
        );

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

    @ParameterizedTest
    @MethodSource("minDistanceResultProvider")
    void 최단거리_경로_테스트(Station startStation, Station endStation, int minDistance, int time, int size) {
        Result result = RouteRepository.getMinDistanceRoute(startStation, endStation);

        assertThat(result.distance()).isEqualTo(minDistance);
        assertThat(result.time()).isEqualTo(time);
        assertThat(result.stations().size()).isEqualTo(size);
    }

    static Stream<Arguments> minDistanceResultProvider() {
        return Stream.of(
                Arguments.of(Station.from("교대역"), Station.from("양재역"), 4, 11, 3),
                Arguments.of(Station.from("교대역"), Station.from("강남역"), 2, 3, 2),
                Arguments.of(Station.from("교대역"), Station.from("매봉역"), 5, 12, 4),
                Arguments.of(Station.from("교대역"), Station.from("양재시민의숲역"), 14, 14, 4),
                Arguments.of(Station.from("양재역"), Station.from("교대역"), 4, 11, 3),
                Arguments.of(Station.from("양재역"), Station.from("역삼역"), 4, 11, 3),
                Arguments.of(Station.from("양재역"), Station.from("남부터미널역"), 6, 5, 2),
                Arguments.of(Station.from("매봉역"), Station.from("역삼역"), 5, 12, 4),
                Arguments.of(Station.from("매봉역"), Station.from("강남역"), 3, 9, 3),
                Arguments.of(Station.from("강남역"), Station.from("남부터미널역"), 5, 5, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("minTimeResultProvider")
    void 최소시간_경로_테스트(Station startStation, Station endStation, int minDistance, int time, int size) {
        Result result = RouteRepository.getMinTimeRoute(startStation, endStation);

        assertThat(result.distance()).isEqualTo(minDistance);
        assertThat(result.time()).isEqualTo(time);
        assertThat(result.stations().size()).isEqualTo(size);
    }

    static Stream<Arguments> minTimeResultProvider() {
        return Stream.of(
                Arguments.of(Station.from("교대역"), Station.from("양재역"), 9, 7, 3),
                Arguments.of(Station.from("교대역"), Station.from("강남역"), 2, 3, 2),
                Arguments.of(Station.from("교대역"), Station.from("매봉역"), 10, 8, 4),
                Arguments.of(Station.from("교대역"), Station.from("양재시민의숲역"), 19, 10, 4),
                Arguments.of(Station.from("양재역"), Station.from("교대역"), 9, 7, 3),
                Arguments.of(Station.from("양재역"), Station.from("역삼역"), 4, 11, 3),
                Arguments.of(Station.from("양재역"), Station.from("남부터미널역"), 6, 5, 2),
                Arguments.of(Station.from("매봉역"), Station.from("역삼역"), 5, 12, 4),
                Arguments.of(Station.from("매봉역"), Station.from("강남역"), 3, 9, 3),
                Arguments.of(Station.from("강남역"), Station.from("남부터미널역"), 5, 5, 3)
        );
    }

    @Test
    void 두_역이_이어져_있지_않을때_오류() {
        Station newFirstStation = Station.from("잠실역");
        Station newSecondStation = Station.from("송파역");

        StationRepository.addStation(newFirstStation);
        StationRepository.addStation(newSecondStation);
        RouteRepository.addStation(newFirstStation);
        RouteRepository.addStation(newSecondStation);
        RouteRepository.addRoute(newFirstStation, newSecondStation, 3, 5);

        Station startStation = StationRepository.getStation("교대역");
        Station endStation = StationRepository.getStation("잠실역");

        assertThatThrownBy(() -> RouteRepository.getMinDistanceRoute(startStation, endStation))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NO_CONNECTED_STATIONS.getErrorMessage());
        assertThatThrownBy(() -> RouteRepository.getMinTimeRoute(startStation, endStation))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NO_CONNECTED_STATIONS.getErrorMessage());
    }
}
