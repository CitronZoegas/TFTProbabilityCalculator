package Controller;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
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
    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,11,initialValue);
    @FXML
    private BarChart<String,Number> barChart;
    @FXML
    public static   XYChart.Series  series = new XYChart.Series<>(),
                                    series1 = new XYChart.Series<>(),
                                    series2 = new XYChart.Series<>();

    private TestWolfram testWolfram;


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
        try{
            createLoadingCircles();
            int userLevel = svf.getValue();
            double[][] percentagesOfHitting = (ChampionTierStats.getChampionPercentage());
            int uniqueUnitAmount = Integer.parseInt(numberOfCopies.getText());
            TestWolfram twf = new TestWolfram();
            Thread cont = new Thread(() -> {
                twf.calculateWolframNormal((percentagesOfHitting),uniqueUnitAmount,getHowMuchGold(),ChampionTierStats.getChampionCost(),userLevel);
                System.out.println("working");
                loadingScreenRotation(firstCircle,true,360,6);
                loadingScreenRotation(secondCircle,true,180,1);
                loadingScreenRotation(thirdCircle,true,140,1);
                loadingScreenRotation(fourthCircle,true,70,1);
            });
            cont.start();
            clearCharts();
            updateNormalCharts();
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
        try{
            createLoadingCircles();
            int userLevel = svf.getValue();
            double[][] percentagesOfHitting = (ChampionTierStats.getChampionPercentage());
            int uniqueUnitAmount = Integer.parseInt(numberOfCopies.getText());
            TestWolfram twf = new TestWolfram();
            //Platform.runLater(() -> twf.calculateWolframSweat((percentagesOfHitting),uniqueUnitAmount,getHowMuchGold(),ChampionTierStats.getChampionCost(),userLevel));
            Thread cont = new Thread(() -> {
                twf.calculateWolframSweat((percentagesOfHitting),uniqueUnitAmount,getHowMuchGold(),ChampionTierStats.getChampionCost(),userLevel);
                System.out.println("working");
                loadingScreenRotation(firstCircle,true,360,6);
                loadingScreenRotation(secondCircle,true,180,1);
                loadingScreenRotation(thirdCircle,true,140,1);
                loadingScreenRotation(fourthCircle,true,70,1);
            });

            clearCharts();
        } catch (Exception e) {
            System.out.println("You have not calculated anything 'Sweat' related.");
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

    /**
     * Clear the example diagrams that the UI gets initialized with or potential residue from already active diagrams.
     */
    public void clearCharts() {
        series.getData().clear();
        series1.getData().clear();
        series2.getData().clear();
    }
    public void updateNormalCharts() {
        testWolfram = new TestWolfram();

        if(testWolfram.getLastCalculatedValueNormal() > 0 || validateDoubleInput(testWolfram.getLastCalculatedValueNormal())){
            series.getData().add(new XYChart.Data("1 Champion",testWolfram.getLastCalculatedValueNormal()));
        }
        if(testWolfram.getLastCalculatedValueSweat() > 0 || validateDoubleInput(testWolfram.getLastCalculatedValueSweat())){
            series1.getData().add(new XYChart.Data("1 Champion",testWolfram.getLastCalculatedValueSweat()));
        }

        System.out.println("yes");
    }
    public void updateSweatCharts() {
        testWolfram = new TestWolfram();
        series1.getData().add(new XYChart.Data("1Champion", testWolfram.getLastCalculatedValueSweat()));
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
        Thread cont = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("working");
                loadingScreenRotation(firstCircle,true,360,6);
                loadingScreenRotation(secondCircle,true,180,1);
                loadingScreenRotation(thirdCircle,true,140,1);
                loadingScreenRotation(fourthCircle,true,70,1);
            }
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
    public void initilizeToolTipForBarChart() {
        Tooltip diagramToolTip = new Tooltip();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadingScreenRotation(firstCircle,true,360,20);
        loadingScreenRotation(secondCircle,true,180,13);
        loadingScreenRotation(thirdCircle,true,140,8);
        loadingScreenRotation(fourthCircle,true,70,13);

        levelSpinner.setValueFactory(svf);
        testWolfram = new TestWolfram();

        series.setName("1 Champion");
        series1.setName("2 Champion");
        series2.setName("3 Champion");

        series.getData().add(new XYChart.Data("X Champion",90));
        XYChart.Data bestBar = (XYChart.Data)series.getData().get(0);
        Tooltip.install(bestBar.getNode(),new Tooltip("LOL"));
        series1.getData().add(new XYChart.Data("Y Champion",40));
        series2.getData().add(new XYChart.Data("Z Champion",9));

        barChart.getData().addAll(series);
        barChart.getData().addAll(series1);
        barChart.getData().addAll(series2);
    }
}
