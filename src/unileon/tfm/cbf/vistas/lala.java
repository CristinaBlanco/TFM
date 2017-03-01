package unileon.tfm.cbf.vistas;

import javax.swing.*;

/**
 * Created by Cristina Blanco Fernandez.
 */
public class lala{
    private JPanel panel;
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new lala().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();


        JMenuBar jmb = new JMenuBar();
        frame.setJMenuBar(jmb);
        JMenu tickets = new JMenu("tickets");
        jmb.add(tickets);
        JMenu caja = new JMenu("caja");
        jmb.add(caja);
        JMenuItem apertura = new JMenuItem("apertura");
        caja.add(apertura);
        JMenuItem cierre = new JMenuItem("cierre");
        caja.add(cierre);
        frame.setVisible(true);
    }


}
