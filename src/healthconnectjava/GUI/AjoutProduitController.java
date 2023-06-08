package healthconnectjava.GUI;


import healthconnectjava.entities.CategorieProduit;
import healthconnectjava.entities.Produit;
import healthconnectjava.services.CategorieProduitService;
import healthconnectjava.services.ProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjoutProduitController implements Initializable {





    @FXML
    private ComboBox<CategorieProduit> category;

    @FXML
    private TextField descProduit;


    @FXML
    private ImageView image;

    @FXML
    private Button importImage;


//    @FXML
//    private TextField image;

    @FXML
    private TextField nomProduit;

    @FXML
    private Pane pane;

    CategorieProduitService cps=new CategorieProduitService();

    @FXML
    private TextField prix;
    //private Image imagev;
    private File selectedImageFile;
    Produit produit =new Produit();
    String path=produit.getImage();

    ProduitService produitService=new ProduitService();





    public void inventoryImportBtn() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        selectedImageFile = openFile.showOpenDialog(pane.getScene().getWindow());

        if (selectedImageFile != null) {
            image .setImage(new Image(selectedImageFile.toURI().toString(), 134, 133, false, true));

//            path = file.getAbsolutePath();
//            imagev = new Image(file.toURI().toString(), 134, 133, false, true);
//
//            image.setImage(imagev);
        }
    }


    @FXML
    void ajouter(ActionEvent event) {
        //Recupere les valeurs des champs
        String nom=nomProduit.getText();
        String desc=descProduit.getText();
       // String img=image.getText();
        CategorieProduit nomCategorie=category.getValue();
        Float prixProduit=Float.parseFloat(prix.getText());

        if (nom.isEmpty() || desc.isEmpty() || prixProduit.isNaN()) {
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
        if (selectedImageFile == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir une image !");
            alert.showAndWait();
            return;
        }


        String imagePath = selectedImageFile.toString();
        Produit produit = new Produit(nomProduit.getText(),descProduit.getText(),imagePath,Float.parseFloat(prix.getText()),category.getValue());
        System.out.println(produit);
        produitService.ajouter(produit);

        // Affichage d'un message de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Produit ajouté avec succès !");
        alert.showAndWait();


        // Effacement des champs après ajout
        nomProduit.setText("");
        descProduit.setText("");
        //image.setText("");
        prix.setText("");
        category.setValue(null); // Effacement de la sélection dans le ComboBox


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            category.getItems().addAll(cps.afficher());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
