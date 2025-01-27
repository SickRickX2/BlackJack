package model;

public class PlayerModel extends EntityModel {

    public PlayerModel() {
            for (int i = 0; i < 2; i++) {
                drawCard();
            }
    }
}
