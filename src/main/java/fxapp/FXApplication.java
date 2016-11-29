package fxapp;


import controller.HistoricalReportsController;
import controller.LoginController;
import controller.MainScreenController;
import controller.ProfileController;
import controller.QualityReportsTableController;
import controller.RegistrationController;
import controller.SourceReportsTableController;
import controller.WaterQualityReportController;
import controller.WaterSourceReportController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Main application class.
 *
 * This class handles all the scene switching to reuse the main stage.
 */
public class FXApplication extends Application {

    /**  the java logger for this class */
    private static final Logger LOGGER = Logger.getLogger("FXApplication");

    /** the main container for the application window */
    private Stage mainScreen;

    /** the main layout for the main window */
    private BorderPane rootLayout;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        mainScreen = primaryStage;
        initRootLayout(mainScreen);
        showLoginPage();
    }

    /**
     * returns a reference to the main window stage
     *
     * @return reference to main stage
     */
    private Stage getMainScreen() { return mainScreen;}

    /**
     * Initialize the main screen for the application.
     * Most other views will be shown in this screen.
     *
     * @param mainScreen  the main Stage window of the application
     */
    private void initRootLayout(Stage mainScreen) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource(
                    "../view/rootLayout.fxml"));
            rootLayout = loader.load();

            rootLayout.setStyle(
                    "-fx-background-color: rgba(63, 63, 63, 0.8);"
            );
            mainScreen.initStyle(StageStyle.TRANSPARENT);
            mainScreen.setTitle("Main Page");
            Scene scene = new Scene(rootLayout);
            scene.setFill(Color.TRANSPARENT);
            mainScreen.setScene(scene);
            mainScreen.show();

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                mainScreen.setX(event.getScreenX() - xOffset);
                mainScreen.setY(event.getScreenY() - yOffset);
            });

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE,
                    "Failed to find the fxml file for RootLayout");
            e.printStackTrace();
        }
    }


    /**
     * Setup our default application view that is shown on application startup
     * This is displayed in the startup window
     */
    private void showLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource(
                    "../view/loginPage.fxml"));
            AnchorPane loginPage = loader.load();

            rootLayout.setCenter(loginPage);

            LoginController controller = loader.getController();
            controller.setDialogStage(getMainScreen());
            controller.setMainApp(this);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,
                    "Failed to find the fxml file for loginPage");
            e.printStackTrace();
        }
    }

    /**
     * Displays main page
     */
    public void showMainPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource(
                    "../view/MainScreen.fxml"));
            AnchorPane mainPage = loader.load();

            rootLayout.setCenter(mainPage);
            MainScreenController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,
                    "Failed to find the fxml file for MainScreen");
            e.printStackTrace();
        }
    }


    /**
     * Displays registration page
     */
    public void showRegistrationPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource(
                    "../view/registrationPage.fxml"));
            AnchorPane mainPage = loader.load();

            rootLayout.setCenter(mainPage);

            RegistrationController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,
                    "Failed to find the fxml file for registrationPage");
            e.printStackTrace();
        }
    }

    /**
     * Displays login page (from main page to login page)
     */
    public void backToLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource(
                    "../view/loginPage.fxml"));
            AnchorPane mainPage = loader.load();

            rootLayout.setCenter(mainPage);

            LoginController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE,
                    "Failed to find the fxml file for loginPage");
            e.printStackTrace();
        }
    }

    /**
     * Displays edit profile page
     */
    public void showEditPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource(
                    "../view/profile.fxml"));
            AnchorPane mainPage = loader.load();

            rootLayout.setCenter(mainPage);

            ProfileController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,
                    "Failed to find the fxml file for Profile");
            e.printStackTrace();
        }
    }

    /**
     * Displays the view source reports table page.
     */
    public void showViewSourceReportsTable() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource(
                    "../view/viewReports/viewSourceReportTable.fxml"));
            AnchorPane mainPage = loader.load();

            rootLayout.setCenter(mainPage);

            SourceReportsTableController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,
                    "Failed to find the fxml file for viewSourceReportTable");
            e.printStackTrace();
        }
    }

    /**
     * Displays the view quality report table page
     */
    public void showViewQualityReportsTable() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource(
                    "../view/viewReports/viewQualityReportTable.fxml"));
            AnchorPane mainPage = loader.load();

            rootLayout.setCenter(mainPage);
            QualityReportsTableController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,
                    "Failed to find the fxml file for QualityReportTable");
            e.printStackTrace();
        }
    }

    /**
     * Displays the submit water source report page.
     */
    public void showSubmitWaterSourceReport() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource(
                    "../view/submitReports/submitWaterSource.fxml"));
            AnchorPane mainPage = loader.load();

            rootLayout.setCenter(mainPage);

            WaterSourceReportController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,
                    "Failed to find the fxml file for WaterSourceReport");
            e.printStackTrace();
        }
    }

    /**
     * Displays the submit water quality report page.
     */
    public void showSubmitWaterQualityReport() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource(
                    "../view/submitReports/submitWaterQuality.fxml"));
            AnchorPane mainPage = loader.load();

            rootLayout.setCenter(mainPage);

            WaterQualityReportController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,
                    "Failed to find the fxml file for WaterQualityReport");
            e.printStackTrace();
        }
    }

    /**
     * Displays the view historical reports page.
     */
    public void showHistoricalReportsPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource(
                    "../view/historicalReportsPage.fxml"));
            AnchorPane mainPage = loader.load();

            mainPage.setStyle(
                    "-fx-background-color: rgba(63, 63, 63, 0.8);"
            );

            rootLayout.setCenter(mainPage);

            HistoricalReportsController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,
                    "Failed to find the fxml file for HistoricalReports");
            e.printStackTrace();
        }
    }

    /**
     * main method
     * @param args args to be passed
     */
    public static void main(String[] args) {
        launch(args);
    }
}
