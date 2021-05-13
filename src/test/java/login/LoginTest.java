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
        assertTrue (login.authorize("customer"));
        assertFalse (login.authorize("invoice"));
        assertTrue (login.authorize("product"));

        login.authenticate ("user2", "2");
        assertFalse (login.authorize("customer"));
        assertTrue (login.authorize("invoice"));
        assertTrue (login.authorize("product"));

        login.authenticate ("user3", "3");
        assertFalse (login.authorize("invoice"));
        assertFalse (login.authorize("customer"));
        assertTrue (login.authorize("product"));

        login.logout ();
        assertFalse (login.authorize("invoice"));
        assertFalse (login.authorize("customer"));
        assertFalse (login.authorize("product"));
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