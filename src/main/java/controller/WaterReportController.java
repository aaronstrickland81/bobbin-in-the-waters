package controller;

import fxapp.FXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import model.Model;
import model.WaterSourceReport;
import model.enums.WaterType;
import model.enums.SourceCondition;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


/**
 * Created by Jason Lin on 10/15/2016.
 *
 * Controller for the Water Report Submission page
 */
public class WaterReportController {

    /** references to FXML widgets */
    @FXML
    private ComboBox waterType;

    @FXML
    private ComboBox waterCondition;

    @FXML
    private TextField coordinates;

    @FXML
    private Button cancel;

    @FXML
    private Button submit;

    /**
     * Helper Function that converts LocalDateTime to Date
     * @return out
     */
    private Date dateConverter() {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        return out;
    }

    /** The window for this dialog */
    private Stage _dialogStage;

    /** Reference to FX App */
    private FXApplication app;

    /** flag to signal whether dialog was closed normally */
    private boolean _waterSourceReportCompleted = false;

    public WaterReportController() {
    }

    @FXML
    private void initialize() {
        waterType.getItems().addAll(
                FXCollections.observableArrayList(WaterType.values()));
        waterCondition.getItems().addAll(
                FXCollections.observableArrayList(SourceCondition.values()));
    }

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
     * Returns true if the user has registered successfully, false otherwise.
     *
     * @return true if the user has registered
     */
    public boolean isWaterSourceReportCompleted() {
        return _waterSourceReportCompleted;
    }

    /** Called when the user clicks the submit button */
    @FXML
    private void handleWaterSourceSubmissionAttempt() throws IOException {
        if (this.isInputValid()) {
            //output user info to CSV
            Model.addReport(new WaterSourceReport(dateConverter(), Model.reportNumGenerator(), Model.getUser().getUname(), coordinates.getText(), (WaterType) waterType.getValue(), (SourceCondition) waterCondition.getValue()));

            _waterSourceReportCompleted = true;
            app.showMainPage();
        }
    }

    @FXML
    private void handleBack() {
        app.showMainPage();
    }

    @FXML
    private void handleCancel() {
        app.showSubmitReports();
    }
    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        //for now just check they actually typed something
        if (coordinates.getText() == null || coordinates.getText().length() == 0) {
            errorMessage += "No coordinates entered\n";
        }
        if (waterType.getValue() == null) {
            errorMessage += "No water type selected\n";
        }
        if (waterCondition.getValue() == null) {
            errorMessage += "No water condition selected\n";
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
}
