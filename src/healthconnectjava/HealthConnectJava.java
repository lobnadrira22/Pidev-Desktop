/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package healthconnectjava;

import healthconnectjava.entities.Client;
import healthconnectjava.entities.Coach;
import healthconnectjava.services.ServiceClient;
import healthconnectjava.services.ServiceCoach;
import healthconnectjava.utils.MyDB;
import java.sql.SQLException;

/**
 *
 * @author wiki
 */
public class HealthConnectJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyDB db = MyDB.getInstance();
        
//        ServiceClient sc = new ServiceClient();
//        
//        try {
//            System.out.println(sc.selectAllForAdmin());
//        } catch (SQLException ex){
//            System.err.println(ex.getMessage());
//        }
        
//        Client cl = new Client("Cité Ghazala", "Ariana", "Homme", "187", "100", "arfaouimondher@gmail.com", "23065277", "Mondher", "Arfaoui", "01/05/1992", "23065277", "mondher.png", "[\"ROLE_CLIENT\"]", false);
//        
//        try {
//            sc.registerClient(cl);
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
        
        
        ServiceCoach sco = new ServiceCoach();
        
        Coach co = new Coach("dip.png", true, "arfaouimondher@gmail.com", "a23065277", "Mondher", "Arfaoui", "01/05/1992", "23065277", "mondher.png", "[\"ROLE_COACH\"]", false);
        try {
            sco.registerCoach(co);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
