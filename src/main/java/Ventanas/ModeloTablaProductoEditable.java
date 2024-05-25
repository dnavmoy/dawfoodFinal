
package Ventanas;

import javax.swing.table.DefaultTableModel;

// Para usar JTable hay que asociar al JTable un objeto de tipo 
// DefaultTableModel que contenga las columnas correspondientes

public class ModeloTablaProductoEditable extends DefaultTableModel {
    
    public ModeloTablaProductoEditable(){
        // Se asignan los nombres de las columnas de la tabla
        // en función de los atributos que tiene la persona
        String[] columnNames = {"Id","Descripcion","Precio","Iva","Stock","codigoCategoria"}; 
        
        // Se le indica al modelo el nombre de las columnas y cantidad
        this.setColumnIdentifiers(columnNames); 
    }
    
    // Este método sobrescrito sirve para indicar qué celdas
    // del JTable son editables
    public boolean isCellEditable (int row, int column)
    {
        // Aquí devolvemos true o false según queramos que una celda
        // identificada por fila,columna (row,column), sea o no editable
        // En nuestro caso ninguna celda se edita
        return true;
    }
    

}
