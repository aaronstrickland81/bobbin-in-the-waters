package fxapp;

//        import controller.CourseOverviewController;
//        import controller.MainScreenController;
//        import controller.StudentEditController;

        import controller.*;
        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.BorderPane;
        import javafx.scene.paint.Color;
        import javafx.stage.Stage;

        import javafx.stage.StageStyle;
        import model.User;
        import services.UserDatabaseInterface;

        import java.io.IOException;
        import java.util.logging.Level;
        import java.util.logging.Logger;


/**
 * Main application class.
 * <p>
 * This class handles all the scene switching to reuse the main stage.
 */
public class FXApplication extends Application {
    /**  the java logger for this class */
    private static final Logger LOGGER = Logger.getLogger("FXApplication");

    /** the main container for the application window */
    private Stage mainScreen;

    /** the main layout for the main window */
    private BorderPane rootLayout;



    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        mainScreen = primaryStage;
        initRootLayout(mainScreen);
        showLoginPage();
    }

    /**
     * return a reference to the main window stage
     * @return reference to main stage
     * */
    public Stage getMainScreen() { return mainScreen;}


    /**
     * Initialize the main screen for the application.  Most other views will be shown in this screen.
     *
     * @param mainScreen  the main Stage window of the application
     */
    private void initRootLayout(Stage mainScreen) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource("../view/rootLayout.fxml"));
            rootLayout = loader.load();

            // Set the Main App title
            mainScreen.setTitle("Main Page");

            // Show the scene containing the root layout.
            UserDatabaseInterface uDB = UserDatabaseInterface.getInstance("./src/main/resources/users.csv");
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }


    /**
     * Setup our default application view that is shown on application startup
     * This is displayed in the startup window
     *
     * precondition - the main stage is already initialized and showing (initRootLayout has been called)
     * postcondition - the view is initialized and displayed
     *
     *
     */
    public void showLoginPage() {
        try {
            // Load login page.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource("../view/loginPage.fxml"));
            AnchorPane loginPage = loader.load();

            // Set login page into the center of root layout.
            rootLayout.setCenter(loginPage);

            // Give the controller access to the main app.
            LoginController controller = loader.getController();
            controller.setDialogStage(getMainScreen());
            controller.setMainApp(this);


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for loginPage!!");
            e.printStackTrace();
        }

    }

    /**
     * Displays main screen
     *
     *
     */
    public void showMainPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource("../view/MainScreen.fxml"));
            AnchorPane mainPage = loader.load();

            mainPage.setStyle(
                    "-fx-background-color: rgba(63, 63, 63, 0.8);"
            );

            Scene scene = new Scene(mainPage);
            scene.setFill(Color.TRANSPARENT);
            mainScreen.setScene(scene);
            mainScreen.show();

            MainScreenController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen");
            e.printStackTrace();
        }
    }


    /**
     * shows registration page when button is clicked
     */
    public void showRegistrationPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource
                    ("../view/registrationPage.fxml"));
            AnchorPane mainPage = loader.load();

            Scene scene = new Scene(mainPage);
            mainScreen.setScene(scene);
            mainScreen.show();

            RegistrationController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen");
            e.printStackTrace();
        }
    }

    /**
     * Shows main page when login is valid and checks when logout is pressed
     *
     *
     */
    public void backToLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource("../view/loginPage.fxml"));
            AnchorPane mainPage = loader.load();

            Scene scene = new Scene(mainPage);
            mainScreen.setScene(scene);
            mainScreen.show();

            LoginController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen");
            e.printStackTrace();
        }
    }

    /**
     * Displays edit profile page
     */
    public void showEditPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource
                    ("../view/profile.fxml"));
            AnchorPane mainPage = loader.load();

            Scene scene = new Scene(mainPage);
            mainScreen.setScene(scene);
            mainScreen.show();

            ProfileController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for Profile");
            e.printStackTrace();
        }
    }

    /** Automatically called upon app close */
    @Override
    public void stop() throws Exception {
        UserDatabaseInterface.close();
    }

    /**
     * Shows the viewReportTable page when View Reports button is pressed.
     */
    public void showViewReportsTable() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource
                    ("../view/viewReportTable.fxml"));
            AnchorPane mainPage = loader.load();

            Scene scene = new Scene(mainPage);
            mainScreen.setScene(scene);
            mainScreen.show();

            ReportsTableController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen");
            e.printStackTrace();
        }
    }

    /**
     * Shows the viewReport page when View Reports button is pressed.
     */
    public void showViewReports() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource
                    ("../view/viewReports.fxml"));
            AnchorPane mainPage = loader.load();

            Scene scene = new Scene(mainPage);
            mainScreen.setScene(scene);
            mainScreen.show();

            ViewReportController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen");
            e.printStackTrace();
        }
    }

    /**
     * Shows the Submit Reports page when Submit Reports button is pressed.
     */
    public void showSubmitReports() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource
                    ("../view/submitReports.fxml"));
            AnchorPane mainPage = loader.load();

            Scene scene = new Scene(mainPage);
            mainScreen.setScene(scene);
            mainScreen.show();

            SubmitReportsController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen");
            e.printStackTrace();
        }
    }

    public void showWaterSourceReport() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource("../view/submitWaterSource.fxml"));
            AnchorPane mainPage = loader.load();

            Scene scene = new Scene(mainPage);
            mainScreen.setScene(scene);
            mainScreen.show();

            WaterReportController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen");
            e.printStackTrace();
        }
    }

    public void showWaterQualityReport() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource("../view/submitWaterQuality.fxml"));
            AnchorPane mainPage = loader.load();

            Scene scene = new Scene(mainPage);
            mainScreen.setScene(scene);
            mainScreen.show();

            WaterQualityReportController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
