package controller;

import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Model;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**
 * Created by Jason on 10/16/2016.
 */
public class SubmitReportsController {
    /** references to FXML Widgets */
    @FXML
    private Button waterReportButton;


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

    @FXML
    private void handleBack() {
        app.showMainPage();
    }

    @FXML
    private void handleSubmitWaterSourceReport() {
        app.showWaterSourceReport();
    }

    @FXML
    private void handleSubmitWaterQualityReport() {
        app.showWaterQualityReport();
    }
}
