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
import model.WaterSourceReport;
import model.enums.SourceCondition;
import model.enums.WaterType;

import java.util.Date;

/**
 * Controller for the Source Reports Table page. This page displays a table of
 * all the source reports.
 *
 * Created by kavish on 10/16/16.
 */
public class SourceReportsTableController {

    /** references to FXML widgets */
    @FXML
    private TableView<WaterSourceReport> reportTable;

    @FXML
    private TableColumn<WaterSourceReport, Integer> reportNumCol;

    @FXML
    private TableColumn<WaterSourceReport, Date> dateOf;

    @FXML
    private TableColumn<WaterSourceReport, String> locationCol;

    @FXML
    private TableColumn<WaterSourceReport, WaterType> typeCol;

    @FXML
    private TableColumn<WaterSourceReport, SourceCondition> conditionCol;

    /** The stage for this dialog */
    private Stage _dialogStage;

    /** reference to FX App */
    private FXApplication app;

    /** reference to the instance of Model */
    private Model model = Model.getInstance();

    /** list of water source reports */
    private ObservableList<WaterSourceReport> list = FXCollections
            .observableList(Model.getSourceReports());

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
     * Called automatically upon load. Populates table with reports from the
     * list of source reports.
     */
    @FXML
    private void initialize() {
        //reportNumCol = new TableColumn("Report#");
        reportNumCol.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, Integer>("_reportNumber")
        );
        //dateOf = new TableColumn("DateOf");
        dateOf.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, Date>("_date")
        );
       // locationCol = new TableColumn("Location");
        locationCol.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, String>("_location")
        );

        typeCol.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, WaterType>("_type")
        );

        conditionCol.setCellValueFactory(
                new PropertyValueFactory<WaterSourceReport, SourceCondition>("_condition")
        );

        reportTable.setItems(list);
        //reportTable.getColumns().addAll(reportNumCol, dateOf, locationCol);
    }

    /**
     * Called when the user clicks the back button. Displays the main page.
     */
    @FXML
    private void handleBack() {
        app.showMainPage();
    }


}
