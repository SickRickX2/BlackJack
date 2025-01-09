package model;

public enum Value {
    ACE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING;
    public static Value getValues(int i){
        return Value.values()[i];
    }
    public static int getValuesLength(){
        return Value.values().length;
    }
}
