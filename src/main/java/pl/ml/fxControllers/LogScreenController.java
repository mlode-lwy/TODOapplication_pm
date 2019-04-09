package pl.ml.fxControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.Data;
import pl.ml.UserController.UserController;
import pl.ml.UserController.Users;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author pmatusiak
 */

@Data
public class LogScreenController implements Initializable {

    @FXML
    private TextField userNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button logInButton;

    @FXML
    private Button registerButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setLogInButton() {
        Users user = UserController.logIn(userNameField.getText(), passwordField.getText());
        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong login or password");
            alert.setHeaderText("Wrong login or password");
            alert.showAndWait();
        } else {

        }

    }

    public void setRegistryButton(MouseEvent mouseEvent) {

    }
}
