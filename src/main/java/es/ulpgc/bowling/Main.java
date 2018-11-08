package es.ulpgc.bowling;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("mainWindow.fxml")));
        primaryStage.setTitle("BowlingServiceImpl");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setMinWidth(620);
        primaryStage.setMinHeight(440);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}