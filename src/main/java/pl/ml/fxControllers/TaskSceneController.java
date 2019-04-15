package pl.ml.fxControllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Data;
import org.hibernate.Session;
import pl.ml.HibernateUtil;
import pl.ml.TaskController.Tasks;
import pl.ml.UserController.Users;


import javax.persistence.TypedQuery;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author pmatusiak
 */

@Data
public class TaskSceneController implements Initializable {


    private Users user;

    @FXML
    public TableView<Tasks> taskTableView;
    @FXML
    public TableColumn<Tasks,String> taskTableColumn;

    @FXML
    public Button doneButton;

    @FXML
    private MenuItem newMenuItem;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private MenuItem editMenuItem;
    @FXML
    private MenuItem showToDoTasksMenuItem;
    @FXML
    private MenuItem showDoneTasksMenuItem;
    @FXML
    private Label descriptionLabel;

    ObservableList<Tasks> tasksObservableList = FXCollections.observableArrayList();

    Session session;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadTasks() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery<Tasks> query = session.createQuery(
                "FROM Tasks "
                        + "WHERE USER_ID ='"
                        + user.getUserID()
                        + "'", Tasks.class);

        tasksObservableList = FXCollections.<Tasks> observableArrayList(query.getResultList());
        taskTableView.setItems(tasksObservableList);
        taskTableColumn.setText("Nazwa taska");
        taskTableColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTaskName()));
    }
}
