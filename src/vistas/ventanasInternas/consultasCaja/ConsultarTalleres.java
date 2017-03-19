package vistas.ventanasInternas.consultasCaja;

import bbdd.consultas.ConsultasActualizar;
import bbdd.consultas.ConsultasSeleccionar;
import modelosTablas.ModeloTablaNoEditable;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Esta clase consulta e imprime en la tabla correspondiente, los empleados que existen en la aplicacion.
 */
public class ConsultarTalleres extends JInternalFrame {

    private JPanel panelCentral;
    private JTable tablaTalleres;

    public ConsultarTalleres() {
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);

        this.setContentPane(this.panelCentral);
        this.setVisible(true);
        this.pack();

        crearEventosIniciales();
        crearTablaTalleres();
        rellenarTablaTalleres();
    }

    /**
     * Asigna un evento a la tabla para que el clic que se haga en ella responda.
     */
    private void crearEventosIniciales() {
        this.tablaTalleres.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() > 1)
                    editarDatosTalleres();
            }
        });
    }

    /**
     * Crea el modelo de la tabla con el nombre de las columnas y se lo asigna a la tabla.
     */
    private void crearTablaTalleres() {
        ConsultasSeleccionar consulta = new ConsultasSeleccionar();
        consulta.conectar();
        String[] nombresCols = consulta.getNombresColsEmpleados();
        consulta.desconectar();

        ModeloTablaNoEditable modelo = new ModeloTablaNoEditable();
        this.tablaTalleres.setModel(modelo);

        for (int i = 0; i < nombresCols.length; i++) {
            modelo.addColumn(nombresCols[i]);
        }
    }

    /**
     * Rellena la tabla con los datos existentes en la base de datos.
     */
    private void rellenarTablaTalleres() {
        ConsultasSeleccionar consulta = new ConsultasSeleccionar();
        consulta.conectar();
        String[][] datosTalleres = consulta.getTalleres();
        consulta.desconectar();

        DefaultTableModel modelo = (DefaultTableModel)this.tablaTalleres.getModel();

        for(int i = 0; i < datosTalleres.length; i++) {
            modelo.addRow(datosTalleres[i]);
        }
    }

    /**
     * Cambia a peticion del usuario, datos sobre los talleres.
     */
    private void editarDatosTalleres() {
        int columnaSelecc = this.tablaTalleres.getSelectedColumn();
        int filaSelecc = this.tablaTalleres.getSelectedRow();
        String nombreTallerSelecc = this.tablaTalleres.getValueAt(filaSelecc, 0).toString();
        String nombreColSelecc = this.tablaTalleres.getColumnName(columnaSelecc);
        String nuevoDato = JOptionPane.showInputDialog("Introduzca el nuevo dato para " + nombreColSelecc);

        ConsultasActualizar consultas = new ConsultasActualizar();
        consultas.conectar();
        consultas.actEmpleados(nombreColSelecc, nuevoDato, nombreTallerSelecc);
        consultas.desconectar();

        crearTablaTalleres();
        rellenarTablaTalleres();
    }
}
