package healthconnectjava.services;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceServiceProduit <T> {
    void ajouter(T object) throws SQLException;
    List<T> afficher() throws SQLException;
    boolean supprimer(int id);
    boolean modifier(T object);
}
