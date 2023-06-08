/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.entities;

import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class Cours {
    private int id;
    private String nom;
    private Date date;
    private int duree;
    private int nbparticipants;
    private Salle salle;

    public Cours() {
    }

    public Cours(int id, String nom, Date date, int duree, int nbparticipants, Salle salle) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.duree = duree;
        this.nbparticipants = nbparticipants;
        this.salle = salle;
    }

    public Cours(String nom, Date date, int duree, int nbparticipants, Salle salle) {
        this.nom = nom;
        this.date = date;
        this.duree = duree;
        this.nbparticipants = nbparticipants;
        this.salle = salle;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Date getDate() {
        return date;
    }

    public int getDuree() {
        return duree;
    }

    public int getNbparticipants() {
        return nbparticipants;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setNbparticipants(int nbparticipants) {
        this.nbparticipants = nbparticipants;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    @Override
    public String toString() {
        return "Cours{" + "id=" + id + ", nom=" + nom + ", date=" + date + ", duree=" + duree + ", nbparticipants=" + nbparticipants + ", salle=" + salle + '}';
    }

    
    
}
