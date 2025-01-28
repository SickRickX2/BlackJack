package model;

public class DealerModel extends EntityModel {
    private static DealerModel instance = null;

    private boolean hidden = true;
    private DealerModel(){
        drawCard();
        drawCard();

    }
    public static DealerModel getInstance(){
        if(instance == null){
            instance = new DealerModel();
        }
        return instance;
    }

    public boolean isHidden(){
        return hidden;
    }


}
