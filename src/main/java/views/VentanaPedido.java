/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import views.modelosTabla.ModeloTablaProducto;
import views.modelosTabla.ModeloTablaCarrito;
import metodos.DawFoodDanielNavarro;
import clases.Carrito;
import models.Producto;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author daniel
 */
public class VentanaPedido extends javax.swing.JDialog {

    private VentanaUsuario padre;
    private double total = 0;
    private double totalIva = 0;
    protected boolean pagado=false;
    
    //private String categoria;
    /**
     * Creates new form VentanaCategoria
     *
     * @param ventana
     * @param modal
     * @param texto
     */
    public VentanaPedido(VentanaUsuario ventana, boolean modal) {
        super(ventana, modal);
        padre = ventana;
        initComponents();
        cargarDatosJTable("comida");
        cargarDatosJTable2();
        cambiarSpinner(1);
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
        for (Producto p : padre.getListaProductos()) {
            try {
                if (p.getStock() > 0) {

                    if (p.getCodCategoria().getCodCategoria() >= numMin && p.getCodCategoria().getCodCategoria() <= numMax) {
                        fila[0] = p.getIdProducto();
                        fila[1] = p.getDescripcion();
                        fila[2] = p.getPrecio();
                        fila[3] = p.getIva();
                        fila[4] = p.getStock();
                        modelo.addRow(fila);
                    }
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

        padre.getCarrito().getCarrito().forEach((k, v) -> {
            int posicion = DawFoodDanielNavarro.buscarEnListaPosicion(padre.getListaProductos(), k);
            fila[0] = k;
            fila[1] = padre.getListaProductos().get(posicion).getDescripcion();
            fila[2] = padre.getListaProductos().get(posicion).getPrecio();
            fila[3] = padre.getListaProductos().get(posicion).getIva();
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
        jTextField1.setText(String.valueOf(total+totalIva));
    }

    protected void calculartotal() {
        this.total = 0;
        this.totalIva=0;
        padre.getCarrito().getCarrito().forEach((k, v) -> {
            int posicion = DawFoodDanielNavarro.buscarEnListaPosicion(padre.getListaProductos(), k);
            
            this.total += Math.round((DawFoodDanielNavarro.getListaProductos().get(posicion).getPrecio())* v);
            
            this.totalIva+=Math.round(((DawFoodDanielNavarro.getListaProductos().get(posicion).getPrecio())
                    * (DawFoodDanielNavarro.getListaProductos().get(posicion).getIva() / 100))
                    * v);
        });

    }

    private void cambiarSpinner(int maximo) {
        SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, maximo, 1);
        
        jSpinner1.setModel(model1);
    }
    
    protected double totalPedido(){
        return this.total;
    }
    protected double totalPedidoIva(){
        return this.totalIva;
    }
    protected Carrito getCarrito(){
        return padre.getCarrito();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jLabel1.setText("Total");

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "comida", "bebida", "postre" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

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
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/addcarrito.png"))); // NOI18N
        jButton1.setText("Añadir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/quitarproducto.png"))); // NOI18N
        jButton3.setText("Quitar Producto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Pagar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(42, 42, 42)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4)))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        try {
            int fila = jTable1.getSelectedRow();
            int idProducto = (int) jTable1.getValueAt(fila, 0);
            if (padre.getCarrito().getCarrito().containsKey(idProducto)) {
                
                
                padre.getCarrito().getCarrito().put(idProducto, padre.getCarrito().getCarrito().get(idProducto) + (int) jSpinner1.getValue());
              
                padre.getListaProductos().get(DawFoodDanielNavarro.buscarEnListaPosicion(padre.getListaProductos(), idProducto)).setStock(DawFoodDanielNavarro.buscarEnLista(padre.getListaProductos(), idProducto).getStock() - (int) jSpinner1.getValue());
                cargarDatosJTable(jComboBox1.getSelectedItem().toString());
            } else {
                padre.getCarrito().getCarrito().put(idProducto, (int) jSpinner1.getValue());
                padre.getListaProductos().get(DawFoodDanielNavarro.buscarEnListaPosicion(padre.getListaProductos(), idProducto)).setStock(DawFoodDanielNavarro.buscarEnLista(padre.getListaProductos(), idProducto).getStock() - (int) jSpinner1.getValue());
                cargarDatosJTable(jComboBox1.getSelectedItem().toString());
            }
            cargarDatosJTable2();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "selecciona un producto");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        cargarDatosJTable(jComboBox1.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
        // TODO add your handling code here:
        int fila = jTable1.getSelectedRow();
        int idProducto = (int) jTable1.getValueAt(fila, 0);
        int stock = DawFoodDanielNavarro.buscarEnLista(padre.getListaProductos(), idProducto).getStock();                
        cambiarSpinner(stock);
    }//GEN-LAST:event_jTable1FocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
        int fila = jTable2.getSelectedRow();
        int idProducto = (int) jTable2.getValueAt(fila, 0);
        int posicion = DawFoodDanielNavarro.buscarEnListaPosicion(padre.getListaProductos(), idProducto);
        padre.getListaProductos().get(posicion).setStock(
                padre.getListaProductos().get(posicion).getStock()
                + padre.getCarrito().getCarrito().get(idProducto)
        );
        cargarDatosJTable(jComboBox1.getSelectedItem().toString());
        padre.getCarrito().getCarrito().remove(idProducto);
        cargarDatosJTable2();

        }catch(Exception e ){
             JOptionPane.showMessageDialog(null, "selecciona un producto");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        PasarelaPago pago = new PasarelaPago(this, true);
        pago.setVisible(true);
        if(pagado){
            this.dispose();
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    protected void setpagado(boolean b){
        pagado=b;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
