package traffic;

import java.util.Optional;

public interface UserInterface {
    int[] trafficLight();

    void showState(TrafficLight light, CircularQueue queue);

    MenuOption choose();

    void close();

    void deleteRoad(Optional<String> road);

    void addRoad(Optional<String> road);

    String roadName();

    void clearLine();

}
