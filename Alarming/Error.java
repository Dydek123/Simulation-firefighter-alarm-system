package Alarming;

public class Error implements Strategy{
    private String unitName;
    public Error(String unitName) {
        this.unitName = unitName;
        action();
    }

    @Override
    public void action() {
        System.out.println(unitName + " ERROR");
    }
}
