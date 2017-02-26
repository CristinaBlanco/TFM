package unileon.tfm.cbf.baseDatos.Consultas;

import unileon.tfm.cbf.baseDatos.Conexion;

/**
 * Esta clase se utiliza para enviar las sentencias de seleccionar datos de la base de datos.
 * Created by Cristina Blanco Fernandez.
 */
public class ConsultasSeleccionar extends Conexion{

    /**
     * Obtiene los usuarios del sistema asi como sus contrasenias ordenados por orden alfabetico descendente.
     * @return matriz con los resultados.
     */
    public String[][] getUsuarios(){
        String[][] usuarios = this.seleccionar("USUARIOS", "CATEGORIA,CONTRASENIA", null, "CATEGORIA DESC");
        return usuarios;
    }
}
