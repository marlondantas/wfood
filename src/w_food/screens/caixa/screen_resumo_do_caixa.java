package w_food.screens.caixa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import static w_food.db.mysql.con;

public class screen_resumo_do_caixa extends javax.swing.JFrame {

    public screen_resumo_do_caixa() {
        initComponents();
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/w_food/images/logo_whatsfood_icone.png"));  
        setIconImage(icon.getImage());
        
        setLocationRelativeTo( null );
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    w_food.db.mysql sql = new w_food.db.mysql();
    
    
    String cpf = "";
    
    //<back>
    public String _getresp()
    {
    return cpf;
    }
    public void _setresp(String cpf)
    {
    this.cpf= cpf;
    }
    public void _calcularPerido(String dataI ,String dataF) throws ClassNotFoundException, SQLException
    {
        DefaultTableModel nemwel1= (DefaultTableModel) jTable_demostratico.getModel();

        sql.startConnection();
        Statement stm;
        stm = con.createStatement();
        
        ResultSet res = stm.executeQuery("SELECT * FROM wf_caixa WHERE 'data' between '"+dataI+"' and '"+dataF+"';");
        
        while (res.next()){

            String cod_reps = res.getString("resp_venda");
            String valor = res.getString("valor");
            String datar = res.getString("data");
            String tipo = res.getString("tipo");
            
            nemwel1.addRow(new Object[]{cod_reps, valor,datar,tipo});
        }
        jTable_demostratico.setModel(nemwel1);
        
        _somardados();
    }
    void _somardados()
    {
    
    }
    public String _getdata()
    {
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        
        return (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+month+"/"+(Calendar.getInstance().get(Calendar.YEAR)));
    }
    public void _calcularvalores(String resp,String data)
    {
    
    }
    void _abrirSuprimento() throws ClassNotFoundException, SQLException
    {
            screen_fundo_de_caixa_adicional ss = new screen_fundo_de_caixa_adicional(this);
    }
    
    void _AbrirSangria() throws ClassNotFoundException, SQLException
    {
            screen_sangria_caixa sc = new screen_sangria_caixa(this);
    }
    //</back>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jFormattedTextField_dataI = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField_dataF = new javax.swing.JFormattedTextField();
        jButton_aplicar = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_demostratico = new javax.swing.JTable();
        jButton_suprimento = new javax.swing.JButton();
        jButton_sangria = new javax.swing.JButton();
        jButton_imprimir = new javax.swing.JButton();
        jButton_fechar_caixa = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel_total = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(238, 56, 59));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Histórico/Resumo do Caixa");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(492, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 760, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel1.setText("Início:");

        try {
            jFormattedTextField_dataI.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_dataI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField_dataIActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel2.setText("Termíno:");

        try {
            jFormattedTextField_dataF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButton_aplicar.setBackground(new java.awt.Color(149, 199, 52));
        jButton_aplicar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_aplicar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_aplicar.setText("Aplicar ");
        jButton_aplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aplicarActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Mostrar todos os caixas abertos");

        jTable_demostratico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Caixa", "Abertura", "Fechamento", "Total", "Diferença"
            }
        ));
        jScrollPane1.setViewportView(jTable_demostratico);

        jButton_suprimento.setBackground(new java.awt.Color(149, 199, 52));
        jButton_suprimento.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_suprimento.setForeground(new java.awt.Color(255, 255, 255));
        jButton_suprimento.setText("Suprimento");
        jButton_suprimento.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_suprimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_suprimentoActionPerformed(evt);
            }
        });

        jButton_sangria.setBackground(new java.awt.Color(238, 56, 59));
        jButton_sangria.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_sangria.setForeground(new java.awt.Color(255, 255, 255));
        jButton_sangria.setText("Sangria");
        jButton_sangria.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_sangria.setPreferredSize(new java.awt.Dimension(55, 19));

        jButton_imprimir.setBackground(new java.awt.Color(204, 204, 204));
        jButton_imprimir.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_imprimir.png"))); // NOI18N
        jButton_imprimir.setText("Imprimir");
        jButton_imprimir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jButton_fechar_caixa.setBackground(new java.awt.Color(238, 56, 59));
        jButton_fechar_caixa.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_fechar_caixa.setForeground(new java.awt.Color(255, 255, 255));
        jButton_fechar_caixa.setText("Fechar Caixa Atual");
        jButton_fechar_caixa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel3.setText("Total Geral:");

        jLabel_total.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel_total.setForeground(new java.awt.Color(238, 56, 59));
        jLabel_total.setText("R$ ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField_dataI, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField_dataF, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_aplicar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                        .addComponent(jCheckBox1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_fechar_caixa, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_total)
                        .addGap(59, 59, 59)
                        .addComponent(jButton_sangria, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_suprimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jFormattedTextField_dataI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jFormattedTextField_dataF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_aplicar)
                    .addComponent(jCheckBox1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton_sangria, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(jButton_suprimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_fechar_caixa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel_total))
                    .addComponent(jButton_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 760, 470));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/images/icones_fileira1.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 780, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextField_dataIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField_dataIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField_dataIActionPerformed

    private void jButton_suprimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_suprimentoActionPerformed
        
    }//GEN-LAST:event_jButton_suprimentoActionPerformed

    private void jButton_aplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aplicarActionPerformed
        try {
            _calcularPerido(jFormattedTextField_dataI.getText(),jFormattedTextField_dataF.getText());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(screen_resumo_do_caixa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(screen_resumo_do_caixa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_aplicarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(screen_resumo_do_caixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(screen_resumo_do_caixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(screen_resumo_do_caixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(screen_resumo_do_caixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new screen_resumo_do_caixa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_aplicar;
    private javax.swing.JButton jButton_fechar_caixa;
    private javax.swing.JButton jButton_imprimir;
    private javax.swing.JButton jButton_sangria;
    private javax.swing.JButton jButton_suprimento;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JFormattedTextField jFormattedTextField_dataF;
    private javax.swing.JFormattedTextField jFormattedTextField_dataI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_total;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable_demostratico;
    // End of variables declaration//GEN-END:variables
}
