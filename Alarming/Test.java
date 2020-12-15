package Alarming;

public class Test implements Strategy{
    private String unitName;
    public Test(String unitName) {
        this.unitName = unitName;
        action();
    }

    @Override
    public void action() {
        System.out.println(unitName + " TEST");
    }
}
