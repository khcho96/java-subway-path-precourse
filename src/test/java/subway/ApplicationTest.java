package subway;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import subway.constant.ErrorMessage;

class ApplicationTest extends NsTest {

    @Test
    void 메인화면_기능_선택_오류() {
        assertSimpleTest(
                () -> {
                    runException("2");
                    assertThat(output()).contains(ErrorMessage.FORMAT_ERROR.getErrorMessage());
                }
        );
    }

    @Test
    void 경로조회화면_기능_선택_오류() {
        assertSimpleTest(
                () -> {
                    runException("1", "3");
                    assertThat(output()).contains(ErrorMessage.FORMAT_ERROR.getErrorMessage());
                }
        );
    }

    @Test
    void 없는_역_선택_오류() {
        assertSimpleTest(
                () -> {
                    runException("1", "1", "잠실역");
                    assertThat(output()).contains(ErrorMessage.NO_EXIST_STATION.getErrorMessage());
                }
        );
    }

    @ParameterizedTest
    @MethodSource("minDistanceResultProvider")
    void 최단거리_경로_구하기(String startStation, String endStation, int distance, int time) {
        assertSimpleTest(
                () -> {
                    runException("1", "1", startStation, endStation);
                    assertThat(output()).contains(distance + "km");
                    assertThat(output()).contains(time + "분");
                }
        );
    }

    static Stream<Arguments> minDistanceResultProvider() {
        return Stream.of(
                Arguments.of("교대역",  "양재역", 4, 11),
                Arguments.of("교대역",  "강남역", 2, 3),
                Arguments.of("교대역",  "매봉역", 5, 12),
                Arguments.of("교대역",  "양재시민의숲역", 14, 14),
                Arguments.of("양재역",  "교대역", 4, 11),
                Arguments.of("양재역",  "역삼역", 4, 11),
                Arguments.of("양재역",  "남부터미널역", 6, 5),
                Arguments.of("매봉역",  "역삼역", 5, 12),
                Arguments.of("매봉역",  "강남역", 3, 9),
                Arguments.of("강남역",  "남부터미널역", 5, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("minTimeResultProvider")
    void 최소거리_경로_구하기(String startStation, String endStation, int distance, int time) {
        assertSimpleTest(
                () -> {
                    runException("1", "2", startStation, endStation);
                    assertThat(output()).contains(distance + "km");
                    assertThat(output()).contains(time + "분");
                }
        );
    }

    static Stream<Arguments> minTimeResultProvider() {
        return Stream.of(
                Arguments.of("교대역",  "양재역", 9,7),
                Arguments.of("교대역",  "강남역", 2, 3),
                Arguments.of("교대역",  "매봉역", 10,8),
                Arguments.of("교대역",  "양재시민의숲역", 19, 10),
                Arguments.of("양재역",  "교대역", 9, 7),
                Arguments.of("양재역",  "역삼역", 4, 11),
                Arguments.of("양재역",  "남부터미널역", 6, 5),
                Arguments.of("매봉역",  "역삼역", 5, 12),
                Arguments.of("매봉역",  "강남역", 3, 9),
                Arguments.of("강남역",  "남부터미널역", 5, 5)
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}