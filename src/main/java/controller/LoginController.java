package controller;

import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Model;

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

    @FXML
    private Button exitButton;

    /** The window for this dialog*/
    private Stage _dialogStage;

    /** Reference to FX APP */
    private FXApplication app;

    private Model model = Model.getInstance();


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
     * Returns true if the user has logged in successfully, false otherwise.
     *
     * @return  true if the user has logged in.
     */
    public boolean verifyLogin() {
        return _loginAuthenticated;
    }

    /**
     * Called when the user clicks the login button. Validates the login and
     * displays main page if successful.
     */
    @FXML
    private void handleLoginAttempt() {

        if (isInputValid()) {

            Model.setUser(Model.verifyLogin(userField
                    .getText(), pwField.getText()));
            String errorMessage = "";

            if (Model.getUser() == null) {
                _loginAuthenticated = false;
                // Show the error message if bad data
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(_dialogStage);
                alert.setTitle("Please Correct Invalid Fields ");
                alert.setHeaderText("Incorrect password and/or username");
                alert.setContentText(errorMessage);

                alert.showAndWait();
            } else {
                _loginAuthenticated = true;
                app.showMainPage();
            }
        }
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

    /**
     * Called when the user clicks the exit button. Exits the application.
     */
    @FXML
    private void handleExit(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
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
