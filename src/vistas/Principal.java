package vistas;

import controladores.PrincipalControlador;
import vistas.ventanasInternas.consultasCaja.ConsultarEmpleados;
import vistas.ventanasInternas.consultasCaja.ConsultarTalleres;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


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
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JFormattedTextField campoFecha;
    private JFormattedTextField formattedTextField1;

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
                PrincipalControlador.llamarCalculadora();
            }
        });

        rellenarFechaHora();
        rellenarMenu();
        rellenarPanelCentral(0);
        this.setVisible(true);
    }

    /**
     * todo
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
        String fecha = PrincipalControlador.obtenerFecha();
        this.campoFecha.setText(fecha);
    }

    /**
     * todo
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
            public void actionPerformed(ActionEvent e) {
                System.out.println("estoy en empleados");
                rellenarPanelCentral(1);
            }
        });
        verTalleres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("estoy en talleres");
                rellenarPanelCentral(2);
            }
        });
    }

    /**
     * todo
     */
    private void rellenarPanelCentral(int seleccion){
        JDesktopPane panelCentral = new JDesktopPane();
        panelCentral.setBackground(Color.gray);
        this.getContentPane().add(panelCentral);

        if(seleccion == 0) {
            //img de fondo
        }
        else if(seleccion == 1) {
            System.out.println("entro en seleccion 1");
            ConsultarEmpleados form = new ConsultarEmpleados();
            panelCentral.removeAll();
            panelCentral.add(form);
            panelCentral.moveToFront(form);
            form.setClosable(false);
            form.toFront();
            form.setVisible(true);
           // panelCentral.removeAll();

            //panelCentral.repaint();
        } else if(seleccion == 2){
            System.out.println("entro en seleccion 2");
            ConsultarTalleres form = new ConsultarTalleres();
            form.setClosable(false);
            form.toFront();
            form.setVisible(true);
            panelCentral.add(form);
        }

        //FormularioInternoPruebas form = new FormularioInternoPruebas();
        /*ConsultarEmpleados form = new ConsultarEmpleados();
        form.setClosable(false);
        form.toFront();
        form.setVisible(true);
        panelCentral.add(form);*/
    }
}
