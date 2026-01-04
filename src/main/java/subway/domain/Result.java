package subway.domain;

import java.util.List;

public record Result(List<String> stations, int distance, int time) {
}
