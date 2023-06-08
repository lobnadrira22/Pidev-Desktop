/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package healthconnectjava;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author wiki
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
           // Parent root = FXMLLoader.load(getClass().getResource("./GUI/LoginFXML.fxml")) ;
          // Parent root = FXMLLoader.load(getClass().getResource("./GUI/ListClientForAdminFXML.fxml")) ;
             Parent root = FXMLLoader.load(getClass().getResource("./GUI/ListClientForAdminFXML.fxml")) ;


            Scene scene = new Scene(root, 600, 400);
            
            primaryStage.setTitle("HealthConnect");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
