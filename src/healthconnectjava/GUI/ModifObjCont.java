package healthconnectjava.GUI;

import healthconnectjava.entities.Objectif;
import healthconnectjava.services.ObjectifService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ModifObjCont  {



    @FXML
    private Button Button;

    @FXML
    private Label pointErreur;

    @FXML
    private Label descriptionErreur;

    @FXML
    private TextField PoidsDeS;

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


    @FXML
    private AnchorPane pane;



    ObjectifService OS = new ObjectifService();
    Objectif objectif = new Objectif();






    public void passer(Objectif objectif){
        this.objectif=objectif;
        PoidsDeS.setText(Integer.toString(objectif.getPoidsDes()));
        CalCons.setText(Integer.toString(objectif.getCalCons()));
        age.setText(Integer.toString(objectif.getAge()));
        Poids.setText(Integer.toString(objectif.getPoids()));
        taille.setText(Integer.toString(objectif.getTaille()));


        TR.getItems().setAll(1,2);
        sexe.getItems().setAll("M","F");

    }


    public void update(ActionEvent e){

        String regex = "^[a-zA-Z]*$";
        String regex2 = "^[0-9][0-9]*$";


        Pattern pattern = Pattern.compile(regex);
        Pattern pattern2 = Pattern.compile(regex2);

        if (pattern2.matcher(PoidsDeS.getText()).matches() && pattern2.matcher(CalCons.getText()).matches() && TR.getValue() !=0 && pattern2.matcher(age.getText()).matches() && sexe.getValue() !="" && pattern2.matcher(Poids.getText()).matches() && pattern2.matcher(taille.getText()).matches() )
        {

            objectif.setCalCons(Integer.valueOf(CalCons.getText()));
            objectif.setPoids(Integer.valueOf(Poids.getText()));
            objectif.setPoidsDes(Integer.valueOf(PoidsDeS.getText()));
            objectif.setAge(Integer.valueOf(age.getText()));
            objectif.setTypeReg(TR.getValue());
            objectif.setSexe(sexe.getValue());
            objectif.setTaille(Integer.valueOf(taille.getText()));



            OS.modify(objectif);
        } else
        { Alert alert = new Alert(Alert.AlertType.ERROR);

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
