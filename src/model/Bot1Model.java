package model;

public class Bot1Model extends EntityModel {
    private static Bot1Model instance = null;
    private Bot1Model() {
    }

    public static Bot1Model getInstance() {
        if (instance == null) {
            instance = new Bot1Model();
        }
        return instance;
    }

    @Override
    public void bustsCheck() {

    }
}
