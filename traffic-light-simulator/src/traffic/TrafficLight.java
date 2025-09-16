package traffic;

public class TrafficLight {
    int roadCount;
    int interval;
    volatile int seconds;
    Thread timerThread;
    volatile boolean stopped = false;
    private final Simulator simulator;
    public TrafficLight(Simulator simulator, int[] nums) {
        this.simulator = simulator;
        this.roadCount = nums[0];
        this.interval = nums[1];
        this.seconds = 0;
        timerThread = new Thread(() -> {
            try {
                while(!stopped) {
                    Thread.sleep(1000L);
                    seconds++;
                    if (simulator.state == ProgramState.SYSTEM) {
                        simulator.ui.showState(this, simulator.queue);
                    }

                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "QueueThread");

        timerThread.start();
    }

    public int getRoadCount() {
        return roadCount;
    }

    public int getInterval() {
        return interval;
    }

    public int getSeconds() {
        return seconds;
    }

    public void stop() {
        stopped = true;
    }
}
