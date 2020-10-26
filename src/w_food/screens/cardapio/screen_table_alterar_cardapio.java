
package w_food.screens.cardapio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import w_food.db.mysql;
import w_food.screens.cardapio.screen_alterar_cardapio;
import static w_food.db.mysql.con;

public class screen_table_alterar_cardapio extends javax.swing.JFrame {

    mysql sql = new mysql();
    static String[] dados = new String[12];
    public boolean adm=true;
    
    
    public screen_table_alterar_cardapio() throws SQLException, ClassNotFoundException {
        initComponents();
        
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/w_food/images/logo_whatsfood_icone.png"));  
        setIconImage(icon.getImage());
        _setADM(true);
        
        setLocationRelativeTo( null );
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        fillTable();
    }
    void _cancelar()
    {
        this.dispose();
    }
    public void _setADM(boolean adm)
    {
    this.adm=adm;
    
    jButton_alterar_cardapio.setEnabled(this.adm);
    }
    public void fillTable() throws SQLException, ClassNotFoundException{
        DefaultTableModel tabelaClientes = (DefaultTableModel) jTable2.getModel();
        sql.startConnection();
        Statement stm;
        stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM wf_card;");
        while (res.next()){
        String id = res.getInt("ID")+"";
        String cod_int = res.getString("cod_int");
        String nome = res.getString("nome");
        String valor_custo = res.getString("valor_custo");
        String valor_venda = res.getString("valor_venda");
        //String ingredientes = res.getString("ingredientes");
        String categoria = res.getString("categoria");
        String sit_prod = res.getInt("sit_prod")+"";
        String data = res.getString("data_insc");
        String quantidade = res.getString("quant_estoq");
        String ativo = res.getInt("valido")+"";
            
        tabelaClientes.addRow(new Object[]{id, cod_int, nome, valor_custo, valor_venda, categoria, sit_prod, data, quantidade, ativo});
        }
    }
    
    public void updatexxx() throws SQLException, ClassNotFoundException{
      DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
      model.fireTableDataChanged();
      jTable2.setModel(model);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField_cardapio = new javax.swing.JTextField();
        jButton_alterar_cardapio = new javax.swing.JButton();
        jButton_cancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField_cardapio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_cardapioActionPerformed(evt);
            }
        });
        jTextField_cardapio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_cardapioKeyReleased(evt);
            }
        });

        jButton_alterar_cardapio.setBackground(new java.awt.Color(149, 199, 52));
        jButton_alterar_cardapio.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_alterar_cardapio.setForeground(new java.awt.Color(255, 255, 255));
        jButton_alterar_cardapio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_alterar.png"))); // NOI18N
        jButton_alterar_cardapio.setText("Alterar selecionado");
        jButton_alterar_cardapio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_alterar_cardapio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_alterar_cardapioActionPerformed(evt);
            }
        });

        jButton_cancelar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_cancelar.setForeground(new java.awt.Color(102, 102, 102));
        jButton_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_cancelar.png"))); // NOI18N
        jButton_cancelar.setText("Cancelar");
        jButton_cancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelarActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_lupa.png"))); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Código Interno", "Nome", "Valor de Custo", "Valor de Venda", "Categoria", "Disponibilidade", "Data", "Quantidade", "Ativo"
            }
        ));
        jTable2.setGridColor(new java.awt.Color(254, 210, 64));
        jTable2.setSelectionBackground(new java.awt.Color(254, 210, 64));
        jScrollPane3.setViewportView(jTable2);

        jLabel19.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(149, 199, 52));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_confirmar.png"))); // NOI18N
        jLabel19.setText("Enter para pesquisar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(554, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_alterar_cardapio, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_cardapio, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(82, 82, 82)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_cardapio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 397, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_alterar_cardapio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 171, 901, 490));

        jPanel2.setBackground(new java.awt.Color(238, 56, 59));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Alterar Cardápio");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(741, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addContainerGap(514, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 113, -1, 560));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/images/icones_fileira1.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 930, 120));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_alterar_cardapioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_alterar_cardapioActionPerformed
         if (jTable2.getSelectedRow() != -1){
            for (int i = 0; i < jTable2.getColumnCount(); i++){
                dados[i] = (String)jTable2.getValueAt(jTable2.getSelectedRow(), i)+"";
            }
        }else{
            JOptionPane.showMessageDialog(null, "Você não selecionou nenhuma linha para alterar!", "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.setVisible(false);
        screen_alterar_cardapio alt = new screen_alterar_cardapio();
        alt.setVisible(true);
    }//GEN-LAST:event_jButton_alterar_cardapioActionPerformed

    private void jTextField_cardapioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_cardapioKeyReleased
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(((DefaultTableModel) jTable2.getModel()));
        
        if (jTextField_cardapio.getText().isEmpty()){
            sorter.setRowFilter(RowFilter.regexFilter(""));
            jTable2.setRowSorter(sorter);
        }else{
            String text = Pattern.quote(jTextField_cardapio.getText());
            String regex = String.format("^%s$(?i)", text);
            sorter.setRowFilter(RowFilter.regexFilter(regex));
            jTable2.setRowSorter(sorter);
        }
    }//GEN-LAST:event_jTextField_cardapioKeyReleased

    private void jTextField_cardapioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_cardapioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_cardapioActionPerformed

    private void jButton_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelarActionPerformed
     _cancelar();
    }//GEN-LAST:event_jButton_cancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and afeel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(screen_table_alterar_cardapio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(screen_table_alterar_cardapio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(screen_table_alterar_cardapio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(screen_table_alterar_cardapio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new screen_table_alterar_cardapio().setVisible(true);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(screen_table_alterar_cardapio.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_alterar_cardapio;
    private javax.swing.JButton jButton_cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField_cardapio;
    // End of variables declaration//GEN-END:variables
}
