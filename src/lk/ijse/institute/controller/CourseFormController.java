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
import lk.ijse.institute.bo.custom.CourseBO;
import lk.ijse.institute.bo.custom.SubjectBO;
import lk.ijse.institute.dto.CourseDTO;
import lk.ijse.institute.dto.CourseDetailDTO;
import lk.ijse.institute.dto.SubjectDTO;
import lk.ijse.institute.view.tm.CourseFormTM;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class CourseFormController {
    @FXML
    private JFXButton btnAddNewCourseID;
    @FXML
    private TableColumn colsubId;
    @FXML
    private TableColumn colsubName;
    @FXML
    private TableColumn colcoursefee;
    @FXML
    private JFXTextField txtCourseId;
    @FXML
    private JFXTextField txtCourseName;
    @FXML
    private JFXTextField txtCourseFee;
    @FXML
    private JFXComboBox cmbSubId;
    @FXML
    private Label lblSubName;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnResetFields;
    @FXML
    private TableView<CourseFormTM> tblCourse;
    @FXML
    private TableColumn colAction;
    @FXML
    private JFXButton btnAddSubjectCourse;
    @FXML
    private JFXButton btnAddCourse;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);
    SubjectBO subjectBO = (SubjectBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUBJECT);

    private ObservableList<CourseFormTM> obList = FXCollections.observableArrayList();

    public void initialize() {
        loadDate();
        loadTime();
        loadSubjectIDs();
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

    public void resetFieldsOnAction(ActionEvent actionEvent) {
        txtCourseId.clear();
        txtCourseName.clear();
        txtCourseFee.clear();
        cmbSubId.setValue("");
        lblSubName.setText("");
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String c_id = txtCourseId.getText();

        try {
            CourseDTO courseDTO = courseBO.searchCourse(c_id);
            txtCourseName.setText(courseDTO.getCourse_name());
            txtCourseFee.setText(String.valueOf(courseDTO.getCourse_fee()));
            cmbSubId.setValue(courseDTO.getSub_id());
            lblSubName.setText(courseDTO.getSub_name());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String c_id = txtCourseId.getText();
        String c_name = txtCourseName.getText();
        double coursefee = Double.parseDouble((txtCourseFee.getText()));
        String sub_id = String.valueOf(cmbSubId.getValue());
        String sub_name = lblSubName.getText();

        try {
            boolean b = courseBO.updateCourse(new CourseDTO(c_id, c_name, coursefee, sub_id, sub_name));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Course Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Course Updated Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String c_id = txtCourseId.getText();

        try {
            boolean b = courseBO.deleteCourse(c_id);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Course Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Course Deleted Failed!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbSubIdOnAction(ActionEvent actionEvent) {
        String sub_id = (String) cmbSubId.getValue();
        try {
            SubjectDTO subjectDTO = subjectBO.searchSubject(sub_id);
            lblSubName.setText(subjectDTO.getSubName());

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addSubjectToCourseOnAction(ActionEvent actionEvent) {
        String subId = String.valueOf(cmbSubId.getValue());
        String subName = lblSubName.getText();
        double coursefee = Double.parseDouble(txtCourseFee.getText());
        Button btnDelete = new Button("Delete");


        if (!obList.isEmpty()) {

            for (int i = 0; i < tblCourse.getItems().size(); i++) {
                if (colsubId.getCellData(i).equals(subId)) {
                    obList.get(i).setSub_name(subName);
                    obList.get(i).setCourseFee(coursefee);
                    tblCourse.refresh();
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
                CourseFormTM tm = tblCourse.getSelectionModel().getSelectedItem();

                tblCourse.getItems().removeAll(tblCourse.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new CourseFormTM(subId, subName, coursefee, btnDelete));
        tblCourse.setItems(obList);
        String course_id = txtCourseId.getText();
        String course_name = txtCourseName.getText();
        Double course_fee = Double.valueOf(txtCourseFee.getText());
        String sub_id = String.valueOf(cmbSubId.getValue());
        String sub_name = lblSubName.getText();
        try {
            courseBO.addCourse(new CourseDTO(course_id, course_name, course_fee, sub_id, sub_name));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addCourseOnAction(ActionEvent actionEvent) {

        ArrayList<CourseDetailDTO> courseDetailDTOS = new ArrayList<>();


        for (int i = 0; i < tblCourse.getItems().size(); i++) {

            CourseFormTM tm = obList.get(i);
            courseDetailDTOS.add(new CourseDetailDTO(tm.getSub_id(), tm.getSub_name(), tm.getCourseFee()));
        }
        obList.clear();
        new Alert(Alert.AlertType.CONFIRMATION, "Course Added Successfully!").show();
    }

    private void loadSubjectIDs() {
        try {
            ArrayList<SubjectDTO> allSubjects = subjectBO.getAllSubjects();
            for (SubjectDTO s : allSubjects) {
                cmbSubId.getItems().add(s.getSubId());
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colsubId.setCellValueFactory(new PropertyValueFactory("sub_id"));
        colsubName.setCellValueFactory(new PropertyValueFactory("sub_name"));
        colcoursefee.setCellValueFactory(new PropertyValueFactory("courseFee"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
    }

    private String generateNewId() {
        try {
            return courseBO.generateNewCourseID();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtCourseId.setText(generateNewId());
    }

}
