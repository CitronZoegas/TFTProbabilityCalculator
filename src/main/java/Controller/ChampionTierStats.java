package Controller;

public class ChampionTierStats {

    private static int userLevel = 1;

    public static void setChampionPercentage(int userLevel) {
        ChampionTierStats.userLevel = userLevel;
    }
    public static int getChampionPercentage(){
        return userLevel;
    }
    public static int[][] levels = {level1 = new int[]{100, 0, 0, 0, 0}};



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

    public static int getUserLevel1() {
        return level1[userLevel];
    }
}
