package model;

public class Model {
    /**
     * Set Model up as a singleton design pattern
     */
    private static final Model instance = new Model();

    public static Model getInstance() {
        return instance;
    }
}

