package healthconnectjava.services;



import healthconnectjava.entities.Objectif;
import healthconnectjava.utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectifService implements InterfaceService<Objectif>{
    Connection cnx;
    public ObjectifService(){
        cnx = MyDB.getInstance().getConx();

    }


    @Override
    public Objectif add(Objectif objet){
        try {
            String sql = "insert into objectif( poids_des, type_reg,  cal_cons,  age,  sexe,  poids,  taille)"
                    + "values (?,?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);

           ste.setInt(1, objet.getPoidsDes());
         ste.setInt(2, objet.getTypeReg());
            ste.setInt(3, objet.getCalCons());
            ste.setInt(4, objet.getAge());

            ste.setString(5, objet.getSexe());
            ste.setInt(6, objet.getPoids());
            ste.setInt(7, objet.getTaille());




            ste.executeUpdate();
            System.out.println("objectif  ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }



    @Override
    public Objectif get(int id) {
        Objectif a=new Objectif();
        try {
            String sql = "select * from objectif where id=?";
            PreparedStatement s = cnx.prepareStatement(sql);
            s.setInt(1,id);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {

                Objectif ob  = new Objectif(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getInt(7),rs.getInt(8) );


                System.out.println("l'objectif"+ob.getId());

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return a;
    }

    @Override
    public List<Objectif> getall() {
        List<Objectif> clotureAchatList = new ArrayList<>();
        try {
            String sql = "select * from objectif";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);

            while (s.next()) {
                Objectif objectif  = new Objectif(s.getInt(1),s.getInt(2),s.getInt(3),s.getInt(4),s.getInt(5),s.getString(6),s.getInt(7),s.getInt(8) );
                System.out.println("waa"+objectif.toString());
                clotureAchatList.add(objectif);



            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clotureAchatList;
    }


    @Override
    public boolean delete(int id) {
        String sql = "delete from objectif where id=?";
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
    public boolean modify(Objectif objet){
        try {
            String sql = "update objectif set poids_des=? , type_reg=? , cal_cons=?, age=?, sexe=?,poids=?,taille=? where id=?";


            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, objet.getPoidsDes());
            ste.setInt(2, objet.getTypeReg());
            ste.setInt(3, objet.getCalCons());
            ste.setInt(4, objet.getAge());

            ste.setString(5, objet.getSexe());
            ste.setInt(6, objet.getPoids());
            ste.setInt(7, objet.getTaille());




            ste.setInt(8, objet.getId());
            ste.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        return false;
    }






}

