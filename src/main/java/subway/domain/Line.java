package subway.domain;

public class Line {
    private final String name;

    public Line(String name) {
        this.name = name;
    }

    public static Line from(String name) {
        return new Line(name);
    }

    public String getName() {
        return name;
    }
}
