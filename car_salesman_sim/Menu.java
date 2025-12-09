
import java.util.Hashtable;
import java.util.Scanner;

public class Menu {

    /**
     * A menu is just a hashtable bundled with a scanner, that allows the programmer to abstract the process of making decision menus
     */

    public Hashtable<Integer, String> options;

    /**
     * The Integer value of the hashtable acts as a pseudoindex, the String contains the actual information
     * @param options The size of the hashtable
     */
    public Menu(int options){
        this.options = new Hashtable<Integer, String>(options);
    }

    /**
     * Prompts the user to select an option from the hashtable
     * @return The "index" of the option that was selected
     */
    public int runMenu(){
        Scanner input = new Scanner(System.in);
        while (true) { 
            this.showOptions();
            Integer choice = input.nextInt();
            if (options.containsKey(choice)){
                return choice;
            } else if (choice == 0){
                System.out.println(input.nextLine());
                return 0;
            }  else {
                System.out.println("You need to choose one of the numbers on the list! (Or press 0 to quit)");
            }
        }

    }
    /**
     * Prints the available options
     */
    public void showOptions(){
        options.forEach((key,value)->{
            System.out.println("==="+ key +": " + value + "===");
        }
        );
        
    }
    
}
