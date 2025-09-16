package traffic;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.IntPredicate;

public class CLInterface implements UserInterface{
    public static final String MENU = """
            Menu:
            1. Add road
            2. Delete road
            3. Open system
            0. Quit""";
    public static final String SYSTEM = """
            ! %ds. have passed since system startup !
            ! Number of roads: %d !
            ! Interval: %d !
            
            %s
            
            ! Press "Enter" to open menu !""";
    static Scanner scan = new Scanner(System.in);

    @Override
    public int[] trafficLight() {
        System.out.println("Welcome to the traffic management system!");
        System.out.print("Input the number of roads: ");
        int roads = feedback(n -> n > 0, "Input");
        System.out.print("Input the interval: ");
        int interval = feedback(n -> n > 0, "Input");
        clearConsole();
        return new int[]{roads, interval};
    }

    @Override
    public void showState(TrafficLight light,  CircularQueue queue) {
        clearConsole();
        String roads = String.join("\n", queue.getRoads());
        String system = String.format(SYSTEM, light.getSeconds(), light.getRoadCount(), light.getInterval(), roads);
        System.out.println(system);
    }

    @Override
    public MenuOption choose() {
        System.out.println(MENU);
        while (true) {
            var x = readInt();
            if (x.isEmpty()) {
                System.out.print("incorrect option: ");
                scan.nextLine();
                System.out.println(MENU);
            } else if (x.get() < 0 || x.get() > 3) {
                System.out.println("incorrect option: ");
                scan.nextLine();
                System.out.println(MENU);
            } else {
                return MenuOption.values()[x.get()];
            }
        }
    }

    @Override
    public void close() {
        scan.close();
        System.out.println("Bye!");
    }

    @Override
    public void deleteRoad(Optional<String> road) {
        if(road.isPresent()) {
            System.out.printf("%s deleted!\n", road.get());
            scan.nextLine();
        } else {
            System.out.println("queue is empty");
            scan.nextLine();
        }
    }

    @Override
    public void addRoad(Optional<String> road) {
        if(road.isPresent()) {
            System.out.printf("%s added!\n", road.get());
            scan.nextLine();
        } else {
            System.out.println("Queue is full");
            scan.nextLine();
        }
    }

    @Override
    public String roadName() {
        System.out.print("Input the road name: ");
        return scan.nextLine();
    }

    @Override
    public void clearLine() {
        scan.nextLine();
    }

    private static Optional<Integer> readInt() {
        try {
            return Optional.of(Integer.parseInt(scan.nextLine()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private static int feedback(IntPredicate predicate, String message) {
        try {
            int number = Integer.parseInt(scan.nextLine());
            if (predicate.test(number)) {
                return number;
            }
        } catch (NumberFormatException ignored) {

        }
        System.out.printf("Error! Incorrect %s. Try again: ", message);
        return feedback(predicate, message);
    }

    static void clearConsole() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ignored) {
        }
    }
}
