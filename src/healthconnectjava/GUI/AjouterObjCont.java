package healthconnectjava.GUI;


import healthconnectjava.entities.Objectif;
import healthconnectjava.services.ObjectifService;
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
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class AjouterObjCont implements Initializable {






    @FXML
    private Pane pane;

    @FXML
    private Button Button;

    @FXML
    private TextField PoidsDeS;

    @FXML
    private Label pointErreur;

    @FXML
    private Label descriptionErreur;

    @FXML
    private Label s;

    @FXML
    private Label s1;

    @FXML
    private TextField CalCons;

    @FXML
    private Label s2;

    @FXML
    private Label s3;

    @FXML
    private Label s4;

    @FXML
    private TextField Poids;

    @FXML
    private Label s5;

    @FXML
    private TextField taille;

    @FXML
    private ComboBox<Integer> TR;

    @FXML
    private ComboBox<String> sexe;

    @FXML
    private TextField age;

    ObjectifService OS = new ObjectifService();









    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TR.getItems().addAll(1,2);
        sexe.getItems().addAll("Male","Female");



    }


    public void ajouter (ActionEvent e){

        String regex = "^[a-zA-Z]*$";
        String regex2 = "^[1-9][0-9]*$";


        Pattern pattern = Pattern.compile(regex);
        Pattern pattern2 = Pattern.compile(regex2);

        Objectif objectif=new Objectif(Integer.valueOf(PoidsDeS.getText()),TR.getValue(), Integer.valueOf(CalCons.getText()),Integer.valueOf(age.getText()), sexe.getValue(),Integer.valueOf(Poids.getText()), Integer.valueOf(taille.getText()));

        if ( pattern2.matcher(PoidsDeS.getText()).matches() && pattern2.matcher(CalCons.getText()).matches() && TR.getValue() !=0 && pattern2.matcher(age.getText()).matches() && sexe.getValue() !="" && pattern2.matcher(Poids.getText()).matches() && pattern2.matcher(taille.getText()).matches()    )
        {
            OS.add(objectif);
            System.out.println("c bon");
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


            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCoachForAdminFXML.fxml"));
            newLoadedPane = loader.load();
            ListCoachForAdminFXMLController c = loader.getController();

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }

}
