/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.services;

import healthconnectjava.entities.Academie;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface IAcademie<T> {
    public void ajoutAcademie(T a)throws SQLException;
    public void ajoutAcademiee(T aa)throws SQLException;
    public List<Academie> afficherListeA()throws SQLException;
     public Academie getAcademiee(int id)throws SQLException;
     public boolean modifieracademie(Academie object) throws SQLException;
     public boolean supprimeracademie(int id);
}
