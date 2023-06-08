/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.services;

import healthconnectjava.entities.Exercice;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface IExercice <T>{
    public void ajoutExercice(T e) throws SQLException;
    public void supprimerExercice(int id) throws SQLException;
    public void modifierExercice(T e) throws SQLException;
    public List<Exercice> afficherListeExercice() throws SQLException;
}
