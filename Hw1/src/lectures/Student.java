package lectures;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {

    private String number; //student number only include digit
    private String name; // student name
    private ArrayList<Lecture> lectureList; // list of lecture taken by the student

    // student constructor by number and name
    public Student(String number, String name) {
        try {
            setNumber(number);
            setName(name);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        lectureList = new ArrayList<Lecture>();
    }

    //student number checker only include digit
    private boolean numberChecker(String number) throws Exception {
        Pattern pattern = Pattern.compile(new String("^[0-9]*$"));
        Matcher matcher = pattern.matcher(number);

        if(matcher.matches()) {
            return true;
        }
        else {
            throw new Exception("Student Number is Invalid.");
        }
    }

    //name checker only include alphabet character and white space
    private boolean nameChecker(String name) throws Exception {
        Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(name);

        if(matcher.matches()) {
            return true;
        }
        else {
            throw new Exception("Student Name is Invalid.");
        }
    }

    //getter of student number
    public String getNumber() {
        return number;
    }

    // setter of student number
    private void setNumber(String number) throws Exception {
        if(numberChecker(number)) {
            this.number = number;
        }
    }

    //getter of student name
    public String getName() {
        return name;
    }

    //setter of student name
    private void setName(String name) throws Exception {
        if(nameChecker(name)) {
            this.name = name;
        }
    }

    //add lecture to student lecture list
    public void addLecture(Lecture lecture) {
        lectureList.add(lecture);
    }

    // remove lecture to student lecture list
    public void removeLecture(Lecture lecture) {
        lectureList.remove(lecture);
    }

    //tostring method return student name and number
    public String toString() {
        return getName() + " " + getNumber();
    }
}
