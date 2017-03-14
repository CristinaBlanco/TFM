package unileon.tfm.cbf.vistas.internas.consultasCaja;

import unileon.tfm.cbf.baseDatos.ConsultasBD.ConsultasSeleccionar;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

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
        ConsultasSeleccionar consulta = new ConsultasSeleccionar();
        consulta.conectar();
        String[] nombresCols = consulta.getNombresColsEmpleados();
        consulta.desconectar();

        DefaultTableModel modelo = new DefaultTableModel();
        this.tablaEmpleados.setModel(modelo);


        for (int i = 0; i < nombresCols.length; i++) {
            modelo.addColumn(nombresCols[i]);
        }
    }

    /**
     * //TODO
     */
    private void rellenarTablaEmpleados() {
        ConsultasSeleccionar consulta = new ConsultasSeleccionar();
        consulta.conectar();
        String[][] datosEmpleados = consulta.getEmpleados();
        consulta.desconectar();

        DefaultTableModel modelo = (DefaultTableModel)this.tablaEmpleados.getModel();

        for(int i = 0; i < datosEmpleados.length; i++) {
                modelo.addRow(datosEmpleados[i]);
        }
    }
}
