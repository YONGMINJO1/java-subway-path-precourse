package subway.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;

public class SectionTest {

    @Test
    void createSection() {
        // given
        Station upStation = new Station("교대역");
        Station downStation = new Station("강남역");
        int distance = 2;
        int duration = 3;

        // when
        Section section = new Section(upStation, downStation, distance, duration);

        // then
        assertThat(section.getUpStation()).isEqualTo(upStation);
        assertThat(section.getDownStation()).isEqualTo(downStation);
        assertThat(section.getDistance()).isEqualTo(distance);
        assertThat(section.getDuration()).isEqualTo(duration);
    }
}
