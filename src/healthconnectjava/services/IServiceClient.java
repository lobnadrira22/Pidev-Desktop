/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package healthconnectjava.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author wiki
 */
public interface IServiceClient<T> {
    
    void registerClient(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    
    List<T> selectAllForAdmin() throws SQLException;
    
    void activer(T t) throws SQLException;
    void desactiver(T t) throws SQLException;
    
}
