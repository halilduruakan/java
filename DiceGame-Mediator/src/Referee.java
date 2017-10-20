import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Referee {
    private Board board ;       //layout
    private ArrayList<Integer> reservedRows = new ArrayList<>();    //all ready selected rows
    private ArrayList<Player> players;      //players list


    public void conductGame(){
        Move move;
        int roundNumber = 5;
        for (Player player : players){      //this loop for players to select rows
            if(player instanceof HumanPlayer) {
                //if player type is human do following
                do {
                    player.rollDice();

                    if (isSuitableRowExist(player.getDiceNumbers())) {  //if there are suitable row exist,player must select that rows
                        System.out.println("Reserved Rows : " + reservedRows);  //already selected rows
                        System.out.println();
                        //if before selected rows by another player ,current player does not select that rows
                        while (isSelected(player.selectPlayerRows())) {
                            System.out.println("Selected Row is Invalid...Please try again");
                        }

                        System.out.println();
                        System.out.print(player.toString() + " select :");
                        System.out.println(player.getPlayerRowsNumbers().get(0) + "," + player.getPlayerRowsNumbers().get(1));
                    }
                }while (!(isSuitableRowExist(player.getDiceNumbers())));//do same process to select suitable rows

                reservedRows.add(player.getPlayerRowsNumbers().get(0));
                reservedRows.add(player.getPlayerRowsNumbers().get(1));         //add selected rows to reserved list
            }
            else {      //if player type is computer do following
                System.out.println("Reserved rows : " + reservedRows);

                do {
                    player.rollDice();
                }while((isSelected(player.selectPlayerRows()))); // if there are not selected suitable rows,roll dice

                reservedRows.add(player.getPlayerRowsNumbers().get(0));
                reservedRows.add(player.getPlayerRowsNumbers().get(1));

                System.out.print(player.toString() + " select :");
                System.out.print(player.getPlayerRowsNumbers().get(0) + "," + player.getPlayerRowsNumbers().get(1));
                System.out.print(" rows.");
                System.out.println();
            }
            System.out.println();
            System.out.println("next player");
            System.out.println();
        }

        for(int i = 0 ; i < roundNumber ; i++) {        //this loop for rounds
            for (Player player : players) {             //in each round all player do their move
                System.out.println(player + ", it is your turn !");
                System.out.println();
                System.out.print("Your rows are : ");
                System.out.print(player.getPlayerRowsNumbers().get(0) + "," + player.getPlayerRowsNumbers().get(1));
                System.out.println();

                move = player.makeMove();       //player move
                board.addStar(move.getRows());  //add star that moves rows
                System.out.println();
                board.display();                //update screen
                System.out.println();
            }
        }
        announceWinner();       //after all rounds finish announce winner
    }

    private boolean isSuitableRowExist(ArrayList<Dice> diceNumbers){

        List diceNumber = new ArrayList<Integer>();     //players dice numbers
        List combination = new ArrayList<Integer>();    //Binary combinations of dices
        int index,secondIndex;

        for (Dice dice : diceNumbers){
            diceNumber.add(dice.getRandomInt());        //add dice number to list
        }

        for (index=0;index<diceNumber.size();index++){  //create all binary combinations of dices and add them to list
            for (secondIndex=index+1 ; secondIndex<diceNumber.size();secondIndex++){
                    combination.add(Integer.parseInt(diceNumber.get(index).toString())+Integer.parseInt(diceNumber.get(secondIndex).toString()));
            }
        }

        //check combination, there suitable rows exist
        if (reservedRows.contains(combination.get(0)) || reservedRows.contains(combination.get(5)) || combination.get(0).equals(combination.get(5))
                && reservedRows.contains(combination.get(1)) || reservedRows.contains(combination.get(4)) || combination.get(1).equals(combination.get(4))
                && reservedRows.contains(combination.get(2)) || reservedRows.contains(combination.get(3)) || combination.get(2).equals(combination.get(3))){
            return false;
        }else {
            return true;
        }

    }

    //is selected rows in reserved list and are two pairs of rows same
    private boolean isSelected(ArrayList<Integer> rows){

        if(reservedRows.contains(rows.get(0)) || reservedRows.contains(rows.get(1)) || rows.get(0).equals(rows.get(1))){
            return true;
        }else {
            return false;
        }
    }

    //Initialize players and board
    public void init( HashMap<String,String> gamePlayers){

        this.players = new ArrayList<>();
        this.board = new Board();

        gamePlayers.forEach((name,type) -> {    //create player according to their type
            if (type.equals("H")){
                players.add(new HumanPlayer(name));
            }else{
                players.add(new ComputerPlayer(name));
            }
        });

    }

    //announce how is winner
    private void announceWinner(){

        int[] score = new int[players.size()];      //scores of players
        int[] rank = new int[players.size()];       //ranks of players
        int index;
        int secondIndex;
        for(index = 0 ; index < players.size() ; index++) { //get scores of players and add to array
            score[index] = board.getScore(players.get(index).getPlayerRowsNumbers());
        }

        for(secondIndex = 0 ; secondIndex < players.size() ; secondIndex++) {
            int max = score[0];
            int temp = 0;
            for (int i = 0; i < players.size(); i++) {
                if (score[i] > max) {                       //find max score
                    max = score[i];
                    temp = i;
                }
            }

            rank[secondIndex] = temp;           //add that scores owner player to rank array
            score[temp] = -1;                   //chance max number to -1 to select next max
        }
        for(int i = 0 ; i < players.size() ; i++) {
            System.out.println(i+1 + ". Player is " + players.get(rank[i]).toString() ); //print screen rank of players
        }
    }
}
