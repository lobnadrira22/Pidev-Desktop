/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.entities;

/**
 *
 * @author User
 */
public class Participation {
    private int id;
    private int evenementId;
    private int userId;

    public Participation(int evenementId, int userId) {
        this.evenementId = evenementId;
        this.userId = userId;
    }

    public Participation(int id, int evenementId, int userId) {
        this.id = id;
        this.evenementId = evenementId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvenementId() {
        return evenementId;
    }

    public void setEvenementId(int evenementId) {
        this.evenementId = evenementId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", evenementId=" + evenementId + ", userId=" + userId + '}';
    }
}
