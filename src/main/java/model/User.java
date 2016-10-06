package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.SnapshotResult;

import java.util.HashMap;

/**
 * Created by Neil on 9/20/2016.
 */
public class User {


    /**
     * Private fields for username, password, firstname, lastname
     */
    public final StringProperty uname = new SimpleStringProperty();

    public final StringProperty email = new SimpleStringProperty();

    public final StringProperty homeAddress = new SimpleStringProperty();

    public final StringProperty title = new SimpleStringProperty();

    public final StringProperty password = new SimpleStringProperty();
    public final StringProperty fname = new SimpleStringProperty();
    public final StringProperty lname = new SimpleStringProperty();
    public final ObjectProperty<AccountType> type = new SimpleObjectProperty<>();

    public String getHomeAddress() {
        return homeAddress.get();
    }

    public void setHomeAddress(String newhAddress) {
        homeAddress.set(newhAddress);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String newTitle) {
        title.set(newTitle);
    }

    public String getUname() {
        return uname.get();
    }

    public StringProperty unameProperty() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname.set(uname);
    }

    public AccountType getType() {
        return type.get();
    }

    public ObjectProperty<AccountType> typeProperty() {
        return type;
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

    public void setType(AccountType t) {
        this.type.set(t);
    }
    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * Default Constructor
     * @param user username
     * @param fname first name
     * @param lname last name
     * @param email email
     * @param pass password
     * @param type account type
     */
    public User(String user, String pass, AccountType type,  String email, String fname, String lname  ) {
        setUname(user);
        setFname(fname);
        setLname(lname);
        setEmail(email);
        setPassword(pass);
        setType(type);
    }

    public User(String user, String pass) {
        this(user,pass, AccountType.USER, "", "", "");
    }

    public User(User aUser) {
        this(aUser.getUname()
                , aUser.getPassword()
                , aUser.getType()
                , aUser.getEmail()
                , aUser.getFname()
                , aUser.getLname());
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) { return false;}
        if (null == obj) { return false; }
        if (this == obj) { return true; }
        User temp = (User) obj;
        if (this.getUname().equals(temp.getUname())
                && this.getPassword().equals(temp.getPassword())
                && this.getEmail().equals(temp.getEmail())
                && this.getFname().equals(temp.getFname())
                && this.getLname().equals(temp.getLname())
                && this.getType().equals(temp.getType())) {
            return true;
        }
        return false;
    }

    //TODO: Make this method better for future use
    @Override
    public String toString() {
        return this.uname.get() + "," + this.password.get() + ","
                + this.type.get().toString() + "," + this.email.get() + ","
                + this.fname.get() + "," + this.lname.get();
    }
}
