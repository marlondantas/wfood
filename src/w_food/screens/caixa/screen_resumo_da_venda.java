
package w_food.screens.caixa;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class screen_resumo_da_venda extends javax.swing.JFrame {
    
    w_food.db.mysql sql = new w_food.db.mysql();
    
    DecimalFormat df = new DecimalFormat("#,###.00");
    
    
    screen_caixa caixa;

    public screen_resumo_da_venda(screen_caixa caixa) throws ClassNotFoundException, SQLException {
        initComponents();
        
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/w_food/images/logo_whatsfood_icone.png"));  
        setIconImage(icon.getImage());
        
        setLocationRelativeTo( null );
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.caixa = caixa;
        
        sql.startConnection();
        jLabel_data.setText("Data: "+_setdata());
    }
    
    String cliente[];
    Object produtos[];
    TableModel tabel;
    
    String total="";
    String troco="";
    String pago="";
    
    String cpfC="";
    String data=_setdata();
    String representate="";
   
    
    private screen_resumo_da_venda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //<back>
    void _voltar()
    {
                
        caixa._limparTUDO();
        caixa.requestFocus();
    
        this.dispose();

    }
     public void _setDados(String cpf,String codrepre)
    {
        if(cpf==null)
        {
        cpfC="CA";//Cliente Avulso
        }
        else
        {
        cpfC=cpf;
        }
        representate = codrepre;
        
        
    }
    
    public void   _FINALIZAR() throws SQLException
    {
    //pegar todos os dados na tabela e salvar
//pegar todos os dados na tabela e salvar

        for(int i = 0; i < jTable_resumo_de_v.getModel().getRowCount(); i++)
        {
            String codi = (String) jTable_resumo_de_v.getValueAt(i, 0);
            String qted = (String) jTable_resumo_de_v.getValueAt(i, 2);
            String valor = (String) jTable_resumo_de_v.getValueAt(i, 3);
            
            sql.registerVenda(codi,qted,valor,data,cpfC,representate);
        }
        
        sql._addmovimentoCaixa(representate, total, data,"venda");
        
        try {
            caixa._limparTUDO();
        } catch (Exception e) {
        }
 
        this.dispose(); 
    }
    
    public void _setValor(String totalv, String pagov)
    {
   
        total=totalv;
        pago=pagov;
        
        Float trocototal = Float.parseFloat(pagov) - Float.parseFloat(totalv);
        troco= (df.format(trocototal) );
                
        jLabel_total.setText(total);
        jLabel_troco.setText(troco);
        jLabel_pago.setText(pago);
        
    }
    public void _setCliente(String dados[])
    {
        cliente=dados;
        
        jLabel_nome_cliente.setText("Nome do Cliente: "+cliente[0]);
        jLabel_cpf.setText("CPF: "+cliente[4]);
        
    } 
    public void _setdataTable(TableModel model)
    {
        tabel = model;
        
        jTable_resumo_de_v.setModel(tabel);
    }
    String _setdata()
    {
    return Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+(Calendar.getInstance().get(Calendar.YEAR));
    }
    //</back>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel_total = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel_pago = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel_troco = new javax.swing.JLabel();
        jButton_imprimir_comprovante = new javax.swing.JButton();
        jLabel_data = new javax.swing.JLabel();
        jLabel_nome_cliente = new javax.swing.JLabel();
        jLabel_cpf = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_resumo_de_v = new javax.swing.JTable();
        jSeparator5 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton_finalizar_venda = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(238, 56, 59));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setEnabled(false);

        jSeparator1.setForeground(new java.awt.Color(254, 210, 64));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(238, 56, 59));
        jLabel2.setText("Total:");

        jLabel_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_total.setText("R$ 00,00");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(238, 56, 59));
        jLabel4.setText("Pago:");

        jLabel_pago.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_pago.setText("R$ 00,00");

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(238, 56, 59));
        jLabel6.setText("Troco:");

        jLabel_troco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_troco.setText("R$ 00,00");

        jButton_imprimir_comprovante.setBackground(new java.awt.Color(204, 204, 204));
        jButton_imprimir_comprovante.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_imprimir_comprovante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_imprimir.png"))); // NOI18N
        jButton_imprimir_comprovante.setText("Imprimir Comprovante");
        jButton_imprimir_comprovante.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_imprimir_comprovante.setPreferredSize(new java.awt.Dimension(165, 30));
        jButton_imprimir_comprovante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_imprimir_comprovanteKeyPressed(evt);
            }
        });

        jLabel_data.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel_data.setText("Data:*");

        jLabel_nome_cliente.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel_nome_cliente.setText("Nome do Cliente:");

        jLabel_cpf.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel_cpf.setText("CPF:");

        jTable_resumo_de_v.setTableHeader(null);
        jTable_resumo_de_v.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Codigo ", "Nome do Produto", "Qtde.", "Preço un.", "Total", "Título 6"
            }
        ));
        jTable_resumo_de_v.setEnabled(false);
        jTable_resumo_de_v.setGridColor(new java.awt.Color(254, 210, 64));
        jScrollPane1.setViewportView(jTable_resumo_de_v);
        if (jTable_resumo_de_v.getColumnModel().getColumnCount() > 0) {
            jTable_resumo_de_v.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable_resumo_de_v.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTable_resumo_de_v.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 102, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_cancelar.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.setPreferredSize(new java.awt.Dimension(83, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton_finalizar_venda.setBackground(new java.awt.Color(238, 56, 59));
        jButton_finalizar_venda.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_finalizar_venda.setForeground(new java.awt.Color(255, 255, 255));
        jButton_finalizar_venda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_confirmar.png"))); // NOI18N
        jButton_finalizar_venda.setText("Finalizar Venda");
        jButton_finalizar_venda.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_finalizar_venda.setPreferredSize(new java.awt.Dimension(129, 30));
        jButton_finalizar_venda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_finalizar_vendaActionPerformed(evt);
            }
        });
        jButton_finalizar_venda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_finalizar_vendaKeyPressed(evt);
            }
        });

        jSeparator6.setBackground(new java.awt.Color(254, 210, 64));
        jSeparator6.setForeground(new java.awt.Color(254, 210, 64));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator5)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator4)
                .addGap(10, 10, 10))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator3)
                .addGap(10, 10, 10))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jSeparator2)
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator6)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_pago, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_total, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_troco, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_nome_cliente)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_finalizar_venda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel_data))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_cpf))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton_imprimir_comprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_data)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_nome_cliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_cpf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel_total))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel_pago))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel_troco))
                .addGap(4, 4, 4)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_imprimir_comprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_finalizar_venda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Resumo da Vendas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_finalizar_vendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_finalizar_vendaActionPerformed
        try {
            _FINALIZAR();
        } catch (SQLException ex) {
            Logger.getLogger(screen_resumo_da_venda.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }//GEN-LAST:event_jButton_finalizar_vendaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        _voltar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton_imprimir_comprovanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_imprimir_comprovanteKeyPressed
        if(evt.getKeyChar() == KeyEvent.VK_ENTER )
        {
            jButton_finalizar_venda.requestFocus();
        }
    }//GEN-LAST:event_jButton_imprimir_comprovanteKeyPressed

    private void jButton_finalizar_vendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_finalizar_vendaKeyPressed
        if(evt.getKeyChar() == KeyEvent.VK_ENTER )
        {
            try {
            _FINALIZAR();
            }
            catch (SQLException ex) 
            {
            Logger.getLogger(screen_resumo_da_venda.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }//GEN-LAST:event_jButton_finalizar_vendaKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new screen_resumo_da_venda().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton_finalizar_venda;
    private javax.swing.JButton jButton_imprimir_comprovante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_cpf;
    private javax.swing.JLabel jLabel_data;
    private javax.swing.JLabel jLabel_nome_cliente;
    private javax.swing.JLabel jLabel_pago;
    private javax.swing.JLabel jLabel_total;
    private javax.swing.JLabel jLabel_troco;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    public javax.swing.JTable jTable_resumo_de_v;
    // End of variables declaration//GEN-END:variables
}
