import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 2015-05-21.
 */
public class Chart implements ActionListener {
    private ChartPanel cp,cp1,cp2;

    private XYDataset createDataset(View_Out view) {

        DefaultXYDataset ds = new DefaultXYDataset();

        double[][] data= new double[2][24];

        for(int i=0; i<24;i++){
            data[0][i]=i;
            data[1][i]= (Double.parseDouble(view.rozpisArray[i][0].getText()));
        }
        ds.addSeries("Rozpis insuliny", data);

        return ds;
    }
    private XYDataset createDataset1(View_Out view) {

        DefaultXYDataset ds = new DefaultXYDataset();

        double[][] data= new double[2][24];

        for(int i=0; i<24;i++){
            data[0][i]=i;
            data[1][i]= (Double.parseDouble(view.rozpisArray[i][1].getText()));
        }
        ds.addSeries("Rozpis insuliny", data);

        return ds;
    }
    private XYDataset createDataset2(View_Out view) {

        DefaultXYDataset ds = new DefaultXYDataset();

        double[][] data= new double[2][24];

        for(int i=0; i<24;i++){
            data[0][i]=i;
            data[1][i]= (Double.parseDouble(view.rozpisArray[i][2].getText()));
        }
        ds.addSeries("Rozpis insuliny", data);

        return ds;
    }
    public void chart(View_Out view) {
        JFrame frame = new JFrame("Charts");

        frame.setSize(500, 300);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        XYDataset ds1 = createDataset(view);
        XYDataset ds2 = createDataset1(view);
        XYDataset ds3 = createDataset2(view);
        JFreeChart chart = ChartFactory.createXYLineChart("Rozpis insuliny",
                "Godzina", "Dawka", ds1, PlotOrientation.VERTICAL, true, true,
                false);

        JFreeChart chart1 = ChartFactory.createXYLineChart("Rozpis insuliny dla +20%",
                "Godzina", "Dawka", ds2, PlotOrientation.VERTICAL, true, true,
                false);

        JFreeChart chart2 = ChartFactory.createXYLineChart("Rozpis insuliny dla -20%",
                "Godzina", "Dawka", ds3, PlotOrientation.VERTICAL, true, true,
                false);

        cp = new ChartPanel(chart);
        cp1 = new ChartPanel(chart1);
        cp1.setVisible(false);
        cp2 = new ChartPanel(chart2);
        cp2.setVisible(false);

        JPanel ContentPanel= new JPanel();
        ContentPanel.add(cp);
        ContentPanel.add(cp1);
        ContentPanel.add(cp2);
       // JPanel buttonspanel= new JPanel();
        JRadioButton Rozpis1 = new JRadioButton("Rozpis bazy");
        Rozpis1.setSelected(true);
        Rozpis1.setActionCommand("cp");
        Rozpis1.addActionListener(this);
        JRadioButton Rozpis2 = new JRadioButton("Rozpis +20%");
        Rozpis2.setSelected(false);
        Rozpis2.setActionCommand("cp1");
        Rozpis2.addActionListener(this);
        JRadioButton Rozpis3 = new JRadioButton("Rozpis -20%");
        Rozpis3.setSelected(false);
        Rozpis3.setActionCommand("cp2");
        Rozpis3.addActionListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(Rozpis1);
        group.add(Rozpis2);
        group.add(Rozpis3);


        Container ContentPane = new Container();
        ContentPane.setLayout(new BoxLayout(ContentPane, BoxLayout.Y_AXIS));

        ContentPane.add(new Container().add(Rozpis1));
        ContentPane.add(new Container().add(Rozpis2));
        ContentPane.add(new Container().add(Rozpis3));
        ContentPanel.add(ContentPane);

        //ContentPanel.setAutoscrolls(true);
        ContentPanel.setBackground(Model.colour);
        frame.setContentPane(ContentPanel);
        frame.pack();
        //frame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if ("cp".equals(e.getActionCommand())) {
            cp.setVisible(true);
            cp1.setVisible(false);
            cp2.setVisible(false);
        }
        if ("cp1".equals(e.getActionCommand())){
            cp.setVisible(false);
            cp1.setVisible(true);
            cp2.setVisible(false);
        }
        if ("cp2".equals(e.getActionCommand())){
            cp.setVisible(false);
            cp1.setVisible(false);
            cp2.setVisible(true);
        }

    }


}
