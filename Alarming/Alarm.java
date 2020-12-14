package Alarming;

public class Alarm implements Strategy{
    String unitName;

    public Alarm(String unitName) {
        this.unitName = unitName;
        action();
    }

    @Override
    public void action() {
        System.out.println(unitName + " ALARM !");
        System.out.println(unitName + " uzbrajam terminal DTD-53!");
        System.out.println(unitName + " Wysyłam SMS");
    }
}
