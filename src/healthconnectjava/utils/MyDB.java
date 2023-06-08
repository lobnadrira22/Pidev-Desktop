/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author wiki
 */
public class MyDB {
    
    String url="jdbc:mysql://localhost:3306/integration";
    String username="root";
    String pwd="";

    public static MyDB instance;
    public Connection conx;

    public static MyDB getInstance(){
       if(instance == null){
           instance = new MyDB ();
       }
       return instance;
     }

    //kenet public radineha private bch ma najamch naaytelha min class okhra
    private MyDB(){
         try {
             //getconnexion =>etabli la conx a la BD
             //exception sql
             conx = DriverManager.getConnection(url,username ,pwd);
             System.out.println("Csonnexion etablie");

         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
    }

     public Connection getConx() {
         return conx;
     }
    
}
