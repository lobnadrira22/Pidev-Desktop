/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.services;

import healthconnectjava.entities.Evenement;
import healthconnectjava.entities.Exercice;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import healthconnectjava.utils.MyDB;
//import static utils.MyDB.instance;

/**
 *
 * @author User
 */
public class ExerciceService {
      public Connection conx;
    public Statement stm;
    public ExerciceService(){
     conx =MyDB.getInstance().getConx();
    }
    
    
     public List<Exercice> afficherListeExercice2() throws SQLException{
        String req = "SELECT * FROM `exercice`";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Exercice> exercices = new ArrayList<>(); 
        while(rs.next()){
            //////au niveau de l'affichage l'index commence par 1
            //Evenement e = new Evenement(rs.getInt("id"),rs.getString(2),rs.getString("type"),rs.getString("adresse"),rs.getString("description"),rs.getString("type"),rs.getString("image"),rs.getDate("date"));
            
            Exercice e = new Exercice(rs.getInt("id"),rs.getInt("evenement_id"),rs.getString("nom"),rs.getString("description"),rs.getString("duree"),rs.getString("image"));
            exercices.add(e);
        }
        return exercices;
    }
   public List<Exercice> afficherListeExercice() throws SQLException {
    List<Exercice> exercices = new ArrayList<>();
    String req = "SELECT e.*, ev.nom AS evenement_nom FROM exercice e JOIN evenement ev ON e.evenement_id = ev.id";
   stm = conx.createStatement();
    ResultSet rs = stm.executeQuery(req);
    while (rs.next()) {
        Exercice exercice = new Exercice(
            rs.getInt("id"), 
            rs.getInt("evenement_id"), 
            rs.getString("evenement_nom"), 
            rs.getString("nom"), 
            rs.getString("description"), 
            rs.getString("duree"), 
            rs.getString("image")
        );
        exercices.add(exercice);
    }
    return exercices;
}

     
     
      public void ajoutExercice(Exercice e) throws SQLException{

            String req="INSERT INTO `exercice`(`evenement_id`, `nom`, `description`, `duree`, `image`) VALUES ('"+e.getEvenementId()+"','"+e.getNom()+"','"+e.getDescription()+"','"+e.getDuree()+"','"+e.getImage()+"')";
            stm=conx.createStatement();
            stm.executeUpdate(req);
            System.out.println("exercice ajoutee");

        }
      
       public void supprimerExercice(int id) throws SQLException {
        String req = "DELETE FROM `exercice` WHERE `id`=" + id;
        stm = conx.createStatement();
        int result = stm.executeUpdate(req);
        if (result == 0) {
            System.out.println("Aucun exercice n'a été supprimé.");
        } else {
            System.out.println("L'exercice a été supprimé avec succès.");
        }
        }
       
       
         public void modifierExercice(Exercice e) throws SQLException {
          
            String req = "UPDATE `exercice` SET `evenement_id`='" + e.getEvenementId()+ "',`nom`='" + e.getNom()+ "',`description`='" + e.getDescription() + "',`duree`='" + e.getDuree() + "',`image`='" + e.getImage() + "' WHERE `id`=" + e.getId();
                       
            stm = conx.createStatement();
            int result = stm.executeUpdate(req);
            if (result == 0) {
                System.out.println("Aucun exercice n'a été modifié.");
            } else {
                System.out.println("L'exercice a été modifié avec succès.");
            }
        }
  public List<Exercice> rechercherExercicesMultiCritere(String texteRecherche) throws SQLException {
    List<Exercice> exercices = new ArrayList<>();
    String req = "SELECT e.*, ev.nom AS evenement_nom FROM exercice e JOIN evenement ev ON e.evenement_id = ev.id WHERE e.nom LIKE '%" + texteRecherche + "%' OR ev.nom LIKE '%" + texteRecherche + "%' OR e.id LIKE '%" + texteRecherche + "%'";    stm = conx.createStatement();
    ResultSet rs = stm.executeQuery(req);
    while (rs.next()) {
        Exercice exercice = new Exercice(
            rs.getInt("id"), 
            rs.getInt("evenement_id"), 
            rs.getString("evenement_nom"), 
            rs.getString("nom"), 
            rs.getString("description"), 
            rs.getString("duree"), 
            rs.getString("image")
            );
            exercices.add(exercice);
        }
        return exercices;
    }

         
      /*   public List<Exercice> rechercherExercicesParNom(String nomExercice) throws SQLException {
        List<Exercice> exercices = new ArrayList<>();
        String req = "SELECT e.*, ev.nom AS evenement_nom FROM exercice e JOIN evenement ev ON e.evenement_id = ev.id WHERE e.nom LIKE '%" + nomExercice + "%'";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        while (rs.next()) {
            Exercice exercice = new Exercice(
                rs.getInt("id"), 
                rs.getInt("evenement_id"), 
                rs.getString("evenement_nom"), 
                rs.getString("nom"), 
                rs.getString("description"), 
                rs.getString("duree"), 
                rs.getString("image")
            );
            exercices.add(exercice);
        }
        return exercices;
    }
      */
 
  


}
