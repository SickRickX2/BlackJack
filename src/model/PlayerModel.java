package model;

public class PlayerModel extends EntityModel {
    private static PlayerModel instance = null;


    private PlayerModel() {


    }
    public static PlayerModel getInstance(){
        if(instance == null){
            instance = new PlayerModel();
        }
        return instance;
    }
    public void bustsCheck() {
        while (TurnManager.getInstance().getCurrentTurn() == TurnManager.Turn.PLAYER) {
            if (sum > 21) {
                TurnManager.getInstance().nextTurn();
            } else {
                return;

            }
        }
    }
}
