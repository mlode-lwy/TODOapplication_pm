package pl.ml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.ml.UserController.UserController;

/**
 * @author pmatusiak
 */
public class Main extends Application {

    public final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        launch(args);
//
//        BasicConfigurator.configure();
//        logger.setLevel(Level.ALL);
//
//        UserController.registerUser();
//
//        //UserController.checkIfLoginExists("kowal137");
//        System.out.println();
//
//        SessionFactory factory = HibernateUtil.getSessionFactory();
//        Session session = factory.openSession();
//        Transaction transaction;
//        try {
//            transaction = session.beginTransaction();
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BasicConfigurator.configure();
        logger.setLevel(Level.ALL);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/logscreen.fxml"));
        fxmlLoader.load();

        Parent root = fxmlLoader.getRoot();
        primaryStage.setScene(new Scene(root, 650, 350));
        primaryStage.show();
    }
}
