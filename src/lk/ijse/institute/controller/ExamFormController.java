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
import lk.ijse.institute.bo.custom.ExamBO;
import lk.ijse.institute.bo.custom.SubjectBO;
import lk.ijse.institute.dto.ExamDTO;
import lk.ijse.institute.dto.SubjectDTO;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ExamFormController {
    @FXML
    private JFXButton btnAddNewExamID;
    @FXML
    private DatePicker date1;
    @FXML
    private JFXComboBox cmbSubjectId;
    @FXML
    private Label lblSubjectName;
    @FXML
    private JFXTextField txtExamId;
    @FXML
    private JFXTextField txtTime;
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
    ExamBO examBO = (ExamBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EXAM);
    SubjectBO subjectBO = (SubjectBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUBJECT);

    public void cmbSubjectIdOnAction(ActionEvent actionEvent) {
        String sub_id = (String) cmbSubjectId.getValue();
        try {
            SubjectDTO subjectDTO = subjectBO.searchSubject(sub_id);
            lblSubjectName.setText(subjectDTO.getSubName());

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void resetOnAction(ActionEvent actionEvent) {
        txtExamId.clear();
        cmbSubjectId.setValue("");
        lblSubjectName.setText("");
        txtTime.clear();
    }

    public void addOnAction(ActionEvent actionEvent) {
        String exam_id = txtExamId.getText();
        String sub_id = String.valueOf(cmbSubjectId.getValue());
        String subName = lblSubjectName.getText();
        String date = String.valueOf(date1.getValue());
        String time = txtTime.getText();

        try {
            boolean b = examBO.addExam(new ExamDTO(exam_id, sub_id, subName, date, time));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Exam Schedule Added Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Exam Schedule Added Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {
        String exam_id = txtExamId.getText();

        try {
            ExamDTO examDTO = examBO.searchExam(exam_id);
            cmbSubjectId.setValue(examDTO.getSub_id());
            lblSubjectName.setText(examDTO.getSub_name());
            date1.setValue(LocalDate.parse(examDTO.getDate()));
            txtTime.setText(examDTO.getTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        String exam_id = txtExamId.getText();
        String subId = String.valueOf(cmbSubjectId.getValue());
        String subName = lblSubjectName.getText();
        String date = String.valueOf(date1.getValue());
        String time = txtTime.getText();

        try {
            boolean b = examBO.updateExam(new ExamDTO(exam_id, subId, subName, date, time));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Exam Schedule Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Exam Schedule Updated Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String exam_id = txtExamId.getText();

        try {
            boolean b = examBO.deleteExam(exam_id);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Exam Schedule Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Exam Schedule Deleted Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void initialize() {
        loadDate();
        loadTime();
        loadSubjectIDs();
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

    private void loadSubjectIDs() {
        try {
            ArrayList<SubjectDTO> allSubjects = subjectBO.getAllSubjects();
            for (SubjectDTO s : allSubjects) {
                cmbSubjectId.getItems().add(s.getSubId());
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNewId() {
        try {
            return examBO.generateNewExamID();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtExamId.setText(generateNewId());
    }

}
