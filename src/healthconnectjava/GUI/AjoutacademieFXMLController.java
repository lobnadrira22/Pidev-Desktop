/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.GUI;

import healthconnectjava.entities.Academie;
import healthconnectjava.services.AcademieService;
import healthconnectjava.services.Emailsender;
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


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjoutacademieFXMLController implements Initializable {
    
       @FXML
    private Label aclabel;

    @FXML
    private Label ad;

    @FXML
    private TextField adTF;

    @FXML
    private AnchorPane anch;

    @FXML
    private Button btn;

    @FXML
    private Label no;

    @FXML
    private TextField nomTF;

    @FXML
    private Label nt;

    @FXML
    private TextField numtelTF;

    @FXML
    private Button revenir;

    @FXML
    private Label sp;

    @FXML
    private TextField spTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
private void ajouterAcademie(ActionEvent event) {
    // Récupérer les valeurs des champs de texte
    String nom = nomTF.getText();
    String adresse = adTF.getText();
    String numtel = numtelTF.getText();
    String sportpropose = spTF.getText();

    // Vérifier que les champs ne sont pas vides
    if (nom.isEmpty() || adresse.isEmpty() || numtel.isEmpty() || sportpropose.isEmpty()) {
        // Afficher une alerte si l'un des champs est vide
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.show();
        return;
    }

    // Vérifier que le numéro de téléphone est composé de 8 chiffres
    if (!numtel.matches("\\d{8}")) {
        // Afficher une alerte si le numéro de téléphone est invalide
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le numéro de téléphone doit être composé de 8 chiffres.");
        alert.show();
        return;
    }

    // Si tout est valide, ajouter l'académie
    Academie aa = new Academie(nom, adresse, numtel, sportpropose);
    AcademieService as = new AcademieService();
     String message = "Bienvenue à HealthConnect \n"
                        + "\n"
                        + "la nouvelle académie a été ajouté au site\n"
                       
                        +  "le nom  : " + aa.getNom()  + "\n"
                     
                        + "l'adresse  : " + aa.getAdresse()  + "\n"
                        + "le numéro de tel de l'academie  : " + aa.getNumtel() + "\n"
              + "Le sport proposé : " + aa.getSportpropose() + "\n"
                      
                       
                        //+ "We are pleased to inform you that your reservation has been successfully processed, and we have reserved the required number of seats for you. Your confirmation number is [Enter confirmation number].\n"
                        + "\n";

                Emailsender.sendEmail_add("donia.drira@esprit.tn", message);
     Notifications notificationBuilder = Notifications.create()
    .title("Académie ajouté avec succés ")
    .text("les ajouts sont enregistrés ")
    .hideAfter(Duration.seconds(5))
    .position(Pos.CENTER)
    .graphic(null)
    .darkStyle()
    .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            // code à exécuter lorsqu'on clique sur la notification
        }
    });
//notificationBuilder.showInformation(); 
    try {
        as.ajoutAcademiee(aa);
        

        nomTF.setText("");
        adTF.setText("");
        numtelTF.setText("");
        spTF.setText("");

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

public void redirect(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AcademieFXML.fxml"));
         
            
            newLoadedPane = loader.load();
            AcademieFXMLController s = loader.getController();
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anch.getChildren().clear();
        anch.getChildren().add(newLoadedPane);
       

    }
    
}
