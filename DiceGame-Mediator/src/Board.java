import java.util.ArrayList;


public class Board {

    private Row[] rowStars = new Row[13];           //How many stars are there in a row
    private int index;

    public Board() {
        for(index = 0 ; index < rowStars.length ; index++) {
            rowStars[index] = new Row();            //We created rows
        }
    }

    //To show the table
    public void display(){
        for(index = 2 ; index < rowStars.length ; index++) {
                if(index < 10) {
                    System.out.print(" ");          //To print properly
                }
                System.out.print(index + " | ");
                rowStars[index].display();          //We showed stars of that row
                System.out.println();
        }
    }

    //Add star to rows which chosen by the player
    public void addStar(ArrayList<Integer> rows) {
        if (rows == null){
            System.out.println("Not match row!");
        }else{
            for(Integer integer : rows) {
                rowStars[integer].increaseStar();
            }
        }
   }

    //To get stars of the player
    public int getScore(ArrayList<Integer> rows) {
        int a;
        if (rows.size()==1){                                //Player can has one row
            a = rowStars[rows.get(0)].getStarNumber();
        }else if (rows.size()==2){
             a = rowStars[rows.get(0)].getStarNumber()+rowStars[rows.get(1)].getStarNumber();  //or Player can has 2 rows
        }else {
            return 0;       //or Player may not have any rows
        }
        return a;
    }
}
