package healthconnectjava.GUI;


import healthconnectjava.GUI.AfficheListeProduitController;
import healthconnectjava.entities.CategorieProduit;
import healthconnectjava.services.CategorieProduitService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AfficheListCategorieController implements Initializable {

    CategorieProduitService categorieProduitService=new CategorieProduitService();

    @FXML
    private TableColumn<CategorieProduit,String> descriptionCategorie;
    @FXML
    private Pane pane;

    @FXML
    private Pane detail;

    @FXML
    private TableColumn<CategorieProduit,Integer> idCategorie;

    @FXML
    private TableColumn<CategorieProduit,String> nomCategorie;

    @FXML
    private TableView<CategorieProduit> table;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }


    public void refresh(){
        {
            try {
                ObservableList<CategorieProduit>categorieProduitsList=
                        FXCollections.observableArrayList(categorieProduitService.afficher());

                idCategorie.setCellValueFactory(cellData ->
                        new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId())));

                nomCategorie.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getNom()));

                descriptionCategorie.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getDescription()));

                table.setItems(categorieProduitsList);

            }catch (SQLException exception){
                System.out.println(exception.getMessage());
            }


        }
    }
    public void produit(){
        Pane newLoadedPane = null;
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheListeProduit.fxml"));
            newLoadedPane = loader.load();
            AfficheListeProduitController c = loader.getController();

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }

    @FXML
    void afficher(MouseEvent event) {

        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherDetailleCategorie.fxml"));
            newLoadedPane = loader.load();
            AfficherDetailleCategorieController c = loader.getController();
            c.categorieData(table.getSelectionModel().getSelectedItem());

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
            System.out.println("aaaa");
        }

        detail.getChildren().clear();
        detail.getChildren().add(newLoadedPane);
    }

    @FXML
    public void ajouter(ActionEvent e) {



        Pane newLoadedPane = null;
        try {

            System.out.println(getClass().getResource("AjoutCategorie.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCategorie.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        detail.getChildren().clear();
        detail.getChildren().add(newLoadedPane);
    }
    
    
    @FXML
    public void modifier(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModiferCategorie.fxml"));
            newLoadedPane = loader.load();
            ModiferCategorieController c = loader.getController();
            c.pass(table.getSelectionModel().getSelectedItem());

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        detail.getChildren().clear();
        detail.getChildren().add(newLoadedPane);
    }
        
    }

