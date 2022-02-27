package Controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class TestWolfram {

    private Controller controller;
    private static final DecimalFormat df = new DecimalFormat();
    private static final String appid = "HP7H85-G52GUJARPY";
    private static final int MAX_THREADS = 10;
    private double unitPercentage;
    private int uniqueUnitAmount,amountOfGoldToRoll, championTier;

    /**
     *
     * Original equation (1 –(1 –(.unitPercentagesArray)/(uniqueUnitAmount))^((5/2)*amountOfGoldToRoll))
     *
     * @param unitPercentagesArray
     * @param uniqueUnitAmount
     * @param amountOfGoldToRoll
     */

    public double calculateWolframNormal(double[][] unitPercentagesArray, int uniqueUnitAmount, int amountOfGoldToRoll, int championTier, int userLevel) { // championTier is int 1-5

        //System.out.println( " % Array: "+ (Arrays.toString(unitPercentagesArray)) +" unique champs:"+ uniqueUnitAmount +" amount of gold: "+ amountOfGoldToRoll +" Championtier: "+ championTier);
        //                                                 LEVEL AND CHAMPIONTIER
        double ChampionPercentage = unitPercentagesArray[userLevel][championTier];
        String expression2 = "(1+%E2%80%93(1+%E2%80%93("+ChampionPercentage+")%2F("+uniqueUnitAmount+"))%5E((5%2F2)*"+amountOfGoldToRoll+"))&plaintext&output=XML&appid="+appid;
        double finalPercentage = 0;
        try{
            URL url = new URL("https://api.wolframalpha.com/v2/query?input="+expression2);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            System.out.println("connected");
            int responeNumber = conn.getResponseCode();
            if(responeNumber != 200){
                throw new RuntimeException("HttpResponeCode: " + responeNumber);
            }else{
                StringBuilder strBuild = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());

                while(sc.hasNext()){
                    strBuild.append(sc.nextLine());
                }
                sc.close();

                //System.out.println(strBuild); //This is to print everything grabbed by the API request
                for(int i = 0; i<strBuild.length(); i++){
                    boolean isFound = strBuild.indexOf("plaintext") != -1;
                    if(isFound){
                        int index = ordinalIndexOf(String.valueOf(strBuild),"plaintext", 3);
                        int lastIndex = index+6;
                        System.out.println(df.format(managePercentageOutput(String.valueOf(strBuild),index,lastIndex)));
                        finalPercentage = ((managePercentageOutput(String.valueOf(strBuild),index,lastIndex)));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalPercentage;
    }

    /**
     * This is the calculator that takes 2 more factors.
     * @param unitPercentagesArray
     * @param uniqueUnitAmount
     * @param amountOfGoldToRoll
     * @param championTier
     * @param userLevel
     * @return
     */
    public double calculateWolframSweat(double[][] unitPercentagesArray, int uniqueUnitAmount, int amountOfGoldToRoll, int championTier, int userLevel) { // championTier is int 1-5

        //System.out.println( " % Array: "+ (Arrays.toString(unitPercentagesArray)) +" unique champs:"+ uniqueUnitAmount +" amount of gold: "+ amountOfGoldToRoll +" Championtier: "+ championTier);
        //                                                 LEVEL AND CHAMPIONTIER
        double ChampionPercentage = unitPercentagesArray[userLevel][championTier];
        String expression2 = "(1+%E2%80%93(1+%E2%80%93("+ChampionPercentage+")%2F("+uniqueUnitAmount+"))%5E((5%2F2)*"+amountOfGoldToRoll+"))&plaintext&output=XML&appid="+appid;
        double finalPercentage = 0;
        try{

            URL url = new URL("https://api.wolframalpha.com/v2/query?input="+expression2);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            System.out.println("connected");

            int responeNumber = conn.getResponseCode();

            if(responeNumber != 200){
                throw new RuntimeException("HttpResponeCode: " + responeNumber);
            }else{
                StringBuilder strBuild = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());

                while(sc.hasNext()){
                    strBuild.append(sc.nextLine());
                }
                sc.close();

                //System.out.println(strBuild); //This is to print everything grabbed by the API request
                for(int i = 0; i<strBuild.length(); i++){
                    boolean isFound = strBuild.indexOf("plaintext") != -1;
                    if(isFound){
                        int index = ordinalIndexOf(String.valueOf(strBuild),"plaintext", 3);
                        int lastIndex = index+6;
                        System.out.println(df.format(managePercentageOutput(String.valueOf(strBuild),index,lastIndex)));
                        finalPercentage = ((managePercentageOutput(String.valueOf(strBuild),index,lastIndex)));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalPercentage;
    }

    /**
     * Getting correct output from API GET request
     * Multiplying return value by 100 to get the correct presentation for a % number.
     * @param originalString
     * @param firstIndex
     * @param lastIndex
     * @return
     */
    public double managePercentageOutput(String originalString, int firstIndex, int lastIndex) {
        String percentageOutput = originalString.substring(firstIndex,lastIndex);
        System.out.println( "PercentageOutput" +Double.parseDouble(percentageOutput));
        return Double.parseDouble(percentageOutput)*100;
    }

    /**
     * To use this method elsewhere, remove the "+10" in the return statement.
     *
     * @param str , The string you want to search through
     * @param substr , The string you want to find in the "str" String.
     * @param repeatedAmountOfTimes , Amount of times the String you are looking for is ignored to find the one you are looking for. For an example:
     *        I am looking for the 3rd output of the String "plaintext" because I know 6-10 characters after that is my mathematical equation answer
     *        from wolfram API.
     * @return integer;
     */
    public int ordinalIndexOf(String str, String substr, int repeatedAmountOfTimes) {
        int position = str.indexOf(substr);
        while (--repeatedAmountOfTimes > 0 && position != -1)
            position = str.indexOf(substr, position + 1);
        return position+10;
    }
}