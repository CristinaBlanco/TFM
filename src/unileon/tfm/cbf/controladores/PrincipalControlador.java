package unileon.tfm.cbf.controladores;

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

}
