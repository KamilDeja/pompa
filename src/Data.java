/**
 * Created by Kamil on 2015-05-16.
 */
public class Data {
    private
    int nrpos=6;
    public
    String imie_nazwisko;
    Double insulina;
    Double insulina_w;
    Double procent_bazy;
    int wiek;
    Double krok_pompy;
    int krok_pompy_s;
    boolean metoda;
    Integer [] kcal = new Integer[nrpos];
    Integer [][] ww = new Integer[nrpos][2];
    Double[] rozpis= new Double[24];
    Double[] rozpis_m= new Double[24];
    Double[] rozpis_w= new Double[24];


    Data(View2 view2){
        imie_nazwisko = new String(view2.imie_nazwisko.getText());
        insulina = new Double(Double.parseDouble(view2.insulina.getText()));
        insulina_w = new Double(0.0);
        procent_bazy = new Double(Double.parseDouble(view2.procent_bazy.getText()));
        krok_pompy_s = view2.krok_pompy.getSelectedIndex();
        switch (view2.krok_pompy.getSelectedIndex()){
            case 1:
                krok_pompy = new Double(0.01);
                break;
            case 2:
                krok_pompy = new Double(0.5);
                break;
            case 3:
                krok_pompy = new Double(0.1);
                break;
        }
        wiek = view2.wiek.getSelectedIndex();
        metoda=view2.kcal.isSelected();
        if(metoda)
            for (int i = 0; i < nrpos; i++) {
                kcal[i] = new Integer(view2.posilkiArraykcal[i].getText());
            }
        else{
            for (int i = 0; i < nrpos; i++) {
                ww[i][1] = new Integer(view2.posilkiArrayWW[i][1].getText());
                ww[i][2] = new Integer(view2.posilkiArrayWW[i][2].getText());
            }
        }
    }

}

