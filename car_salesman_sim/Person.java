
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Person{

    /*
     * The Person class has multiple variations for the different types of people in the game
     */

    public String name;
    public Place location;
    public int mood;
    public String job;
    public ArrayList<Car> cars;
    public String[] randomResponses;


    /**
     * The player class supers from this constructor
     */
    public Person(){
        this.location = new Place("null");
        this.mood = 0; 
        this.cars = new ArrayList<>();
    }

    /**
     * NPCs super from this constructor
     * @param name The name of the NPC
     * @param location The place where NPC can be found
     */
    public Person(String name, Place location){
        this.name = name;
        this.location = location;
        this.mood = 0;
        this.cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Car c = new Car(this.location);
            this.cars.add(c);
        }
    }
    /**
     * Employees super from this constuctor
     * @param name The name of the employee
     * @param job Can be used to differentiate employees from other NPCs
     */
    public Person(String name, String job){
        this.name = name;
        this.location = new Place("null");
        this.mood = 0;
        this.job = job;
    }

    
    /**
     * Method used by this person to sell a car to the player
     * @param c The car to be sold
     * @param p The player to sell the car to
     * @param r The repairshop belonging to the player
     */
    public void sellCar(Car c, Player p, RepairShop r){
        r.cars.add(c);
        p.cars.add(c);
        this.cars.remove(c);
    }
    
    /**
     * Method used by this person to buy a car from the player
     * @param c The car to be bought
     * @param p The player to buy the car from
     */
    public void buyCar(Car c, Player p){
        p.cars.remove(c);
        p.bankBalance += c.price;
        this.cars.add(c);

    }

    /**
     * Produces a sale interaction, then calls sellCar with the result
     * @param p The player
     * @param c The car to be sold to the player
     */
    public void negotiate(Player p, Car c){
        if (c!= null){
            Scanner negotiation = new Scanner(System.in);
            int salePrice = c.price-(50*mood);
            if (salePrice < 1){
                salePrice = 1;
            }
            String offer = (this.name + " offers to sell you the car for $" + salePrice);
            System.out.println(offer);
            String response = negotiation.nextLine().toLowerCase();
            if(response.contains("yes")||response.contains("buy")){
                System.out.println("Nice doing business with you");
                p.bankBalance -= salePrice;
                this.sellCar(c, p, p.shop);
            } else {
                System.out.println("The negotiation failed and "+ this.name + " is angry that you wasted their time. If you want a good price, make sure the person you're dealing with is in a good mood.");
            }
            p.updateTime(1);
        }       

    }
    /**
     * Produces a sale interaction, then calls buyCar with the result
     * @param p The player
     * @param c The car to be sold by the player to an NPC
     */
    public void negotiateAsCustomer(Player p, Car c){
        if (c!= null){
            Scanner negotiation = new Scanner(System.in);
            c.calculateFinalPrice();
            c.price += 50*this.mood;
            String offer = (this.name + " offers to buy  the car from you for $" + c.price);
            System.out.println(offer);
            String response = negotiation.nextLine().toLowerCase();
            if(response.contains("yes")||response.contains("sell")){
                System.out.println("Nice doing business with you");
                this.buyCar(c, p);
            } else {
                System.out.println("The negotiation failed and "+ this.name + " is angry that you wasted their time. If you want a good price, make sure the person you're dealing with is in a good mood.");
            }
            p.updateTime(1);
            

        }
    }

    /**
     * Method that triggers when you take something from an NPC's house
     * @return boolean of wether or not they noticed the theft
     */
    public boolean  noticeTheft(){
        Random random = new Random();
        int notice = random.nextInt(0, 2);
        if (notice == 0){
            return false;
        } else {
            this.mood -= 10;
            System.out.println(this.name + " caught you stealing and is very upset...");
            return true;
        }
    }

    /**
     * Creates a conversation interaction, then prompts the player to get a drink. If they accept, the NPC's mood is greatly increased
     * @param p The player
     */
    public void talk(Player p){
        Random responseChooser = new Random();
        Scanner conversation = new Scanner(System.in);
        System.out.println("Hey what's up?");
        if (this.mood > 3){
            System.out.println("You should ask me about my cars sometime after this conversation.");
        }
        for (int idx = 0; idx < (randomResponses.length/2); idx++) {
            conversation.nextLine();
            System.out.println(randomResponses[responseChooser.nextInt(randomResponses.length)]);
        }
        this.mood += 1;
        System.out.println("You wanna grab a drink or something?");
        if (!conversation.nextLine().toLowerCase().contains("n")){
            p.statusEffects.replace("Intoxicated", true);
            System.out.println("You got drunk with " + this.name + ". They really like you now");
            this.mood += 5;
            p.updateTime(1);
        }
        System.out.println("Hey I'll see you around. Come back if you ever wanna buy a car or something.");
        p.updateTime(1);
    }

    /**
     * Creates a conversation interaction, but doesn't prompt the player to drink
     * @param p The player
     */
    public void talkAsCustomer(Player p){
        Random responseChooser = new Random();
        Scanner conversation = new Scanner(System.in);
        System.out.println("Hey what's up?");
        for (int idx = 0; idx < (randomResponses.length/2); idx++) {
            conversation.nextLine();
            System.out.println(randomResponses[responseChooser.nextInt(randomResponses.length)]);
        }
        this.mood += 1;
        System.out.println("After talking to you, the customer's mood improved! You should try to sell them a car.");
        p.updateTime(1);
    }

    /**
     * Prompts the player to select one of the cars owned by the NPC
     * @return The car that was selected
     */
    public Car showCars(){
        Menu pickCar = new Menu(this.cars.size());
        for (int idx = 0; idx < this.cars.size(); idx++) {
            pickCar.options.put(idx+1, this.cars.get(idx).toString());
            System.out.println(this.cars.get(idx).toDetailedString());
        }
        int choice = pickCar.runMenu();
        if (choice >0){
            return this.cars.get(choice-1);
        } else {
            System.out.println("Didn't pick one");
            return null;
        }
        
    }




    public String toString(){
        return this.name;
    }

    

    


}