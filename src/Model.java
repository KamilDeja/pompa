/**
 * Created by Kamil on 2015-05-15.
 */
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Model {
    private int nrpos=6;
    Integer [] stand_kcal = new Integer[nrpos];

    public static Color colour= new Color(233, 238, 255);

    private Double rozklad_dobowy[][] = new Double[24][3];
    private double[] wagi = new double[nrpos];

    public void standardkcal(){

        for(int i=0; i<nrpos; i++){
            stand_kcal[i]= new Integer(300);
        }
    }

    public Model(){
        Scan();
    }

    public void Scan () {
        Scanner scan;
        File file = new File("files/config.txt");
        try {
            scan = new Scanner(file);
            int i=-1;
            int j;
            while (scan.hasNext("male_dzieci:")||scan.hasNext("srednie_dzieci:")||scan.hasNext("duze_dzieci:")) {
                scan.next();
                ++i;
                j=0;
                while (scan.hasNextDouble()) {
                    rozklad_dobowy[j][i]=scan.nextDouble();
                    ++j;
                }
            }
            if(scan.hasNext())
                scan.next();
            j=0;
            while(scan.hasNextDouble()) {
                wagi[j]=scan.nextDouble();
                ++j;
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        int i=0,j=0;
        while(i<24){
            System.out.print(i+": ");
            j=0;
            while(j<3) {
                System.out.print(rozklad_dobowy[i][j] + "  ");
                ++j;
            }
            System.out.println();
            ++i;
        }
    }

    public void posilki(Data data){

    }
    public void rozpis(Data data){
//        Double[] rozpis= new Double[24];
//        Double[] rozpis_m= new Double[24];
//        Double[] rozpis_w= new Double[24];
        data.insulina_w=0.0;
        double val,valw,valm,rest=0,restw=0,restm=0;
        for(int i=0;i<24;i++) {
            val=data.rozpis[i] = new Double(data.insulina * data.procent_bazy*0.01 * rozklad_dobowy[i][data.wiek - 1]+rest);
            valm=data.rozpis_m[i] = new Double(data.insulina * data.procent_bazy*0.01*0.8 * rozklad_dobowy[i][data.wiek - 1]+restm);
            valw=data.rozpis_w[i] = new Double(data.insulina * data.procent_bazy*0.01*1.2 * rozklad_dobowy[i][data.wiek - 1]+restw);
            switch(data.krok_pompy_s){
                case 1:{
                    data.rozpis[i]=Math.floor(data.rozpis[i]*100)/100;
                    data.rozpis_w[i]=Math.floor(data.rozpis_w[i] * 100)/100;
                    data.rozpis_m[i]=Math.floor(data.rozpis_m[i] * 100)/100;
                    break;
                }
                case 2:{
                    data.rozpis[i]=Math.floor(data.rozpis[i]*20)/20;
                    data.rozpis_w[i]=Math.floor(data.rozpis_w[i] * 20)/20;
                    data.rozpis_m[i]=Math.floor(data.rozpis_m[i] * 20)/20;
                    break;
                }
                case 3: {
                    data.rozpis[i] = Math.floor((data.rozpis[i]) * 10) / 10;
                    data.rozpis_w[i] = Math.floor(data.rozpis_w[i] * 10) / 10;
                    data.rozpis_m[i] = Math.floor(data.rozpis_m[i] * 10) / 10;
                    break;
                }
            }
            rest=val-data.rozpis[i];
            restw=valw-data.rozpis_w[i];
            restm=valm-data.rozpis_m[i];
            data.insulina_w+=data.rozpis[i];
        }
    }
//    public static void main(String[] args) {
//
//        Model model =  new Model();
//        model.Scan();
//    }
}
