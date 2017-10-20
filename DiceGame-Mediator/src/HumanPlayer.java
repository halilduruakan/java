import java.util.ArrayList;
import java.util.Scanner;


public class HumanPlayer implements Player {
    private Scanner reader = new Scanner(System.in);        //to get input
    private String playerName;                                    //player playerName
    private ArrayList<Dice> playerDices = new ArrayList<>();        //players dices list
    private ArrayList<Integer> playerRowsNumbers = new ArrayList<>();   //player selected rows

    public HumanPlayer(String name){
        try{
            setPlayerName(name);//set name
            createDices();      //create four dices

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    //create player dices
    private void createDices(){
        int index;
        for (index=0 ; index<4 ; index++){
            playerDices.add(new Dice());
        }
    }

    //get players dices numbers
    public ArrayList<Dice> getDiceNumbers(){
        return playerDices;
    }

    //add players dice to a random number
    public void rollDice(){
        for(Dice dice : playerDices){
            dice.setRandomInt();
        }
    }

    //get players rows numbers
    public ArrayList<Integer> getPlayerRowsNumbers(){
        return this.playerRowsNumbers;
    }

    //check and set players rows numbers
    private void setPlayerRowsNumbers(Integer firstRow,Integer secondRow) {
        if(!playerRowsNumbers.isEmpty()) {
            playerRowsNumbers.set(0,firstRow);
            playerRowsNumbers.set(1,secondRow);
        }
        else {
            playerRowsNumbers.add(firstRow);
            playerRowsNumbers.add(secondRow);
        }
    }

    private void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    //first round all player select their rows
    public ArrayList<Integer> selectPlayerRows() {
        //we get rows as pair
        Integer[] firstRowDiceNumber = new Integer[2];
        Integer[] secondRowDiceNumber = new Integer[2];

        System.out.println("This round for select your row number.");
        System.out.println(this.toString() + ", it is your tern !");
        System.out.print("Your dices are :");
        playerDices.forEach((dice) -> System.out.print(dice.getRandomInt() + " "));
        System.out.println();
        System.out.println("Group your dices as pairs.");
        System.out.println("Enter dices order started on 1-4");
        System.out.println("Enter your first pair of row");


        //select dice pairs with checking is already selected
        System.out.println("Select your first pair first dice :");
        firstRowDiceNumber[0] = reader.nextInt()-1;

        while(firstRowDiceNumber[0] > 4 ) {
            System.out.println("Your decision invalid please select first pair first dice");
            firstRowDiceNumber[0] = reader.nextInt()-1;
        }

        System.out.println("Select your first pair second dice :");
        firstRowDiceNumber[1] = reader.nextInt()-1;

        while(firstRowDiceNumber[1] > 4 || firstRowDiceNumber[0].equals(firstRowDiceNumber[1])) {
            System.out.println("Your decision is invalid please select first pair second dice");
            firstRowDiceNumber[1] = reader.nextInt()-1;
        }

        System.out.println("Select your second pair first dice :");
        secondRowDiceNumber[0] = reader.nextInt()-1;

        while(secondRowDiceNumber[0] > 4 || firstRowDiceNumber[0].equals(secondRowDiceNumber[0]) || firstRowDiceNumber[1].equals(secondRowDiceNumber[0])) {
            System.out.println("Your decision is invalid please select second pair first dice");
            secondRowDiceNumber[0] = reader.nextInt()-1;
        }
        System.out.println("Select your second pair second dice :");
        secondRowDiceNumber[1] = reader.nextInt()-1;

        while(secondRowDiceNumber[1] > 4 || firstRowDiceNumber[0].equals(secondRowDiceNumber[1]) || firstRowDiceNumber[1].equals(secondRowDiceNumber[1]) || secondRowDiceNumber[0].equals(secondRowDiceNumber[1])) {
            System.out.println("Your decision is invalid please select second pair second dice");
            secondRowDiceNumber[0] = reader.nextInt()-1;
        }

        Integer row1 = playerDices.get(firstRowDiceNumber[0]).getRandomInt()+playerDices.get(firstRowDiceNumber[1]).getRandomInt();
        Integer row2 = playerDices.get(secondRowDiceNumber[0]).getRandomInt()+playerDices.get(secondRowDiceNumber[1]).getRandomInt();

        setPlayerRowsNumbers(row1,row2);    // set player rows

        return playerRowsNumbers;
    }

    //make player move decision
    @Override
    public Move makeMove() {
        //our pairs same as selectedPlayerRows
        Integer[] firstRowDiceNumber = new Integer[2];
        Integer[] secondRowDiceNumber = new Integer[2];

        rollDice();                     //roll dice every move

        System.out.print("Your dices are :");
        playerDices.forEach((dice) -> System.out.print(dice.getRandomInt() + " "));
        System.out.println();
        System.out.println("Group your dices as pairs.");
        System.out.println("Enter dices order started on 1-4");
        System.out.println("Enter your first pair of row");

        System.out.println("Select your first pair first dice :");
        firstRowDiceNumber[0] = reader.nextInt();
        while(firstRowDiceNumber[0] > 4 ) {
            System.out.println("Your decision invalid please select first pair first dice");
            firstRowDiceNumber[0] = reader.nextInt();
        }

        System.out.println("Select your first pair second dice :");
        firstRowDiceNumber[1] = reader.nextInt();
        while(firstRowDiceNumber[1] > 4 || firstRowDiceNumber[0].equals(firstRowDiceNumber[1])){
            System.out.println("Your decision is invalid please select first pair second dice");
            firstRowDiceNumber[1] = reader.nextInt();
        }

        System.out.println("Select your second pair first dice :");
        secondRowDiceNumber[0] = reader.nextInt();
        while(secondRowDiceNumber[0] > 4 || firstRowDiceNumber[0].equals(secondRowDiceNumber[0]) || firstRowDiceNumber[1].equals(secondRowDiceNumber[0])) {
            System.out.println("Your decision is invalid please select second pair first dice");
            secondRowDiceNumber[0] = reader.nextInt();
        }
        System.out.println("Select your second pair second dice :");
        secondRowDiceNumber[1] = reader.nextInt();
        while(secondRowDiceNumber[1] > 4 || firstRowDiceNumber[0].equals(secondRowDiceNumber[1]) || firstRowDiceNumber[1].equals(secondRowDiceNumber[1]) || secondRowDiceNumber[0].equals(secondRowDiceNumber[1])) {
            System.out.println("Your decision is invalid please select second pair second dice");
            secondRowDiceNumber[0] = reader.nextInt();
        }

        //select row according to pairs
        Integer row1 = playerDices.get(firstRowDiceNumber[0]).getRandomInt() + playerDices.get(firstRowDiceNumber[1]).getRandomInt();
        Integer row2 = playerDices.get(secondRowDiceNumber[0]).getRandomInt() + playerDices.get(secondRowDiceNumber[1]).getRandomInt();

        //if selected 2 rows in player rows list make move with them
        if(playerRowsNumbers.contains(row1)){
            if(playerRowsNumbers.contains(row2)){
                return (new Move(row1,row2));
            }else {     //if one of them in list make move with that row
                return (new Move(row1));
            }
        }
        else if (playerRowsNumbers.contains(row2)) {
            return new Move(row2);  //if second one in list make move with it
        }
        else {
            return (new Move());        //if no one in list make empty move
        }
    }

    public String toString() {
        return playerName;
    }

}
