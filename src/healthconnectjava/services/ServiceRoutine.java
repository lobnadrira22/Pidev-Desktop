package healthconnectjava.services;



import healthconnectjava.entities.Routine;
import healthconnectjava.utils.MyDB;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceRoutine implements InterfaceService<Routine>{
    Connection cnx;
    AlimentService AS = new AlimentService();
    ObjectifService OS= new ObjectifService();

    public ServiceRoutine(){
        cnx = MyDB.getInstance().getConx();

    }


    @Override
    public Routine add(Routine objet){
        try {
            String sql = "insert into routine (dejeuner_id,midi_id,dinner_id,cal_cons,date ,objectif_id)"
                    + "values (?,?,?,?,?,?)";

            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.setInt(1, objet.getId_dejeuner().getId());
            ste.setInt(2, objet.getId_midi().getId());
            ste.setInt(3, objet.getId_dinner().getId());
            ste.setInt(4, objet.getCalCons());
            ste.setDate(5, Date.valueOf(LocalDate.now()));
            ste.setInt(6, objet.getIdObj().getId());


            ste.executeUpdate();
            System.out.println("routine  ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }


    @Override
    public Routine get(int id) {
        Routine a=new Routine();
        try {
            String sql = "select * from routine where id=?";
            PreparedStatement s = cnx.prepareStatement(sql);
            s.setInt(1,id);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {

                a = new Routine(rs.getInt(1),AS.get(rs.getInt(5)),AS.get(rs.getInt(6)),AS.get(rs.getInt(7)),rs.getInt(3),rs.getDate(4),OS.get(rs.getInt(2)) );
                System.out.println("LALALA"+a.getCalCons());



            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return a;
    }


    public List<Integer> getCal(int objectif_id){
        List<Integer> ListCal = new ArrayList<>();
        try {
            String sql = "select cal_cons from routine where objectif_id=?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, objectif_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int Calorie = rs.getInt("cal_cons");
                ListCal.add(Calorie);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListCal;
    }

    public List<java.util.Date> getDates(int objectif_id){
        List<java.util.Date> ListDates = new ArrayList<>();
        try {
            String sql = "SELECT date FROM routine WHERE objectif_id = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, objectif_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                java.util.Date Dates = rs.getDate("date");
                ListDates.add(Dates);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListDates;
    }






    @Override
    public List<Routine> getall() {
        List<Routine> clotureAchatList = new ArrayList<>();
        try {
            String sql = "select * from routine ";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);

            while (rs.next()) {
             Routine   a = new Routine(rs.getInt("id"),AS.get(rs.getInt("dejeuner_id")),AS.get(rs.getInt("midi_id")),AS.get(rs.getInt("dinner_id")),rs.getInt("cal_cons"),rs.getDate("date"),OS.get(rs.getInt("objectif_id")) );
         //   Routine b = new Routine(rs.getInt(1),AS.get(rs.getInt(5)),AS.get(rs.getInt(6)),AS.get(rs.getInt(7)),rs.getInt(3),rs.getDate(4),OS.get(rs.getInt(2)) )) );
                clotureAchatList.add(a);
                System.out.println("wsh:"+a.getIdObj());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clotureAchatList;
    }










    @Override
    public boolean delete(int id) {
        String sql = "delete from routine where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }


    @Override
    public boolean modify(Routine objet){
        try {
            String sql = "update routine set dejeuner_id=? , midi_id=? , dinner_id=?,cal_cons=?,date=?,objectif_id=? where id=?";

            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.setInt(1, objet.getId_dejeuner().getId());
            ste.setInt(2, objet.getId_midi().getId());
            ste.setInt(3, objet.getId_dinner().getId());
            ste.setInt(4, objet.getCalCons());
            ste.setDate(5, (Date) objet.getDate());
            ste.setInt(6, objet.getIdObj().getId());

            ste.setInt(7, objet.getId());




            ste.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        return false;
    }



    public List<String> getByname() {
        List<String> Names = new ArrayList<>();
        try {
            String sql = "select nom from routine ";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);

            while (rs.next()) {
                String nom = rs.getString("nom");
                Names.add(nom);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Names;
    }









}

