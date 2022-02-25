package Controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestWolfram {
    private Controller controller;
    private static final DecimalFormat df = new DecimalFormat();
    private static final String appid = "XXXXXX";
    private static final int MAX_THREADS = 10;
    private double unitPercentage;
    private int uniqueUnitAmount,amountOfGoldToRoll;

    /**
     *
     * Original equation (1 –(1 –(.unitPercentage)/(uniqueUnitAmount))^((5/2)*amountOfGoldToRoll))
     *
     * @param unitPercentage
     * @param uniqueUnitAmount
     * @param amountOfGoldToRoll
     */
    public void callWolfram(double unitPercentage, int uniqueUnitAmount, int amountOfGoldToRoll) {

        String expression2 = "(1+%E2%80%93(1+%E2%80%93("+unitPercentage+")%2F("+uniqueUnitAmount+"))%5E((5%2F2)*"+amountOfGoldToRoll+"))&plaintext&output=XML&appid="+appid;
        try{

            URL url = new URL("https://api.wolframalpha.com/v2/query?input="+expression2);
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

                //System.out.println(strBuild); //This is to print everything grabbed by the API request
                for(int i = 0; i<strBuild.length(); i++){
                    boolean isFound = strBuild.indexOf("plaintext") != -1;
                    if(isFound){
                        int index = ordinalIndexOf(String.valueOf(strBuild),"plaintext", 3);
                        int lastIndex = index+6;
                        System.out.println(df.format(managePercentageOutput(String.valueOf(strBuild),index,lastIndex)));
                        break;
                    }
                }
            }
        } catch (Exception e) {
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
    public void setController(Controller controller) {
        this.controller = controller;
    }

}