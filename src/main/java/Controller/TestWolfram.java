package Controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.awt.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Scanner;

public class TestWolfram {

    private Controller controller;

    private static final DecimalFormat df = new DecimalFormat();
    private static final String appid = "HP7H85-G52GUJARPY";
    private static final int MAX_THREADS = 10;

    public static double lastCalculatedValueNormal;
    public static double lastCalculatedValueSweat;
    public static double lastCalculatedValueTwoUnits;

    /**
     * The logic behind the calculations can you find here: https://en.wikipedia.org/wiki/Binomial_distribution
     * @param unitPercentagesArray
     * @param uniqueUnitAmount
     * @param amountOfGoldToRoll
     * @param championTier
     * @param userLevel
     */
    public void calculateWolframNormal(double[][] unitPercentagesArray, int uniqueUnitAmount, int amountOfGoldToRoll, int championTier, int userLevel) {
        controller = new Controller();
        double ChampionPercentage = unitPercentagesArray[userLevel][championTier];

        String expression1 = "+1-(5%2F2)"+amountOfGoldToRoll+"("+ChampionPercentage+")("+uniqueUnitAmount+")(1+%E2%80%93+("+ChampionPercentage+"%2F"+uniqueUnitAmount+"))%5E((5%2F2)"+amountOfGoldToRoll+"%E2%80%93+1)&appid="+appid;

        try{

            URL url = new URL("https://api.wolframalpha.com/v2/query?input="+expression1);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

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

                System.out.println(strBuild); //This is to print everything grabbed by the API request
                for(int i = 0; i<strBuild.length(); i++){
                    boolean isFound = strBuild.indexOf("plaintext") != -1;
                    if(isFound){

                        int index = ordinalIndexOf(String.valueOf(strBuild),"plaintext", 3);
                        int lastIndex = index+6;
                        lastCalculatedValueNormal = ((managePercentageOutput(String.valueOf(strBuild),index,lastIndex)));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void calculateWolframSweat(double[][] unitPercentagesArray, int uniqueUnitAmount, int amountOfGoldToRoll, int championTier, int userLevel) {

        controller = new Controller();
        double ChampionPercentage = unitPercentagesArray[userLevel][championTier];
        String expression2 = "(1+%E2%80%93(1+%E2%80%93("+ChampionPercentage+")%2F("+uniqueUnitAmount+"))%5E((5%2F2)*"+amountOfGoldToRoll+"))&plaintext&output=XML&appid="+appid;

        try{

            URL url = new URL("https://api.wolframalpha.com/v2/query?input="+expression2);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responeNumber = conn.getResponseCode();

            if(responeNumber != 200){
                throw new RuntimeException("HttpResponeCode: " + responeNumber);
            } else {

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

                        lastCalculatedValueSweat = ((managePercentageOutput(String.valueOf(strBuild),index,lastIndex)));
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will calculate the odds of getting 2 units of the same cost
     * @param unitPercentagesArray
     * @param uniqueUnitAmount
     * @param amountOfGoldToRoll
     * @param championTier
     * @param userLevel
     */
    public void calculateTwoUnits(double[][] unitPercentagesArray, int uniqueUnitAmount, int amountOfGoldToRoll, int championTier, int userLevel) {

        controller = new Controller();
        double ChampionPercentage = unitPercentagesArray[userLevel][championTier];
        String expression1 = "1+-+(1+-+((1%2F10)(.15)))%5E(24)+-+(24)*((1%2F10)(.15))*(1-(1%2F10)(.15))%5E24&format=image,plaintext&output=XML&appid="+appid;
        String expression2 = "1+%E2%80%93+(1+%E2%80%93+("+ChampionPercentage+")%2F("+uniqueUnitAmount+"))%5E((5%2F2)*"+amountOfGoldToRoll+")+%E2%80%93+(5%2F2)*("+amountOfGoldToRoll+")("+ChampionPercentage+")("+uniqueUnitAmount+")(1+%E2%80%93+("+ChampionPercentage+"%2F"+uniqueUnitAmount+"))%5E((5%2F2)"+amountOfGoldToRoll+"+%E2%80%93+1)&format=image,plaintext&output=XML&appid="+appid;
        String lol = "1 – (1 – (1/12)/(numUnits))^((5/2)g) – (5/2)g(pCost)(numUnits)(1 – (pCost/numUnits))^((5/2)g – 1)";
        try{

            URL url = new URL("https://api.wolframalpha.com/v2/query?input="+expression1);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responeNumber = conn.getResponseCode();

            if(responeNumber != 200){
                throw new RuntimeException("HttpResponeCode: " + responeNumber);
            } else {

                StringBuilder strBuild = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());

                while(sc.hasNext()){
                    strBuild.append(sc.nextLine());
                }

                sc.close();

                for(int i = 0; i<strBuild.length(); i++){
                    boolean isFound = strBuild.indexOf("plaintext") != -1;
                    if(isFound){
                        int index = ordinalIndexOf(String.valueOf(strBuild),"plaintext", 3);
                        int lastIndex = index+6;
                        System.out.println(strBuild);
                        System.out.println(lastCalculatedValueTwoUnits);
                        lastCalculatedValueTwoUnits = ((managePercentageOutput(String.valueOf(strBuild),index,lastIndex)));
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void calculateLvlOrStay(double[][] unitPercentagesArray, int uniqueUnitAmount, int amountOfGoldToRoll, int championTier, int userLevel) {

        controller = new Controller();
        double ChampionPercentage = unitPercentagesArray[userLevel][championTier];
        String expression2 = "1+-+(1+-+((1%2F6)(((5/2)"+ChampionPercentage+")))%5E"+ChampionPercentage+"%E2%80%93+((5/2)*"+amountOfGoldToRoll+")((1%2F6)("+ChampionPercentage+"))(1-(1%2F6)("+ChampionPercentage+"))%5E209+-+(1+-+(1+-+((1%2F6)(.15)))%5E70+%E2%80%93+(70)((1%2F6)(.15))(1-(1%2F6)(.15))%5E69)&format=image,plaintext&output=XML&appid="+appid;
        try{

            URL url = new URL("https://api.wolframalpha.com/v2/query?input="+expression2);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responeNumber = conn.getResponseCode();

            if(responeNumber != 200){
                throw new RuntimeException("HttpResponeCode: " + responeNumber);
            } else {

                StringBuilder strBuild = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());

                while(sc.hasNext()){
                    strBuild.append(sc.nextLine());
                }

                sc.close();

                for(int i = 0; i<strBuild.length(); i++){
                    boolean isFound = strBuild.indexOf("plaintext") != -1;
                    if(isFound){
                        int index = ordinalIndexOf(String.valueOf(strBuild),"plaintext", 3);
                        int lastIndex = index+6;
                        lastCalculatedValueSweat = ((managePercentageOutput(String.valueOf(strBuild),index,lastIndex)));
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

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
        return Double.parseDouble(percentageOutput)*100;

    }

    public double getLastCalculatedValueNormal() {
        return lastCalculatedValueNormal;
    }

    public double getLastCalculatedValueSweat() {
        return lastCalculatedValueSweat;
    }
    public double getlastCalculatedValueTwoUnits(){
        return lastCalculatedValueTwoUnits;
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

        while (--repeatedAmountOfTimes > 0 && position != -1) {
            position = str.indexOf(substr, position + 1);
        }
        return position+10;
    }
}
