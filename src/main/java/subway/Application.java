package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.domain.DataInitializer;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Section;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.view.InputView;

public class Application {
    public static void main(String[] args) {
        DataInitializer.initialize();
        final Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        SubwayController controller = new SubwayController(inputView);

        controller.run();
    }
}
