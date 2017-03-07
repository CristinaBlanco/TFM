package unileon.tfm.cbf.vistas;

import unileon.tfm.cbf.controladores.PrincipalControlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


/**
 * Created by Cristina Blanco Fernandez.
 */
public class Principal extends JFrame {
    private JPanel panelBase;
    private JPanel panelMenu;
    private JPanel panelBotones;
    private JPanel panelInferior;
    private JPanel panelLateral;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton botonCalculadora;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JFormattedTextField campoFecha;
    private JFormattedTextField formattedTextField1;
    private JDesktopPane panelCentral;
    private JButton estoyEnElPanelButton;


    public Principal() {
        super("Cascanueces");
        this.setContentPane(panelBase);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
        //cuando se intente cerrar la ventana con la X
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancelar();
            }
        });
        //llamar a la calculadora de windows en el boton habilitado
        botonCalculadora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrincipalControlador.llamarCalculadora();
            }
        });

        rellenarDatos();
        panelCentralRellenar();
        this.setVisible(true);
    }

    private void onCancelar() {
        int cerrar = JOptionPane.showConfirmDialog(this, "¿Desea cerrar la aplicación?");
        if (JOptionPane.OK_OPTION == cerrar) {
            System.exit(0);
        }
    }

    private void rellenarDatos() {
        rellenarFechaHora();
        rellenarMenu();
    }

    private void rellenarFechaHora() {
        String fecha = PrincipalControlador.obtenerFecha();
        this.campoFecha.setText(fecha);
    }

    private void rellenarMenu() {
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuTickets = new JMenu("Tickets");
        JMenu menuCaja = new JMenu("Caja");
        JMenu menuAyuda = new JMenu("Ayuda");
        barraMenu.add(menuTickets);
        barraMenu.add(menuCaja);
        barraMenu.add(menuAyuda);


        JMenuItem apertura = new JMenuItem("Apertura");
        JMenuItem cierre = new JMenuItem("Cierre");
        menuCaja.add(apertura);
        menuTickets.add(cierre);
        this.setJMenuBar(barraMenu);
    }

    private void panelCentralRellenar(){
        JInternalFrame internal = new JInternalFrame();
        internal.pack();
        JPanel panelDentroInternal = new JPanel();

        JButton a = new JButton("estoy en el internalFrame");
        panelDentroInternal.add(a);
        internal.add(panelDentroInternal);
        this.panelCentral = new JDesktopPane();
        this.panelCentral.add(internal);
    }

}
