package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("SSG Note Block");
        primaryStage.setScene(new Scene(root, 1280, 1024));
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../images/sbi.png")));
        primaryStage.show();
    }
}
