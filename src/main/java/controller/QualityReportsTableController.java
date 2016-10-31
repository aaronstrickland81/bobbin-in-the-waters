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
import model.Report;
import model.WaterQualityReport;
import model.enums.PurityCondition;
import model.enums.SourceCondition;
import model.enums.WaterType;

import java.util.Date;

/**
 * Created by kavish on 10/32/16.
 */
public class QualityReportsTableController {

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

    /**
     * The window for this dialog
     */
    private Stage _dialogStage;

    // REf to FX APP
    private FXApplication app;

    private Model model = Model.getInstance();

    private ObservableList<WaterQualityReport> list = FXCollections
            .observableList(Model.getQualityReports());

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
                new PropertyValueFactory<WaterQualityReport, String>("_location")
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

    @FXML
    private void handleBack() {
        app.showMainPage();
    }


}
