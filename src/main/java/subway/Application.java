package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Section;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.view.InputView;

public class Application {
    public static void main(String[] args) {
        initializeData();
        final Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        SubwayController controller = new SubwayController(inputView);

        controller.run();
    }

    private static void initializeData() {
        // 1. 역 생성 (7개)
        Station 교대역 = new Station("교대역");
        Station 강남역 = new Station("강남역");
        Station 역삼역 = new Station("역삼역");
        Station 남부터미널역 = new Station("남부터미널역");
        Station 양재역 = new Station("양재역");
        Station 양재시민의숲역 = new Station("양재시민의숲역");
        Station 매봉역 = new Station("매봉역");

        // 2. 역 등록
        StationRepository.addStation(교대역);
        StationRepository.addStation(강남역);
        StationRepository.addStation(역삼역);
        StationRepository.addStation(남부터미널역);
        StationRepository.addStation(양재역);
        StationRepository.addStation(양재시민의숲역);
        StationRepository.addStation(매봉역);

        // 3. 2호선 생성 및 구간 추가
        Line line2 = new Line("2호선");
        line2.addSection(new Section(교대역, 강남역, 2, 3));
        line2.addSection(new Section(강남역, 역삼역, 2, 3));

        // 4. 3호선 생성 및 구간 추가
        Line line3 = new Line("3호선");
        line3.addSection(new Section(교대역, 남부터미널역, 3, 2));
        line3.addSection(new Section(남부터미널역, 양재역, 6, 5));
        line3.addSection(new Section(양재역, 매봉역, 1, 1));
        
        // 5. 신분당선 생성 및 구간 추가
        Line 신분당선 = new Line("신분당선");
        신분당선.addSection(new Section(강남역, 양재역, 2, 8));
        신분당선.addSection(new Section(양재역, 양재시민의숲역, 10, 3));
        
        // 6. 노선 등록
        LineRepository.addLine(line2);
        LineRepository.addLine(line3);
        LineRepository.addLine(신분당선);

    }
}
