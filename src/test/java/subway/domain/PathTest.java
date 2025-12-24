package subway.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PathTest {

    @Test
    void createPath() {
        // given
        Station station1 = new Station("교대역");
        Station station2 = new Station("강남역");
        List<Station> stations = Arrays.asList(station1, station2);
        int totalDistance = 2;
        int totalDuration = 3;

        // when
        Path path = new Path(stations, totalDistance, totalDuration);

        // then
        assertThat(path.getStations()).hasSize(2);
        assertThat(path.getTotalDistance()).isEqualTo(2);
        assertThat(path.getTotalDuration()).isEqualTo(3);

    }
}
