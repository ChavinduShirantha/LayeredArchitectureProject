package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import lk.ijse.institute.bo.BOFactory;
import lk.ijse.institute.bo.custom.StudentAttendanceBO;
import lk.ijse.institute.bo.custom.StudentBO;
import lk.ijse.institute.db.DBConnection;

import lk.ijse.institute.dto.StudentAttendanceDTO;
import lk.ijse.institute.dto.StudentDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;


import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class StudentAttendanceFormController {
    @FXML
    private Label lblDateAttend;
    @FXML
    private JFXComboBox cmbStudentId;
    @FXML
    private Label lblStudentName;
    @FXML
    private JFXButton btngetReport;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnReset;
    @FXML
    private DatePicker adddate;
    @FXML
    private JFXRadioButton rbtnAbsent;
    @FXML
    private ToggleGroup Attendance;
    @FXML
    private JFXRadioButton rbtnPresent;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    StudentAttendanceBO attendanceBO = (StudentAttendanceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT_ATTENDANCE);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() {
        loadDate();
        loadTime();
        loadStudentIDs();
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

    public void cmbStudentIdOnAction(ActionEvent actionEvent) {
        String std_id = (String) cmbStudentId.getValue();
        try {
            StudentDTO studentDTO = studentBO.searchStudent(std_id);
            lblStudentName.setText(studentDTO.getFirstName() + " " + studentDTO.getLastName());

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void resetOnAction(ActionEvent actionEvent) {
        lblStudentName.setText("");
        rbtnAbsent.setSelected(false);
        rbtnPresent.setSelected(false);
        cmbStudentId.setValue("");
    }

    public void addOnAction(ActionEvent actionEvent) {
        String std_id = String.valueOf(cmbStudentId.getValue());
        String std_name = lblStudentName.getText();
        String date = String.valueOf(adddate.getValue());

        if (rbtnAbsent.isSelected()) {
            String attendance = rbtnAbsent.getText();

            try {
                boolean b = attendanceBO.addStudentAttendance(new StudentAttendanceDTO(std_id, std_name, date, attendance));
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Attendance Added Successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Student Attendance Added Failed!").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

        if (rbtnPresent.isSelected()) {
            String attendance = rbtnPresent.getText();

            try {
                boolean b = attendanceBO.addStudentAttendance(new StudentAttendanceDTO(std_id, std_name, date, attendance));
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Attendance Added Successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Student Attendance Added Failed!").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        String std_id = String.valueOf(cmbStudentId.getValue());

        try {
            StudentAttendanceDTO studentAttendanceDTO = attendanceBO.searchStudentAttendance(std_id);
            lblStudentName.setText(studentAttendanceDTO.getStd_name());
            adddate.setValue(LocalDate.parse(studentAttendanceDTO.getDate()));
            String attendance = studentAttendanceDTO.getAttendance();
            if (attendance.equals("Absent")) {
                rbtnAbsent.setSelected(true);
            } else {
                rbtnPresent.setSelected(true);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        String std_id = String.valueOf(cmbStudentId.getValue());
        String std_name = lblStudentName.getText();
        String date = String.valueOf(adddate.getValue());

        if (rbtnAbsent.isSelected()) {
            String attendance = rbtnAbsent.getText();

            try {
                boolean b = attendanceBO.updateStudentAttendance(new StudentAttendanceDTO(std_id, std_name, date, attendance));
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Attendance Updated Successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Student Attendance Updated Failed!").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

        if (rbtnPresent.isSelected()) {
            String attendance = rbtnPresent.getText();

            try {
                boolean b = attendanceBO.updateStudentAttendance(new StudentAttendanceDTO(std_id, std_name, date, attendance));
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Attendance Updated Successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Student Attendance Updated Failed!").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String std_id = String.valueOf(cmbStudentId.getValue());

        try {
            boolean b = attendanceBO.deleteStudentAttendance(std_id);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Attendance Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student Attendance Deleted Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btngetReportOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/institute/report/StudentAttendance.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint);
        } catch (ClassNotFoundException | SQLException | JRException e) {
            e.printStackTrace();
        }
    }

    private void loadStudentIDs() {
        try {
            ArrayList<StudentDTO> allStudents = studentBO.getAllStudents();
            for (StudentDTO s : allStudents) {
                cmbStudentId.getItems().add(s.getStd_id());
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
