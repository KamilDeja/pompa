import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class View2 extends JFrame implements ActionListener {
    JTextField imie_nazwisko,insulina,procent_bazy;
    JLabel l_imie_nazwisko, l_insulina;
    int nrpos=6;
    JTextField[] posilkiArraykcal = new JTextField[nrpos];
    Integer [][] stand_ww = new Integer[nrpos][2];
    JLabel[] l_kcal =new JLabel[nrpos];             //We need the whole array because it's not possible to use the component few times.
    JLabel[] l_ww =new JLabel[nrpos];
    JLabel[] l_wbt =new JLabel[nrpos];
    JTextField[][] posilkiArrayWW = new JTextField[nrpos][2];
    JComboBox wiek, krok_pompy;
    JRadioButton kcal,ww_wbt;
    JButton autoPosilki, rozpispompy;

    Container newContentPane;

    public  View2() {

        imie_nazwisko= new JTextField("Adam Nowak");
        imie_nazwisko.setPreferredSize(new Dimension(100,20));

        l_imie_nazwisko= new JLabel("Imie i nazwisko pacjenta");

        insulina= new JTextField("30");
        insulina.setPreferredSize(new Dimension(60, 20));

        l_insulina= new JLabel("Dobowe zapotrzebowanie na insuline (j.)");

        procent_bazy= new JTextField("30");
        procent_bazy.setPreferredSize(new Dimension(40, 20));

        String[] przedzial_wiekowy = { " ","0-5", "5-11", "11-18" };
        wiek= new JComboBox(przedzial_wiekowy);
        wiek.setSelectedIndex(2);

        String[] dokladnosc_pompy = { " ","0.01", "0.05", "0.1" };
        krok_pompy= new JComboBox(dokladnosc_pompy);

        kcal = new JRadioButton("kcal");
        kcal.setSelected(true);
        kcal.setActionCommand("kcal");
        ww_wbt = new JRadioButton("WW/WBT");
        ww_wbt.setSelected(false);
        ww_wbt.setActionCommand("ww");
        ButtonGroup group = new ButtonGroup();
        group.add(kcal);
        group.add(ww_wbt);

        autoPosilki = new JButton("Wypelnij automatycznie");
        autoPosilki.setActionCommand("default");

        rozpispompy = new JButton("Rozpis pompy");
        rozpispompy.setActionCommand("rozpis");

        for(int i=0;i<nrpos; i++)
        {
            l_kcal[i]= new JLabel("kcal");
            l_kcal[i].setVisible(true);
            l_ww[i] = new JLabel("WW");
            l_ww[i].setVisible(false);
            l_wbt[i] = new JLabel("WBT");
            l_wbt[i].setVisible(false);
            posilkiArrayWW[i][0]= new JTextField();
            posilkiArrayWW[i][1]= new JTextField();
            posilkiArrayWW[i][0].setPreferredSize(new Dimension(30, 20));
            posilkiArrayWW[i][1].setPreferredSize(new Dimension(30, 20));
            posilkiArrayWW[i][0].setVisible(false);
            posilkiArrayWW[i][1].setVisible(false);
            posilkiArraykcal[i]= new JTextField();
            posilkiArraykcal[i].setPreferredSize(new Dimension(50, 20));
            posilkiArraykcal[i].setVisible(true);
        }

        int nrrows=14;
        Container [] containerArray;
        containerArray = new Container[nrrows];
        for(int i=0;i<nrrows;i++) {
            containerArray[i] =new Container();
            containerArray[i].setLayout(new FlowLayout());
        }
        newContentPane = new Container();
        newContentPane.setLayout(new BoxLayout(newContentPane, BoxLayout.Y_AXIS));
        //Add Components to this container, using the default FlowLayout.
        containerArray[0].add(l_imie_nazwisko);
        containerArray[0].add(imie_nazwisko);

        containerArray[1].add(l_insulina);
        containerArray[1].add(insulina);


        containerArray[2].add(new JLabel("Procent bazy (%)"));
        containerArray[2].add(procent_bazy);

        containerArray[3].add(new JLabel("Przedzial wiekowy:"));
        containerArray[3].add(wiek);

        containerArray[4].add(new JLabel("Krok pompy (j):"));
        containerArray[4].add(krok_pompy);

        containerArray[5].add(new JLabel("Wybierz metode wprowadzania: "));
        containerArray[5].add(kcal);
        containerArray[5].add(ww_wbt);

        containerArray[6].add(new JLabel("Posilki:"));
        containerArray[6].add(autoPosilki);

        containerArray[7].add(new JLabel("I Sniadanie:"));
        containerArray[8].add(new JLabel("II Sniadanie:"));
        containerArray[9].add(new JLabel("Obiad:"));
        containerArray[10].add(new JLabel("Podwieczorek:"));
        containerArray[11].add(new JLabel("I Kolacja:"));
        containerArray[12].add(new JLabel("II Kolacja:"));

        containerArray[13].add(rozpispompy);

        int i=0;
        int j=7;
        while(i<nrpos){

            containerArray[j].add(posilkiArraykcal[i]);
            containerArray[j].add(l_kcal[i]);
            containerArray[j].add(posilkiArrayWW[i][0]);
            containerArray[j].add(l_ww[i]);
            containerArray[j].add(posilkiArrayWW[i][1]);
            containerArray[j].add(l_wbt[i]);
            i++;
            j++;
        }


        for(i=0; i<nrrows;i++)                      //Adding all the rows to the panel
        {
            newContentPane.add(containerArray[i]);
        }

        kcal.addActionListener(this);
        ww_wbt.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(newContentPane);
        setBackground(Model.colour);

    }
    public void addlistener(Controller c){
        rozpispompy.addActionListener(c);
        autoPosilki.addActionListener(c);
    }
    public void actionPerformed(ActionEvent e) {
        if ("kcal".equals(e.getActionCommand())) {
            for(int i=0;i<nrpos; i++)
            {
                l_kcal[i].setVisible(true);
                l_ww[i].setVisible(false);
                l_wbt[i].setVisible(false);
                posilkiArrayWW[i][0].setVisible(false);
                posilkiArrayWW[i][1].setVisible(false);
                posilkiArraykcal[i].setVisible(true);
            }
        }
        if ("ww".equals(e.getActionCommand()))
            for(int i=0;i<nrpos; i++)
            {
                l_kcal[i].setVisible(false);
                l_ww[i].setVisible(true);
                l_wbt[i].setVisible(true);
                posilkiArrayWW[i][0].setVisible(true);
                posilkiArrayWW[i][1].setVisible(true);
                posilkiArraykcal[i].setVisible(false);

        }
    }

    /**
     * Create the GUI and show it.
     */
    public void createAndShowGUI() {


        //Display the window.
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
}