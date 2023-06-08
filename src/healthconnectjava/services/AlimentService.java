package healthconnectjava.services;



import healthconnectjava.entities.Aliment;
import healthconnectjava.utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlimentService implements InterfaceService<Aliment>{
    Connection cnx;
    public AlimentService(){
        cnx = MyDB.getInstance().getConx();

    }


    @Override
    public Aliment add(Aliment objet){
        try {
            String sql = "insert into aliment(nom,nb_cal,descr)"
                    + "values (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.setString(1, objet.getNom());
            ste.setInt(2, objet.getNbcal());
            ste.setString(3, objet.getDesc());

            ste.executeUpdate();
            System.out.println("aliment  ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }


    @Override
    public Aliment get(int id) {
        Aliment a=new Aliment();
        try {
            String sql = "select * from aliment where id=?";
            PreparedStatement s = cnx.prepareStatement(sql);
            s.setInt(1,id);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {

                a = new Aliment(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
                System.out.println(a);


            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return a;
    }

    @Override
    public List<Aliment> getall() {
        List<Aliment> clotureAchatList = new ArrayList<>();
        try {
            String sql = "select * from aliment";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);

            while (s.next()) {
                Aliment aliment  = new Aliment(s.getInt(1),s.getString(2),s.getInt(3),s.getString(4) );
                System.out.println(aliment.toString());
                clotureAchatList.add(aliment);





            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clotureAchatList;
    }


    @Override
    public boolean delete(int id) {
        String sql = "delete from aliment where id=?";
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
    public boolean modify(Aliment objet){
        try {
        String sql = "update aliment set nom=? , nb_cal=? , descr=? where id=?";

        PreparedStatement ste = cnx.prepareStatement(sql);


        ste.setString(1, objet.getNom());
        ste.setInt(2, objet.getNbcal());
        ste.setString(3, objet.getDesc());
        ste.setInt(4, objet.getId());
        ste.executeUpdate();


    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }


        return false;
    }









    }

