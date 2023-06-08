package healthconnectjava.GUI;


import com.stripe.exception.StripeException;
import healthconnectjava.services.Emailsender_2;
import healthconnectjava.services.ServicePaymentStripe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentController implements Initializable {



    ServicePaymentStripe servicePayment;
    int prix;
    @FXML
    private TextField numCardField;

    private boolean payed = false;
    @FXML
    private TextField expMoisField;
    @FXML
    private TextField expanneeField;
    @FXML
    private TextField cvvField;
    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addTextLimiter(expMoisField, 2);
        addTextLimiter(expanneeField, 4);
        addTextLimiter(cvvField, 3);

    }

    @FXML
    private void Payer(ActionEvent event) {
        String nom = "youssef";
        String email = "youssef.benlahouel@esprit.tn";
        String numCard = numCardField.getText();
        String expMois = expMoisField.getText();
        String exAnnee = expanneeField.getText();
        String cvv = cvvField.getText();
//        BilletAjoutModifyController billetAjoutModifyController = null;

        servicePayment = new ServicePaymentStripe(email, nom, 250, numCard, expMois, exAnnee, cvv);
        // Récupérer la valeur de l'e-mail du champ de saisie

        String message = "Health Connect \n"
                + "\n"
                + "Votre paiement a  ete  effectué :\n"
                + "\n"
                +  "le nom  : " + nom  + "\n"

                //+ "We are pleased to inform you that your reservation has been successfully processed, and we have reserved the required number of seats for you. Your confirmation number is [Enter confirmation number].\n"
                + "\n";

        //  Emailsender.sendEmail_add("lina.fakhfakh@esprit.tn", message);
        Emailsender_2.sendEmail_add("youssefbenlahouel@esprit.tn",message);

        try {
            servicePayment.payer();

            FXMLLoader fxmlLoader = new FXMLLoader();
//            Pane p = fxmlLoader.load(getClass().getResource("BilletAjoutModify.fxml").openStream());
//            billetAjoutModifyController = fxmlLoader.getController();
            payed = true;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("valid payment");
            alert.show();

        } catch (StripeException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("error payment");
            alert.show();

        }

    }
    public void back(){
        Pane newLoadedPane = null;
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ProduitFront.fxml"));
            newLoadedPane = loader.load();
            ProduitFrontController c = loader.getController();

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }

    public boolean getReturn() {
        return payed; //return what you want controlled by your controller class
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

}
