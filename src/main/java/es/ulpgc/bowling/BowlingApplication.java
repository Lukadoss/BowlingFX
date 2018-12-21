package es.ulpgc.bowling;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Main class of the application, SpringBoot entry point.
 * Loads the JavaFX necessary files and loads GUI
 * @author David Bohmann
 */
@SpringBootApplication
public class BowlingApplication extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent root;
    private static Stage stage;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(BowlingApplication.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainWindow.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        primaryStage.setTitle("Bowling");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }

    public static Stage getPrimaryStage() {
        return stage;
    }

    private void setPrimaryStage(Stage stage) {
        BowlingApplication.stage = stage;
    }

    public static void main(String[] args) {
        launch(BowlingApplication.class, args);
    }
}