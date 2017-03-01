package unileon.tfm.cbf.vistas;

import unileon.tfm.cbf.controladores.PrincipalControlador;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Cristina Blanco Fernandez.
 */
public class Principal extends JFrame{
    private JPanel panelBase;
    private JPanel panelMenu;
    private JPanel panelBotones;
    private JPanel panelInferior;
    private JPanel panelLateral;
    private JPanel panelCentral;
    private JMenu menuTickets;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JFormattedTextField campoFecha;
    private JFormattedTextField formattedTextField1;
    private JMenu menuCaja;
    private JMenu menuConsultas;

    public Principal() {
        this.setContentPane(panelBase);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        //cuando se intente cerrar la ventana con la X
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancelar();
            }
        });
        rellenarDatos();
        this.setVisible(true);
    }

    private void onCancelar() {
        int cerrar = JOptionPane.showConfirmDialog(this, "¿Desea cerrar la aplicación?");
        if(JOptionPane.OK_OPTION == cerrar) {
            System.exit(0);
        }
    }

    private void rellenarDatos() {
        rellenarFechaHora();
        rellenarMenus();
    }

    private void rellenarFechaHora() {
        String fecha = PrincipalControlador.obtenerFecha();
        this.campoFecha.setText(fecha);
    }

    private void rellenarMenus() {
        JMenuItem apertura = new JMenuItem("Apertura");
        JMenuItem cierre = new JMenuItem("Cierre");
        JMenuItem a = this.menuCaja.add(apertura);
        apertura.setVisible(true);
        this.menuCaja.add(cierre);

        this.menuCaja.setText("perro");
        this.menuCaja.insert(apertura,0);
    }
}
