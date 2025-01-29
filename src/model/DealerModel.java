package model;

public class DealerModel extends EntityModel {
    private static DealerModel instance = null;

    private boolean hidden = true;
    private DealerModel(){


    }
    public static DealerModel getInstance(){
        if(instance == null){
            instance = new DealerModel();
        }
        return instance;
    }

    public boolean isHidden(){
        if (TurnManager.getInstance().getCurrentTurn() == TurnManager.Turn.DEALER || TurnManager.getInstance().getCurrentTurn() == TurnManager.Turn.END){
            hidden = false;
        }
        return hidden;
    }

    @Override
    public void hit() {
        if (PlayerModel.getInstance().sum < sum){
            endTurn();
        }
        else
        if (PlayerModel.getInstance().busts){
            endTurn();
            }
        else while ((PlayerModel.getInstance().getSum() <= 21 && PlayerModel.getInstance().getSum() > sum) || (Bot1Model.getInstance().getSum() <= 21 && Bot1Model.getInstance().getSum() > sum) || (Bot2Model.getInstance().getSum() <= 21 && Bot2Model.getInstance().getSum() > sum)){
            super.hit();
        }

    }

    @Override
    public void bustsCheck() {
        while (TurnManager.getInstance().getCurrentTurn() == TurnManager.Turn.DEALER) {
            if (sum > 21) {
                busts = true;
                endTurn();
            } else {
                return;

            }
        }
    }
}
