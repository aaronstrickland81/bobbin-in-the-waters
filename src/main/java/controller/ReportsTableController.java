package controller;

import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Model;

/**
 * Created by kavish on 10/16/16.
 */
public class ReportsTableController {

    @FXML
    private TableView tableview;

    @FXML
    private TableColumn tableColumn;

    /**
     * The window for this dialog
     */
    private Stage _dialogStage;

    // REf to FX APP
    private FXApplication app;

    private Model model = Model.getInstance();


}
