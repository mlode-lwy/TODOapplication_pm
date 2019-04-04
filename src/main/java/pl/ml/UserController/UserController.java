package pl.ml.UserController;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import pl.ml.HibernateUtil;

import javax.persistence.NoResultException;

/**
 * @author pmatusiak
 */
public class UserController {

    private static Session session;

    private final static Logger logger = Logger.getLogger(UserController.class);

    public static void registerUser() {
        session = HibernateUtil
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        Users user = new Users();
        user.setFirstName("Jan");
        user.setLastName("Kowalski");
        user.setUserName("kowal1337");
        user.setPassword("dupa");

        logger.log(Level.INFO, "Dodano "
                + user.getFirstName() + " "
                + user.getLastName() + " "
                + user.getUserName());

        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Try-catch subsides if statement.
     * Whenever query finds single result method ends with true.
     * When query finds nothing program proceeds to catch block.
     * Finally session is being closes
     */
    public static boolean checkIfLoginExists(String userName) {
        session = HibernateUtil
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        Users user;

        try {
            user = (Users) session.createQuery("FROM Users "
                    + "WHERE USER_NAME = \'" + userName + "\'")
                    .getSingleResult();

            logger.log(Level.WARN, "Sprawdzono: "
                    + " Imię: "
                    + user.getFirstName()
                    + ", Nazwisko: "
                    + user.getLastName()
                    + ", Nick: "
                    + user.getUserName()
                    + " -----OBIEKT ISTNIEJE");
            System.out.println("true");
            return true;
        } catch (NoResultException e) {
            logger.log(Level.WARN, "-----OBIEKT NIE ISTNIEJE!");
        } finally {
            session.close();
        }
        System.out.println("false");
        return false;
    }

    public static boolean checkIfLoginMatchesPassword(String userName, String password) {
        if (!checkIfLoginExists(userName)) {
            logger.log(Level.INFO, "Wrong login, or password!");
        } else {
            try {
                session = HibernateUtil
                        .getSessionFactory()
                        .openSession();
                session.beginTransaction();

                session.createQuery(
                        "FROM Users "
                                + "WHERE USER_NAME = '"
                                + userName
                                + "'"
                                + " AND PASSWORD = '"
                                + password
                                + "'")
                        .getSingleResult();
                return true;
            } catch (NoResultException e) {
                logger.log(Level.INFO,
                        "Sprawdzono: "
                                + "HASŁO BŁĘDNE");
                return false;
            } finally {
                session.close();
            }
        }
        return false;
    }


    public static void logIn() {
    }
}
