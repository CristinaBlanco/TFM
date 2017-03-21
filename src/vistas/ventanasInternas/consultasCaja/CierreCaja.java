package vistas.ventanasInternas.consultasCaja;

import controladores.PrincipalControlador;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controladores.CierreCajaControlador.*;

/**
 * Clase utilizada para hacer el cierre de la caja del dia
 * Created by Cristina Blanco Fernandez.
 */
public class CierreCaja extends JInternalFrame{

    private JPanel panelBase;
    private JPanel panelBotones;
    private JButton btnAceptarCierre;
    private JPanel panelCentro;
    private JPanel panelCierre;
    private JPanel panelArqueo;
    private JSeparator separadorTotal;
    private JTextField campo200e;
    private JTextField campo100e;
    private JTextField campo50e;
    private JTextField campo20e;
    private JTextField campo10e;
    private JTextField campo5e;
    private JTextField campo2e;
    private JTextField campo1e;
    private JTextField campo50c;
    private JTextField campo20c;
    private JTextField campo10c;
    private JTextField campo5c;
    private JTextField campo2c;
    private JTextField campo1c;
    private JTextField campoTotal;
    private JTextField cmpSaldoInicial;
    private JTextField cmpVentasT;
    private JTextField cmpVentasE;
    private JTextField cmpEntradas;
    private JTextField cmpSalidas;
    private JTextField cmpFecha;
    private JTextField cmpEfectTeorico;
    private JTextField cmpEfectReal;
    private JTextField cmpDiferencia;
    private JSeparator separadorCuenta;
    private JSeparator separadorFecha;
    private JButton btnContabilizar;


    public CierreCaja() {
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);

        this.setContentPane(this.panelBase);
        this.setVisible(true);
        this.pack();

        crearAccionesCajasTexto();
        rellenarCierre();

        btnAceptarCierre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //imprimir ticket y cerrar el dia en la bbdd
            }
        });
        btnContabilizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { calcularDiferenciaEfectivo();}
        });
    }

    /**
     * Asigna una accion al pulsar enter en cada caja de texto del arqueo.
     * Ademas, al hacer enter en una caja de texto, automaticamente pasa a la siguiente caja
     */
    private void crearAccionesCajasTexto(){
        this.campo200e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo100e.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), DOSCIENTOSEUROS); }
        });
        this.campo100e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo50e.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), CIENEUROS); }
        });
        this.campo50e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo20e.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), CINCUENTAEUROS);
            }
        });
        this.campo20e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo10e.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), VEINTEEUROS);
            }
        });
        this.campo10e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo5e.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), DIEZEUROS);
            }
        });
        this.campo5e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo2e.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), CINCOEUROS);
            }
        });
        this.campo2e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo1e.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), DOSEUROS);
            }
        });
        this.campo1e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo50c.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), UNEURO);
            }
        });
        this.campo50c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo20c.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), CINCUENTACENT);
            }
        });
        this.campo20c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo10c.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), VEINTECENT);
            }
        });
        this.campo10c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo5c.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), DIEZCENT);
            }
        });
        this.campo5c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo2c.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), CINCOCENT);
            }
        });
        this.campo2c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                campo1c.requestFocus();
                sumarAlTotal(((JTextField)e.getSource()).getText(), DOSCENT);
            }
        });
        this.campo1c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { sumarAlTotal(((JTextField)e.getSource()).getText(), UNCENT);

            }
        });
    }

    /**
     * Metodo para ir acumulando el total del dinero de la caja en el arqueo
     * @param valorIntroducido cantidad de monedas o billetes del correspondiente campo
     * @param valorMoneda valor de la moneda o billete en el que se introduce el dato
     */
    private void sumarAlTotal(String valorIntroducido, double valorMoneda) {
        boolean valorCorrecto = comprobarValorIntroducido(valorIntroducido);
        if(!valorCorrecto) {
            JOptionPane.showMessageDialog(this, "Debe introducir solo números sin decimales.");
        }
        this.campoTotal.setText(sumarTotal(this.campoTotal.getText(), valorMoneda, valorIntroducido));
    }

    /**
     * Calcula la diferencia de dinero entre el de la caja y el dinero real contado por el usuario
     */
    private void calcularDiferenciaEfectivo() {
        this.cmpEfectReal.setText(this.campoTotal.getText());
        this.cmpDiferencia.setText(calcularDiferencia(this.campoTotal.getText(), this.cmpEfectTeorico.getText()));
    }


    private void rellenarCierre() {
        String fecha = PrincipalControlador.obtenerFecha();
        this.cmpFecha.setText(fecha);
    }



}
