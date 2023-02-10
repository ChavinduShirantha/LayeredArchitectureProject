package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.institute.bo.BOFactory;
import lk.ijse.institute.bo.custom.StudentBO;
import lk.ijse.institute.bo.custom.TeacherBO;

import lk.ijse.institute.navigate.Navigation;
import lk.ijse.institute.navigate.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DashBoardFormController {
    @FXML
    private Label lblCountAllStudent;
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
    private JFXButton btnStudentMarksForm;
    @FXML
    private JFXButton btnStudentAttendance;
    @FXML
    private JFXButton btnSubjectForm;
    @FXML
    private JFXButton btnExam;
    @FXML
    private JFXButton btnPayment;
    @FXML
    private JFXButton btnCourse;
    @FXML
    private JFXButton btnBatch;
    @FXML
    private JFXButton btnSalary;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblDate;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private AnchorPane pane1;
    @FXML
    private JFXButton btnTeacherForm;
    @FXML
    private JFXButton btnDashBoardDetailForm;
    @FXML
    private JFXButton btnStudentForm;
    @FXML
    private JFXButton btnBack;
    @FXML
    private Label lblUserNameDashBoard;
    @FXML
    private AnchorPane pane;
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

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Login, pane);
    }

    public void studentFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Student, pane1);
    }

    public void teacherFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Teacher, pane1);
    }

    public void dashBoardFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DashBoardDetail, pane1);
    }

    public void logoutOnAction(ActionEvent actionEvent) {
        System.exit(0);
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

    public void StudentMarksFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Marks, pane1);
    }

    public void StudentAttendanceFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Attendance, pane1);
    }

    public void subjectFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Subject, pane1);
    }

    public void examFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Exam, pane1);
    }

    public void paymentFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Payment, pane1);
    }

    public void courseFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Course, pane1);
    }

    public void batchFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Batch, pane1);
    }

    public void salaryFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.Salary, pane1);
    }

    public void AllCountStudent() {
        int count = 0;
        try {
            int studentDTO = studentBO.countAllStudent(count);
            lblCountAllStudent.setText(String.valueOf(studentDTO));

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
