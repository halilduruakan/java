package Tests;

import CommandPattern.*;
import StrategyPattern.Fırında;
import StrategyPattern.Kızartma;
import StrategyPattern.Tavada;
import VisitorPattern.Visitable.*;

/**
 * Created by ugurbelge on 5/31/17.
 */
public class SimpleTest {
    public static void main(String[] args) {

        //create waiter and account
        //create command
        //create order and add which fish and which strategy
        //add order to the command
        //then say it to the waiter
        //waiter executes your command
        //you can check your account



        Waiter waiter = new Waiter();
        AddOrderCMD addOrderCMD = new AddOrderCMD();
        Account account = new Account();
        Order order1 = new Order();

        order1.addFish(new Sardalya(2));
        order1.addStrategy(new Fırında());
        addOrderCMD.setOrder(order1);

        waiter.performCommand(addOrderCMD, account);
        account.printStatus();
        account.calculateTotalPrice();

        waiter.undoLastOrder();
        account.printStatus();
        account.calculateTotalPrice();

        waiter.redoLastOrder();
        account.printStatus();
        account.calculateTotalPrice();

        ChangeFishCMD cmd = new ChangeFishCMD();
        cmd.changeFishinOrder(order1,new Cipura(2));
        waiter.performCommand(cmd, account);
        account.printStatus();

        waiter.undoLastOrder();
        account.printStatus();
        account.calculateTotalPrice();


        Order order2 = new Order();
        order2.addStrategy(new Kızartma());
        order2.addFish(new Levrek(2));
        AddOrderCMD addOrderCMD1 = new AddOrderCMD();
        addOrderCMD1.setOrder(order2);
        waiter.performCommand(addOrderCMD1, account);

        account.printStatus();

        ChangeStrategyCMD strategyCMD = new ChangeStrategyCMD();
        strategyCMD.changeStrategyinOrder(order2,new Tavada());
        waiter.performCommand(strategyCMD, account);
        account.printStatus();

        waiter.undoLastOrder();
        account.printStatus();


        strategyCMD.changeStrategyinOrder(order1,new Tavada());
        waiter.performCommand(strategyCMD, account);
        account.printStatus();

        Order order3 = new Order();
        order3.addStrategy(new Fırında());
        order3.addFish(new Somon(2));
        AddOrderCMD newOrder = new AddOrderCMD();
        newOrder.setOrder(order3);
        waiter.performCommand(newOrder, account);
        account.printStatus();


        ChangeFishCMD cmd1 = new ChangeFishCMD();
        cmd1.changeFishinOrder(order3,new Paplina(2));
        waiter.performCommand(cmd1, account);
        account.printStatus();
        account.calculateTotalPrice();

        DeleteOrder deleteOrder = new DeleteOrder();
        deleteOrder.setOrder(order2);
        waiter.performCommand(deleteOrder, account);
        account.printStatus();
        account.calculateTotalPrice();

        waiter.undoLastOrder();
        account.printStatus();

        waiter.redoLastOrder();
        account.printStatus();

        waiter.undoLastOrder();
        account.printStatus();
        account.calculateTotalPrice();



    }
}
