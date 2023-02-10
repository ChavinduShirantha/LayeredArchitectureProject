package lk.ijse.institute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.institute.bo.BOFactory;
import lk.ijse.institute.bo.custom.CreateNewAccountBO;
import lk.ijse.institute.dto.UserDTO;
import lk.ijse.institute.navigate.Navigation;
import lk.ijse.institute.navigate.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class LoginFormController {
    @FXML
    private Label lblNote;
    @FXML
    private JFXButton btnShowPassword;
    @FXML
    private ToggleGroup role;
    @FXML
    private JFXTextField txtShowPassword;
    @FXML
    private JFXButton btnHidePassword;
    @FXML
    private JFXRadioButton rbtnAdmin;
    @FXML
    private JFXRadioButton rbtnManager;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnCreateNewAccount;
    @FXML
    private AnchorPane pane;
    CreateNewAccountBO userBO = (CreateNewAccountBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CREATE_NEW_ACCOUNT);

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String user_name = txtUserName.getText();
        String pwd = password.getText();
        if (txtUserName.getText().isEmpty() || password.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all blank fields").show();
        } else {
            if (rbtnAdmin.isSelected()) {
                try {
                    ArrayList<UserDTO> allUsers = userBO.getAllUsers();
                    for (UserDTO s : allUsers) {
                        String Uname = s.getUserName();
                        String UPwd = s.getPassword();
                        String role = s.getRole();
                        if (Uname.equals(user_name) && UPwd.equals(pwd) && role.equals("Admin")) {
                            Navigation.navigate(Routes.DashBoard, pane);
                        } else {
                            txtUserName.requestFocus();
                            txtUserName.setFocusColor(Paint.valueOf("Red"));
                            password.requestFocus();
                            password.setFocusColor(Paint.valueOf("Red"));
                            lblNote.setText("Enter Valid User name & Password");
                        }
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (rbtnManager.isSelected()) {
                try {
                    ArrayList<UserDTO> allUsers = userBO.getAllUsers();
                    for (UserDTO s : allUsers) {
                        String Uname = s.getUserName();
                        String UPwd = s.getPassword();
                        String role = s.getRole();
                        if (Uname.equals(user_name) && UPwd.equals(pwd) && role.equals("Manager")) {
                            Navigation.navigate(Routes.ManagerDashBoard, pane);
                        } else {
                            txtUserName.requestFocus();
                            txtUserName.setFocusColor(Paint.valueOf("Red"));
                            password.requestFocus();
                            password.setFocusColor(Paint.valueOf("Red"));
                            lblNote.setText("Enter Valid User name & Password");
                        }
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public void createNewAccountOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CreateNewAccount, pane);
    }

    public void txtUserNameOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            password.requestFocus();
        }
    }

    public void passwordOnAction(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String user_name = txtUserName.getText();
            String pwd = password.getText();

            if (txtUserName.getText().isEmpty() || password.getText().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all blank fields").show();
            } else {
                if (rbtnAdmin.isSelected()) {
                    try {
                        ArrayList<UserDTO> allUsers = userBO.getAllUsers();
                        for (UserDTO s : allUsers) {
                            String Uname = s.getUserName();
                            String UPwd = s.getPassword();
                            String role = s.getRole();
                            if (Uname.equals(user_name) && UPwd.equals(pwd) && role.equals("Admin")) {
                                Navigation.navigate(Routes.DashBoard, pane);
                            } else {
                                txtUserName.requestFocus();
                                txtUserName.setFocusColor(Paint.valueOf("Red"));
                                password.requestFocus();
                                password.setFocusColor(Paint.valueOf("Red"));
                                lblNote.setText("Enter Valid User name & Password");
                            }
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                if (rbtnManager.isSelected()) {
                    try {
                        ArrayList<UserDTO> allUsers = userBO.getAllUsers();
                        for (UserDTO s : allUsers) {
                            String Uname = s.getUserName();
                            String UPwd = s.getPassword();
                            String role = s.getRole();
                            if (Uname.equals(user_name) && UPwd.equals(pwd) && role.equals("Manager")) {
                                Navigation.navigate(Routes.ManagerDashBoard, pane);
                            } else {
                                txtUserName.requestFocus();
                                txtUserName.setFocusColor(Paint.valueOf("Red"));
                                password.requestFocus();
                                password.setFocusColor(Paint.valueOf("Red"));
                                lblNote.setText("Enter Valid User name & Password");
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public void btnShowPasswordOnAction(ActionEvent actionEvent) {
        String pwd = password.getText();
        password.setVisible(false);
        txtShowPassword.setText(pwd);
        btnShowPassword.setVisible(false);
        btnHidePassword.setVisible(true);
        txtShowPassword.setVisible(true);
    }

    public void btnHidePasswordOnAction(ActionEvent actionEvent) {
        password.setVisible(true);
        btnHidePassword.setVisible(false);
        btnShowPassword.setVisible(true);
        txtShowPassword.setVisible(false);
    }
}
