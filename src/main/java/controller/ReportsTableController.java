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
import model.enums.AccountType;
import model.enums.SourceCondition;
import model.enums.WaterType;

import java.util.Date;

/**
 * Created by kavish on 10/16/16.
 */
public class ReportsTableController {

    @FXML
    private TableView<Report> reportTable;

    @FXML
    private TableColumn<Report, Integer> reportNumCol;

    @FXML
    private TableColumn<Report, Date> dateOf;

    @FXML
    private TableColumn<Report, String> locationCol;

    @FXML
    private TableColumn<Report, WaterType> typeCol;

    @FXML
    private TableColumn<Report, SourceCondition> conditionCol;

    /**
     * The window for this dialog
     */
    private Stage _dialogStage;

    // REf to FX APP
    private FXApplication app;

    private Model model = Model.getInstance();

    private ObservableList<Report> list = FXCollections.observableList(Model.getReports());

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

    /** Called automatically upon load */
    @FXML
    private void initialize() {
        //reportNumCol = new TableColumn("Report#");
        reportNumCol.setCellValueFactory(
                new PropertyValueFactory<Report, Integer>("_reportNumber")
        );
        //dateOf = new TableColumn("DateOf");
        dateOf.setCellValueFactory(
                new PropertyValueFactory<Report, Date>("_date")
        );
       // locationCol = new TableColumn("Location");
        locationCol.setCellValueFactory(
                new PropertyValueFactory<Report, String>("_location")
        );

        typeCol.setCellValueFactory(
                new PropertyValueFactory<Report, WaterType>("_type")
        );

        conditionCol.setCellValueFactory(
                new PropertyValueFactory<Report, SourceCondition>("_condition")
        );

        reportTable.setItems(list);
        //reportTable.getColumns().addAll(reportNumCol, dateOf, locationCol);
    }

    @FXML
    private void handleBack() {
        app.showMainPage();
    }


}
