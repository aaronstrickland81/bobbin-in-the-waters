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

import java.util.Date;

/**
 * Created by kavish on 10/16/16.
 */
public class ReportsTableController {

    @FXML
    private TableView reportTable;

    @FXML
    private TableColumn reportNumCol;

    @FXML
    private TableColumn dateOf;

    @FXML
    private TableColumn locationCol;


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
        reportNumCol.setCellFactory(
                new PropertyValueFactory<Report, Integer>("reportNumber")
        );
        //dateOf = new TableColumn("DateOf");
        dateOf.setCellFactory(
                new PropertyValueFactory<Report, Date>("_date")
        );
       // locationCol = new TableColumn("Location");
        locationCol.setCellFactory(
                new PropertyValueFactory<Report, String>("_location")
        );
        reportTable.setItems(list);
        //reportTable.getColumns().addAll(reportNumCol, dateOf, locationCol);
    }

    @FXML
    private void handleBack() {
        app.showMainPage();
    }


}
