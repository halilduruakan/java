package model;
import composite.*;
import composite.analyst.JuniorAnalyst;
import composite.analyst.SeniorAnalyst;
import composite.designer.JuniorDesigner;
import composite.designer.SeniorDesigner;
import composite.developer.EntryLevelDeveloper;
import composite.developer.JuniorDeveloper;
import composite.developer.SeniorDeveloper;
import composite.tester.SeniorTester;
import composite.tester.TestSpecialist;
import java.util.*;

/**
 * Created by Ugur Belge and Halil Duruakan.
 */

public class Model extends java.util.Observable{

    private double projectCosts[];
    private String  expectedTime;
    private TeamLeader teamLeader;

    public Model(){
        teamLeader = new TeamLeader(150);
        projectCosts = new double[5];
    }

    //create team according to input from user and calculate cost of teams
    public void setProjectCost(String[] inputs){
        teamLeader.removeAllMembers();

        createAnalystTeam(inputs[0],inputs[1]);
        createDesignerTeam(inputs[2],inputs[3]);
        createDeveloperTeam(inputs[4],inputs[5],inputs[6]);
        createTesterTeam(inputs[7],inputs[8]);

        projectCosts=teamLeader.getProjectCost();

        //Notify view of changes
        setChanged();
        notifyObservers();
    }

    private void createAnalystTeam(String junior,String senior){
        try {
            int juniorNumber = Integer.parseInt(junior);
            int seniorNumber = Integer.parseInt(senior);

            for (int i = 0 ; i<juniorNumber;i++){
                teamLeader.addTeamMember(new JuniorAnalyst(65,150));
            }

            for (int i = 0 ; i<seniorNumber;i++){
                teamLeader.addTeamMember(new SeniorAnalyst(95,90));
            }
        }catch (Exception e){
            System.out.println("Analyst does not created! e : "+e.toString());
        }

    }

    private void createDesignerTeam(String junior,String senior){
        try {
            int juniorNumber = Integer.parseInt(junior);
            int seniorNumber = Integer.parseInt(senior);

            for (int i = 0 ; i<juniorNumber;i++){
                teamLeader.addTeamMember(new JuniorDesigner(55,210));
            }

            for (int i = 0 ; i<seniorNumber;i++){
                teamLeader.addTeamMember(new SeniorDesigner(85,90));
            }
        }catch (Exception e){
            System.out.println("Designer does not created! e : "+e.toString());
        }

    }


    private void createDeveloperTeam(String entryLevel ,String junior,String senior){
        try {
            int entryLevelNumber = Integer.parseInt(entryLevel);
            int juniorNumber = Integer.parseInt(junior);
            int seniorNumber = Integer.parseInt(senior);

            for (int i = 0 ; i<entryLevelNumber;i++){
                teamLeader.addTeamMember(new EntryLevelDeveloper(60,730));
            }

            for (int i = 0 ; i<juniorNumber;i++){
                teamLeader.addTeamMember(new JuniorDeveloper(90,365));
            }

            for (int i = 0 ; i<seniorNumber;i++){
                teamLeader.addTeamMember(new SeniorDeveloper(120,180));
            }
        }catch (Exception e){
            System.out.println("Developer does not created! e : "+e.toString());
        }

    }

    private void createTesterTeam(String junior,String senior){
        try {
            int juniorNumber = Integer.parseInt(junior);
            int seniorNumber = Integer.parseInt(senior);

            for (int i = 0 ; i<juniorNumber;i++){
                teamLeader.addTeamMember(new SeniorTester(100,180));
            }

            for (int i = 0 ; i<seniorNumber;i++){
                teamLeader.addTeamMember(new TestSpecialist(130,90));
            }
        }catch (Exception e){
            System.out.println("Tester does not created! e : "+e.toString());
        }

    }

    //optimize employee numbers and date
    public void optimize(Date[] dates){
        teamLeader.removeAllMembers();

        //The analyst, design and test team is finish their task almost at the same time,
        //and half of developer team completion time.
        //we decide to dividing expected time into 5 and assigning teams according to their weight
        //analyst 1 , design 1 , developer 2 , test 1
        //then when we look at table,seniors more efficient according to cost performance index
        //so we decide to create senior until the expected time of team is less than completion time of team

        setExpectedTime(dates);
        optimizeAnalystTeam();
        optimizeDesignerTeam();
        optimizeDeveloperTeam();
        optimizeTesterTeam();

        projectCosts=teamLeader.getProjectCost();
        setChanged();
        notifyObservers();
    }


    private void optimizeAnalystTeam(){
        try {
            float analystTeam;
            analystTeam= Integer.parseInt(expectedTime)/5;
            boolean result=true;
            while (result){

                teamLeader.addTeamMember(new SeniorAnalyst(95, 90));
                teamLeader.getProjectCost();
                if (teamLeader.getAllTeamCompletionDays()[0] <= analystTeam) {
                    result=false;
                }
            }
        }catch (Exception e){
            System.out.println("Analyst team does not optimized! e : " + e.toString());
        }

    }

    private void optimizeDesignerTeam(){
        try {
            float designerTeam;
            designerTeam= Integer.parseInt(expectedTime)/5;
            boolean result=true;
            while (result){
                teamLeader.addTeamMember(new SeniorDesigner(85, 90));
                teamLeader.getProjectCost();
                if (teamLeader.getAllTeamCompletionDays()[1] <= designerTeam) {
                    result=false;
                }
            }
        }catch (Exception e){
            System.out.println("Design team does not optimized! e : " + e.toString());
        }

    }

    private void optimizeDeveloperTeam(){
        try {
            float developerTeam;
            developerTeam= (Integer.parseInt(expectedTime)/5)*2;
            boolean result=true;
            while (result){
                teamLeader.addTeamMember(new SeniorDeveloper(120, 180));
                teamLeader.getProjectCost();
                if (teamLeader.getAllTeamCompletionDays()[2] <= developerTeam) {
                    result=false;
                }
            }
        }catch (Exception e){
            System.out.println("Developer team does not optimized! e : " + e.toString());
        }

    }

    private void optimizeTesterTeam(){
        try {
            float testerTeam;
            testerTeam= Integer.parseInt(expectedTime)/5;
            boolean result=true;
            while (result){
                teamLeader.addTeamMember(new TestSpecialist(130, 90));
                teamLeader.getProjectCost();
                if (teamLeader.getAllTeamCompletionDays()[3] <= testerTeam) {
                    result=false;
                }
            }
        }catch (Exception e){
            System.out.println("Tester team does not optimized! e : " + e.toString());
        }

    }

    public double[] getProjectCosts(){ return projectCosts; }

    public void setExpectedTime(Date[] dates){
        //entered date must be greater than 5 otherwise it does not optimize
        if ((dates[1].getTime() - dates[0].getTime()) < 5){
            System.out.println("Date does not valid");
            expectedTime="5";
        }else
            expectedTime = String.valueOf( (dates[1].getTime() - dates[0].getTime())/ (1000 * 60 * 60 * 24) );
    }

    public String getExpectedTime(){ return expectedTime; }

    public double getTotalCost(){
        return teamLeader.getTotalCost();
    }

    public double getCalculatedTime(){
        return teamLeader.getTaskCompletionTime();
    }

    public float[] getTeamCompletionDays(){
        return teamLeader.getAllTeamCompletionDays();
    }

    public TeamLeader getTeamLeader(){
        return teamLeader;
    }
}
