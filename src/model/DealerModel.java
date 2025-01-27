package model;

public class DealerModel extends EntityModel {
    private static DealerModel instance = null;
    private DealerModel(){
        drawCard();
        drawCard();
        drawCard();

    }
    public static DealerModel getInstance(){
        if(instance == null){
            instance = new DealerModel();
        }
        return instance;
    }

}
