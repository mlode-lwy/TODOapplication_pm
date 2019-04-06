package pl.ml.UserController;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.rmi.runtime.Log;

import javax.jws.soap.SOAPBinding;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pmatusiak
 */
class UserControllerTest {

    static Users user;
    static Logger logger;

    @BeforeEach
    void setUp(){
        BasicConfigurator.configure();
        logger = Logger.getLogger(UserControllerTest.class);
        user = new Users();
        user.setFirstName("Jan");
        user.setLastName("Kowalski");
        user.setUserName("kowal1337");
        user.setPassword("dupa");
    }

    @Test
    void registerUserTest(){
        //TODO when
    }

    @Test
    void checkIfLoginExists(){
        assertAll("Should return first-true; second-false",
                () -> assertTrue(UserController.checkIfLoginExists("kowal1337")),
                () -> assertFalse(UserController.checkIfLoginExists("asdf")));
    }

    @Test
    void checkIfLoginMatchesPassword() {
        assertAll("Check",
                () -> assertTrue(UserController.checkIfLoginMatchesPassword("kowal1337", "dupa")),
                () -> assertFalse(UserController.checkIfLoginMatchesPassword("kowal1337","apud")),
                () -> assertFalse(UserController.checkIfLoginMatchesPassword("asd", "asd")),
                () -> assertFalse(UserController.checkIfLoginMatchesPassword("asd", "dupa")));
    }

}