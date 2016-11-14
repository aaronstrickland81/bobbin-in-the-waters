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
    private final StringProperty uname = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty homeAddress = new SimpleStringProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty fname = new SimpleStringProperty();
    private final StringProperty lname = new SimpleStringProperty();
    private final ObjectProperty<AccountType> accountType = new SimpleObjectProperty<>();


    /**
     * Getter for home address
     *
     * @return String of home address
     */
    public String getHomeAddress() {
        return homeAddress.get();
    }

    /**
     * Setter for home address
     *
     * @param newhAddress string that is to be set
     */
    public void setHomeAddress(String newhAddress) {
        homeAddress.set(newhAddress);
    }

    /**
     * Getter for title
     *
     * @return String of title
     */
    public String getTitle() {
        return title.get();
    }

    /**
     * Setter for title
     *
     * @param newTitle string that is to be set
     */
    public void setTitle(String newTitle) {
        title.set(newTitle);
    }

    /**
     * Getter for username
     *
     * @return String of username
     */
    public String getUname() {
        return uname.get();
    }

    /**
     * Setter for username
     *
     * @param uname string that is to be set
     */
    private void setUname(String uname) {
        this.uname.set(uname);
    }

    /**
     * Getter for account type
     *
     * @return AccountType of the account type
     */
    public AccountType getAccountType() {
        return accountType.get();
    }

    /**
     * Se
     *
     * @param t
     */
    public void setAccountType(AccountType t) {
        this.accountType.set(t);
    }

    /**
     * Getter for password
     *
     * @return String of password
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * Setter for password
     *
     * @param password string to be set
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

    /**
     * Getter for first name
     *
     * @return String of first name
     */
    public String getFname() {
        return fname.get();
    }

    /**
     * Setter for first name
     *
     * @param fname string to set
     */
    public void setFname(String fname) {
        this.fname.set(fname);
    }

    /**
     * Getter for last name
     *
     * @return String of last name
     */
    public String getLname() {
        return lname.get();
    }

    /**
     * Setter for last name
     *
     * @param lname string to set
     */
    public void setLname(String lname) {
        this.lname.set(lname);
    }

    /**
     * Getter for email
     *
     * @return String of email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * Setter for email
     *
     * @param email the string to set
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * Default constructor
     *
     * @param user username
     * @param pass password
     * @param type type
     * @param email email
     * @param fname first name
     * @param lname last name
     * @param address address
     * @param title title
     */
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

    /**
     * Constructor
     *
     * @param user  username
     * @param pass  password
     * @param type  account accountType
     * @param email email
     * @param fname first name
     * @param lname last name
     */
    public User(String user, String pass, AccountType type, String email, String fname, String lname) {
        this(user, pass, type, email, fname, lname, "", "");
    }

    /**
     * Constructor
     *
     * @param user username
     * @param pass password
     */
    public User(String user, String pass) {
        this(user,pass, AccountType.USER, "", "", "", "", "");
    }

    /**
     * Constructor
     *
     * @param aUser user
     */
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
        if (null == obj) { return false; }
        if(!(obj instanceof User)) { return false;}
        if (this == obj) { return true; }
        User temp = (User) obj;
        return this.getUname().equals(temp.getUname())
                && this.getPassword().equals(temp.getPassword())
                && this.getEmail().equals(temp.getEmail())
                && this.getFname().equals(temp.getFname())
                && this.getLname().equals(temp.getLname())
                && this.getAccountType().equals(temp.getAccountType())
                && this.getHomeAddress().equals(temp.getHomeAddress())
                && this.getTitle().equals(temp.getTitle());
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
