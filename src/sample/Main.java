package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("characterUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        this.primaryStage=primaryStage;
        primaryStage.setTitle("Chinese Charactors");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
