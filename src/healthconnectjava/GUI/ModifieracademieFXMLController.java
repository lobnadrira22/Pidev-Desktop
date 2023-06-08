/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.GUI;

import healthconnectjava.entities.Academie;
import healthconnectjava.services.AcademieService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import java.util.regex.Pattern;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifieracademieFXMLController implements Initializable {
  @FXML
    private Label adresse;

    @FXML
    private TextField adtext;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private Button modbtn;

    @FXML
    private Label modiif;

    @FXML
    private Label nom;

    @FXML
    private TextField nomtxt;

    @FXML
    private Label numtel;

    @FXML
    private TextField numteltext;

    @FXML
    private Label sportpropose;

    @FXML
    private TextField sptx;
    
    @FXML
    private Button retour;
    
    Academie academie=new Academie();
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
   
  
    public void pass(Academie academie){
       
        this.academie=academie;
        nomtxt.setText(academie.getNom());
        adtext.setText(academie.getAdresse());
        numteltext.setText(academie.getNumtel());
        sptx.setText(academie.getSportpropose());
        

    }
    @FXML
   public void Modifier(ActionEvent e) throws SQLException{
    AcademieService as = new AcademieService();

    if (nomtxt.getText().isEmpty() || adtext.getText().isEmpty() || numteltext.getText().isEmpty() || sptx.getText().isEmpty()) {
        // Afficher une alerte si l'un des champs est vide
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.show();
        return;
    }
    
   if (!numteltext.getText().matches("\\d{8}")) {
        // Afficher une alerte si le numéro de téléphone est invalide
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le numéro de téléphone doit être composé de 8 chiffres.");
        alert.show();
        return;
    }
    academie.setNom(nomtxt.getText());
    academie.setAdresse(adtext.getText());
    academie.setNumtel(numteltext.getText());
    academie.setSportpropose(sptx.getText());
    
    /* Notifications notificationBuilder = Notifications.create()
    .title("Académie modifiée avec succés ")
    .text("les modifications sont enregistrés ")
    .hideAfter(Duration.seconds(5))
    .position(Pos.CENTER)
    .graphic(null)
    .darkStyle()
    .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            // code à exécuter lorsqu'on clique sur la notification
        }
    })
//notificationBuilder.showInformation(); */

    as.modifieracademie(academie);
}

     public void redirecttoacademie(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AcademieFXML.fxml"));
         
            
            newLoadedPane = loader.load();
            AcademieFXMLController ac = loader.getController();
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchor2.getChildren().clear();
        anchor2.getChildren().add(newLoadedPane);
       

    }

   
   
}


