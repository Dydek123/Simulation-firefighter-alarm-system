import Alarming.BaseUnit;
import Alarming.VFDUnit;
import Firefighters.Firefighter;

public class Main {
    public static void main(String[] args) {
        BaseUnit baseUnit = new BaseUnit();
        VFDUnit testUnit = new VFDUnit("test","123","456");
        baseUnit.registerObserver(new VFDUnit("name","test","alarm"));
        baseUnit.registerObserver(new VFDUnit("name2","test2","alarm2"));
        baseUnit.registerObserver(testUnit);

        testUnit.addFirefighter(new Firefighter("Jan","Kowalski","987654321"));
        testUnit.addFirefighter(new Firefighter("Jan","Nowak","123465798"));

        baseUnit.notifySpecificObservers();
    }
    
}
