package bbdd.consultas;

import bbdd.Conexion;

/**
 * Esta clase sirve para enviar las sentencias de actualización a ejecutar en la base de datos.
 * Created by Cristina Blanco Fernandez.
 */
public class ConsultasActualizar extends Conexion{

    /**
     * todo
     */
    public boolean actDatosEmpleados(String columna, String nuevoValor, String idEmpSelecc) {
        boolean actualizado = this.actualizar("EMPLEADOS", columna, "'" + nuevoValor + "'",
                "ID_EMPLEADO='" + idEmpSelecc + "'");
        return actualizado;
    }
}
