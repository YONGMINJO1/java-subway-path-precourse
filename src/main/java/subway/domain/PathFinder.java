package subway.domain;

import java.util.List;
import java.util.stream.Collectors;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class PathFinder {
    public Path findPath(Station source, Station target, boolean isDistanceBased) {
        // 1. 그래프 생성
        WeightedMultigraph<String, DefaultWeightedEdge> graph =
                new WeightedMultigraph<>(DefaultWeightedEdge.class);

        // 2. 모든 역을 정점으로 추가
        for (Line line : LineRepository.lines()) {
            for (Section section : line.getSections()) {
                graph.addVertex(section.getUpStation().getName());
                graph.addVertex(section.getDownStation().getName());
            }
        }

        // 3. 모든 구간을 간선으로 추가
        for (Line line : LineRepository.lines()) {
            for (Section section : line.getSections()) {
                String up = section.getUpStation().getName();
                String down = section.getDownStation().getName();

                int weight = isDistanceBased ?
                        section.getDistance() : section.getDuration();

                DefaultWeightedEdge edge = graph.addEdge(up, down);
                if (edge != null) {
                    graph.setEdgeWeight(edge, weight);
                }
            }
        }

        // 4. 최단 경로 찾기
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra =
                new DijkstraShortestPath<>(graph);

        List<String> stationNames = dijkstra
                .getPath(source.getName(), target.getName())
                .getVertexList();

        // 5. 경로의 Station 객체 변환
        List<Station> pathStations = stationNames.stream()
                .map(name -> StationRepository.stations().stream()
                        .filter(station -> station.getName().equals(name))
                        .findFirst()
                        .orElseThrow())
                .collect(Collectors.toList());

        // 6. 총 거리, 총 시간 계산
        int totalDistance = calculateTotalDistance(pathStations);
        int totalDuration = calculateTotalDuration(pathStations);

        return new Path(pathStations, totalDistance, totalDuration);
    }

    private int calculateTotalDistance(List<Station> stations) {
        int total = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            Section section = findSection(stations.get(i), stations.get(i + 1));
            total += section.getDistance();
        }
        return total;
    }

    private int calculateTotalDuration(List<Station> stations) {
        int total = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            Section section = findSection(stations.get(i), stations.get(i + 1));
            total += section.getDuration();
        }
        return total;
    }

    private Section findSection(Station up, Station down) {
        for (Line line : LineRepository.lines()) {
            for (Section section : line.getSections()) {
                if (section.getUpStation().equals(up) &&
                        section.getDownStation().equals(down)) {
                    return section;
                }
            }
        }
        throw new IllegalArgumentException("구간을 찾을 수 없습니다.");
    }
}
