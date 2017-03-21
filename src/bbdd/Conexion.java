package bbdd;

import java.sql.*;
import java.util.ArrayList;

/**
 * Esta clase se encarga de conectar, desconectar y enviar consultas a la base de datos.
 * Created by Cristina Blanco Fernandez.
 */
public class Conexion {
    private Connection conexion;
    private final String usuario = "TFM";
    private final String contrasenia = "cbf";

    /**
     * Metodo para conectar con la base de datos mediante el nombre de la base, usuario, contrasenia y driver utilizado.
     *
     * @return true en caso de que se haya conectado correctamente, false en caso contrario.
     */
    public boolean conectar() {
        boolean conectado = false;
        if (this.conexion == null) {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE";
                this.conexion = DriverManager.getConnection(BaseDeDatos, usuario, contrasenia);
                conectado = true;
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("No se puede conectar: " + e);
            }
        } else {
            System.out.println("Ya existe una conexión.");
        }
        return conectado;
    }

    /**
     * Desconecta la sesion con la base de datos en caso de que exista una sesion abierta.
     *
     * @return true en caso de que se desconecte correctamente, false en caso contrario.
     */
    public boolean desconectar() {
        boolean desconectado = false;
        if (this.conexion != null) {
            try {
                this.conexion.close();
                this.conexion = null;
                desconectado = true;
            } catch (SQLException ex) {
            }
        }
        return desconectado;
    }

    /**
     *
     * @param tabla
     * @param order
     * @return
     */
    public String[][] seleccionarTodo(String tabla, String order) {
        String[][] datos = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String q = ("SELECT * FROM " + tabla);
        if (order != null) {
            q = q + " ORDER BY " + order;
        }

        try {
            stmt = this.conexion.prepareStatement(q);
            rs = stmt.executeQuery();
            int numCols = rs.getMetaData().getColumnCount();

            //se utiliza un arraylist para ir almacenando los datos y luego pasarlo a matriz
            //se pasa a matriz para evitar equivocaciones con el resto de metodos que se devuelven en matriz
            ArrayList<String> listaDatos = new ArrayList<>();

            while (rs.next()) {
                for (int i = 1; i <= numCols; i++) {
                    listaDatos.add(rs.getString(i));
                }
            }
            int tamLista = listaDatos.size();
            int numFils = tamLista / numCols;
            datos = new String[numFils][numCols];

            int contador = 0;
            for (int i = 0; i < numFils; i++) {
                for (int j = 0; j < numCols; j++) {
                    datos[i][j] = listaDatos.get(contador);
                    contador++;
                }
            }
        } catch (SQLException e) {
        }
        return datos;
    }

    /**
     * Obtiene los datos solicitados de la tabla
     * @param tabla nombre de la tabla de la que obtener los datos
     * @param columna nombre de la/s columnas de las que se requieren datos
     * @param where parametro que indica la condicion de cuando se deben obtener los datos
     * @param order parametro que indica el orden en el que obtener esos datos
     * @return matriz de dos dimensiones con los datos solicitados
     */
    public String[][] seleccionar(String tabla, String columna, String where, String order) {
        int registros = 0;
        String[] colname = columna.split(",");

        String q = "SELECT " + columna + " FROM " + tabla;
        String q2 = "SELECT count(*) as total FROM " + tabla;
        if (where != null) {
            q = q + " WHERE " + where;
            q2 = q2 + " WHERE " + where;
        }
        if (order != null) {
            q = q + " ORDER BY " + order;
        }
        try {
            PreparedStatement pstm = this.conexion.prepareStatement(q2);
            ResultSet res = pstm.executeQuery();
            Throwable throwableSelecc = null;
            try {
                res.next();
                registros = res.getInt("total");
            } catch (Throwable localThrowable) {
                //TODO exportar error al log o visualizar en pantalla si no permite realizar la operacion
            } finally {
                if (res != null) {
                    if (throwableSelecc != null) {
                        try {
                            res.close();
                        } catch (Throwable localThrowable) {
                            //throwableSelecc.addSuppressed(localThrowable2);
                            //TODO exportar error al log o visualizar en pantalla si no permite realizar la operacion
                        }
                    } else {
                        res.close();
                    }
                }
            }
        } catch (SQLException e) {
            //TODO exportar error al log o visualizar en pantalla si no permite realizar la operacion
        }

        String[][] data = new String[registros][colname.length];
        try {
            PreparedStatement pstm = this.conexion.prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            Throwable throwableSelecc2 = null;
            try {
                int filas = 0;
                while (res.next()) {
                    for (int j = 0; j <= colname.length - 1; j++) {
                        data[filas][j] = res.getString(colname[j]);
                    }
                    filas++;
                }
            } catch (Throwable localThrowable) {
                //TODO exportar error al log o visualizar en pantalla si no permite realizar la operacion
            } finally {
                if (res != null) {
                    if (throwableSelecc2 != null) {
                        try {
                            res.close();
                        } catch (Throwable localThrowable) {
                            //TODO exportar error al log o visualizar en pantalla si no permite realizar la operacion
                        }
                    } else {
                        res.close();
                    }
                }
            }
        } catch (SQLException e) {
            //TODO exportar error al log o visualizar en pantalla si no permite realizar la operacion
        }
        return data;
    }

    /**
     * Obtiene los nombres de las columnas de la tabla indicada.
     * @param tabla nombre de la tabla de la que obtener las columnas
     * @return matriz de una dimension con los nombres de las columnas
     */
    public String[] seleccionarNombresCols(String tabla) {
        String[] nombresCols = null;
        String q = ("SELECT column_name FROM all_tab_columns WHERE table_name = '" + tabla + "'");

        try {
            PreparedStatement pstm = this.conexion.prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            Throwable throwableSelecc = null;
            try {
                //se utiliza un arraylist para ir almacenando los datos y luego pasarlo a matriz
                //se pasa a matriz para evitar equivocaciones con el resto de metodos que se devuelven en matriz
                ArrayList<String> listaNombres = new ArrayList<>();
                while (res.next()) {
                    listaNombres.add(res.getString(1));
                }
                nombresCols = listaNombres.toArray(new String[listaNombres.size()]);
            } catch (Throwable localThrowable) {
                //TODO exportar error al log o visualizar en pantalla si no permite realizar la operacion
            } finally {
                if (res != null) {
                    if (throwableSelecc != null) {
                        try {
                            res.close();
                        } catch (Throwable localThrowable) {
                            //throwableSelecc.addSuppressed(localThrowable2);
                            //TODO exportar error al log o visualizar en pantalla si no permite realizar la operacionç
                        }
                    } else {
                        res.close();
                    }
                }
            }
        } catch (SQLException e) {
            //TODO exportar error al log o visualizar en pantalla si no permite realizar la operacion
        }
        return nombresCols;
    }

    /**
     * Aniade un dato a la tabla indicada en la base de datos. Se recuerda que cada dato esta formado por varios valores
     * que corresponden a las columnas, una fila es un dato.
     *
     * @param tabla    tabla en la que se desean aniadir datos.
     * @param columnas columnas en las que ira el nuevo dato.
     * @param valores  nuevos valores para el nuevo dato.
     * @return true si se ha aniadido el dato correctamente, false en caso contrario.
     */
    public boolean insertar(String tabla, String columnas, String valores) {
        boolean insertado = false;

        String sentenciaInsertar = " INSERT INTO " + tabla + " ( " + columnas + " ) VALUES ( " + valores + " ) ";
        try {
            PreparedStatement pstm = this.conexion.prepareStatement(sentenciaInsertar);
            Throwable throwableInsertar = null;
            try {
                pstm.execute();
            } catch (Throwable localThrowable1) {
                throwableInsertar = localThrowable1;
                throw localThrowable1;
            } finally {
                if (pstm != null) {
                    if (throwableInsertar != null) {
                        try {
                            pstm.close();
                        } catch (Throwable localThrowable2) {
                            throwableInsertar.addSuppressed(localThrowable2);
                        }
                    } else {
                        pstm.close();
                    }
                }
            }
            insertado = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return insertado;
    }

    /**
     * Actualiza datos de la tabla indicada.
     *
     * @param tabla     tabla de la que se quieren actualizar los datos.
     * @param columna   columna en la que estan los datos a actualizar.
     * @param valor     nuevos valores para esos datos.
     * @param condicion restriccion que indica en que caso se deben actualizar los datos.
     * @return true en caso de que se haya actualizado correctamente, false en caso contrario.
     */
    public boolean actualizar(String tabla, String columna, String valor, String condicion) {
        boolean actualizado = false;
        String sentenciaActualizar = " UPDATE " + tabla + " SET " + columna + " = " + valor + " where " + condicion;
        try {
            PreparedStatement pstm = this.conexion.prepareStatement(sentenciaActualizar);
            Throwable throwableAct = null;
            try {
                pstm.execute();
            } catch (Throwable localThrowable1) {
                throwableAct = localThrowable1;
                throw localThrowable1;
            } finally {
                if (pstm != null) {
                    if (throwableAct != null) {
                        try {
                            pstm.close();
                        } catch (Throwable localThrowable2) {
                            throwableAct.addSuppressed(localThrowable2);
                        }
                    } else {
                        pstm.close();
                    }
                }
            }
            actualizado = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return actualizado;
    }

    /**
     * Elimina datos de una tabla concreta.
     *
     * @param tabla     tabla de la que se quieren eliminar los datos.
     * @param condicion restriccion que indica que datos se deben eliminar.
     * @return true en caso de que la eliminacion sea satisfactoria, false en caos contrario.
     */
    public boolean eliminar(String tabla, String condicion) {
        boolean eliminado = false;
        String sentenciaEliminar = " DELETE FROM " + tabla + " where " + condicion;
        try {
            PreparedStatement pstm = this.conexion.prepareStatement(sentenciaEliminar);
            Throwable throwableEliminar = null;
            try {
                pstm.executeUpdate();
            } catch (Throwable localThrowable1) {
                throwableEliminar = localThrowable1;
                throw localThrowable1;
            } finally {
                if (pstm != null) {
                    if (throwableEliminar != null) {
                        try {
                            pstm.close();
                        } catch (Throwable localThrowable2) {
                            throwableEliminar.addSuppressed(localThrowable2);
                        }
                    } else {
                        pstm.close();
                    }
                }
            }
            eliminado = true;
        } catch (SQLException e) {
        }
        return eliminado;
    }
}