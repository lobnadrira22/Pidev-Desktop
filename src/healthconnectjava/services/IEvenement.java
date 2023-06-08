/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.services;

import healthconnectjava.entities.Evenement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface IEvenement  <T>{
   public void ajoutEvenement(T e) throws SQLException;
   public void supprimerEvenement(int id) throws SQLException;
   public void modifierEvenement(T e) throws SQLException;
   public List<Evenement> afficherListeEvenement() throws SQLException;
   
}
