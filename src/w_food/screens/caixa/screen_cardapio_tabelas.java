package w_food.screens.caixa;

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
import static w_food.db.mysql.con;

public class screen_cardapio_tabelas extends javax.swing.JFrame {
    
    w_food.db.mysql sql = new w_food.db.mysql();
    
    screen_caixa caixa;
    
    public screen_cardapio_tabelas(screen_caixa caixa) throws ClassNotFoundException, SQLException {
        initComponents();
        
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/w_food/images/logo_whatsfood_icone.png"));  
        setIconImage(icon.getImage());
        
        sql.startConnection();
        _carregarProduto();
        
        this.caixa=caixa;
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private screen_cardapio_tabelas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //<back>
    void _carregarProduto() throws SQLException
    {   
        DefaultTableModel tabelacard = (DefaultTableModel) jTable_cardapio.getModel();
        
        Statement stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM `wf_card` WHERE `valido` = 1;");
        
        while (res.next())
        {
            //String id = res.getInt("ID")+"";
            String cod_int = res.getString("cod_int");
            String nome = res.getString("nome");
            //String valor_custo = res.getString("valor_custo");
            String valor_venda = res.getString("valor_venda");
            //String ingredientes = res.getString("ingredientes");
            String categoria = res.getString("categoria");
            //String sit_prod = res.getInt("sit_prod")+"";
            //String data = res.getString("data_insc");
            //String quantidade = res.getString("quant_estoq");
            //String ativo = res.getInt("valido")+"";

            tabelacard.addRow(new Object[]{cod_int, nome, valor_venda, categoria});
        }
    }
    void _voltar()
    {
        caixa.requestFocus();
        this.dispose();
        
        
    }
    
    void _concluir(String cod) throws SQLException
    {
        caixa.jTextField_produto_buscar_cod.setText(cod);  
        caixa.jTextField_produto_buscar_cod.requestFocus();
        
        this.dispose();
    }
    
    void _buscarProduto(String text)
    {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(((DefaultTableModel) jTable_cardapio.getModel()));
        
        if (text.isEmpty())
        {
            sorter.setRowFilter(RowFilter.regexFilter(""));
            jTable_cardapio.setRowSorter(sorter);
        }
        else
        {
            String regex = String.format("^%s$(?i)", text);
            sorter.setRowFilter(RowFilter.regexFilter(regex));
            jTable_cardapio.setRowSorter(sorter);
        }
    }
    
    
    //</back>
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_cardapio = new javax.swing.JTable();
        jTextField_buscar = new javax.swing.JTextField();
        jButton_selecionar = new javax.swing.JButton();
        jButton_cancelar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable_cardapio.setAutoCreateRowSorter(true);
        jTable_cardapio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nome", "Valor de Venda", "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_cardapio.setGridColor(new java.awt.Color(254, 210, 64));
        jScrollPane1.setViewportView(jTable_cardapio);

        jTextField_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_buscarKeyReleased(evt);
            }
        });

        jButton_selecionar.setBackground(new java.awt.Color(238, 56, 59));
        jButton_selecionar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_selecionar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_selecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_confirmar.png"))); // NOI18N
        jButton_selecionar.setText("Selecionar");
        jButton_selecionar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_selecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_selecionarActionPerformed(evt);
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

        jLabel19.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(149, 199, 52));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_confirmar.png"))); // NOI18N
        jLabel19.setText("Enter para pesquisar");

        jPanel2.setBackground(new java.awt.Color(238, 56, 59));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cardápio");

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator1.setBackground(new java.awt.Color(238, 56, 59));
        jSeparator1.setForeground(new java.awt.Color(238, 56, 59));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_selecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_selecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 64, 580, 340));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/images/fundo_icones.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_buscarKeyReleased
        _buscarProduto(jTextField_buscar.getText());
    }//GEN-LAST:event_jTextField_buscarKeyReleased

    private void jButton_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelarActionPerformed
        _voltar();
    }//GEN-LAST:event_jButton_cancelarActionPerformed

    private void jButton_selecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_selecionarActionPerformed
        if(JOptionPane.showConfirmDialog(null,"Codigo: "+jTable_cardapio.getValueAt(jTable_cardapio.getSelectedRow(), 0)+"\n Produto: "+jTable_cardapio.getValueAt(jTable_cardapio.getSelectedRow(), 1),"Produto Selecionado",JOptionPane.OK_CANCEL_OPTION) == 0)
        {
            try {
                _concluir(jTable_cardapio.getValueAt(jTable_cardapio.getSelectedRow(), 0)+"");
            } catch (SQLException ex) {
                Logger.getLogger(screen_cardapio_tabelas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_selecionarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new screen_cardapio_tabelas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_cancelar;
    private javax.swing.JButton jButton_selecionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable_cardapio;
    private javax.swing.JTextField jTextField_buscar;
    // End of variables declaration//GEN-END:variables
}
