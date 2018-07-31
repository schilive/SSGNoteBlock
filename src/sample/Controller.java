package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private double ftz = 24; // Font Text Size
    private String ff = "Ubuntu"; // Font Family
    private String tic = "white"; // Text Inner Color
    private int themess = 0; // Theme S selected
    @FXML
    public TextArea tftype; // The text typed for the USER
    public RadioMenuItem lightbt;
    public RadioMenuItem darkbt;
    private ToggleGroup themer = new ToggleGroup();
    public Parent Vboxmain;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        update();
        tftype.setWrapText(true);
        lightbt.setToggleGroup(themer);
        darkbt.setToggleGroup(themer);
        darkbt.setSelected(true);

    }

    public void save() throws IOException { // Save what was typed for USER
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text document (*.txt)", ".txt");
        FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("All Files (.*.)", "*"); // Represent all files with the extension "*"
        String txtSaving = tftype.getText();
        FileChooser escoger = new FileChooser();
        escoger.getExtensionFilters().addAll(filter, filter2);
        Stage vista = (Stage) Vboxmain.getScene().getWindow();
        File file = escoger.showSaveDialog(vista);

        if (file != null) {
            String extension;
            if (file.getCanonicalPath().endsWith("txt")) {
                extension = ".txt";
            } else {
                extension = "";
            }
            FileWriter f = new FileWriter(file + extension);
            f.write(txtSaving);
            f.close();

        }
    }

    public void load() { // Load a archive *.txt
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text document (*.txt)", "txt");
        JFileChooser fs = new JFileChooser();
        fs.setDialogTitle("Load your document");
        fs.setFileFilter(filter);
        int result = fs.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){

            try{
                File saver = fs.getSelectedFile();
                BufferedReader b = new BufferedReader(new FileReader(saver.getPath()));
                String line;
                StringBuilder s = new StringBuilder();
                while ((line = b.readLine()) != null){
                    s.append(line);
                }
                tftype.setText(s.toString());
                b.close();
            }
            catch (Exception e2){
                JOptionPane.showMessageDialog(null, e2.getMessage());
            }

        }
    }

    private void update(){
        String theme = "null";
        if(themess == 0){theme = "black";tic = "white";} // Dark Theme
        if(themess == 1){theme = "white";tic = "black";} // Light Theme
        tftype.setStyle("-fx-font-family: " + ff + "; -fx-text-inner-color: " + tic +";  -fx-font-size: " + ftz + "; text-area-background: " + theme);
        Vboxmain.setStyle("text-area-background: " + theme);


    }

    public void scp(){ // Source Code Pro
        ff = "'Source Code Pro'";
        update();
        System.out.println("Source Code Pro");
    }

    public void arial(){
        ff = "Arial";
        update();
        System.out.println("Arial");
    }

    public void ubuntu(){
        ff = "Ubuntu";
        update();
        System.out.println("Ubuntu");
    }

    public void exit() { // Exit from the program (Close)
        Platform.exit(); // Close the program

    }

    public void n6() {
        ftz = 6;
        tftype.setStyle("-fx-text-inner-color: white; -fx-font-size: " + ftz);
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


    public void darkTheme() {
        System.out.println("Dark Theme");
        themess = 0; // Number of the theme; 0 = dark theme
        update();
    }
    public void lightTheme(){
        System.out.println("Light Theme");
        themess = 1; // Number of the theme; 1 = light theme
        update();
    }

}
