package lk.ijse.institute.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import lk.ijse.institute.bo.BOFactory;
import lk.ijse.institute.bo.custom.StudentBO;
import lk.ijse.institute.bo.custom.TeacherBO;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DashBoardDetailController {
    @FXML
    private Label lblTotStudent;
    @FXML
    private Label lblTotMaleStudent;
    @FXML
    private Label lblTotFemaleStudent;
    @FXML
    private Label lblTotTeachers;
    @FXML
    private Label lblTotMaleTeachers;
    @FXML
    private Label lblFemaleTeachers;
    @FXML
    private BarChart barchart;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblStudentCount;
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    TeacherBO teacherBO = (TeacherBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TEACHER);

    public void initialize() {
        loadDate();
        loadTime();
        AllCountStudent();
        AllCountMaleStudent();
        AllCountFemaleStudent();
        AllCountTeacher();
        AllCountMaleTeacher();
        AllCountFemaleTeacher();
        barChart();
    }

    public void loadDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    public void loadTime() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(time);
                });

            }

        });
        thread.start();
    }

    public void AllCountStudent() {
        int count = 0;
        try {
            int studentDTO = studentBO.countAllStudent(count);
            lblTotStudent.setText(String.valueOf(studentDTO));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AllCountMaleStudent() {
        int count = 0;
        try {
            int studentDTO = studentBO.countAllMaleStudent(count);
            lblTotMaleStudent.setText(String.valueOf(studentDTO));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AllCountFemaleStudent() {
        int count = 0;
        try {
            int studentDTO = studentBO.countAllFemaleStudent(count);
            lblTotFemaleStudent.setText(String.valueOf(studentDTO));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AllCountTeacher() {
        int count = 0;
        try {
            int teacher = teacherBO.countAllTeacher(count);
            lblTotTeachers.setText(String.valueOf(teacher));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AllCountMaleTeacher() {
        int count = 0;
        try {
            int teacher = teacherBO.countAllMaleTeacher(count);
            lblTotMaleTeachers.setText(String.valueOf(teacher));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AllCountFemaleTeacher() {
        int count = 0;
        try {
            int teacher = teacherBO.countAllFemaleTeacher(count);
            lblFemaleTeachers.setText(String.valueOf(teacher));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void barChart() {
        barchart.setTitle("Enrolled Participant Details");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Students");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Teachers");


        int count = 0;
        try {
            int studentDTO = studentBO.countAllStudent(count);

            series1.getData().add(new XYChart.Data("Total Student ", studentDTO));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            int teacher = teacherBO.countAllTeacher(count);

            series2.getData().add(new XYChart.Data("Total Teachers ", teacher));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            int studentDTO = studentBO.countAllMaleStudent(count);

            series1.getData().add(new XYChart.Data("Total Male Students ", studentDTO));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            int studentDTO = studentBO.countAllFemaleStudent(count);

            series1.getData().add(new XYChart.Data("Total Female Students ", studentDTO));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            int teacher = teacherBO.countAllMaleTeacher(count);

            series2.getData().add(new XYChart.Data("Total Male Teachers ", teacher));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            int teacher = teacherBO.countAllFemaleTeacher(count);

            series2.getData().add(new XYChart.Data("Total Female Teachers ", teacher));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        barchart.getData().addAll(series1, series2);
    }

}
