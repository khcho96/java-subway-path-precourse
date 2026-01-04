package subway.domain;

import java.util.Objects;

public class Station {

    private final String name;

    public Station(String name) {
        this.name = name;
    }

    public static Station from(String name) {
        return new Station(name);
    }
    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Station station = (Station) object;
        return Objects.equals(name, station.name);
    }

    public String getName() {
        return name;
    }
}
