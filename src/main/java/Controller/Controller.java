package Controller;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller extends Thread implements Initializable {
    //final CategoryAxis xAxis = new CategoryAxis();
    //final NumberAxis yAxis = new NumberAxis();
    @FXML
    private AnchorPane groundLayer;
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
    private Circle firstCircle;
    @FXML
    private Circle secondCircle;
    @FXML
    private Circle thirdCircle;
    @FXML
    private Circle fourthCircle;

    @FXML
    private Spinner<Integer> levelSpinner;
    final int initialValue = 1;
    SpinnerValueFactory<Integer> spinnerValueForLevels = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,11,initialValue);
    @FXML
    private BarChart<String,Number> barChart;
    @FXML
    private NumberAxis leftLabelOfBarChart;
    @FXML
    public static   XYChart.Series  series1 = new XYChart.Series<>(),
                                    series2 = new XYChart.Series<>(),
                                    series3 = new XYChart.Series<>(),
                                    series4 = new XYChart.Series<>(),
                                    series5 = new XYChart.Series<>();

    @FXML
    public static XYChart.Series    championTierDiagram1 = new XYChart.Series<>(),
                                    championTierDiagram2 = new XYChart.Series<>(),
                                    championTierDiagram3 = new XYChart.Series<>(),
                                    championTierDiagram4 = new XYChart.Series<>(),
                                    championTierDiagram5 = new XYChart.Series<>();

    private TestWolfram testWolfram;


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

    private int getNumberOfCopiesLeft(){
        if(validateIntegerInputString(numberOfCopies.getText())){
            return Integer.parseInt(numberOfCopies.getText());
        }
        return 0;
    }

    /**
     * HERE ARE THE DIFFERENT TYPES OF CALCULATIONS YOU CAN DO
     */
    @FXML
    public void calculateNormal() {
        setBarChartLabelx1();
        try{
            createLoadingCircles();
            int userLevel = spinnerValueForLevels.getValue();
            double[][] percentagesOfHitting = (ChampionTierStats.getChampionPercentage());
            int uniqueUnitAmount = Integer.parseInt(numberOfCopies.getText());

            TestWolfram twf = new TestWolfram();
            Thread calculateThread = new Thread(() -> {

                twf.calculateWolframNormal((percentagesOfHitting),uniqueUnitAmount,getHowMuchGold(),ChampionTierStats.getChampionCost(),userLevel);

                loadingScreenRotation(firstCircle,true,360,6);
                loadingScreenRotation(secondCircle,true,180,1);
                loadingScreenRotation(thirdCircle,true,140,1);
                loadingScreenRotation(fourthCircle,true,70,1);

            });
            calculateThread.start();
            clearCharts();
        } catch (Exception e) {
            System.out.println("You have not calculated anything yet.");
            e.printStackTrace();
        }

    }

    /**
     * This one have 2 more parameters
     * @return
     */
    public void calculateSweat() {
        setBarChartLabelx1();
        try{
            createLoadingCircles();
            int userLevel = spinnerValueForLevels.getValue();
            double[][] percentagesOfHitting = (ChampionTierStats.getChampionPercentage());
            int uniqueUnitAmount = Integer.parseInt(numberOfCopies.getText());

            TestWolfram twf = new TestWolfram();
            //Platform.runLater(() -> twf.calculateWolframSweat((percentagesOfHitting),uniqueUnitAmount,getHowMuchGold(),ChampionTierStats.getChampionCost(),userLevel));
            Thread calculateThread = new Thread(() -> {

                twf.calculateWolframSweat((percentagesOfHitting),uniqueUnitAmount,getHowMuchGold(),ChampionTierStats.getChampionCost(),userLevel);

                loadingScreenRotation(firstCircle,true,360,6);
                loadingScreenRotation(secondCircle,true,180,1);
                loadingScreenRotation(thirdCircle,true,140,1);
                loadingScreenRotation(fourthCircle,true,70,1);
            });
            calculateThread.start();
            clearCharts();
        } catch (Exception e) {
            System.out.println("You have not calculated anything 'Sweat' related.");
            e.printStackTrace();
        }
    }

    @FXML
    public void calculateNormalTimesTwo() {
        setBarChartLabelx2();
        try{
            createLoadingCircles();
            int userLevel = spinnerValueForLevels.getValue();
            double[][] percentagesOfHitting = (ChampionTierStats.getChampionPercentage());
            int uniqueUnitAmount = Integer.parseInt(numberOfCopies.getText());

            TestWolfram twf = new TestWolfram();
            Thread calculateThread = new Thread(() -> {
                twf.calculateTwoUnits((percentagesOfHitting),uniqueUnitAmount,getHowMuchGold(),ChampionTierStats.getChampionCost(),userLevel);

                loadingScreenRotation(firstCircle,true,360,6);
                loadingScreenRotation(secondCircle,true,180,1);
                loadingScreenRotation(thirdCircle,true,140,1);
                loadingScreenRotation(fourthCircle,true,70,1);

            });
            calculateThread.start();
            clearCharts();
        } catch (Exception e) {
            System.out.println("You have not calculated anything yet.");
            e.printStackTrace();
        }

    }

    private int getHowMuchGold() {
        return Integer.parseInt(amountOfGold.getText());
    }
    /**
     * Validates the input of being an Integer
     * @param input
     * @return boolean
     */
    public boolean validateIntegerInputString(String input) {
        try{
            int x = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("That is not a valid number.");
        }
        return false;
    }

    public boolean validateDoubleInput(double input) {

        try{
            double x = 0.0;
            if(input == x)
            return true;

        } catch (NumberFormatException e) {
            System.out.println("That is not a valid number.");
        }
        return false;
    }
    public void setBarChartLabelx1() {

        if(champTier1.isSelected()) leftLabelOfBarChart.setLabel("% Chance to get 1, "+champTier1.getText()+" Tier Champion");
        if(champTier2.isSelected()) leftLabelOfBarChart.setLabel("% Chance to get 1, "+champTier2.getText()+" Tier Champion");
        if(champTier3.isSelected()) leftLabelOfBarChart.setLabel("% Chance to get 1, "+champTier3.getText()+" Tier Champion");
        if(champTier4.isSelected()) leftLabelOfBarChart.setLabel("% Chance to get 1, "+champTier4.getText()+" Tier Champion");
        if(champTier5.isSelected()) leftLabelOfBarChart.setLabel("% Chance to get 1, "+champTier5.getText()+" Tier Champion");
    }

    public void setBarChartLabelx2() {

        if(champTier1.isSelected()) leftLabelOfBarChart.setLabel("% Chance to get 2, "+champTier1.getText()+" Tier Champion");
        if(champTier2.isSelected()) leftLabelOfBarChart.setLabel("% Chance to get 2, "+champTier2.getText()+" Tier Champion");
        if(champTier3.isSelected()) leftLabelOfBarChart.setLabel("% Chance to get 2, "+champTier3.getText()+" Tier Champion");
        if(champTier4.isSelected()) leftLabelOfBarChart.setLabel("% Chance to get 2, "+champTier4.getText()+" Tier Champion");
        if(champTier5.isSelected()) leftLabelOfBarChart.setLabel("% Chance to get 2, "+champTier5.getText()+" Tier Champion");
    }

    /**
     * Clear the example diagrams that the UI gets initialized with or potential residue from already active diagrams.
     */
    public void clearCharts() {

        series1.getData().clear();
        series2.getData().clear();
        series3.getData().clear();
    }

    public void updateNormalCharts() {

        series1.getData().clear();
        series2.getData().clear();
        series3.getData().clear();
        series4.getData().clear();
        series5.getData().clear();

        championTierDiagram1.getData().clear();
        championTierDiagram2.getData().clear();
        championTierDiagram3.getData().clear();
        championTierDiagram4.getData().clear();
        championTierDiagram5.getData().clear();

        testWolfram = new TestWolfram();

        if(testWolfram.getLastCalculatedValueNormal() > 0 || validateDoubleInput(testWolfram.getLastCalculatedValueNormal())){
            series1.getData().add(new XYChart.Data("1 Champion",testWolfram.getLastCalculatedValueNormal()));
        }

        if (testWolfram.getLastCalculatedValueSweat() > 0 || validateDoubleInput(testWolfram.getLastCalculatedValueSweat())){
            series2.getData().add(new XYChart.Data("1 Champion",testWolfram.getLastCalculatedValueSweat()));
        }
        if(testWolfram.getlastCalculatedValueTwoUnits() > 0 || validateDoubleInput(testWolfram.getlastCalculatedValueTwoUnits())){
            series3.getData().add(new XYChart.Data("2 Champions",testWolfram.getlastCalculatedValueTwoUnits()));
        }


    }
    public void updateSweatCharts() {

        testWolfram = new TestWolfram();
        series2.getData().add(new XYChart.Data("1Champion", testWolfram.getLastCalculatedValueSweat()));

    }

    @FXML
    public void createLoadingCircles() {

        //possibly useful later on

        /*Scene scene = Main.getScene();
        Stage stage = (Stage) levelSpinner.getScene().getWindow();

        Circle innerCircle = new Circle();
        Circle middleCircle = new Circle();
        Circle outerCircle = new Circle();

        innerCircle.setStrokeDashOffset(50);
        innerCircle.fillProperty();
        innerCircle.setStroke(Color.ALICEBLUE);
        innerCircle.setRadius(80);
        innerCircle.setStrokeWidth(6);
        innerCircle.centerXProperty().bind(scene.widthProperty().divide(2));
        innerCircle.centerYProperty().bind(scene.heightProperty().divide(2));
        innerCircle.radiusProperty().bind(Bindings.min(scene.widthProperty(),scene.heightProperty()));

        middleCircle.setStrokeDashOffset(50);
        middleCircle.fillProperty();
        middleCircle.setStroke(Color.LIGHTGREEN);
        middleCircle.setRadius(50);
        innerCircle.setStrokeWidth(5);
        middleCircle.centerXProperty().bind(scene.widthProperty().divide(2));
        middleCircle.centerYProperty().bind(scene.heightProperty().divide(2));
        middleCircle.radiusProperty().bind(Bindings.min(scene.widthProperty(),scene.heightProperty()));

        outerCircle.setStrokeDashOffset(50);
        outerCircle.fillProperty();
        outerCircle.setStroke(Color.MEDIUMPURPLE);
        outerCircle.setRadius(20);
        innerCircle.setStrokeWidth(4);
        outerCircle.centerXProperty().bind(scene.widthProperty().divide(2));
        outerCircle.centerYProperty().bind(scene.heightProperty().divide(2));
        outerCircle.radiusProperty().bind(Bindings.min(scene.widthProperty(),scene.heightProperty()));*/
        Thread cont = new Thread(() -> {

            System.out.println("working");
            loadingScreenRotation(firstCircle,true,360,6);
            loadingScreenRotation(secondCircle,true,180,1);
            loadingScreenRotation(thirdCircle,true,140,1);
            loadingScreenRotation(fourthCircle,true,70,1);

        });
        cont.start();
    }

    @FXML
    public void loadingScreenRotation(Circle circle, boolean bool, int angle, int duration){

        RotateTransition rotate = new RotateTransition(Duration.seconds(duration),circle);
        rotate.setAutoReverse(bool);
        rotate.setByAngle(angle);
        rotate.setDelay(Duration.seconds(0));
        rotate.setRate(3);
        rotate.setCycleCount(18);
        rotate.play();
    }

    public void cancelLoadingScreen(Circle circle ) {

        loadingScreenRotation(firstCircle,true,360,30);
        loadingScreenRotation(secondCircle,true,180,18);
        loadingScreenRotation(thirdCircle,true,140,26);
        loadingScreenRotation(fourthCircle,true,70,34);
    }

    public void championPoolDiagram(){

        series1.getData().clear();
        series2.getData().clear();
        series3.getData().clear();
        series4.getData().clear();
        series5.getData().clear();

        championTierDiagram1.getData().clear();
        championTierDiagram2.getData().clear();
        championTierDiagram3.getData().clear();
        championTierDiagram4.getData().clear();
        championTierDiagram5.getData().clear();

        championTierDiagram1.setName("1 Tier");
        championTierDiagram2.setName("2 Tier");
        championTierDiagram3.setName("3 Tier");
        championTierDiagram4.setName("4 Tier");
        championTierDiagram5.setName("5 Tier");

        championTierDiagram1.getData().add(new XYChart.Data("1 Tier",29));
        championTierDiagram2.getData().add(new XYChart.Data("2 Tier",22));
        championTierDiagram3.getData().add(new XYChart.Data("3 Tier",18));
        championTierDiagram4.getData().add(new XYChart.Data("4 Tier",12));
        championTierDiagram5.getData().add(new XYChart.Data("5 Tier",10));

        //barChart.getData().addAll(series);
        //barChart.getData().addAll(series1);
        barChart.getData().addAll(championTierDiagram1,championTierDiagram2,championTierDiagram3,championTierDiagram4,championTierDiagram5);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        leftLabelOfBarChart.setLabel("% Chance to get X, Y Tier Champion");

        loadingScreenRotation(firstCircle,true,360,20);
        loadingScreenRotation(secondCircle,true,180,13);
        loadingScreenRotation(thirdCircle,true,140,8);
        loadingScreenRotation(fourthCircle,true,70,13);

        levelSpinner.setValueFactory(spinnerValueForLevels);
        testWolfram = new TestWolfram();

        /*series1.setName("1 Champion");
        series2.setName("2 Champion");
        series3.setName("3 Champion");
        series4.setName("4 Champion");
        series5.setName("5 Champion");*/

        series1.getData().add(new XYChart.Data("X Champion",90));
        series2.getData().add(new XYChart.Data("Y Champion",40));
        series3.getData().add(new XYChart.Data("Z Champion",9));
        series4.getData().add(new XYChart.Data("S Champion",25));
        series5.getData().add(new XYChart.Data("D Champion",68));

        barChart.getData().addAll(series1);
        barChart.getData().addAll(series2);
        barChart.getData().addAll(series3);
        barChart.getData().addAll(series4);
        barChart.getData().addAll(series5);
        barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: black;");

    }
    //Testing things.
    /*public void initializePopUp() {

        Scene scene = Main.getScene();
        Popup pop = new Popup();

        pop.setX(726);
        pop.setY(932);

        javafx.scene.control.Label label = new javafx.scene.control.Label("The calculations are done. ");
        javafx.scene.control.Label labelESC = new Label("\n\n\nPress 'ESC' to remove the score text.");

        label.setFont(new javafx.scene.text.Font("Arial",50));
        label.setTextFill(javafx.scene.paint.Color.BLUE);

        labelESC.setFont(new Font("Arial", 20));
        labelESC.setTextFill(Color.RED);

        pop.getContent().add(label);
        pop.getContent().add(labelESC);

        if(!pop.isShowing()){
            pop.isHideOnEscape();
            pop.show(scene);
        }
    }*/

    public void exitApplication() {
        Platform.exit();
        System.exit(0);
    }
}
