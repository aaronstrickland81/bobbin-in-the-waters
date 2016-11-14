package controller;

import fxapp.FXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Model;
import model.WaterQualityReport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Karthik Praturu on 11/5/2016.
 */
public class HistoricalReportsController {

    private class PPMDataHandler {
        public String month = "";
        public double virusPPM;
        public double contaminantPPM;
        private int collisions;
        public int monthNumber;

        public void addFromReport(WaterQualityReport report) {
            if (report.get_month_name().equals(this.month)) {
                virusPPM = (virusPPM * collisions + report.get_virusPPM()) / (collisions + 1);
                contaminantPPM = (contaminantPPM * collisions + report.get_chemPPM()) / (collisions + 1);
            } else {
                month = report.get_month_name();
                monthNumber = report.get_month();
                virusPPM = report.get_virusPPM();
                contaminantPPM = report.get_chemPPM();
            }
            collisions++;
        }
    }

    /** References to FXML widgets */
    @FXML
    private ComboBox locationBox;

    @FXML
    private ComboBox yearBox;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private RadioButton virusButton;

    @FXML
    private RadioButton contaminantButton;

    @FXML
    private ToggleGroup group;

    /** The stage for this dialog */
    private Stage _dialogStage;

    /** Reference to FX App */
    private FXApplication app;

    /** list of water quality reports */
    private List<WaterQualityReport> qualityReports;

    /** map of quality reports by location */
    private Map<String, List<WaterQualityReport>> locationMap;

    /** map of quality reports by year */
    private Map<Integer, List<WaterQualityReport>> yearMap;

    private Map<String, PPMDataHandler> ppmMap;

    private List<PPMDataHandler> ppmList;

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
        lineChart.setAnimated(false);
        lineChart.setStyle(
                "-fx-background-color: rgba(255,255,255,1);"
        );

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

    /**
     * When manager clicks on a location, a hash map of years related to
     * reports for that location is populated, and a year box is brought
     * into view
     */
    @FXML
    private void handleOnLocAction() {
        List<WaterQualityReport> relevantReports = locationMap.get(locationBox.getValue());
        yearMap = new HashMap<>();
        yearBox.getSelectionModel().clearSelection();

        for (WaterQualityReport report : relevantReports) {
            int year = report.get_year();
            List<WaterQualityReport> yearBin = yearMap.get(year);
            if (null == yearBin) {
                yearBin = new ArrayList<>();
                yearMap.put(year, yearBin);
            }
            yearBin.add(report);
        }

        yearBox.setVisible(true);
        yearBox.getItems().clear();
        yearBox.getItems().addAll(
                FXCollections.observableArrayList(yearMap.keySet()));
    }

    /**
     * When a manager selects a year, two options for choosing which
     * graph to plot are brought into view, and a list of data
     * for each accountType of graph is populated
     */
    @FXML
    private void handleOnYearAction() {

        ppmMap = new HashMap<>();

        group = new ToggleGroup();

        virusButton.setVisible(true);
        contaminantButton.setVisible(true);

        virusButton.setSelected(false);
        contaminantButton.setSelected(false);

        virusButton.setToggleGroup(group);
        contaminantButton.setToggleGroup(group);

        List<WaterQualityReport> relevantReports = yearMap.get(yearBox.getValue());

        if (null != relevantReports) {
            for (WaterQualityReport report : relevantReports) {
                String month = report.get_month_name();
                PPMDataHandler monthPPM = ppmMap.get(month);
                if (null == monthPPM) {
                    monthPPM = new PPMDataHandler();
                    ppmMap.put(month, monthPPM);
                }
                monthPPM.addFromReport(report);
            }

            ppmList = new ArrayList<>(ppmMap.values());
            Collections.sort(ppmList, (o1, o2) -> o1.monthNumber - o2.monthNumber);
        }
    }

    /**
     * When virusPPM option is clicked, graph the virus data by making use of
     * inner class, which automatically handles reports created in the same month
     */
    @FXML
    private void handleVirusPPM() {
        lineChart.getData().clear();
        lineChart.setTitle("Graph of Virus PPM");

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (PPMDataHandler ppmData : ppmList) {
            series.getData().add(new XYChart.Data<String, Number>(ppmData.month, ppmData.virusPPM));
        }
        lineChart.getData().add(series);
    }

    /**
     * When contaminantPPM option is clicked, graph the contaminant data by making use of
     * the PPMDataHandler inner class, which automatically handles reports created in
     * the same month
     */
    @FXML
    private void handleContaminantPPM() {
        lineChart.getData().clear();
        lineChart.setTitle("Graph of Contaminant PPM");
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (PPMDataHandler ppmData : ppmList) {
            series.getData().add(new XYChart.Data<String, Number>(ppmData.month, ppmData.contaminantPPM));
        }
        lineChart.getData().add(series);
    }


    @FXML
    private void handleBack() {
        app.showMainPage();
    }
}
