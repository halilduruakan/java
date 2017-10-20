package composite;
/**
 * Created by Ugur Belge and Halil Duruakan.
 */

public abstract class Member implements Team{
    private double dailyCost;
    private float taskCompletionTime;
    private float teamCompletionTime;

    public Member(double dailyCost, float taskCompletionTime){
        try {
            setDailyCost(dailyCost);
            setTaskCompletionTime(taskCompletionTime);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public float getTeamCompletionTime() {
        return teamCompletionTime;
    }

    public void setTeamCompletionTime(float teamCompletionTime) {
        this.teamCompletionTime = teamCompletionTime;
    }

    private void setDailyCost(double dailyCost) {
        this.dailyCost = dailyCost;
    }

    private void setTaskCompletionTime(float taskCompletionTime) {
        this.taskCompletionTime = taskCompletionTime;
    }

    public double getDailyCost() {
        return dailyCost;
    }

    public float getTaskCompletionTime() {
        return taskCompletionTime;
    }

}
