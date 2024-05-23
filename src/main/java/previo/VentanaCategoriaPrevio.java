/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package previo;

import previo.VentanaPedidoPrevio;
import Entidades.Producto;
import Ventanas.DawFoodDanielNavarro;
import Ventanas.ModeloTablaCarrito;
import Ventanas.ModeloTablaProducto;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel
 */
public class VentanaCategoriaPrevio extends javax.swing.JDialog {

    private VentanaPedidoPrevio padre;
    private double total=0;

    //private String categoria;
    /**
     * Creates new form VentanaCategoria
     *
     * @param ventana
     * @param modal
     * @param texto
     */
    public VentanaCategoriaPrevio(VentanaPedidoPrevio ventana, boolean modal, String texto) {
        super(ventana, modal);
        padre = ventana;
        initComponents();
        cargarDatosJTable(texto);
        cargarDatosJTable2();
    }

    private void cargarDatosJTable(String texto) {

        // Se crea el modelo de datos que contendrá el JTable
        // Este modelo se rellena de datos y luego se asfechaNacimientoocia al JTable
        ModeloTablaProducto modelo = new ModeloTablaProducto();
        int numMin = 0;
        int numMax = 0;

        //filtrar para que solo aparezca el tipo de producto que hemos seleccionado
        if (texto.equalsIgnoreCase("comida")) {
            numMin = 1;
            numMax = 3;
        } else if (texto.equalsIgnoreCase("bebida")) {
            numMin = 4;
            numMax = 6;
        } else if (texto.equalsIgnoreCase("postre")) {
            numMin = 7;
            numMax = 9;
        }
        // Array de object con el número de columnas del jtable
        // Para guardar cada campo de cada Persona de la lista
        Object[] fila = new Object[modelo.getColumnCount()];

        // Iteramos por la lista y asignamos a
        // cada celda del array el valor del atributo de esa persona
        //List<Producto> = ProductoJpaController.g
        for (Producto p : DawFoodDanielNavarro.getListaProductos()) {
            try {
                if (p.getCodCategoria().getCodCategoria() >= numMin && p.getCodCategoria().getCodCategoria() <= numMax) {
                    fila[0] = p.getIdProducto();
                    fila[1] = p.getDescripcion();
                    fila[2] = p.getPrecio();
                    fila[3] = p.getIva();
                    fila[4] = p.getStock();
                    modelo.addRow(fila);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "producto no encontrado");
            }

        }

        // Al finalizar el bucle el modelo tendrá tantas filas como nuestra lista
        // Decimos al JTable el modelo a usar
        jTable1.setModel(modelo);

        //oculto la primera fila para poder obtener el valor del id de producto sin mostrarlo
        jTable1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);

    }

    private void cargarDatosJTable2() {
        //jtable2 para mostrar el carrito
        
        // Se crea el modelo de datos que contendrá el JTable
        // Este modelo se rellena de datos y luego se asfechaNacimientoocia al JTable
        ModeloTablaCarrito tablaCarrito = new ModeloTablaCarrito();
        // Array de object con el número de columnas del jtable
        // Para guardar cada campo de cada Persona de la lista
        Object[] fila = new Object[tablaCarrito.getColumnCount()];
        // Iteramos por la lista y asignamos a
        // cada celda del array el valor del atributo de esa persona
        //List<Producto> = ProductoJpaController.g

        padre.getCarrito().getCarrito().forEach((k,v)-> {
            fila[0] = k;
            fila[1] = DawFoodDanielNavarro.getListaProductos().get(k-1).getDescripcion();
            fila[2] = DawFoodDanielNavarro.getListaProductos().get(k-1).getPrecio();
            fila[3] = DawFoodDanielNavarro.getListaProductos().get(k-1).getIva();
            fila[4] = v;
            tablaCarrito.addRow(fila);
            
            
                });
        
        // Al finalizar el bucle el modelo tendrá tantas filas como nuestra lista
        // Decimos al JTable el modelo a usar
        jTable2.setModel(tablaCarrito);

        //oculto la primera fila para poder obtener el valor del id de producto sin mostrarlo
        jTable2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        jTable2.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        jTable2.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable2.getColumnModel().getColumn(0).setMinWidth(0);
        //mostrar el total del carrito->metodo recorre el map y calcula total. Muestra total en Jtextfield
        calculartotal();
        jTextField1.setText(String.valueOf(total));
    }

    private  void calculartotal(){
        this.total=0;
         padre.getCarrito().getCarrito().forEach((k,v)-> {
                    this.total+=((DawFoodDanielNavarro.getListaProductos().get(k-1).getPrecio())*
                                (1+(DawFoodDanielNavarro.getListaProductos().get(k-1).getIva()/100)))                            
                            *v;    
                });
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable2);

        jButton1.setText("Añadir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Total");

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(42, 42, 42)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int fila = jTable1.getSelectedRow();
        int idProducto = (int) jTable1.getValueAt(fila, 0);
        
        if (padre.getCarrito().getCarrito().containsKey(idProducto)) {
            padre.getCarrito().getCarrito().put(idProducto, padre.getCarrito().getCarrito().get(idProducto) + 1);            
        } else {            
            padre.getCarrito().getCarrito().put(idProducto, 1);
        }
        cargarDatosJTable2();
        System.out.println(padre.getCarrito().getCarrito().toString()+"prueba");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
