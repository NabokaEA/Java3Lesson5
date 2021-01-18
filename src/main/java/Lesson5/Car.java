package Lesson5;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable{
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private String name;
    private int speed;
    private CyclicBarrier cyclicBarrier;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier cyclicBarrier1) {
        this.race = race;
        this.speed = speed;
        this.cyclicBarrier=cyclicBarrier1;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
            Thread.sleep(2);
            System.out.println("Поток " + this.name + " запустился");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }
}
