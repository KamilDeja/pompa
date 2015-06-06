import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.lang.Double;
import java.util.regex.Pattern;

public class View1 extends JFrame {
    protected JButton b1, b2;

    public void addlistener(ActionListener c) {

        //System.out.println("add listener " + c.getClass() + ' ' + c.getClass().getName());
        //System.out.println(this.getName() + ' ' + this.getClass());
        b1.addActionListener(c);
        b2.addActionListener(c);

    }


    public  View1(){//} throws MalformedURLException {
       // System.out.println(new File("/images/1pompa.gif").getAbsoluteFile());
        //URL url = new URL("C:\\Users\\Kamil\\IdeaProjects\\Pompa\\images\\1pompa.gif");
        JPanel panel = new JPanel();
        System.out.println("View1 costructor");
        ImageIcon firstPompIcon= new ImageIcon("images\\1pompa.gif");
        ImageIcon secondPompIcon = new ImageIcon("images\\2pompa.gif");

        b1 = new JButton("Pompa nr 1", firstPompIcon);
        b1.setVerticalTextPosition(AbstractButton.BOTTOM);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setMnemonic(KeyEvent.VK_KP_LEFT);
        b1.setActionCommand("pompa1");

        b2 = new JButton("Pompa nr 2", secondPompIcon);
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_KP_RIGHT);
        b2.setActionCommand("pompa2");



        b1.setToolTipText("Wybierz pompe nr 1");
        b2.setToolTipText("Wybierz pompe nr 2");

        //Add Components to this container, using the default FlowLayout.
        panel.add(b1);
        panel.add(b2);

        //Create and set up the window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        panel.setOpaque(true);
        panel.setBackground(Model.colour);
        setContentPane(panel);
    }


    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = View1.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
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