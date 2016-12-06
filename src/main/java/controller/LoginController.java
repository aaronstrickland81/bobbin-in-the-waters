package controller;

import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Model;
import model.User;
import services.UserInfoTable;

/**
 * The controller for the login page. At the login page the user can login or
 * navigate to the registration page.
 *
 * @author Karthik Praturu
 */
public class LoginController {
    /**
     * References to the FXML widgets in the .fxml file
     */
    @FXML
    private TextField userField;

    @FXML
    private PasswordField pwField;

    /** The window for this dialog*/
    private Stage _dialogStage;

    /** Reference to FX APP */
    private FXApplication app;


    /** flag to signal whether dialog was closed normally */
    private boolean _loginAuthenticated = false;

    /**
     * Sets the main app for the controller.
     *
     * @param fxapp App to set as main app
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
     * Called when the user clicks the login button. Validates the login and
     * displays main page if successful.
     */
    @FXML
    private void handleLoginAttempt() {

        if (isInputValid()) {
            String uname = userField.getText();
            if (Model.doesUsernameExist(uname)) {
                User user = Model.verifyLogin(uname, pwField.getText());

                if (user == null) {
                    // uname exists but incorrect login
                    user = UserInfoTable.getUserFromUserName(uname);
                    _loginAuthenticated = false;
                    user.setLockoutNum(user.getLockoutNum() + 1);
                    Model.editUser(user);

                    // increment incorrect login attempts, check if banned
                    if (user.getLockoutNum() >= 3) {
                        bannedAlert();
                        return;
                    }
                } else {
                    // uname exists and correct login
                    // check if banned
                    if (user.getLockoutNum() >= 3) {
                        bannedAlert();
                        return;
                    }
                    user.setLockoutNum(0);
                    Model.editUser(user);
                    Model.setUser(user);
                    _loginAuthenticated = true;
                    app.showMainPage();
                    return;
                }
            }
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Please Correct Invalid Fields ");
            alert.setHeaderText("Incorrect password and/or username");

            alert.showAndWait();
        }
    }

    /**
     * Helper function to alert user that their account has been banned.
     */
    private void bannedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(_dialogStage);
        alert.setTitle("Account blocked");
        alert.setHeaderText("Account blocked");
        alert.setContentText("Your account has been blocked due"
                + " to too many incorrect login attempts. To "
                + "unblock your account, please contact an "
                + "administrator.");

        alert.showAndWait();
        return;
    }

    /**
     * Validates the user input in the text fields. If any are invalid, opens
     * an alert box describing the invalid fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        //for now just check they actually typed something
        if ((userField.getText() == null) || userField.getText().isEmpty()) {
            errorMessage += "No username entered\n";
        }
        if ((pwField.getText() == null) || pwField.getText().isEmpty()) {
            errorMessage += "No password entered\n";
        }

        //no error message means success / good input
        if (errorMessage.isEmpty()) {
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

    /**
     * Called when the user clicks the register button. Displays the
     * registration page.
     */
    @FXML
    private void handleRegistration() {
        app.showRegistrationPage();
    }

}
