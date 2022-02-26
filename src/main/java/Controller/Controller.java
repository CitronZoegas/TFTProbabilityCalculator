package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller extends Thread implements Initializable {
    @FXML
    private TextField amountOfGold;
    @FXML
    private RadioButton champTier1;
    @FXML
    private RadioButton champTier2;
    @FXML
    private RadioButton champTier3;
    @FXML
    private RadioButton champTier4;
    @FXML
    private RadioButton champTier5;
    @FXML
    TextField numberOfCopies;
    @FXML
    private Spinner<Integer> levelSpinner;
    final int initialValue = 1;
    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,11,initialValue);
    @FXML
    private BarChart<?,?> barChart;
    private TestWolfram testWolfram;
    private Thread thread;
    private int one = 1;
    private int two = 2;
    private int three = 3;
    private int four = 4;
    private int five = 5;

    public Controller() {

    }

    public void getChampionTier(ActionEvent event) {
        Button button = (Button) event.getSource();

    }

    public boolean validateIntegerInput(String input) {
        try{
            int x = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("That is not valid");
        }
        return false;
    }

    @FXML
    public void getChampionTier1(ActionEvent event){
        ChampionTierStats.setChampionCost(0);
        champTier2.setSelected(false);
        champTier3.setSelected(false);
        champTier4.setSelected(false);
        champTier5.setSelected(false);
    }

    @FXML
    public void getChampionTier2(ActionEvent event){
        ChampionTierStats.setChampionCost(1);
        champTier1.setSelected(false);
        champTier3.setSelected(false);
        champTier4.setSelected(false);
        champTier5.setSelected(false);
    }

    @FXML
    public void getChampionTier3(ActionEvent event){
        ChampionTierStats.setChampionCost(2);
        champTier1.setSelected(false);
        champTier2.setSelected(false);
        champTier4.setSelected(false);
        champTier5.setSelected(false);
    }

    @FXML
    public void getChampionTier4(ActionEvent event){
        ChampionTierStats.setChampionCost(3);
        champTier1.setSelected(false);
        champTier2.setSelected(false);
        champTier3.setSelected(false);
        champTier5.setSelected(false);
    }

    @FXML
    public void getChampionTier5(ActionEvent event){
        ChampionTierStats.setChampionCost(4);
        champTier1.setSelected(false);
        champTier2.setSelected(false);
        champTier3.setSelected(false);
        champTier4.setSelected(false);
    }

    public void updateCharts() {

    }

    private int getNumberOfCopiesLeft(){
        return 12;
    }

    public int getNumberOfXChampionsGone() {
        //return Integer.parseInt(numberOfCopies.getText());
        return 0;
    }

    private int getHowMuchGold() {
        return Integer.parseInt(amountOfGold.getText());
    }


    /**
     * HERE ARE THE DIFFERENT TYPES OF CALCULATIONS YOU CAN DO
     */
    public void calculateNormal() {

        int[] userLevel = (ChampionTierStats.getChampionPercentage()[Integer.parseInt((champTier1.getText()))]);
         // 0 = 1;
        TestWolfram twf = new TestWolfram();

        twf.callWolfram((userLevel),getNumberOfCopiesLeft(),getHowMuchGold(),ChampionTierStats.getChampionCost());
    }

    public void calculateSweat() {

    }

    @Override
    public void run() {
        try{
            //testWolfram.callWolfram(1,0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        levelSpinner.setValueFactory(svf);
        testWolfram = new TestWolfram();
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        series.setName("1 Champ");
        series1.setName("2 Champ");
        series2.setName("3 Champ");
        series.getData().add(new XYChart.Data("X Cost",90));
        series1.getData().add(new XYChart.Data("Y Cost",40));
        series2.getData().add(new XYChart.Data("Z Cost",9));
        barChart.getData().addAll(series);
        barChart.getData().addAll(series1);
        barChart.getData().addAll(series2);
    }
}
