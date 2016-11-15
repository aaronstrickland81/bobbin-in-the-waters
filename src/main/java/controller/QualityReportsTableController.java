package controller;

import fxapp.FXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.Model;
import model.WaterQualityReport;
import model.enums.SourceCondition;
import model.enums.WaterType;

import java.util.Date;

/**
 * Controller for the quality reports table page. This page displays a table of
 * all water quality reports, and is only accessible by Workers and Mangers.
 *
 * Created by kavish on 10/32/16.
 */
public class QualityReportsTableController {

    /** references to FXML widgets */
    @FXML
    private TableView<WaterQualityReport> reportTable;

    @FXML
    private TableColumn<WaterQualityReport, Integer> reportNumCol;

    @FXML
    private TableColumn<WaterQualityReport, Date> dateOf;

    @FXML
    private TableColumn<WaterQualityReport, String> locationCol;

    @FXML
    private TableColumn<WaterQualityReport, SourceCondition> conditionCol;

    @FXML
    private TableColumn<WaterQualityReport, WaterType> virusCol;

    @FXML
    private TableColumn<WaterQualityReport, WaterType> chemCol;

    /** reference to FX App */
    private FXApplication app;

    /** list of water quality reports */
    private final ObservableList<WaterQualityReport> list = FXCollections
            .observableList(Model.getQualityReports());

    /**
     * Sets the FX App for the controller.
     *
     * @param fxapp FX App for the controller
     */
    public void setMainApp(FXApplication fxapp) {
        app = fxapp;
    }

    /**
     * Called automatically upon load
     */
    @FXML
    private void initialize() {
        reportNumCol.setCellValueFactory(
                new PropertyValueFactory<WaterQualityReport, Integer>
                        ("_reportNumber")
        );

        dateOf.setCellValueFactory(
                new PropertyValueFactory<WaterQualityReport, Date>("_date")
        );

        locationCol.setCellValueFactory(
                new PropertyValueFactory<WaterQualityReport, String>(
                        "_location")
        );

        conditionCol.setCellValueFactory(
                new PropertyValueFactory<>("_condition")
        );

        virusCol.setCellValueFactory(
                new PropertyValueFactory<>("_virusPPM")
        );

        chemCol.setCellValueFactory(
                new PropertyValueFactory<>("_chemPPM")
        );

        reportTable.setItems(list);
    }

    /**
     * Called when the user clicks the back button. Displays the main page.
     */
    @FXML
    private void handleBack() {
        app.showMainPage();
    }


}
