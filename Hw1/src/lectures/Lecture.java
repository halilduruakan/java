package lectures;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lecture {

    private String name; //lecture name
    private String code; //lecture code. for example ceng431 (it has only 3 digit )
    private ArrayList<Student> studentList;  //take lecture student list
    private ArrayList<Exam> examList = new ArrayList<Exam>(); // lecture exam list

    //lecture constructor by code and name
    public Lecture(String code, String name) {
        try{
            setName(name);
            setCode(code);
            studentList = new ArrayList<Student>();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    //lecture name checker
    private boolean nameChecker(String name) throws Exception {
        Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$")); //only character and whitespace
        Matcher matcher = pattern.matcher(name);

        if(matcher.matches()) {
            return true;
        }
        else {
            throw new Exception("Lecture Name is Invalid.");
        }
    }

    // lecture code checker
    private boolean codeChecker(String code) throws Exception {
        Pattern pattern = Pattern.compile(new String("^[a-zA-Z]*\\d{3}$")); //only character and 3 digit at the end of the string
        Matcher matcher = pattern.matcher(code);

        if(matcher.matches()) {
            return true;
        }
        else {
            throw new Exception("Lecture Code is Invalid.");
        }
    }

    // lecture name getter
    public String getName() {
        return name;
    }

    //lecture name setter
    private void setName(String name) throws Exception {
        if(nameChecker(name)) {
            this.name = name;
        }
    }

    //lecture code getter
    public String getCode() {
        return code;
    }

    //lecture code setter
    private void setCode(String code) throws Exception {
        if(codeChecker(code)) {
           this.code = code;
        }
    }

    //getter student list take lecture
    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    // number of student take lecture
    public Integer numberOfStudents() {
        return studentList.size();
    }

    //get exam list in lecture
    public ArrayList<Exam> getExamList() {
        return examList;
    }

    //add exam to lecture and set exam student list(student default grade is zero)
    public void addExam(Exam exam) {
        exam.setExamStudents(studentList);
        examList.add(exam);
    }

    //remove exam from lecture
    public boolean removeExam(Exam exam) {
        if(examList.size() > 0) {
            examList.remove(exam);
            return true;
        }
        return false;
    }

    //add student to lecture and add lecture to student list
    public void addStudent(Student student) {
        studentList.add(student);
        for(Exam exam : examList){
            exam.setStudentExamGrades(student.getNumber(),0);
        }
        student.addLecture(this);
    }

    //remove student to lecture student list
    public boolean removeStudent(Student student) {
        if ( studentList.size() > 0 ) {
            studentList.remove(student);
            return true;
        }
        else
            return false;
    }

    //get student lecture grade (percentage of all exams is the same)
    public String getStudentLectureGrade(String studentNumber) {
        if(examList.size() > 0) {
            Integer lectureGradeValue = 0;
            for (Exam exam : getExamList()) {
                lectureGradeValue = lectureGradeValue + exam.getStudentExamGrade(studentNumber);
            }
            return evaluateLectureGrade(lectureGradeValue / examList.size());
        }
        return "Student Has Not Exam";
    }

    // evaluate lecture grade  through exam grade average (calculated according to catalog note system)
    private String evaluateLectureGrade(Integer lectureGrade) {
        if(lectureGrade >= 90) {
            return "AA";
        }
        if(lectureGrade < 90 && lectureGrade >=85) {
            return "BA";
        }
        if(lectureGrade < 85 && lectureGrade >=80) {
            return "BB";
        }
        if(lectureGrade < 80 && lectureGrade >= 75) {
            return "CB";
        }
        if(lectureGrade < 75 && lectureGrade >= 70) {
            return "CC";
        }
        if(lectureGrade < 70 && lectureGrade >= 65) {
            return "DC";
        }
        if(lectureGrade < 65 && lectureGrade >= 60) {
            return "DD";
        }
        if(lectureGrade < 60 && lectureGrade >= 50) {
            return "FD";
        }
            return "FF";
    }

    //tostring method return lecture name and code
    public String toString() {
       return name + " " + code;
    }
}
