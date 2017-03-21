package controladores;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Cristina Blanco Fernandez.
 */
public class CierreCajaControlador {
    public final static double DOSCIENTOSEUROS = 200;
    public final static double CIENEUROS = 100;
    public final static double CINCUENTAEUROS = 50;
    public final static double VEINTEEUROS = 20;
    public final static double DIEZEUROS = 10;
    public final static double CINCOEUROS = 5;
    public final static double DOSEUROS = 2;
    public final static double UNEURO = 1;
    public final static double CINCUENTACENT = 0.50;
    public final static double VEINTECENT = 0.20;
    public final static double DIEZCENT= 0.10;
    public final static double CINCOCENT = 0.05;
    public final static double DOSCENT = 0.02;
    public final static double UNCENT = 0.01;

    /**
     * Comprueba que el valor introducido por el usuario esta dentro de ser un numero entero
     * @param valorIntroducido valor que comprobar
     * @return boolean si el valor es correcto, false en caso contrario.
     */
    public static boolean comprobarValorIntroducido(String valorIntroducido) {
        boolean valorCorrecto = true;
        if(!valorIntroducido.isEmpty()) {
            try {
                Integer.parseInt(valorIntroducido);
            } catch (NumberFormatException e) {
                valorCorrecto = false;
            }
        }
        return valorCorrecto;
    }


    /**
     * Suma a una cantidad existente, otra formada por una cantidad de monedas/billetes y su valor
     * @param sumaAnterior cadena con el valor de la suma anterior a la que sumar la nueva cantidad
     * @param valorMoneda valor que tiene la moneda o billete. Ej: el billete vale 100 euros
     * @param cantidadSumar cadena con el valor con la cantidad de esos billetes/monedas que hay
     * @return cadena de texto con la nueva suma total
     */
    public static String sumarTotal(String sumaAnterior, double valorMoneda, String cantidadSumar) {
        int cantidadBilletes = 0;
        if(!cantidadSumar.isEmpty()) {
            cantidadBilletes = Integer.parseInt(cantidadSumar);
        }

        double dineroAnterior = 0;
        if(!sumaAnterior.isEmpty()) {
            dineroAnterior = Double.parseDouble(sumaAnterior);
        }

        double nuevaSuma = dineroAnterior + (valorMoneda * cantidadBilletes);
        //para que solo tenga dos decimales el valor total
        BigDecimal bd = new BigDecimal(nuevaSuma).setScale(2, RoundingMode.HALF_EVEN);
        nuevaSuma = bd.doubleValue();
        return Double.toString(nuevaSuma);
    }

    /**
     * Calcula la diferencia entre el dinero total introducido por el usuario y el dinero teorico que esta en la caja
     * @param efectivoUsuario cantidad de dinero que ha introducido el usuario
     * @param efectivoTeorico cantidad de dinero teorico que deberia haber en la caja
     * @return cadena de texto con la diferencia
     */
    public static String calcularDiferencia(String efectivoUsuario, String efectivoTeorico) {
        double efectivoCalculado = Double.parseDouble(efectivoUsuario);
        double diferencia = Double.parseDouble(efectivoTeorico) - efectivoCalculado;
        //para que solo tenga dos decimales el valor total
        BigDecimal bd = new BigDecimal(diferencia).setScale(2, RoundingMode.HALF_EVEN);
        diferencia = bd.doubleValue();
        return Double.toString(diferencia);
    }

}
