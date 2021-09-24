package domain;

public class Number {

    int value;

    public Number(int value){
        checkMinus(value);
        this.value=value;
    }

    public Number(String value){
        int iValue=Integer.parseInt(value);
        checkMinus(iValue);
        this.value=iValue;
    }

    public void checkMinus(int value){
        if (value < 0){
            throw new RuntimeException("음수 감지");
        }
    }

    public int getValue() {
        return this.value;
    }
}
