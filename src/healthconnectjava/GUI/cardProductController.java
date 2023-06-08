package healthconnectjava.GUI;


import healthconnectjava.entities.Produit;
import healthconnectjava.utils.MyDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class cardProductController implements Initializable {




    @FXML
    private AnchorPane card_form;

    @FXML
    private Button prod_addBtn;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;

    private double totalP,pr;

    @FXML
    private Spinner<Integer> prod_spinner;

    private SpinnerValueFactory<Integer> spin;


    private Produit prodData;
    public Connection conx;
    private Image image;
    private int qty;
    private Alert alert;
    private String prod_image;
    ProduitFrontController ps=new ProduitFrontController();


    public cardProductController(){
        conx= MyDB.getInstance().getConx();

    }


//    public void setData(Produit prodData){
//        this.prodData=prodData;
//        prod_name.setText(prodData.getNom());
//        prod_price.setText(String.valueOf(prodData.getPrix()));
//        prod_image=prodData.getImage();
//        String path ="File: "+ prodData.getImage() ;
//        System.out.println(path);
//        image=new Image(path,190,94,false,true);
//        prod_imageView.setImage(image);
//        pr=prodData.getPrix();
//    }
public void setData(Produit prodData) {
    this.prodData = prodData;
    prod_image = prodData.getImage();
    prod_name.setText(prodData.getNom());
    prod_price.setText(String.valueOf(prodData.getPrix()));
    String path = "File:" + prodData.getImage();
    image = new Image(path, 190, 94, false, true);
    prod_imageView.setImage(image);
    pr = prodData.getPrix();

}

    public void setQuantite(){
        spin=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0); //a spinner that has a range of integers from 0 to 100, and an initial value of 0
        prod_spinner.setValueFactory(spin);
    }




    public void addBtn(){
        qty=prod_spinner.getValue();
        if (qty==0){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("somtheing worng");
            alert.showAndWait();

        }else {
            String insertData="Insert Into  commande (nom,quantite_commande,prix,date_commande)"
                    +"VALUES(?,?,?,?)";
            try {
                PreparedStatement statement=conx.prepareStatement(insertData);
                statement.setString(1,prod_name.getText());
                statement.setString(2,String.valueOf(qty));
                totalP=(qty*pr);
                statement.setString(3,String.valueOf(totalP));
                Date date=new Date();
                java.sql.Date sqlDate=new java.sql.Date(date.getTime());
                statement.setString(4,String.valueOf(sqlDate));
                statement.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();
            }catch (SQLException exception){
                System.out.println(exception.getMessage());
            }


        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setQuantite();

    }
}
