package model.enums;


/**
 * Created by Neil on 10/16/2016.
 */
public enum SourceCondition {
    WASTE("Waste"),
    TREATABLE_CLEAR("Treatable Clear"),
    TREATABLE_MUDDY("Treatable Muddy"),
    POTABLE("Potable");

    String cond;

    SourceCondition(String str) {
        this.cond = str;
    }

    public String toString() {
        return cond;
    }

    public static SourceCondition getCondition(String tString) {
        for (SourceCondition t : SourceCondition.values()) {
            if (t.toString().equals(tString)) {
                return t;
            }
        }
        return null;
    }
}
