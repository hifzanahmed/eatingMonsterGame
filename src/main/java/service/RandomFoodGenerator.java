package service;

import java.util.ArrayList;
import java.util.Random;

public class RandomFoodGenerator {
    private static Random random = new Random();

    public static int generateRandomFood() {
        return random.nextInt(3);
    }
    //generate the calories packet in each round
    public static ArrayList<Integer> generateFood() {
        ArrayList<Integer> foodList = new ArrayList<>();
        //System.out.print("GenerateFood :");
        for (int i = 0; i < 5; i++) {
            //int twentyPercent = (int) (100 * Math.random());
            if (random.nextInt(100) <= 20) {
                foodList.add(generateRandomFood() * -1);
            } else {
                foodList.add(generateRandomFood());
            }
        }
        //System.out.println(foodList);
        return foodList;
    }
}
