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
    public boolean actEmpleados(String columna, String nuevoValor, String idEmpSelecc) {
        boolean actualizado = this.actualizar("EMPLEADOS", columna, "'" + nuevoValor + "'",
                "ID_EMPLEADO='" + idEmpSelecc + "'");
        return actualizado;
    }

    public boolean actTalleres(String columna, String nuevoValor, String nombreTallerSelecc) {
        boolean actualizado = this.actualizar("TALLERES", columna, "'" + nuevoValor + "'",
                "NOMBRE='" + nombreTallerSelecc + "'");
        return actualizado;
    }
}
