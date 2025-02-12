package Progetto;

import javax.swing.*;
import java.awt.event.*;

public class InserisciGUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;

    public InserisciGUI() {
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
    }

    private void onOK() {
        String CF = textField1.getText();
        String Nome = textField2.getText();
        String Cognome = textField3.getText();
        String CAP = textField4.getText();
        String Via = textField5.getText();
        String Citta = textField6.getText();
        if(CF.length() != 16)
            JOptionPane.showMessageDialog(InserisciGUI.this, "Codice Fiscale non conforme", "Errore", JOptionPane.ERROR_MESSAGE);
        else {
            if(Main.connessione.inserimentoPaziente(CF, Nome, Cognome, CAP, Via, Citta) == 1) {
                JOptionPane.showMessageDialog(InserisciGUI.this, "Inserimento avvenuto correttamente");
                dispose();
            }
            else
                JOptionPane.showMessageDialog(InserisciGUI.this, "Errore: Inserimento non avvenuto correttamente", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }
}
