This file will contain documentation for all commands available in your game.

Note:  It's a good idea to also make this list available inside the game, in response to a `HELP` command.

OTHER THAN THE KEYWORDS (in quotes) AND NAMES OF ITEMS/PLACES, NOTHING YOU ENTER INTO THE COMMAND LINE MATTERS
input reader is not case sensitive

Supports "go" and "walk" for moving within a location. Use "drive" to move between locations (eg: home to work). You can go to any location if you get in your car first
Locations you can go to
Home
    -Living room (default when you type 'go home')
    -bedroom
    -bed (you start here)
    -hallway
    -kitchen
    -bathroom
Outside (need car to access these)
    - Suburbs
    - Trailer park
    - diner
    - grocery store
    - auto parts store
Work
    - office ('go work' defaults to here)
    - parking lot
    - repair shop

Menus:
In situations where reading input would be finnicky to the point of defeating the purpose of having a typed command system, a menu will be triggered instead. You will know you are in a menu when a numbered list appears on the screen. Simply enter the number next to the thing you want. 0 is always 'go back' / 'quit'. Menus can have layers, in which case you may need to press 0 multiple times in order to exit.

You can "eat" and "drink" certain items, beware of drinking toxic chemicals. The items must be in your inventory first. Use 'take' to pick them up.

You can "use" any item, depending on what it is the approprate function triggers automatically (eg: use computer opens the computer menu, use milk calls .drink(milk))
You can "sleep" in your office, car,  and bed. In your office and car will advance time by 8 hours, in your bed will send you to 8am the next day. Sleeping clears the 'intoxicated' status effect but gives 'hungry'.
You can "get" in/out/on/off vehicles
use 'chat' or 'talk' to talk to any NPC that waves at you. It triggers three turns of conversation where what you type does not matter, then you will have the option to drink with them. Talking to an NPC raises their mood, and drinking with them rasies it a lot, so it is strategic to do this to get good deals on cars.
You can "buy"  items in stores. If you take an item from a loction that has an NPC they have a 50% chance to catch you stealing.
If you are in a location with an NPC that waves at you, and NOT ACTIVELY IN CONVERSATION WITH THEM, you can use 'buy car' to get them to show you the cars they have for sale. If you select a car but then don't buy it you'll put them in a bad mood and get worse deals next time.
In the parking lot, you can use 'sell' to sell your cars. Note that the sale price may be higher or lower than the actual value of the car.
In the repair shop, using 'talk' dos not trigger a conversation, it opens the employee menu, which will allow you to train your workers and assign them tasks. Workers you have hired will be in the shop from 7am to 6pm (18) but if you assign them to do something that takes 4 hours at 5pm (for example), they will stay overtime.
At home, in the kitchen, you can use 'cook' to prepare meals for yourself. You must have at least 4 items in the fridge for this to work.
The input reader is a bit delicate, so if you want to go to a location or take an item, make sure the keyword is first and the item/location name is last in the command
In the bathroom, use 'shower' to clear the unwashed status effect
Use 'drop' to drop items. They will remain in the location where you dropped them





# SPOILER ALERT

If your game includes challenges that must be overcome to win, also list them below.

Rather than being a game you can win, this is a game where you have to survive without losing. If your bills are due and you can't pay them, you will lose. If you drive while intoxicated, you will also lose.

Sample test series of commands:
"go to car" > "drive to grocery store" > Buy some items > "drive home" > "Go to kitchen" > "cook" > "eat breakfast" >
"go to car" > "drive to trailer park" > "talk to dave" > "buy car" > "go to car" (if you agreed to drink with dave sleep in your car before going to work) > "drive to work" > "use computer" > hire some employees and turn off the computer > "Go to repair shop" >
if your employees are still here, "talk" and then "assign" them to fix the car you bought. otherwise, "drive home" "go to bed" "sleep" 
and "drive to work", then "go to repair shop" the next day. once the car is fixed "go to parking lot. Optionally "talk" to the customer or just "sell". The sale price will be wildly different from the price displayed when you choose the car, just roll with it. Type "yes" to complete the sale and conragtulations, you have experienced the life of a used car salesman. There's more in the game but I know you have a lot of these to grade. Just use ^X^C to quit.

