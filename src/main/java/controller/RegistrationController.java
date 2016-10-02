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

import javax.lang.model.type.PrimitiveType;
import java.io.*;

/**
 * Created by Aaron Strickland on 9/18/2016.
 *
 * Controller for the Registration page.
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

    /** The window for this dialog */
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

    /** Called when the user clicks the register button */
    @FXML
    private void handleRegistrationAttempt() throws IOException {
        if (this.isInputValid()) {
            //output user info to CSV
            String userInfo = userField.getText() + ";" + passField.getText()
                    + ";" + accountTypeComboBox.getValue().toString() + ";"
                    + emailField.getText() + ";" + firstNameField.getText()
                    + ";" + lastNameField.getText() + "\n";
            File users = new File("../../../resources/user.csv");
            //create new file if it does not exist
            users.createNewFile();
            PrintWriter in = new PrintWriter(users);
            in.append(userInfo);

            _registrationCompleted = true;
            app.backToLoginPage();
        }
    }

    @FXML
    private void handleCancel() {
        app.backToLoginPage();
    }

    /**
     * Validates the user input in the text fields.
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
            errorMessage += "No account type selected\n";
        }

        //check if password and confirm password match
        if (!passField.getText().equals(confirmPassField.getText())) {
            errorMessage += "Passwords do not match\n";
        }

        //TODO: check if username already exists

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