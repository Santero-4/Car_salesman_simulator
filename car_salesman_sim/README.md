# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`

## Design justification
Why was this not included as a section anywhere and only vaugly mentioned in the rubric? Until just now I though it was covered by the additional questions. You're going to trip so many people up with this. Anyway:
Now that everything's been said and done I think my life would have been a lot easier if I just made this the exact same game but less complicated. Like honestly 70% of the methods I wrote have nothing to do with cars and are just me trying to make the game fun. But the game still turned out kind of boring (in the opinion of everyone except me and also me a little bit I think graphics would have really helped). My only consolation is that dwarf fortress is an even more unintuitive mess than this thing is and it has a HUGE playerbase (relative to how annoying it is to play) so somewhere out there there must be people who are into this kind of thing. 
I think specifically an alternative idea would have been to make this a game with an actual win condition rather than an "objective: survive" situation. Like sure it ends after a year but I don't think anyone will play that far in because other than the emails you can experience all the in-game content in two months maximum and honestly probably a lot less than that. So it would have been cool to have a win condition the player can trigger that would allow for a shorter playtime. However, in my defense, I really wanted to make a realistic (as realistic a text based game can be, anyway) life simulator and I think I accomplished that. It's not like you can "win" life, all you can do is keep going and not die, and my game mirrors that, and I think that's worth it. Sure it's kind of boring, but life is also kind of boring. 

  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
 I started by writing a list of locations I wanted to have, then a list of actions that would make sense for those locations, then some different endings so I'd have something to work twords. After that I opened up vs code and started making classes, mostly with empty methods. Then I started filling in the methods for the player class and as I was doing them I was like "oh i'll need such-and-such class for this to work" so I would make that class too. I tried to stay concious of using superclasses whenever possible to reduce my workload since I don't want to work with a team. 
 And then when I was like halfway done with filling out the classes I started testing and found a boatload of stupid bugs such as the "can't get out of bed" bug and the "can't leave the house" bug and the "can't get in the car" bug and the "every day is day 1" bug. Honestly I could list bugs forever I had so many. I literally made a text file just to keep track of all the bugs I found while playing. So now I'm just fixing those and eventually I'll go back to filling in the remaining classes.
 Once I got all the classes filled in I was just testing testing testing. I got some people to try the game before demo day which was good because I hadn't really realize how few instructions I put until I watched literally everyone get lost.
 THEN I remebered I have to comment my code so I went back and did all the comments

 - What **new thing(s)** did you learn / figure out in completing this project?
 How to actually plan my code. This is the first time I've had a project big enough that actually writing out what I would need ahead of time felt worthwhile. In 110 Pablo was always like "PLAN BEFORE YOU CODE" but that always felt like busywork, why plan to make something that only takes like ~300 lines tops. It's faster to just write somthing and test it. But java is kind of a pain to test so it was worth it to make my little spreadsheet. 

 Also I'm basically a hashtable expert now, love love love hashtables.
 - Is there anything that you wish you had **implemented differently**?

 I was thinking about making a `world` class to store all the people and cars, and make the locations into actual classes rather than strings. but in the end decided that would be feature creep and too time consuming. Better to keep it simple.
Then I started thinking about making a location class that stores people and cars and stuff but, again, feature creep.
Also, letting the computer use a text interface instead of a number one. But honestly handling text input is annoying and a simple menu that presents all the available choices to the user is a lot easier for me to code and for them to play.  
Also, actual dialogue instead of a menu. But again that would have been a pain to implement with having to give each person actual, different dialouge options. This game is about fixing cars and selling them so the majority of my effort needs to be invested into that part.
Also I ended up using so many scanners that in hindsight it would've been easier to just give each a scanner as an attribute instead of making new ones every time.

Looking back on it now it would've been a lot more efficient to have one "locate item" function in the main that took a parameter for where it should look for the item, but at this point I don't want to change anything becuase I don't have time to mess with things that work and have to debug them again.

I have some obsolete methods/methods that could be consolidated and are left over from times when I was implementing things in different ways. If I had time I would remove them but I don't want to accidentally take out something load-bearing.

I have several functions that are designed to fail silently and I'm wishing I had set them to return default values that are actually useful instead of an instance of the expected class with the name "null". Even just returning an actual null would be more productive!



Update: I ended up making a Place class after all becuase it solves the problem of the program only recognizing hyperspecific location names and the problem of the player picking up objects that shouldn't exist.

 - If you had **unlimited time**, what additional features would you implement?

a way to prevent the player from just taking whatever items they wand regradless of wether the item would logically be in that location. Right now you could be at home and say `take milk` and it would just put milk into your inventory, even if you had run out. This is basically an infinite grocery hack that lets you never spend moeny on shopping and all I can do is hope players don't reaize it exists. (ended up implementing this)
Also the world and/or location classes (implenented this too)
Also graphics
Also good dialouge
The paint method. I coded it in the car class but then forgot about it so it can't actually be used right now.
And the ability for workers to train each other. I meant to add it but just never got around to it
Basically everything that I listed in the above section. 
Also, a way to update the car prices before the time of sale without getting into a decreasing value feedback loop. It's more mathematically complicated than it sounds, trust me.
The ability to quit the game WITHOUT having to throw an exception from the command line

 
 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
I was struggling with implementing this one easter egg involving the bus, and I was talking to Hajer about it and she was like "You don't have to add that." and that was like game-changing to me. 

 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
think about organization before implementation. do some debugging before you're hundereds of lines of code in. Just becuase vscode didn't highlight anything doesn't mean you don't have bugs. 
KEEP IT SIMPLE, STUPID.
What is intuitive to you is not intuitive to the players
The amount of planning you thought you did was not actually enough. When you think you're done planning, go plan again.
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.
