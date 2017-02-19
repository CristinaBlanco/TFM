package unileon.tfm.cbf.controlador.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Esta clase se encarga de conectar, desconectar y enviar consultas a la base de datos.
 * Created by Cristina Blanco Fernandez.
 */
public class Conexion {
    private Connection conexion;

    /**
     * Metodo para conectar con la base de datos mediante el nombre de la base, usuario, contrasenia y driver utilizado.
     * @return true en caso de que se haya conectado correctamente, false en caso contrario.
     */
    public boolean conectar() {
        boolean conectado = false;
        if (this.conexion == null) {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE";
                this.conexion = DriverManager.getConnection(BaseDeDatos, "TFM", "cbf");
                conectado = true;
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("No se puede conectar: ");
            }
        } else {
            System.out.println("Ya existe una conexión.");
        }
        return conectado;
    }

    /**
     * Desconecta la sesion con la base de datos en caso de que exista una sesion abierta.
     * @return true en caso de que se desconecte correctamente, false en caso contrario.
     */
    public boolean desconectar() {
        boolean desconectado = false;
        if(this.conexion != null) {
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
     * Selecciona datos de la tabla de la base de datos.
     * @param tabla tabla de la base de datos de la que se quieren obtener datos
     * @param columna columna de la tabla de la que obtener datos, puede ser mas de una
     * @param where restriccion para que obtenga los datos bajo esa condicion
     * @param order restriccion que indica el orden por el que se devuelven los resultados
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
            } catch (Throwable localThrowable1) {
                throwableSelecc = localThrowable1;
                throw localThrowable1;
            } finally {
                if (res != null) {
                    if (throwableSelecc != null) {
                        try {
                            res.close();
                        } catch (Throwable localThrowable2) {
                            throwableSelecc.addSuppressed(localThrowable2);
                        }
                    } else {
                        res.close();
                    }
                }
            }
        } catch (SQLException e) {
        }
        String[][] data = new String[registros][colname.length];
        try {
            PreparedStatement pstm = this.conexion.prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            Throwable throwableSelecc2 = null;
            try {
                int i = 0;
                while (res.next()) {
                    for (int j = 0; j <= colname.length - 1; j++) {
                        data[i][j] = res.getString(colname[j]);
                    }
                    i++;
                }
            } catch (Throwable localThrowable8) {
                throwableSelecc2 = localThrowable8;
                throw localThrowable8;
            } finally {
                if (res != null) {
                    if (throwableSelecc2 != null) {
                        try {
                            res.close();
                        } catch (Throwable localThrowable5) {
                            throwableSelecc2.addSuppressed(localThrowable5);
                        }
                    } else {
                        res.close();
                    }
                }
            }
        } catch (SQLException e) {
        }
        return data;
    }

    /**
     * Aniade un dato a la tabla indicada en la base de datos. Se recuerda que cada dato esta formado por varios valores
     * que corresponden a las columnas, una fila es un dato.
     * @param tabla tabla en la que se desean aniadir datos.
     * @param columnas columnas en las que ira el nuevo dato.
     * @param valores nuevos valores para el nuevo dato.
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
     * @param tabla tabla de la que se quieren actualizar los datos.
     * @param columna columna en la que estan los datos a actualizar.
     * @param valor nuevos valores para esos datos.
     * @param condicion restriccion que indica en que caso se deben actualizar los datos.
     * @return true en caso de que se haya actualizado correctamente, false en caso contrario.
     */
    public boolean actualizar(String tabla, String columna, String valor, String condicion) {
        boolean actualizado = false;
        String sentenciaActualizar = " UPDATE " + tabla + " SET " + columna + "=" + valor + " where " + condicion;
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
     * @param tabla tabla de la que se quieren eliminar los datos.
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