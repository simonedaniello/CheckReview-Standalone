package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author dandi on 28/12/16.
 */
public class ListBoundary {

    private JList<String> list1;


    public ListBoundary(JFrame frame) {
        DefaultListModel<String> model = new DefaultListModel<>();
        list1 = new JList<>();
        list1.setModel(model);
        JScrollPane scrollpane = new JScrollPane(list1);

        JButton okB = new JButton("OK");
        JButton refreshB = new JButton("Refresh");
        Actions okAction = new Actions(0);
        Actions refreshAction = new Actions(1);
        okB.addActionListener(okAction);
        refreshB.addActionListener(refreshAction);

        JPanel pannello = new JPanel();
        pannello.add(scrollpane);
        JPanel bottoni = new JPanel();
        bottoni.setLayout(new BoxLayout(bottoni, BoxLayout.PAGE_AXIS));
        bottoni.add(okB);
        bottoni.add(refreshB);

        pannello.add(bottoni);

        frame.add(pannello);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2- frame.getSize().width/2, dim.height/2- frame.getSize().height/2);
        frame.setVisible(true);
    }

    /*contiene l'azione dell'unico JButton della boundary*/
    private class Actions implements ActionListener {

        private int kind;

        private Actions(int kind){
            this.kind = kind;
        }

        public void actionPerformed(ActionEvent event) {
            if(this.kind == 1){
                System.out.println("azione 1");
            }
            else if(this.kind == 0){
                System.out.println("azione 0");
            }
        }
    }
}
