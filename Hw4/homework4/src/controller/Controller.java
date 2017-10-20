package controller;

import model.Model;
import view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ugur Belge and Halil Duruakan.
 */
public class Controller {
    private View view;
    private Model model;

    public Controller(Model model, View view){
        try {
            this.view = view;
            this.model = model;
            view.addCalculateListener(new CalculateListener());
            view.addOptimizeListener(new OptimizeListener());
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    //calculate trigger get info of view and send them model
    class CalculateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.setProjectCost(view.getInfoOfTeam());
            model.setExpectedTime(view.getInfoOfDate());
        }
    }
    //optimize trigger get info of view and send them model
    class OptimizeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                model.optimize(view.getInfoOfDate());
        }
    }

}
