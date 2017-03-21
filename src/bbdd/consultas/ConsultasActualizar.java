package bbdd.consultas;

import bbdd.Conexion;

/**
 * Esta clase sirve para enviar las sentencias de actualización a ejecutar en la base de datos.
 * Created by Cristina Blanco Fernandez.
 */
public class ConsultasActualizar extends Conexion{

    /**
     * Actualiza los datos de los empleados.
     * @param columna nombre de la columna donde actualizar el dato
     * @param nuevoValor nuevo valor para el dato
     * @param idEmpSelecc id del empleado del que cambiar el dato
     * @return true si el cambio se ha realizado correctamente, false en caso contrario
     */
    public boolean actEmpleados(String columna, String nuevoValor, String idEmpSelecc) {
        boolean actualizado = this.actualizar("EMPLEADOS", columna, "'" + nuevoValor + "'",
                "ID_EMPLEADO='" + idEmpSelecc + "'");
        return actualizado;
    }

    /**
     * Actualiza los datos de los talleres.
     * @param columna nombre de la columna de la tabla donde actualizar el dato
     * @param nuevoValor nuevo valor para el dato
     * @param nombreTallerSelecc nombre del taller del que cambiar el dato
     * @return true si el cambio se ha realizado correctamente, false en caso contrario
     */
    public boolean actTalleres(String columna, String nuevoValor, String nombreTallerSelecc) {
        boolean actualizado = this.actualizar("TALLERES", columna, "'" + nuevoValor + "'",
                "NOMBRE='" + nombreTallerSelecc + "'");
        return actualizado;
    }
}
