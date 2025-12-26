package subway;

import java.util.Scanner;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Path;
import subway.domain.PathFinder;
import subway.domain.Section;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.view.InputView;
import subway.view.OutputView;

public class Application {
    public static void main(String[] args) {
        initializeData();
        final Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        PathFinder pathFinder = new PathFinder();

        while (true) {
            OutputView.printMainMenu();
            String choice = inputView.readMenu();

            if (choice.equals("Q")) {
                break;
            }

            if (choice.equals("1")) {
                handlePathSearch(inputView, pathFinder);
            }
        }
    }

    private static void handlePathSearch(InputView inputView, PathFinder pathFinder) {
        try {
            OutputView.printPathCriteriaMenu();
            String criteria = inputView.readMenu();

            if (criteria.equals("B")) {
                return;
            }

            String sourceName = inputView.readStation("## 출발역을 입력하세요.");
            Station source = findStationByName(sourceName);
            String targetName = inputView.readStation("## 도착역을 입력하세요.");
            //Station source = findStationByName(sourceName);
            Station target = findStationByName(targetName);

            boolean isDistance = criteria.equals("1");
            Path path = pathFinder.findPath(source, target, isDistance);
            OutputView.printResult(path);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }

    }

    private static Station findStationByName(String name) {
        return StationRepository.stations().stream()
                .filter(station -> station.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 역 입니다."));
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
