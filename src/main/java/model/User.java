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



    public final StringProperty password = new SimpleStringProperty();
    public final StringProperty fname = new SimpleStringProperty();
    public final StringProperty lname = new SimpleStringProperty();
    public final ObjectProperty<AccountType> type = new SimpleObjectProperty<>();


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

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     * {@code x}, {@code x.equals(x)} should return
     * {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     * {@code x} and {@code y}, {@code x.equals(y)}
     * should return {@code true} if and only if
     * {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * {@code x}, {@code y}, and {@code z}, if
     * {@code x.equals(y)} returns {@code true} and
     * {@code y.equals(z)} returns {@code true}, then
     * {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     * {@code x} and {@code y}, multiple invocations of
     * {@code x.equals(y)} consistently return {@code true}
     * or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the
     * objects is modified.
     * <li>For any non-null reference value {@code x},
     * {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) { return false;}
        if (null == obj) { return false; }
        if (this == obj) { return true; }
        User temp = (User) obj;
        if(this.getUname().equals(temp.getUname()) && this.getPassword().equals(temp.getPassword()) && this.getEmail().equals(temp.getEmail())
                && this.getFname().equals(temp.getFname()) && this.getLname().equals(temp.getLname()) && this.getType().equals(temp.getType())) {
            return true;
        }
        return false;
    }

    public User() {
        this("user", "pass");
    }
}
