package model;

public class PlayerModel extends EntityModel {
    private static PlayerModel instance = null;


    private PlayerModel() {
        blackjackCheck();
    }
    public static PlayerModel getInstance(){
        if(instance == null){
            instance = new PlayerModel();
        }
        return instance;
    }
    @Override
    public void bustsCheck() {
        while (TurnManager.getInstance().getCurrentTurn() == TurnManager.Turn.PLAYER) {
            if (sum > 21) {
                busts = true;
                endTurn();
            } else {
                return;

            }
        }
    }
}
