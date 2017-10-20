package lectures;

public class RegularInstructor extends Instructor {

    private String socialSecurityNumber;

    //regular instructor constructor by firstname lastname and ssn
    public RegularInstructor(String firstName, String lastName, String SSN) {
        super(firstName, lastName);
        this.socialSecurityNumber = SSN;
    }

    public String getSSN()	{
        return socialSecurityNumber;
    }

    private void setSSN(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

}
