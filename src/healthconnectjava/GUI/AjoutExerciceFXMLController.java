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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
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
public class AjoutExerciceFXMLController implements Initializable {

    @FXML
    private TextField nomtf;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField dureetf;
    @FXML
    private TextArea descriptiontf;
    @FXML
    private Button AjoutB;
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

    @FXML
    private void ajouterExercice(ActionEvent event) {
     try {
        // Récupération des valeurs des champs
        String nom = nomtf.getText();
        String duree = dureetf.getText();
        String description = descriptiontf.getText();
        String image = imagetf.getText();
        String nomEvenement = comboBox.getValue(); // Récupération de la valeur sélectionnée dans le ComboBox
        
       
        
        
        
        ////////////////////////////////////////verification(controle de saisie)/////////////////////////////
         if (nom.isEmpty() || duree.isEmpty() || description.isEmpty() || image.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return;
    }
         
         if (nomEvenement == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir un type d'evenement !");
        alert.showAndWait();
        return;
    }
         
           if (description.length() < 20) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La description doit contenir au moins 20 caractères !");
        alert.showAndWait();
        return;
    }
         
         
      // Vérifier que l'image est au format jpg ou png
    if (!image.toLowerCase().endsWith(".jpg") && !image.toLowerCase().endsWith(".png")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur lors de l'ajout");
        alert.setHeaderText(null);
        alert.setContentText("L'image doit être au format JPG ou PNG !");
        alert.showAndWait();
        return;
    }    
           
           
        // Récupération de l'ID de l'événement correspondant au nom sélectionné
        int idEvenement = evenementService.getIdEvenementByNom(nomEvenement);
        
        // Création de l'exercice
        Exercice exercice = new Exercice(idEvenement,nom,description, duree,image);
        
        // Ajout de l'exercice à la base de données
        ExerciceService exerciceService = new ExerciceService();
        exerciceService.ajoutExercice(exercice);
        
        // Affichage d'un message de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Exercice ajouté avec succès !");
        alert.showAndWait();
//        closeWindow();
              ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();


        
        // Effacement des champs après ajout
        nomtf.setText("");
        dureetf.setText("");
        descriptiontf.setText("");
        imagetf.setText("");
        comboBox.setValue(null); // Effacement de la sélection dans le ComboBox
        
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout de l'exercice : " + ex.getMessage());
        // Affichage d'un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur est survenue lors de l'ajout de l'exercice !");
        alert.showAndWait();
       // closeWindow();

    }  
}

   /* private void closeWindow() {
    Stage stage = (Stage) intMod.getScene().getWindow();
    stage.close();
}*/

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
    private void annuler(ActionEvent event) {
        // Fermer la fenêtre de modification sans enregistrer les modifications
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }     
    
}
