package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.institute.bo.BOFactory;
import lk.ijse.institute.bo.custom.SubjectBO;
import lk.ijse.institute.dto.SubjectDTO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

public class SubjectFormController {
    @FXML
    private JFXButton btnAddNewSubjectID;
    @FXML
    private JFXTextField txtSubId;
    @FXML
    private JFXTextField txtSubName;
    @FXML
    private JFXTextField txtSubHours;
    @FXML
    private JFXTextField txtTeacherId;
    @FXML
    private JFXTextField txtTeacherName;
    @FXML
    private JFXButton btnReset;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    SubjectBO subjectBO = (SubjectBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUBJECT);

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

    public void addOnAction(ActionEvent actionEvent) {
        String subId = txtSubId.getText();
        String subName = txtSubName.getText();
        int subHours = Integer.parseInt(txtSubHours.getText());
        String tId = txtTeacherId.getText();
        String tName = txtTeacherName.getText();

        Pattern patternName = Pattern.compile("^[A-Z][a-zA-Z]+$");

        if (patternName.matcher(subName).matches()) {

            try {
                boolean b = subjectBO.addSubject(new SubjectDTO(subId, subName, subHours, tId, tName));
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Subject Added Successfully!").show();
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else {
            if (!patternName.matcher(tName).matches()) {
                txtTeacherName.setFocusColor(Paint.valueOf("red"));
                new Alert(Alert.AlertType.ERROR, "Subject Added Failed!").show();
            }
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        String sub_id = txtSubId.getText();

        try {
            SubjectDTO subjectDTO = subjectBO.searchSubject(sub_id);
            txtSubName.setText(subjectDTO.getSubName());
            txtSubHours.setText(String.valueOf(subjectDTO.getSubHours()));
            txtTeacherId.setText(subjectDTO.getT_id());
            txtTeacherName.setText(subjectDTO.getT_name());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateOnAction(ActionEvent actionEvent) {
        String subId = txtSubId.getText();
        String subName = txtSubName.getText();
        int subHours = Integer.parseInt(txtSubHours.getText());
        String tId = txtTeacherId.getText();
        String tName = txtTeacherName.getText();


        try {
            boolean b = subjectBO.updateSubject(new SubjectDTO(subId, subName, subHours, tId, tName));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Subject Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Subject Updated Failed!").show();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String sub_id = txtSubId.getText();

        try {
            boolean b = subjectBO.deleteSubject(sub_id);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Subject Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Subject Deleted Failed!").show();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void resetOnAction(ActionEvent actionEvent) {
        txtSubId.clear();
        txtSubName.clear();
        txtTeacherId.clear();
        txtSubHours.clear();
        txtTeacherName.clear();
    }

    public void subIdOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtSubName.requestFocus();
        }
    }

    public void subNameOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtSubHours.requestFocus();
        }
    }

    public void subHoursOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtTeacherId.requestFocus();
        }
    }

    public void TeacherIDOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtTeacherName.requestFocus();
        }
    }

    private String generateNewId() {
        try {
            return subjectBO.generateNewSubjectID();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtSubId.setText(generateNewId());
    }

}
