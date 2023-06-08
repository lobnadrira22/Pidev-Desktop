package healthconnectjava.GUI;

import healthconnectjava.entities.Aliment;
import healthconnectjava.entities.Objectif;
import healthconnectjava.entities.Routine;
import healthconnectjava.services.AlimentService;
import healthconnectjava.services.ObjectifService;
import healthconnectjava.services.ServiceRoutine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AjouterRoutine {

    @FXML
    private Pane pane;

    @FXML
    private Button Button;

    @FXML
    private Label pointErreur;

    @FXML
    private Label descriptionErreur;

    @FXML
    private Label s;

    @FXML
    private Label s1;

    @FXML
    private Label s3;

    @FXML
    private ComboBox<Aliment> Midi;

    @FXML
    private ComboBox<Objectif> obj;

    @FXML
    private ComboBox<Aliment> Dejeuner;

    @FXML
    private ComboBox<Aliment> Dinner;

    AlimentService AS = new AlimentService();
    ObjectifService OS= new ObjectifService();
    ServiceRoutine RS = new ServiceRoutine();
    Routine r = new Routine();





    public void wsh(){
        List<Aliment> Lista = AS.getall();
        System.out.println(Lista);


        ObservableList<Aliment> observableList = FXCollections.observableList(Lista);

        Dejeuner.setItems(observableList);

    }
    public void wsh1(){
        List<Aliment> Lista = AS.getall();
        System.out.println(Lista);


        ObservableList<Aliment> observableList = FXCollections.observableList(Lista);

        Midi.setItems(observableList);
    }

    public void wsh2(){
        List<Aliment> Lista = AS.getall();
        System.out.println(Lista);


        ObservableList<Aliment> observableList = FXCollections.observableList(Lista);

        Dinner.setItems(observableList);
    }

    public void wsh3(){
        List<Objectif> Lista = OS.getall();
        System.out.println(Lista);


        ObservableList<Objectif> observableList = FXCollections.observableList(Lista);

        obj.setItems(observableList);
    }





    public void ajouter() {

        int Calcons = Dejeuner.getValue().getNbcal() + Midi.getValue().getNbcal() + Dinner.getValue().getNbcal();

        Routine routine = new Routine(Dejeuner.getValue(), Midi.getValue(), Dinner.getValue(), Calcons, obj.getValue());

        if (Dejeuner.getValue().getId() != 0 && Midi.getValue().getId() != 0 && Dinner.getValue().getId() != 0 && obj.getValue().getId() != 0) {
            RS.add(routine);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        }
    }

    public void Back(){

        Pane newLoadedPane = null;
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterRoutine.fxml"));
            newLoadedPane = loader.load();
            ConsulterRCont c = loader.getController();

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }





}
