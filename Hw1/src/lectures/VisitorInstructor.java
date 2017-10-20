package lectures;

public class VisitorInstructor extends Instructor {

    private double hourFee;

    //visitor instructor constructor by firstname lastname and hourfee
    public VisitorInstructor(String firstName, String lastName, double hourFee) {
        super(firstName, lastName);
        this.hourFee = hourFee;
    }

    public double getHourFee() {
        return hourFee;
    }

    private void setHourFee(double hourFee) {
        this.hourFee = hourFee;
    }

}