package healthconnectjava.GUI;


import healthconnectjava.entities.CategorieProduit;
import healthconnectjava.services.CategorieProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class ModiferCategorieController {

    @FXML
    private javafx.scene.control.Button Button;

    @FXML
    private TextArea description;

    @FXML
    private TextField nom;

    CategorieProduitService cps=new CategorieProduitService();
    CategorieProduit categorieProduit=new CategorieProduit();

    @FXML
    public void update (ActionEvent e){

        String regex = "^[a-zA-Z]*$";
        //String regex2 = "^[1-9][0-9]*$";


        Pattern pattern = Pattern.compile(regex);

        if ( pattern.matcher(nom.getText()).matches() && pattern.matcher(description.getText()).matches() ) {
            categorieProduit.setNom(nom.getText());
            categorieProduit.setDescription(description.getText());

                cps.modifier(categorieProduit);
            }


        }



        public void pass(CategorieProduit categorieProduit){
        this.categorieProduit=categorieProduit;
        nom.setText(categorieProduit.getNom());
        description.setText((categorieProduit.getDescription()));

    }

}
