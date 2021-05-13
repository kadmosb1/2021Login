package login;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private static Login login;

    @BeforeAll
    public static void init () {
        login = Login.getInstance ();
    }

    @Test
    public void authorizeTest () {

        login.authenticate ("user1", "1");
        assertTrue (login.isAuthorized("customer"));
        assertFalse (login.isAuthorized("invoice"));
        assertTrue (login.isAuthorized("product"));

        login.authenticate ("user2", "2");
        assertFalse (login.isAuthorized("customer"));
        assertTrue (login.isAuthorized("invoice"));
        assertTrue (login.isAuthorized("product"));

        login.authenticate ("user3", "3");
        assertFalse (login.isAuthorized("invoice"));
        assertFalse (login.isAuthorized("customer"));
        assertTrue (login.isAuthorized("product"));

        login.logout ();
        assertFalse (login.isAuthorized("invoice"));
        assertFalse (login.isAuthorized("customer"));
        assertFalse (login.isAuthorized("product"));
    }

    @Test
    public void authenticateTest () {
        User user1 = new User ("user1", "1");
        User user2 = new User ("user2", "2");
        login.authenticate ("user1", "1");
        assertTrue (login.userIsActive());
        assertTrue (login.userIsAuthenticated ());
        assertEquals (user1, login.getAuthenticatedUser());
        assertEquals (user1, login.getActiveUser ());
        login.setActiveUser ("user1");
        assertTrue (login.userIsActive());
        assertEquals (user1, login.getAuthenticatedUser());
        assertEquals (user1, login.getActiveUser ());
        login.setActiveUser ("user2");
        assertTrue (login.userIsActive());
        assertNull (login.getAuthenticatedUser());
        assertEquals (user2, login.getActiveUser ());
        login.setActiveUser ("user1");
        assertTrue (login.userIsActive());
        assertNull (login.getAuthenticatedUser());
        assertEquals (user1, login.getActiveUser ());
        login.authenticate ("user1", "1");
        assertTrue (login.userIsActive());
        assertTrue (login.userIsAuthenticated());
        assertEquals (user1, login.getAuthenticatedUser());
        assertEquals (user1, login.getActiveUser ());
    }
}