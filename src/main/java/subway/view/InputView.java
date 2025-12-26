package subway.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readMenu() {
        System.out.println("## 원하는 기능을 선택하세요.");
        return scanner.nextLine();
    }

    public String readStation(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
