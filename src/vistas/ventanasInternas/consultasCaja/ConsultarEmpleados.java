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
 * //todo
 * TODO LO QUE ESTA COMENTADO ES POR SI AL FINAL DECIDO QUE PARA CAMBIAR DATOS DE EMPLEADOS HAY QUE METER LA CONTRASENIA DEL ADMIN
 */
public class ConsultarEmpleados extends JInternalFrame {

    private JPanel panelCentral;
    private JTable tablaEmpleados;
    private JPanel panelBotones;
    //private JButton btnEditar;

    public ConsultarEmpleados() {
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);

        this.setContentPane(this.panelCentral);
        this.pack();
        this.setVisible(true);

        crearEventosIniciales();
        crearTablaEmpleados();
        rellenarTablaEmpleados();
    }

   /* /**
     * //todo
     */
    private void crearEventosIniciales() {
        //this.btnEditar.setEnabled(false);

        this.tablaEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                //if(e.getClickCount() > 1)

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
     * //TODO
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

    /**
     * todo
     */
    /*private void solicitarContraseniaAdmin() {
        ConsultasSeleccionar consultas = new ConsultasSeleccionar();
        consultas.conectar();
        String[][] contraAdmin = consultas.getContraseniaAdministrador();
        consultas.desconectar();

        String contraAdminEscrita = JOptionPane.showInputDialog("Introduzca la contraseña del administrador:");

        if(contraAdmin[0][0].equals(contraAdminEscrita)) {
            editarDatosEmpleados();
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "La contraseña introducida es incorrecta. No puede cambiar los datos.");
        }
    }*/

    /**
     * todo
     */
    private void editarDatosEmpleados() {
        if(this.tablaEmpleados.isEditing()) {
            int columnaSelecc = this.tablaEmpleados.getSelectedColumn();
            int filaSelecc = this.tablaEmpleados.getSelectedRow();
            String idEmpSelecc = this.tablaEmpleados.getValueAt(filaSelecc, 0).toString();
            String nombreColSelecc = this.tablaEmpleados.getColumnName(columnaSelecc);
            String nuevoDato = this.tablaEmpleados.getValueAt(filaSelecc, columnaSelecc).toString();

            ConsultasActualizar consultas = new ConsultasActualizar();
            consultas.conectar();
            boolean oky = consultas.actDatosEmpleados(nombreColSelecc, nuevoDato, idEmpSelecc);
            consultas.desconectar();
            // System.out.print(oky);

            crearTablaEmpleados();
            rellenarTablaEmpleados();
        }
        else {
            System.out.print("caca podrida");
        }
        }
}
