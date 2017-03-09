package unileon.tfm.cbf.vistas.internas.consultasCaja;

import unileon.tfm.cbf.baseDatos.ConsultasBD.ConsultasSeleccionar;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * //todo
 */
public class ConsultarEmpleados extends JInternalFrame {

    private JPanel panelCentral;
    private JTable tablaEmpleados;

    public ConsultarEmpleados() {
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);

        this.setContentPane(this.panelCentral);
        this.pack();
        this.setVisible(true);
        crearTablaEmpleados();
        rellenarTablaEmpleados();
    }

    /**
     * //TODO
     */
    private void crearTablaEmpleados() {
       // String[][] empleados = obtenerDatosEmpleados();
        String[] nombresCols = obtenerNombreColsEmpleados();

        DefaultTableModel modelo = new DefaultTableModel();
        this.tablaEmpleados.setModel(modelo);

        for (int i = 0; i < nombresCols.length; i++) {
            modelo.addColumn(nombresCols[i]);
        }


    }

    /**
     * //TODO
     * @return
     */
    private String[] obtenerNombreColsEmpleados() {
        ConsultasSeleccionar consulta = new ConsultasSeleccionar();
        consulta.conectar();
        String[] nombresCols = consulta.getNombresColsEmpleados();
        consulta.desconectar();
        return nombresCols;
    }

    /**
     * //TODO
     */
    private void rellenarTablaEmpleados() {

    }

    /**
     * //TODO
     * @return
     */
    private String[][] obtenerDatosEmpleados() {
        ConsultasSeleccionar consulta = new ConsultasSeleccionar();
        consulta.conectar();
        String[][] empleado = consulta.getEmpleados();
        consulta.desconectar();
        return empleado;
    }

}
