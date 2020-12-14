package lab5;

import Alarming.BaseUnit;
import Alarming.VFDUnit;

public class Lab5 {
    public static void main(String[] args) {
        // TODO code application logic here

        BaseUnit baseUnit = new BaseUnit();
        VFDUnit testUnit = new VFDUnit("test","123","456");
        baseUnit.registerObserver(new VFDUnit("name","test","alarm"));
        baseUnit.registerObserver(new VFDUnit("name2","test2","alarm2"));
        baseUnit.registerObserver(testUnit);
        baseUnit.notifySpecificObservers();
//        baseUnit.notifyAllObservers();
//        baseUnit.unregisterObserver(testUnit);
//        baseUnit.notifyAllObservers();
    }
    
}
