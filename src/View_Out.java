import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by Kamil on 2015-05-16.
 */
public class View_Out extends JFrame implements ActionListener {

       // JFreeChart chart = new JFreeChart();
      //  new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
        protected
        JTextField imie_nazwisko;
        JTextField insulina;
        JTextField insulina_w;
        int nrpos=6;
        JTextField[][] rozpisArray = new JTextField[24][3];
        JLabel[] l_rozpisArray =new JLabel[24];
        JButton drukuj;
        JButton wykres;
        Container newContentPane;

        public void addlistener(ActionListener c) {

            drukuj.addActionListener(c);
            wykres.addActionListener(c);

        }

        public  View_Out(Data data) {
            imie_nazwisko= new JTextField(data.imie_nazwisko);
            imie_nazwisko.setPreferredSize(new Dimension(100,20));
            double ins=data.procent_bazy * data.insulina / 100;
            insulina = new JTextField( String.format("%.2f",ins));
            insulina.setPreferredSize(new Dimension(50,20));
            insulina.setEnabled(false);

            insulina_w = new JTextField( String.format("%.2f", data.insulina_w));
            insulina_w.setPreferredSize(new Dimension(50, 20));
            insulina_w.setEnabled(false);




            Integer i = new Integer(0);
            while(i<24)
            {
                l_rozpisArray[i]= new JLabel(i.toString()+":00");
                rozpisArray[i][0]= new JTextField();
                rozpisArray[i][1]= new JTextField();
                rozpisArray[i][2]= new JTextField();
                rozpisArray[i][0].setPreferredSize(new Dimension(50, 25));
                rozpisArray[i][0].setText(String.format("%.2f", data.rozpis[i]).replace(',', '.'));
                rozpisArray[i][1].setPreferredSize(new Dimension(50, 25));
                rozpisArray[i][1].setText(String.format("%.2f", data.rozpis_m[i]).replace(',', '.'));
                rozpisArray[i][2].setPreferredSize(new Dimension(50, 25));
                rozpisArray[i][2].setText(String.format("%.2f", data.rozpis_w[i]).replace(',', '.'));
                rozpisArray[i][0].setActionCommand("aktualizacja");
                rozpisArray[i][1].setActionCommand("aktualizacja");
                rozpisArray[i][2].setActionCommand("aktualizacja");
                rozpisArray[i][0].addActionListener(this);
                rozpisArray[i][1].addActionListener(this);
                rozpisArray[i][2].addActionListener(this);
                rozpisArray[i][0].setInputVerifier(new Verifier());
                rozpisArray[i][1].setInputVerifier(new Verifier());
                rozpisArray[i][2].setInputVerifier(new Verifier());

                ++i;
            }

            int nrrows=25;
            Container [] containerArray;
            containerArray = new Container[nrrows];
            for(i=0;i<nrrows;i++) {
                containerArray[i] =new Container();
                containerArray[i].setLayout(new FlowLayout());
            }
            newContentPane = new Container();
            newContentPane.setLayout(new BoxLayout(newContentPane, BoxLayout.Y_AXIS));
            //Add Components to this container, using the default FlowLayout.
            containerArray[0].add(new JLabel("Imie i Nazwisko: "));
            containerArray[0].add(imie_nazwisko);
            newContentPane.add(containerArray[0]);
            containerArray[1].add(new JLabel("Insulina: "));
            containerArray[1].add(insulina);
            containerArray[1].add(new JLabel("Suma insuliny rozpisanej: "));
            containerArray[1].add(insulina_w);
            newContentPane.add(containerArray[1]);
            i=0;
            int j=2;
            while(i<12){
                containerArray[j].add(l_rozpisArray[i]);
                containerArray[j].add(rozpisArray[i][0]);
                containerArray[j].add(rozpisArray[i][1]);
                containerArray[j].add(rozpisArray[i][2]);

                containerArray[j].add(l_rozpisArray[i+12]);
                containerArray[j].add(rozpisArray[i+12][0]);
                containerArray[j].add(rozpisArray[i+12][1]);
                containerArray[j].add(rozpisArray[i+12][2]);
                newContentPane.add(containerArray[j]);
                i++;
                j++;
            }

            drukuj = new JButton("Drukuj");
            drukuj.setActionCommand("print");
            containerArray[j].add(drukuj);

            wykres = new JButton("Wykres");
            wykres.setActionCommand("wykres");
            containerArray[j].add(wykres);
            newContentPane.add(containerArray[j]);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setContentPane(newContentPane);
            setBackground(Model.colour);
           // chart(data);


        }

        public void actionPerformed(ActionEvent e) {
            if ("aktualizacja".equals(e.getActionCommand())) {
                int i=0;
                Double sum= new Double(0.0);
                while(i<24) {
                    sum+=Double.parseDouble(rozpisArray[i][0].getText());
                    ++i;
                }
                insulina_w.setText(String.format("%.2f", sum));
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

//        public static void main(String[] args) {
//            //Schedule a job for the event-dispatching thread:
//            //creating and showing this application's GUI.
//            javax.swing.SwingUtilities.invokeLater(new Runnable() {
//                public void run() {
//                    createAndShowGUI();
//                }
//            });
//        }
}
