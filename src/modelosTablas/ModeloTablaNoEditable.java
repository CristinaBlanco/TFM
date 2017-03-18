package modelosTablas;

import javax.swing.table.DefaultTableModel;

/**
 * Created by Cristina Blanco Fernandez.
 */
public class ModeloTablaNoEditable extends DefaultTableModel {

    public boolean isCellEditable(int row, int column) {
        //no se puede editar la primera columna
        if (column == 0)
          return false;
        return true;
    }
}
