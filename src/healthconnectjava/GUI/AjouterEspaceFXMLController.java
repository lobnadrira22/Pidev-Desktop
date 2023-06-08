/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.GUI;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import healthconnectjava.entities.CategorieLocation;
import healthconnectjava.entities.Espace;
import healthconnectjava.services.CategorieLocationService;
import healthconnectjava.services.Emailsender;
import healthconnectjava.services.EspaceService;

/**
 * FXML Controller class
 *
 * @author lobna
 */
public class AjouterEspaceFXMLController implements Initializable {
    private File selectedImageFile;
    @FXML
    private AnchorPane anchorajes;

    @FXML
    private Label labelajoutes;

    @FXML
    private TextField textfieldnom;



    @FXML
    private TextField textfieldadress;

    @FXML
    private ImageView imgviewes;

   

    @FXML
    private DatePicker datepickertarif;

    @FXML
    private TextField textfieldprix;

    @FXML
    private ComboBox<CategorieLocation> comboboxcat;

    @FXML
    private Label labelnomes;

    @FXML
    private Label labelcaract;

    @FXML
    private Label labelimg;

    @FXML
    private Label labeladresse;

    @FXML
    private Label labeldispo;

    @FXML
    private Label labeltarif;

    @FXML
    private Label labelprix;

    @FXML
    private Label labelcat;
    @FXML
    private Button btna;
    @FXML
    private TextField imagetf;

    @FXML
    private TextArea textareacarac;
     @FXML
    private TextField dispo;
       @FXML
    private ImageView imagedatecheck;
       
       @FXML
    private Label checkdatetxt;
     
    


    /**
     * Initializes the controller class.
     */
      CategorieLocationService ct = new CategorieLocationService();
      EspaceService es = new EspaceService();
      @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
       comboboxcat.getItems().addAll(ct.afficherListeC());
       } catch(SQLException e){
           e.printStackTrace();
       }
    }  
    
  
      
    @FXML
private void ajoutEspace(ActionEvent event) {
    LocalDate localDate = datepickertarif.getValue();
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
    String image = imagetf.getText();

    if (textfieldnom.getText().isEmpty() || dispo.getText().isEmpty() || textfieldadress.getText().isEmpty()
            || textfieldprix.getText().isEmpty()  || textareacarac.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }

    if (comboboxcat.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir la catégorie de l'espace.");
        alert.showAndWait();
        return;
    }

    if (textareacarac.getText().length() < 7) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("caractéristique trop courte");
        alert.setHeaderText(null);
        alert.setContentText("La caractéristique doit contenir au moins 7 caractères.");
        alert.showAndWait();
        return;
    }
if (textfieldprix.getText().isEmpty() || Double.parseDouble(textfieldprix.getText()) <= 0) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Champ de prix invalide");
    alert.setHeaderText(null);
    alert.setContentText("Le prix doit être supérieur à 0.");
    alert.showAndWait();
    return;
}


    // Vérifier que l'image est au format jpg ou png
   if (selectedImageFile == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir une image !");
            alert.showAndWait();
            return;
        }


        String imagePath = selectedImageFile.toString();

    // Vérifier que la date est supérieure ou égale à la date actuelle
    if (localDate.isBefore(LocalDate.now())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Date incorrecte");
        alert.setHeaderText(null);
        alert.setContentText("La date doit être supérieure ou égale à la date actuelle.");
        alert.showAndWait();
        return;
    }

    Espace espace = new Espace(textfieldnom.getText(), textareacarac.getText(), imagePath,
            textfieldadress.getText(), dispo.getText(), date, Double.parseDouble(textfieldprix.getText()),
            comboboxcat.getValue());
    
    
    String message = "Bienvenue à HealthConnect \n"
                        + "\n"
                        + "le nouvel espace a été ajouté au site\n"
                        + "vous pouvez consulter le prix de location, l'adresse et la date de location de l'espace"
                        + "\n"
                        +  "le nom  : " + espace.getNom()  + "\n"
                     
                        + "l'adresse  : " + espace.getAdresse()  + "\n"
                        + "le prix de location de l'espace  : " + espace.getPrixlocation() + "\n"
                        + "à la date  : " + espace.getTarifhoraire()  + "\n"
                       
                        //+ "We are pleased to inform you that your reservation has been successfully processed, and we have reserved the required number of seats for you. Your confirmation number is [Enter confirmation number].\n"
                        + "\n";

                Emailsender.sendEmail_add("lobna.drira@esprit.tn", message);
   /* Notifications notificationBuilder = Notifications.create()
    .title("espace ajouté avec succés ")
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
notificationBuilder.showInformation(); */
   
    try {
        es.ajoutEspace(espace);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Espace ajouté avec succès");
        alert.show();

        textfieldnom.setText("");
        textfieldadress.setText("");
        textareacarac.setText("");
        textfieldprix.setText("");
        imagetf.setText("");
        datepickertarif.setValue(null);
    } catch (SQLException ex) {
        Logger.getLogger(AjouterEspaceFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
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
            imagetf.setText(imagePath);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'ajout");
            alert.setHeaderText(null);
            alert.setContentText("L'image doit être au format JPG, PNG ou GIF !");
            alert.showAndWait();
        }
    }
}
 
  @FXML
    private void redirigerliste(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherListeEspaceFXML.fxml"));
    Parent root;
    try {
    root = loader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    
    
       public void filechooseImage() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        selectedImageFile = openFile.showOpenDialog(anchorajes.getScene().getWindow());

        if (selectedImageFile != null) {
            imgviewes.setImage(new Image(selectedImageFile.toURI().toString(), 134, 133, false, true));

//            path = file.getAbsolutePath();
//            imagev = new Image(file.toURI().toString(), 134, 133, false, true);
//
//            image.setImage(imagev);
        }
    }
 
}
    

    
    