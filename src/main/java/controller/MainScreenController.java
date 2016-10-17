package controller;

import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import model.Model;

import java.util.Optional;

/**
 * Created by Kavish on 9/27/2016.
 */
public class MainScreenController {

    /** references to FXML Widgets */
    @FXML
    private Button submitReports;

    @FXML
    private Button viewReports;

    @FXML
    private Button viewMap;


    private Stage _dialogStage;
    private Boolean _logoutPressed;

    private FXApplication app;

    private Model model = Model.getInstance();

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
        alert.setTitle("Confirm Logout");
        alert.setHeaderText("Confirm Logout");
        alert.setContentText("Are you sure you want to Logout?");


        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            _logoutPressed = true;
            Model.setUser(null);
            app.backToLoginPage();
        } else {
            _logoutPressed = false;
        }
    }

    @FXML
    private void handleSubmitReports() {
        app.showSubmitReports();
    }

    @FXML
    private void handleViewReports() {
        app.showViewReports();
    }

    @FXML
    private void handleEdit() {
        app.showEditPage();
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
