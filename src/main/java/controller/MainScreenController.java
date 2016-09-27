package controller;

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

    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    /**
     * Called when the user clicks the login button
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
        } else {
            _logoutPressed = false;
        }

    }

    public boolean verifyLogout() {
        return _logoutPressed;
    }
}
