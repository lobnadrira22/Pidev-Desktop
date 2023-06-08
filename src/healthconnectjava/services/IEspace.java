/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lobna
 */
public interface IEspace<Espace> {
     public void ajoutEspace(Espace es) throws SQLException;
    public List<Espace> afficherListeES() throws SQLException;
    public boolean modifierEspace(Espace es)throws SQLException ;
     public Espace get(int id) throws SQLException;
    //public CategorieLocation get(int id);
    public boolean supprimerEspace(int id);
   

    
}
