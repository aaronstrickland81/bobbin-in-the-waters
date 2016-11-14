package model.enums;

/**
 * AccountType Enum for Users
 * Created by Neil on 9/20/2016.
 */
public enum AccountType {
    USER("User"),
    WORKER("Worker"),
    MANAGER("Manager"),
    ADMINISTRATOR("Administrator");

    final String name;

    AccountType(String str) {
        this.name = str;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * gets the type from string
     * @param tString string representation of type
     * @return AccountType enum
     */
    public static AccountType getType(String tString) {
        for(AccountType t: AccountType.values()) {
            if (t.toString().equals(tString)) {
                return t;
            }
        }
        return null;
    }
}
