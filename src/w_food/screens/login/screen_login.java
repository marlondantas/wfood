package w_food.screens.login;

import java.awt.event.KeyEvent;
import w_food.screens.funcionarios.screen_funcionario;
import w_food.db.mysql;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import w_food.db.funcs;

public class screen_login extends javax.swing.JFrame {
    
    mysql sql = new mysql();
    funcs func = new funcs();
    
    public screen_login() throws ClassNotFoundException, SQLException {
        initComponents();
        
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/w_food/images/logo_whatsfood_icone.png"));  
        setIconImage(icon.getImage());
        
        sql.startConnection();
        

        
        setLocationRelativeTo( null );
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        _cadastrar();
    }
    
    void _cadastrar() throws ClassNotFoundException, SQLException
    {
        int i =sql._contarRe();
        if(i>1)
        {
            
        }
        else
        {
            screen_cadastrar_usuario cu = new screen_cadastrar_usuario();
            cu.setVisible(true);
            
            this.dispose();
        }
    }
    
    void _login() throws ClassNotFoundException
    {
        boolean auth = false;
        try 
        {
            String pass=String.valueOf(jPasswordField_usuario.getPassword());
            auth = func.login(jFormattedTextField_usuario.getText(), pass);
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(screen_login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (auth)
        {
            try 
            {
                int i = sql.checkPermission(jFormattedTextField_usuario.getText());
                if (i == 1)
                {
                    screen_gerencia gen = new screen_gerencia();
                    gen._setcpf(jFormattedTextField_usuario.getText());
                    gen._setDataAtual();
                    
                    gen.setVisible(true);
                    this.dispose();
                }
                else
                {
                    
                    String dd[] = sql._buscarFun(jFormattedTextField_usuario.getText());
                    
                    screen_funcionario fun = new screen_funcionario();
                    
                    fun._setNomeCpf(dd);
                    
                    fun.setVisible(true);
                    this.dispose();
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(screen_login.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.toString(), "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Nome de usuário ou senha incorretos!", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(screen_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new screen_login().setVisible(true);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(screen_login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jButton_entrar = new javax.swing.JButton();
        jPasswordField_usuario = new javax.swing.JPasswordField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jFormattedTextField_usuario = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jButton_entrar.setBackground(new java.awt.Color(238, 56, 59));
        jButton_entrar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_entrar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_entrar.setText("Entrar");
        jButton_entrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_entrarjButton_entrarActionPerformed(evt);
            }
        });

        jPasswordField_usuario.setBorder(null);
        jPasswordField_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordField_usuarioKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel16.setText("Senha");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel17.setText("Usuário");

        jPanel9.setBackground(new java.awt.Color(238, 56, 59));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Login");

        jLabel15.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/logo_whatsfood_50.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addGap(97, 97, 97))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel18)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel15))
        );

        jFormattedTextField_usuario.setBorder(null);
        try {
            jFormattedTextField_usuario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField_usuarioActionPerformed(evt);
            }
        });
        jFormattedTextField_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField_usuarioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFormattedTextField_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel16)
                        .addComponent(jLabel17)
                        .addComponent(jPasswordField_usuario)
                        .addComponent(jSeparator8)
                        .addComponent(jSeparator9)
                        .addComponent(jButton_entrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, -1, 270));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/images/24-fast-food-icons.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1160, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_entrarjButton_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_entrarjButton_entrarActionPerformed
        try {
            _login();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(screen_login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_entrarjButton_entrarActionPerformed

    private void jFormattedTextField_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField_usuarioActionPerformed

    private void jFormattedTextField_usuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField_usuarioKeyReleased
               
        String _cpfSemformt = jFormattedTextField_usuario.getText();
        
        _cpfSemformt = _cpfSemformt.replace(".","");
        _cpfSemformt = _cpfSemformt.replace("-", "");
        _cpfSemformt = _cpfSemformt.replace(" ","");
        
        if(_cpfSemformt.length()>= 11)
        {
            jPasswordField_usuario.requestFocus();
        }
        
        
    }//GEN-LAST:event_jFormattedTextField_usuarioKeyReleased

    private void jPasswordField_usuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField_usuarioKeyReleased
               if(evt.getKeyChar() == KeyEvent.VK_ENTER && !jPasswordField_usuario.getPassword().equals(""))
        {
                   try {          
                       _login();
                   } catch (ClassNotFoundException ex) {
                       Logger.getLogger(screen_login.class.getName()).log(Level.SEVERE, null, ex);
                   }
        }
    }//GEN-LAST:event_jPasswordField_usuarioKeyReleased

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_entrar;
    private javax.swing.JFormattedTextField jFormattedTextField_usuario;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField_usuario;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    // End of variables declaration//GEN-END:variables
}
