package modelosTablas;

import javax.swing.table.DefaultTableModel;

/**
 * Created by Cristina Blanco Fernandez.
 */
public class ModeloTablaNoEditable extends DefaultTableModel {

    public boolean isCellEditable(int row, int column) {
        // Aquí devolvemos true o false según queramos que una celda
        // identificada por fila,columna (row,column), sea o no editable
        //if (column == 3)
        //  return true;
        return false;
    }
}
