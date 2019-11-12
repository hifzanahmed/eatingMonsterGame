package bean;

public class Monster {
    private int monsterNumber;
    private boolean isAlive;
    private int score;
    private int caloriesInEachRound;
    private boolean isThief;

    public Monster(int monsterNumber, boolean isAlive, int score, int caloriesInEachRound, boolean isThief) {
        this.monsterNumber = monsterNumber;
        this.isAlive = isAlive;
        this.score = score;
        this.caloriesInEachRound = caloriesInEachRound;
        this.isThief = isThief;
    }

    public boolean isThief() {
        return isThief;
    }

    public void setThief(boolean thief) {
        isThief = thief;
    }

    public int getMonsterNumber() {
        return monsterNumber;
    }

    public void setMonsterNumber(int monsterNumber) {
        this.monsterNumber = monsterNumber;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCaloriesInEachRound() {
        return caloriesInEachRound;
    }

    public void setCaloriesInEachRound(int caloriesInEachRound) {
        this.caloriesInEachRound = caloriesInEachRound;
    }

    public String toString() {
        if (isAlive) {
            return "MonsterNumber:"+monsterNumber + " isAlive: "+ isAlive + " Score: "+ score + " poisonedscore:"+ caloriesInEachRound;
        } else {
            return "Monster " + monsterNumber + " RIP";
        }
    }
}
