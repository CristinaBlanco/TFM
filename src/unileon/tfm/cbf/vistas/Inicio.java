package unileon.tfm.cbf.vistas;

import javax.swing.*;

/**
 * Created by Cristina Blanco Fernandez.
 */
public class Inicio extends JFrame{
    private JLabel etiqUsuario;
    private JLabel etiqContrasenia;
    private JTextField campoUsuario;
    private JPasswordField campoContrasenia;
    private JPanel panelInicio;

    public Inicio() {
        this.setContentPane(panelInicio);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
}
