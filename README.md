# TFTProbabilityCalculator

 - Using JavaFX
 - WolframAlphaLibrary
 - Maven

 Calculating probability to make my gaming flawless.
 Based on https://en.wikipedia.org/wiki/Binomial_distribution ,
 - https://www.reddit.com/r/CompetitiveTFT/comments/fc17a9/everything_you_need_to_know_about_your_odds_while/
 TFT = Teamfight Tactics

# TFT is a turnbased autoplayer strategy game based on probability of a pool of 1121(60unique) champions inside. 
 - Each round you can place your champions on a board of 28 hexagons(4x7).
 - Each round you get a "shop" of 5 different champions which you can buy for the amount of gold relative to the tier(1-5) of the champion. This can be re-rolled as many times as your gold can manage to get another 5 champions to pick from.
 - Each round you can replace, re-structure
 - Different champions are aquired on different levels with different % chance of finding them in your shop

# This application calculates odds of finding a specific tier champion depending on your input of:
 - level, 
 - gold, 
 - champion-tier, 
 - how much gold to next level,
 - how many champions of the tier thats gone(grabbed by other players) 
 - and how many champions you want(1 or 2). 
 - and tells you if you are supposed to stay on your level and roll

# Other random features;
 - Champion pool distribution displayed in a diagram.
 - Roll percentage of all champion tiers.
 - 


Picture of how the application is looking at the moment (44dd1d1)
![bild](https://user-images.githubusercontent.com/81166713/156577474-8f72036c-2e2e-49da-9876-20a4ccc6763a.png)


A preview of one of the small features, The champion pool diagram (44dd1d1)
![bild](https://user-images.githubusercontent.com/81166713/156577728-dc658ab7-5022-405b-bd11-c462e9b77786.png)
