package controller;

import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by Kavish on 9/27/2016.
 */
public class MainScreenController {

    private Stage _dialogStage;
    private Boolean _logoutPressed;

    private FXApplication app;

    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    public void setMainApp(FXApplication fxapp) {
        app = fxapp;
    }
    /**
     * Called when the user clicks the logout button
     */
    @FXML
    private void handleLogout() {

        // Show the error message if bad data
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(_dialogStage);
        alert.setTitle("Are you sure you want to Logout?");
        alert.setHeaderText("Logout");
        alert.setContentText("Are you sure you want to Logout?");


        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            _logoutPressed = true;
            app.backToLoginPage();
        } else {
            _logoutPressed = false;
        }
        //app.backToLoginPage();

    }

    /**
     * Checks to see if the user has pressed the logout button
     *
     * @return boolean representing whether they have pressed the button or not
     */
    public boolean verifyLogout() {
        return _logoutPressed;
    }
}
