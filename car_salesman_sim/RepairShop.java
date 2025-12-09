import java.util.ArrayList;

public class RepairShop {
    /**
     * Repair shop is also the name of a place instance, however the actual repairshop is its own class
     */
    public ArrayList<Car> cars;
    public int buildingSize; //Determines the number of bays that can be installed
    public int garageCapacity; //The number of bays currently installed
    public int parkingCapacity;
    private int upgrade; //Which attribute is currently being upgraded
    private boolean inProgress; //Wether or not upgrades are in progress
    private int completionDate; //The date on which the upgrades will be completed
    public ArrayList<Item> supplyCloset;
    Item[] supplies = {new Item("bleach"),new Item("oil"),new Item("coolant"),new Item("brake fluid"),
                       new Item("transmission fluid"),new Item("brakeleen"),new Item("pb-blaster"),new Item("wiper fluid")};

    public RepairShop(){
        this.cars = new ArrayList<>();
        this.buildingSize = 1;
        this.garageCapacity = 3;
        this.parkingCapacity = 15;
        this.supplyCloset = new ArrayList<>();
        this.inProgress = false;
        this.completionDate = -1;
        this.upgrade = -1;
        for (int idx = 0; idx < supplies.length; idx++) {
            this.supplyCloset.add(supplies[idx]);
            
        }
        
    }

    /**
     * Called from the computer class, schedules grarge upgrades
     * @param p The player who owns the repairshop
     * @param upgradeThis The attribute to be upgraded
     * @return boolean of wether or not the upgraded was sucessfully scheduled
     */
    public boolean  upgrade(Player p, int upgradeThis){
        if (this.inProgress){
            System.out.println("Upgrades are already in progress. They will be finished in " + this.completionDate + " days");
            return false;
        } else {
            Menu confirm = new Menu(2);
            confirm.options.put(1, "Yes");
            confirm.options.put(2, "No");
            if (upgradeThis == 1){
                System.out.println("Increase the size of the garage, allowing 5 new bays to be built. The cost of this upgrade is $6000 and the time it takes is 30 days \n Confirm upgrade?");
                int c = confirm.runMenu();
                if (c == 1){
                    p.bankBalance -= 6000;
                    this.completionDate = p.day+30;
                    this.upgrade = upgradeThis;
                    return true;
                } else {
                    return false;
                }
            } else if (upgradeThis == 2){
                System.out.println("Install a new bay and lift in the garage, allowing 1 new car to be worked on at a time. The cost of this upgrade is $1000 and the time it will take is 10 days.\n Confirm upgrade?");
                int c = confirm.runMenu();
                if (c == 1){
                    p.bankBalance -= 1000;
                    this.completionDate = p.day+10;
                    this.upgrade = upgradeThis;
                    return true;
                } else {
                    return false;
                }
            } else if (upgradeThis == 3){
                System.out.println("Clear weeds and pour asphalt to make room for 10 new parking spaces in the front lot, allowing you to store 10 more cars. The cost of this upgrade is $500 and the time it will take is 7 days. \n Confirm upgrade?");
                int c = confirm.runMenu();
                if (c == 1){
                    p.bankBalance -= 500;
                    this.completionDate = p.day+7;
                    this.upgrade = upgradeThis;
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
        
    }

    /**
     * Silent function that runs regularly to check if the upgrades have been completed
     * @param p The player who owns the repairshop
     */
    public void passiveUpgradeCheck(Player p){
        this.inProgress = true;
        if (p.day == this.completionDate){
            if (this.upgrade == 1){
                this.buildingSize += 1;
                this.inProgress = false;
            } else if (this.upgrade==2){
                this.garageCapacity += 5;
            } else if (this.upgrade == 3){
                this.parkingCapacity += 10;
            }
        }

    }

    public String toString(){
        return ("Garage has a max capacity of "+ 5*this.buildingSize + " with " + this.garageCapacity + 
        " bays currently installed. \n A maximum of " +  this.parkingCapacity +" cars can be stored here. \n Upgrades in progress: " + this.inProgress);
    }
    
}
