package Controller;

public class ChampionTierStats {

    private static int championCost = 1;


    public static double[][] levels =  {
            {0,0,0,0,0},
            {1,0,0,0,0},
            {1,0,0,0,0},
            {0.75,0.25,0,0,0},
            {0.55,0.30,0.15,0,0},
            {0.45,0.33,0.20,0.02,0},
            {0.25,0.40,0.30,0.05,0},
            {0.19,0.30,0.35,0.15,0.01},
            {0.16,0.20,0.35,0.25,0.04},
            {0.09,0.15,0.30,0.30,0.16},
            {0.05,0.10,0.20,0.40,0.25},
            {0.01,0.02,0.12,0.50,0.35}
    };

    public static void setChampionCost(int championCost) {
        ChampionTierStats.championCost = championCost;
    }

    public static int getChampionCost(){
        return championCost;
    }

    public static double[][] getChampionPercentage(){
        return levels;
    }




















    public static int[] level1 = {100,0,0,0,0};
    public static int[] level2 = {100,0,0,0,0};
    public static int[] level3 = {75,25,0,0,0};
    public static int[] level4 = {55,30,15,0,0};
    public static int[] level5 = {45,33,20,2,0};
    public static int[] level6 = {25,40,30,5,0};
    public static int[] level7 = {19,30,35,15,1};
    public static int[] level8 = {16,20,35,25,4};
    public static int[] level9 = {9,15,30,30,16};
    public static int[] level10 = {5,10,20,40,25};
    public static int[] level11 = {1,2,12,50,35};

    public static double[][] getUserLevel1() {
        return levels;
    }
}
