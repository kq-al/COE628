package coe628.lab9;

public class Main {
    static final int NUM_PHILOSOPHERS = 5;
    static DiningPhilosophers[] philosophers = new DiningPhilosophers[NUM_PHILOSOPHERS];
    static fork[] forks = new fork[NUM_PHILOSOPHERS];
    
    public static void main(String[] args) {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new fork(i);
        }
        
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            fork leftfork = forks[i];
            fork rightfork = forks[(i + 1) % 5];
            if (i == NUM_PHILOSOPHERS - 1) {
                philosophers[i] = new DiningPhilosophers(i, rightfork, leftfork);
            } else {
                philosophers[i] = new DiningPhilosophers(i, leftfork, rightfork);
            }
            new Thread(philosophers[i]).start();
        }
    }
}