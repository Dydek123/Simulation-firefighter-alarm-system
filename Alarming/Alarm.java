package Alarming;

import Firefighters.IFirefighter;

import java.util.ArrayList;
import java.util.List;

public class Alarm implements Strategy{
    private String unitName;
    private  List<IFirefighter> firefighters = new ArrayList<>();

    public Alarm(String unitName, List<IFirefighter> firefighters) {
        this.unitName = unitName;
        this.firefighters = firefighters;
        action();
    }

    @Override
    public void action() {
        System.out.println(unitName + " ALARM !");
        System.out.println(unitName + " uzbrajam terminal DTD-53!");
        for (IFirefighter Firefighter : firefighters){
            Firefighter.sendSms("Alarm");
        }
    }
}
