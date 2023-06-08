package healthconnectjava.GUI;


import healthconnectjava.entities.CategorieProduit;
import healthconnectjava.services.CategorieProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class AjoutCategorieController {

    @FXML
    private javafx.scene.control.Button Button;

    @FXML
    private TextField Nom;

    @FXML
    private TextArea description;

    @FXML
    private Pane pane;

    CategorieProduitService cps=new CategorieProduitService();

    public void ajouter (ActionEvent e){

        //String regex = "^[a-zA-Z]*$";
        //String regex2 = "^[1-9][0-9]*$";
        //Pattern pattern = Pattern.compile(regex);
        //if ( pattern.matcher(Nom.getText()).matches() && pattern.matcher(description.getText()).matches() ) {
            CategorieProduit categorieProduit=new CategorieProduit(Nom.getText(),description.getText());
            try {
                String nom=Nom.getText();
                String desc=description.getText();

                if (nom.isEmpty() || desc.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir tous les champs !");
                    alert.showAndWait();
                    return;
                }
                if (nom == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez choisir nom de Produit  !");
                    alert.showAndWait();
                    return;
                }
                if (desc.length() < 20) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("La description doit contenir au moins 20 caractères !");
                    alert.showAndWait();
                    return;
                }

                cps.ajouter(categorieProduit);
                // Affichage d'un message de succès
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Categorie ajouté avec succès !");
                alert.showAndWait();

                Nom.setText("");
                description.setText("");


    } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }
    }




