package boundary;

import control.AdminController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @author dandi on 28/12/16.
 */

public class ReviewBoundary {

    private String user;
    private String article;
    private JFrame frame;
    private JFrame confirmFrame;

    public ReviewBoundary(JFrame frame, String review, String owner, int rating, String article, String user) {

        this.user = user;
        this.article = article;
        this.frame = frame;

        GridBagConstraints gbc = new GridBagConstraints();
        JPanel mainJpanel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(new JLabel("Utente : "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(new JLabel(user), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(new JLabel("Articolo : "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(new JLabel(article), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(new JLabel("Proprietario : "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(new JLabel(owner), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(new JLabel("Rating : "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(new JLabel(String.valueOf(rating)), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(new JLabel("Recensione : "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(new JLabel(review), gbc);

        JButton acceptB = new JButton("Accetta");
        JButton refuseB = new JButton("Rifiuta");
        JButton backB = new JButton("Indietro");

        Actions acceptAction = new Actions(0);
        Actions refuseAction = new Actions(1);
        Actions backAction = new Actions(2);
        acceptB.addActionListener(acceptAction);
        refuseB.addActionListener(refuseAction);
        backB.addActionListener(backAction);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(acceptB, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(refuseB, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(backB, gbc);

        frame.setContentPane(mainJpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private class Actions implements ActionListener {

        private int kind;

        private Actions(int kind){
            this.kind = kind;
        }

        public void actionPerformed(ActionEvent event) {
            if(this.kind == 1){
                try {
                    if(!AdminController.getInstance().refuseReview(article, user)){
                        JOptionPane.showMessageDialog(null, "C'è stato un errore", "Warning", JOptionPane.ERROR_MESSAGE);
                        AdminController.getInstance().finishedReview(frame);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Recensione rimossa con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
                        AdminController.getInstance().finishedReview(frame);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if(this.kind == 0){
                try {
                    if(!AdminController.getInstance().acceptReview(article, user)){
                        initSuccessFrame();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Recensione accettata con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
                        AdminController.getInstance().finishedReview(frame);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if(this.kind == 2){
                AdminController.getInstance().finishedReview(frame);
            }
        }
    }

    private void initSuccessFrame() {

        confirmFrame = new JFrame();
        JPanel confirmPanel = new JPanel();
        confirmPanel.add(new JLabel("Eliminazione della segnalazione. Continuare ?"));
        JButton yes = new JButton("Si");
        JButton no = new JButton("No");

        confirmPanel.add(yes);
        confirmPanel.add(no);
        confirmFrame.add(confirmPanel);
        confirmFrame.pack();
        confirmFrame.setVisible(true);

        Actions azione = new Actions(1);
        yes.addActionListener(azione);

        no.addActionListener(e -> confirmFrame.setVisible(false));
    }
}
