package controller;

import fxapp.FXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Model;
import model.WaterQualityReport;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Karthik Praturu on 11/5/2016.
 */
public class HistoricalReportsController {

    /** References to FXML widgets */
    @FXML
    private ComboBox locationBox;

    @FXML
    private ComboBox yearBox;

    @FXML
    private LineChart<String, Number> lineChart;

    /** The stage for this dialog */
    private Stage _dialogStage;

    /** Reference to FX App */
    private FXApplication app;

    /** list of water quality reports */
    private ArrayList<WaterQualityReport> qualityReports;

    /** map of quality reports by location */
    private Map<String, List<WaterQualityReport>> locationMap;

    /**
     * Called automatically upon load.
     *
     * Initializes the hash map of water quality report locations.
     * The map's keys are the locations, and the values are an
     * arrayList of quality reports for that location
     */
    @FXML
    private void initialize() {
        qualityReports = Model.getQualityReports();
        locationMap = new HashMap<>();

        for (WaterQualityReport report : qualityReports) {
            String locString = report.get_location();
            List<WaterQualityReport> currentBin = locationMap.get(locString);
            if (null == currentBin) {
                currentBin = new ArrayList<>();
                locationMap.put(locString, currentBin);
            }
            currentBin.add(report);
        }

        locationBox.getItems().addAll(
                FXCollections.observableArrayList(locationMap.keySet()));
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

    @FXML
    private void handleOnLocAction() {
        List<WaterQualityReport> relevantReports = locationMap.get(locationBox.getValue());
        /*
            Propagate year combobox with values
         */

        for (WaterQualityReport report : relevantReports) {

        }

        yearBox.setVisible(true);
    }

    @FXML
    private void handleOnYearAction() {
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        /*
            Add data to chart
         */
    }
}
