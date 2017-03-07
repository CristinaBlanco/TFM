package unileon.tfm.cbf.controladores;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cristina Blanco Fernandez.
 */
public class PrincipalControlador {

    public static String obtenerFecha() {
        String fecha;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Date hoy = new Date();
        fecha = sdf.format(hoy);
        return fecha;
    }

    public static void llamarCalculadora() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("calc");
            p.waitFor();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

}
