package lectures;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Department {

    private String name;   // department name
    private ArrayList<Instructor> instructorList;  // department instructor list

    //department constructor by name
    public Department(String name) {
        try {
            setName(name);
            instructorList = new ArrayList<>();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    //namechecker only alphabet character and white space
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

    //department name getter
    public String getName() {
        return name;
    }

    // department name setter
    private void setName(String name) throws Exception {
        if(nameChecker(name)) {
            this.name = name ;
        }
    }

    // add instructor  to  department instructor list and instructor department field (if it is null)
    public void addInstructor( Instructor instructor) {
        instructorList.add(instructor);
        if(instructor.getDepartment() == null) {
            instructor.setDepartment(this);
        }
    }

    // remove instructor from department instructorlist and instructor department field()
    public boolean removeInstructor(Instructor instructor) {
        if ( instructorList.size() > 0 ) {
            instructorList.remove(instructor);
            instructor.setDepartment(null);
            return true;
        }
        else
            return false;
    }

    //get department instructor list
    public ArrayList<Instructor> getInstructorList() {
        return instructorList;
    }

    // get all given lectures  from department
    public ArrayList<Lecture> getGivenLectures() {
        ArrayList<Lecture> lectures = new ArrayList<Lecture>();
        for(Instructor instructor : instructorList) {
            for(Lecture lecture : instructor.getLectureList()) {
                if(lecture != null) {
                    lectures.add(lecture);
                }
            }
        }
        return lectures;
    }

    //get all student take lecture from department
    public ArrayList<Student> getStudentObjectsTakeLectureFromDepartment() {
        ArrayList<Student> studentsList = new ArrayList<Student>();
        for (Lecture lecture : getGivenLectures()) {
            for (Student student : lecture.getStudentList()) {
                studentsList.add(student);
            }
        }
        return studentsList;
    }

    // check instructor existance in department by name. if instructor exist return true else return false
    public boolean instructorExistFindByName(String instructorName){
        for (Instructor instructor : instructorList){
            if (instructor.toString().equals(instructorName)){
                return true;
            }
        }
        return false;

    }

    // check student existance in department by number.if student exist return true else return false
    public boolean studentExistFindByNumber(String studentNumber) {
            for(Student student : getStudentObjectsTakeLectureFromDepartment()) {
                if(student.getNumber().equals(studentNumber)) {
                    return true;
                }
            }
        return false;
    }

    // check lecture existance in department by name. if lecture exist return true else return false
    public boolean lectureExistFindByName(String lectureName) {
        for(Lecture lecture : getGivenLectures()) {
            if(lecture.getName().equals(lectureName)) {
                return true;
            }
        }
        return false;
    }

    // get number of students take lecture from department
    public Integer numberOfStudentsTakesLectureFromDepartment() {
        Integer numberOfStudents = 0;
        for(Instructor instructor : getInstructorList()) {
            numberOfStudents = numberOfStudents + instructor.numberOfStudents();
        }
        return numberOfStudents;
    }

    // get number of hourly instructor from department
    public Integer getHourlyInstructorNumber() {
        Integer numberOfHourlyInstructor = 0;
        for(Instructor instructor : getInstructorList()) {
            if(instructor instanceof VisitorInstructor) {
                numberOfHourlyInstructor = numberOfHourlyInstructor + 1 ;
            }
        }
        return numberOfHourlyInstructor;
    }

    // toString return department name
    public String toString() {
        return "Departmant Name : " + name ;
    }

}
