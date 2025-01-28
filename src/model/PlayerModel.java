package model;

public class PlayerModel extends EntityModel {
    private static PlayerModel instance = null;


    private PlayerModel() {
        System.out.println("Player's hand: " + hand);


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
                System.out.println("Player busts");
                TurnManager.getInstance().nextTurn();
            } else {
                return;

            }
        }
    }
}
