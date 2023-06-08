/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package healthconnectjava.GUI;

import com.itextpdf.text.Rectangle;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import healthconnectjava.GUI.CardEventController;
import healthconnectjava.entities.Evenement;
import healthconnectjava.services.EvenementService;
import healthconnectjava.utils.MyDB;

public class AffichageEvFrontController implements Initializable {

     

    @FXML
    private Pane menuForm;

    @FXML
    private GridPane menuGridPane;

    @FXML
    private ScrollPane menuScrollPane;

    @FXML
    private TableView<Evenement> menuTableView;

    @FXML
    private Label menuTot;

 
   
    private ObservableList<Evenement> cardListData= FXCollections.observableArrayList();
    public Connection conx;
    public Statement stm;
    EvenementService categorieProduitService =new EvenementService();
    public AffichageEvFrontController(){
        conx= MyDB.getInstance().getConx();
    }

    public ObservableList<Evenement>menuGetData(){
        ObservableList<Evenement>listData=FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM evenement";
            stm=conx.createStatement();
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next()){
                //float prix = Float.parseFloat(rs.getString("prix"));
              //   Evenement e = new Evenement(rs.getString("nom"), rs.getString("type"), rs.getString("adresse"), 
                //         rs.getString("description"), rs.getString("image"), rs.getDate("date"));
                Evenement e = new Evenement(rs.getInt("id"),rs.getString("nom"), rs.getString("type"), rs.getString("adresse"), 
                         rs.getString("description"), rs.getString("image"), rs.getDate("date"));


                listData.add(e);

            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return listData;
    }
    public void menuDisplayCard(){
        cardListData.clear();
        cardListData.addAll(menuGetData());
        int row=0;
        int column=0;

        menuGridPane.getRowConstraints().clear();
        menuGridPane.getColumnConstraints().clear();

        for(int q=0;q<cardListData.size();q++){
            try {
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("cardEvent.fxml"));
                AnchorPane pane=loader.load();
                CardEventController cardC=loader.getController();
                cardC.setData(cardListData.get(q));
                if(column==3){
                    column=0;
                    row+=1;
                }
                menuGridPane.add(pane,column++,row);
               // GridPane.setMargin(pane,new Insets(10));
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }


    private ObservableList<Evenement> menuOrderListData ;
  

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuDisplayCard();
   ///     menuGetOrder();
    ///    menuShowOrderData();
    }
    }


