
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

public class Main {
    /**
     * The game is executed from this class
     */

        
    public Scanner getInput = new Scanner(System.in);
    public ArrayList<Item> items = new ArrayList<>();
    public String[] itemNames =  {"bread", "sandwich", "pizza", "dinner", "breakfast", "lunch", "egg", "burger", "milk", "water", 
                                 "soda", "juice", "coffee", "wine(alcohol)", "beer(alcohol)", "bleach", "oil", "coolant", "brake fluid", 
                                 "transmission fluid", "brakeleen", "pb-blaster", "wiper fluid"};
    public String[] locationNames = {"home, living room", "home, bed", "home, bedroom", "home, hallway", "home, bathroom", "home, kitchen", 
                                "lost", "outside, driveway", "outside, bus stop", "outside, bus", "outside, auto parts store",
                                "car","outside, diner", "outside, trailer park", "outside, suburbs", "outside, grocery store", "work, office", 
                                "work, parking lot", "work, repair shop"};
    

    public ArrayList<Place> locations;

    public Main(){
        this.locations = new ArrayList<>();
        for (int idx = 0; idx < locationNames.length; idx++) {
            String name = locationNames[idx];
            locations.add(new Place(name));
        }

    }
    /**
     * Locates instances of items that are owned by a Place
     * @param input The user's entire input string
     * @param p The player
     * @return The item, if it existed in the player's current location
     */
    public Item parseItemLocation(String input, Player p){
        for (int idx = 0; idx < p.location.objects.size(); idx++) {
            String itemName = p.location.objects.get(idx).name;
            if (input.contains(itemName))
            return p.location.objects.get(idx);
            
        }
        Item null_item = new Item("null");
        return null_item;
        }

        
    
    /**
     * Locates instances of items that are in the player's inventory
     * @param input The user's entire input string
     * @param p The player
     * @return The item, if it was found
     */
    public Item parseItemInventory(String input, Player p){
        Enumeration<Item> playerItems = p.inventory.keys();
        while (playerItems.hasMoreElements()){
            Item testItem = playerItems.nextElement();
            if (input.contains(testItem.name)){
                return testItem;
            }
        }

        Item null_item = new Item("null");
        return null_item;
    }
    /**
     * Locates instances of items that are stored in item storage areas
     * @param input The user's entire input string
     * @param p The player
     * @return The item, if it was found
     */
    public Item parseItemStorage(String input, Player p){
        if (p.location.name.contains("kitchen")){
            if(p.fridge.isEmpty()){
                System.out.println("There's absolutely nothing in the fridge. Buy some groceries");
                return null;
            }
            for (int idx = 0; idx < p.fridge.size(); idx++) {
                Item testItem = p.fridge.get(idx);
                if (input.contains(testItem.name)){
                    p.fridge.remove(idx);
                    return testItem;
                } 
            }
            System.out.println("You're fresh out of that! Go to the grocery store!");
            return null;
        } else {
            for (int idx = 0; idx < p.shop.supplyCloset.size(); idx++) {
                Item testItem = p.shop.supplyCloset.get(idx);
                if (input.contains(testItem.name)){
                    p.shop.supplyCloset.remove(idx);
                    return testItem;
                } 
            }
        }
        System.out.println("You're fresh out of that! Go to the auto parts store!");
        return null;
    }

    /**
     * Locates an instance of a place, if the place has NPCs or anything that needs to remain consistent. Otherwise just makes a new instance
     * @param input The user's entire input string
     * @return A place
     */
    public Place parseLocation(String input){
        if (input.contains("grocery")){
            return new Place("grocery store");
        }
        if (input.contains("auto")){
            return new Place("auto parts store");
        }
        String[] parts = input.split(" ");
        String location = parts[parts.length - 1];
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).name.contains(location)){
                return locations.get(i);
            }
        }
        return new Place("null");

    }
    /**
     * Calls the appropriate functions based on the user's text input
     * @param p The player
     */
    public void parse(Player p){
        String input = getInput.nextLine();
        input = input.toLowerCase();
        if (input.contains("look")) {
            p.look();
        } else if (input.contains("sleep")){
            p.sleep();
        } else if (input.contains(" up")){
            p.getUp();
        } else if ((input.contains("take")&!(input.contains("shower")||input.contains("from"))) || input.contains("pick") ){
            Item item = parseItemLocation(input, p);
            if ((item.name.contentEquals("null"))){
                System.out.println("You couldn't pick it up.");
            } else {
                p.take(item);
                p.location.objects.remove(item);
                items.add(item);
            }
        } else if (input.contains("help")){
            p.help();
        } else if (input.contains("walk")|| input.contains("go")|| 
        (input.contains("get")&& !input.contains("dressed"))){
            Place location = parseLocation(input);
            if (!(location.name.contentEquals("null"))){
                p.go(location);
            } else {
                System.out.println("Can't get there");
            }
        } else if ((input.contains("use ")&! input.contains("computer"))|| input.contains("eat ")|| input.contains("drink ")) {
            Item item = parseItemInventory(input, p);
            item.use(p);
        } else if (input.contains("drive ")){
            Place destination = parseLocation(input);
            p.drive(destination);
        } else if (input.contains("shower")) {
            p.shower();
        } else if (input.contains("drop")||input.contains("put down")) {
            Item item = parseItemInventory(input, p);
            for (int idx = 0; idx < items.size(); idx++) {
                Item dropThis = items.get(idx);
                if (dropThis.name.equals(item.name)){
                    p.drop(dropThis);
                    items.remove(dropThis);
                    p.location.objects.add(dropThis);
                } 
                
            }
        } else if (input.contains("buy")&!input.contains("car")) {
            Item item = parseItemLocation(input, p);
            p.buy(item);
        } else if (input.contains("inventory")) {
            p.showInventory();
        } else if (input.contains("status")) {
            p.showStatus();
        } else if (input.contains("computer")&&(p.location.name.contains("office"))) {
            p.computer.parseMenu();
            p.updateTime(1);

        } else if (input.contains("dress")){
            p.dress();
        
        } else if (input.contains("talk")&!p.location.name.contains("shop")&!input.contains("bar")) {
            if (p.location.NPC != null){
                p.talk(p.location.NPC);
            } else {
                System.out.println("Talking to yourself...? Not a good look....");
            }
        } else if (input.contains("car")&! input.contains("sell")){
            if (p.location.NPC != null){
                Car c = p.location.NPC.showCars();
                p.location.NPC.negotiate(p, c);
            } else {
                System.out.println("Talking to yourself...? Not a good look....");
            }
        } else if (input.contains("talk")&& p.location.name.contains("shop")){
            p.interactWithWorkers();
        } else if (input.contains("talk")&&p.location.name.contains("diner")&&input.contains("bar")){
            p.shop();
        }else if (input.contains("sell")){
            if (p.location.name.contains("parking")){
                System.out.println("Sell which car?");
                Car c = p.showCars();
                p.location.NPC.negotiateAsCustomer(p, c);
            }
        } else if ((input.contains("cook")||input.contains("prepare")||input.contains("make"))&& p.location.name.contains("kitchen")){
            p.makeMeal();
        } else if (input.contains("fridge")||input.contains("closet")){
            Item item = parseItemStorage(input, p);
            if (item!= null){
                p.take(item);
            }  
        } else {
            System.out.println("That command isn't recognized");
        }
        
    }

    public static void main(String[] args) {
        Player p = new Player();
        p.help();
        Main main = new Main();
        p.showUI();
        p.showStatus();
        p.look();
        p.showInventory();
        while (p.day <365) {
            main.parse(p);
            p.checkBillsDue(true);
            p.shop.passiveUpgradeCheck(p);
        }

        
    }
    
}
