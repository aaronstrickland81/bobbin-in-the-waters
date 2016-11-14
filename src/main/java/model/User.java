package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.enums.AccountType;

/**
 * Container for all account data
 */
public class User {


    /**
     * Private fields for user properties
     */
    public final StringProperty uname = new SimpleStringProperty();
    public final StringProperty email = new SimpleStringProperty();
    public final StringProperty homeAddress = new SimpleStringProperty();
    public final StringProperty title = new SimpleStringProperty();
    public final StringProperty password = new SimpleStringProperty();
    public final StringProperty fname = new SimpleStringProperty();
    public final StringProperty lname = new SimpleStringProperty();
    public final ObjectProperty<AccountType> accountType = new SimpleObjectProperty<>();


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

    public void setUname(String uname) {
        this.uname.set(uname);
    }

    public AccountType getAccountType() {
        return accountType.get();
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getFname() {
        return fname.get();
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public String getLname() {
        return lname.get();
    }

    public void setLname(String lname) {
        this.lname.set(lname);
    }

    public void setAccountType(AccountType t) {
        this.accountType.set(t);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * Default Constructor
     *
     * @param user username
     * @param fname first name
     * @param lname last name
     * @param email email
     * @param pass password
     * @param type account accountType
     */
    public User(String user, String pass, AccountType type, String email, String fname, String lname) {
        this(user, pass, type, email, fname, lname, "", "");
    }

    public User(String user, String pass, AccountType type, String email,
                String fname, String lname, String address, String title) {
        setUname(user);
        setFname(fname);
        setLname(lname);
        setEmail(email);
        setPassword(pass);
        setAccountType(type);
        setHomeAddress(address);
        setTitle(title);
    }

    public User(String user, String pass) {
        this(user,pass, AccountType.USER, "", "", "", "", "");
    }

    public User(User aUser) {
        this(aUser.getUname()
                , aUser.getPassword()
                , aUser.getAccountType()
                , aUser.getEmail()
                , aUser.getFname()
                , aUser.getLname()
                , aUser.getHomeAddress()
                , aUser.getTitle());
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
                && this.getAccountType().equals(temp.getAccountType())
                && this.getHomeAddress().equals(temp.getHomeAddress())
                && this.getTitle().equals(temp.getTitle())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.uname.get() + "," + this.password.get() + ","
                + this.accountType.get().toString() + "," + this.email.get()
                + ","
                + this.fname.get() + "," + this.lname.get() + ","
                + this.homeAddress.get() + "," + this.title.get();
    }
}
