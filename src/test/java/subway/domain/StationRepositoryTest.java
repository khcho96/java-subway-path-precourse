package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import subway.constant.ErrorMessage;

class StationRepositoryTest {

    @BeforeEach
    void setUp() {
        StationRepository.deleteAll();
    }

    @Test
    void 등록된_역_찾기_성공() {
        List<Station> stations = List.of(Station.from("강남역"), Station.from("역삼역"));

        for (Station station : stations) {
            StationRepository.addStation(station);
        }

        Station findStation = Station.from("강남역");
        assertThat(StationRepository.getStation("강남역")).isEqualTo(findStation);
    }

    @Test
    void 등록된_역_찾기_실패() {
        List<Station> stations = List.of(Station.from("강남역"), Station.from("교대역"));

        for (Station station : stations) {
            StationRepository.addStation(station);
        }

        assertThatThrownBy(() -> StationRepository.getStation("역삼역"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ErrorMessage.NO_EXIST_STATION.getErrorMessage());
    }
}