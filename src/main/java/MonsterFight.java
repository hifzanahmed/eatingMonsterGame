import bean.Monster;
import service.MonsterService;

import java.util.Comparator;
import java.util.List;

import static service.MonsterService.*;

public class MonsterFight {
    public static void main(String[] args) {
        MonsterFight monsterFight = new MonsterFight();
            monsterFight.play();
    }
    public void play(){
        boolean lastMonster = true;
        //Generate Monsters
        List<Monster> monsters = generateMonsters();
        MonsterService monsterService = new MonsterService();

        do {
            monsters.sort(Comparator.comparing(Monster::getScore).reversed());
            //Generate Header for each round
            eachRoundHeaderPrint();
            //perform each round to feed monsters and stole food/packet in each round
            monsterService.feedCalories();

            int aliveCount = 0;
            int winnerMonster = 0;
            int winnerMonsterCalories = 0;
            for (Monster monster : monsters) {
                if (monster.isAlive()) {
                    aliveCount++;
                    winnerMonster = monster.getMonsterNumber();
                    winnerMonsterCalories = monster.getScore();
                }
            }
            if (aliveCount == 1) {
                lastMonster = false;
                eachRoundHeaderPrint();
                System.out.println("The winner is monster " + winnerMonster + " with " + winnerMonsterCalories + " calories left");
            }
        } while (lastMonster);
    }
}
