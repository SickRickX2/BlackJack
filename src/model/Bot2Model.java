package model;

public class Bot2Model extends EntityModel {
    private static Bot2Model instance = null;
    private Bot2Model() {
    }

    public static Bot2Model getInstance() {
        if (instance == null) {
            instance = new Bot2Model();
        }
        return instance;
    }
    @Override
    public void bustsCheck() {

    }
}
