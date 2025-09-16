package traffic;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CircularQueue {
    private final List<String> roads;
    private final int size;

    public  CircularQueue(int size) {
        this.size = size;
        roads = new LinkedList<>();
    }

    public Optional<String> enqueue(String road) {
        if (roads.size() == size) {
            return Optional.empty();
        }
        roads.addLast(road);
        return Optional.of(road);
    }

    public Optional<String> dequeue() {
        return roads.isEmpty() ? Optional.empty() : Optional.of(roads.removeFirst());
    }

    public List<String> getRoads() {
        return roads;
    }
}
