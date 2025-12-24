package subway.domain;

public class Section {
    private Station upStation;
    private Station downStation;
    private int distance;
    private int duration;

    public Section(Station upStation, Station downStation, int distance, int duration) {
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance = distance;
        this.duration = duration;
    }

    public Station getUpStation() {
        return upStation;
    }

    public Station getDownStation() {
        return downStation;
    }

    public int getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }
}
