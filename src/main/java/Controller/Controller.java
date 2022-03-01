package Controller;

import javafx.animation.RotateTransition;
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
    private BarChart<?,?> barChart;
    @FXML
    public static XYChart.Series series = new XYChart.Series();
    public static XYChart.Series series1 = new XYChart.Series();
    public static XYChart.Series series2 = new XYChart.Series();

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
        if(validateIntegerInput(numberOfCopies.getText())){
            return Integer.parseInt(numberOfCopies.getText());
        }
        return 0;
    }

    /**
     * HERE ARE THE DIFFERENT TYPES OF CALCULATIONS YOU CAN DO
     */
    @FXML
    public void calculateNormal() {
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
        //TestWolfram twf = new TestWolfram();
        //twf.calculateWolframNormal((percentagesOfHitting),uniqueUnitAmount,getHowMuchGold(),ChampionTierStats.getChampionCost(),userLevel);
    }

    /**
     * This one have 2 more parameters
     * @return
     */
    public double calculateSweat() {
        createLoadingCircles();
        int userLevel = svf.getValue();
        double[][] percentagesOfHitting = (ChampionTierStats.getChampionPercentage());
        int uniqueUnitAmount = Integer.parseInt(numberOfCopies.getText());
        TestWolfram twf = new TestWolfram();
        return twf.calculateWolframSweat((percentagesOfHitting),uniqueUnitAmount,getHowMuchGold(),ChampionTierStats.getChampionCost(),userLevel);
    }
    private int getHowMuchGold() {
        return Integer.parseInt(amountOfGold.getText());
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
        //createLoadingCircles();
        System.out.println("updater works");
        series.getData().add(new XYChart.Data("1 Champion",testWolfram.getLastCalculatedValueNormal()));
        series1.getData().add(new XYChart.Data("2 Champions",testWolfram.getLastCalculatedValueSweat()));
        System.out.println("yes");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (final XYChart.Series<?, ?> series : barChart.getData()) {
            for (final XYChart.Data<?, ?> data : series.getData()) {
                Tooltip tooltip = new Tooltip();
                tooltip.setText(data.getXValue().toString() +" "+
                        data.getYValue().toString());
                Tooltip.install(data.getNode(), tooltip);
            }
        }

        loadingScreenRotation(firstCircle,true,360,20);
        loadingScreenRotation(secondCircle,true,180,13);
        loadingScreenRotation(thirdCircle,true,140,8);
        loadingScreenRotation(fourthCircle,true,70,13);

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
