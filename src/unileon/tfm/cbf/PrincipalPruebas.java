package unileon.tfm.cbf;

import unileon.tfm.cbf.unileon.tfm.cbf.database.Conexion;

public class PrincipalPruebas {

    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        //conexion.conectar();

        conexion.desconectar();
    }
}
