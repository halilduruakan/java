import java.util.*;

public class DiceGame {

    private static Scanner reader = new Scanner(System.in);             //to input
    private static HashMap<String, String> players = new HashMap<>();   //player list

    public static void main(String[] args) {
        DiceGame game;
        System.out.println("!!!! Welcome to the Dice Game !!!!");
        try {
            game = new DiceGame();                                      //start game
            game.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void start() {

        try {
            Referee referee = new Referee();                            //create referee
            getPlayers();                                               //get players
            System.out.println();                                       //show them
            players.forEach((name, type) -> System.out.println("Player name : " + name + " / Type :" + type));
            System.out.println();
            referee.init(players);                                      //tell players to referee
            referee.conductGame();                                      //stars playing

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //create players
    private static void getPlayers() {
        String playerType;
        String playerName;
        int index;
        int numberOfPlayer;
        try {
            System.out.println("How many players we have(2-4) : ");
            numberOfPlayer = reader.nextInt();

            if (2 <= numberOfPlayer && numberOfPlayer <= 4) {                //get how many players and their type with name
                System.out.println("C : Computer player /  H : Human player");

                for (index = 0; index < numberOfPlayer; index++) {
                    System.out.println("Player " + (index + 1) + " type(H/C) : ");
                    playerType = reader.next();

                    while(!(playerType.toUpperCase().equals("H")) && !(playerType.toUpperCase().equals("C"))) {
                        System.out.println("Invalid type!! Enter valid type : ");
                        playerType = reader.next();
                    }

                    System.out.println("Player " + (index + 1) + " name : ");
                    playerName = reader.next();
                    players.put(playerName, playerType);              //map player name and type
                }
            }else {
                System.out.println("Invalid input !!!");
                getPlayers();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
