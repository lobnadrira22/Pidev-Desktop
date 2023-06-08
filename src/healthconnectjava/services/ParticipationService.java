/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.services;

import healthconnectjava.entities.Participation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import healthconnectjava.utils.MyDB;

/**
 *
 * @author User
 */
public class ParticipationService {
      public Connection conx;
    public Statement stm;
    public ParticipationService(){
     conx =MyDB.getInstance().getConx();
    }
    
     public List<Participation> afficherListeParticipation() throws SQLException{
        String req = "SELECT * FROM `participation`";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Participation> participations = new ArrayList<>(); 
        while(rs.next()){
            //////au niveau de l'affichage l'index commence par 1
            //Evenement e = new Evenement(rs.getInt("id"),rs.getString(2),rs.getString("type"),rs.getString("adresse"),rs.getString("description"),rs.getString("type"),rs.getString("image"),rs.getDate("date"));
            
            Participation p = new Participation(rs.getInt("id"),rs.getInt("evenement_id"),rs.getInt("user_id"));
            participations.add(p);
        }
        return participations;
    }
     
      public void ajoutParticipation(Participation p) throws SQLException{

            String req="INSERT INTO `participation`(`evenement_id`, `user_id`) VALUES ('"+p.getEvenementId()+"','1')";
            stm=conx.createStatement();
            stm.executeUpdate(req);
            System.out.println("participation ajoutee");

        }
      
       public void supprimerParticipation(int id) throws SQLException {
        String req = "DELETE FROM `participation` WHERE `id`=" + id;
        stm = conx.createStatement();
        int result = stm.executeUpdate(req);
        if (result == 0) {
            System.out.println("Aucune participation n'a été supprimé.");
        } else {
            System.out.println("La participation a été supprimé avec succès.");
        }
        }
        public boolean isUserParticipating(int userId, int eventId) throws SQLException {
        String req = "SELECT * FROM `participation` WHERE `user_id`=" +'1'+ " AND `evenement_id`=" + eventId;
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        return rs.next();
}
        
        public Participation getParticipationByEvenementIdAndUserId(int evenementId, int userId) throws SQLException {
    
        PreparedStatement ps = conx.prepareStatement("SELECT * FROM participation WHERE evenement_id = ? AND user_id = ?");
        ps.setInt(1, evenementId);
        ps.setInt(2, userId);
        ResultSet rs = ps.executeQuery();
        Participation participation = null;
        if (rs.next()) {
            participation = new Participation(rs.getInt("id"), rs.getInt("evenement_id"), rs.getInt("user_id"));
        }
        rs.close();
        ps.close();
        return participation;
    }

}
