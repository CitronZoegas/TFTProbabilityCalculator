package Controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;

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
    private TextField numberOfCopies;
    @FXML
    private Spinner<Integer> levelSpinner;
    final int initialValue = 1;
    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,11,initialValue);
    @FXML
    private BarChart<?,?> barChart;
    @FXML
    public static XYChart.Series series = new XYChart.Series();
    public static XYChart.Series series1 = new XYChart.Series();
    public static XYChart.Series series2 = new XYChart.Series();

    private TestWolfram testWolfram;
    private Thread thread;

    @FXML
    public void getChampionTier1(ActionEvent event){
        ChampionTierStats.setChampionCost(0);
        System.out.println(ChampionTierStats.getChampionCost());
        champTier2.setSelected(false);
        champTier3.setSelected(false);
        champTier4.setSelected(false);
        champTier5.setSelected(false);
    }

    @FXML
    public void getChampionTier2(ActionEvent event){
        ChampionTierStats.setChampionCost(1);
        System.out.println(ChampionTierStats.getChampionCost());
        champTier1.setSelected(false);
        champTier3.setSelected(false);
        champTier4.setSelected(false);
        champTier5.setSelected(false);
    }

    @FXML
    public void getChampionTier3(ActionEvent event){
        ChampionTierStats.setChampionCost(2);
        System.out.println(ChampionTierStats.getChampionCost());
        champTier1.setSelected(false);
        champTier2.setSelected(false);
        champTier4.setSelected(false);
        champTier5.setSelected(false);
    }

    @FXML
    public void getChampionTier4(ActionEvent event){
        ChampionTierStats.setChampionCost(3);
        System.out.println(ChampionTierStats.getChampionCost());
        champTier1.setSelected(false);
        champTier2.setSelected(false);
        champTier3.setSelected(false);
        champTier5.setSelected(false);
    }

    @FXML
    public void getChampionTier5(ActionEvent event){
        ChampionTierStats.setChampionCost(4);
        System.out.println(ChampionTierStats.getChampionCost());
        champTier1.setSelected(false);
        champTier2.setSelected(false);
        champTier3.setSelected(false);
        champTier4.setSelected(false);
    }

    /*public int getChampionTierLevel(int level) {

        switch(level) {
            case 1:
                if(champTier1.isSelected()){
                    level = Integer.parseInt(champTier1.getText());
                    break;
                }
                level++;

            case 2:
                if(champTier2.isSelected()){
                    level = Integer.parseInt(champTier2.getText());
                    break;
                }
                level++;
            case 3:
                if(champTier3.isSelected()){
                    level = Integer.parseInt(champTier3.getText());
                    break;
                }
                level++;
            case 4:
                if(champTier4.isSelected()){
                    level = Integer.parseInt(champTier4.getText());
                    break;
                }
                level++;
            case 5:
                if(champTier5.isSelected()){
                    level = Integer.parseInt(champTier5.getText());
                    break;
                }
                level++;
        }
        return level;
    }*/

    private int getNumberOfCopiesLeft(){
        if(validateIntegerInput(numberOfCopies.getText())){
            return Integer.parseInt(numberOfCopies.getText());
        }
        return 0;
    }

    private int getHowMuchGold() {
        return Integer.parseInt(amountOfGold.getText());
    }

    /**
     * HERE ARE THE DIFFERENT TYPES OF CALCULATIONS YOU CAN DO
     */
    public double calculateNormal() {
        int userLevel = svf.getValue();
        double[][] percentagesOfHitting = (ChampionTierStats.getChampionPercentage());
        double percentage = ChampionTierStats.getChampionPercentage()[userLevel][ChampionTierStats.getChampionCost()];
        TestWolfram twf = new TestWolfram();
        return twf.calculateWolframNormal((percentagesOfHitting),12,getHowMuchGold(),ChampionTierStats.getChampionCost(),userLevel);
    }

    /**
     * This one have 2 more parameters
     * @return
     */
    public double calculateSweat() {
        int userLevel = svf.getValue();
        double[][] percentagesOfHitting = (ChampionTierStats.getChampionPercentage());
        double percentage = ChampionTierStats.getChampionPercentage()[userLevel][ChampionTierStats.getChampionCost()];
        TestWolfram twf = new TestWolfram();
        return twf.calculateWolframSweat((percentagesOfHitting),12,getHowMuchGold(),ChampionTierStats.getChampionCost(),userLevel);
    }

    /**
     * Validates the input of being an Integer
     * @param input
     * @return boolean
     */
    public boolean validateIntegerInput(String input) {
        try{
            int x = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("That is not a valid number.");
        }
        return false;
    }

    public void updateCharts() {
        series.getData().clear();
        series1.getData().clear();
        series2.getData().clear();
        series.getData().add(new XYChart.Data("1",calculateNormal()));
        series1.getData().add(new XYChart.Data("2",calculateNormal()));
        series2.getData().add(new XYChart.Data("3",calculateNormal()));
        barChart.getData().addAll(series);
        barChart.getData().addAll(series1);
        barChart.getData().addAll(series2);
    }
    @FXML
    public void createLoadingCircles() {
        Scene scene = Main.getScene();
        Circle innerCircle = new Circle();
        Circle middleCircle = new Circle();
        Circle outerCircle = new Circle();

        innerCircle.centerXProperty().bind(scene.widthProperty().divide(2));
        innerCircle.centerYProperty().bind(scene.heightProperty().divide(2));
        innerCircle.radiusProperty().bind(Bindings.min(scene.widthProperty(),scene.heightProperty()));
        middleCircle.centerXProperty().bind(scene.widthProperty().divide(2));
        middleCircle.centerYProperty().bind(scene.heightProperty().divide(2));
        middleCircle.radiusProperty().bind(Bindings.min(scene.widthProperty(),scene.heightProperty()));
        outerCircle.centerXProperty().bind(scene.widthProperty().divide(2));
        outerCircle.centerYProperty().bind(scene.heightProperty().divide(2));
        outerCircle.radiusProperty().bind(Bindings.min(scene.widthProperty(),scene.heightProperty()));


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
        series.setName("1 example");
        series1.setName("2 example");
        series2.setName("3 example");
        series.getData().add(new XYChart.Data("X Cost",90));
        series1.getData().add(new XYChart.Data("Y Cost",40));
        series2.getData().add(new XYChart.Data("Z Cost",9));
        barChart.getData().addAll(series);
        barChart.getData().addAll(series1);
        barChart.getData().addAll(series2);
    }
}
