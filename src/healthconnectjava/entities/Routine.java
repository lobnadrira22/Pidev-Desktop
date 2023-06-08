package healthconnectjava.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Routine {
    private int id;
    private Aliment id_dejeuner;
    private Aliment id_midi;
    private Aliment id_dinner;
    private int CalCons;
    private Date date;
    private Objectif idObj;

    public Routine(int id, Objectif objectif_id, Aliment dejeuner_id, Aliment midi_id, Aliment dinner_id, int calCons, java.sql.Date date) {
        this.id = id;
        this.id_dejeuner = id_dejeuner;
        this.id_midi = id_midi;
        this.id_dinner = id_dinner;
        CalCons = calCons;
        this.date = date;
        this.idObj = idObj;
    }

    @Override
    public String toString() {
        return id_dejeuner.getNom();
    }



    public Routine(int id, Aliment id_dejeuner, Aliment id_midi, Aliment id_dinner, int calCons, Date date, Objectif idObj) {
        this.id = id;
        this.id_dejeuner = id_dejeuner;
        this.id_midi = id_midi;
        this.id_dinner = id_dinner;
        CalCons = calCons;
        this.date = date;
        this.idObj = idObj;
    }

    public Routine( Aliment id_dejeuner, Aliment id_midi, Aliment id_dinner, int calCons, Date date, Objectif idObj) {

        this.id_dejeuner = id_dejeuner;
        this.id_midi = id_midi;
        this.id_dinner = id_dinner;
        CalCons = calCons;
        this.date = date;
        this.idObj = idObj;
    }

    public Routine() {
    }


    public Routine(int id ,Objectif idObj, int calCons,  Date date,Aliment id_dejeuner, Aliment id_midi, Aliment id_dinner) {

        this.id_dejeuner = id_dejeuner;
        this.id_midi = id_midi;
        this.id_dinner = id_dinner;
        CalCons = calCons;
        this.date = date;
        this.idObj = idObj;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aliment getId_dejeuner() {
        return id_dejeuner;
    }

    public void setId_dejeuner(Aliment id_dejeuner) {
        this.id_dejeuner = id_dejeuner;
    }

    public Aliment getId_midi() {
        return id_midi;
    }

    public void setId_midi(Aliment id_midi) {
        this.id_midi = id_midi;
    }

    public Aliment getId_dinner() {
        return id_dinner;
    }

    public void setId_dinner(Aliment id_dinner) {
        this.id_dinner = id_dinner;
    }

    public int getCalCons() {
        return CalCons;
    }

    public void setCalCons(int calCons) {
        CalCons = calCons;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Objectif getIdObj() {
        return idObj;
    }

    public void setIdObj(Objectif idObj) {
        this.idObj = idObj;
    }

    public Routine(Aliment id_dejeuner, Aliment id_midi, Aliment id_dinner, int calCons, Objectif idObj) {
        this.id_dejeuner = id_dejeuner;
        this.id_midi = id_midi;
        this.id_dinner = id_dinner;
        CalCons = calCons;

        this.idObj = idObj;
    }


    @Override
    public boolean equals(Object o) {


        Routine routine = (Routine) o;
        return
                CalCons == routine.CalCons &&
                Objects.equals(id_dejeuner.getId(), routine.id_dejeuner.getId()) &&
                Objects.equals(id_midi.getId(), routine.id_midi.getId()) &&
                Objects.equals(id_dinner.getId(), routine.id_dinner.getId()) &&

                Objects.equals(date, routine.date);
    }







    public static boolean contains(List<Routine> routines, Routine routine) {
        for (Routine r : routines) {
            if (r.equals(routine)) {
                return true;
            }
        }
        return false;
    }

















}