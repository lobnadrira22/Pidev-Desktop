package healthconnectjava.GUI;


import healthconnectjava.entities.Aliment;
import healthconnectjava.services.AlimentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.regex.Pattern;

public class ModifAlCont {


    @FXML
    private TextField Nom;

    @FXML
    private TextArea description;
    @FXML
    private AnchorPane pane;





    @FXML
    private TextField NbC;


    AlimentService AS = new AlimentService();
    Aliment aliment=new Aliment();




    public void pass(Aliment aliment){
        this.aliment=aliment;
      Nom.setText(aliment.getNom());
      description.setText((aliment.getDesc()));
      NbC.setText(Integer.toString(aliment.getNbcal()));
    }




    public void update(ActionEvent e){

        String regex = "^[a-zA-Z]*$";
        String regex2 = "^[1-9][0-9]*$";


        Pattern pattern = Pattern.compile(regex);
        Pattern pattern2 = Pattern.compile(regex2);

        if ( pattern.matcher(Nom.getText()).matches() && pattern2.matcher(NbC.getText()).matches() )
        {
            aliment.setNom(Nom.getText());
            aliment.setDesc(description.getText());
            aliment.setNbcal(Integer.valueOf(NbC.getText()));

            AS.modify(aliment);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();}

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
