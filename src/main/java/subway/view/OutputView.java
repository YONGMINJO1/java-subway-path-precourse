package subway.view;

import subway.domain.Path;
import subway.domain.Station;

public class OutputView {

    public static void printMainMenu() {
        System.out.println("## 메인 화면");
        System.out.println("1. 경로 조회");
        System.out.println("Q. 종료");
        System.out.println();
    }

    public static void printPathCriteriaMenu() {
        System.out.println("## 경로 기준");
        System.out.println("1. 최단 거리");
        System.out.println("2. 최소 시간");
        System.out.println("B. 돌아가기");
        System.out.println();
    }

    public static void printResult(Path path) {
        System.out.println("## 조회 결과");
        System.out.println("[INFO] ---");
        System.out.println("[INFO] 총 거리: " + path.getTotalDistance() + "km");
        System.out.println("[INFO] 총 소요 시간: " + path.getTotalDuration() + "분");
        System.out.println("[INFO] ---");

        for (Station station : path.getStations()) {
            System.out.println("[INFO] " + station.getName());
        }
        System.out.println();
    }

    public static void printError(String message) {
        System.out.println("[ERROR] " + message );
    }
}
