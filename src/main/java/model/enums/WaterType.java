package model.enums;

/**
 * Enumeration for type of water source.
 *
 * Created by Neil on 10/16/2016.
 */
public enum WaterType {
    BOTTLED("Bottled"),
    Well("Well"),
    Stream("Stream"),
    Lake("Lake"),
    Spring("Spring"),
    Other("Other");

    final String type;

    /**
     * Constructor for WaterType.
     *
     * @param str String representing WaterType
     */
    WaterType(String str) {
        this.type = str;
    }

    @Override
    public String toString() {
        return type;
    }

    /**
     * Converts string to associated WaterType.
     *
     * @param tString String representing WaterType
     * @return WaterType according to given string
     */
    public static WaterType getType(String tString) {
        for (WaterType t : WaterType.values()) {
            if (t.toString().equals(tString)) {
                return t;
            }
        }
        return null;
    }
}
