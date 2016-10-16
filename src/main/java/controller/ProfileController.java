package controller;

import database.MySQLdb;
import fxapp.FXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;
import model.User;
import services.UserDatabaseInterface;


/**
 * Created by kavish on 10/1/16.
 */
public class ProfileController {
    private Stage _dialogStage;

    private FXApplication app;

    private Model model = new Model();

    public void setDialogStage(Stage dialogStage) {
        _dialogStage = dialogStage;
    }

    private User aUser;

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

    /** Called automatically upon load */
    @FXML
    private void initialize() {
        aUser = Model.getUser();
        firstName.setText(aUser.getFname());
        lastName.setText(aUser.getLname());
        email.setText(aUser.getEmail());
    }


    public void setMainApp(FXApplication fxapp) {
        app = fxapp;
    }

    @FXML
    private void handleBack() {
        app.showMainPage();
    }

    @FXML
    private void handleSave() {
        if (this.isInputValid()) {
            aUser = updateUser(aUser);
            MySQLdb.updateUser(aUser);
            app.showMainPage();
        }
    }

    private User updateUser(User user) {
        user.setFname(firstName.getText());
        user.setLname(lastName.getText());
        user.setEmail(email.getText());
        if (newPassword != null && newPassword.getText().length() != 0) {
            user.setPassword(newPassword.getText());
        }
        return user;
    }

    /**
     * Validates the user input in the text fields.
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
        if (errorMessage.length() == 0) {
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
