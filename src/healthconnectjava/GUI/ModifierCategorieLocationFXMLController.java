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
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lobna
 */
public class ModifierCategorieLocationFXMLController implements Initializable {
 ;
@FXML
    private AnchorPane anchorm;

    @FXML
    private Label labelmod;

    @FXML
    private Label labeldescc;

    @FXML
    private TextField txtfieldNommod;

    @FXML
    private TextArea textareaDescmod;

    @FXML
    private Button btnmod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    


    CategorieLocationService cps=new CategorieLocationService();
    CategorieLocation categorieloc=new CategorieLocation();

    @FXML
    public void modifier (ActionEvent e){
        
if (txtfieldNommod.getText().isEmpty() || textareaDescmod.getText().isEmpty() )

{
        // Afficher une alerte si l'un des champs est vide
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.show();
        return;
    }

 categorieloc.setNom(txtfieldNommod.getText());
    categorieloc.setDescription(textareaDescmod.getText());
  

    cps.modifiercategorieL(categorieloc);


        }




 public void pass(CategorieLocation categorieloc){
        this.categorieloc=categorieloc;
        txtfieldNommod.setText(categorieloc.getNom());
        textareaDescmod.setText((categorieloc.getDescription()));

    }

@FXML
    private void redirigerlistec(ActionEvent event) {
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

    
    

