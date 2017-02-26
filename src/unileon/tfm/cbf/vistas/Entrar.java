package unileon.tfm.cbf.vistas;

import unileon.tfm.cbf.ayuda.CrearAyuda;
import unileon.tfm.cbf.baseDatos.Consultas.ConsultasSeleccionar;

import javax.swing.*;
import java.awt.event.*;

public class Entrar extends JDialog {
    private JPanel contenedor;
    private JButton btnEntrar;
    private JButton btnCancelar;
    private JLabel etqUsuario;
    private JLabel etqContrasenia;
    private JPasswordField cmpoContrasenia;
    private JComboBox comboUsuario;
    private JPanel panelBotones;
    private JPanel panelCampos;
    private JPanel panelSeparador;
    private JButton btnAyuda;
    private JPanel panelAyuda;

    private String[][] usuarios;

    public Entrar() {
        this.setContentPane(contenedor);
        //para que la nueva ventana siempre quede encima
        this.setModal(true);
        getRootPane().setDefaultButton(btnEntrar);

        this.setLocationRelativeTo(null);
        this.pack();


        rellenarComboUsuarios();

        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onEntrar();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancelar();
            }
        });

        // llama a la misma funcion que cancelar cuando se hace click en la X
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancelar();
            }
        });


        btnAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { onBotonAyuda();}
        });
        this.setVisible(true);
    }

    private void onBotonAyuda() {
        CrearAyuda vnAyuda = new CrearAyuda();
        vnAyuda.crearAyuda(this.btnAyuda);
    }

    private void rellenarComboUsuarios() {
        ConsultasSeleccionar consulta = new ConsultasSeleccionar();
        consulta.conectar();
        this.usuarios = consulta.getUsuarios();
        consulta.desconectar();

        //se aniaden los usuarios al combobox
        for(int i = 0; i < this.usuarios.length; i++){
            this.comboUsuario.addItem(this.usuarios[i][0]);
        }
    }

    private void onEntrar() {
        dispose();
        boolean puedeEntrar = false;
        String contrasenia = new String(this.cmpoContrasenia.getPassword());
        String usuarioSelecc = this.comboUsuario.getSelectedItem().toString();

        for(int i = 0; i < this.usuarios.length; i++) {
            if (usuarioSelecc == this.usuarios[i][0] && contrasenia.equals(this.usuarios[i][1])) {
                //entramos a la siguiente ventana que es la generica
                puedeEntrar = true;
            }
        }

        if(!puedeEntrar) {
            JOptionPane.showMessageDialog(this, "Combinacion de usuario y contrasenia incorrecta.");
        }
    }

    private void onCancelar() {
        System.exit(0);
    }
}
