package vistas.ventanasInternas.consultasCaja;

import bbdd.consultas.ConsultasActualizar;
import bbdd.consultas.ConsultasSeleccionar;
import modelosTablas.ModeloTablaNoEditable;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * //todo
 */
public class ConsultarEmpleados extends JInternalFrame {

    private JPanel panelCentral;
    private JTable tablaEmpleados;
    private JPanel panelBotones;
    private JButton btnEditar;

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

    /**
     * //todo
     */
    private void crearEventosIniciales() {
        this.btnEditar.setEnabled(false);

        this.tablaEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(tablaEmpleados.getSelectedColumn() > 0) {
                    btnEditar.setEnabled(true);
                }

            }
        });
        this.btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarContraseniaAdmin();
            }
        });
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
    private void solicitarContraseniaAdmin() {
        ConsultasSeleccionar consultas = new ConsultasSeleccionar();
        consultas.conectar();
        String[][] contraAdmin = consultas.getContraseniaAdministrador();
        consultas.desconectar();
        System.out.print(contraAdmin.length);

        JPasswordField contraAdminEscrita = new JPasswordField("Introduzca la contrasña del administrador:");
        if(contraAdmin[0][0].equals(contraAdminEscrita)) {
            editarDatosEmpleados();
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "La contraseña introducida es incorrecta.No puede cambiar los datos.");
        }
    }

    /**
     * todo
     */
    private void editarDatosEmpleados() {
        int columnaSelecc = this.tablaEmpleados.getSelectedColumn();
        int filaSelecc = this.tablaEmpleados.getSelectedRow();
        String idEmpSelecc = this.tablaEmpleados.getValueAt(0, columnaSelecc).toString();
        String nombreColSelecc = this.tablaEmpleados.getColumnName(columnaSelecc);
        String nuevoDato = this.tablaEmpleados.getValueAt(filaSelecc,columnaSelecc).toString();

        ConsultasActualizar consultas = new ConsultasActualizar();
        consultas.conectar();
        boolean ok = consultas.actDatosEmpleados(nombreColSelecc, nuevoDato, idEmpSelecc);
        consultas.desconectar();
        System.out.println(ok);
    }
}
