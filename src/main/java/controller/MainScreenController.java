package controller;


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;

import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import model.Model;
import model.WaterSourceReport;
import model.enums.AccountType;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Optional;

/**
 * Controller for the main screen of the application. The main screen contains
 * a Google Map display of all water sources, as well as a menu to navigate
 * to the rest of the application's pages.
 *
 * Created by Kavish on 9/27/2016.
 *
 * UPDATE: Combined with MapController (Neil)
 * and revamped by Karthik on 10/25/2016
 */
public class MainScreenController implements Initializable, MapComponentInitializedListener{

    /** references to FXML Widgets */
    @FXML
    private Button submitReports;

    @FXML
    private Button submitSourceReport;

    @FXML
    private Button submitQualityReport;

    @FXML
    private Button viewReports;

    @FXML
    private Button viewSourceReports;

    @FXML
    private Button viewQualityReports;

    @FXML
    private Button viewHistoricalReport;

    @FXML
    private Button viewMap;

    @FXML
    GoogleMapView mapView;

    @FXML
    BorderPane border;

    /** reference to map display */
    GoogleMap map;

    /** the stage for this page */
    private Stage _dialogStage;

    /** flag to signal whether dialog was closed normally */
    private Boolean _logoutPressed;

    /** reference to FX App */
    private FXApplication app;

    private static final int INITLAT = 34;
    private static final int INITLONG = -88;


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapView.addMapInializedListener(this);
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
     * Sets the FX app for the controller.
     *
     * @param fxapp FX App for the controller
     */
    public void setMainApp(FXApplication fxapp) {
        app = fxapp;
    }

    @Override
    public void mapInitialized() {
        mapView.setPrefHeight(border.getHeight());
        mapView.setPrefWidth(border.getWidth());
        MapOptions options = new MapOptions();

        //set up the center location for the map
        LatLong center = new LatLong(INITLAT, INITLONG);

        options.center(center)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);


        //Talk with Model to get reports

        Model model = Model.getInstance();

        List<WaterSourceReport> reports = Model.getSourceReports();
        for (WaterSourceReport r : reports) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLong loc = new LatLong(r.get_latitude(), r.get_longitude());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title(r.getTitle());

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content(r.toString());

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);});

            map.addMarker(marker);

        }
    }

    /**
     * Called when the user clicks the logout button.
     *
     * Pops up an alert to confirm that the user wants to log out, and if
     * confirmed, logs out the user and returns to the login page.
     */
    @FXML
    private void handleLogout() {

        // Show the error message if bad data
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(_dialogStage);
        alert.setTitle("Confirm Logout");
        alert.setHeaderText("Confirm Logout");
        alert.setContentText("Are you sure you want to Logout?");


        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            _logoutPressed = true;
            Model.setUser(null);
            app.backToLoginPage();
        } else {
            _logoutPressed = false;
        }
    }

    /**
     * Called when the user clicks the submit report button. If the user is of
     * AccountType User, displays the water source report submission page.
     * Otherwise, drops down more menu button options.
     */
    @FXML
    private void handleSubmitReports() {
        if (Model.getUser().getAccountType().equals(AccountType.USER)) {
            app.showSubmitWaterSourceReport();
        } else {
            toggleButton(submitSourceReport);
            toggleButton(submitQualityReport);
        }
    }

    /**
     * Called when the user clicks the submit source report button. Displays the
     * submit source report page.
     */
    @FXML
    private void handleSubmitSourceReport() {
        app.showSubmitWaterSourceReport();
    }

    /**
     * Called when the user clicks the submit quality report button. Displays
     * the submit quality report page.
     */
    @FXML
    private void handleSubmitQualityReport() {
        app.showSubmitWaterQualityReport();
    }

    /**
     * Called when the user clicks the view reports button. If the user is of
     * AccountType User, displays the view source reports table page. Otherwise,
     * drops down more menu button options.
     */
    @FXML
    private void handleViewReports() {
        if (Model.getUser().getAccountType().equals(AccountType.USER)) {
            app.showViewSourceReportsTable();
        } else {
            toggleButton(viewSourceReports);
            toggleButton(viewQualityReports);
            if (Model.getUser().getAccountType().equals(AccountType.MANAGER)) {
                toggleButton(viewHistoricalReport);
            }
        }
    }

    /**
     * Called when the user clicks the view source reports button. Displays the
     * view source reports table page.
     */
    @FXML
    private void handleViewSourceReports() {
        app.showViewSourceReportsTable();
    }

    /**
     * Called when the user clicks the view quality reports button. Displays the
     * view quality reports table page.
     */
    @FXML
    private void handleViewQualityReports() {
        app.showViewQualityReportsTable();
    }

    @FXML
    private void handleViewHistoricalReport() {
        app.showHistoricalReportsPage();
    }

    /**
     * Called when the user clicks the edit profile button. Displays the edit
     * profile page.
     */
    @FXML
    private void handleEdit() {
        app.showEditPage();
    }

    /**
     * Private helper function to toggle the display of a button. Toggles the
     * "Visible" and "Managed" properties of the given button.
     *
     * @param button Button to toggle
     */
    private void toggleButton(Button button) {
        if (button.isVisible()) {
            button.setVisible(false);
            button.setManaged(false);
        } else {
            button.setVisible(true);
            button.setManaged(true);
        }
    }

    /**
     * Checks to see if the user has pressed the logout button
     *
     * @return boolean representing whether they have pressed the button or not
     */
    public boolean verifyLogout() {
        return _logoutPressed;
    }
}
