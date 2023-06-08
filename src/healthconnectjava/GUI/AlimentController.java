package healthconnectjava.GUI;


import healthconnectjava.entities.Aliment;
import healthconnectjava.services.AlimentService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AlimentController implements Initializable {

    AlimentService as = new AlimentService();

    @FXML
    private Pane N1;

    @FXML
    private TableView<Aliment> T1;

    @FXML
    private TableColumn<Aliment, Integer> dd;

    @FXML
    private TableColumn<Aliment, Integer> NbCal;

    @FXML
    private TableColumn<Aliment, String> nn;

    @FXML
    private TextField search;

    @FXML
    private TableColumn<Aliment, String> dcr;
    AlimentService AS = new AlimentService();




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AfficherListeAliment();
        recherchefiltre();

    }

    @FXML
    private void recherchefiltre() {
        ObservableList<Aliment> espaces = T1.getItems();
        FilteredList<Aliment> filterData = new FilteredList(espaces, e->true);
        search.setOnKeyReleased(e->{
            search.textProperty().addListener((observable,oldValue,newValue)->{
                filterData.setPredicate((Predicate<? super Aliment >) esp->{
                    if(newValue==null){
                        return true;
                    }
                    String toLowerCaseFilter = newValue.toLowerCase();
                    if(esp.getNom().contains(newValue)){
                        return true;
                    } else if(Integer.toString(esp.getNbcal()).contains(newValue)) {
                        return true;
                 //   } else if (esp.getNbcal().toString().contains(newValue)) {
                   //     return true;
                    } else if(esp.getDesc().toLowerCase().contains(toLowerCaseFilter)){
                        return true;
                 //   } else if (esp.getPrixlocation().toString().contains(newValue)) {
                     //   return true;
                    }
                    return false;
                });
            });
            final SortedList<Aliment> esps = new SortedList<>(filterData);
            esps.comparatorProperty().bind(T1.comparatorProperty());
            T1.setItems(esps);
        });
    }




    @FXML
    private void AfficherListeAliment() {

        ObservableList<Aliment> ListaAliment = FXCollections.observableArrayList(this.as.getall()        );
        System.out.println(as.getall());



        dd.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId())));


        NbCal.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getNbcal())));


        nn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNom()));

        dcr.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDesc()));


        T1.setItems(ListaAliment);

    }


    public void supprimer(ActionEvent e){

        AS.delete(T1.getSelectionModel().getSelectedItem().getId());
        AfficherListeAliment();


    }


    public void Transit(ActionEvent e){
        Pane newLoadedPane = null;
        try {

            System.out.println(getClass().getResource("/GUI/ModifAliment.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifAliment.fxml"));
            newLoadedPane = loader.load();
            ModifAlCont c = loader.getController();
            c.pass(T1.getSelectionModel().getSelectedItem());

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


            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilCoachFXML.fxml"));
            newLoadedPane = loader.load();
            ProfilCoachFXMLController c = loader.getController();

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


            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterAliment.fxml"));
            newLoadedPane = loader.load();
            AjouterALCont c = loader.getController();

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        N1.getChildren().clear();
        N1.getChildren().add(newLoadedPane);
    }
















}
