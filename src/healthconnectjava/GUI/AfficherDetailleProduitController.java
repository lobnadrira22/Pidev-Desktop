package healthconnectjava.GUI;


import healthconnectjava.entities.Produit;
import healthconnectjava.services.ProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AfficherDetailleProduitController {
    @FXML
    private Label categorie;

    @FXML
    private Label descProduit;

    @FXML
    private Label idproduit;

    @FXML
    private Label image;

    @FXML
    private Label nomProduit;

    @FXML
    private Pane pane;

    @FXML
    private Label prix;

    Produit produit;

    public void ProduitData(Produit produit){
        System.out.println(produit);


        this.produit=produit;
        idproduit.setText(Integer.toString(produit.getId()));
        nomProduit.setText(produit.getNom());
        descProduit.setText(produit.getDescription());
        image.setText(produit.getImage());
        prix.setText(Float.toString(produit.getPrix()));
        categorie.setText(produit.getCategory().getNom());


    }

    @FXML
    void supprimerProduit(ActionEvent event) {
        Pane newLoadedPane =null;
        ProduitService produitService=new ProduitService();
        produitService.supprimer(produit.getId());

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


