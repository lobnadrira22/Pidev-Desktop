/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package healthconnectjava.GUI;


import healthconnectjava.entities.Exercice;
import healthconnectjava.services.EvenementService;
import healthconnectjava.services.ExerciceService;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author User
 */
public class ModifierExerciceFXMLController implements Initializable {
    private Exercice exercice;

    @FXML
    private TextField nomtf;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField dureetf;
    @FXML
    private TextArea descriptiontf;
    @FXML
    private Button ModifB;
    @FXML
    private Button imageB;
    @FXML
    private TextField imagetf;
    
    private EvenementService evenementService = new EvenementService();
    @FXML
    private AnchorPane intMod;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // récupérer la référence du ComboBox et remplir la liste des noms des événements
        try {
            List<String> nomsEvenements = evenementService.getNomsEvenements();
            comboBox.getItems().addAll(nomsEvenements);
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement des noms des événements : " + ex.getMessage());
        }  
    }    

    
    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
        nomtf.setText(exercice.getNom());
        comboBox.setValue(exercice.getEvenementNom());
        dureetf.setText(exercice.getDuree());
        descriptiontf.setText(exercice.getDescription());
        imagetf.setText(exercice.getImage());
    }
    
    
    
    @FXML
    private void modiferExercice(ActionEvent event) {
        
        
         if (nomtf.getText().isEmpty() || dureetf.getText().isEmpty() || descriptiontf.getText().isEmpty() || imagetf.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return; // sortir de la méthode si un champ est vide
    }
          if (comboBox.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir un type d'evenement !");
        alert.showAndWait();
        return; // sortir de la méthode si un champ est vide
    }
          
           // Vérifier que la longueur de la description est d'au moins 20 caractères
    if (descriptiontf.getText().length() < 20) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("La description doit contenir au moins 20 caractères !");
        alert.showAndWait();
        return; // sortir de la méthode si la description est trop courte
    }
    
        // Vérifier que l'image a une extension valide
       String imagePath = imagetf.getText();
        if(!imagePath.endsWith(".jpg") && !imagePath.endsWith(".png")){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Extension d'image invalide");
        alert.setHeaderText(null);
        alert.setContentText("L'image doit être au format .jpg ou .png");
        alert.showAndWait();
        return;
    }
         
        exercice.setNom(nomtf.getText());
        exercice.setEvenementNom(comboBox.getValue());
        exercice.setDuree(dureetf.getText());
        exercice.setDescription(descriptiontf.getText());
        exercice.setImage(imagetf.getText());
        
        ExerciceService exService = new ExerciceService();
        try {
            exService.modifierExercice(exercice);
            // Afficher une fenêtre de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de modification");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous confirmer la modification?");
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierEventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Fermer la fenêtre de modification
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
        imagetf.setText(imagePath);
        }
    }
    
}
