package model.enums;


/**
 * Source Condition enum for SourceReports
 * Created by Neil on 10/16/2016.
 */
public enum SourceCondition {
    WASTE("Waste"),
    TREATABLE_CLEAR("Treatable Clear"),
    TREATABLE_MUDDY("Treatable Muddy"),
    POTABLE("Potable");

    final String cond;

    SourceCondition(String str) {
        this.cond = str;
    }

    public String toString() {
        return cond;
    }

    /**
     * Converts string to appropriate SourceCondition object.
     *
     * @param tString String representing SourceCondition
     * @return SourceCondition object matching given string
     */
    public static SourceCondition getCondition(String tString) {
        for (SourceCondition t : SourceCondition.values()) {
            if (t.toString().equals(tString)) {
                return t;
            }
        }
        return null;
    }
}
