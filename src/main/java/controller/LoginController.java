package controller;

import fxapp.FXApplication;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;
import model.UserDatabaseInterface;

import java.io.IOException;


/**
 * The controller for the login page
 *
 * @author Karthik Praturu
 */
public class LoginController {
    /*
     * References to the FXML widgets in the .fxml file
     */
    @FXML
    private TextField userField;

    @FXML
    private PasswordField pwField;

    /** The window for this dialog*/
    private Stage _dialogStage;

    /** The user trying to log in*/
    private User _user;

    // REf to FX APP
    private FXApplication app;


    /** flag to signal whether dialog was closed normally */
    private boolean _loginAuthenticated = false;


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
     * Returns true if the user has logged in successfully, false otherwise.
     *
     * @return  true if the user has logged in.
     */
    public boolean verifyLogin() {
        return _loginAuthenticated;
    }

    /**
     * Called when the user clicks the login button
     */
    @FXML
    private void handleLoginAttempt() {
        try {
            UserDatabaseInterface uDB = UserDatabaseInterface.getInstance("./src/main/resources/users.csv");

            if (isInputValid()) {
                _user = new User(userField.getText(), pwField.getText());
                String errorMessage = "";

                if (!uDB.verifyUser(_user.getUname(), _user.getPassword())) {
                    _loginAuthenticated = false;
                    // Show the error message if bad data
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(_dialogStage);
                    alert.setTitle("Incorrect password and/or username ");
                    alert.setHeaderText("Please correct invalid fields");
                    alert.setContentText(errorMessage);

                    alert.showAndWait();
                } else {
                    _loginAuthenticated = true;
                    app.showMainPage();
                }
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("FATAL ERROR");
            alert.setHeaderText("Error Code: 0x0003");
            alert.setContentText("Cannot Load CSV");
            alert.showAndWait();
        }
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        //for now just check they actually typed something
        if (userField.getText() == null || userField.getText().length() == 0) {
            errorMessage += "No username entered\n";
        }
        if (pwField.getText() == null || pwField.getText().length() == 0) {
            errorMessage += "No password entered\n";
        }

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

    @FXML
    private void handleRegistration() {
        app.showRegistrationPage();
    }

}
