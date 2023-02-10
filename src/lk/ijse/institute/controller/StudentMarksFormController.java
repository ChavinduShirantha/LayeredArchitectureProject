package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.institute.bo.BOFactory;
import lk.ijse.institute.bo.custom.StudentBO;
import lk.ijse.institute.bo.custom.StudentMarksBO;
import lk.ijse.institute.bo.custom.SubjectBO;

import lk.ijse.institute.dto.StudentDTO;
import lk.ijse.institute.dto.StudentMarkDTO;
import lk.ijse.institute.dto.SubjectDTO;
import lk.ijse.institute.view.tm.StudentMarkFormTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class StudentMarksFormController {
    @FXML
    private TableView<StudentMarkFormTM> tblStudentMark;
    @FXML
    private TableColumn colstd_id;
    @FXML
    private TableColumn colstd_name;
    @FXML
    private TableColumn colsubId;
    @FXML
    private TableColumn colsubName;
    @FXML
    private TableColumn colsubMark;
    @FXML
    private TableColumn colAction;
    @FXML
    private JFXButton btnAddtable;
    @FXML
    private JFXButton btnDeleted;
    @FXML
    private JFXButton btnReset;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXComboBox cmbStudentId;
    @FXML
    private Label lblStudentName;
    @FXML
    private JFXComboBox cmbSubjectId;
    @FXML
    private Label lblSubjectName;
    @FXML
    private JFXTextField txtsubMark;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    StudentMarksBO marksBO = (StudentMarksBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT_MARKS);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    SubjectBO subjectBO = (SubjectBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUBJECT);

    private ObservableList<StudentMarkFormTM> obList = FXCollections.observableArrayList();

    public void initialize() {
        loadDate();
        loadTime();
        loadSubjectIDs();
        loadStudentIDs();
        setCellValueFactory();

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

    public void resetOnAction(ActionEvent actionEvent) {
        txtsubMark.clear();
        lblStudentName.setText("");
        lblSubjectName.setText("");
    }

    public void addOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.CONFIRMATION, "Student Marks Added Successfully!").show();
        obList.clear();
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String std_id = String.valueOf(cmbStudentId.getValue());

        try {
            StudentMarkDTO studentMarkDTO = marksBO.searchStudentMarks(std_id);
            lblStudentName.setText(studentMarkDTO.getStd_name());
            cmbSubjectId.setValue(studentMarkDTO.getSub_id());
            lblSubjectName.setText(studentMarkDTO.getSub_name());
            txtsubMark.setText(String.valueOf(studentMarkDTO.getMarks()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        String std_id = String.valueOf(cmbStudentId.getValue());
        String std_name = lblStudentName.getText();
        String sub_id = String.valueOf(cmbSubjectId.getValue());
        String sub_name = lblSubjectName.getText();
        double mark = Double.parseDouble(txtsubMark.getText());

        try {
            boolean b = marksBO.updateStudentMarks(new StudentMarkDTO(std_id, std_name, sub_id, sub_name, mark));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Marks Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student Marks Updated Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String std_id = String.valueOf(cmbStudentId.getValue());

        try {
            boolean b = marksBO.deleteStudentMarks(std_id);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Marks Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student Marks Deleted Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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

    public void cmbStudentIdOnAction(ActionEvent actionEvent) {
        String std_id = (String) cmbStudentId.getValue();
        try {
            StudentDTO studentDTO = studentBO.searchStudent(std_id);
            lblStudentName.setText(studentDTO.getFirstName() + " " + studentDTO.getLastName());

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
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

    public void cmbSubjectIdOnAction(ActionEvent actionEvent) {
        String sub_id = (String) cmbSubjectId.getValue();
        try {
            SubjectDTO subjectDTO = subjectBO.searchSubject(sub_id);
            lblSubjectName.setText(subjectDTO.getSubName());

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addTableOnAction(ActionEvent actionEvent) {
        String std_id = String.valueOf(cmbStudentId.getValue());
        String stdName = lblStudentName.getText();
        String sub_id = String.valueOf(cmbSubjectId.getValue());
        String sub_name = lblSubjectName.getText();
        double mark = Double.parseDouble(txtsubMark.getText());
        Button btnDelete = new Button("Delete");

        if (!obList.isEmpty()) {

            for (int i = 0; i < tblStudentMark.getItems().size(); i++) {
                if (colstd_id.getCellData(i).equals(std_id)) {
                    obList.get(i).setStd_name(stdName);
                    obList.get(i).setMark(mark);
                    tblStudentMark.refresh();
                    return;
                }
            }
        }

        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                StudentMarkFormTM tm = tblStudentMark.getSelectionModel().getSelectedItem();

                tblStudentMark.getItems().removeAll(tblStudentMark.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new StudentMarkFormTM(std_id, stdName, sub_id, sub_name, mark, btnDelete));
        tblStudentMark.setItems(obList);

        try {
            marksBO.addStudentMarks(new StudentMarkDTO(std_id, stdName, sub_id, sub_name, mark));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colstd_id.setCellValueFactory(new PropertyValueFactory("std_id"));
        colstd_name.setCellValueFactory(new PropertyValueFactory("std_name"));
        colsubId.setCellValueFactory(new PropertyValueFactory("sub_id"));
        colsubName.setCellValueFactory(new PropertyValueFactory("sub_name"));
        colsubMark.setCellValueFactory(new PropertyValueFactory("mark"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
    }

}
