package subway.controller;

import subway.domain.Path;
import subway.domain.PathFinder;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {
    private final InputView inputView;
    private final PathFinder pathFinder;

    public SubwayController(InputView inputView) {
        this.inputView = inputView;
        this.pathFinder = new PathFinder();
    }

    public void run() {
        while (true) {
            OutputView.printMainMenu();
            String choice = inputView.readMenu();

            if (choice.equals("Q")) {
                break;
            }
            if (choice.equals("1")) {
                handlePathSearch();
            }
        }
    }
    private void handlePathSearch() {
        try {
            OutputView.printPathCriteriaMenu();
            String criteria = inputView.readMenu();

            if (criteria.equals("B")) {
                return;
            }

            String sourceName = inputView.readStation("## 출발역을 입력하세요.");
            Station source = findStationByName(sourceName);

            String targetName = inputView.readStation("## 도착역을 입력하세요.");
            Station target = findStationByName(targetName);

            boolean isDistance = criteria.equals("1");
            Path path = pathFinder.findPath(source, target, isDistance);
            OutputView.printResult(path);

        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }

    private Station findStationByName(String name) {
        return StationRepository.stations().stream()
                .filter(station -> station.getName().equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 역 입니다."));
    }
}
