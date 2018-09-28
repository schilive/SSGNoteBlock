package sample;

// JavaFX All

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

// JavaFX beans
// JavaFX fxml
// JavaFX scene
// JavaFX stage
//JavaFX Io
// initialize


public class Controller implements Initializable {
    private double ftz = 24; // Font Text Size
    private String ff = "Ubuntu"; // Font Family
    private String tic = "white"; // Text Inner Color
    private int themess = 0; // Theme S selected
    private ToggleGroup themer = new ToggleGroup(); // The group that contains the radio buttons
    private File savedFile; // The file has been saved
    private Boolean savedFileB = false; // If is to do the simple save
    private String savedFileExt; // The extension of the file has been saved
    private Boolean saveAs = false; // If to do the save as
    private BooleanProperty controlKey = new SimpleBooleanProperty(false);
    private BooleanProperty nKey = new SimpleBooleanProperty(false);
    private BooleanProperty oKey = new SimpleBooleanProperty(false);
    private BooleanProperty sKey = new SimpleBooleanProperty(false);
    private final BooleanBinding newShortcut = controlKey.and(nKey);
    private final BooleanBinding openShortcut = controlKey.and(oKey);
    private final BooleanBinding saveShortcut = controlKey.and(sKey);
    @FXML
    public TextArea tftype; // The text typed for the USER
    public RadioMenuItem lightbt; // Button to change to light theme (themer)
    public RadioMenuItem darkbt; // Button to change to dark theme (themer)
    public Parent Vboxmain; // The head of all
    public AnchorPane exitAnchor;

    public Label cancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) { // Be activates when the program opens.
        update();
        tftype.setWrapText(true); // Set the tftype can be used like a text editor
        lightbt.setToggleGroup(themer); // Sets the ToogleGroup of the lightbt (Every Radio Button needs a ToogleGroup)
        darkbt.setToggleGroup(themer); // Sets the ToogleGroup of the darkbt (Every Radio Button needs a ToogleGroup)
        darkbt.setSelected(true); // Sets the darkbt button bes selected or as default option

    }

    // Archive (Save) System
    public void save() throws IOException, java.lang.NullPointerException { // Save what was typed for USER
        if (!savedFileB || saveAs) {
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text document (*.txt)", "*.txt"); // Creates a filter with the description "Text Document (*.txt)" (The description is what shows when you selects the extension) and with the extension "*txt" or "*txy" or "*.txt"
            FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("All Files (.*.)", "*"); // Creates a filter with the description ""All Files (.*.)" (The description is what shows when you selects the extension) and with the extension "*.*"
            String txtSaving = tftype.getText(); // Gets the text from the TextArea
            FileChooser escoger = new FileChooser(); // Creates a FileChooser (It's in the name)
            escoger.getExtensionFilters().addAll(filter, filter2); // Adds the extension created before
            Stage vista = (Stage) Vboxmain.getScene().getWindow(); // Creates a Stage variable using the Vboxmain as window.
            File file = escoger.showSaveDialog(vista); // Start the fileChooser (escoger)
            String extension = ""; // creates a variable to be used to the file be saved with the right extension (gonna be sense)
            if (file != null) { // If file is null is because has a error
                if (!file.toString().contains(".txt")) { // If the

                    if (file.getCanonicalPath().endsWith("txt")) {
                        extension = "*.txt";
                    } else {
                        extension = null;
                    }
                }
                FileWriter f = new FileWriter(file + extension);
                f.write(txtSaving);
                f.close();
                savedFileB = true;
                savedFile = file;
                savedFileExt = extension;
                saveAs = false;
            }
            try {
                if (file != null) {
                    ((Stage) Vboxmain.getScene().getWindow()).setTitle(file.getName() + " - SSG Note Block");
                }
            } catch (java.lang.NullPointerException e) {
                e.printStackTrace();
            }

        } else {
            FileWriter f = new FileWriter(savedFile + savedFileExt);
            f.write(tftype.getText());
            f.close();
        }
    }


    public void load() throws IOException { // Load a archive
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text document (*.txt)", "*.txt");
        FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("All Files (.*.)", "*");
        FileChooser escoger = new FileChooser();
        escoger.getExtensionFilters().addAll(filter, filter2);
        Stage vista = (Stage) Vboxmain.getScene().getWindow();
        File file = escoger.showOpenDialog(vista);

        if (file != null) {
            FileReader reader = new FileReader(file);
            BufferedReader readerl = new BufferedReader(reader);
            String linha = readerl.readLine();
            tftype.setText("");
            while (linha != null) {
                tftype.setText(tftype.getText() + linha + "\n");
                linha = readerl.readLine();
            }
            reader.close();
        }
        try {
            if (file != null) {
                ((Stage) Vboxmain.getScene().getWindow()).setTitle(file.getName() + " - SSG Note Block");
            }

        } catch (java.lang.NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void saveAs() {
        saveAs = true;
        try {
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void news() {
        tftype.setText(null);
    }

    public void keyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.CONTROL) {
            System.out.println(keyEvent.getEventType());
            controlKey.set(true);
            Timeline time = new Timeline();
            time.setCycleCount(Timeline.INDEFINITE);
            KeyFrame frame = new KeyFrame(Duration.seconds(0.5), event -> {
                controlKey.set(false);
                System.out.println('b');
                time.stop();
            });
            time.getKeyFrames().add(frame);
            time.playFromStart();
        } else if (keyEvent.getCode() == KeyCode.N) {
            nKey.set(true);
            Timeline time = new Timeline();
            time.setCycleCount(Timeline.INDEFINITE);
            KeyFrame frame = new KeyFrame(Duration.seconds(0.5), event -> {
                nKey.set(false);
                System.out.println('N');
                time.stop();
            });
            time.getKeyFrames().add(frame);
            time.playFromStart();
        } else if (keyEvent.getCode() == KeyCode.O) {
            oKey.set(true);
            Timeline time = new Timeline();
            time.setCycleCount(Timeline.INDEFINITE);
            KeyFrame frame = new KeyFrame(Duration.seconds(0.5), event -> {
                nKey.set(false);
                System.out.println('O');
                time.stop();
            });
            time.getKeyFrames().add(frame);
            time.playFromStart();
        } else if (keyEvent.getCode() == KeyCode.S) {
            sKey.set(true);
            Timeline time = new Timeline();
            time.setCycleCount(Timeline.INDEFINITE);
            KeyFrame frame = new KeyFrame(Duration.seconds(0.5), event -> {
                nKey.set(false);
                System.out.println('S');
                time.stop();
            });
            time.getKeyFrames().add(frame);
            time.playFromStart();
        }
        newShortcut.addListener((observable, oldValue, newValue) -> {
            if (nKey.get() && controlKey.get()) {
                System.out.println("Control+N");
                nKey.set(false);
                controlKey.set(false);
                news();
            }

        });
        openShortcut.addListener((observable, oldValue, newValue) -> {
            if (oKey.get() && controlKey.get()) {
                System.out.println("Control+N");
                oKey.set(false);
                controlKey.set(false);
                try {
                    load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        saveShortcut.addListener((observable, oldValue, newValue) -> {
            if (sKey.get() && controlKey.get()) {
                System.out.println("Control+S");
                sKey.set(false);
                controlKey.set(false);
                try {
                    save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Updates System
    private void update() { // Updates the settings and personalization of the program.
        String theme = "null";
        if(themess == 0){theme = "black";tic = "white";} // Dark Theme
        if(themess == 1){theme = "white";tic = "black";} // Light Theme
        tftype.setStyle("-fx-font-family: " + ff + "; -fx-text-inner-color: " + tic +";  -fx-font-size: " + ftz + "; text-area-background: " + theme);
        Vboxmain.setStyle("text-area-background: " + theme);


    }


    // Fonts
    public void scp(){ // Source Code Pro
        ff = "'Source Code Pro'";
        update();
    }

    public void arial() { // Arial
        ff = "Arial";
        update();
    }

    public void ubuntu() { // Ubuntu
        ff = "Ubuntu";
        update();
    }

    public void exit() { // Exits from the program (Close)
        Platform.exit(); // Closes the program

    }

    // The fonts sizes
    public void n6() {
        ftz = 6; // Font Text Size
        update();
    }

    public void n7() {
        ftz = 7;
        update();
    }

    public void n8() {
        ftz = 8;
        update();
    }

    public void n10() {
        ftz = 10;
        update();
    }

    public void n9() {
        ftz = 9;
        update();
    }

    public void n10p5() {
        ftz = 10.5;
        update();
    }

    public void n11() {
        ftz = 11;
        update();
    }

    public void n12() {
        ftz = 12;
        update();
    }

    public void n13() {
        ftz = 13;
        update();
    }

    public void n14() {
        ftz = 14;
        update();
    }

    public void n15() {
        ftz = 15;
        update();
    }

    public void n16() {
        ftz = 16;
        update();
    }

    public void n18() {
        ftz = 18;
        update();
    }

    public void n20() {
        ftz = 20;
        update();
    }

    public void n22() {
        ftz = 22;
        update();
    }

    public void n24() {
        ftz = 24;
        update();
    }

    public void n26() {
        ftz = 26;
        update();
    }

    public void n28() {
        ftz = 28;
        update();
    }

    public void n32() {
        ftz = 32;
        update();
    }

    public void n36() {
        ftz = 36;
        update();
    }

    public void n40() {
        ftz = 40;
        update();
    }

    public void n44() {
        ftz = 44;
        update();
    }

    public void n48() {
        ftz = 48;
        update();
    }

    public void n54() {
        ftz = 54;
        update();
    }

    public void n60() {
        ftz = 60;
        update();
    }

    public void n66() {
        ftz = 66;
        update();
    }

    public void n72() {
        ftz = 72;
        update();
    }


    public void darkTheme() { // Define DarkTheme
        themess = 0; // Number of the theme; 0 = dark theme
        update();
    }

    public void lightTheme() { // Define LightTheme
        themess = 1; // Number of the theme; 1 = light theme
        update();

    }

//    public void exiter(String title, String menssage) throws IOException, java.lang.NullPointerException{
//        try {
//            Stage stage = (Stage) Vboxmain.getScene().getWindow();
//            stage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("exit.fxml"));
//            stage.setTitle(title);
//            cancel.setText(menssage);
//            stage.setTitle("SSG Note Block");
//            stage.setScene(new Scene(root));
//            stage.initOwner(stage);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }catch (java.lang.NullPointerException b){
//            b.printStackTrace();
//        }
//
//    }
//
//    public void close() {
//        System.exit(ExitException.OK);
//    }
//
//    public void closeOne(ActionEvent actionEvent) {
//
//
//    }
}