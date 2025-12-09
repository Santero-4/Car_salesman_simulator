
import java.util.Hashtable;
import java.util.Random;

public class Worker extends Person{

    /**
     * The workers who fix the cars
     */
    public Hashtable<String, Integer> skills;
    public int wage;
    public Player boss;

    

    public Worker(String name, Player boss){
        super(name, "Mechanic");
        this.location = new Place("garage");
        this.skills = new Hashtable<>();
        Random skillAssigner = new Random();
        this.wage = skillAssigner.nextInt(64, 200);
        skills.put("Tires", skillAssigner.nextInt(0, 6));
        skills.put("Brakes", skillAssigner.nextInt(0, 6));
        skills.put("Suspension", skillAssigner.nextInt(0, 6));
        skills.put("Oil", skillAssigner.nextInt(0, 6));
        skills.put("Transmission", skillAssigner.nextInt(0, 6));
        skills.put("Engine", skillAssigner.nextInt(0, 6));
        this.boss = boss;
        


    }

    /**
     * Upgrades the skill level of a worker in one skill by one point, if it is not already at level five
     * @param skill The skill to be upgraded
     */
    public void learn(String skill){
        int level = this.skills.get(skill);
        if (level >= 5){
            System.out.println("I'm already profiecent in " + skill + " repair.");
        } else {
            this.skills.replace(skill, level +1);
            System.out.println(this.name + " increased skill of " + skill + " repair by one point.");
            this.boss.updateTime(2);

        }

    }

    /**
     * Repairs one part of one car by one point, if the worker has a high enough skill level
     * @param c The car to be repaired
     * @param part The part to be repaired
     */
    public void fix(Car c, String part){
        if (this.skills.get(part) >= 5){
            c.upgrade(part);
            System.out.println("Repaired the " + part + " using " + boss.shop.supplyCloset.get(0));
            boss.shop.supplyCloset.remove(0);
            if (boss.shop.supplyCloset.isEmpty()){
                System.out.println("Out of suppiles. You'll need to go to the auto parts store and restock.");
            }
            boss.updateTime(1);

        } else {
            System.out.println(this.name + " needs more training in order to repair " + part + 
                               ". \n Current skill level: " + this.skills.get(part) + "Required level: 5");
        }

    }
    public String toString(){
        return ("Worker: " +this.name + ". Daily wage: $" + this.wage + "\n Skills: \n" + this.skills);
    }
}
