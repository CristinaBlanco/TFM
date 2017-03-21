package vistas.ventanasInternas.consultasPersonal;

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
public class ConsultarEmpTaller extends JInternalFrame {

    private JPanel panelCentral;
    private JTable tablaDatos;

    private static final String EMPLEADOS = "empleados";
    private static final String TALLERES = "talleres";

    private String opcionSelecc;

    public ConsultarEmpTaller(String opcionConsulta) {
        this.opcionSelecc = opcionConsulta;

        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);

        this.setContentPane(this.panelCentral);
        this.setVisible(true);
        this.pack();

        crearEventosIniciales();
        crearTabla();
        rellenarTabla();
    }

    /**
     * Asigna un evento a la tabla para que el clic que se haga en ella responda.
     */
    private void crearEventosIniciales() {
        this.tablaDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() > 1) {
                    editarDatosTabla();
                }
            }
        });

        //para asignar la tecla F1 al boton
        /*this.btnEditar.getInputMap(btnEditar.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "editar");
        this.btnEditar.getActionMap().put("editar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { accionAlPresionarBoton(); }
        });*/
    }

    /**
     * Crea el modelo de la tabla con el nombre de las columnas y se lo asigna a la tabla.
     */
    private void crearTabla() {
        ConsultasSeleccionar consulta = new ConsultasSeleccionar();
        consulta.conectar();
        String nombresCols[] = null;

        if(this.opcionSelecc.equals(EMPLEADOS)) {
            nombresCols = consulta.getNombresColsEmpleados();
        } else if(this.opcionSelecc.equals(TALLERES)) {
            nombresCols = consulta.getNombresColsTalleres();
        }
        consulta.desconectar();

        ModeloTablaNoEditable modelo = new ModeloTablaNoEditable();
        this.tablaDatos.setModel(modelo);

        for (int i = 0; i < nombresCols.length; i++) {
            modelo.addColumn(nombresCols[i]);
        }
    }

    /**
     * Rellena la tabla con los datos existentes en la base de datos.
     */
    private void rellenarTabla() {
        ConsultasSeleccionar consulta = new ConsultasSeleccionar();
        consulta.conectar();

        String[][] datosEmpleados = null;
        if(this.opcionSelecc.equals(EMPLEADOS)) {
            datosEmpleados = consulta.getEmpleados();
        } else if(this.opcionSelecc.equals(TALLERES)) {
            datosEmpleados = consulta.getTalleres();
        }
        consulta.desconectar();

        DefaultTableModel modelo = (DefaultTableModel)this.tablaDatos.getModel();
        for(int i = 0; i < datosEmpleados.length; i++) {
            modelo.addRow(datosEmpleados[i]);
        }
    }

    /**
     * Cambia datos de la tabla a peticion del usuario.
     */
    private void editarDatosTabla() {
        int columnaSelecc = this.tablaDatos.getSelectedColumn();
        int filaSelecc = this.tablaDatos.getSelectedRow();
        String idSelecc = this.tablaDatos.getValueAt(filaSelecc, 0).toString();
        String nombreColSelecc = this.tablaDatos.getColumnName(columnaSelecc);
        String nuevoDato = JOptionPane.showInputDialog("Introduzca el nuevo dato para " + nombreColSelecc);

        ConsultasActualizar consultas = new ConsultasActualizar();
        consultas.conectar();
        if(this.opcionSelecc.equals(EMPLEADOS)) {
            consultas.actEmpleados(nombreColSelecc, nuevoDato, idSelecc);
        } else if(this.opcionSelecc.equals(TALLERES)) {
            consultas.actTalleres(nombreColSelecc, nuevoDato, idSelecc);
        }
        consultas.desconectar();

        crearTabla();
        rellenarTabla();
    }
}
