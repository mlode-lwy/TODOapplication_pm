package pl.ml.UserController;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import pl.ml.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pmatusiak
 */
public class UserController {

    private static Session session;

    private final static Logger logger = Logger.getLogger(UserController.class);

    private final static String passwordPattern =  "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

    public static void registerUser(String firstName, String lastName, String userName, String password) {
        session = HibernateUtil
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);

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

    public static void removeUser(String userName, String password) {
        if (checkIfLoginExists(userName)) {
            if (checkIfLoginMatchesPassword(userName, password)) {
                session = HibernateUtil
                        .getSessionFactory()
                        .openSession();
                session.beginTransaction();

                Users user;
                user = (Users) session.createQuery(
                        "FROM Users "
                                + "WHERE USER_NAME = '"
                                + userName
                                + "'"
                                + " AND PASSWORD = '"
                                + password
                                + "'")
                        .getSingleResult();

                logger.log(Level.INFO, "Your account has been removed!");
                session.delete(user);
                session.getTransaction().commit();
                session.close();
                //TODO move to loginScene
            } else {
                logger.log(Level.INFO, "Wrong password!");
            }
        } else {
            logger.log(Level.INFO, "Wrong login!");
        }
    }

    public static void editUser(String userName, String password, String firstName, String lastName) {
        if (checkIfLoginExists(userName)) {
            if (checkIfLoginMatchesPassword(userName, password)) {
                session = HibernateUtil
                        .getSessionFactory()
                        .openSession();
                session.beginTransaction();

                Users user;
                user = (Users) session.createQuery(
                        "FROM Users "
                                + "WHERE USER_NAME = '"
                                + userName
                                + "'"
                                + " AND PASSWORD = '"
                                + password
                                + "'")
                        .getSingleResult();

                logger.log(Level.INFO, "Your account has been edited!");
                user.setUserName(userName);
                user.setUserName(password);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                session.update(user);
                session.getTransaction().commit();
                session.close();
                //TODO close scene
            } else {
                logger.log(Level.INFO, "Wrong password!");
            }
        } else {
            logger.log(Level.INFO, "Wrong login!");
        }
    }

    public static Users logIn(String userName, String password) {
        if (!checkIfLoginExists(userName)) {
            logger.log(Level.INFO, "Wrong login, or password!");
        } else {
            try {
                session = HibernateUtil
                        .getSessionFactory()
                        .openSession();
                session.beginTransaction();

                Users user  = (Users) session.createQuery(
                        "FROM Users "
                                + "WHERE USER_NAME = '"
                                + userName
                                + "'"
                                + " AND PASSWORD = '"
                                + password
                                + "'")
                        .getSingleResult();
                return user;
            } catch (NoResultException e) {
                logger.log(Level.INFO,
                        "Sprawdzono: "
                                + "HASŁO BŁĘDNE");
                return null;
            } finally {
                session.close();
            }
        }
        return null;
    }

    public static boolean checkIfPasswordMatchesPattern(String password){
        Matcher matcher = Pattern.compile(passwordPattern).matcher(password);
        return matcher.matches();
    }
}
