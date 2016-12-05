package controller;

import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserInfoTable;

/**
 * Controller for the Admin Features page. On this page an administrator can
 * enter a username and delete, ban, or unblock a user.
 *
 * Created by Aaron Strickland on 12/4/2016.
 */
public class AdminFeaturesController {

    /** References to FXML widgets */
    @FXML
    private TextField unameField;

    /** The stage for this dialog */
    private Stage _dialogStage;

    /** Reference to FX App */
    private FXApplication app;

    /**
     * Sets the FX App for the controller.
     *
     * @param fxapp FX App for the controller
     */
    public void setMainApp(FXApplication fxapp) {
        app = fxapp;
    }

    /**
     * Called when the user clicks the delete user button.
     *
     * Checks to see if the entered user exists and if so deletes them,
     * otherwise alerts the user of the invalid field.
     */
    @FXML
    private void handleDeleteUser() {
        String uname = unameField.getText();
        if (UserInfoTable.checkUserExists(uname)) {
            UserInfoTable.removeUser(uname);
        } else {
            invalidUserAlert();
        }
    }

    @FXML
    private void handleBanUser() {
        String uname = unameField.getText();
        if (UserInfoTable.checkUserExists(uname)) {
            //TODO: ban user
        } else {
            invalidUserAlert();
        }
    }

    @FXML
    private void handleUnblockUser() {
        String uname = unameField.getText();
        if (UserInfoTable.checkUserExists(uname)) {
            //TODO: unblock user
        } else {
            invalidUserAlert();
        }
    }

    @FXML
    private void handleBack() {
        app.backToLoginPage();
    }

    /**
     * Private helper function that displays an alert informing the user of an
     * invalid username field.
     */
    private void invalidUserAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(_dialogStage);
        alert.setTitle("Invalid User");
        alert.setHeaderText("Error: Invalid User");
        alert.setContentText("Entered user does not exist. Please correct "
                + "and try again.");
        alert.showAndWait();
    }
}
