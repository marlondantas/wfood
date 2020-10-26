
package w_food.screens.produtos;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


import w_food.db._objectEmpty;

public class screen_cadastrar_produto extends javax.swing.JFrame {


    public screen_cadastrar_produto() throws SQLException, ClassNotFoundException {
        initComponents();
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/w_food/images/logo_whatsfood_icone.png"));  
        setIconImage(icon.getImage());
        //<back>
        
        
        setLocationRelativeTo( null );
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        _initMain();
        _initCombobox();
        //</back>
    }

    //<back>
        w_food.db.mysql sql = new w_food.db.mysql();
        w_food.db.funcs funcoes = new w_food.db.funcs();
    
        String _codFonercedor = ""; 
        
        public void _initMain() throws SQLException, ClassNotFoundException
        {
            sql.startConnection();
            
        }
        public void _initCombobox() throws ClassNotFoundException, SQLException
        {
            for (_objectEmpty x : sql.read("wf_forn", "id", "nome")) {
                jComboBox_Fornecedor.addItem(x);
            }
        }
        public void _limparCampos()
        {
            jTextField_cad_nome.setText("");
            jTextField_cad_quant.setText("");
            jFormattedTextField_data_de_validade.setText("");
            jFormattedTextField_valor.setText("");
            
            jComboBox_Fornecedor.setSelectedIndex(0);
        }
        
        public void _setInicio()
        {
            jTextField_cad_nome.requestFocus();
        }
        
        public void _addProduto(String nome,String Quantidade, String data_validade, String valor, String Cod_foner) throws ClassNotFoundException, ParseException
        {
            
         try
         {
            sql.startConnection();
            sql.registerEstoque(nome, Quantidade,  Cod_foner, data_validade, valor);
            _limparCampos();
            _setInicio();
            JOptionPane.showMessageDialog(null,"Adicionado com Sucesso!!!!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
         }
         catch (HeadlessException | SQLException e)
         {
            JOptionPane.showMessageDialog(null, e.toString(), "ERRO", JOptionPane.ERROR_MESSAGE);      
         }
         
         
        }
        
        void  _cadastarFornecedor(String _codigo)
        {
            w_food.screens.fornecedor.screen_cadastro_fornecedor forn = new w_food.screens.fornecedor.screen_cadastro_fornecedor();
            
            forn.setVisible(true);
            
        }
        
    //</back>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton_limpar1 = new javax.swing.JButton();
        jButton_cancelar1 = new javax.swing.JButton();
        jButton_cadastrar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jTextField_cad_nome = new javax.swing.JTextField();
        jFormattedTextField_data_de_validade = new javax.swing.JFormattedTextField();
        jTextField_cad_quant = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextField_valor = new javax.swing.JFormattedTextField();
        jComboBox_Fornecedor = new javax.swing.JComboBox<>();
        jButton_Pesquisar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(238, 56, 59));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel10.setText("Tempo para validade");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel11.setText("Valor de compras");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel12.setText("Nome");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel13.setText("Quantidade");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel14.setText("Fornecedor");

        jSeparator2.setForeground(new java.awt.Color(254, 210, 64));

        jButton_limpar1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_limpar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_clean.png"))); // NOI18N
        jButton_limpar1.setText("Limpar");
        jButton_limpar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_limpar1.setPreferredSize(new java.awt.Dimension(67, 25));
        jButton_limpar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_limpar1ActionPerformed(evt);
            }
        });

        jButton_cancelar1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_cancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_cancelar.png"))); // NOI18N
        jButton_cancelar1.setText("Cancelar");
        jButton_cancelar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_cancelar1.setPreferredSize(new java.awt.Dimension(79, 25));
        jButton_cancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar1ActionPerformed(evt);
            }
        });

        jButton_cadastrar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_confirmar.png"))); // NOI18N
        jButton_cadastrar.setText("Cadastrar");
        jButton_cadastrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_cadastrar.setPreferredSize(new java.awt.Dimension(65, 25));
        jButton_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cadastrarActionPerformed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(254, 210, 64));

        try {
            jFormattedTextField_data_de_validade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel8.setText("R$:");

        jFormattedTextField_valor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFormattedTextField_valor.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jFormattedTextField_valorInputMethodTextChanged(evt);
            }
        });

        jComboBox_Fornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jButton_Pesquisar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_Pesquisar.setText("Cadastar Fonercedor");
        jButton_Pesquisar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jComboBox_Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Pesquisar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 465, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_cad_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_cad_quant, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jFormattedTextField_data_de_validade))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextField_valor))))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton_limpar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_cancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(23, 23, 23))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(jTextField_cad_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField_data_de_validade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11)
                    .addComponent(jTextField_cad_quant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jFormattedTextField_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Fornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_cancelar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_limpar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jButton_cadastrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Cadastrar Produtos");

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator4)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 550, 290));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/images/fundo_icones.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 560, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextField_valorInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jFormattedTextField_valorInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField_valorInputMethodTextChanged

    private void jButton_PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PesquisarActionPerformed
        //<back>
        
        
        _cadastarFornecedor(_codFonercedor);
        
        //</back>
    }//GEN-LAST:event_jButton_PesquisarActionPerformed

    private void jButton_limpar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_limpar1ActionPerformed
        //<bacK>
        
        _limparCampos();
        
        //</back>
    }//GEN-LAST:event_jButton_limpar1ActionPerformed

    private void jButton_cancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelar1ActionPerformed
       //<back>
        
       w_food.screens.login.screen_gerencia gerencia;
        try {
            gerencia = new w_food.screens.login.screen_gerencia();
            gerencia.setVisible(true);
            
            
            this.setVisible(false);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(screen_cadastrar_produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</back>
        

       

        
       //</back>
    }//GEN-LAST:event_jButton_cancelar1ActionPerformed

    private void jButton_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cadastrarActionPerformed
    //<back>
        
        _objectEmpty fornecedor = (_objectEmpty) jComboBox_Fornecedor.getSelectedItem();
        
        String Cod_foner = fornecedor.getId();

        try {
            _addProduto(jTextField_cad_nome.getText(), jTextField_cad_quant.getText(), jFormattedTextField_data_de_validade.getText(), jFormattedTextField_valor.getText(), Cod_foner);
            
            //</back>                
        } catch (ClassNotFoundException | ParseException ex) {
            Logger.getLogger(screen_cadastrar_produto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_cadastrarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new screen_cadastrar_produto().setVisible(true);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(screen_cadastrar_produto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Pesquisar;
    private javax.swing.JButton jButton_cadastrar;
    private javax.swing.JButton jButton_cancelar1;
    private javax.swing.JButton jButton_limpar1;
    private javax.swing.JComboBox<Object> jComboBox_Fornecedor;
    private javax.swing.JFormattedTextField jFormattedTextField_data_de_validade;
    private javax.swing.JFormattedTextField jFormattedTextField_valor;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTextField_cad_nome;
    private javax.swing.JTextField jTextField_cad_quant;
    // End of variables declaration//GEN-END:variables
}
