/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package healthconnectjava.GUI;

import healthconnectjava.entities.Coach;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author wiki
 */
public class CardCoachFXMLController implements Initializable {

    @FXML
    private HBox box;
    @FXML
    private ImageView image;
    @FXML
    private Text nomPre;
    @FXML
    private Text email;
    @FXML
    private Text numTel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void setData(Coach coach) {
        
        Image imagerx = new Image("http://127.0.0.1:8000/Client_img/"+coach.getImage());
        image.setImage(imagerx);
        nomPre.setText(coach.getNom()+" "+coach.getPrenom());
        email.setText(coach.getEmail());
        numTel.setText(coach.getTelephone());
        
    }   
    
}
