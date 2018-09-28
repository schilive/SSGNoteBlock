package sample;

// JavaFX All
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

// JavaFX Scene
// Others

public class sscnoteblock extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, java.lang.OutOfMemoryError, java.lang.NullPointerException {
        Parent root = FXMLLoader.load(getClass().getResource("sscnoteblock.fxml"));
        primaryStage.setTitle("Untitled - SSG Note Block");
        primaryStage.setScene(new Scene(root, 1280, 1024));
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../images/sbi.png")));
        primaryStage.show();

    }
}
