package healthconnectjava.GUI;


import healthconnectjava.entities.CategorieProduit;
import healthconnectjava.entities.Produit;
import healthconnectjava.services.CategorieProduitService;
import healthconnectjava.services.ProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ModifierProduitConntroller implements Initializable {




    @FXML
    private ComboBox<CategorieProduit> category;

    @FXML
    private TextField descProduit;

    @FXML
    private TextField image;

    @FXML
    private TextField nomProduit;

    @FXML
    private Pane pane;

    @FXML
    private TextField prix;

    ProduitService produitService=new ProduitService();
    CategorieProduitService cps=new CategorieProduitService();
    CategorieProduit categorieProduit=new CategorieProduit();
    Produit produit=new Produit();
    @FXML
    void modifier(ActionEvent event) {
       // String regex = "^[a-zA-Z]*$";
        //String regex2 = "^[1-9][0-9]*$";
        //Pattern pattern = Pattern.compile(regex);
       // if (pattern.matcher(nomProduit.getText()).matches() && pattern.matcher(descProduit.getText()).matches()
               // && pattern.matcher(image.getText()).matches() && pattern.matcher(prix.getText()).matches() && pattern.matcher(category.getValue().getNom()).matches()) {


        if (nomProduit.getText().isEmpty() || descProduit.getText().isEmpty() || image.getText().isEmpty()
                || category.getValue() == null) {
            // Afficher une alerte si l'un des champs est vide
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

            produit.setNom(nomProduit.getText());
            produit.setDescription(descProduit.getText());
            produit.setCategory(category.getValue());
            produit.setImage(image.getText());
            produitService.modifier(produit);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modification");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous confirmer la modification?");
        alert.showAndWait();


        }



    public void pass(Produit produit){
        this.produit=produit;
        nomProduit.setText(produit.getNom());
        descProduit.setText(produit.getDescription());
        image.setText(produit.getImage());
        prix.setText(String.valueOf(produit.getPrix()));
        category.setValue(produit.getCategory());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            category.getItems().addAll(cps.afficher());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

