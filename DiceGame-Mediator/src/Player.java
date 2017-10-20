import java.util.ArrayList;

public interface Player {

    ArrayList<Dice> getDiceNumbers();
    ArrayList<Integer> getPlayerRowsNumbers();
    ArrayList<Integer> selectPlayerRows();
    void rollDice();
    Move makeMove();


}
