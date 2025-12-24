package subway.domain;

import java.util.List;

public class Path {
    private final List<Station> stations;
    private final int totalDistance;
    private final int totalDuration;

    public Path(List<Station> stations, int totalDistance, int totalDuration) {
        this.stations = stations;
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
    }

    public List<Station> getStations() {
        return stations;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getTotalDuration() {
        return totalDuration;
    }
}
