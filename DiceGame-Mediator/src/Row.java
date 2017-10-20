
public class Row {

    private int starNumber = 0;

    //add star to a row
    public void increaseStar() {
        starNumber ++ ;
    }

    //show how many stars does row have
    public void display() {
        for(int i = 0 ; i < starNumber ; i++) {
            System.out.print("*");
        }
    }

    //Get how many stars you have in a row
    public int getStarNumber(){
        return starNumber;
    }
}
