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
public class ConsultarEmpleados extends JInternalFrame {

    private JPanel panelCentral;
    private JTable tablaEmpleados;

    public ConsultarEmpleados() {
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);

        this.setContentPane(this.panelCentral);

        this.setVisible(true);
        this.pack();

        crearEventosIniciales();
        crearTablaEmpleados();
        rellenarTablaEmpleados();
    }

    /**
     * Asigna un evento a la tabla para que el clic que se haga en ella responda.
     */
    private void crearEventosIniciales() {
        this.tablaEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() > 1)
                    editarDatosEmpleados();
            }
        });

        //para asignar la tecla F1 al boton de editar
        /*this.btnEditar.getInputMap(btnEditar.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "editar");
        this.btnEditar.getActionMap().put("editar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { solicitarContraseniaAdmin(); }
        });

        this.btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarContraseniaAdmin();
            }
        });*/
    }

    /**
     * Crea el modelo de la tabla con el nombre de las columnas y se lo asigna a la tabla.
     */
    private void crearTablaEmpleados() {
        ConsultasSeleccionar consulta = new ConsultasSeleccionar();
        consulta.conectar();
        String[] nombresCols = consulta.getNombresColsEmpleados();
        consulta.desconectar();

        ModeloTablaNoEditable modelo = new ModeloTablaNoEditable();
        this.tablaEmpleados.setModel(modelo);

        for (int i = 0; i < nombresCols.length; i++) {
            modelo.addColumn(nombresCols[i]);
        }
    }

    /**
     * Rellena la tabla con los datos existentes en la base de datos.
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

    /**
     * Cambia a peticion del usuario, datos sobre los empleados.
     */
    private void editarDatosEmpleados() {
        int columnaSelecc = this.tablaEmpleados.getSelectedColumn();
        int filaSelecc = this.tablaEmpleados.getSelectedRow();
        String idEmpSelecc = this.tablaEmpleados.getValueAt(filaSelecc, 0).toString();
        String nombreColSelecc = this.tablaEmpleados.getColumnName(columnaSelecc);
        String nuevoDato = JOptionPane.showInputDialog("Introduzca el nuevo dato para " + nombreColSelecc);

        ConsultasActualizar consultas = new ConsultasActualizar();
        consultas.conectar();
        consultas.actEmpleados(nombreColSelecc, nuevoDato, idEmpSelecc);
        consultas.desconectar();

        crearTablaEmpleados();
        rellenarTablaEmpleados();
    }
}
