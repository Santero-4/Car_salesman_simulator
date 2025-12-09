public class Item {

    /**
     * The items in the game, which have various properties
     */

    public String name;
    public String type;
    public int price;

    public Item(String name){
        this.name = name;
        this.setPrice();
    }

    public void findType(){
        String eatables = "bread, sandwich, pizza, dinner, breakfast, lunch, egg, burger, jam, tomatoes, roast beef, onion, orange breakfast, lunch, dinner, sandwich, pizza";
        String drinkables = "milk, water, soda, juice, coffee, wine(alcohol), beer(alcohol)";
        String toxics = "bleach, oil, coolant, brake fluid, transmission fluid, brakeleen, pb-blaster, wiper fluid";
        if (eatables.contains(this.name)){
            this.type = "eatable";
        } else if (drinkables.contains(this.name)){
            this.type = "drinkable";

        } else if (toxics.contains(this.name)){
            this.type = "toxic";

        } else {
            this.type = "nonconsumable";
        }
    }

    public void setPrice(){
        this.findType();
        if (this.type.contains("eatable")){
            this.price = 50;
        } else if (this.type.contains("drinkable")) {
            this.price = 25;
        } else {
            this.price = 10;
        }
    }

    public void use(Player p){
        if (this.type.contains("eatable")){
            p.eat(this);
        } else if (this.type.contains("drinkable")||this.type.contains("toxic")) {
            p.drink(this);
        } else {
            System.out.println("You don't know how");
        }
    }

    public String toString(){
        return this.name;
    }

    public boolean equals(Item i){
        return this.name.equals(i.name);
    }

    
}
