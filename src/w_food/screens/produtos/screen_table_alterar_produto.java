
package w_food.screens.produtos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import w_food.db.mysql;
import static w_food.db.mysql.con;

public class screen_table_alterar_produto extends javax.swing.JFrame {
mysql sql = new mysql();
    static String[] dados = new String[12];
    public boolean adm=true;
    
    public screen_table_alterar_produto() throws SQLException, ClassNotFoundException {
        initComponents();
        
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/w_food/images/logo_whatsfood_icone.png"));  
        setIconImage(icon.getImage());
        
        setLocationRelativeTo( null );
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        fillTable();
        _setAdm(adm);
    }
    //<back>
    public void _setAdm(boolean adm)
    {
    this.adm = adm;
    
    jButton_alterar.setEnabled(this.adm);
    jButton_remover.setEnabled(this.adm);
    jTable1.setEnabled(this.adm);
    
    }
    
    public void fillTable() throws SQLException, ClassNotFoundException{
        DefaultTableModel tabelaClientes = (DefaultTableModel) jTable1.getModel();
        sql.startConnection();
        Statement stm;
        stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM wf_estoque WHERE `valido` = 1;");
        while (res.next()){
        String id = res.getInt("id")+"";
        String cod_int = res.getString("nome");
        String nome = res.getString("quantidade");
        String valor_custo = res.getString("fornecedor");
        String valor_venda = res.getString("data_val");
        String categoria = res.getString("valor_custo");
        String sit_prod = res.getInt("valido")+"";
            
        tabelaClientes.addRow(new Object[]{id, cod_int, nome, valor_custo, valor_venda, categoria, sit_prod});
        }
    }
    
    private void removeline(String id) throws SQLException, ClassNotFoundException{
        int i = jTable1.getSelectedRow();
        if (i != -1){
            String a = jTable1.getValueAt(i, 0).toString();
            sql.startConnection();
            Statement stm;
            stm = con.createStatement();
            stm.executeUpdate("DELETE FROM wf_estoque WHERE id = " + id);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.removeRow(i);
            jTable1.setModel(model);
        }else{
            JOptionPane.showMessageDialog(null, "Você não selecionou nenhuma linha para alterar!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jTextField_pesquisa = new javax.swing.JTextField();
        jButton_pesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton_alterar = new javax.swing.JButton();
        jButton_cancelar = new javax.swing.JButton();
        jButton_remover = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(238, 56, 59));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Alterar Produto");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(791, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 910, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton_pesquisar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_lupa.png"))); // NOI18N
        jButton_pesquisar.setText("Pesquisar");
        jButton_pesquisar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Quantidade", "Fornecedor", "Data de Validade", "Valor", "Ativo"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(254, 210, 64));
        jTable1.setSelectionBackground(new java.awt.Color(254, 210, 64));
        jScrollPane1.setViewportView(jTable1);

        jButton_alterar.setBackground(new java.awt.Color(238, 56, 59));
        jButton_alterar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_alterar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_confirmar.png"))); // NOI18N
        jButton_alterar.setText("Confirmar Alteração");
        jButton_alterar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_alterarActionPerformed(evt);
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

        jButton_remover.setBackground(new java.awt.Color(149, 99, 52));
        jButton_remover.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_remover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_lixeira.png"))); // NOI18N
        jButton_remover.setText("Confirmar Remoção");
        jButton_remover.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_removerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_remover, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 372, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_pesquisa)
                    .addComponent(jButton_pesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_alterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_remover, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 115, -1, 420));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/images/icones_fileira3.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-210, -50, 1120, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_removerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_removerActionPerformed
        int i = jTable1.getSelectedRow();
        if (i != -1){
            try {
                removeline(jTable1.getValueAt(i, 0).toString());
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(screen_table_alterar_produto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Você não selecionou nenhuma linha para alterar!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_removerActionPerformed

    private void jButton_alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_alterarActionPerformed
       if (jTable1.getSelectedRow() != -1){
        for (int i = 0; i < jTable1.getColumnCount(); i++){
            dados[i] = (String)jTable1.getValueAt(jTable1.getSelectedRow(), i)+"";
        }
    }else{
        JOptionPane.showMessageDialog(null, "Você não selecionou nenhuma linha para alterar!", "ERRO", JOptionPane.ERROR_MESSAGE);
        return;
    }
    JOptionPane.showMessageDialog(null, dados[1] + dados[2], "ERRO", JOptionPane.ERROR_MESSAGE);
    screen_alterar_produtos alt = new screen_alterar_produtos();
    alt.setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_jButton_alterarActionPerformed

    private void jButton_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_cancelarActionPerformed

    
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new screen_table_alterar_produto().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(screen_table_alterar_produto.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(screen_table_alterar_produto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_alterar;
    private javax.swing.JButton jButton_cancelar;
    private javax.swing.JButton jButton_pesquisar;
    private javax.swing.JButton jButton_remover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_pesquisa;
    // End of variables declaration//GEN-END:variables
}
