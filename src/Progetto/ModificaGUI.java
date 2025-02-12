package Progetto;

import javax.swing.*;
import java.awt.event.*;

public class ModificaGUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;

    public ModificaGUI() {
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
        String temp1 = textField1.getText();
        String temp2 = textField2.getText();
            if(temp1.length() != 16)
                JOptionPane.showMessageDialog(ModificaGUI.this, "Codice Fiscale non conforme", "Errore", JOptionPane.ERROR_MESSAGE);
            else {
                if(Main.connessione.modificaNome(temp1, temp2)==1) {
                    JOptionPane.showMessageDialog(ModificaGUI.this, "Modifica effettuata con successo");
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(ModificaGUI.this, "Errore: Paziente non modificato correttamente", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }

    private void onCancel() {
        dispose();
    }
}
