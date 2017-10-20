package lectures;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instructor {

    private String firstName; // instructor firstname
    private String lastName; // instuctor lastname
    private Department department; // instructor department field
    private ArrayList<Lecture> lectureList;  // list of lecture given by lecture

    // if want to force the programmer to create an Instructor tied to a Department, remove this create method
    public Instructor(String firstName, String lastName) {
        try {
            setFirstName(firstName);
            setLastName(lastName);
            department = null;
            lectureList = new ArrayList<Lecture>();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    //instructor first name and last name checker. name has alphabet character and white space
    private boolean nameChecker(String name) throws Exception {
        Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches()) {
    	    return true;
        }
        else {
    	    throw new Exception("Last or First name invalid");
        }
    }

    //getter of firstname
    public String getFirstName() {
        return firstName;
    }

    //getter of last name
    public  String getLastName() {
        return lastName;
    }

    //setter of first name
    private void setFirstName(String firstName) throws  Exception{
        if(nameChecker(firstName)) {
            this.firstName = firstName;
        }
    }

    //setter of last name
    private void setLastName(String lastName) throws  Exception {
        if (nameChecker(lastName)) {
            this.lastName = lastName;
        }
    }

    //getter of instructor department
    public Department getDepartment() {
        return department;
    }

    //setter of instructor department
    public void setDepartment(Department department) {
        this.department = department;
    }

    //number of student take lecture from instructor
    public Integer numberOfStudents() {
        Integer numberOfStudents = 0;
        for(Lecture lecture : lectureList) {
            numberOfStudents = numberOfStudents + lecture.numberOfStudents();
        }
        return numberOfStudents;
    }

    //add lecture to instructor
    public void addLecture(Lecture lecture) {
        // check if lecture is a valid lecture
        lectureList.add(lecture);

    }

    //get lecture list given by instructor
    public ArrayList<Lecture> getLectureList() {
        return lectureList;
    }

    //remove lecture to instructor
    public boolean removeLecture (Lecture lecture) {
        if ( lectureList.size() > 0 ) {
            lectureList.remove(lecture);
            return true;
        }
        else
            return false;
    }

    //tostring method return instructor firstname and lastname
    public String toString() {
        return firstName + " " + lastName;
    }
}
