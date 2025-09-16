package traffic;

public class Simulator {
    UserInterface ui;
    TrafficLight light;
    CircularQueue queue;
    ProgramState state;
    public Simulator() {
        ui = new CLInterface();
        var nums = ui.trafficLight();
        light = new TrafficLight(this, nums);
        state = ProgramState.MENU;
        queue = new CircularQueue(light.getRoadCount());
    }

    public void run() {
        switch (ui.choose()) {
            case MenuOption.QUIT -> {
                light.stop(); ui.close(); return;
            }
            case MenuOption.ADD_ROAD ->
                    ui.addRoad(queue.enqueue(ui.roadName()));
            case MenuOption.DELETE_ROAD ->
                    ui.deleteRoad(queue.dequeue());
            case MenuOption.OPEN_SYSTEM -> {
                state = ProgramState.SYSTEM;
                ui.showState(light, queue);
                ui.clearLine();
                state = ProgramState.MENU;
            }
        }
        run();
    }
}

enum ProgramState {
    MENU, SYSTEM
}