package model.enums;

/**
 * Created by Neil on 10/11/2016.
 */
public enum PurityCondition {
    SAFE("Safe"),
    TREATABLE("Treatable"),
    UNSAFE("Unsafe");

    String cond;

    PurityCondition(String str) {
        this.cond = str;
    }

    public String toString() {
        return cond;
    }

    public static PurityCondition getCondition(String tString) {
        for (PurityCondition t : PurityCondition.values()) {
            if (t.toString().equals(tString)) {
                return t;
            }
        }
        return null;
    }
}
