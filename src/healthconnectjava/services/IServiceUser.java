/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package healthconnectjava.services;

import java.sql.SQLException;
/**
 *
 * @author wiki
 */
public interface IServiceUser<T> {
    String login(T t) throws SQLException;
    void forgotPassword(T t) throws SQLException;
}
