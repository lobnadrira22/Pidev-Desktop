/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.GUI;



import healthconnectjava.entities.Academie;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import healthconnectjava.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FrontAcFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private GridPane menuGridPane;

    @FXML
    private ScrollPane menuScrollPane;
      private Connection conx;
      public Statement stm;
           

          private ObservableList<Academie> cardListData= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuGetData();
        menuDisplayCard();

    }    

    public FrontAcFXMLController(){
        conx= MyDB.getInstance().getConx();
    }

    public ObservableList<Academie>menuGetData(){
        ObservableList<Academie>listData=FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM Academie";
            stm=conx.createStatement();
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next()){
               
                Academie a = new Academie(rs.getInt("id"),rs.getString("nom"),
                        rs.getString("adresse"),rs.getString("numtel"), rs.getString("sportpropose")
                       );

                listData.add(a);

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
                loader.setLocation(getClass().getResource("CardacademieControllerFXML.fxml"));
                AnchorPane pane=loader.load();
                CardacademieControllerFXMLController cardA=loader.getController(); 
                cardA.setData(cardListData.get(q));
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
    
   @FXML
    private void redirigerback(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AcademieFXML.fxml"));
    Parent root;
    try {
    root = loader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    
     @FXML
    private void redirigerbacksalle(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SalleFXML.fxml"));
    Parent root;
    try {
    root = loader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    
      @FXML
    private void redirigerbackcours(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("CoursFXML.fxml"));
    Parent root;
    try {
    root = loader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    
}
