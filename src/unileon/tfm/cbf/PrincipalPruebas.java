package unileon.tfm.cbf;
import unileon.tfm.cbf.controlador.database.Conexion;


public class PrincipalPruebas {

    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        System.out.println(conexion.conectar());

        conexion.desconectar();
        probando probando2 = new probando();


    }
}
