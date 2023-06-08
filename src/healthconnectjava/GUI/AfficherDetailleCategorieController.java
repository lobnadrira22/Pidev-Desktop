package healthconnectjava.GUI;


import healthconnectjava.entities.CategorieProduit;
import healthconnectjava.services.CategorieProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AfficherDetailleCategorieController {


    @FXML
    private Label descCategorie;

    @FXML
    private Label idCategorie;

    @FXML
    private Label nomCategorie;

    @FXML
    private Pane pane;



    CategorieProduit categorieProduit;

    public void categorieData(CategorieProduit categorieProduit){
        System.out.println(categorieProduit);


        this.categorieProduit=categorieProduit;
        idCategorie.setText(Integer.toString(categorieProduit.getId()));
        nomCategorie.setText(categorieProduit.getNom());
        descCategorie.setText(categorieProduit.getDescription());

    }

    @FXML
    void supprimerCategorie(ActionEvent e) {

        Pane newLoadedPane =null;
        CategorieProduitService categorieProduitService=new CategorieProduitService();
        categorieProduitService.supprimer(categorieProduit.getId());

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("supprimerAvecSucces.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }
}
