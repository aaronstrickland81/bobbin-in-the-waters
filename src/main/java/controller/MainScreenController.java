package controller;


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import model.Model;
import model.Report;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Optional;

/**
 * Created by Kavish on 9/27/2016.
 *
 * UPDATE: Combined with MapController
 * and revamped by Karthik on 10/25/2016
 */
public class MainScreenController implements Initializable, MapComponentInitializedListener{

    /** references to FXML Widgets */
    @FXML
    private Button submitReports;

    @FXML
    private Button viewReports;

    @FXML
    private Button viewMap;

    @FXML
    GoogleMapView mapView;

    @FXML
    BorderPane border;

    GoogleMap map;

    private Stage _dialogStage;
    private Boolean _logoutPressed;

    private FXApplication app;

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

    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    public void setMainApp(FXApplication fxapp) {
        app = fxapp;
    }

    @Override
    public void mapInitialized() {
        mapView.setPrefHeight(border.getHeight());
        mapView.setPrefWidth(border.getWidth());
        MapOptions options = new MapOptions();

        //set up the center location for the map
        LatLong center = new LatLong(34, -88);

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

        List<Report> reports = Model.getSourceReports();
        for (Report r: reports) {
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
     * Called when the user clicks the logout button
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

    @FXML
    private void handleSubmitReports() {
        app.showSubmitReports();
    }

    @FXML
    private void handleViewReports() {
        app.showViewReports();
    }

    @FXML
    private void handleViewMap() {
        app.showMapPage();
    }

    @FXML
    private void handleEdit() {
        app.showEditPage();
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
