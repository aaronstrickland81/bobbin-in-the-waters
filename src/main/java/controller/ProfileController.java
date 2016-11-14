package controller;

import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;
import model.User;


/**
 * Controller for the edit profile page. On this page, users can edit their
 * profile information.
 *
 * Created by kavish on 10/1/16.
 */
public class ProfileController {

    /** the stage for this dialog */
    private Stage _dialogStage;

    /** reference to the FX App */
    private FXApplication app;

    /** reference to the model instance */
    private final Model model = Model.getInstance();

    /**
     * Sets the stage for this dialog.
     *
     * @param dialogStage Stage for the dialog
     */
    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    /** reference to currently logged in User */
    private User aUser;

    /** references to FXML widgets */
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private PasswordField confPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private TextField homeAddress;

    @FXML
    private TextField title;

    /** Called automatically upon load */
    @FXML
    private void initialize() {
        aUser = Model.getUser();
        firstName.setText(aUser.getFname());
        lastName.setText(aUser.getLname());
        email.setText(aUser.getEmail());
        homeAddress.setText(aUser.getHomeAddress());
        title.setText(aUser.getTitle());
    }

    /**
     * Sets the FX App for the controller.
     *
     * @param fxapp the FX App for the controller
     */
    public void setMainApp(FXApplication fxapp) {
        app = fxapp;
    }

    /**
     * Called when the user clicks the back button. Displays the main page.
     */
    @FXML
    private void handleBack() {
        app.showMainPage();
    }

    /**
     * Called when the user clicks the save button. Validates input, if valid
     * stores the updated user information and displays main page.
     */
    @FXML
    private void handleSave() {
        if (this.isInputValid()) {
            updateUser(aUser);
            Model.editUser(aUser);
            app.showMainPage();
        }
    }

    /**
     * Updates a user based on the information in the entry fields.
     *
     * @param user User to be updated
     * @return Updated user
     */
    private void updateUser(User user) {
        user.setFname(firstName.getText());
        user.setLname(lastName.getText());
        user.setEmail(email.getText());
        user.setHomeAddress(homeAddress.getText());
        user.setTitle(title.getText());
        if ((newPassword != null) && !newPassword.getText().isEmpty()) {
            user.setPassword(newPassword.getText());
        }
    }

    /**
     * Validates the user input in the text fields. If invalid, displays an
     * alert box informing the user of the invalid data.
     *
     * @return true if input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        //check if password and confirm password match
        if (!newPassword.getText().equals(confPassword.getText())) {
            errorMessage += "Passwords do not match\n";
        }

        //no error message means success / good input
        if (errorMessage.isEmpty()) {
            return true;
        } else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(_dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
