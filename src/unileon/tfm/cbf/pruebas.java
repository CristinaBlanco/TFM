package unileon.tfm.cbf;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;

/**
 * Created by Cristina on 07/03/2017.
 */
public class pruebas {


        /** Instancia esta clase */
        public static void main(String[] args) {
            new pruebas();
        }

        /**
         * Crea el JFrame, el JDesktopPane, un JInternalFrame de
         * muestra y lo visualiza.
         */
        public pruebas()
        {
            // El JFrame con el JDesktopPane
            JFrame frame = new JFrame();
            JDesktopPane destokPane = new JDesktopPane();
            frame.getContentPane().add(destokPane);

            // Se construye el panel que ira dentro del JInternalFrame
            JPanel panelEnInternalFrame = new JPanel();
            panelEnInternalFrame.setLayout(new FlowLayout());
            panelEnInternalFrame.add (new JLabel("Estoy en el panel interno"));

            // Se construye el JInternalFrame
            JInternalFrame internal = new JInternalFrame("internal");
            internal.add(panelEnInternalFrame);
            internal.pack();

            BasicInternalFrameTitlePane titlePane =
                    (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) internal.getUI()).
                            getNorthPane();
            internal.remove(titlePane);

            destokPane.add(internal);

            // Se visualiza todo.
            frame.setSize(500,500);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            // Se visualiza el JInternalFrame
            internal.setVisible(true);
        }


}
