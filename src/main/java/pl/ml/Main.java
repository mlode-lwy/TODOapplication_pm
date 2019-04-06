package pl.ml;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.ml.UserController.UserController;

import javax.jws.soap.SOAPBinding;

/**
 * @author pmatusiak
 */
public class Main {

    public final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        logger.setLevel(Level.ALL);

        //UserController.registerUser();

//        UserController.checkIfLoginExists("kowal137");
        System.out.println();

        UserController.removeUser("kowal1337", "dupa");

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
