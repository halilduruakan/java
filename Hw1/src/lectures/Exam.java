package lectures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exam {
    private String examName = ""; // exam name
    private Map<String,Integer> examGrades = new HashMap<String,Integer>(); //exam grade map(String -> integer(default zero))

    //exam constructor by name
    public Exam (String examName) {
        try {
            setExamName(examName);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    //exam name setter
    private void setExamName(String name) throws Exception {
        if(nameChecker(name)) {
            this.examName = name;
        }
    }

    //exam name getter
    public String getExamName() {
        return examName;
    }

    //set exam students (exam grade is defauld zero)
    public void setExamStudents(ArrayList<Student> studentList) {
        for(Student student : studentList) {
            examGrades.put(student.getNumber(),0);
        }
    }

    //set student exam grade through student number and student grade
    public void setStudentExamGrades(String studentNumber, Integer studentGrades) {
        examGrades.put(studentNumber,studentGrades);
    }

    //get student exam grade through student number
    public Integer getStudentExamGrade (String studentNumber) {
        return examGrades.get(studentNumber);
    }

    //get exam grades all student
    public Map<String,Integer> getExamGrades() {
        return examGrades;
    }

    //exam name checker alphabet character number and whitespace
    private boolean nameChecker(String name) throws Exception {
        Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s\\d]*$"));//alphabet Character number and whitespace
        Matcher matcher = pattern.matcher(name);

        if(matcher.matches()) {
            return true;
        }
        else {
            throw new Exception("Exam Name is Invalid.");
        }
    }

}
