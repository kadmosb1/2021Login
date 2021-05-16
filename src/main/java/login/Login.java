package login;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Met Login worden authenticatie en autorisatie afgehandeld.
 */
public class Login {

    private static final Scanner scanner = new Scanner (System.in);

    // Voor login maken we gebruik van het Singleton Pattern.
    private static Login singleton;
    private ArrayList<User> users;
    private ArrayList<Role> roles;

    /*
     * Omdat we het Singleton Pattern toepassen, blijft de default constructor private.
     */
    private Login () {
        // Normaal lezen we onderstaande gegevens uit de database. Nu coderen we ze hard in deze constructor.
        users = new ArrayList<> ();
        User user1 = new User("user1", "1");
        User user2 = new User("user2", "2");
        User user3 = new User("user3", "3");
        users.add (user1);
        users.add (user2);
        users.add (user3);

        roles = new ArrayList<> ();
        Role role1 = new Role("customer");
        Role role2 = new Role("product");
        Role role3 = new Role("invoice");
        role1.addUser (user1);
        role1.addUser (user3);
        role2.addUser (user2);
        role2.addUser (user3);
        role3.addUser (user1);
        role3.addUser (user2);
        role3.addUser (user3);
        roles.add (role1);
        roles.add (role2);
        roles.add (role3);
    }

    /*
     * Toepassing van het Singleton Pattern.
     */
    public static Login getInstance () {

        if (singleton == null) {
            singleton = new Login ();
        }

        return singleton;
    }

    /*
     * In de lijst met users wordt gezocht naar een gebruiker met userName.
     */
    private User getUser (String userName) {

        for (User user : users) {

            if (user.getUserName().equals (userName)) {
                return user;
            }
        }

        return null;
    }

    /*
     * In de lijst met user wordt gezocht naar een actieve gebruiker (die misschien zelfs is ingelogd).
     */
    public User getActiveUser () {

        for (User user : users) {

            if (user.isActive ()) {
                return user;
            }
        }

        return null;
    }

    /*
     * De gebruikersnaam van de actieve gebruiker wordt opgezocht.
     */
    public String getUserNameOfActiveUser () {

        User activeUser = getActiveUser ();

        if (activeUser != null) {
            return activeUser.getUserName ();
        }
        else {
            return "unknown";
        }
    }

    /*
     * De ingelogde gebruiker wordt opgezocht (als die er is).
     */
    public User getAuthenticatedUser () {

        for (User user : users) {

            if (user.isAuthenticated ()) {
                return user;
            }
        }

        return null;
    }

    /*
     * De naam van een gebruiker wordt ingelezen vanaf het toetsenbord.
     */
    private String readUserName () {
        System.out.print ("Voer uw gebruikersnaam in: ");
        return scanner.nextLine ();
    }

    /*
     * Het password van een gebruiker wordt ingelezen vanaf het toetsenbord.
     */
    private String readPassword () {
        System.out.print ("Voer uw password in: ");
        return scanner.nextLine ();
    }

    /*
     * Een melding (bijv. 'Mislukt') wordt in het volgende formaat getoond:
     *
     *      ========================================
     *      = Mislukt                              =
     *      ========================================
     */
    private void printMessage (String message) {

        System.out.println ("=".repeat (40));
        int numberOfSpaces = message.length () > 36 ? 0 : 40 - message.length ();
        System.out.printf ("= %s%s =", message, " ".repeat (numberOfSpaces));
        System.out.println ("=".repeat (40));
    }

    /*
     * Als er nog geen actieve gebruiker bekend is, kan de gebruiker zijn/haar gebruikersnaam invoeren. Hij/zij heeft
     * daarvoor 3 kansen (als de gebruikersnaam niet voorkomt in de 'database', kan hij/zij daarmee geen gebruik maken
     * van de applicatie).
     */
    private boolean activate () {

        if (getActiveUser () != null) {
            return true;
        }
        else {

            for (int i = 0; i < 3; i++) {

                String userName = readUserName();

                if (getUser (userName) != null) {
                    return true;
                }
                else {
                    printMessage ("Unknown user. " + (2 - i) + " attempts left.");
                }
            }
        }

        return false;
    }

    /*
     * Als er nog geen gebruiker actief is, kan een nieuwe gebruiker worden geactiveerd.
     */
    public boolean userIsActive () {

        if (getActiveUser () != null) {
            return true;
        }
        else {
            return activate ();
        }
    }

    /*
     * Een gebruiker met userName kan zonder toetsenbord worden geactiveerd (als hij/zij in de lijst voorkomt).
     */
    public void setActiveUser (String userName) {

        User activeUser = getActiveUser ();
        User newUser = getUser (userName);

        if (newUser != null) {

            // Een eventuele actieve gebruiker wordt eerst uitgelogd.
            if (newUser != activeUser) {
                logout ();
            }

            newUser.setActive ();
        }
    }

    /*
     * Als er nog geen gebruiker is ingelogd, kan een nieuwe gebruiker inloggen.
     */
    private boolean authenticate () {

        // Als er al een gebruiker actief is (die nog niet is ingelogd), hoeft deze alleen nog een password in te
        // voeren.
        User activeUser = getActiveUser ();

        if ((activeUser != null) && activeUser.isAuthenticated ()) {
            return true;
        }
        else {

            for (int i = 0; i < 3; i++) {

                String userName;
                User user = activeUser;

                if (user == null) {
                    userName = readUserName();
                    user = getUser (userName);
                }

                String password = readPassword ();

                if ((user != null) && user.authenticate (password)) {
                    return true;
                }
                else {
                    printMessage ("Unknown user or incorrect password. " + (2 - i) + " attempts left.");
                }
            }
        }

        return false;
    }

    /*
     * Als er nog geen ingelogde gebruiker is, kan een nieuwe gebruiker inloggen.
     */
    public boolean userIsAuthenticated () {

        if (getAuthenticatedUser () != null) {
            return true;
        }
        else {
            return authenticate ();
        }
    }

    /*
     * Een gebruiker kan direct (zonder toetsenbord) worden ingelegd met gebruikersnaam en password.
     */
    public boolean authenticate (String userName, String password) {

        // Als de ingelogde gebruiker de gebruiker met userName is, is hij al ingelogd.
        User user = getAuthenticatedUser ();

        if ((user != null) && (user.getUserName ().equals (userName))) {
            return true;
        }

        // Als de gebruiker niet bestaat, kan zij/hij ook niet inloggen.
        user = getUser (userName);

        if (user == null) {
            return false;
        }
        else {

            // Een eventuele gebruiker die al was ingelogd, wordt uitgelogd.
            logout ();

            // Als het password van de nieuwe gebruiker klopt, wordt hij ingelogd. Anders kan hij met een ander (het
            // correcte) password inloggen.
            if (user.authenticate (password)) {
                return true;
            }
            else {
                user.setActive ();
                return authenticate ();
            }
        }
    }

    /*
     * Een gebruiker kan met userName inloggen.
     */
    public boolean authenticate (String userName) {
        return authenticate (userName, null);
    }

    /*
     * De rol met roleName wordt opgevraagd (als die tenminste bekend is).
     */
    private Role getRole (String roleName) {

        for (Role role : roles) {

            if (role.getName ().equals (roleName)) {
                return role;
            }
        }

        return null;
    }

    /*
     * Voor een actieve gebruiker wordt gecontroleerd of hij in een rol is toegewezen bij de start van Login.
     */
    public boolean isAuthorized (String roleName) {

        User user = getActiveUser ();
        Role role = getRole (roleName);
        return (role != null) && (role.userIsInRole(user));
    }

    /*
     * De actieve gebruiker wordt uitgelogd.
     */
    public void logout () {
        User user = getActiveUser ();

        if (user != null) {
            user.logout ();
        }
    }
}