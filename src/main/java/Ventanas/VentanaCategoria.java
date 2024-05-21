/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Ventanas;

import Entidades.Producto;
import Entidades.ProductoJpaController;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel
 */
public class VentanaCategoria extends javax.swing.JDialog {
    
    private VentanaPedido padre;
    
    //private String categoria;
           

    /**
     * Creates new form VentanaCategoria
     * @param ventana
     * @param modal
     * @param texto
     */
    public VentanaCategoria(VentanaPedido ventana, boolean modal,String texto) {
        super(ventana, modal);
        padre=ventana;
        initComponents();
        cargarDatosJTable(texto);
    }

    
   private void cargarDatosJTable(String texto) {

        // Se crea el modelo de datos que contendrá el JTable
        // Este modelo se rellena de datos y luego se asfechaNacimientoocia al JTable
        ModeloTablaProducto modelo = new ModeloTablaProducto();
        int numMin=0;
        int numMax=0;
        if(texto.equalsIgnoreCase("comida")){
            numMin=1;
            numMax=3;
        }else if(texto.equalsIgnoreCase("bebida")){
            numMin=4;
            numMax=6;
        }
        else if(texto.equalsIgnoreCase("postre")){
            numMin=7;
            numMax=9;
        }
        // Array de object con el número de columnas del jtable
        // Para guardar cada campo de cada Persona de la lista
        Object[] fila = new Object[modelo.getColumnCount()];

        // Iteramos por la lista y asignamos a
        // cada celda del array el valor del atributo de esa persona
        //List<Producto> = ProductoJpaController.g
        
        for (Producto p : DawFoodDanielNavarro.getListaProductos()){
            try{
            if(p.getCodCategoria().getCodCategoria()>=numMin&&p.getCodCategoria().getCodCategoria()<=numMax){
            fila[0]=p.getDescripcion();
            fila[1]=p.getPrecio();
            fila[2]=p.getIva();
            fila[3]=p.getStock();
              modelo.addRow(fila);
            }
            
            
          
            }catch (Exception e ) {
                JOptionPane.showMessageDialog(null, "producto no encontrado");
            }
            
        }
        
         // Al finalizar el bucle el modelo tendrá tantas filas como nuestra lista

        // Decimos al JTable el modelo a usar
        jTable1.setModel(modelo);

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
