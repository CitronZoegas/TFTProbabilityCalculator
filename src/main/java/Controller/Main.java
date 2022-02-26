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
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static Main instance;
    private Controller controller;

    @Override
    public void start(Stage stage) {
        try{
            controller = new Controller();
            initializeConnection();
            FXMLLoader fxmlloader = new FXMLLoader(Main.class.getResource("/FXML/clientPage.fxml"));
            Controller controller = fxmlloader.getController();
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            stage.setTitle("TFT Probability Calculator");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeConnection(){
        instance = this;
    }

    public static void main (String[] args){
        launch();
    }
}
