package unileon.tfm.cbf.vistas.internas;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 * Created by Cristina on 07/03/2017.
 */
public class FormularioInternoPruebas extends JInternalFrame{

    private JPanel panelCentral;
    private JButton botonInterno;

    public FormularioInternoPruebas() {
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
       // this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.setContentPane(this.panelCentral);
        this.botonInterno.setText("hola efectivamente es el panel interno");

                this.botonInterno.setVisible(true);
        this.pack();
        this.setVisible(true);

    }

}
