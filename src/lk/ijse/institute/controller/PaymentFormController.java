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
import lk.ijse.institute.bo.custom.BatchBO;
import lk.ijse.institute.bo.custom.PaymentBO;
import lk.ijse.institute.bo.custom.StudentBO;
import lk.ijse.institute.dto.BatchDTO;
import lk.ijse.institute.dto.PaymentDTO;
import lk.ijse.institute.dto.StudentDTO;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PaymentFormController {
    @FXML
    private JFXButton btnAddNewPaymentID;
    @FXML
    private JFXTextField txtPayment;
    @FXML
    private JFXComboBox cmbStudentId;
    @FXML
    private Label lblStudentName;
    @FXML
    private Label lblBatch;
    @FXML
    private Label lblCourse;
    @FXML
    private JFXTextField txtAmount;
    @FXML
    private JFXTextField txtTime;
    @FXML
    private DatePicker adddate;
    @FXML
    private JFXButton btnAddStudent;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnResetFields;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    BatchBO batchBO = (BatchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BATCH);

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

            BatchDTO batchDTO = batchBO.searchBatchAgain(std_id);
            lblBatch.setText(batchDTO.getBatchName());

            BatchDTO batchDTO1 = batchBO.searchBatchAgain(std_id);
            lblCourse.setText(batchDTO1.getCourseName());

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addStudentOnAction(ActionEvent actionEvent) {
        String pid = txtPayment.getText();
        String std_id = String.valueOf(cmbStudentId.getValue());
        String std_name = lblStudentName.getText();
        String batch_id = lblBatch.getText();
        String course = lblCourse.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        String date = String.valueOf(adddate.getValue());
        String time = txtTime.getText();


        try {
            PaymentDTO paymentDTO = new PaymentDTO(pid, std_id, std_name, batch_id, course, amount, date, time);
            boolean b = paymentBO.addPayment(paymentDTO);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Added Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Payment Added Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String pid = txtPayment.getText();

        try {
            boolean b = paymentBO.deletePayment(pid);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Payment Deleted Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        String pid = txtPayment.getText();
        String std_id = String.valueOf(cmbStudentId.getValue());
        String std_name = lblStudentName.getText();
        String batch_id = lblBatch.getText();
        String course = lblCourse.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        String date = String.valueOf(adddate.getValue());
        String time = txtTime.getText();

        try {
            boolean b = paymentBO.updatePayment(new PaymentDTO(pid, std_id, std_name, batch_id, course, amount, date, time));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Payment Updated Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        String pid = txtPayment.getText();

        try {
            PaymentDTO paymentDTO = paymentBO.searchPayment(pid);
            cmbStudentId.setValue(paymentDTO.getStd_id());
            lblStudentName.setText(paymentDTO.getStd_name());
            lblBatch.setText(paymentDTO.getBatch_id());
            lblCourse.setText(paymentDTO.getCourse_name());
            txtAmount.setText(String.valueOf(paymentDTO.getAmount()));
            adddate.setValue(LocalDate.parse(paymentDTO.getDate()));
            txtTime.setText(paymentDTO.getTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetFieldsOnAction(ActionEvent actionEvent) {
        txtPayment.clear();
        cmbStudentId.setValue("");
        lblStudentName.setText("");
        lblBatch.setText("");
        lblCourse.setText("");
        txtAmount.clear();
        txtTime.clear();
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

    private String generateNewId() {
        try {
            return paymentBO.generateNewPaymentID();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtPayment.setText(generateNewId());
    }

}
