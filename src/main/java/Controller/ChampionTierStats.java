package Controller;

public class ChampionTierStats {

    private static int champCost = 0;


    public static int[][] levels =  {
            {100,0,0,0,0},
            {100,0,0,0,0},
            {75,25,0,0,0},
            {55,30,15,0,0},
            {45,33,20,2,0},
            {25,40,30,5,0},
            {19,30,35,15,1},
            {16,20,35,25,4},
            {9,15,30,30,16},
            {5,10,20,40,25},
            {1,2,12,50,35}
    };

    public static void setChampionCost(int championCost) {
        ChampionTierStats.champCost = championCost;
    }
    public static int getChampionCost(){
        return champCost;
    }



    public static int[][] getChampionPercentage(){
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

    public static int[][] getUserLevel1() {
        return levels;
    }
}
