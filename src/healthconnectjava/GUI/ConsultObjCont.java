package healthconnectjava.GUI;


import healthconnectjava.entities.Objectif;
import healthconnectjava.services.ObjectifService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ConsultObjCont implements Initializable {
    ObjectifService OS = new ObjectifService();

    @FXML
    private AnchorPane N1;


    @FXML
    private TableView<Objectif> T1;

    @FXML
    private TableColumn<Objectif, Integer> id;

    @FXML
    private TableColumn<Objectif, Integer> PoidsDes;

    @FXML
    private TableColumn<Objectif, Integer> TR;

    @FXML
    private TableColumn<Objectif, Integer> CalCons;

    @FXML
    private TableColumn<Objectif, Integer> Age;

    @FXML
    private TableColumn<Objectif, String> Sexe;

    @FXML
    private TableColumn<Objectif, Integer> Poids;

    @FXML
    private TableColumn<Objectif, Integer> Taille;

    @FXML
    private TextField search;

    @FXML
    private Button Supprimer;

    @FXML
    private Button Modif;

    @FXML
    private Button Ajout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayObjectif();
        recherchefiltre();

    }



    @FXML
    private void DisplayObjectif() {

        ObservableList<Objectif> ListaObjectif = FXCollections.observableArrayList(this.OS.getall());
        System.out.println(OS.getall());



        id.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId())));


        PoidsDes.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getPoidsDes())));

        TR.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getTypeReg())));
        CalCons.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getCalCons())));

        Age.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getAge())));

        Poids.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getPoids())));
        Taille.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getTaille())));




        Sexe.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getSexe()));




        T1.setItems(ListaObjectif);

    }

    public void supprimer(ActionEvent e){

        OS.delete(T1.getSelectionModel().getSelectedItem().getId());
        DisplayObjectif();

    }

    @FXML
    private void recherchefiltre() {
        ObservableList<Objectif> espaces = T1.getItems();
        FilteredList<Objectif> filterData = new FilteredList(espaces, e->true);
        search.setOnKeyReleased(e->{
            search.textProperty().addListener((observable,oldValue,newValue)->{
                filterData.setPredicate((Predicate<? super Objectif >) esp->{
                    if(newValue==null){
                        return true;
                    }
                    String toLowerCaseFilter = newValue.toLowerCase();
                    if(Integer.toString(esp.getTaille()).contains(newValue)){
                        return true;
                    } else if(Integer.toString(esp.getCalCons()).contains(newValue)) {
                        return true;
                        //   } else if (esp.getNbcal().toString().contains(newValue)) {
                        //     return true;
                    } else if(Integer.toString(esp.getPoids()).contains(newValue)){
                        return true;

                    } else if(esp.getSexe().toLowerCase().contains(toLowerCaseFilter)){
                        return true;

                    }
                    return false;
                });
            });
            final SortedList<Objectif> esps = new SortedList<Objectif>(filterData);
            esps.comparatorProperty().bind(T1.comparatorProperty());
            T1.setItems(esps);
        });
    }



    public void Transit(ActionEvent e){
        Pane newLoadedPane = null;
        try {
            System.out.println(getClass().getResource("ModifObj.fxml"));



            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifObj.fxml"));

            newLoadedPane = loader.load();
            ModifObjCont c = loader.getController();
            c.passer(T1.getSelectionModel().getSelectedItem());




        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        N1.getChildren().clear();
        N1.getChildren().add(newLoadedPane);

    }


    public void Back(){

        Pane newLoadedPane = null;
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCoachForAdminFXML.fxml"));
            newLoadedPane = loader.load();
            ListCoachForAdminFXMLController c = loader.getController();

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        N1.getChildren().clear();
        N1.getChildren().add(newLoadedPane);
    }


    public void Add(){
        Pane newLoadedPane = null;
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterObj.fxml"));
            newLoadedPane = loader.load();
            AjouterObjCont c = loader.getController();

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        N1.getChildren().clear();
        N1.getChildren().add(newLoadedPane);
    }
















}
