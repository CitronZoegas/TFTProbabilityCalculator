package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static Main instance;
    private Controller controller;
    private TestWolfram testWolfram;
    @Override
    public void start(Stage stage) {
        try{
            controller = new Controller();
            initializeConnection();
            FXMLLoader fxmlloader = new FXMLLoader(Main.class.getResource("/FXML/clientPage.fxml"));

            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(String.valueOf((Objects.requireNonNull(getClass().getResource("/CSS/stylesheet.css")).toExternalForm())));
            stage.setTitle("TFT Probability Calculator");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeConnection(){
        instance = this;
    }
    public static Scene getScene() {
        Scene scene = null ;
        try {
            FXMLLoader fxmlloader = new FXMLLoader(Main.class.getResource("/FXML/clientPage.fxml"));
            Parent root = fxmlloader.load();
            scene = new Scene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scene;
    }

    public static void main (String[] args){
        launch();
    }
}
