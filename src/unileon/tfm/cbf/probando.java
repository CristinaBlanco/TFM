package unileon.tfm.cbf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cristina Blanco Fernandez.
 */
public class probando extends JFrame {
    private JPanel panel;
    private JButton clickHereButton;
    private JTextField cagadaCadaVezQueTextField;
    private JButton aVerQueEsButton;

    public probando() {
        initComponents();
    }

    private void accionClickHereButton() {
        System.out.println("cojones");
    }

    private void accionAVerQueEsButton() {
        System.out.println("cojoneeees");
    }

    private void initComponents() {
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.setVisible(true);
        clickHereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionClickHereButton();

            }
        });

        aVerQueEsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionAVerQueEsButton();
            }
        });
    }


}
