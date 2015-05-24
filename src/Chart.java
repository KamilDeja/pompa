import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;

/**
 * Created by Kamil on 2015-05-21.
 */
public class Chart {

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
    public void chart(View_Out view) {
        JFrame frame = new JFrame("Charts");

        frame.setSize(500, 300);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        XYDataset ds = createDataset(view);
        JFreeChart chart = ChartFactory.createXYLineChart("Rozpis insuliny",
                "Godzina", "Dawka", ds, PlotOrientation.VERTICAL, true, true,
                false);

        ChartPanel cp = new ChartPanel(chart);

        frame.getContentPane().add(cp);
        //frame.setLocationRelativeTo(null);
    }

}
