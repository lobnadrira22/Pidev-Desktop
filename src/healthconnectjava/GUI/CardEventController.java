/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package healthconnectjava.GUI;



import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import healthconnectjava.entities.Evenement;
import healthconnectjava.entities.Participation;
import healthconnectjava.services.ParticipationService;


/**
 * FXML Controller class
 *
 * @author User
 */
public class CardEventController implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_date;

    @FXML
    private Button participer_btn;
    
     @FXML
    private Button details_btn;

    private Evenement prodData;
    private ParticipationService participationService;
    private int userId = 1; // ID de l'utilisateur statique pour le moment
    private boolean participe = false;
    private Image image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        participationService = new ParticipationService();
    }

     public void setData(Evenement prodData){
        this.prodData=prodData;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = formatter.format(prodData.getDate());
        System.out.println( dateStr);
        prod_date.setText(dateStr);
        prod_name.setText(prodData.getNom());
        String path ="File: "+ prodData.getImage() ;
        System.out.println(path);
        File file = new File(prodData.getImage());
        System.out.println("File exists: " + file.exists());
        image=new Image(file.toURI().toString());
        prod_imageView.setImage(image);
        prod_imageView.setFitWidth(400);
        prod_imageView.setFitHeight(200);
      // Date pr = prodData.getDate();
    }

    @FXML
    public void participerBTN() {
        try {
            if (participe) {
                // Si l'utilisateur a déjà participé, supprime la participation
                Participation p = participationService.getParticipationByEvenementIdAndUserId(prodData.getId(), userId);
                participationService.supprimerParticipation(p.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Suppression de la participation");
                alert.setHeaderText(null);
                alert.setContentText("Votre participation a été supprimée avec succès");
                alert.showAndWait();
                participer_btn.setText("Participer");
                participe = false;
            } else {
                // Sinon, ajoute une participation
                System.out.println(prodData.getId());
                Participation p = new Participation(prodData.getId(), userId);
                participationService.ajoutParticipation(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation de la participation");
                alert.setHeaderText(null);
                alert.setContentText("Votre participation a été effectuée avec succès");
                alert.showAndWait();
                participer_btn.setText("S'abstenir");
                participe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CardEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void detailsBTN(ActionEvent event) {
        // Créez une boîte de dialogue et configurez-la avec l'adresse, la description et le type de l'événement.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails de l'événement");
        alert.setHeaderText(prodData.getNom());
        alert.setContentText("Adresse: " + prodData.getAdresse() + "\n\nDescription: " + prodData.getDescription() + "\n\nType: " + prodData.getType());
        alert.showAndWait();
    }
}  