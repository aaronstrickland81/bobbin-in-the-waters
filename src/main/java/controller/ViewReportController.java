package controller;

import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Model;

/**
 * Created by Jason Lin on 10/29/2016.
 */
public class ViewReportController {

    @FXML
    private Button waterSourceButton;

    @FXML
    private Button waterQualityButton;

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
    private void handleViewWaterSourceReport() {
        app.showViewSourceReportsTable();
    }

    @FXML
    private void handleViewWaterQualityReport() {
        app.showViewQualityReportsTable();
    }

    @FXML
    private void handleViewHistoricalReport() {
        app.showHistoricalReportsPage();
    }
}
