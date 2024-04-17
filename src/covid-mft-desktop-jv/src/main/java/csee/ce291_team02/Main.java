package csee.ce291_team02;


import csee.ce291_team02.Forms.MainForm;
import mdlaf.MaterialLookAndFeel;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {


    public static void main(String[] args) {
        AppConstants.RuntimeVariables.initializeRuntimeVariables();
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            MainForm mainForm = new MainForm();

            mainForm.setVisible(true);
        });
    }
}
