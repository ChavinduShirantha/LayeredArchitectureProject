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
import lk.ijse.institute.bo.custom.BatchBO;
import lk.ijse.institute.bo.custom.CourseBO;
import lk.ijse.institute.bo.custom.StudentBO;
import lk.ijse.institute.dto.BatchDTO;
import lk.ijse.institute.dto.CourseDTO;
import lk.ijse.institute.dto.StudentDTO;
import lk.ijse.institute.view.tm.BatchFormTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class BatchFormController {

    @FXML
    private JFXButton btnAddNewBatchID;
    @FXML
    private TableColumn colCourseId;
    @FXML
    private TableColumn colCourseName;
    @FXML
    private TableColumn colBatchId;
    @FXML
    private JFXComboBox cmbStudentId;
    @FXML
    private Label lblStudentName;
    @FXML
    private JFXComboBox cmbCourseId;
    @FXML
    private TableColumn colstd_id;
    @FXML
    private TableColumn colStudentName;
    @FXML
    private TableColumn colAction;
    @FXML
    private JFXButton btnAddTable;
    @FXML
    private JFXTextField txtBatchid;
    @FXML
    private JFXTextField txtbatchname;
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
    private JFXTextField txtCourseId;
    @FXML
    private JFXTextField txtCourseName;
    @FXML
    private TableView<BatchFormTM> tblStudentdetails;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    BatchBO batchBO = (BatchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BATCH);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    private ObservableList<BatchFormTM> obList = FXCollections.observableArrayList();

    public void initialize() {
        loadDate();
        loadTime();
        loadStudentIDs();
        loadCourseIDs();
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

    public void addOnAction(ActionEvent actionEvent) {
        obList.clear();
        new Alert(Alert.AlertType.CONFIRMATION, "Batch Added Successfully!").show();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String b_id = txtBatchid.getText();

        try {
            boolean b = batchBO.deleteBatch(b_id);

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Batch Deleted Failed !");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String batch_id = txtBatchid.getText();
        String batch_name = txtbatchname.getText();
        String course_id = String.valueOf(cmbCourseId.getValue());
        String course_name = txtCourseName.getText();
        String std_id = String.valueOf(cmbStudentId.getValue());
        String std_name = lblStudentName.getText();

        try {
            boolean b = batchBO.updateBatch(new BatchDTO(batch_id, batch_name, course_id, course_name, std_id, std_name));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Batch Updated Failed !");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String b_id = txtBatchid.getText();

        try {
            BatchDTO batchDTO = batchBO.searchBatch(b_id);
            txtbatchname.setText(batchDTO.getBatchName());
            cmbCourseId.setValue(batchDTO.getCourseId());
            txtCourseName.setText(batchDTO.getCourseName());
            cmbStudentId.setValue(batchDTO.getStd_id());
            lblStudentName.setText(batchDTO.getStd_name());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void resetFieldsOnAction(ActionEvent actionEvent) {
        txtBatchid.clear();
        txtbatchname.clear();
        cmbCourseId.setValue("");
        txtCourseName.clear();
        cmbStudentId.setValue("");
        lblStudentName.setText("");
    }

    public void addTableOnAction(ActionEvent actionEvent) {
        String b_id = txtBatchid.getText();
        String b_name = txtbatchname.getText();
        String c_id = String.valueOf(cmbCourseId.getValue());
        String c_name = txtCourseName.getText();
        String std_id = String.valueOf(cmbStudentId.getValue());
        String stdName = lblStudentName.getText();
        Button btnDelete = new Button("Delete");

        if (!obList.isEmpty()) {

            for (int i = 0; i < tblStudentdetails.getItems().size(); i++) {
                if (colstd_id.getCellData(i).equals(std_id)) {
                    obList.get(i).setStd_id(stdName);
                    tblStudentdetails.refresh();
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
                BatchFormTM tm = tblStudentdetails.getSelectionModel().getSelectedItem();

                tblStudentdetails.getItems().removeAll(tblStudentdetails.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new BatchFormTM(b_id, c_id, c_name, std_id, stdName, btnDelete));
        tblStudentdetails.setItems(obList);

        try {
            batchBO.addBatch(new BatchDTO(b_id, b_name, c_id, c_name, std_id, stdName));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colBatchId.setCellValueFactory(new PropertyValueFactory("batch_id"));
        colCourseId.setCellValueFactory(new PropertyValueFactory("c_id"));
        colCourseName.setCellValueFactory(new PropertyValueFactory("course_name"));
        colstd_id.setCellValueFactory(new PropertyValueFactory("std_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory("std_name"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
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

    public void cmbCourseIdOnAction(ActionEvent actionEvent) {
        String c_id = (String) cmbCourseId.getValue();
        try {
            CourseDTO courseDTO = courseBO.searchCourse(c_id);
            txtCourseName.setText(courseDTO.getCourse_name());

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
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

    private void loadCourseIDs() {

        try {
            ArrayList<CourseDTO> allCourses = courseBO.getAllCourses();
            for (CourseDTO c : allCourses) {
                cmbCourseId.getItems().add(c.getCourse_id());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private String generateNewId() {
        try {
            return batchBO.generateNewBatchID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtBatchid.setText(generateNewId());
    }

}
