package controller;

import fxapp.FXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AccountType;
import model.User;
import model.UserDatabaseInterface;

import java.io.IOException;


/**
 * Created by kavish on 10/1/16.
 */
public class ProfileController {
    private Stage _dialogStage;

    private FXApplication app;

    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    private User aUser;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField newPassword;

    /** Called automatically upon load */
    @FXML
    private void initialize() {
        aUser = FXApplication.getUser();
        firstName.setText(aUser.getFname());
        lastName.setText(aUser.getLname());
        email.setText(aUser.getEmail());
    }


    public void setMainApp(FXApplication fxapp) {
        app = fxapp;
    }

    @FXML
    private void handleBack() {
        app.showMainPage();
    }

    @FXML
    private void handleSave() {
        aUser = (updateUser(aUser));
        UserDatabaseInterface.editUser(aUser);
        app.showMainPage();
    }

    private User updateUser(User aUser) {
        aUser.setFname(firstName.getText());
        aUser.setLname(lastName.getText());
        aUser.setEmail(email.getText());
        if (password != null || password.getText().length() != 0) {
            aUser.setPassword(password.getText());
        }
        return aUser;
    }

}
