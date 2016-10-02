package controller;

import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.stage.Stage;


/**
 * Created by kavish on 10/1/16.
 */
public class ProfileController {
    private Stage _dialogStage;

    private FXApplication app;

    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    public void setMainApp(FXApplication fxapp) {
        app = fxapp;
    }

    @FXML
    private void handleBack() {
        app.showMainPage();
    }

    @FXML
    private void handleSave() {
        app.showMainPage();
    }
}
