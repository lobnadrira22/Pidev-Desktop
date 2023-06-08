package healthconnectjava.services;

import java.util.List;

public interface InterfaceService <T> {

    T add(T objet);
    T get(int id);
    List<T> getall();
    boolean delete(int id);
    boolean modify(T objet);

}