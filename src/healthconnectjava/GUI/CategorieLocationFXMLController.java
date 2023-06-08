/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.GUI;


import healthconnectjava.entities.CategorieLocation;
import healthconnectjava.services.CategorieLocationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;

import java.sql.SQLException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author lobna
 */
public class CategorieLocationFXMLController implements Initializable {
      @FXML
    private AnchorPane anchorl;
     @FXML
    private Label titrecat;
     @FXML
    private ImageView image;
    @FXML
    private TableView<CategorieLocation> tableviewcat;
    @FXML
    private TableColumn<CategorieLocation,Integer> idcat;

    @FXML
    private TableColumn<CategorieLocation, String> nomcat;

    @FXML
    private TableColumn<CategorieLocation, String> descriptioncat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherListeCategorie();
    }    
    
   private void afficherListeCategorie() {
    CategorieLocationService cat = new CategorieLocationService();
    try {
        // Récupérer la liste des académies depuis le service
        ObservableList<CategorieLocation> categories = FXCollections.observableArrayList(cat.afficherListeC());
idcat.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId())));
      
nomcat.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNom()));
       descriptioncat.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDescription()));
      
        // Afficher les données dans le TableView
        tableviewcat.setItems(categories);
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Afficher une alerte en cas d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de l'affichage de la liste des catégories.");
        alert.show();
    }
}
 
public void redirect(ActionEvent e){
        Pane newLoadedPane = null;
      
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCategorieLocationFXML.fxml"));
            newLoadedPane = loader.load();
            
            
            AjouterCategorieLocationFXMLController a = loader.getController();
           
            
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchorl.getChildren().clear();
        anchorl.getChildren().add(newLoadedPane);
        
        
        

    }

public void redirecttoupdate(ActionEvent e){
        Pane newLoadedPane = null;
      
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCategorieLocationFXML.fxml"));
            newLoadedPane = loader.load();
            
            
            ModifierCategorieLocationFXMLController m = loader.getController();
            m.pass(tableviewcat.getSelectionModel().getSelectedItem());
            
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchorl.getChildren().clear();
        anchorl.getChildren().add(newLoadedPane);
        
        
        

    }
public void redirectespace(ActionEvent e){
        Pane newLoadedPane = null;
      
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherListeEspaceFXML.fxml"));
            newLoadedPane = loader.load();
            
            
            AfficherListeEspaceFXMLController a = loader.getController();
           
            
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchorl.getChildren().clear();
        anchorl.getChildren().add(newLoadedPane);
        
        
        

    }
public void supprimer(ActionEvent e){
        CategorieLocationService ct = new CategorieLocationService();
        ct.supprimercategorieL(tableviewcat.getSelectionModel().getSelectedItem().getId());
        afficherListeCategorie();
    }

}
