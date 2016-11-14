package controller;

import fxapp.FXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;
import model.enums.AccountType;
import model.User;

import java.io.IOException;


/**
 * Controller for the Registration page. This page allows the user to enter
 * information to create a new account.
 *
 * Created by Aaron Strickland on 9/18/2016.
 */
public class RegistrationController {

    /** References to FXML widgets */
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField userField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passField;

    @FXML
    private PasswordField confirmPassField;

    @FXML
    private ComboBox accountTypeComboBox;

    /** The stage for this dialog */
    private Stage _dialogStage;

    /** Reference to FX App */
    private FXApplication app;

    /** flag to signal whether dialog was closed normally */
    private boolean _registrationCompleted = false;

    /** Called automatically upon load */
    @FXML
    private void initialize() {
        accountTypeComboBox.getItems().addAll(
                FXCollections.observableArrayList(AccountType.values()));
    }

    /**
     * Sets the FX App for the controller.
     *
     * @param fxapp FX App for the controller
     */
    public void setMainApp(FXApplication fxapp) {
        app = fxapp;
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage for this dialog
     */
    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    /**
     * Returns true if the user has registered successfully, false otherwise.
     *
     * @return true if the user has registered
     */
    public boolean isRegistrationCompleted() {
        return _registrationCompleted;
    }

    /**
     * Called when the user clicks the register button. Validates input, if
     * valid creates a new user, stores it to the database, and displays the
     * login page.
     */
    @FXML
    private void handleRegistrationAttempt() throws IOException {
        if (this.isInputValid()) {
            User user = new User(userField.getText(), passField.getText(),
                    (AccountType) accountTypeComboBox.getValue(), emailField
                    .getText(), firstNameField.getText(), lastNameField.getText());
            Model.addUser(user);

            _registrationCompleted = true;
            app.backToLoginPage();
        }
    }

    @FXML
    private void handleCancel() {
        app.backToLoginPage();
    }

    /**
     * Validates the user input in the text fields. If invalid, displays an
     * alert informing the user of the invalid data.
     *
     * @return true if input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        //check if they actually typed something
        if (firstNameField.getText() == null
                || firstNameField.getText().length() == 0) {
            errorMessage += "No first name entered\n";
        }
        if (lastNameField.getText() == null
                || lastNameField.getText().length() == 0) {
            errorMessage += "No last name entered\n";
        }
        if (userField.getText() == null || userField.getText().length() == 0) {
            errorMessage += "No username entered\n";
        }
        if (emailField.getText() == null
                || emailField.getText().length() == 0) {
            errorMessage += "No email entered\n";
        }
        if (passField.getText() == null || passField.getText().length() == 0) {
            errorMessage += "No password entered\n";
        }
        if (confirmPassField.getText() == null
                || confirmPassField.getText().length() == 0) {
            errorMessage += "No confirm password entered\n";
        }
        if (accountTypeComboBox.getValue() == null) {
            errorMessage += "No account accountType selected\n";
        }

        //check if password and confirm password match
        if (!passField.getText().equals(confirmPassField.getText())) {
            errorMessage += "Passwords do not match\n";
        }

        //check if user exists
        if (Model.doesUsernameExist(userField.getText())) {
            errorMessage += "Username already exists. Choose another one\n";
        }


        //TODO: check if email is of valid format (maybe if it exists already as well?)

        //no error message means success / good input
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
