package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PathFinderTest {

    private PathFinder pathFinder;
    private Station 교대역;
    private Station 강남역;
    private Station 역삼역;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 준비
        교대역 = new Station("교대역");
        강남역 = new Station("강남역");
        역삼역 = new Station("역삼역");

        // Repository 초기화
        StationRepository.deleteAll();
        LineRepository.deleteAll();

        // 역 등록 ← 추가!
        StationRepository.addStation(교대역);
        StationRepository.addStation(강남역);
        StationRepository.addStation(역삼역);

        Line line2 = new Line("2호선");
        line2.addSection(new Section(교대역, 강남역, 2, 3));
        line2.addSection(new Section(강남역, 역삼역, 2, 3));
        LineRepository.addLine(line2);

        pathFinder = new PathFinder();
    }

    @Test
    void findShortestPathByDistance() {
        // when
        Path path = pathFinder.findPath(교대역, 강남역, true);

        // then
        assertThat(path.getStations()).containsExactly(교대역, 강남역);
        assertThat(path.getTotalDistance()).isEqualTo(2);
        assertThat(path.getTotalDuration()).isEqualTo(3);
    }

    @Test
    void findShortestPathByDuration() {
        // when
        Path path = pathFinder.findPath(교대역, 역삼역, false);

        // then
        assertThat(path.getStations())
                .containsExactly(교대역, 강남역, 역삼역);
        assertThat(path.getTotalDistance()).isEqualTo(4);
        assertThat(path.getTotalDuration()).isEqualTo(6);
    }

}
