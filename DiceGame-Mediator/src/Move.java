import java.util.ArrayList;


public class Move {

    //move has rows
    private ArrayList<Integer> rows = new ArrayList<>();

    //if move can not have any rows
    public Move(){}

    //move can has one row
    public Move(Integer row){
        try{
            rows.add(row);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    //move can has two row
    public Move(Integer row1, Integer row2){
        try{
            rows.add(row1);
            rows.add(row2);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    //get rows of a move
    public ArrayList<Integer> getRows(){
        return rows;
    }
}
