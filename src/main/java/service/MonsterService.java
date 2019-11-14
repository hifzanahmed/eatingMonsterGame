package service;

import bean.Monster;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static service.RandomFoodGenerator.generateFood;

public class MonsterService {
    static List<Monster> monsters = new ArrayList<>(5);
    static Random random = new Random();

    //Initialize all monster first time
    public static List<Monster> generateMonsters() {
        for (int i = 0; i < 5; i++) {
            monsters.add(new Monster(i, true, 5, 0, false));
        }
        return monsters;
    }

    //feed Calories in each round
    public void feedCalories() {
        ArrayList<Integer> caloriesList = generateFood();
        //Logic to steaL the food
        int thiefMonster = stealFood(caloriesList);

        for (int i = 0; i < 5; i++) {
            //check if monster died or alive
            if (monsters.get(i).isAlive() && (thiefMonster != monsters.get(i).getMonsterNumber())) {
                //check if calories packet is posioned (each round)
                if (caloriesList.get(i) < 0) {
                    monsters.get(i).setScore(caloriesList.get(i) + monsters.get(i).getScore());
                    monsters.get(i).setCaloriesInEachRound(caloriesList.get(i));
                    monsters.get(i).setThief(false);

                    if (monsters.get(i).getScore() > 0) {
                        System.out.println("Oh no, monster " + monsters.get(i).getMonsterNumber() + " was poisoned: " + (monsters.get(i).getScore()) + " calories left");
                    } else {
                        printMonsterDeadMessage(i);
                        monsters.get(i).setAlive(false);
                        monsters.get(i).setThief(false);
                    }

                } else {
                    //-1 calories in each round and add/substract packet calories
                    monsters.get(i).setScore(caloriesList.get(i) + monsters.get(i).getScore() - 1);
                    monsters.get(i).setCaloriesInEachRound(caloriesList.get(i));
                    monsters.get(i).setThief(false);
                    if (monsters.get(i).getScore() <= 0) {
                        printMonsterDeadMessage(i);
                        monsters.get(i).setAlive(false);
                        monsters.get(i).setThief(false);
                    }
                }

            }
        }
        //System.out.println(monsters.get(i));
    }

    private void printMonsterDeadMessage(int i) {
        System.out.println("Oh no, monster " + monsters.get(i).getMonsterNumber() + " was poisoned and died");
        System.out.println("Monster " + monsters.get(i).getMonsterNumber() + " RIP");
    }

    public int stealFood(ArrayList<Integer> caloriesList) {
        boolean stealFoodLogicFlag = true;
        //get the thief Monster, one for each round
        int thiefMonster = getThiefMonster();
        //System.out.println("the thief Monster1 :" + thiefMonster);
        //stole food logic , monster can stole from any other alive monster in each round
        for (int i = 0; i < 5; i++) {
            //check if monster died or alive
            if (monsters.get(i).isAlive()) {
                if (stealFoodLogicFlag && (thiefMonster == monsters.get(i).getMonsterNumber())) {
                    //System.out.println("the thief Monster2 :" + thiefMonster);
                    //get the random alive monster number and random monster should not be similar to current monster number
                    int monsterToBeRobbed = getVictimMonster(monsters.get(i).getMonsterNumber());
                    //System.out.print("Current Monster : " + monsters.get(i).getMonsterNumber());
                    //System.out.println(" Random Monster : " + monsterToBeRobbed);
                    System.out.println("Monster " + monsters.get(i).getMonsterNumber() + " stole food from monster " + monsterToBeRobbed);
                    for (int j = 0; j < 5; j++) {
                        if (monsterToBeRobbed == monsters.get(j).getMonsterNumber()) {
                            //set the stolen, store positive calories to current Monster (score/calories) and remove the calories from score/calories stolen monster
                            //also add the calories feed in each round
                            if (monsters.get(j).getCaloriesInEachRound() >= 0) {
                                //set the current monster values
                                monsters.get(i).setScore(monsters.get(j).getCaloriesInEachRound() + monsters.get(i).getScore() + caloriesList.get(i) - 1);
                                monsters.get(i).setCaloriesInEachRound(caloriesList.get(i));
                                //monsters.get(i).setCaloriesInEachRound(monsters.get(j).getCaloriesInEachRound());
                                //remove the random monster values
                                monsters.get(j).setScore(monsters.get(j).getScore() - monsters.get(j).getCaloriesInEachRound());
                                monsters.get(j).setCaloriesInEachRound(0);
                                if (monsters.get(i).getScore() <= 0) {
                                    printMonsterDeadMessage(i);
                                    monsters.get(i).setAlive(false);
                                }
                            } else {
                                int roundCalories = caloriesList.get(i);
                                //making current cal packet negative in case of stolen packed is poisoned
                                if (caloriesList.get(i) > 0) {
                                    roundCalories = (caloriesList.get(i) * -1);
                                }
                                monsters.get(i).setScore(monsters.get(j).getCaloriesInEachRound() + monsters.get(i).getScore() + roundCalories - 1);
                                monsters.get(j).setScore(monsters.get(j).getScore() - monsters.get(j).getCaloriesInEachRound());
                                monsters.get(j).setCaloriesInEachRound(0);
                                if (monsters.get(i).getScore() > 0) {
                                    System.out.println("Oh no, monster " + monsters.get(i).getMonsterNumber() + " was poisoned: " + (monsters.get(i).getScore()) + " calories left");
                                } else {
                                    printMonsterDeadMessage(i);
                                    monsters.get(i).setAlive(false);
                                }
                            }
                        }
                    }
                    stealFoodLogicFlag = false;
                }
            }
        }
        return thiefMonster;
    }

    public int getThiefMonster() {
        int thiefMonster = 0;
        boolean thiefMonsterFlag = true;
        int randomNumber;
        do {
            randomNumber = random.nextInt(5);
            for (int i = 0; i < 5; i++) {
                if (monsters.get(i).isAlive() && (monsters.get(i).getMonsterNumber() == randomNumber) && (!monsters.get(i).isThief())) {
                    monsters.get(i).setThief(true);
                    thiefMonster = randomNumber;
                    thiefMonsterFlag = false;
                }
            }
        } while (thiefMonsterFlag);
        return thiefMonster;
    }

    public int getVictimMonster(int currentMonster) {
        int victimMonster = 0;
        boolean monsterToStoleFlag = true;
        int randomNumber;
        do {
            randomNumber = random.nextInt(5);
            for (int i = 0; i < 5; i++) {
                if ((monsters.get(i).getMonsterNumber() != currentMonster) && monsters.get(i).isAlive() && (monsters.get(i).getMonsterNumber() == randomNumber)) {
                    victimMonster = randomNumber;
                    monsterToStoleFlag = false;
                }
            }
        } while (monsterToStoleFlag);
        return victimMonster;
    }

    //Display header in each round
    public static void eachRoundHeaderPrint() {
        List<Monster> monsterList = new ArrayList<>(monsters);
        monsterList.sort(Comparator.comparing(Monster::getMonsterNumber));
        monsterList.forEach(monster -> {
            System.out.printf("monster %d(%s) ", monster.getMonsterNumber(), getScoreFormat(monster.getScore()));
        });
        System.out.println();
    }

    public static String getScoreFormat(int score) {
        if (score <= 0) {
            return "X";
        }
        return Integer.toString(score);
    }
}

