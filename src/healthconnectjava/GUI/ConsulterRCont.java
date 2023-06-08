package healthconnectjava.GUI;


import healthconnectjava.entities.Routine;
import healthconnectjava.services.ServiceRoutine;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ConsulterRCont implements Initializable {

    ServiceRoutine RS = new ServiceRoutine();

    @FXML
    private TableView<Routine> T1;


    @FXML
    private TextField search;

    @FXML
    private TableColumn<Routine, Integer> id;

    @FXML
    private TableColumn<Routine, Integer> Objectif;

    @FXML
    private TableColumn<Routine, Integer> Dejeuner;

    @FXML
    private TableColumn<Routine, Integer> Midi;

    @FXML
    private TableColumn<Routine, Integer> Dinner;

    @FXML
    private TableColumn<Routine, Integer> CaloriesConsommés;

    @FXML
    private TableColumn<Routine, Date> Date;
    @FXML
    private AnchorPane N1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Display();
        recherchefiltre();
    }



    public void Display(){

        ObservableList<Routine> ListaRoutine = FXCollections.observableArrayList(this.RS.getall());



        id.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId())));

        Objectif.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getIdObj().getAge())));


        Dejeuner.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId_dejeuner().getId())));
        Midi.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId_midi().getId())));
        Dinner.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId_dinner().getId())));
        CaloriesConsommés.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getCalCons())));

        Date.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Date>(cellData.getValue().getDate()));

        T1.setItems(ListaRoutine);

    }

    public void supprimer(ActionEvent e){

        RS.delete(T1.getSelectionModel().getSelectedItem().getId());
        Display();
    }


    public void Back(){

        Pane newLoadedPane = null;
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListClientForAdminFXML.fxml"));
            newLoadedPane = loader.load();
            ListClientForAdminFXMLController c = loader.getController();

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterRoutine.fxml"));
            newLoadedPane = loader.load();
            AjouterRoutine c = loader.getController();

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        N1.getChildren().clear();
        N1.getChildren().add(newLoadedPane);
    }


    public void Modifiy(){
        Pane newLoadedPane = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifR.fxml"));
            newLoadedPane = loader.load();
            ModifR c = loader.getController();
            c.passer(T1.getSelectionModel().getSelectedItem());


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        N1.getChildren().clear();
        N1.getChildren().add(newLoadedPane);
    }


    @FXML
    private void recherchefiltre() {
        ObservableList<Routine> espaces = T1.getItems();
        FilteredList<Routine> filterData = new FilteredList(espaces, e->true);
        search.setOnKeyReleased(e->{
            search.textProperty().addListener((observable,oldValue,newValue)->{
                filterData.setPredicate((Predicate<? super Routine >) esp->{
                    if(newValue==null){
                        return true;
                    }

                    if (Integer.toString(esp.getId()).contains(newValue)){
                        return true;
                    } else if(Integer.toString(esp.getCalCons()).contains(newValue)) {
                        return true;
                        //   } else if (esp.getNbcal().toString().contains(newValue)) {
                        //     return true;
                    } else if(Integer.toString(esp.getId_dejeuner().getId()).contains(newValue)){
                        return true;
                        //   } else if (esp.getPrixlocation().toString().contains(newValue)) {
                        //   return true;
                    } else if(Integer.toString(esp.getId_midi().getId()).contains(newValue)){
                        return true;
                        //   } else if (esp.getPrixlocation().toString().contains(newValue)) {
                        //   return true;
                    } else if(Integer.toString(esp.getId_dinner().getId()).contains(newValue)){
                        return true;
                        //   } else if (esp.getPrixlocation().toString().contains(newValue)) {
                        //   return true;
                    } else if(Integer.toString(esp.getIdObj().getId()).contains(newValue)){
                        return true;
                        //   } else if (esp.getPrixlocation().toString().contains(newValue)) {
                        //   return true;
                    }
                    return false;
                });
            });
            final SortedList<Routine> esps = new SortedList<Routine>(filterData);
            esps.comparatorProperty().bind(T1.comparatorProperty());
            T1.setItems(esps);
        });
    }




















}
