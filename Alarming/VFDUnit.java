package Alarming;

import Firefighters.IFirefighter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VFDUnit implements IVFDUnit {

    private String unitName;
    private String testCode;
    private String alarmCode;
    private List<IFirefighter> firefighters = new ArrayList<>();
    public Strategy strategy;
    private ResponseCode state = ResponseCode.TEST_OK;
    public VFDUnit(String unitName, String testCode, String alarmCode) {
        this.unitName = unitName;
        this.testCode = testCode;
        this.alarmCode = alarmCode;
    }


    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public void ContextInterface(){
        if (state == ResponseCode.ALARM_OK)
            setAction(new Alarm(this.unitName, firefighters));
        if (state == ResponseCode.TEST_OK)
            setAction(new Test(this.unitName));
        if (state == ResponseCode.ERROR){
            setAction(new Error(this.unitName));
        }
    }
    
    @Override
    public ResponseCode notify(String CCIR_CODE) {
        String type = CCIR_CODE.substring(0,1);
        String alertCode = CCIR_CODE.substring(1);
        String enteredCode = enterCode();
        if (type.equals("T")){
            if (enteredCode.equals(this.testCode)) {
                this.state = ResponseCode.TEST_OK;
            }
        }
        else if (type.equals("A")){
            if (enteredCode.equals(this.alarmCode)) {
                this.state = ResponseCode.ALARM_OK;
            }
        }
        else
            this.state = ResponseCode.ERROR;

        ContextInterface();
        return this.state;
    }

    public void setAction(Strategy action){
        strategy = action;
    }

    private String enterCode(){
        Scanner sc = new Scanner(System.in);
        System.out.print(this.unitName + " kod:");
        return sc.nextLine();
    }

    public void addFirefighter(IFirefighter newFirefighter){
        firefighters.add(newFirefighter);
    }

    public void removeFirefighter(IFirefighter firefighterToRemove){
        firefighters.add(firefighterToRemove);
    }

    public void notifyAllFirefighters() {
        for (IFirefighter Firefighter : firefighters){
            Firefighter.sendSms("Alarm");
        }
    }
}
