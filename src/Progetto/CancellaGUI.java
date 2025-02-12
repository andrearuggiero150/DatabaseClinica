package Progetto;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CancellaGUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private ArrayList<Paziente> listaPazienti;

    public CancellaGUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        listaPazienti = Main.connessione.selectAll();
        for(int i=0; i<listaPazienti.size(); i++) {
            comboBox1.addItem(listaPazienti.get(i).getCF());
        }
        comboBox1.setSelectedIndex(-1);
    }

    private void onOK() {
        String temp = (String)comboBox1.getSelectedItem();
        if(Main.connessione.eliminaPaziente(temp) == 1) {
            JOptionPane.showMessageDialog(CancellaGUI.this, "Paziente eliminato correttamente");
            dispose();
        }
        else
            JOptionPane.showMessageDialog(CancellaGUI.this, "Errore: Paziente non eliminato correttamente", "Errore", JOptionPane.ERROR_MESSAGE);
    }

    private void onCancel() {
        dispose();
    }

}
