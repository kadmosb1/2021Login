package login;

import java.util.ArrayList;

class Role {

    private String name;
    private ArrayList<User> users;

    public Role (String name) {
        this.name = name;
        users = new ArrayList<> ();
    }

    public String getName () {
        return name;
    }

    public void addUser (User user) {
        users.add (user);
    }

    public void removeUser (User user) {
        users.remove (user);
    }

    public boolean userIsInRole (User user) {

        for (User u : users) {
            if (u == user) {
                return true;
            }
        }

        return false;
    }
}
