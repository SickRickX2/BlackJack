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
        if (TurnManager.getInstance().getBotCount() == 0){
            if(PlayerModel.getInstance().getSum() < sum){
                endTurn();
            }
            else if (PlayerModel.getInstance().busts){
                endTurn();
            }
            else while (PlayerModel.getInstance().getSum() <= 21 && PlayerModel.getInstance().getSum() > sum){
                super.hit();
            }
        }
        else if (TurnManager.getInstance().getBotCount() == 1){
            if(PlayerModel.getInstance().getSum() < sum && Bot1Model.getInstance().getSum() < sum){
                endTurn();
            }
            else if (PlayerModel.getInstance().busts && Bot1Model.getInstance().busts){
                endTurn();
            }
            else while ((PlayerModel.getInstance().getSum() <= 21 && PlayerModel.getInstance().getSum() > sum) || (Bot1Model.getInstance().getSum() <= 21 && Bot1Model.getInstance().getSum() > sum)){
                super.hit();
            }
        }
        else if (TurnManager.getInstance().getBotCount() == 2){
            if(PlayerModel.getInstance().getSum() < sum && Bot1Model.getInstance().getSum() < sum && Bot2Model.getInstance().getSum() < sum){
                endTurn();
            }
            else if (PlayerModel.getInstance().busts && Bot1Model.getInstance().busts && Bot2Model.getInstance().busts){
                endTurn();
            }
            else while ((PlayerModel.getInstance().getSum() <= 21 && PlayerModel.getInstance().getSum() > sum) || (Bot1Model.getInstance().getSum() <= 21 && Bot1Model.getInstance().getSum() > sum) || (Bot2Model.getInstance().getSum() <= 21 && Bot2Model.getInstance().getSum() > sum)){
                super.hit();
            }
        }




//        if (PlayerModel.getInstance().sum < sum && Bot1Model.getInstance().sum < sum && Bot2Model.getInstance().sum < sum){
//            endTurn();
//        }
//        else
//        if ((PlayerModel.getInstance().busts && TurnManager.getInstance().getBotCount() == 0)|| ((PlayerModel.getInstance().busts && Bot1Model.getInstance().busts && TurnManager.getInstance().getBotCount() == 1)) || ((PlayerModel.getInstance().busts && Bot1Model.getInstance().busts && Bot2Model.getInstance().busts && TurnManager.getInstance().getBotCount() == 2))){
//            endTurn();
//            }
//        else while ((PlayerModel.getInstance().getSum() <= 21 && PlayerModel.getInstance().getSum() > sum) || (Bot1Model.getInstance().getSum() <= 21 && Bot1Model.getInstance().getSum() > sum) || (Bot2Model.getInstance().getSum() <= 21 && Bot2Model.getInstance().getSum() > sum)){
//            super.hit();
//        }

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
