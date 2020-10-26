package w_food.screens.caixa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import static w_food.db.mysql.con;


public class screen_fechamento_do_caixa extends javax.swing.JFrame {
           
    String fun[];
    
    public screen_fechamento_do_caixa() throws ClassNotFoundException, SQLException {
        initComponents();
        
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/w_food/images/logo_whatsfood_icone.png"));  
        setIconImage(icon.getImage());
        
        setLocationRelativeTo( null );
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }

    
    
    //<back>
    w_food.db.mysql sql = new w_food.db.mysql();
    DecimalFormat df = new DecimalFormat("#,###.00");


    public void _init() throws ClassNotFoundException, SQLException
    {
        
        //apagar depois:
        //String funs="111.222.111-22";
        
    jLabel_data.setText(_setDataAtual());
    jLabel_cpf.setText(fun[2]);  
    
    _calcularProdutos(_setDataAtual(), fun[2]);
    _calcularvalores(_setDataAtual(), fun[2]);

    
    }
    void _fecharCaixa(String _cpf,String _valorFinal) throws SQLException
    {
        
        String caixa[] = sql._checarAbruturaCaixa(_cpf);
        
        sql._fecharcaixa(caixa[0], _valorFinal);
        
        sql._addmovimentoCaixa(_cpf, _valorFinal.replace(",","."), _setDataAtual(), "Fechamento");
    }
    void _cancelar()
    {
    this.dispose();
    }
    void _calcularvalores(String data,String reps) throws ClassNotFoundException, SQLException
    {
       
        _limpartabela();
                
        DefaultTableModel nemwel1= new DefaultTableModel();
        nemwel1.addColumn("Venderdor");
        nemwel1.addColumn("Valor");
        nemwel1.addColumn("Data");
        nemwel1.addColumn("Tipo");
    
        sql.startConnection();
        Statement stm;
        stm = con.createStatement();
        
        data=sql._arrumardata(data);
        
        ResultSet res = stm.executeQuery("SELECT * FROM `wf_movicaixa` WHERE `data` = '"+data+"' AND `resp_venda` = '"+reps+"';");
        
        while (res.next()){

            String cod_reps = res.getString("resp_venda");
            String valor = res.getString("valor");
            String datar = res.getString("data");
            String tipo = res.getString("tipo");
            
            nemwel1.addRow(new Object[]{cod_reps, valor,datar,tipo});
        }
        jTable_demonstrativo.setModel(nemwel1);
        
        _somardados();
        
    }
    void _somardados()
    {
        float _totais[]=new float[4];
        
        for (int i = 0; i < jTable_demonstrativo.getRowCount(); i++) {
            switch((jTable_demonstrativo.getValueAt(i,3)+"").toUpperCase()) 
                    {
                
                case "ABERTURA":
                    _totais[0]=_totais[0] + Float.parseFloat((jTable_demonstrativo.getValueAt(i,1)+""));
                        break;
                        
                case "VENDA":
                    _totais[1]=_totais[1] + Float.parseFloat((jTable_demonstrativo.getValueAt(i,1)+""));
                        break;
                        
                case "SANGRIA":
                    _totais[2]=_totais[2] + Float.parseFloat((jTable_demonstrativo.getValueAt(i,1)+""));
                        break;
                                
                case "FUNDO":
                    _totais[3]=_totais[3] + Float.parseFloat((jTable_demonstrativo.getValueAt(i,1)+""));
                        break;
                        
                    }
        }
        
        jLabel_abretura.setText("R$"+df.format(_totais[0]));
        jLabel_venda.setText("R$"+df.format(_totais[1]));
        jLabel_suprimento.setText("R$"+df.format(_totais[3]));
        jLabel_sangria.setText("R$"+df.format(_totais[2]));
    
        
        jLabel_total.setText("R$"+df.format(_totais[0]+_totais[1]-_totais[2]+_totais[3]));
    }
    void _limpartabela()
    {
        
        DefaultTableModel model = new DefaultTableModel();
        
        jTable_demonstrativo.setModel(model);
        
    }
    void _calcularProdutos(String data, String reps) throws SQLException, ClassNotFoundException
    {
        _limpartabela();
        
        DefaultTableModel nemwel= new DefaultTableModel();
        nemwel.addColumn("Venderdor");
        nemwel.addColumn("Cliente");
        nemwel.addColumn("Produto");
        nemwel.addColumn("Quantidade");
        nemwel.addColumn("Valor");
        nemwel.addColumn("Data");
        
        sql.startConnection();
        Statement stm;
        stm = con.createStatement();
        
        data=sql._arrumardata(data);
        
        ResultSet res = stm.executeQuery("SELECT * FROM `wf_vendas` WHERE `data_venda` = '"+data+"' AND `resp_venda` = '"+reps+"';");
        
        while (res.next()){

            String codigo_resp = res.getString("resp_venda");
            String cod_cliente = res.getString("cpf_cliente");
            String produto = res.getString("produto");
            String quantidade = res.getString("quantidade");
            String valor_unit = res.getString("valor_unit");
            String datas = res.getString("data_venda");
                                                                            //codigo do produto    
            nemwel.addRow(new Object[]{codigo_resp,cod_cliente,sql._checarProduto(produto)[1],quantidade,valor_unit,datas});
        }
        
        jTable_demonstrativo.setModel(nemwel);
    }
    public String _setDataAtual()
    {
       int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        
       return (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+month+"/"+(Calendar.getInstance().get(Calendar.YEAR)));
    }
    public void _setdados(String dado[])
    {
     fun=dado;
    }

    public String _getresp()
    {
    return fun[2];
    }
    public String _getdata()
    {
    return _setDataAtual();
    }
    
    //</back>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_demonstrativo = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel_suprimento = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel_sangria = new javax.swing.JLabel();
        jLabel_venda = new javax.swing.JLabel();
        jLabel_abretura = new javax.swing.JLabel();
        jButton_valores = new javax.swing.JButton();
        jButton_produtos = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel_total = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jButton_cancelar = new javax.swing.JButton();
        jButton_imprimir = new javax.swing.JButton();
        jButton_confirmar_fechamento = new javax.swing.JButton();
        jLabel_data = new javax.swing.JLabel();
        jLabel_cpf = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(238, 56, 59));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTable_demonstrativo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Caixa", "Abertura", "Fechamento", "Total", "Diferença"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_demonstrativo.setGridColor(new java.awt.Color(254, 210, 64));
        jScrollPane1.setViewportView(jTable_demonstrativo);

        jLabel3.setText("Total Vendas:");

        jLabel_suprimento.setText("R$ ");

        jLabel5.setText("Total Sangria:");

        jLabel6.setText("Total Suprimento:");

        jLabel7.setText("Total Geral:");

        jLabel_sangria.setText("R$ -");

        jLabel_venda.setText("R$ ");

        jLabel_abretura.setText("R$ ");

        jButton_valores.setBackground(new java.awt.Color(204, 204, 204));
        jButton_valores.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_valores.setText("Valores");
        jButton_valores.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_valores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_valoresActionPerformed(evt);
            }
        });

        jButton_produtos.setBackground(new java.awt.Color(204, 204, 204));
        jButton_produtos.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_produtos.setText("Visualizar Produtos");
        jButton_produtos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_produtos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_produtosActionPerformed(evt);
            }
        });

        jLabel11.setText("Total Abretura:");

        jLabel_total.setText("R$ ");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setForeground(new java.awt.Color(254, 210, 64));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Fechamento de Caixa");

        jSeparator3.setForeground(new java.awt.Color(254, 56, 59));

        jButton_cancelar.setBackground(new java.awt.Color(204, 204, 204));
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

        jButton_imprimir.setBackground(new java.awt.Color(204, 204, 204));
        jButton_imprimir.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_imprimir.png"))); // NOI18N
        jButton_imprimir.setText("Imprimir");
        jButton_imprimir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jButton_confirmar_fechamento.setBackground(new java.awt.Color(238, 56, 59));
        jButton_confirmar_fechamento.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_confirmar_fechamento.setForeground(new java.awt.Color(255, 255, 255));
        jButton_confirmar_fechamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_confirmar.png"))); // NOI18N
        jButton_confirmar_fechamento.setText("Confirmar Fechamento de Caixa");
        jButton_confirmar_fechamento.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_confirmar_fechamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_confirmar_fechamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_valores, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_produtos))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(43, 43, 43)
                                    .addComponent(jLabel_total))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_suprimento)
                                        .addComponent(jLabel_sangria)
                                        .addComponent(jLabel_venda)
                                        .addComponent(jLabel_abretura))))
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_confirmar_fechamento)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel_abretura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_venda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_sangria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_suprimento)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_total))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_valores)
                    .addComponent(jButton_produtos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_imprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_confirmar_fechamento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel_data.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel_data.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_data.setText("*DATA*");

        jLabel_cpf.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel_cpf.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_cpf.setText("*CPF*");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_cpf, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_data, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_cpf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_data)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelarActionPerformed
      _cancelar();
    }//GEN-LAST:event_jButton_cancelarActionPerformed

    private void jButton_confirmar_fechamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_confirmar_fechamentoActionPerformed
        
        String valorF= JOptionPane.showInputDialog(null,"Digite o valor em caixa: \n\n","Fechamento de caixa",JOptionPane.WARNING_MESSAGE);
        
        try {
            _fecharCaixa(_getresp(),valorF.replace(",", "."));
            _calcularvalores(_getdata(),_getresp());
        } catch (SQLException ex) {
            Logger.getLogger(screen_fechamento_do_caixa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(screen_fechamento_do_caixa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_confirmar_fechamentoActionPerformed

    private void jButton_valoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_valoresActionPerformed
        try {
            _calcularvalores(_getdata(),_getresp());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(screen_fechamento_do_caixa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(screen_fechamento_do_caixa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_valoresActionPerformed

    private void jButton_produtosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_produtosActionPerformed
        try {
            _calcularProdutos(_getdata(),_getresp());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(screen_fechamento_do_caixa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(screen_fechamento_do_caixa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_produtosActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new screen_fechamento_do_caixa().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(screen_fechamento_do_caixa.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(screen_fechamento_do_caixa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_cancelar;
    private javax.swing.JButton jButton_confirmar_fechamento;
    private javax.swing.JButton jButton_imprimir;
    private javax.swing.JButton jButton_produtos;
    private javax.swing.JButton jButton_valores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_abretura;
    private javax.swing.JLabel jLabel_cpf;
    private javax.swing.JLabel jLabel_data;
    private javax.swing.JLabel jLabel_sangria;
    private javax.swing.JLabel jLabel_suprimento;
    private javax.swing.JLabel jLabel_total;
    private javax.swing.JLabel jLabel_venda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable_demonstrativo;
    // End of variables declaration//GEN-END:variables
}
