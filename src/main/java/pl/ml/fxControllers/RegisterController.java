package pl.ml.fxControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.Data;

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

    public void setApplyButton(MouseEvent mouseEvent) {

    }

    public void setCancelButton(MouseEvent mouseEvent) {

    }
}
