# TFTProbabilityCalculator

 - Using JavaFX
 - WolframAlphaLibrary
 - WolframAlpha API(ID needed)
 - Maven

I created this app to make my decisionmaking in-game flawless.

 Based on:
 - https://en.wikipedia.org/wiki/Binomial_distribution
 - https://www.reddit.com/r/CompetitiveTFT/comments/fc17a9/everything_you_need_to_know_about_your_odds_while/
 - TFT = Teamfight Tactics

Using an API to calculate mathematical expressions is by no means the most optimal way of making a good application but I made it to learn more about APIs and how they operate.

# About the game. 

TFT is a turnbased autoplayer strategy game based on probability of a pool of 1121(60unique) champions inside.

 - Each round you can place your champions on a board of 28 hexagons(4x7).
 - Each round you get a "shop" of 5 different champions which you can buy for the amount of gold relative to the tier(1-5) of the champion. This can be re-rolled as many times as your gold can manage to get another 5 champions to pick from.
 - Each round you can replace, re-structure
 - Different champions are aquired on different levels with different % chance of finding them in your shop

# What do the application do?

This application calculates odds of finding a specific tier champion depending on your input of:
 - Level
 - Gold
 - Champion-tier 
 - How much gold to next level
 - How many champions of the tier thats gone(grabbed by other players) 
 - And how many champions you want(1 or 2)
 
 # What does the application output?
 
 - The output is displayed in form of a diagram and a "notes" section for notes depending on your input.
 - Percentage value you can base your actions on.
 - Tells you if you are supposed to stay on your level and roll for your specific tier champion.
 - Tells you if you are supposed to level up and roll for your specific tier champion.
 - Gives you 

# Other random features;

 - Champion pool distribution displayed in a diagram.
 - Roll percentage of all champion tiers.


Picture of how the application is looking at the moment (4b918f3)
![bild](https://user-images.githubusercontent.com/81166713/156657769-642da216-41af-486c-9475-7244e6888539.png)


A preview of one of the small features, The champion pool diagram (4b918f3)
![bild](https://user-images.githubusercontent.com/81166713/156657818-e127dbc6-2ccb-4497-bf42-79abdd97750b.png)
