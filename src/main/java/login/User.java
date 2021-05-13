package login;

import java.util.Objects;

class User {

    private String userName;
    private String password;
    private boolean isActive;
    private boolean isAuthenticated;

    public User (String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.isActive = false;
        this.isAuthenticated = false;
    }

    public boolean isActive () {
        return isActive || isAuthenticated;
    }

    public void setActive () {
        isActive = true;
    }

    public boolean isAuthenticated () {
        return isAuthenticated;
    }

    public boolean authenticate (String password) {

        if (this.password.equals (password)) {
            isAuthenticated = true;
            return true;
        }

        return false;
    }

    public String getUserName () {
        return userName;
    }

    protected String getPassword () {
        return password;
    }

    public void logout () {
        isActive = false;
        isAuthenticated = false;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass () != o.getClass())) {
            return false;
        }

        User user = (User) o;
        return (this.userName.equals (user.getUserName ())) && (password.equals (user.getPassword ()));
    }
}