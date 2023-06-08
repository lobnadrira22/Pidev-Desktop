/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package healthconnectjava.GUI;

import healthconnectjava.entities.Evenement;
import healthconnectjava.services.EvenementService;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ModifierEventFXMLController implements Initializable {
    private Evenement evenement;
    @FXML
    private TextField nomtfM;
    @FXML
    private ComboBox<String> comboBoxM;
    @FXML
    private DatePicker datepickerM;
    @FXML
    private TextField adressetfM;
    @FXML
    private TextArea descriptiontfM;
    @FXML
    private Button ModifB;
    @FXML
    private TextField imagetfM;
    @FXML
    private Button imageB;
    @FXML
    private AnchorPane intModif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<String> options = FXCollections.observableArrayList(
        "Yoga",
        "Tennis",
        "Gymnastic",
        "Musculation"

        );
        comboBoxM.setItems(options);
    }    

 
    
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
        nomtfM.setText(evenement.getNom());
        comboBoxM.setValue(evenement.getType());
        adressetfM.setText(evenement.getAdresse());
        descriptiontfM.setText(evenement.getDescription());
        datepickerM.setValue(LocalDate.parse(evenement.getDate().toString()));
        imagetfM.setText(evenement.getImage());
    }
    
    
    
        @FXML
    private void modifierEvenement(ActionEvent event) {
       
        //////////////////controle de saisie///////////
          if (nomtfM.getText().isEmpty() || adressetfM.getText().isEmpty() || descriptiontfM.getText().isEmpty() || imagetfM.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return; // sortir de la méthode si un champ est vide
    }
          
            if (comboBoxM.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir un type d'evenement !");
        alert.showAndWait();
        return; // sortir de la méthode si un champ est vide
    }
            
        // Vérifier que la longueur de la description est d'au moins 20 caractères
        if (descriptiontfM.getText().length() < 20) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("La description doit contenir au moins 20 caractères !");
            alert.showAndWait();
            return; // sortir de la méthode si la description est trop courte
        }
        
         // Vérifier que l'image a une extension valide
       String imagePath = imagetfM.getText();
        if(!imagePath.endsWith(".jpg") && !imagePath.endsWith(".png")){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Extension d'image invalide");
        alert.setHeaderText(null);
        alert.setContentText("L'image doit être au format .jpg ou .png");
        alert.showAndWait();
        return;
    }
          
        
        // Vérification de la date
        LocalDate currentDate = LocalDate.now();
        if (datepickerM.getValue().isBefore(currentDate)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("La date de l'événement doit être supérieure ou égale à la date du jour.");
            alert.showAndWait();
            return;
        }
        
        

        
        evenement.setNom(nomtfM.getText());
        evenement.setType(comboBoxM.getValue());
        evenement.setAdresse(adressetfM.getText());
        evenement.setDescription(descriptiontfM.getText());
        evenement.setDate(Date.valueOf(datepickerM.getValue()));
        evenement.setImage(imagetfM.getText());
        
        EvenementService evService = new EvenementService();
        try {
            evService.modifierEvenement(evenement);
            // Afficher une fenêtre de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes de modification");
            alert.setHeaderText(null);
            alert.setContentText("Evenement modifié avec succes");
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierEventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Fermer la fenêtre de modification
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    private void annuler(ActionEvent event) {
        // Fermer la fenêtre de modification sans enregistrer les modifications
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
   

    @FXML
    private void chooseImageButton(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
          );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
        String imagePath = selectedFile.getAbsolutePath();
        imagetfM.setText(imagePath);
        }
    }
     private void closeWindow() {
    Stage stage = (Stage) intModif.getScene().getWindow();
    stage.close();
}
}

