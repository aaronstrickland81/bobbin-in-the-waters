package model;

/**
 * Created by Neil on 9/20/2016.
 */
public enum AccountType {
    USER("User"),
    WORKER("Worker"),
    MANAGER("Manager"),
    ADMINISTRATOR("Administrator");

    String name;

    AccountType(String str) {
        this.name = str;
    }

    public String toString() {
        return name;
    }

    public static AccountType getType(String tString) {
        boolean contains = false;
        for(AccountType t: AccountType.values()) {
            if (t.toString().equals(tString)) {
                return t;
            }
        }
        return null;
    }
}
