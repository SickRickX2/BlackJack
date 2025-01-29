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
    public void hit() {
        while(sum < 17){
            super.hit();
        }

    }

    @Override
    public void bustsCheck() {
        while (TurnManager.getInstance().getCurrentTurn() == TurnManager.Turn.BOT1) {
            if (sum > 21) {
                busts = true;
                endTurn();
            } else {
                return;
            }
        }

    }
}
