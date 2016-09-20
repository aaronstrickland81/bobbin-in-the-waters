package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.SnapshotResult;

/**
 * Created by Neil on 9/20/2016.
 */
public class User {


    /**
     * Private fields for username, password, firstname, lastname
     */
    public final StringProperty uname = new SimpleStringProperty();


    public final StringProperty password = new SimpleStringProperty();
    public final StringProperty fname = new SimpleStringProperty();
    public final StringProperty lname = new SimpleStringProperty();


    public String getUname() {
        return uname.get();
    }

    public StringProperty unameProperty() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname.set(uname);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getFname() {
        return fname.get();
    }

    public StringProperty fnameProperty() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public String getLname() {
        return lname.get();
    }

    public StringProperty lnameProperty() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname.set(lname);
    }

    public User(String user, String pass) {
        setUname(user);
        setPassword(pass);
    }

    public User() {
        this("user", "pass");
    }
}
