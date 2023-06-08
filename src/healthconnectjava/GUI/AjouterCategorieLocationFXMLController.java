/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.GUI;

import healthconnectjava.entities.CategorieLocation;
import healthconnectjava.services.CategorieLocationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author lobna
 */
public class AjouterCategorieLocationFXMLController implements Initializable {
      @FXML
    private AnchorPane anchor;

    @FXML
    private TextArea textareaDesc;

    @FXML
    private Label labelnom;

    @FXML
    private TextField txtfieldNom;

    @FXML
    private Label labeldescription;

    @FXML
    private Button btn;

    @FXML
    private Label titrecatajout;
    @FXML
    private Button revenir;
    
      @FXML
    private ImageView checkdesc;

    @FXML
    private ImageView checknom;

    @FXML
    private Label desclabel;

    @FXML
    private Label labelcheckn;

    /**
     * Initializes the controller class.
     */
    
    
        
    @FXML
    private void verifiernom(KeyEvent event) {
        int nbNonChar = 0;
            String nom = txtfieldNom.getText().trim(); // Enlever les espaces en début et en fin
            for (int i = 0; i < nom.length(); i++) {
                char ch = nom.charAt(i);
                if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) { // Vérifier les espaces aussi
                    nbNonChar++;
                }
            }
        if (nbNonChar == 0 && txtfieldNom.getText().trim().length() >=4) {
            labelcheckn.setText ("nom valide ");
            labelcheckn.setTextFill(Color.GREEN);
            checknom.setImage(new Image("@../../Images/validercheck.png"));


            // verificationUserNom = true;
        } else {
            labelcheckn.setText ("vérifiez le nom !!! ");
            labelcheckn.setTextFill(Color.RED);
            checknom.setImage(new Image("@../../Images/erreurcheck.png"));


        }
    }
     @FXML
    private void verifierdesc(KeyEvent event) {
       int nbNonChar = 0;
            String description = textareaDesc.getText().trim(); // Enlever les espaces en début et en fin
            for (int i = 0; i < description.length(); i++) {
                char ch = description.charAt(i);
                if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) { // Vérifier les espaces aussi
                    nbNonChar++;
                }
            }
        
        if (nbNonChar == 0 && textareaDesc.getText().trim().length() >=7) {
            desclabel.setText ("description valide ");
            desclabel.setTextFill(Color.GREEN);
            checkdesc.setImage(new Image("@../../Images/validercheck.png"));


            // verificationUserNom = true;
        } else {
            desclabel.setText ("vérifiez la description !!! ");
            desclabel.setTextFill(Color.RED);
            checkdesc.setImage(new Image("@../../Images/erreurcheck.png"));


        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
private void ajoutCategorielocation(ActionEvent event) {
    String nom = txtfieldNom.getText();
    String description = textareaDesc.getText();


    
    if (nom.isEmpty() || description.isEmpty()) {
        // Afficher un message d'erreur si les champs sont vides
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.show();
    } else if (description.length() <= 7) {
        // Afficher un message d'erreur si la description est trop courte
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La description doit contenir au moins 8 caractères");
        alert.show();
    } else {
        // Ajouter la catégorie si les champs sont remplis et la description est valide
        CategorieLocation c = new CategorieLocation(nom, description);
        CategorieLocationService service = new CategorieLocationService();
        try {
            service.ajoutCategorielocation(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText("catégorie ajoutée avec succes");
            alert.show();

            txtfieldNom.setText("");
            textareaDesc.setText("");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


          

        
@FXML
    private void redirigerliste(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieLocationFXML.fxml"));
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
}
