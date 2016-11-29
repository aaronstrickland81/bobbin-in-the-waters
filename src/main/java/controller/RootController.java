package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by Karthik on 11/29/2016.
 */
public class RootController {

    @FXML
    private Button exitButton;

    @FXML
    private Button minimizeButton;

    /**
     * Called when the user clicks the exit button. Exits the application.
     */
    @FXML
    private void handleExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleMinimize() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }
}
