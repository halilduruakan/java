package view;

import composite.Team;
import composite.analyst.Analyst;
import composite.analyst.JuniorAnalyst;
import composite.designer.Designer;
import composite.designer.JuniorDesigner;
import composite.developer.Developer;
import composite.developer.EntryLevelDeveloper;
import composite.developer.JuniorDeveloper;
import composite.tester.SeniorTester;
import composite.tester.Tester;
import model.Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by Ugur Belge and Halil Duruakan.
 */
public class View extends AView {
    private JPanel panel1;
    private JPanel panel2,panel3;
    private JFrame frame;
    private JLabel[] calculatedTotalInfo;//part of gui where we show total cost and time of project
    private JLabel[] calculatedTeamInfo;//part of gui where we show total cost and time of project
    private JLabel[] projectDates;//
    private JButton calculateButton;
    private JButton optimizeButton;
    private JTextField[] numbersOfEmployee;//part of gui where the user enters the number of employees
    private JTextField[] dateField;//part of gui where the user enters the dates of project
    private JLabel[] showInformation;//part of gui where we show who the information belongs to
    private Model model;
    private String[] inputs;//employee numbers entered by the user
    private Date startDate;
    private Date endDate;

    private String employers[] = {
            "Junior Analyst:",
            "Senior Analyst:",
            "Junior Designer:",
            "Senior Designer:",
            "Entry Level Developer:",
            "Junior Developer:",
            "Senior Developer:",
            "Senior Tester:",
            "Test Specialist:"
    };

    private String costInfo[] = {
            "Analyst Team : ",
            "Designer Team : " ,
            "Developer Team : ",
            "Tester Team : "
    };

    private String projectInfo[] = {
            "Project Start Date:",
            "Project End Date:",
            "Expected Time:"
    };

    private String calculated[] = {
            "Calculated Time :",
            "Total Calculated Cost :"
    };

    public View(Model model){
        try {
            this.model = model;
            model.addObserver(this); //gives the model a reference to the view
            createView();
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    //creating gui component
    private void createView(){
        frame = new JFrame();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new GridLayout(12,1,2,8));
        panel3.setLayout(new GridLayout(12,1,2,8));

        projectDates = new JLabel[3];
        calculatedTeamInfo = new JLabel[4];
        showInformation = new JLabel[13];
        calculatedTotalInfo = new JLabel[2];
        numbersOfEmployee = new JTextField [9];
        dateField = new JTextField[3];
        inputs = new String[9];

        calculateButton = new JButton("Calculate Cost & Time");
        optimizeButton = new JButton("Optimize Time & Cost");

        startDate = new Date();
        endDate = new Date();
        fillFrame();
    }

    //when observers are notified by model view will be updated
    public void update(Observable t, Object o) {
        try {
            double projectCosts[] ;
            projectCosts = model.getProjectCosts();
            DecimalFormat myFormatter = new DecimalFormat("###,###,###");
            //Update team information
            for(int i = 0; i < calculatedTeamInfo.length ; i++)
            {
                calculatedTeamInfo[i] .setText(costInfo[i]+myFormatter.format(projectCosts[i]) + " $" + " , " + myFormatter.format(model.getTeamCompletionDays()[i]) + " days");
            }
            //Update total cost and time information
            dateField[2].setText(model.getExpectedTime() + " days");
            calculatedTotalInfo[0].setText(calculated[0] + myFormatter.format(model.getCalculatedTime()) + " days");
            calculatedTotalInfo[1].setText(calculated[1]+ myFormatter.format(model.getTotalCost()) + " $");
            rePaint();
        }catch (Exception e){
            System.out.println("Update error :"+ e.toString());
        }

    }

    //Update employee numbers
    private void rePaint(){
        int[] numberOfMembers = {0,0,0,0,0,0,0,0,0};
        for (Team team : model.getTeamLeader().getTeamMembers()){
            if (team instanceof Analyst){
                if (team instanceof JuniorAnalyst)
                    numberOfMembers[0]++;
                else
                    numberOfMembers[1]++;
            }else if (team instanceof Designer){
                if (team instanceof JuniorDesigner)
                    numberOfMembers[2]++;
                else
                    numberOfMembers[3]++;
            }else if (team instanceof Developer){
                if (team instanceof EntryLevelDeveloper)
                    numberOfMembers[4]++;
                else if(team instanceof JuniorDeveloper)
                    numberOfMembers[5]++;
                else
                    numberOfMembers[6]++;
            }else if(team instanceof Tester){
                if (team instanceof SeniorTester)
                    numberOfMembers[7]++;
                else
                    numberOfMembers[8]++;
            }
        }

        for (int i = 0; i< numbersOfEmployee.length; i++){
            numbersOfEmployee[i].setText(String.valueOf(numberOfMembers[i]));
        }
    }

    //fill the frame with necessary components
    private void fillFrame(){
        for(int i = 0; i < numbersOfEmployee.length; i++)
        {
            numbersOfEmployee[i] = new JTextField("0");
            showInformation[i] = new JLabel(employers[i]);
            panel2.add(showInformation[i]);
            panel2.add(numbersOfEmployee[i]);
        }

        for (int i = 0; i< calculated.length; i++){
            calculatedTotalInfo[i] = new JLabel(calculated[i]);
            panel2.add(calculatedTotalInfo[i]);
        }

        for(int i = 0; i < calculatedTeamInfo.length ; i++)
        {
            calculatedTeamInfo[i] = new JLabel(costInfo[i]);
            panel2.add(calculatedTeamInfo[i]);
        }
        Date dt = new Date();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        for (int i=0 ; i<dateField.length ;i++){
            dateField[i] = new JTextField();
            projectDates[i] = new JLabel(projectInfo[i]);
            panel3.add(projectDates[i]);
            panel3.add(dateField[i]);
        }
        dateField[0].setText(format.format(cal.getTime()));
        cal.add(Calendar.DATE, 5);
        dateField[1].setText(format.format(cal.getTime()));
        panel3.add(calculateButton);
        panel3.add(optimizeButton);
        panel1.add(panel2,BorderLayout.NORTH);
        panel1.add(panel3,BorderLayout.CENTER);

        frame.setTitle("Software Project Management Program");
        frame.add(panel1);
        frame.setSize(700, 400);
        frame.setVisible(true);

    }

    //get number of employees entered by user
    public String[] getInfoOfTeam(){
        for (int i = 0; i< numbersOfEmployee.length; i++){
            inputs[i] = numbersOfEmployee[i].getText();
        }
        return inputs;
    }

    //get dates entered by user
    public Date[] getInfoOfDate(){

        Date[] dates = new Date[2];
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
                startDate = dateFormat.parse(dateField[0].getText());
                endDate = dateFormat.parse(dateField[1].getText());

        } catch (ParseException e1) {
            System.out.println("Date format is invalid");
        }
        dates[0]=startDate;
        dates[1]=endDate;

        return dates;
    }


    public void addCalculateListener(ActionListener actionListener) {
        calculateButton.addActionListener(actionListener);
    }

    public void addOptimizeListener(ActionListener actionListener) {
        optimizeButton.addActionListener(actionListener);
    }

}
