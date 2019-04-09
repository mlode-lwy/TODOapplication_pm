package pl.ml.fxControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Data;
import pl.ml.UserController.UserController;

/**
 * @author pmatusiak
 */

@Data
public class RegisterController {
    @FXML
    public TextField firstNameField;

    @FXML
    public TextField lastNameField;

    @FXML
    public TextField userNameField;

    @FXML
    public TextField passwordField;

    @FXML
    public Button applyButton;

    @FXML
    public Button cancelButton;

    public void setApplyButton() {
        if (UserController.checkIfLoginExists(userNameField.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Given login already exists");
            alert.setHeaderText("Login you entered already exists. Please chose another one");
            alert.showAndWait();
        } else {
            if (firstNameField.getText().equals("")
                    || lastNameField.getText().equals("")
                    || userNameField.getText().equals("")
                    || passwordField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("All fields must be filled");
                alert.setHeaderText("All fields must be filled");
                alert.showAndWait();
            } else if (!UserController.checkIfPasswordMatchesPattern(passwordField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect password");
                alert.setHeaderText("Password must: \n" +
                        "Be between 8 and 40 characters long\n" +
                        "Contain at least one digit.\n" +
                        "Contain at least one lower case character.\n" +
                        "Contain at least one upper case character.\n" +
                        "Contain at least on special character from [ @ # $ % ! . ].");
                alert.showAndWait();
            } else {
                UserController.registerUser(firstNameField.getText(), lastNameField.getText(), userNameField.getText(), passwordField.getText());
                Stage stage = (Stage) applyButton.getScene().getWindow();
                stage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User created!");
                alert.setHeaderText("Thank you for registering");
                alert.showAndWait();
            }
        }
    }

    public void setCancelButton() {
        Stage stage = (Stage) applyButton.getScene().getWindow();
        stage.close();
    }
}
