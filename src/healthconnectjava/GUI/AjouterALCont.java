package healthconnectjava.GUI;


import healthconnectjava.entities.Aliment;
import healthconnectjava.services.AlimentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.regex.Pattern;

public class AjouterALCont {
    @FXML
    private Pane pane;

    @FXML
    private TextField Nom;

    @FXML
    private TextArea description;

    @FXML
    private Label pointErreur;

    @FXML
    private Label descriptionErreur;

    @FXML
    private javafx.scene.control.Button Button;
    @FXML
    private Label s;

    @FXML
    private TextField NbC;

    AlimentService AS = new AlimentService();


    public void ajouter (ActionEvent e){

        String regex = "^[a-zA-Z]*$";
        String regex2 = "^[1-9][0-9]*$";


        Pattern pattern = Pattern.compile(regex);
        Pattern pattern2 = Pattern.compile(regex2);

        if ( pattern.matcher(Nom.getText()).matches() && pattern2.matcher(NbC.getText()).matches() ) {
            Aliment aa = new Aliment(Nom.getText(),Integer.valueOf(NbC.getText()),description.getText());
            AS.add(aa);









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


            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterAliment.fxml"));
            newLoadedPane = loader.load();
            AlimentController c = loader.getController();

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }















}
