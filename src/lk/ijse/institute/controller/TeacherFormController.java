package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.institute.bo.BOFactory;
import lk.ijse.institute.bo.custom.TeacherBO;
import lk.ijse.institute.db.DBConnection;
import lk.ijse.institute.dto.TeacherDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

public class TeacherFormController {
    @FXML
    private JFXButton btnAddNewTeacherID;
    @FXML
    private JFXButton btngetReport;
    @FXML
    private DatePicker DateOfBirth;
    @FXML
    private ToggleGroup gender;
    @FXML
    private JFXTextField txtDateOfBirth;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private JFXTextField txtTeacherId;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXRadioButton rbtnMale;
    @FXML
    private JFXRadioButton rbtnFemale;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private DatePicker date;
    @FXML
    private JFXButton btnReset;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnUpdate;
    TeacherBO teacherBO = (TeacherBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TEACHER);

    public void resetOnAction(ActionEvent actionEvent) {
        txtTeacherId.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtAddress.clear();
        rbtnMale.setSelected(false);
        rbtnFemale.setSelected(false);
        txtContact.clear();
        txtEmail.clear();
        txtDateOfBirth.clear();
    }

    public void addOnAction(ActionEvent actionEvent) {
        String t_id = txtTeacherId.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        String dob = String.valueOf(DateOfBirth.getValue());

        Pattern patternName = Pattern.compile("^[A-Z][a-zA-Z]+$");
        Pattern patternAddress = Pattern.compile("^[A-Z][a-zA-Z]+$");
        Pattern patternContact = Pattern.compile("^(?:\\+94|070|071|072|074|075|076|077|078)[0-9]{7,9}$");
        Pattern patternEmail = Pattern.compile("([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})");


        if (patternName.matcher(firstName).matches() & patternName.matcher(lastName).matches() &
                patternAddress.matcher(address).matches() & patternContact.matcher(contact).matches() & patternEmail.matcher(email).matches()) {
            if (rbtnMale.isSelected()) {
                String gender = rbtnMale.getText();

                try {
                    boolean b = teacherBO.addTeacher(new TeacherDTO(t_id, firstName, lastName, address, gender, contact, email, dob));
                    if (b) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Teacher Added Successfully!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Teacher Added Failed!").show();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            } else if (rbtnFemale.isSelected()) {
                String gender = rbtnFemale.getText();

                try {
                    boolean b = teacherBO.addTeacher(new TeacherDTO(t_id, firstName, lastName, address, gender, contact, email, dob));
                    if (b) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Teacher Added Successfully!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Teacher Added Failed!").show();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }

        } else {
            if (!patternName.matcher(firstName).matches()) {
                txtFirstName.setFocusColor(Paint.valueOf("Red"));
            }
            if (!patternName.matcher(lastName).matches()) {
                txtLastName.setFocusColor(Paint.valueOf("Red"));
            }
            if (!patternAddress.matcher(address).matches()) {
                txtAddress.setFocusColor(Paint.valueOf("Red"));
            }
            if (!patternEmail.matcher(email).matches()) {
                txtEmail.setFocusColor(Paint.valueOf("Red"));
            }
            if (!patternContact.matcher(lastName).matches()) {
                txtContact.setFocusColor(Paint.valueOf("Red"));
            }
            new Alert(Alert.AlertType.ERROR, "Teacher Added Failed!").show();
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        String t_id = txtTeacherId.getText();

        try {
            TeacherDTO teacherDTO = teacherBO.searchTeacher(t_id);
            txtFirstName.setText(teacherDTO.getFirstName());
            txtLastName.setText(teacherDTO.getLastName());
            txtAddress.setText(teacherDTO.getAddress());
            String gender = teacherDTO.getGender();
            if (gender.equals("Male")) {
                rbtnMale.setSelected(true);
            } else {
                rbtnFemale.setSelected(true);
            }
            txtContact.setText(teacherDTO.getContact());
            txtEmail.setText(teacherDTO.getEmail());
            DateOfBirth.setValue(LocalDate.parse(teacherDTO.getDate_of_birth()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String t_id = txtTeacherId.getText();

        try {
            boolean b = teacherBO.deleteTeacher(t_id);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Teacher Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Teacher Deleted Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        String t_id = txtTeacherId.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        String dob = String.valueOf(DateOfBirth.getValue());

        if (rbtnMale.isSelected()) {
            String gender = rbtnMale.getText();

            try {
                boolean b = teacherBO.updateTeacher(new TeacherDTO(t_id, firstName, lastName, address, gender, contact, email, dob));
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Teacher Updated Successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Teacher Updated Failed!").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else if (rbtnFemale.isSelected()) {
            String gender = rbtnFemale.getText();
            try {
                boolean b = teacherBO.updateTeacher(new TeacherDTO(t_id, firstName, lastName, address, gender, contact, email, dob));
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Teacher Updated Successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Teacher Updated Failed!").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void initialize() {
        loadDate();
        loadTime();
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

    public void tidOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtFirstName.requestFocus();
        }
    }

    public void firstNameOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtLastName.requestFocus();
        }
    }

    public void lastNameOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtAddress.requestFocus();
        }
    }

    public void contactOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtEmail.requestFocus();
        }
    }

    public void emailOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            DateOfBirth.requestFocus();
        }
    }

    public void btngetReportOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/institute/report/TeacherReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint);
        } catch (ClassNotFoundException | SQLException | JRException e) {
            e.printStackTrace();
        }
    }

    private String generateNewId() {
        try {
            return teacherBO.generateNewTeacherID();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtTeacherId.setText(generateNewId());
    }

}
