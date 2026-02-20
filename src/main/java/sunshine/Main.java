package sunshine;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * A GUI for Sunshine using FXML.
 */
public class Main extends Application {

    private Sunshine sunshine = new Sunshine();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Region root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sunshine");
            stage.setMinWidth(root.getPrefWidth());
            stage.setMinHeight(root.getPrefHeight());
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setStage(stage);
            mainWindow.setSunshine(sunshine); // inject the Sunshine instance
            String loadResultString = sunshine.loadTasks();
            mainWindow.showLoadResult(loadResultString);
            mainWindow.showWelcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
