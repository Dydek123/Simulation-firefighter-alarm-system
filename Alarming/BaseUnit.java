package Alarming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseUnit {
    private List<IVFDUnit> observerCollection = new ArrayList<>();

    public void registerObserver(IVFDUnit newUnit){
        observerCollection.add(newUnit);
    }

    public void unregisterObserver(IVFDUnit unitToRemove){
      observerCollection.remove(unitToRemove);
    }

    public void notifyAllObservers() {
        String type = getAlertType();
        for (IVFDUnit unit : observerCollection){
            VFDUnit unitObject = (VFDUnit) unit;
            String msg = new String(type+unitObject.getUnitName());
            ResponseCode responseCode = unit.notify(msg);
            System.out.println(unitObject.getUnitName()+" to base: " + responseCode + "\n");
        }
    }

    public void notifySpecificObservers() {
        int i=0;
        int howMany=0;
        List<Integer> unitToNorify = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (IVFDUnit unit : observerCollection){
            VFDUnit unitObject = (VFDUnit) unit;
            System.out.println(i +". " +unitObject.getUnitName());
            i++;
        }
        while (true){
            System.out.println("Ile jednostek powiadomić?");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next();
                continue;
            }
            howMany = scanner.nextInt();
            if (howMany<0 || howMany>observerCollection.size())
                System.out.println("Wybierz poprawną liczbe jednostek");
            else
                break;
        }
        if (howMany!=observerCollection.size()) {
            int j = 0;
            while (j < howMany) {
                System.out.print("Wybierz numer jednostki: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("That's not a number!");
                    scanner.next();
                    continue;
                }
                int unitNumber = scanner.nextInt();
                if (unitNumber < 0 || unitNumber >= observerCollection.size() || unitToNorify.contains(unitNumber)) {
                    System.out.println("Wybrano niepoprawną jednostkę");
                } else {
                    unitToNorify.add(unitNumber);
                    j++;
                }
            }

            String type = getAlertType();
            for (int k : unitToNorify) {
                VFDUnit unitObject = (VFDUnit) observerCollection.get(k);
                String msg = new String(type + unitObject.getUnitName());
                ResponseCode responseCode = unitObject.notify(msg);
                System.out.println(unitObject.getUnitName()+" to base: " + responseCode + "\n");
            }
        }
        else
            notifyAllObservers();
    }

    public String getAlertType(){
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Wprowadz typ powiadomenia");
            String type = sc.nextLine();
            type = type.toUpperCase();
            if (type.equals("ALARM"))
                return "A";
            if (type.equals("TEST"))
                return "T";
        }
    }
}
