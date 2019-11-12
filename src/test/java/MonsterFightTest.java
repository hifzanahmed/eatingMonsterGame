import org.junit.Before;
import org.junit.Test;

public class MonsterFightTest {
    private MonsterFight monsterFight = null;
    @Before
    public void setup(){
        monsterFight = new MonsterFight();
    }

    //Single Run for gameLogic
    @Test
    public void gameLogic() {
        monsterFight.play();
    }

}