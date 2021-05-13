package invoice;

public class Login {

    public static boolean isActive () {
        return login.Login.getInstance ().userIsActive ();
    }

    public static boolean isAuthenticated () {
        return login.Login.getInstance ().userIsAuthenticated ();
    }

    public static boolean isAuthorized (String roleName) {
        return login.Login.getInstance ().isAuthorized (roleName);
    }
}
