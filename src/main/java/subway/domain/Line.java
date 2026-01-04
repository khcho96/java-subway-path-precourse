package subway.domain;

import java.util.Objects;

public class Line {
    private final String name;

    public Line(String name) {
        this.name = name;
    }

    public static Line from(String name) {
        return new Line(name);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Line line = (Line) object;
        return Objects.equals(name, line.name);
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
}
