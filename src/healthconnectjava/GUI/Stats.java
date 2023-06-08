package healthconnectjava.GUI;



import healthconnectjava.entities.Routine;
import healthconnectjava.services.ServiceRoutine;
import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Stats    {

    @FXML
    private LineChart<?, ?> chart;

    @FXML
    private CategoryAxis dt;

    @FXML
    private NumberAxis CL;

    @FXML
    private Label CC;

    @FXML
    private Label CE;

    @FXML
    private Label TR;

    @FXML
    private Label Diff;

    @FXML
    private Label Resultat;









    Routine routine = new Routine();
    ServiceRoutine RS = new ServiceRoutine();


    public void pass(Routine routine){
        this.routine=routine;

        System.out.println(routine);


        CC.setText(String.valueOf(routine.getCalCons()));
        CE.setText(String.valueOf(routine.getIdObj().getCalCons()));

        if ((routine.getIdObj().getTypeReg())==1){
            TR.setText("Perte de poids");

        } else {
            TR.setText("Prise de poids");
        }

        Diff.setText(String.valueOf(routine.getIdObj().getCalCons()-routine.getCalCons()));

        if ((routine.getIdObj().getTypeReg())==1 && routine.getIdObj().getCalCons()-routine.getCalCons()>0 )
        {
            Resultat.setText("Bravo! D'après la formule de l'expert Harris-Benedict \n vous êtes dans la bonne voie");

        }

        if ((routine.getIdObj().getTypeReg())==1 && routine.getIdObj().getCalCons()-routine.getCalCons()<0 )
        {
            Resultat.setText("Malheuresement ! D'après la formule de l'expert Harris-Benedict \n vous devrez pensez a diminuer vos caloriques consommés");

        }

        if ((routine.getIdObj().getTypeReg())==2 && routine.getIdObj().getCalCons()-routine.getCalCons()>0 )
        {
            Resultat.setText("Malheuresement ! D'après la formule de l'expert Harris-Benedict \n  vous devrez pensez a diminuer vos caloriques consommés");

        }

        if ((routine.getIdObj().getTypeReg())==2 && routine.getIdObj().getCalCons()-routine.getCalCons()<0 )
        {
            Resultat.setText("Bravo! D'après la formule de l'expert Harris-Benedict \n  vous êtes dans la bonne voie");

        }







    }








    public void refresh(ActionEvent e){


         List<Integer>  calories = RS.getCal(routine.getIdObj().getId());
        System.out.println(calories);
        List<Date>  dates = RS.getDates(routine.getIdObj().getId());
          System.out.println(dates);
        System.out.println(routine.getIdObj().getId());


        dt.setLabel("Date");
          CL.setLabel("Calories");

        // Création de la série de données pour le LineChart
          XYChart.Series series = new XYChart.Series();
         for (int i = 0; i < dates.size(); i++) {
        series.getData().add(new XYChart.Data(dates.get(i).toString(), calories.get(i)));
        }

        // Ajout de la série de données au LineChart
     chart.getData().clear();
     chart.getData().add(series);

    }


}
