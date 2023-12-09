package ru.itis.inf301.lab12.expedition;

public class Car {
    public static float P = 0.6f;
    public static int MAX_SEATS = 5;
    private int takenSeats = 2;

    public int getTakenSeats() {
        return takenSeats;
    }

    public void setTakenSeats(int takenSeats) {
        this.takenSeats = takenSeats;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    private boolean working = true;

    public boolean willWork() {
        return !(Math.random() < P);
    }

}
