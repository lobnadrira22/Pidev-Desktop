/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.services;

import healthconnectjava.entities.Participation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface IParticipation <T>{
    public void ajoutParticipation(T e) throws SQLException;
    public void supprimerParticipation(int id) throws SQLException;
    public List<Participation> afficherListeParticipation() throws SQLException;

}