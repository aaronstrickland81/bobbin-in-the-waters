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
import model.WaterQualityReport;
import model.enums.PurityCondition;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Controller for the submit quality report page. This page lets the user enter
 * information for a water quality report and submit it to the database. This
 * page is only accessible by Workers and Managers.
 *
 * Created by Jason on 10/30/2016.
 */
public class WaterQualityReportController {

    /** references to FXML widgets */
    @FXML
    private ComboBox waterCondition;

    @FXML
    private TextField longitude;

    @FXML
    private TextField latitude;

    @FXML
    private TextField virusPPM;

    @FXML
    private TextField contaminantPPM;

    @FXML
    private Button cancel;

    @FXML
    private Button submit;

    /**
     * Helper Function that converts LocalDateTime to Date
     *
     * @return current date
     */
    private Date dateConverter() {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /** The stage for this dialog */
    private Stage _dialogStage;

    /** Reference to FX App */
    private FXApplication app;

    /** flag to signal whether dialog was closed normally */
    private boolean _waterQualityReportCompleted = false;

    public WaterQualityReportController() {
    }

    /**
     * Called automatically upon load. Populates the combobox for water
     * condition with the appropriate enum values.
     */
    @FXML
    private void initialize() {
        waterCondition.getItems().addAll(
                FXCollections.observableArrayList(PurityCondition.values()));
    }

    /**
     * Sets the FX App for the controller.
     *
     * @param fxapp FX App for the controller
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
     * Returns true if the user has registered successfully, false otherwise.
     *
     * @return true if the user has registered
     */
    public boolean isWaterQualityReportCompleted() {
        return _waterQualityReportCompleted;
    }

    /**
     * Called when the user clicks the submit button. Validates user input, if
     * valid creates a new quality report, adds it to the database, and displays
     * the main page.
     */
    @FXML
    private void handleWaterSourceSubmissionAttempt() throws IOException {
        if (this.isInputValid()) {
            //output user info to CSV
            Model.addQualityReport(new WaterQualityReport(dateConverter(), Model
                    .qualityNumGenerator(), Model.getUser().getUname(),
                    Double.parseDouble(longitude.getText()), Double.parseDouble(latitude.getText()),
                    (PurityCondition) waterCondition.getValue(), Double.parseDouble(virusPPM.getText()),
                    Double.parseDouble(contaminantPPM.getText())));

            _waterQualityReportCompleted = true;
            app.showMainPage();
        }
    }

    /**
     * Called when the user clicks the back button. Displays the main page.
     */
    @FXML
    private void handleBack() {
        app.showMainPage();
    }

    /**
     * Called when the user clicks the cancel button. Displays the main page.
     */
    @FXML
    private void handleCancel() {
        app.showMainPage();
    }

    /**
     * Validates the user input in the text fields. If invalid, displays an
     * alert informing the user of the invalid data.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        //for now just check they actually typed something
        if (longitude.getText() == null || longitude.getText().length() == 0) {
            errorMessage += "No longitude entered\n";
        } else if (!validDouble(longitude.getText())) {
            errorMessage += "That is not a valid longitude. Please give a " +
                    "number\n";
        }
        if (latitude.getText() == null || latitude.getText().length() == 0) {
            errorMessage += "No latitude entered\n";
        } else if (!validDouble(latitude.getText())) {
            errorMessage += "That is not a valid latitude. Please give a " +
                    "number\n";
        }
        if (virusPPM.getText() == null) {
            errorMessage += "No virus PPM entered\n";
        } else if (!validDouble(virusPPM.getText())) {
            errorMessage += "That is not a valid virus PPM. Please give a " +
                    "number\n";
        }
        if (contaminantPPM.getText() == null) {
            errorMessage += "No contaminate PPM entered\n";
        } else if (!validDouble(contaminantPPM.getText())) {
            errorMessage += "That is not a valid contaminant PPM. Please give a " +
                    "number\n";
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

    /**
     * Validates if the lat and long inputs are valid doubles.
     *
     * @param str Input to be validated
     * @return true if valid double, false otherwise
     */
    private boolean validDouble(String str) {
        final String Digits = "(\\p{Digit}+)";
        final String HexDigits = "(\\p{XDigit}+)";
        final String Exp = "[eE][+-]?" + Digits;
        final String fpRegex =
                ("[\\x00-\\x20]*[+-]?(NaN|Infinity|(((" + Digits + "(\\.)?" +
                        "(" + Digits + "?)(" + Exp + ")?)|(\\.(" + Digits + ")(" +
                        Exp + ")?)|"
                        + "(((0[xX]" + HexDigits + "(\\.)?)|(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")"
                        + ")[pP][+-]?" + Digits + "))" +
                        "[fFdD]?))" +
                        "[\\x00-\\x20]*");
        if (Pattern.matches(fpRegex, str)) {
            return true;
        } else {
            return false;
        }
    }
}
