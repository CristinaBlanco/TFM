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
     * Obtiene todos los empleados actuales, asi como todos sus datos almacenados
     * @return matriz de dos dimensiones con los datos ordenados por el id del empleado
     */
    public String[][] getEmpleados() {
        String[][] empleados = this.seleccionarTodo("EMPLEADOS", "ID_EMPLEADO");
        return empleados;
    }

    /**
     * Obtiene todos los talleres actuales, asi como todos sus datos correspondientes
     * @return matriz de dos dimensiones con los datos ordenados por el nombre del taller
     */
    public String[][] getTalleres() {
        String[][] talleres = this.seleccionarTodo("TALLERES", "NOMBRE");
        return talleres;
    }

    /**
     * Obtiene los nombres de las columnas de los empleados, es decir, el tipo de datos almacenados.
     * @return matriz de una dimension con los datos solicitados.
     */
    public String[] getNombresColsEmpleados() {
        String[] nombres = this.seleccionarNombresCols("EMPLEADOS");
        return nombres;
    }

    /**
     * Obtiene los nombres de las columnas de los talleres, es decir, el tipo de datos almacenados.
     * @return matriz de una dimension con los datos solicitados.
     */
    public String[] getNombresColsTalleres() {
        String[] nombres = this.seleccionarNombresCols("TALLERES");
        return nombres;
    }

    public String[][] getContraseniaAdministrador() {
        String[][] contrasenia = this.seleccionar("USUARIOS", "CONTRASENIA", "CATEGORIA='Administrador'", null);
        return contrasenia;
    }
}
