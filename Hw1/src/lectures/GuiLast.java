package lectures;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class GuiLast extends JFrame {
    final JFrame child = new JFrame();
    private JTextField firstName ;
    private JTextField lastName ;
    private JTextField info ;
    private JTextField typeOfInstructur ;
    private JScrollPane scrollPane;
    private JPanel lecturePanel;
    private JSplitPane splitPaneMain, splitPaneSecond, splitPaneFirst, splitPaneThird;
    private JPanel departmentPanel;
    private JPanel instructorPanel;
    private JPanel examPanel;
    private JPanel studentPanel;

    private JButton departmentSearchButton, departmentRemoveButton, departmentAddButton, departmentShowButton;
    private JButton instructorAddButton, instructorRemoveButton, instructorSearchButton, instructorShowButton;
    private JButton lectureAddButton, lectureRemoveButton, lectureSearchButton, lectureShowButton, lectureAddExamButton;
    private JButton examAddButton;
    private JButton studentAddButton, studentRemoveButton, studentSearchButton, studentShowButton;

    private JList departmentJList, instructorJList, lectureJList, studentJList, examJList;
    private JLabel departmentJLabel, instructorJLabel, lectureJLabel, studentJlabel, examJLabel;
    private JPanel departmentButtonPanel, instructorButtonPanel, lectureButtonPanel, studentButtonPanel, examButtonPanel;
    private TitledBorder departmentBorder, instructorBorder, lectureBorder, studentBorder, examBorder;
    private DefaultListModel departmentListModel, instructorListModel, lectureListModel, studentListModel, examListModel;

    private static ArrayList<Department> departmentList = new ArrayList<Department>();

    public static void main(String[] args) {

        addSomeInformations();

        GuiLast gui = new GuiLast();
        gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }

    private GuiLast() {
        creation();
        getDepartmentList();
        settings();
        departmentButtons();
        instructorButtons();
        lectureButtons();
        studentButtons();
        examButtons();
        addComponents();
    }

    private void creation() {

    //in this method we create gui classes

        departmentListModel = new DefaultListModel();
        instructorListModel = new DefaultListModel();
        lectureListModel = new DefaultListModel();
        examListModel = new DefaultListModel();
        studentListModel = new DefaultListModel();

        departmentJLabel = new JLabel();
        instructorJLabel = new JLabel();
        lectureJLabel = new JLabel();
        studentJlabel = new JLabel();
        examJLabel = new JLabel();

        departmentAddButton = new JButton("Add");
        departmentRemoveButton = new JButton("Remove");
        departmentSearchButton = new JButton("Search");
        departmentShowButton = new JButton("Show");

        instructorAddButton = new JButton("Add");
        instructorRemoveButton = new JButton("Remove");
        instructorSearchButton = new JButton("Search");
        instructorShowButton = new JButton("Show");

        lectureAddButton = new JButton("Add");
        lectureRemoveButton = new JButton("Remove");
        lectureSearchButton = new JButton("Search");
        lectureShowButton = new JButton("Show");
        lectureAddExamButton = new JButton("Exam Add");

        studentAddButton = new JButton("Add");
        studentRemoveButton = new JButton("Remove");
        studentSearchButton = new JButton("Search");
        studentShowButton = new JButton("Show");

        examAddButton = new JButton("Add student grade");


        departmentBorder = new TitledBorder("Departmans");
        departmentBorder.setTitleJustification(TitledBorder.CENTER);
        departmentBorder.setTitlePosition(TitledBorder.TOP);

        instructorBorder = new TitledBorder("Instructor");
        instructorBorder.setTitleJustification(TitledBorder.CENTER);
        instructorBorder.setTitlePosition(TitledBorder.TOP);

        lectureBorder = new TitledBorder("Lectures");
        lectureBorder.setTitleJustification(TitledBorder.CENTER);
        lectureBorder.setTitlePosition(TitledBorder.TOP);

        studentBorder = new TitledBorder("Students");
        studentBorder.setTitleJustification(TitledBorder.CENTER);
        studentBorder.setTitlePosition(TitledBorder.TOP);

        examBorder = new TitledBorder("Exams");
        examBorder.setTitleJustification(TitledBorder.CENTER);
        examBorder.setTitlePosition(TitledBorder.TOP);

        departmentButtonPanel = new JPanel(new BorderLayout(8, 8));
        instructorButtonPanel = new JPanel(new BorderLayout(8, 8));
        lectureButtonPanel = new JPanel(new BorderLayout(8, 8));
        studentButtonPanel = new JPanel(new BorderLayout(8, 8));
        examButtonPanel = new JPanel(new BorderLayout(8, 8));

        departmentPanel = new JPanel(new BorderLayout(8, 8));
        instructorPanel = new JPanel(new BorderLayout(8, 8));
        lecturePanel = new JPanel(new BorderLayout(8, 8));
        studentPanel = new JPanel(new BorderLayout(8, 8));
        examPanel = new JPanel(new BorderLayout(8, 8));


        splitPaneThird = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, studentPanel, examPanel);
        splitPaneSecond = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, departmentPanel, instructorPanel);
        splitPaneFirst = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lecturePanel, splitPaneThird);
        splitPaneMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPaneSecond, splitPaneFirst);

        departmentJList = new JList(departmentListModel);
        instructorJList = new JList(instructorListModel);
        lectureJList = new JList(lectureListModel);
        studentJList = new JList(studentListModel);
        examJList = new JList(examListModel);

        scrollPane = new JScrollPane(studentJList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    }

    private void settings() {

        //in this method we set some gui classes to proper informations

        departmentJLabel.setText("Department count:"+countByList(departmentListModel).toString());

        splitPaneMain.setEnabled(true);
        splitPaneSecond.setEnabled(true);
        splitPaneFirst.setEnabled(true);
        splitPaneThird.setEnabled(true);

        splitPaneSecond.setDividerSize(1);
        splitPaneFirst.setDividerSize(1);
        splitPaneMain.setDividerSize(1);
        splitPaneThird.setDividerSize(1);


        departmentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        instructorJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lectureJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        examJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        departmentJList.setFixedCellWidth(260);
        departmentJList.setFixedCellHeight(25);

        instructorJList.setFixedCellWidth(260);
        instructorJList.setFixedCellHeight(25);

        lectureJList.setFixedCellWidth(260);
        lectureJList.setFixedCellHeight(25);

        studentJList.setFixedCellWidth(260);
        studentJList.setFixedCellHeight(25);

        examJList.setFixedCellWidth(260);
        examJList.setFixedCellHeight(25);


        departmentPanel.setBorder(departmentBorder);
        instructorPanel.setBorder(instructorBorder);
        lecturePanel.setBorder(lectureBorder);
        studentPanel.setBorder(studentBorder);
        examPanel.setBorder(examBorder);


        departmentPanel.add(departmentJLabel,BorderLayout.CENTER);
        instructorPanel.add(instructorJLabel,BorderLayout.CENTER);
        lecturePanel.add(lectureJLabel,BorderLayout.CENTER);
        studentPanel.add(studentJlabel,BorderLayout.CENTER);
        examPanel.add(examJLabel,BorderLayout.CENTER);


        departmentPanel.add(departmentJList,BorderLayout.NORTH);
        instructorPanel.add(instructorJList,BorderLayout.NORTH);
        lecturePanel.add(lectureJList,BorderLayout.NORTH);
        studentPanel.add(studentJList,BorderLayout.NORTH);
        studentJList.add(scrollPane);
        examPanel.add(examJList,BorderLayout.NORTH);


    }



    private void departmentButtons() {

        //department buttons listened here

        departmentShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Department department : departmentList){        //show all department's instructors , lectures and other informations about department
                    try {
                        if (departmentJList.getSelectedValue().toString().equals(department.getName())) {
                            getAllList(department);
                            lectureJLabel.setText("Lectures count :" + countByList(lectureListModel).toString());
                            instructorJLabel.setText("Visitor count :" + getDepartmen(departmentJList.getSelectedValue().toString()).getHourlyInstructorNumber().toString());
                            studentJlabel.setText("Students count : " + countByList(studentListModel).toString());
                            examJLabel.setText("Exam count : " + countByList(examListModel).toString());
                        }
                    }catch (Exception NullPointerException){
                            System.out.println("Unpicked departmen can not be shown");
                    }
                }
            }
        });

        child.add(departmentAddButton);
        child.pack();
        //add new department (create and add departmentlist)
        departmentAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(child, "What is departman name?");
                try {
                    if(name != null){
                        if(addDepartment(name)){
                            JOptionPane.showMessageDialog(child,name + " added.");
                            departmentListModel.addElement(name);
                            departmentJList.setModel(departmentListModel);
                            departmentJLabel.setText("Department count:"+countByList(departmentListModel).toString());
                            child.setVisible(false);
                        }else{
                            JOptionPane.showMessageDialog(child,name + " is already exist.");
                            child.setVisible(false);
                        }
                    }
                }catch (Exception exception){
                    System.out.println("Department is not added.");
                }
            }
        });

        //remove department from departmentlist
        departmentRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (departmentJList.getSelectedValue() == null) {
                        JOptionPane.showMessageDialog(child, "You should pick a department.");
                        child.setVisible(false);
                    } else {
                        if (removeDepartment(departmentJList.getSelectedValue().toString())) {
                            JOptionPane.showMessageDialog(child, departmentJList.getSelectedValue().toString() + " removed.");
                        }
                        getDepartmentList();
                        departmentJLabel.setText("Department count:" + countByList(departmentListModel).toString());
                    }
                }catch (Exception exception){
                    System.out.println("Department is not removed.");
                }
            }
        });

        child.add(departmentSearchButton);
        child.pack();
        //search department is in the list?
        departmentSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(child, "What is departman name?");
                try{
                    if (name.equals("")){
                        JOptionPane.showMessageDialog(child, "Information dose not complete.");
                        child.setVisible(false);

                    }else{
                        if (!(isValidDepartment(name))){
                            JOptionPane.showMessageDialog(child,name + " is found.");
                            child.setVisible(false);
                        }else {
                            JOptionPane.showMessageDialog(child,name + " is not found.");
                            child.setVisible(false);
                        }

                    }
                }catch (Exception exception){
                    System.out.println("Search does not complete.");
                }

            }
        });
    }

    private void instructorButtons(){

        //instructors control here

        child.add(instructorSearchButton);
        //search is in the department's instructorlist?
        instructorSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    firstName = new JTextField();
                    lastName = new JTextField();
                    Object[] message = {
                            "Firstname :", firstName,
                            "Lastname :", lastName
                    };
                    int option = JOptionPane.showConfirmDialog(null, message, "Remove", JOptionPane.OK_CANCEL_OPTION);

                    if (option == JOptionPane.OK_OPTION) {
                        if (firstName.getText().equals("") || lastName.getText().equals("")) {
                            JOptionPane.showMessageDialog(child, "Information dose not complete.");
                            child.setVisible(false);
                        } else {
                            String name = firstName.getText() + " " + lastName.getText();
                            if (!(isValidInstructor(name))) {
                                JOptionPane.showMessageDialog(child, name + " is found");
                                child.setVisible(false);
                            } else {
                                JOptionPane.showMessageDialog(child, name + " is not found");
                                child.setVisible(false);
                            }

                            child.setVisible(false);
                        }


                    } else {
                        System.out.println("Login canceled");
                    }
                }catch (Exception exception){
                    System.out.println("Instructor search does not complete.");
                }
            }
        });

        child.add(instructorAddButton);
        child.pack();
        //add new instructor to department
        instructorAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    firstName = new JTextField();
                    lastName = new JTextField();
                    info = new JTextField();
                    typeOfInstructur = new JTextField();
                    Object[] message = {
                            "Firstname :", firstName,
                            "Lastname :", lastName,
                            "SSN / HourFee :", info,
                            "Type of Instructor : ", typeOfInstructur
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Add", JOptionPane.OK_CANCEL_OPTION);

                    if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || info.getText().isEmpty() || typeOfInstructur.getText().isEmpty()){
                        System.out.println("Missed information of instructor.");

                    }else{
                        if (option != JOptionPane.OK_OPTION) {
                            System.out.println("Login canceled");
                        } else {
                            if (typeOfInstructur.getText().equals("Visitor")) {
                                if(addVisitorInstructor(firstName.getText().toString(), lastName.getText().toString(), Double.parseDouble(info.getText()),departmentJList.getSelectedValue().toString())){
                                    System.out.println("Visitor Instructor added.");
                                    instructorListModel.removeAllElements();
                                    getInstructorList(getDepartmen(departmentJList.getSelectedValue().toString()));
                                }
                            } else {
                                if (addRegularInstructor(firstName.getText().toString(),lastName.getText().toString(),info.getText().toString(),departmentJList.getSelectedValue().toString())){
                                    System.out.println("Regular Instructor added.");
                                    instructorListModel.removeAllElements();
                                    getInstructorList(getDepartmen(departmentJList.getSelectedValue().toString()));
                                }

                            }
                        }

                        instructorJLabel.setText("Visitor count :" + getDepartmen(departmentJList.getSelectedValue().toString()).getHourlyInstructorNumber().toString());
                        lectureJLabel.setText("Lectures count :" + countByList(lectureListModel).toString());
                        studentJlabel.setText("Studens count :" + countByList(studentListModel).toString());
                        examJLabel.setText("Exam count :" + countByList(examListModel).toString());

                    }
                    child.setVisible(false);

                }catch (Exception exception){
                    System.out.println("Instructor does not added.");

                }
            }
        });

        child.add(instructorRemoveButton);
        child.pack();
        //remove instructor from departmen's insturctorlist
        instructorRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    firstName = new JTextField();
                    lastName = new JTextField();
                    Object[] message = {
                            "Firstname :", firstName,
                            "Lastname :", lastName
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Remove", JOptionPane.OK_CANCEL_OPTION);

                    if (option == JOptionPane.OK_OPTION) {
                        if(removeInstructor(firstName.getText(), lastName.getText(), departmentJList.getSelectedValue().toString())){
                            instructorListModel.removeAllElements();
                            getInstructorList(getDepartmen(departmentJList.getSelectedValue().toString()));
                        }

                        instructorJLabel.setText("Visitor count :" + getDepartmen(departmentJList.getSelectedValue().toString()).getHourlyInstructorNumber().toString());
                        lectureJLabel.setText("Lectures count :" + countByList(lectureListModel).toString());
                        studentJlabel.setText("Studens count :" + countByList(studentListModel).toString());
                        examJLabel.setText("Exam count :" + countByList(examListModel).toString());
                    } else {
                        System.out.println("Login canceled");
                    }
                    child.setVisible(false);
                }catch (Exception exception){
                    System.out.println("Remove problem.");
                }
            }
        });

        //show instructor's info (lectures,students,exams)
        instructorShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    for (Instructor instructor : getDepartmen(departmentJList.getSelectedValue().toString()).getInstructorList()) {
                        if (instructor.toString().equals(instructorJList.getSelectedValue().toString())) {
                            getLectureList(instructor);
                        }
                    }

                    instructorJLabel.setText("Visitor count :" + getDepartmen(departmentJList.getSelectedValue().toString()).getHourlyInstructorNumber().toString());
                    lectureJLabel.setText("Lectures count :" + countByList(lectureListModel).toString());
                    studentJlabel.setText("Studens count :" + countByList(studentListModel).toString());
                    examJLabel.setText("Exam count :" + countByList(examListModel).toString());
                }catch (Exception exception){
                    System.out.println("Instruction show problem.");
                }
            }
        });
    }

    private void lectureButtons(){
        //lecture control

        child.add(lectureAddButton);
        child.pack();

        //add new lecture to instructor
        lectureAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    firstName = new JTextField();
                    lastName = new JTextField();
                    Object[] message = {
                            "Name :", firstName,
                            "Code :", lastName
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Add", JOptionPane.OK_CANCEL_OPTION);

                    if (firstName.getText().isEmpty() || lastName.getText().isEmpty()) {
                        System.out.println("Missed information of lecture.");

                    } else {
                        if (option == JOptionPane.OK_OPTION) {

                            if (addLecture(firstName.getText().toString(),
                                    lastName.getText().toString(),
                                    getInstructur(instructorJList.getSelectedValue().toString()).getFirstName(),
                                    getInstructur(instructorJList.getSelectedValue().toString()).getLastName(),
                                    departmentJList.getSelectedValue().toString())
                                    ) {
                                System.out.print("Added.");
                            }
                        } else {
                            System.out.println("Add canceled.");
                        }
                    }
                    getLectureList(getInstructur(instructorJList.getSelectedValue().toString()));
                    lectureJLabel.setText("Lecture count :" + countByList(lectureListModel).toString());
                    child.setVisible(false);
                }catch (Exception exception){
                    System.out.println("Lecture add problem.");
                }
            }
        });

        child.add(lectureSearchButton);
        //search lecture is given by the instructor
        lectureSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    firstName = new JTextField();
                    Object[] message = {
                            "Name :", firstName,
                    };
                    int option = JOptionPane.showConfirmDialog(null, message, "Search", JOptionPane.OK_CANCEL_OPTION);

                    if (option == JOptionPane.OK_OPTION) {
                        if (firstName.getText().equals("")) {
                            JOptionPane.showMessageDialog(child, "Information dose not complete.");
                            child.setVisible(false);
                        } else {
                            if (!(isValidLecture(firstName.getText()))) {
                                JOptionPane.showMessageDialog(child, firstName.getText() + " is found");
                                child.setVisible(false);
                            } else {
                                JOptionPane.showMessageDialog(child, firstName.getText() + " is not found");
                                child.setVisible(false);
                            }

                            child.setVisible(false);
                        }


                    } else {
                        System.out.println("Search canceled");
                    }
                }catch (Exception exception){
                    System.out.println("Lecture search does not complete.");
                }
            }
        });

        //add lecture to instructor
        lectureAddExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    firstName = new JTextField();
                    Object[] message = {
                            "Name :", firstName,
                    };
                    int option = JOptionPane.showConfirmDialog(null, message, "Search", JOptionPane.OK_CANCEL_OPTION);


                    String[] lecturespart = lectureJList.getSelectedValue().toString().split("\\s+");
                    String name = "";
                    for (int i = 0; i < lecturespart.length - 1; i++) {
                        name = name + " " + lecturespart[i];
                    }

                    name = name.substring(1, name.length());


                    if (option == JOptionPane.OK_OPTION) {

                        if (firstName.getText().equals("")) {

                            JOptionPane.showMessageDialog(child, "Information dose not complete.");
                            child.setVisible(false);
                        } else {

                            Exam exam = new Exam(firstName.getText().toString());
                            getLecture(lectureJList.getSelectedValue().toString()).addExam(exam);
                            getStudentList(getLecture(lectureJList.getSelectedValue().toString()));
                            System.out.println("Exam added.");
                            child.setVisible(false);

                        }
                    } else {
                        System.out.println("Search canceled");
                    }
                }catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

        //show how take that lecture
        lectureShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    examListModel.removeAllElements();
                    studentListModel.removeAllElements();
                    getStudentList(getLecture(lectureJList.getSelectedValue().toString()));

                    instructorJLabel.setText("Visitor count :" + getDepartmen(departmentJList.getSelectedValue().toString()).getHourlyInstructorNumber().toString());
                    lectureJLabel.setText("Lectures count :" + countByList(lectureListModel).toString());
                    studentJlabel.setText("Studens count :" + countByList(studentListModel).toString());
                    examJLabel.setText("Exam count :" + countByList(examListModel).toString());

                }catch (Exception exception){
                    System.out.println("Lecture show problem.");
                }
            }
        });

        //remove lecture from instructor lecture list
        lectureRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] parts = lectureJList.getSelectedValue().toString().split("\\s+");

                    String name = "";
                    for(int i=0;i<parts.length-1;i++){
                        name = name +" "+parts[i];
                    }
                    name = name.substring(1);

                    if(removeLecture(name,
                            parts[parts.length-1],
                            departmentJList.getSelectedValue().toString(),
                            getInstructur(instructorJList.getSelectedValue().toString()).getFirstName(),
                            getInstructur(instructorJList.getSelectedValue().toString()).getLastName()
                            )){
                            lectureListModel.removeAllElements();
                            getLectureList(getInstructur(instructorJList.getSelectedValue().toString()));
                            System.out.println("Lecture removed.");
                        }else {
                        System.out.println("Remove lecture problem.");
                    }
                    instructorJLabel.setText("Visitor count :" + getDepartmen(departmentJList.getSelectedValue().toString()).getHourlyInstructorNumber().toString());
                        lectureJLabel.setText("Lectures count :" + countByList(lectureListModel).toString());
                        studentJlabel.setText("Studens count :" + countByList(studentListModel).toString());
                        examJLabel.setText("Exam count :" + countByList(examListModel).toString());

                    child.setVisible(false);
                }catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

    }

    private void studentButtons(){

        //Student control

        child.add(studentAddButton);
        child.pack();

        //add student to lecture
        studentAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    firstName = new JTextField();
                    lastName = new JTextField();
                    Object[] message = {
                            "Name :", firstName,
                            "Number :", lastName
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Add", JOptionPane.OK_CANCEL_OPTION);

                    if (firstName.getText().isEmpty() || lastName.getText().isEmpty()) {
                        System.out.println("Missed information of student.");

                    } else {
                        if (option == JOptionPane.OK_OPTION) {


                            if (addNewStudent(firstName.getText().toString(),
                                    lastName.getText().toString(),
                                    getLecture(lectureJList.getSelectedValue().toString()).getName())
                                    ) {
                                System.out.print("Student added.");
                            }else{
                                System.out.println("Student does not added.");
                            }
                        } else {
                            System.out.println("Student add canceled.");
                        }
                    }
                    studentListModel.removeAllElements();
                    examListModel.removeAllElements();
                    getStudentList(getLecture(lectureJList.getSelectedValue().toString()));
                    studentJlabel.setText("Student count :" + countByList(studentListModel).toString());
                    child.setVisible(false);
                }catch (Exception exception){
                    System.out.println("Student add problem.");
                }
            }
        });

        //show student exam result
        studentShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    examListModel.removeAllElements();
                    String[] parts = studentJList.getSelectedValue().toString().split("\\s+");
                    getStudentGradeList(parts[parts.length-1],getLecture(lectureJList.getSelectedValue().toString()));
                    examListModel.addElement("Lecture grade : " + getLecture(lectureJList.getSelectedValue().toString()).getStudentLectureGrade(parts[parts.length-1]));
                    examJList.setModel(examListModel);

                    instructorJLabel.setText("Visitor count :" + getDepartmen(departmentJList.getSelectedValue().toString()).getHourlyInstructorNumber().toString());
                    lectureJLabel.setText("Lectures count :" + countByList(lectureListModel).toString());
                    studentJlabel.setText("Studens count :" + countByList(studentListModel).toString());
                    examJLabel.setText("Exam count :" + countByList(examListModel).toString());

                }catch (Exception exception){
                    System.out.println("Student show problem.");
                }
            }
        });

        //remove student from lecture
        studentRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] parts = studentJList.getSelectedValue().toString().split("\\s+");
                    String[] lecturespart = lectureJList.getSelectedValue().toString().split("\\s+");
                    String name = "";
                    for(int i=0 ;i <lecturespart.length-1;i++){
                        name = name + " " + lecturespart[i];
                    }
                    name = name.substring(1,name.length());

                    if (removeStudentToLecture(parts[parts.length-1],name,departmentJList.getSelectedValue().toString())){
                        studentListModel.removeAllElements();
                        examListModel.removeAllElements();
                        getStudentList(getLecture(lectureJList.getSelectedValue().toString()));
                        System.out.println("Student removed.");
                    }else {
                        System.out.println("Remove student problem.");
                    }

                    instructorJLabel.setText("Visitor count :" + getDepartmen(departmentJList.getSelectedValue().toString()).getHourlyInstructorNumber().toString());
                    lectureJLabel.setText("Lectures count :" + countByList(lectureListModel).toString());
                    studentJlabel.setText("Studens count :" + countByList(studentListModel).toString());
                    examJLabel.setText("Exam count :" + countByList(examListModel).toString());

                    child.setVisible(false);
                }catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

        //search Does this student take that lecture?
        studentSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    firstName = new JTextField();
                    Object[] message = {
                            "Number :", firstName,
                    };
                    int option = JOptionPane.showConfirmDialog(null, message, "Search", JOptionPane.OK_CANCEL_OPTION);

                    if (option == JOptionPane.OK_OPTION) {
                        if (firstName.getText().equals("")) {
                            JOptionPane.showMessageDialog(child, "Information dose not complete.");
                            child.setVisible(false);
                        } else {
                            if (!(isValidStudent(firstName.getText().toString()))) {
                                JOptionPane.showMessageDialog(child, firstName.getText() + " is found");
                                child.setVisible(false);
                            } else {
                                JOptionPane.showMessageDialog(child, firstName.getText() + " is not found");
                                child.setVisible(false);
                            }

                            child.setVisible(false);
                        }


                    } else {
                        System.out.println("Search canceled");
                    }
                }catch (Exception exception){
                    System.out.println("Lecture search does not complete.");
                }
            }
        });

    }

    private void examButtons(){

        //exam control

        child.add(examAddButton);
        child.pack();

        //add grade to selected student's selected exam
        examAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    firstName = new JTextField();
                    Object[] message ={
                            "Grade :",firstName
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Add grade", JOptionPane.OK_CANCEL_OPTION);

                    //these operation is parting showing name and use to reach info
                    String[] studentParts = studentJList.getSelectedValue().toString().split("\\s+");
                    String[] parts = examJList.getSelectedValue().toString().split(":");
                    String[] number = studentJList.getSelectedValue().toString().split("\\s");
                    System.out.println("----------");

                    if (firstName.getText().isEmpty()) {
                        System.out.println("Missed information of student.");

                    } else {

                        if (option == JOptionPane.OK_OPTION) {
                            examListModel.removeAllElements();
                            for (Exam exam : getLecture(lectureJList.getSelectedValue().toString()).getExamList()) {
                                if (exam.getExamName().equals(parts[0])) {

                                    Integer x = Integer.valueOf(firstName.getText().toString());
                                    exam.setStudentExamGrades(number[number.length - 1],x);



                            }

                        }
                    }else {
                            System.out.println("Exam add canceled.");
                        }
                    }
                    getStudentGradeList(studentParts[studentParts.length-1],getLecture(lectureJList.getSelectedValue().toString()));
                    examListModel.addElement("Lecture grade : " + getLecture(lectureJList.getSelectedValue().toString()).getStudentLectureGrade(studentParts[studentParts.length-1]));
                    instructorJLabel.setText("Visitor count :" + getDepartmen(departmentJList.getSelectedValue().toString()).getHourlyInstructorNumber().toString());
                    lectureJLabel.setText("Lectures count :" + countByList(lectureListModel).toString());
                    instructorJLabel.setText("Visitor count :" + getDepartmen(departmentJList.getSelectedValue().toString()).getHourlyInstructorNumber().toString());

                    studentJlabel.setText("Studens count :" + countByList(studentListModel).toString());
                    examJLabel.setText("Exam count :" + countByList(examListModel).toString());
                    examJList.setModel(examListModel);


                }catch (Exception exception){
                    System.out.println("Exam1 add problem.");
                }
            }
        });

    }

    //get lecture object which name is name(arg)
    private Lecture getLecture(String name){

        try{
            String[] parts = name.split("\\s+");

            for (Lecture lecture : getInstructur(instructorJList.getSelectedValue().toString()).getLectureList()){
                if(lecture.getCode().equals(parts[parts.length-1])){
                    return lecture;
                }

            }

        }catch (Exception exception){
            System.out.println("Lecture get problem");

        }
        return null;
    }

    //get instructor which name is name(arg)
    private Instructor getInstructur(String name){
        String[] parts = name.split("\\s+");

        for(Instructor instructor : getDepartmen(departmentJList.getSelectedValue().toString()).getInstructorList()){
            if(instructor.getFirstName().equals(parts[0]) && instructor.getLastName().equals(parts[1])){
                return instructor;
            }
        }
        return null;
    }


    //add components to panels for showing ind the gui
    private void addComponents(){

        departmentPanel.add(departmentButtonPanel,BorderLayout.SOUTH);
        instructorPanel.add(instructorButtonPanel,BorderLayout.SOUTH);
        lecturePanel.add(lectureButtonPanel,BorderLayout.SOUTH);
        studentPanel.add(studentButtonPanel,BorderLayout.SOUTH);
        examPanel.add(examButtonPanel,BorderLayout.SOUTH);

        departmentButtonPanel.add(departmentShowButton,BorderLayout.NORTH);
        departmentButtonPanel.add(departmentAddButton,BorderLayout.CENTER);
        departmentButtonPanel.add(departmentRemoveButton,BorderLayout.LINE_END);
        departmentButtonPanel.add(departmentSearchButton,BorderLayout.AFTER_LAST_LINE);

        instructorButtonPanel.add(instructorShowButton,BorderLayout.NORTH);
        instructorButtonPanel.add(instructorAddButton,BorderLayout.CENTER);
        instructorButtonPanel.add(instructorRemoveButton,BorderLayout.LINE_END);
        instructorButtonPanel.add(instructorSearchButton,BorderLayout.AFTER_LAST_LINE);

        lectureButtonPanel.add(lectureShowButton,BorderLayout.NORTH);
        lectureButtonPanel.add(lectureAddButton,BorderLayout.CENTER);
        lectureButtonPanel.add(lectureRemoveButton,BorderLayout.LINE_END);

        lectureButtonPanel.add(lectureSearchButton,BorderLayout.AFTER_LAST_LINE,1);
        lectureButtonPanel.add(lectureAddExamButton,BorderLayout.LINE_START,2);

        studentButtonPanel.add(studentShowButton,BorderLayout.NORTH);
        studentButtonPanel.add(studentAddButton,BorderLayout.CENTER);
        studentButtonPanel.add(studentRemoveButton,BorderLayout.LINE_END);
        studentButtonPanel.add(studentSearchButton,BorderLayout.AFTER_LAST_LINE);


        examButtonPanel.add(examAddButton,BorderLayout.CENTER);



        getContentPane().add(splitPaneMain);
        this.add(splitPaneMain);
        this.pack();
        this.setVisible(true);
    }

    //added some examples to show in the gui
    private static void addSomeInformations(){

        RegularInstructor regularInstructor1 = new RegularInstructor("tugkan","tuglular","10000");
        RegularInstructor regularInstructor2 = new RegularInstructor("selma","tekin","10001");
        RegularInstructor regularInstructor3 = new RegularInstructor("serap","sahin","10002");
        RegularInstructor regularInstructor4 = new RegularInstructor("mustafa","ozuysal","10003");
        RegularInstructor regularInstructor5 = new RegularInstructor("fatih", "tekbacak","12345");

        VisitorInstructor visitorInstructor1 = new VisitorInstructor("turgut","kalfaoglu",11.5);
        VisitorInstructor visitorInstructor2 = new VisitorInstructor("nesli","erdogmus",12.5);
        VisitorInstructor visitorInstructor3 = new VisitorInstructor("buket","ersahin",13.5);
        VisitorInstructor visitorInstructor4 = new VisitorInstructor("bora","hoca",14.5);
        VisitorInstructor visitorInstructor5 = new VisitorInstructor("tolga","ayav",15.5);



        Department compEngDepartment = new Department("Computer Engineering");
        Department meEngDepartment = new Department("Mecanical Engineering");
        Department electroEngDepartment = new Department("Electronical Engineering");


		/*
		 * Assign instructors to department
		 */
        compEngDepartment.addInstructor(regularInstructor1);
        compEngDepartment.addInstructor(regularInstructor2);
        compEngDepartment.addInstructor(regularInstructor3);
        compEngDepartment.addInstructor(visitorInstructor1);
        meEngDepartment.addInstructor(regularInstructor4);
        meEngDepartment.addInstructor(visitorInstructor2);
        electroEngDepartment.addInstructor(regularInstructor5);
        electroEngDepartment.addInstructor(visitorInstructor4);
        electroEngDepartment.addInstructor(visitorInstructor3);
        electroEngDepartment.addInstructor(visitorInstructor5);

        departmentList.add(compEngDepartment);
        departmentList.add(meEngDepartment);
        departmentList.add(electroEngDepartment);


        Student student1 = new Student ("1", "Orhan");
        Student student2 = new Student ("2", "Dilek");
        Student student3 = new Student ("3", "Veli");
        Student student4 = new Student ("4", "Cuma");
        Student student5 = new Student ("5", "Metin");
        Student student6 = new Student ("6", "Ali");
        Student student7 = new Student ("7", "Feyyaz");
        Student student8 = new Student ("8", "Pascal");
        Student student9 = new Student ("9", "Sergen");
        Student student10 = new Student ("10", "Ilhan");
        Student student11 = new Student ("11", "Cenk");
        Student student12 = new Student ("12", "Tolga");
        Student student13 = new Student ("13", "Mehmet");
        Student student14 = new Student ("14", "Abobakar");
        Student student15 = new Student ("15", "Dembaba");
        Student student16 = new Student ("16", "Ekin");


        Lecture dataStructures = new Lecture("CENG114","Data Structures");
        dataStructures.addStudent(student1);
        dataStructures.addStudent(student2);
        dataStructures.addStudent(student9);
        dataStructures.addStudent(student16);


        Lecture computerArchitecture = new Lecture("CENG311","Computer Archtitecture");
        computerArchitecture.addStudent(student1);
        computerArchitecture.addStudent(student2);
        computerArchitecture.addStudent(student3);

        Lecture operatingSystems = new Lecture("CENG313","Operating Systems");
        operatingSystems.addStudent(student6);
        operatingSystems.addStudent(student4);
        operatingSystems.addStudent(student15);
        operatingSystems.addStudent(student7);

        Lecture criptografy = new Lecture("CENG471","criptografy");
        criptografy.addStudent(student2);
        criptografy.addStudent(student5);
        criptografy.addStudent(student6);

        Lecture programingFundamental = new Lecture("CENG211","Programing Fundamentals");
        programingFundamental.addStudent(student4);
        programingFundamental.addStudent(student6);
        programingFundamental.addStudent(student8);

        Lecture buildingSoftware = new Lecture("CENG431","Building Software systems");
        buildingSoftware.addStudent(student1);
        buildingSoftware.addStudent(student13);

        Lecture webPrograming = new Lecture("CENG388","Web Programing");
        webPrograming.addStudent(student16);
        webPrograming.addStudent(student12);

        Lecture softwareQuality = new Lecture("CENG437","Software Quality Management");
        softwareQuality.addStudent(student10);
        softwareQuality.addStudent(student11);

        Lecture networkPrograming = new Lecture("CENG421","Network Programming");
        networkPrograming.addStudent(student6);
        Lecture networkSecurity = new Lecture("CENg472","Network Security");
        networkSecurity.addStudent(student9);

        Lecture statics = new Lecture("ME221","Statics");
        statics.addStudent(student11);

        Lecture dynamics = new Lecture("ME222","Dynamics");
        dynamics.addStudent(student5);

        Lecture fluidMekanic = new Lecture("ME202","Fluid Mechanics");
        fluidMekanic.addStudent(student4);

        Lecture thermo = new Lecture("ME307","Thermodynamics");
        thermo.addStudent(student1);

        Lecture introElectrical = new Lecture("EE101","Introduction to Electrical Engineering");
        introElectrical.addStudent(student14);

        Lecture circuit = new Lecture("EE201","Circuit Analysis");
        circuit.addStudent(student7);
        circuit.addStudent(student4);

        Lecture electronics = new Lecture("EE212","Electronics");
        electronics.addStudent(student10);

        Lecture electromagnetic = new Lecture("EE222","Electromagnetic");
        electromagnetic.addStudent(student16);

        Lecture signal = new Lecture("EE331","Signals and Systems");
        signal.addStudent(student3);

        Lecture feedback = new Lecture("EE362","Feedback Control Systems");
        feedback.addStudent(student4);


        Lecture lightwave = new Lecture("EE456","Lightwave Communication");
        lightwave.addStudent(student15);

        Lecture speechProcessing = new Lecture("EE432","Speech Processing");
        speechProcessing.addStudent(student15);


        regularInstructor1.addLecture(dataStructures);
        regularInstructor1.addLecture(buildingSoftware);
        regularInstructor1.addLecture(softwareQuality);
        visitorInstructor1.addLecture(webPrograming);
        regularInstructor2.addLecture(programingFundamental);
        regularInstructor3.addLecture(operatingSystems);
        regularInstructor3.addLecture(criptografy);
        regularInstructor2.addLecture(computerArchitecture);
        regularInstructor2.addLecture(networkSecurity);
        visitorInstructor1.addLecture(networkPrograming);

        regularInstructor4.addLecture(statics);
        regularInstructor4.addLecture(dynamics);
        visitorInstructor2.addLecture(fluidMekanic);
        visitorInstructor2.addLecture(thermo);

        regularInstructor5.addLecture(introElectrical);
        regularInstructor5.addLecture(circuit);
        regularInstructor5.addLecture(feedback);
        visitorInstructor4.addLecture(lightwave);
        visitorInstructor4.addLecture(electromagnetic);
        visitorInstructor5.addLecture(speechProcessing);
        visitorInstructor3.addLecture(signal);
        visitorInstructor3.addLecture(electronics);


		/*
		 * Assign students to lectures
		 */


        Exam midterm1 = new Exam("midtermone");
        Exam midterm2 = new Exam("midtermtwo");
        Exam midterm3 = new Exam("midtermthree");
        Exam midterm11 = new Exam("midtermone");
        Exam midterm22 = new Exam("midtermtwo");
        Exam midterm33 = new Exam("midtermthree");
        Exam midterm111 = new Exam("midtermone");
        Exam midterm222 = new Exam("midtermtwo");
        Exam midterm333 = new Exam("midtermthree");
        Exam final1 = new Exam("finalone");
        Exam final2 = new Exam("finaltwo");
        Exam final3 = new Exam("finalthree");

        computerArchitecture.addExam(midterm1);
        computerArchitecture.addExam(midterm2);
        computerArchitecture.addExam(final2);

        circuit.addExam(midterm11);
        circuit.addExam(final3);

        operatingSystems.addExam(midterm3);
        operatingSystems.addExam(midterm22);

        thermo.addExam(midterm33);

        buildingSoftware.addExam(midterm111);
        buildingSoftware.addExam(midterm222);
        buildingSoftware.addExam(midterm333);
        buildingSoftware.addExam(final1);



        midterm1.setStudentExamGrades("1",100);
        midterm2.setStudentExamGrades("1",50);
        final2.setStudentExamGrades("1",60);

        midterm1.setStudentExamGrades("2",80);
        midterm2.setStudentExamGrades("2",20);
        final2.setStudentExamGrades("2",60);

        midterm1.setStudentExamGrades("3",100);
        midterm2.setStudentExamGrades("3",100);
        final2.setStudentExamGrades("3",100);

        midterm11.setStudentExamGrades("7",50);
        midterm11.setStudentExamGrades("4",10);
        final3.setStudentExamGrades("7",100);
        final3.setStudentExamGrades("4",100);

        midterm3.setStudentExamGrades("4",40);
        midterm3.setStudentExamGrades("6",60);
        midterm3.setStudentExamGrades("7",70);
        midterm3.setStudentExamGrades("15",15);

        final3.setStudentExamGrades("4",40);
        final3.setStudentExamGrades("6",60);
        final3.setStudentExamGrades("7",70);
        final3.setStudentExamGrades("15",15);


        midterm33.setStudentExamGrades("1",100);

        midterm111.setStudentExamGrades("1",80);
        midterm222.setStudentExamGrades("1",90);
        midterm333.setStudentExamGrades("1",100);
        final1.setStudentExamGrades("1",100);

    }

    //count objects according to list(arg)
    private Integer countByList(DefaultListModel list){
        Integer count = new Integer(list.getSize());
        return count;
    }

    //add departmen which name is departmentName(arg)
    private boolean addDepartment(String departmentName) {
        if(isValidDepartment(departmentName)) {
            departmentList.add(new Department(departmentName));
            return true;
        }
        return false;
    }

    //remove department according to departmentname
    private boolean removeDepartment(String departmentName) {
        for(Department department : departmentList) {
            if(department.getName().equals(departmentName)) {
                departmentList.remove(department);
                return true;
            }
        }
        return false;
    }

    //add visitor instructor with its informations(arg)
    private boolean addVisitorInstructor(String firstName, String lastName,double hourFree, String departmentName) {
        for(Department department : departmentList) {
            if(department.getName().equals(departmentName)) {
                department.addInstructor(new VisitorInstructor(firstName,lastName,hourFree));
                return true;
            }
        }
        return false;
    }

    //add regular instructor
    private boolean addRegularInstructor(String firstName, String lastName, String SSN, String departmentName) {
        for(Department department : departmentList) {
            if(department.getName().equals(departmentName)) {
                department.addInstructor(new RegularInstructor(firstName,lastName,SSN));
                return true;
            }
        }
        return false;
    }

    //remove specific instructor
    private boolean removeInstructor(String firstName, String lastName, String departmentName) {
        for(Department department : departmentList) {
            if(department.getName().equals(departmentName)) {
                for(Instructor instructor : department.getInstructorList()) {
                    if(instructor.getFirstName().equals(firstName) && instructor.getLastName().equals(lastName)) {
                        department.removeInstructor(instructor);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //add lecture to dedicated departmen and instructor
    private boolean addLecture(String lectureName, String lectureCode, String firstName, String lastName, String departmentName) {
        if(isValidLecture(lectureName)) {
            for (Department department : departmentList) {
                if (department.getName().equals(departmentName)) {
                    for (Instructor instructor : department.getInstructorList()) {
                        if (instructor.getFirstName().equals(firstName) && instructor.getLastName().equals(lastName)) {
                            instructor.addLecture(new Lecture(lectureCode, lectureName));
                            return true;
                        }
                    }
                }
            }
       }
        return false;
    }

    //remove lecture from a instructor and a department
    private boolean removeLecture(String lectureName, String lectureCode, String departmentName, String firstName, String lastName) {
        for(Department department : departmentList) {
            if(department.getName().equals(departmentName)) {
                for(Instructor instructor : department.getInstructorList()) {
                    if(instructor.getFirstName().equals(firstName) && instructor.getLastName().equals(lastName)) {
                        for(Lecture lecture : instructor.getLectureList()) {
                            if(lecture.getName().equals(lectureName) && lecture.getCode().equals(lectureCode)) {
                                if(instructor.removeLecture(lecture)){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    //add new student to lecture
    private boolean addNewStudent(String studentName, String studentNumber, String lectureName) {
        if(isValidNumber(studentNumber)) {
            for(Department department : departmentList) {
                for(Lecture lecture : department.getGivenLectures()) {

                    if(lecture.getName().equals(lectureName)) {
                        lecture.addStudent(new Student(studentNumber, studentName));
                        return true;

                    }
                }
            }
        }
        return false;
    }

    //remove student from lecture
    private boolean removeStudentToLecture(String studentNumber, String lectureName, String departmentName) {
        for(Department department : departmentList) {
            if(department.getName().equals(departmentName)) {
                for(Lecture lecture : department.getGivenLectures()) {
                    if(lecture.getName().equals(lectureName)) {
                        for(Student student : lecture.getStudentList()) {
                            if(student.getNumber().equals(studentNumber)) {
                                student.removeLecture(lecture);
                                lecture.removeStudent(student);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    //check is number valid for student id
    private boolean isValidNumber(String number) {
        if(departmentList.size()>0) {
            for(Department department : departmentList) {
                if (department.studentExistFindByNumber(number)) {
                    return false;
                }
            }
        }
        return true;
    }

    //is valid department(maybe in the list or null for example)
    private boolean isValidDepartment(String name) {
        if(departmentList.size()>0) {
            for(Department department : departmentList) {
                if (department.getName().equals(name)) {
                    return false;
                }
            }
        }
        return true;
    }

    //is valid lecture same as department
    private boolean isValidLecture(String name) {
        if(departmentList.size()>0) {
            for(Department department : departmentList) {
                if (department.lectureExistFindByName(name)) {
                    return false;
                }
            }
        }
        return true;
    }

    //is valid student same as department and lecture
    private boolean isValidStudent(String number) {
        if(departmentList.size()>0) {
            for(Department department : departmentList) {
                if (department.studentExistFindByNumber(number)) {
                    return false;
                }
            }
        }
        return true;
    }

    //is valid instructor
    private boolean isValidInstructor(String name) {
        if(departmentList.size()>0) {
            for(Department department : departmentList) {
                if (department.instructorExistFindByName(name)) {
                        return false;

                }
            }
        }
        return true;
    }

    //get all department in the system
    private void getDepartmentList(){
        departmentListModel.removeAllElements();
        instructorListModel.removeAllElements();
        for(Department department : departmentList) {
            departmentListModel.addElement(department.getName());
        }

    }

    //get the department's instructorlist
    private void getInstructorList(Department department){
        for(Instructor instructor : department.getInstructorList()) {
            instructorListModel.addElement(instructor.toString());
            getLectureList(instructor);
            }
            instructorJList.setModel(instructorListModel);
    }

    //get students which take the lecture(arg)
    private void getStudentList(Lecture lecture){

        for (Student student : lecture.getStudentList()) {
            studentListModel.addElement(student.toString());
        }
        for (Exam exam : lecture.getExamList()){
            examListModel.addElement(exam.getExamName());
        }

        studentJList.setModel(studentListModel);
        examJList.setModel(examListModel);


    }

    //get all lists to print out to gui
    private void getAllList(Department department){

        instructorListModel.removeAllElements();
        lectureListModel.removeAllElements();
        studentListModel.removeAllElements();
        examListModel.removeAllElements();

        for (Instructor instructor : department.getInstructorList()) {
            instructorListModel.addElement(instructor.toString());
            for (Lecture lecture : instructor.getLectureList()){
                lectureListModel.addElement(lecture.toString());
                for (Student student : lecture.getStudentList()){
                    studentListModel.addElement(student.toString());
                }
                for (Exam exam : lecture.getExamList()){
                    examListModel.addElement(exam.getExamName());
                }
            }
        }

            instructorJList.setModel(instructorListModel);
            lectureJList.setModel(lectureListModel);
            studentJList.setModel(studentListModel);
            examJList.setModel(examListModel);
        }

    //get lectures given by the instructor(arg)
    private void getLectureList(Instructor instructor){
        lectureListModel.removeAllElements();
        studentListModel.removeAllElements();
        examListModel.removeAllElements();
        for (Lecture lecture : instructor.getLectureList()){
            lectureListModel.addElement(lecture.toString());
            getStudentList(lecture);
        }
        lectureJList.setModel(lectureListModel);
    }

    //get student grade from the lecture(arg)
    private void getStudentGradeList(String studentNumber, Lecture lecture){

                for(Student student : lecture.getStudentList()) {
                    if (student.getNumber().equals(studentNumber)) {
                        for (Exam exam : lecture.getExamList()) {
                            examListModel.addElement(exam.getExamName() + ":" +exam.getStudentExamGrade(studentNumber).toString());
                        }
                    }
                }


    }

    //get department object according to department name(arg)
    private Department getDepartmen(String name){
        for(Department department : departmentList){
            if(department.getName().equals(name)){
                return department;
            }
        }
        return null;
    }

}