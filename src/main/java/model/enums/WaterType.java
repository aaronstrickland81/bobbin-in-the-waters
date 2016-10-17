package model.enums;

/**
 * Created by Neil on 10/16/2016.
 */
public enum WaterType {
    BOTTLED("Bottled"),
    Well("Well"),
    Stream("Stream"),
    Lake("Lake"),
    Spring("Spring"),
    Other("Other");

    String type;

    WaterType(String str) {
        this.type = str;
    }

    public String toString() {
        return type;
    }

    public static WaterType getType(String tString) {
        for (WaterType t : WaterType.values()) {
            if (t.toString().equals(tString)) {
                return t;
            }
        }
        return null;
    }
}
