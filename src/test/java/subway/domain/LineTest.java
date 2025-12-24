package subway.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class LineTest {
    @Test
    void addSection() {
        // given - 준비?
        Line line = new Line("2호선");

        Station upStation = new Station("교대역");
        Station downStation = new Station("강남역");
        Section section = new Section(upStation, downStation, 2, 3);

        // when - 실행?
        line.addSection(section); // 매서드 생성 전엔 빨간색

        // then - 겅증?
        List<Section> sections = line.getSections(); // 매서드 생성 전엔 빨간색
        assertThat(sections).hasSize(1);
        assertThat(sections.get(0)).isEqualTo(section);
    }
}
