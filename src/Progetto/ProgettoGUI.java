package Progetto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProgettoGUI extends JFrame{
    private JButton inserisciButton;
    private JButton modificaNomeButton;
    private JButton cancellaButton;
    private JButton visualizzaButton;
    private JTextArea textArea1;
    private JPanel mainPanel;
    private JLabel label1;
    private JButton visButton;

    public ProgettoGUI() {
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(500, 500);
        this.add(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        textArea1.setEditable(false);
        label1.setPreferredSize(new Dimension(100, 30));
        inserisciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InserisciGUI dialog = new InserisciGUI();
                dialog.setLocationRelativeTo(ProgettoGUI.this);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        visualizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                ArrayList<Paziente> listaPazienti = Main.connessione.selectAll();
                for(int i=0; i<listaPazienti.size(); i++) {
                    textArea1.append(listaPazienti.get(i).toString() + "\n");
                }
            }
        });
        cancellaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CancellaGUI dialog = new CancellaGUI();
                dialog.pack();
                dialog.setLocationRelativeTo(ProgettoGUI.this);
                dialog.setVisible(true);
            }
        });
        visButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) JOptionPane.showInputDialog(ProgettoGUI.this, "Inserire Citta");
                if (s != null) {
                    ArrayList<Paziente> temp = Main.connessione.cercaConCitta(s);
                    if (temp.size() == 0) {
                        JOptionPane.showMessageDialog(ProgettoGUI.this, "Nessun paziente per questa Città", "Errore", JOptionPane.ERROR_MESSAGE);
                    } else {
                        textArea1.setText("");
                        for (int i = 0; i < temp.size(); i++) {
                            textArea1.append(temp.get(i).toString() + "\n");
                        }
                    }
                }
            }
        });
        modificaNomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificaGUI dialog = new ModificaGUI();
                dialog.pack();
                dialog.setLocationRelativeTo(ProgettoGUI.this);
                dialog.setVisible(true);
            }
        });
    }
}
