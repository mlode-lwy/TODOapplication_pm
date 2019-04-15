package pl.ml.fxControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Data;
import pl.ml.UserController.UserController;
import pl.ml.UserController.Users;

import java.io.IOException;
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

    public void setLogInButton() throws IOException {
        Users user = UserController.logIn(userNameField.getText(), passwordField.getText());
        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong login or password");
            alert.setHeaderText("Wrong login or password");
            alert.showAndWait();
        } else {
            Stage registryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/tasks.fxml"));
            fxmlLoader.load();

            TaskSceneController taskSceneController = fxmlLoader.getController();
            taskSceneController.setUser(user);
            Parent root = fxmlLoader.getRoot();

            Stage stage = (Stage) logInButton.getScene().getWindow();
            stage.close();

            registryStage.setScene(new Scene(root, 650, 350));
            registryStage.show();
            taskSceneController.loadTasks();
        }
    }

    public void setRegistryButton() throws IOException {
        Stage registryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/registry.fxml"));
        fxmlLoader.load();

        Parent root = fxmlLoader.getRoot();
        registryStage.setScene(new Scene(root, 650, 350));
        registryStage.show();
    }
}
