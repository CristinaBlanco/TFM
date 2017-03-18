package unileon.tfm.cbf;

import unileon.tfm.cbf.codigo.vistas.Acceso;

/**
 * Created by Cristina Blanco Fernandez.
 */
public class Inicio {

    public static void main(String[] args) {
        Acceso entrada = new Acceso();
        entrada.setVisible(true);
       /* ConsultasSeleccionar seleccionar = new ConsultasSeleccionar();
        seleccionar.conectar();
        String[][] nombres = seleccionar.getEmpleados();
        seleccionar.desconectar();*/

        /*Conexion consultas = new Conexion();
        consultas.conectar();
        String[] nombres = consultas.seleccionarNombresCols("EMPLEADOS");

        consultas.desconectar();
        System.out.println("tam final: " + nombres.length);

        for(int i = 0; i < nombres.length; i++) {
            //for (int j = 0; j < nombres[i].length; j++) {
                System.out.print(nombres[i] + "\t");

            //}
            System.out.println();
        }*/
    }
}
