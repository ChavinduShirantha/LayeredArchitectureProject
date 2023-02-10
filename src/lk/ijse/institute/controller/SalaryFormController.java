package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import lk.ijse.institute.bo.BOFactory;
import lk.ijse.institute.bo.custom.SalaryBO;
import lk.ijse.institute.bo.custom.TeacherBO;
import lk.ijse.institute.dto.SalaryDTO;
import lk.ijse.institute.dto.TeacherDTO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SalaryFormController {
    @FXML
    private JFXButton btnAddNewSalaryID;
    @FXML
    private DatePicker date1;
    @FXML
    private JFXTextField txtsalaryId;
    @FXML
    private JFXTextField txtAmount;
    @FXML
    private JFXTextField txtTime;
    @FXML
    private Label lblTeacherName;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnResetFields;
    @FXML
    private JFXComboBox cmbTeacherId;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    SalaryBO salaryBO = (SalaryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SALARY);
    TeacherBO teacherBO = (TeacherBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TEACHER);

    public void initialize() {
        loadDate();
        loadTime();
        loadTeacherIDs();
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

    public void addOnAction(ActionEvent actionEvent) {
        String sal_id = txtsalaryId.getText();
        String t_id = String.valueOf(cmbTeacherId.getValue());
        String t_name = lblTeacherName.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        String date = String.valueOf(date1.getValue());
        String time = txtTime.getText();


        try {
            SalaryDTO salaryDTO = new SalaryDTO(sal_id, t_id, t_name, amount, date, time);
            boolean b = salaryBO.addSalary(salaryDTO);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary Detail Added Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Salary Detail Added Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String sal_id = txtsalaryId.getText();

        try {
            boolean b = salaryBO.deleteSalary(sal_id);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary Detail Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Salary Detail Deleted Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        String sal_id = txtsalaryId.getText();
        String t_id = String.valueOf(cmbTeacherId.getValue());
        String t_name = lblTeacherName.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        String date = String.valueOf(date1.getValue());
        String time = txtTime.getText();

        try {
            boolean b = salaryBO.updateSalary(new SalaryDTO(sal_id, t_id, t_name, amount, date, time));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary Detail Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Salary Detail Updated Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        String sal_id = txtsalaryId.getText();

        try {
            SalaryDTO salaryDTO = salaryBO.searchSalary(sal_id);
            cmbTeacherId.setValue(salaryDTO.getT_id());
            lblTeacherName.setText(salaryDTO.getT_name());
            txtAmount.setText(String.valueOf(salaryDTO.getAmount()));
            txtTime.setText(salaryDTO.getTime());
            date1.setValue(LocalDate.parse(salaryDTO.getDate()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void resetFieldsOnAction(ActionEvent actionEvent) {
        txtsalaryId.clear();
        txtTime.clear();
        txtAmount.clear();
        lblTeacherName.setText("");
        cmbTeacherId.setValue("");

    }

    private void loadTeacherIDs() {
        try {
            ArrayList<TeacherDTO> allTeachers = teacherBO.getAllTeachers();
            for (TeacherDTO t : allTeachers) {
                cmbTeacherId.getItems().add(t.getT_id());
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbTeacherIdOnAction(ActionEvent actionEvent) {
        String t_id = (String) cmbTeacherId.getValue();
        try {
            TeacherDTO teacherDTO = teacherBO.searchTeacher(t_id);
            lblTeacherName.setText(teacherDTO.getFirstName() + " " + teacherDTO.getLastName());

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private String generateNewId() {
        try {
            return salaryBO.generateNewSalaryID();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtsalaryId.setText(generateNewId());
    }

}
