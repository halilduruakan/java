package composite;

import composite.analyst.Analyst;
import composite.designer.Designer;
import composite.developer.Developer;
import composite.tester.Tester;
import java.util.ArrayList;
/**
 * Created by Ugur Belge and Halil Duruakan .
 */

public class TeamLeader implements Team {
    private float teamCompletionTime;
    private float calculatedTime = 0 ;
    private double totalCost = 0;
    private float[] teamCompletionDays = {0,0,0,0};
    private double[] projectCosts;
    private double dailyCost ;
    private ArrayList<Team> teamMembers;


    public TeamLeader(double dailyCost){

        projectCosts = new double[4];
        teamMembers = new ArrayList<Team>();
        setDailyCost(dailyCost);
    }

    private void calculateTeamCompletionDays(){
        double analystTeamCompDays = 0.0;
        double designerTeamCompDays = 0;
        double developerTeamCompDays = 0;
        double testerTeamCompDays = 0;

        try {
        //we calculate how many days the team will take a job together
        for (Team team : getTeamMembers()){
            if (team instanceof Analyst){
                analystTeamCompDays = analystTeamCompDays +1/(team.getTaskCompletionTime());
            }else if (team instanceof Designer){
                designerTeamCompDays = designerTeamCompDays + 1/(team.getTaskCompletionTime());
            }else if (team instanceof Developer){
                developerTeamCompDays = developerTeamCompDays + 1/(team.getTaskCompletionTime());
            }else if(team instanceof Tester){
                testerTeamCompDays = testerTeamCompDays + 1/(team.getTaskCompletionTime());
            }
        }

            //if number of completion day is not integer(1,75 for example) we will it to upper integer(2)
            //because we give employees daily money
            teamCompletionDays[0]=(float)Math.ceil(1/analystTeamCompDays);
            teamCompletionDays[1]=(float)Math.ceil(1/designerTeamCompDays);
            teamCompletionDays[2]=(float)Math.ceil(1/developerTeamCompDays);
            teamCompletionDays[3]=(float)Math.ceil(1/testerTeamCompDays);


            calculatedTime=0;
            for (int i = 0; i<teamCompletionDays.length;i++){
                if (teamCompletionDays[i] < Float.MAX_VALUE )
                    calculatedTime = calculatedTime + teamCompletionDays[i];
            }
        }catch (Exception e){
            System.out.print("Error occur when calculating team comp time e: " +e.toString());
        }

        //team leader works total time of teams
        setTeamCompletionTime(calculatedTime);
        setAllTeamCompTime();


    }

    public void setAllTeamCompTime(){
        try {
            for (Team team : getTeamMembers()){
                if (team instanceof Analyst){
                    team.setTeamCompletionTime(teamCompletionDays[0]);
                }else if (team instanceof Designer){
                    team.setTeamCompletionTime(teamCompletionDays[1]);
                }else if (team instanceof Developer){
                    team.setTeamCompletionTime(teamCompletionDays[2]);
                }else if(team instanceof Tester){
                    team.setTeamCompletionTime(teamCompletionDays[3]);
                }
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    //calculate team costs and total cost
    public double[] getProjectCost(){

        calculateTeamCompletionDays();
        try {
            double analystTeamCost = 0;
            double designerTeamCost = 0;
            double developerTeamCost = 0;
            double testerTeamCost = 0;
            double teamLeaderCost = 0;

            //to calculate cost of team we multiply daily cost and task completion time
            for (Team team : getTeamMembers()){
                if (team instanceof Analyst){
                    analystTeamCost = analystTeamCost+ team.getDailyCost()*team.getTeamCompletionTime();
                }else if (team instanceof Designer){
                    designerTeamCost = designerTeamCost+ team.getDailyCost()*team.getTeamCompletionTime();
                }else if (team instanceof Developer){
                    developerTeamCost = developerTeamCost+ team.getDailyCost()*team.getTeamCompletionTime();
                }else if(team instanceof Tester){
                    testerTeamCost = testerTeamCost+ team.getDailyCost()*team.getTeamCompletionTime();
                }
            }
            projectCosts[0]=analystTeamCost;
            projectCosts[1]=designerTeamCost;
            projectCosts[2]=developerTeamCost;
            projectCosts[3]=testerTeamCost;
            teamLeaderCost = this.getDailyCost() * this.getTeamCompletionTime();
            totalCost=analystTeamCost+designerTeamCost+developerTeamCost+testerTeamCost+ teamLeaderCost;
        }catch (Exception e){
            System.out.println("Error occur when calculating team cost e :" +e.toString());
        }


        return projectCosts;
    }

    public ArrayList<Team> getTeamMembers() {
        return teamMembers;
    }

    public boolean addTeamMember(Team team){
        return teamMembers.add(team);
    }

    public void removeAllMembers(){
        teamMembers.clear();
    }

    @Override
    public float getTaskCompletionTime() {
        return calculatedTime;
    }

    public double getTotalCost(){
        return totalCost;
    }

    public double getDailyCost() {
        return dailyCost;
    }

    private void setDailyCost(double dailyCost) {
        this.dailyCost = dailyCost;
    }

    public float[] getAllTeamCompletionDays(){
        return teamCompletionDays;
    }

    @Override
    public float getTeamCompletionTime() {
        return teamCompletionTime;
    }

    @Override
    public void setTeamCompletionTime(float teamCompletionTime) {

        this.teamCompletionTime = teamCompletionTime;
    }

}
