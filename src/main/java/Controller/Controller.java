package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller extends Thread implements Initializable {

    @FXML
    private Button champTier1;
    @FXML
    private Button champTier2;
    @FXML
    private Button champTier3;
    @FXML
    private Button champTier4;
    @FXML
    private Button champTier5;
    @FXML
    TextField numberOfCopies;
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
        if(champTier1.getText().isEmpty() || validateIntegerInput(champTier1.getText())){
            System.out.println("Set a value dummy");
        }else{
            TestWolfram twf = new TestWolfram();
            twf.callWolfram(ChampionTierStats.getChampionPercentage(),0,0);
        }
    }
    @FXML
    public void getChampionTier2(ActionEvent event){
        if(champTier2.getText().isEmpty() || validateIntegerInput(champTier2.getText())){
            System.out.println("Set a value dummy");
        }else{
            TestWolfram twf = new TestWolfram();
            twf.callWolfram(ChampionTierStats.getChampionPercentage(),0,0);
        }
    }

    @FXML
    public void getChampionTier3(ActionEvent event){
        if(champTier3.getText().isEmpty() || validateIntegerInput(champTier3.getText())){
            System.out.println("Set a value dummy");
        }else{
            TestWolfram twf = new TestWolfram();
            twf.callWolfram(ChampionTierStats.getChampionPercentage(),0,0);
        }
    }

    @FXML
    public void getChampionTier4(ActionEvent event){
        if(champTier4.getText().isEmpty() || validateIntegerInput(champTier4.getText())){
            System.out.println("Set a value dummy");
        }else{
            TestWolfram twf = new TestWolfram();
            twf.callWolfram(ChampionTierStats.getChampionPercentage(),0,0);
        }
    }

    @FXML
    public void getChampionTier5(ActionEvent event){
        if(champTier5.getText().isEmpty() || validateIntegerInput(champTier5.getText())){
            System.out.println("Set a value dummy");
        }else{
            TestWolfram twf = new TestWolfram();
            twf.callWolfram(ChampionTierStats.getChampionPercentage(),0,0);
        }
    }

    public void updateCharts() {

    }

    private void getNumberOfCopiesLeft(){

    }

    private void getNumberOfXChampionsGone() {

    }

    private void getHowMuchGold() {

    }

    @Override
    public void run() {
        try{
            testWolfram.callWolfram(1,0,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setWolfram(TestWolfram testWolfram){
        this.testWolfram = testWolfram;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        testWolfram = new TestWolfram();
        XYChart.Series series = new XYChart.Series();
        series.setName("1 Champ");
        series.getData().add(new XYChart.Data("X Cost",90));
        series.getData().add(new XYChart.Data("Y Cost",40));
        series.getData().add(new XYChart.Data("Z Cost",9));
        barChart.getData().addAll(series);

    }
}
