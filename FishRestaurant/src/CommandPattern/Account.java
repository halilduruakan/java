package CommandPattern;

import StrategyPattern.CookingStrategy;
import VisitorPattern.Visitable.IFish;
import VisitorPattern.Visitor.CalculateFishPrice;

import java.util.*;

public class Account {
    private Deque<Order> undoStack ;    //real order list
    private Deque<Order> redoStack ;    //list of changed or deleted orders
    private CalculateFishPrice calculateFishPrice;  //to visit and calculate fish price of each Fish objects

    public Account(){
        undoStack = new LinkedList<>();
        redoStack = new LinkedList<>();
        calculateFishPrice = new CalculateFishPrice();
    }

    //add new order to account
    public void addOrder(Order order){
        undoStack.add(order);
    }

    //undo last command
    public void undo(Order order){
        redoStack.add(order);
        undoStack.remove(order);
    }
    //redo last command
    public void redo(Order order){
        undoStack.add(order);
        redoStack.remove(order);

    }

    //get last undo order
    public Order getLastUndoOrder(){
        if (undoStack.isEmpty()){//if undo stack is empty then get last order of redo stack
            return redoStack.peekLast();
        }else {
            return undoStack.peekLast();
        }
    }
    //get last redo order
    public Order getLastRedoOrder(){
        if (redoStack.isEmpty()){//if redo stack is empty then get last order of undo stack
            return undoStack.peekLast();
        }else {
            return redoStack.peekLast();
        }
    }

    //change the fish in the order
    public void changeFishinOrder(Order changeOrder, IFish fish){
        Order newOrder = new Order();//create new order
        if (undoStack.contains(changeOrder)){//if order in undo stack which is real list of orders
            for (Order order : undoStack) {//and invoke the stack
                if (order == changeOrder) {//find order
                    newOrder.addFish(fish);//set new order selected fish with strategy of last order
                    newOrder.addStrategy(order.getStrategy());
                    undo(order);//undo old order
                }
            }
            undoStack.add(newOrder);//add new order to undo stack
        }
    }

    //same steps with changeFishinOrder
    public void changeStrategyinOrder(Order changeOrder, CookingStrategy cookingStrategy){
        Order newOrder = new Order();
        for (Order order : undoStack) {
            if (order == changeOrder) {
                newOrder.addStrategy(cookingStrategy);
                newOrder.addFish(order.getFish());
                undo(order);
            }
        }
        addOrder(newOrder);
    }

    //
    public void calculateTotalPrice(){
        double cost = 0;
        if (!undoStack.isEmpty()){
            for (Order order : undoStack){
                 if (order != null){
                     if (order.getFish() != null && order.getStrategy() != null){
                         //to calculate fish price, we use VISITOR PATTERN
                         cost = cost + order.getStrategy().cook() + order.getFish().accept(calculateFishPrice);
                     }else if (order.getFish() != null && order.getStrategy() == null){
                         cost = cost + order.getFish().accept(calculateFishPrice);
                     }else if (order.getFish() == null && order.getStrategy() != null){
                         cost = cost + order.getStrategy().cook();
                     }
                 }
            }
        }
        System.out.println("Total Cost : "+cost+"\n");
    }

    //print the last status of orders
    public void printStatus(){
        int i = 1;
        if (!undoStack.isEmpty()){
            for (Order order : undoStack){
                if (order != null){
                    if (order.getFish() != null && order.getStrategy() != null){
                        System.out.println("\nOrder "+i+"{\n" + " - " + order.getFish().toString() + " \n " + "- [" + order.getStrategy().toString() + "] \n}");
                    }else if (order.getFish() != null && order.getStrategy() == null){
                        System.out.println("\nOrder "+i+"{\n" + " - " + order.getFish().toString() + "\n}");
                    }else if (order.getFish() == null && order.getStrategy() != null){
                        System.out.println("\nOrder "+i+"{\n" + " - " + order.getStrategy().toString() + "\n}");
                    }else {
                        System.out.println("\nOrder list empty!!");
                    }
                    i++;
                }else {
                    System.out.println("\nOrder {\n empty! \n}");
                }
            }
        }else {
            System.out.println("\nOrder list empty!!");
        }

    }

}
