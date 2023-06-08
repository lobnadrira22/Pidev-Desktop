package healthconnectjava.GUI;

import healthconnectjava.entities.Objectif;
import healthconnectjava.services.ObjectifService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.Initializable;


public class ObjectifFront implements Initializable{


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










    public void ajouter (ActionEvent e){

        String regex = "^[a-zA-Z]*$";
        String regex2 = "^[1-9][0-9]*$";


        Pattern pattern = Pattern.compile(regex);
        Pattern pattern2 = Pattern.compile(regex2);
        double calorie;

        if (TR.getValue()==1){
            if (sexe.getValue()=="Male"){
                 calorie =(Integer.valueOf(Poids.getText()))*13.4+(4.8*Integer.valueOf(taille.getText()))+(Integer.valueOf(age.getText())*5.7)+ 88.36-600 ;
            }else

             calorie =(Integer.valueOf(Poids.getText()))*9.2+(3.1*Integer.valueOf(taille.getText()))+(Integer.valueOf(age.getText())*4.3)+  447.6-600 ;

        } else {
            if (sexe.getValue()=="Male"){
                calorie =(Integer.valueOf(Poids.getText()))*13.4+(4.8*Integer.valueOf(taille.getText()))+(Integer.valueOf(age.getText())*5.7)+ 88.36-600 ;



            }else
                calorie =(Integer.valueOf(Poids.getText()))*9.2+(3.1*Integer.valueOf(taille.getText()))+(Integer.valueOf(age.getText())*4.3)+  447.6-600 ;
        }



        Objectif objectif=new Objectif(Integer.valueOf(PoidsDeS.getText()),TR.getValue(), (int)calorie,Integer.valueOf(age.getText()), sexe.getValue(),Integer.valueOf(Poids.getText()), Integer.valueOf(taille.getText()));

        if ( pattern2.matcher(PoidsDeS.getText()).matches()  && TR.getValue() !=0 && pattern2.matcher(age.getText()).matches() && sexe.getValue() !="" && pattern2.matcher(Poids.getText()).matches() && pattern2.matcher(taille.getText()).matches()    )
        {
            OS.add(objectif);
            System.out.println("c bon");
        } else {
            System.out.println("ghalta");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TR.getItems().addAll(1,2);
        sexe.getItems().addAll("Male","Female");




    }
}
