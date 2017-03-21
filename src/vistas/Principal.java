package vistas;

import vistas.ventanasInternas.consultasCaja.CierreCaja;
import vistas.ventanasInternas.consultasPersonal.ConsultarEmpTaller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static controladores.PrincipalControlador.llamarCalculadora;
import static controladores.PrincipalControlador.obtenerFecha;

/**
 * Created by Cristina Blanco Fernandez.
 */
public class Principal extends JFrame {
    private JPanel panelBase;
    private JPanel panelMenu;
    private JPanel panelInferior;
    private JPanel panelLateral;
    private JButton botonCalculadora;
    private JButton button9;
    private JButton btnCambiarJPanel;
    private JButton button11;
    private JButton button12;
    private JFormattedTextField campoFecha;

    private final static String EMPLEADOS = "empleados";
    private final static String TALLERES = "talleres";
    private final static String CIERRE = "cierre";

    public Principal(){
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
                llamarCalculadora();
            }
        });

        rellenarFechaHora();
        rellenarMenu();
        imgFondoPanelCentral();
        this.setVisible(true);
    }

    /**
     * Muestra el mensaje al usuario para comprobar que desea cerrar la aplicacion
     */
    private void onCancelar() {
        int cerrar = JOptionPane.showConfirmDialog(this, "¿Desea cerrar la aplicación?");
        if (JOptionPane.OK_OPTION == cerrar) {
            System.exit(0);
        }
    }

    /**
     * todo
     */
    private void rellenarFechaHora() {
        String fecha = obtenerFecha();
        this.campoFecha.setText(fecha);
    }

    /**
     * Crea el menu para la ventana con las distintas opciones a elegir.
     */
    private void rellenarMenu() {
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuTickets = new JMenu("Tickets");
        JMenu menuCaja = new JMenu("Caja");
        JMenu menuAyuda = new JMenu("Ayuda");
        barraMenu.add(menuTickets);
        barraMenu.add(menuCaja);
        barraMenu.add(menuAyuda);

        JMenuItem verEmpleados = new JMenuItem("Ver Empleados");
        JMenuItem verTalleres = new JMenuItem("Ver Talleres");
        menuCaja.add(verEmpleados);
        menuCaja.add(verTalleres);

        JMenuItem cierre = new JMenuItem("Cierre");
        menuTickets.add(cierre);

        this.setJMenuBar(barraMenu);

        verEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { rellenarPanelCentral(EMPLEADOS); }
        });
        verTalleres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { rellenarPanelCentral(TALLERES); }
        });

        cierre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { rellenarPanelCentral(CIERRE); }
        });
    }

    /**
     * Rellena el panel central en funcion de la opcion elegida
     */
    private void rellenarPanelCentral(String opcion) {
        JDesktopPane panelCentral = new JDesktopPane();
        this.getContentPane().remove(3);
        this.getContentPane().add(panelCentral);
        this.getContentPane().revalidate();
        this.repaint();

        if(opcion.equals(EMPLEADOS)) {
            ConsultarEmpTaller form = new ConsultarEmpTaller(EMPLEADOS);
            form.setClosable(false);
            form.toFront();
            form.setVisible(true);
            panelCentral.add(form);
        } else if(opcion.equals(TALLERES)) {
            ConsultarEmpTaller form = new ConsultarEmpTaller(TALLERES);
            form.setClosable(false);
            form.toFront();
            form.setVisible(true);
            panelCentral.add(form);
        } else if(opcion.equals(CIERRE)) {
            CierreCaja form = new CierreCaja();
            form.setClosable(false);
            form.toFront();
            form.setVisible(true);
            panelCentral.add(form);
        }
    }

    /**
     * todo
     */
    private void imgFondoPanelCentral(){
        JDesktopPane panelCentral = new JDesktopPane();
        panelCentral.setBackground(Color.gray);
        this.getContentPane().add(panelCentral);
            //img de fondo
    }
}
