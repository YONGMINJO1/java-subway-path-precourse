package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.domain.DataInitializer;
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
