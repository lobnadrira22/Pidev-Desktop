/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.GUI;


import healthconnectjava.entities.CategorieLocation;
import healthconnectjava.entities.Espace;
import healthconnectjava.services.CategorieLocationService;
import healthconnectjava.services.EspaceService;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lobna
 */
public class ModifierEspaceFXMLController implements Initializable {
    private Espace espace;
     @FXML
    private AnchorPane anchormodif;

    @FXML
    private Label txtmodifier;

    @FXML
    private Label nommodif;

    @FXML
    private Label caracteristic;

    @FXML
    private Label dispo;

    @FXML
    private Label adresse;

    @FXML
    private TextField txtfieldnom;

    @FXML
    private TextArea txtareacaract;

    @FXML
    private TextField txtfielddispo;

    @FXML
    private TextField txtfieldadress;

    @FXML
    private DatePicker datepickertar;

    @FXML
    private Label tariflbl;

    @FXML
    private Label prixlbl;

    @FXML
    private TextField txtfieldprix;

    @FXML
    private ComboBox<CategorieLocation> comboboxcat;

    @FXML
    private Label categorielbl;

    @FXML
    private Button btnmodif;

    @FXML
    private Button btnannuler;

    @FXML
    private Label imglbl;

    @FXML
    private Button btnfile;

    @FXML
    private TextField imagetxf;


    /**
     * Initializes the controller class.
     */
    CategorieLocationService ct = new CategorieLocationService();
      EspaceService esp = new EspaceService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
       comboboxcat.getItems().addAll(ct.afficherListeC());
       } catch(SQLException e){
           e.printStackTrace();
       }
    }   
    
    public void setEspace(Espace espace) {
        this.espace = espace;
        txtfieldnom.setText(espace.getNom());
        txtareacaract.setText(espace.getCaracteristique());
        txtfielddispo.setText(espace.getDispo());
        txtfieldadress.setText(espace.getAdresse());
        txtfieldprix.setText(Double.toString(espace.getPrixlocation()));
        imagetxf.setText(espace.getImage());
        datepickertar.setValue(LocalDate.parse(espace.getTarifhoraire().toString()));
                
        comboboxcat.setValue(espace.getCategorieloc());
        
    }
      @FXML
    private void modifierEspace(ActionEvent event) {
       LocalDate localDate = datepickertar.getValue();
    if (localDate == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }
   
Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
Date date = Date.from(instant);


    String image = imagetxf.getText();
        //////////////////controle de saisie///////////
          if (txtfieldnom.getText().isEmpty() || txtareacaract.getText().isEmpty() || txtfielddispo.getText().isEmpty() || txtfieldadress.getText().isEmpty() || txtfieldprix.getText().isEmpty() || imagetxf.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return; // sortir de la méthode si un champ est vide
    }
           if (comboboxcat.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir la catégorie de l'espace.");
        alert.showAndWait();
        return;
    }
         if (txtareacaract.getText().length() < 7) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("caractéristique trop courte");
        alert.setHeaderText(null);
        alert.setContentText("La caractéristique doit contenir au moins 7 caractères.");
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
    // Vérifier que la date est supérieure ou égale à la date actuelle
    if (localDate.isBefore(LocalDate.now())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Date incorrecte");
        alert.setHeaderText(null);
        alert.setContentText("La date doit être supérieure ou égale à la date actuelle.");
        alert.showAndWait();
        return;
    }
        /*txtfieldnom.setText(espace.getNom());
        txtareacaract.setText(espace.getCaracteristique());
        txtfielddispo.setText(espace.getDispo());
        txtfieldadress.setText(espace.getAdresse());
        txtfieldprix.setText(Double.toString(espace.getPrixlocation()));
        imagetxf.setText(espace.getImage());
        datepickertar.setValue(LocalDate.parse(espace.getTarifhoraire().toString())); */
    espace.setNom(txtfieldnom.getText());
    espace.setCaracteristique(txtareacaract.getText());
    espace.setDispo(txtfielddispo.getText());
    espace.setAdresse(txtfieldadress.getText());
    espace.setPrixlocation(Double.parseDouble(txtfieldprix.getText()));
    espace.setImage(imagetxf.getText());
        espace.setCategorieloc(comboboxcat.getValue());
        espace.setTarifhoraire(java.sql.Date.valueOf(datepickertar.getValue()));

       
           
           
   EspaceService esService = new EspaceService();
        try {
            esService.modifierEspace(espace);
            // Afficher une fenêtre de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de modification");
            alert.setHeaderText(null);
            alert.setContentText("Voulez vous confirmer la modification?");
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierEspaceFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Fermer la fenêtre de modification
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
        
     @FXML
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
        if (imagePath.toLowerCase().endsWith(".jpg") || imagePath.toLowerCase().endsWith(".png") || imagePath.toLowerCase().endsWith(".gif")) {
            imagetxf.setText(imagePath);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'ajout");
            alert.setHeaderText(null);
            alert.setContentText("L'image doit être au format JPG, PNG ou GIF !");
            alert.showAndWait();
        }
    }
}
  private void closeWindow() {
    Stage stage = (Stage) anchormodif.getScene().getWindow();
    stage.close();
}
    
}
