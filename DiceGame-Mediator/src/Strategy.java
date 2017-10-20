import java.util.ArrayList;

public class Strategy {

    //combination that can be selected dice
    private ArrayList<Integer> evaluateCombination(ArrayList<Dice> dices) {
        ArrayList<Integer> combination = new ArrayList<>();
        int index,index2;

        for(index = 0 ; index < dices.size() ; index++ ) {
            for(index2 = index+1 ; index2 < dices.size() ; index2++) {
                combination.add(dices.get(index).getRandomInt() + dices.get(index2).getRandomInt());
            }
        }
        return combination;
    }

    //select 2 value (0-5 , 1-4 , 2-3)  in combination list according to choice list
    private ArrayList<Integer> evaluateSelection(ArrayList<Integer> combination, ArrayList<Integer> choiceList) {
        ArrayList<Integer> selected = new ArrayList<>();

        if(choiceList.contains(combination.get(0)) || choiceList.contains(combination.get(5))) {
            selected.add(combination.get(0));
            selected.add(combination.get(5));
            return selected;
        }
        else if(choiceList.contains(combination.get(1)) || choiceList.contains(combination.get(4))) {
            selected.add(combination.get(1));
            selected.add(combination.get(4));
            return selected;
        }
        else {
            selected.add(combination.get(2));
            selected.add(combination.get(3));
            return selected;
        }

    }

    //generate move strategy according to dices and player's rows
    public ArrayList<Integer> generateStrategy(ArrayList<Dice> dices, ArrayList<Integer> computerRows) {

        ArrayList<Integer> combination ;

        combination = evaluateCombination(dices);

        ArrayList<Integer> row = new ArrayList<>();

        //if combination only include 2 computer rows return 2 rows
        if(((combination.get(0).equals(computerRows.get(0)) && combination.get(5).equals(computerRows.get(1))) || (combination.get(0).equals(computerRows.get(0)) && combination.get(5).equals(computerRows.get(1))))
                || ((combination.get(1).equals(computerRows.get(0)) && combination.get(4).equals(computerRows.get(1))) || (combination.get(1).equals(computerRows.get(0)) && combination.get(4).equals(computerRows.get(1))))
                || ((combination.get(2).equals(computerRows.get(0)) && combination.get(3).equals(computerRows.get(1))) || (combination.get(2).equals(computerRows.get(0)) && combination.get(3).equals(computerRows.get(1))))) {
            return computerRows;

            //if combination include computer row 1 return row 1
        }else if (combination.contains(computerRows.get(0))) {
            row.add(computerRows.get(0));
            return row;

            //if combination only include computer row 2 return row 2
        }else if (combination.contains(computerRows.get(1))) {
            row.add(computerRows.get(1));
            return row;
        }

        return new ArrayList<Integer>();

    }

    //select computer row according to dices
    public ArrayList<Integer> selectRows(ArrayList<Dice> diceNumbers) {
        ArrayList<Integer> bestChoice  = new ArrayList<>();//
        ArrayList<Integer> goodChoice  = new ArrayList<>();
        ArrayList<Integer> badChoice  = new ArrayList<>();
        ArrayList<Integer> combination;

        bestChoice.add(6);
        bestChoice.add(7);
        bestChoice.add(8);

        goodChoice.add(4);
        goodChoice.add(5);
        goodChoice.add(9);
        goodChoice.add(10);

        badChoice.add(2);
        badChoice.add(3);
        badChoice.add(11);
        badChoice.add(12);

        //evaluate combination according to dice numbers
        combination = evaluateCombination(diceNumbers);
        //evaluate selected rows according to best , good and bad choice
        if(combination.contains(6) || combination.contains(7) || combination.contains(8)) {
            return evaluateSelection(combination,bestChoice);
        } else if(combination.contains(4) || combination.contains(5) || combination.contains(9) || combination.contains(10)) {
            return evaluateSelection(combination,goodChoice);
        }else {
            return evaluateSelection(combination,badChoice);
        }

    }
}
