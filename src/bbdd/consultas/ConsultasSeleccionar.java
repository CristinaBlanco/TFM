package bbdd.consultas;

import bbdd.Conexion;

/** //TODO ordenar por tablas por ejemplo
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

    /**
     * //TODO
     * @return
     */
    public String[][] getEmpleados() {
        String[][] empleados = this.seleccionar("EMPLEADOS", "ID_EMPLEADO,NOMBRE,APELLIDOS,DIRECCION,TELEFONO,CATEGORIA_EMPLEADO",
                null, "ID_EMPLEADO");
        return empleados;
    }

    /**
     * //TODO
     * @return
     */
    public String[][] getTalleres() {
        String[][] talleres = this.seleccionar("TALLERES", "NOMBRE,TIPO_TALLER,DIRECCION,TELEFONO,HORARIO,EMPLEADO",
                null, null);
        return talleres;
    }

    public String[] getNombresColsEmpleados() {
        String[] nombres = this.seleccionarNombresCols("EMPLEADOS");
        return nombres;
    }

    public String[][] getContraseniaAdministrador() {
        String[][] contrasenia = this.seleccionar("USUARIOS", "CONTRASENIA", "CATEGORIA='Administrador'", null);
        return contrasenia;
    }
}
