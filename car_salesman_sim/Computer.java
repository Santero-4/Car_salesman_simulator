
import java.util.ArrayList;
import java.util.Random;




public class Computer extends Menu{

    /**
     * The computer class is used to simplify a lot of important gameplay actions
     */

    public Player owner;
    
    
    public Computer(Player p, int options){
        super(options);
        this.options.put(1, "Hire employees");
        this.options.put(2, "Fire employees");
        this.options.put(3, "View employees");
        this.options.put(4, "View cars and schedule garage upgrades");
        this.options.put(5, "Pay bills");
        this.options.put(6, "Check email");
        this.owner = p;
    }


    public void parseMenu(){
        while (true){
            int choice = this.runMenu();
            if (choice == 0){
                System.out.println("Power off...");
                break;
            } else {
                switch (choice) {
                    case 1 -> hire();
                    case 2 -> fire();
                    case 3 -> viewWorkers();
                    case 4 -> upgradeGarage();
                    case 5 -> payBills();
                    case 6 -> checkEmail();
                    default -> {
                    }
                }
            }
            
        }
        

    }

    public void hire(){
        Menu potentialEmployees = new Menu(3);
        ArrayList<Worker> workers = new ArrayList<>();
            for (int i = 1; i < 4; i++) {
                String name = this.generateWorkerName();
                Worker w = new Worker(name, this.owner);
                workers.add(w);
                potentialEmployees.options.put(i, w.toString());  
            }
            int widx = (potentialEmployees.runMenu()-1);
            this.owner.hire(workers.get(widx));
            
        
    }

    public String generateWorkerName(){
        String[] names = {"Marco", "Ross", "Billy", "Chet", "Roberto", "Emily", "Sam", "Lyle", "Steven", "Beth", "Debra", "Arnold", "Wanda", 
        "John","Amy", "Nelson", "Vivian", "Beatrice", "Harvey", "Mason", "Matt", "Mary", "Lucy", "Simon", "Ruthie", "Eric", "Annie"};
        Random randName = new Random();
        return names[randName.nextInt(0, 26)];
    }

    public void fire(){
        System.out.println("Fire who? (enter the number next to their name, or 0 to quit)");
        Menu workers = new Menu(this.owner.employees.size());
        for (int idx = 0; idx < this.owner.employees.size(); idx++) {
            Worker w = this.owner.employees.get(idx);
            workers.options.put((idx+1), w.name);
        }
        int widx = (workers.runMenu()-1);
        this.owner.fire(this.owner.employees.get(widx));

        

    }

    public void viewWorkers(){
        for (int idx = 0; idx < this.owner.employees.size(); idx++) {
            System.out.println(this.owner.employees.get(idx));
        }

    }

    public void upgradeGarage(){
        Menu garage = new Menu(3);
        System.out.println("Your cars: ");
        this.owner.showMyCars();
        System.out.println(this.owner.shop);
        System.out.println("Upgrade what?");
        garage.options.put(1, "Building size");
        garage.options.put(2, "Work bays");
        garage.options.put(3, "Parking spaces");
        int upgradeThis = garage.runMenu();
        boolean success = this.owner.shop.upgrade(this.owner, upgradeThis);
        if (success){
            System.out.println("Upgrade schedueled");
        } else {
            System.out.println("Upgrade canceled");
        }
    }

    public void payBills(){
        ArrayList<Integer> bills = this.owner.checkBillsDue(false);
        System.out.println("What do you want to pay?");
        Menu payBills = new Menu(4);
        payBills.options.put(1, "House");
        payBills.options.put(2, "Shop");
        payBills.options.put(3, "Wages");
        payBills.options.put(4, "Taxes");
        int bill = payBills.runMenu();
        if (bill <=4 && bill != 0){
            this.owner.bankBalance -= bills.get(bill-1);
            this.owner.bills.replace((payBills.options.get(bill)), true);
        } else {
            System.out.println("Canceling payment...");
        }
        
    }

    public void checkEmail(){
        String jan = ("====Sent: January 01\n====From: Your Uncle Roy\n====To: "+ this.owner.name+"\n====Subject: A new year is upon us\n"+
        """
        Hey kiddo! I heard you're taking over the auto shop this year. Nice work, I'm very proud. Don't forget to keep up with your
        bills, okay? You can get into some serious financial trouble if you aren't careful. Have you learned how to cook yet? 
        It's pretty easy, you just stand in your kitchen and type "cook". As long as there's food in the fridge you can whip 
        something right up. I'll check on you again next month.
        -Roy
                """ + "\nP.S: I heard the customers are really into "+ this.owner.trendingColor + " this month.");
        String feb = ("====Sent: Februrary 01\n====From: Your Uncle Roy\n====To: "+ this.owner.name+"\n====Subject: A romantic month\n"+
        """
        Hey kiddo! You made it through your first month, congrats! Since Valentine's day is right around the corner I thought I'd 
        give you some pointers on social interaction, if you haven't figured it all out yet. First of all, anybody who waves to you 
        is friendly, you can "talk" to them. It doesn't really matter what you say, people get in a better mood just from having someone
        who listens. People who like you give you discounts on cars. People who don't, dont't. Simple as that.
        -Roy
                """+ "\nP.S: I heard "+ this.owner.trendingColor + " is all the rage this month.");
        String march = ("====Sent: March 01\n====From: Your Uncle Roy\n====To: "+ this.owner.name+"\n====Subject: The shop\n"+
        """
        Hey kiddo! Have you upgraded the shop yet? It's pretty epensive, but you gotta spend money to make money! At the very least 
        you should make sure you have the maximum number of bays installed. Otherwise you're just wasting space.
        -Roy
                """ + "\nP.S: I heard "+ this.owner.trendingColor + " is really trendy this month.");
        String april = ("====Sent: April 01\n====From: Your Uncle Roy\n====To: "+ this.owner.name+"\n====Subject: April Fools!!\n"+
        """
        Pranks are pretty funny. But you know what's not funny? Driving while "intoxicated". You'll get busted and lose your whole 
        career. Be careful out there.
        -Roy
                """+ "\nP.S: I heard "+ this.owner.trendingColor + " is really big this month.");
        String may = ("====Sent: May 01\n====From: Your Uncle Roy\n====To: "+ this.owner.name+"\n====Subject: Spring is almost over\n"+
        """
        Have you paid your taxes yet? The amount you owe depends on how much money you have, so if you pay while you're broke you
        can save some serious dough. Don't say I never told you nothin.
        -Roy
                """+ "\nP.S: I heard "+ this.owner.trendingColor + " is really popular this month.");
        String june = ("====Sent: June 01\n====From: Your Uncle Roy\n====To: "+ this.owner.name+"\n====Subject: Summer is here\n"+
        """
        Not that the seasons matter in this business. Too bad we're not near a beach, huh?
        -Roy
                """+ "\nP.S: I heard "+ this.owner.trendingColor + " is really big this month.");
        String july = ("====Sent: July 01\n====From: Your Uncle Roy\n====To: "+ this.owner.name+"\n====Subject: Difficult choices...\n"+
        """
        If you're ever forced to skip a bill, it's better to stiff your employees. They'll all quit, but your business won't go under. 
        Not right away, anyway.
        -Roy
                """+ "\nP.S: I heard "+ this.owner.trendingColor + " is really big this month.");
        String aug = ("====Sent: August 01\n====From: Your Uncle Roy\n====To: "+ this.owner.name+"\n====Subject: Still here?\n"+
        """
        Hey kiddo, you still alive and kicking? Nice work. I'm proud of you.
        -Roy
                """+ "\nP.S: I heard "+ this.owner.trendingColor + " is really big this month.");
        String sep = ("====Sent: September 01\n====From: Your Uncle Roy\n====To: "+ this.owner.name+"\n====Subject: Summer's over..\n"+
        """
        Good thing you're not in school anymore, huh? No homework to dread.
        -Roy
                """+ "\nP.S: I heard "+ this.owner.trendingColor + " is really big this month.");
        String oct = ("====Sent: October 01\n====From: Your Uncle Roy\n====To: "+ this.owner.name+"\n====Subject: Goals\n"+
        """
        Hey, you made it 3/4s of the way through the year. That's a pretty big deal. Have you figured out what you wanna do with 
        yourself yet? There's all kinds of goals a person can have. Maybe you want to sell every kind of car there is. Or only sell
        the best quality cars. Or only sell the worst ones, and scam a bunch of people. What do I know? Maybe you even want to become
        a millionare! Anything is possible, kid, the sky's the limit.
        -Roy
                """+ "\nP.S: I heard "+ this.owner.trendingColor + " is really big this month.");
        String nov = ("====Sent: November 01\n====From: Your Uncle Roy\n====To: "+ this.owner.name+"\n====Year's almost over\n"+
        """
        Can you belive it? Feels like you just started this crazy adventure a couple weeks ago and now it's almost done. Sure the
        shop will still be there next year but, there's  nothing like your first year as an entreprenuer. Make the most of it.
        -Roy
                """+ "\nP.S: I heard "+ this.owner.trendingColor + " is really big this month.");
        String dec = ("====Sent: December 01\n====From: Your Uncle Roy\n====To: "+ this.owner.name+"\n====Subject: 29 days to go...\n"+
        """
        This is the last email you'll get from me, kiddo. 
        -Roy
                """+ "\nP.S: I heard "+ this.owner.trendingColor + " is really big this month.");
        String[] emails = {jan, feb, march, april, may, june, july, aug, sep, oct, nov, dec};
        System.out.println(emails[this.owner.day/30]);

        
    }

    

    
}
