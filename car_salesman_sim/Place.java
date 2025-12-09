
import java.util.ArrayList;

public class Place {


    /**
     * The locations in the game
     */
    public String name;
    public Person NPC;
    public ArrayList<Item> objects;
    public ArrayList<Car> cars;

    public Place(String name){
        this.name = name;
        this.NPC = null;
        this.objects = new ArrayList<>();
        setObjects(name);
    }

  
    /**
     * Creates and adds items to each loction for the player to pick up. Also creates NPCs.
     * @param name
     */
    public void setObjects(String name){
        String[] homeItems = {"shoes", "keys", "wallet", "watch", "sunglasses", "clothes"};
        String[] fridgeItems = {"bread", "egg", "milk", "water", "soda", "juice", "jam", "tomatoes", "roast beef", "onion", "orange"};
        String[] dinerItems = {"peanuts", "soda", "juice", "breakfast", "lunch", "dinner", "sandwich", "pizza", "wine(alcohol)", "beer(alcohol)"};
        String[] carItems = { "oil", "coolant", "brake fluid", "transmission fluid", "brakeleen", "pb-blaster", "wiper fluid", 
        "brake rotors", "tires", "paint", "o-rings", "oil filters"};
        String[] officeItems = {"paperclips", "paper", "stapler", "rubber band ball"};
        String[] suburbItems = {"wine(alcohol)", "beer(alcohol)", "tv remote", "baseball", "magazine"};
        String[] trailerItems = {"beer(alcohol)", "cigarettes", "dog toy", "rusted tricycle"};


        if (name.contains("home")){
            for (int idx = 0; idx < homeItems.length; idx++) {
                Item item = new Item(homeItems[idx]);
                this.objects.add(item);
                
            }
        }
      
        if (name.contains("diner")){
            for (int idx = 0; idx < dinerItems.length; idx++) {
                Item item = new Item(dinerItems[idx]);
                this.objects.add(item);
                this.NPC = new Person("Honey the Waitress", this);
                this.NPC.randomResponses = new String[]{"You need anything?", "Talk to the bartender if you want to order food.", 
                "Sorry sugar, I'm on the clock.", "Busy busy.", "No time to talk now", "Don't forget to leave me a tip"};
                
            }  
        }
        if (name.contains("auto")||name.contains("shop")){
            for (int idx = 0; idx < carItems.length; idx++) {
                Item item = new Item(carItems[idx]);
                this.objects.add(item);
            }
              
        }
        if (name.contains("office")){
            for (int idx = 0; idx < officeItems.length; idx++) {
                Item item = new Item(officeItems[idx]);
                this.objects.add(item);
                
            }  
        }
        if (name.contains("suburb")){
            this.NPC = new Person("Suburban dad", this);
            this.NPC.randomResponses =  new String[] {"Now you don't see weather like this very often.", 
            "You know, the other day it rained so hard, it wasn't even raining cats and dogs, it was raining lions and wolves!",
            "Yeah, I know what you mean.", "Hey, cheer up pal.", "You check your email recently? I hear you can find out about market trends that way.",
            "Did you see what that guy said on TV last night? Unbelievable."};
            for (int idx = 0; idx < suburbItems.length; idx++) {
                Item item = new Item(suburbItems[idx]);
                this.objects.add(item);
            }  
        }
        if (name.contains("trailer")){
            this.NPC = new Person("Shady Dave", this);
            this.NPC.randomResponses = new String[]{"You know anybody selling a lawnmower?", 
            "Watch out, this yard full of snakes, nearly stepped on one yesterday.", "My dishwasher broke the other day. Unbelievable. Can't have nothing in Oklahoma.",
            "You ever see anybody play the banjo underwater? Yeah, me neither.", "Yeah, no kidding"};
            for (int idx = 0; idx < trailerItems.length; idx++) {
                Item item = new Item(trailerItems[idx]);
                this.objects.add(item);
            }  
        }
        if (name.contains("grocery")){
            for (int idx = 0; idx < fridgeItems.length; idx++) {
                Item item = new Item(fridgeItems[idx]);
                this.objects.add(item);
            }
        }
        if (name.contains("parking")){
            this.NPC = new Person("Bored customer", this);
        }

    }
}
