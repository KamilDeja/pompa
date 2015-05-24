import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 2015-05-15.
 */
public class Controller implements ActionListener{
    private
        View1 view1;
        View2 view2;
        View_Out view_out;
        Data data;
        Model model;

    public Controller(){
        view1 = new View1();
        view2 = new View2();
        model = new Model();
    }
    private void action(){
        view1.addlistener(this);
        view2.addlistener(this);
        view1.createAndShowGUI();
    }

    public void actionPerformed(ActionEvent e) {
        if ("pompa1".equals(e.getActionCommand())) {
            view1.setVisible(false);
            view2.createAndShowGUI();
            view2.krok_pompy.setSelectedIndex(2);
        }
        if ("pompa2".equals(e.getActionCommand())) {
            view1.setVisible(false);
            view2.createAndShowGUI();
            view2.krok_pompy.setSelectedIndex(3);
        }
        if ("rozpis".equals(e.getActionCommand())) {
            System.out.println("rozpis");
            data = new Data(view2);
            model.rozpis(data);
            view_out = new View_Out(data);
            view_out.addlistener(this);
            view_out.createAndShowGUI();
            view2.setVisible(false);
        }
        if("default".equals(e.getActionCommand())){
            model.standardkcal();
            for(int i=0; i<view2.nrpos; i++){
                view2.posilkiArraykcal[i].setText(model.stand_kcal[i].toString());
            }
        }
        if ("wykres".equals(e.getActionCommand())) {
            Chart chart= new Chart();
            chart.chart(view_out);
        }
    }
    public static void main(String[] args){
    Controller controller = new Controller();
        System.out.println(controller);
        controller.action();
}

}
