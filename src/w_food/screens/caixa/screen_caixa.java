
package w_food.screens.caixa;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class screen_caixa extends javax.swing.JFrame {

    private JSeparator jSeparator8;

    
    
    public screen_caixa() throws ClassNotFoundException, SQLException {
        initComponents();
        
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/w_food/images/logo_whatsfood_icone.png"));  
        setIconImage(icon.getImage());
        
        
        sql.startConnection();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
       
    //<back>
    
    screen_cadastrar_cliente cadastrarCliente;
    
    String cliente[] = new String[5];
    String produto[] = new String[5];
    String Funcionario[] = new String[19];
    
    DecimalFormat df = new DecimalFormat("#,###.00");
    
    float  _total=0;




    w_food.db.mysql sql = new w_food.db.mysql();
    w_food.db.funcs func = new w_food.db.funcs();
    

    public void _dadosUSER(String dados[])
    {
        Funcionario = dados;
    }
    void _limparTUDO()
    {
      //limpar o campo clientecliente[]=new String[5];
      cliente = new String[5];
      produto = new String[5];
      _total = 0;
      
      _limparProduto();
      _limparRCliente();
      _limparMetodosPag();
      _limparTabela();
       
      jTabbedPane_painel.setSelectedIndex(0);
      jFormattedTextField_cpf_do_cliente_cad.requestFocus();
        try {
           _limparTabela();
        } catch (Exception e) {
        }

    }
    void _limparTabela()
    {
        DefaultTableModel model = (DefaultTableModel) jTable_caixa_produtos.getModel();
        
        for (int i = 0; i < jTable_caixa_produtos.getRowCount()+1; i++) {
            model.removeRow(0);      
        }
    }
    void _limparMetodosPag()
    {
        jTextField_metodo_credito.setText("");
        jTextField_metodo_debito.setText("");
        jTextField_metodo_dinheiro.setText("");
        
        jLabel_caixa_total.setText("");
        jLabel_caixa_total.setBackground(Color.BLACK);
        
    }
    void _limparRCliente()
    {
        jLabel_cliente_telefone.setText("");
        jLabel_cliente_cnome.setText("");
        jLabel_cliente_email.setText("");
        jLabel_cliente_cpf.setText("");
//        jLabel_cliente_endereco.setText("");

        
        jFormattedTextField_cpf_do_cliente_cad.setText("");
    }
    
    void _removerProduto()
    {
        String cod_prod = JOptionPane.showInputDialog(null,"Digite o codigo do produto que deseja retirar","Remover Produto da venda",JOptionPane.OK_CANCEL_OPTION);
        
        for(int i = 0; i < jTable_caixa_produtos.getModel().getRowCount(); i++)
        {
          if(jTable_caixa_produtos.getValueAt(i, 0).equals(cod_prod))
          {
              //JOptionPane.showMessageDialog(null,"teste", "teste",JOptionPane.OK_CANCEL_OPTION);
              
              DefaultTableModel table = (DefaultTableModel) jTable_caixa_produtos.getModel();
              _total = Float.parseFloat(jTable_caixa_produtos.getValueAt(i, 5)+"") - _total;
            
              jLabel_caixa_total.setText("R$"+df.format(_total)+"");
              
              table.removeRow(i);
          }
        }
    
    }
    
    void _voltar()
    {}
    
    String[] _buscarProduto(String _codProduto) throws SQLException
    {
        String _dado[] = new String[10];

        if(sql.checkCardapio(_codProduto))
        {
        
            _dado = sql._checarProduto(_codProduto);
            //caso de erro aqui 
        
        }
        else
        {
            _dado[0]="null";
        }
        return _dado;
    }
    
    String[] _adicionarProduto(String _codProduto) throws SQLException, ClassNotFoundException
    {
        String _dados[] = _buscarProduto(_codProduto);
        if(_dados[0].equals("null"))
        {
            _abrirTabelaProds();
        }
        
        
        String _saida[] = new String[3];
        
        _saida[0]=_dados[1];
        _saida[1]=_dados[3];
        _saida[2]=_codProduto;
                
                
        //JOptionPane.showMessageDialog(null,""+_saida[0]+_saida[1]+_saida[2],"Certo",JOptionPane.INFORMATION_MESSAGE);
        
        
        return _saida;
    }
    
    void _addprodutotabela()
    {
        DefaultTableModel model = (DefaultTableModel) jTable_caixa_produtos.getModel();
        
          
        float total= Float.parseFloat(produto[3].replace(",",".")) * Float.parseFloat(produto[1].replace(",","."));
        
        total= total -((Float.parseFloat(produto[4].replace(",","."))/100) * total);
        
        String Ttotal=df.format(total);
        String preco = df.format(Float.parseFloat(produto[1].replace(",",".")));
                                //codigo     nome         qtde        preco       desconto    total
        model.addRow(new Object[]{produto[2], produto[0] ,produto[3] ,preco, produto[4].replace(",","."), Ttotal.replace(",",".")});

        //calcular total aqui 
        _calcularTotal();
        //limpar os campos
        _limparProduto();

    }
    void _calcularTotal()
    {
        _total=0;
        for (int i = 0; i < jTable_caixa_produtos.getRowCount();i++) {
            _total=_total+Float.parseFloat((jTable_caixa_produtos.getValueAt(i, 5)+"").replace(",","."));
            
        }
        jLabel_caixa_total.setText("R$"+df.format(_total).replace(",",".")+"");
    }
    
    void _limparProduto()
    {
        jTextField_produto_buscar_cod.setText("");
        jTextField_produto_desconto.setText("");
        jTextField_valor_unitario.setText("");
        jTextField_produto_valor_de_venda.setText("");
        jLabel_produtos_nome.setText("");
        jTextField_produto_quantidade.setText("");
        
        
        jTextField_produto_buscar_cod.requestFocus();
    
    }
    
    void _abrirProdutos()
    {     
        jTabbedPane_painel.setSelectedIndex(1);
        
        jTextField_produto_buscar_cod.requestFocus();   
    }
    void _abrirFormaspag()
    {
        jTabbedPane_painel.setSelectedIndex(2);
    
    }
    void _somarTabela(float valor)
    {
        _total = _total + valor;
        
    }
    void _setTextlabelproduto(String _dado[])
    {      
        jLabel_produtos_nome.setText(_dado[0]);
        jTextField_produto_valor_de_venda.setText(_dado[1]);
        
        jTextField_produto_quantidade.requestFocus();
    }

    void _buscarCliente(String cpf) throws SQLException
    {
        String dado[] = sql._checarNomeCliente(cpf);
        
        cliente[0]=dado[0];
        if(cliente[0]=="Cliente Desativo")
        {
            _abrirCadastraCliente(cpf); 
        }
        else
        {
        cliente[1]=dado[1];
        cliente[2]=dado[2]; 
        cliente[3]=dado[3];
        cliente[4]=cpf;
        }
        //JOptionPane.showMessageDialog(null,cliente[4]+cliente[3]+cliente[2]+cliente[1]+cliente[0],"VEr mes",JOptionPane.INFORMATION_MESSAGE);
    }
    void _setTextlabel()
    {
        jLabel_cliente_telefone.setText(cliente[2]);
        jLabel_cliente_cnome.setText(cliente[0]);
        jLabel_cliente_email.setText(cliente[3]);
        jLabel_cliente_cpf.setText(cliente[4]);
        //jLabel_cliente_endereco.setText(cliente[1]);

        jButton_cliente_Continuar.requestFocus();
    }

    public screen_caixa(screen_cadastrar_cliente cadastrarCliente, JButton jButton1, JButton jButton3, JButton jButton4, JButton jButton5, JButton jButton8, JButton jButton_cliente_Continuar, JButton jButton_cliente_cadastrar, JButton jButton_produto_inserir, JFormattedTextField jFormattedTextField_cpf_do_cliente_cad, JLabel jLabel1, JLabel jLabel10, JLabel jLabel11, JLabel jLabel12, JLabel jLabel13, JLabel jLabel15, JLabel jLabel16, JLabel jLabel18, JLabel jLabel19, JLabel jLabel2, JLabel jLabel20, JLabel jLabel21, JLabel jLabel22, JLabel jLabel23, JLabel jLabel24, JLabel jLabel25, JLabel jLabel26, JLabel jLabel3, JLabel jLabel30, JLabel jLabel32, JLabel jLabel4, JLabel jLabel5, JLabel jLabel6, JLabel jLabel7, JLabel jLabel8, JLabel jLabel9, JLabel jLabel_caixa_total, JLabel jLabel_cliente_cnome, JLabel jLabel_cliente_cpf, JLabel jLabel_cliente_email, JLabel jLabel_cliente_endereco, JLabel jLabel_cliente_telefone, JLabel jLabel_produtos_nome, JLabel jLabel_produtos_nome2, JPanel jPanel2, JPanel jPanel4, JPanel jPanel5, JPanel jPanel7, JProgressBar jProgressBar1, JScrollPane jScrollPane1, JSeparator jSeparator1, JSeparator jSeparator2, JSeparator jSeparator3, JSeparator jSeparator4, JSeparator jSeparator6, JSeparator jSeparator7, JSeparator jSeparator8, JSeparator jSeparator9, JTabbedPane jTabbedPane_painel, JTable jTable_caixa_produtos, JTextField jTextField11, JTextField jTextField12, JTextField jTextField6, JTextField jTextField9, JTextField jTextField_produto_buscar_cod, JTextField jTextField_produto_desconto, JTextField jTextField_produto_quantidade, JTextField jTextField_produto_valor_de_venda, JTextField jTextField_valor_unitario) throws HeadlessException {
        this.cadastrarCliente = cadastrarCliente;
        this.jButton_Produtos_tabela = jButton1;
        this.jButton_cancelar_vendas = jButton3;
        this.jButton_finalizar_venda = jButton4;
        //this.jButton5 = jButton5;
        //this.jButton8 = jButton8;
        this.jButton_cliente_Continuar = jButton_cliente_Continuar;
        this.jButton_cliente_cadastrar = jButton_cliente_cadastrar;
        this.jButton_produto_inserir = jButton_produto_inserir;
        this.jFormattedTextField_cpf_do_cliente_cad = jFormattedTextField_cpf_do_cliente_cad;
        //this.jLabel1 = jLabel1;
//        this.jLabel10 = jLabel10;
        //this.jLabel11 = jLabel11;
        this.jLabel12 = jLabel12;
        this.jLabel13 = jLabel13;
        this.jLabel15 = jLabel15;
        this.jLabel16 = jLabel16;
        this.jLabel18 = jLabel18;
        this.jLabel19 = jLabel19;
        this.jLabel2 = jLabel2;
        this.jLabel20 = jLabel20;
        this.jLabel21 = jLabel21;
        this.jLabel22 = jLabel22;
        this.jLabel23 = jLabel23;
        this.jLabel24 = jLabel24;
        this.jLabel25 = jLabel25;
        this.jLabel26 = jLabel26;
        this.jLabel3 = jLabel3;
        this.jLabel30 = jLabel30;
        this.jLabel32 = jLabel32;
        this.jLabel4 = jLabel4;
        this.jLabel5 = jLabel5;
        this.jLabel6 = jLabel6;
        this.jLabel7 = jLabel7;
        this.jLabel8 = jLabel8;
        this.jLabel9 = jLabel9;
        this.jLabel_caixa_total = jLabel_caixa_total;
        this.jLabel_cliente_cnome = jLabel_cliente_cnome;
        this.jLabel_cliente_cpf = jLabel_cliente_cpf;
        this.jLabel_cliente_email = jLabel_cliente_email;
       // this.jLabel_cliente_endereco = jLabel_cliente_endereco;
        this.jLabel_cliente_telefone = jLabel_cliente_telefone;
        this.jLabel_produtos_nome = jLabel_produtos_nome;
        this.jLabel_produtos_nome2 = jLabel_produtos_nome2;
        this.jPanel2 = jPanel2;
        this.jPanel4 = jPanel4;
        this.jPanel5 = jPanel5;
        this.jPanel7 = jPanel7;

        this.jScrollPane1 = jScrollPane1;
        //this.jSeparator1 = jSeparator1;
        this.jSeparator2 = jSeparator2;
        this.jSeparator3 = jSeparator3;
        this.jSeparator4 = jSeparator4;
        this.jSeparator6 = jSeparator6;
        this.jSeparator7 = jSeparator7;
        this.jSeparator8 = jSeparator8;
        this.jTabbedPane_painel = jTabbedPane_painel;
        this.jTable_caixa_produtos = jTable_caixa_produtos;
        this.jTextField_metodo_debito = jTextField11;
        this.jTextField_metodo_credito = jTextField12;
        this.jTextField6 = jTextField6;
        this.jTextField_metodo_dinheiro = jTextField9;
        this.jTextField_produto_buscar_cod = jTextField_produto_buscar_cod;
        this.jTextField_produto_desconto = jTextField_produto_desconto;
        this.jTextField_produto_quantidade = jTextField_produto_quantidade;
        this.jTextField_produto_valor_de_venda = jTextField_produto_valor_de_venda;
        this.jTextField_valor_unitario = jTextField_valor_unitario;
    }
    
    public void _cadastrarCliente(String nome, String endereco, String cpf, String telefone, String email) throws SQLException
    {
        if(sql.checkCliente(cpf))
        {
            JOptionPane.showMessageDialog(null,"CPF já Cadastrado OTARIO","ERRO",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
        sql.registerCliente(nome, endereco, cpf, telefone, email);
        
        cliente[0]=nome;
        cliente[1]=endereco;
        cliente[2]=telefone; 
        cliente[3]=email;
        cliente[4]=cpf;
        
        _buscarCliente(cpf);
        _limparRCliente();
        _setTextlabel();

        
        }
    }
    
    
    void _realizarPagamento()
    {}
    
    void _calcularTroco()
    {}
    
    //Textchanged:
    void _textchange(String cpf) throws SQLException
    {
            
        String _dado=cpf;
        
        _dado= _dado.replace(" ", "");
        
        

        if(_dado.length() >= 14)
        {
            _buscarCliente(cpf);
            _limparRCliente();
            _setTextlabel();
            
        }

    }
    void _calcularValores()
    {
        float totais[] = new float[3];
        
        totais[0] = Float.parseFloat(jTextField_metodo_credito.getText());
        totais[1] = Float.parseFloat(jTextField_metodo_debito.getText());
        totais[2] = Float.parseFloat(jTextField_metodo_dinheiro.getText());
        
        
        
        if(_total <= (totais[0]+totais[1]+totais[2]))
        {
            //pode ir embora
            
            //JOptionPane.showMessageDialog(null,"aqui funfnougdslkhfdskjfds","cores",JOptionPane.OK_OPTION);
            _total=(totais[0]+totais[1]+totais[2]);
            jButton_finalizar_venda.requestFocus();
            
        }
        else
        {
            //opa falta moneis
            
            jLabel_metodo_total.setText(((totais[0]+totais[1]+totais[2]) - _total)+"");
            
            jLabel_metodo_total.setForeground(Color.red);
            
            jTextField_metodo_dinheiro.requestFocus();
        }
        
    }
    void _abrirCadastraCliente(String cpd)
    {
        w_food.screens.caixa.screen_cadastrar_cliente cc = new w_food.screens.caixa.screen_cadastrar_cliente(this);
        cc.jFormattedTextField_cpf.setText(cpd);
        
        cc.setVisible(true);
        
    }
    void _abrirTabelaProds() throws ClassNotFoundException, SQLException
    {
        w_food.screens.caixa.screen_cardapio_tabelas cct= new  w_food.screens.caixa.screen_cardapio_tabelas(this);
        cct.setVisible(true);
        
    }
    
    void _calcularQuantidade(String quantidade)
    {
        Integer quan = Integer.parseInt(quantidade); 
        
    }
    
    void _abirFinalizar() throws ClassNotFoundException, SQLException
    {
    
        w_food.screens.caixa.screen_resumo_da_venda cc = new w_food.screens.caixa.screen_resumo_da_venda(this);
        
        cc._setCliente(cliente);
        cc._setdataTable(jTable_caixa_produtos.getModel());
        
        cc._setValor(jLabel_caixa_total.getText().replace("R$",""),_total+"");
        cc._setDados(cliente[4],Funcionario[2]);
        
        cc.setVisible(true);
        
    }
    //</back>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField6 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_remover = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_caixa_produtos = new javax.swing.JTable();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel_caixa_total = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTabbedPane_painel = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jFormattedTextField_cpf_do_cliente_cad = new javax.swing.JFormattedTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jButton_cliente_cadastrar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel_cliente_cnome = new javax.swing.JLabel();
        jLabel_cliente_cpf = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel_cliente_email = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel_cliente_telefone = new javax.swing.JLabel();
        jButton_cliente_Continuar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTextField_produto_valor_de_venda = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_produto_quantidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_produto_desconto = new javax.swing.JTextField();
        jTextField_produto_buscar_cod = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton_Produtos_tabela = new javax.swing.JButton();
        jTextField_valor_unitario = new javax.swing.JTextField();
        jButton_produto_inserir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel_produtos_nome2 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel_produtos_nome = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField_metodo_debito = new javax.swing.JTextField();
        jTextField_metodo_dinheiro = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField_metodo_credito = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel_metodo_total = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jButton_finalizar_venda = new javax.swing.JButton();
        jButton_cancelar_vendas = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        jTextField6.setText("jTextField6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(238, 56, 59));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("CAIXA LIVRE");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Nome do Prod.");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Qtde.");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Preço un.");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Valor Total");

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel28.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("(%)");

        jLabel29.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Cd.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addGap(70, 70, 70)
                .addComponent(jLabel25)
                .addGap(53, 53, 53)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jLabel26)
                .addGap(40, 40, 40))
            .addComponent(jSeparator5)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(jLabel21)
                        .addComponent(jLabel23)
                        .addComponent(jLabel25)
                        .addComponent(jLabel28)
                        .addComponent(jLabel26))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1160, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_remover.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel_remover.setForeground(new java.awt.Color(204, 0, 0));
        jLabel_remover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_lixeira_red.png"))); // NOI18N
        jLabel_remover.setText("Remover");
        jLabel_remover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_removerMouseClicked(evt);
            }
        });
        jPanel2.add(jLabel_remover, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 420, -1, -1));

        jSeparator9.setBackground(new java.awt.Color(238, 56, 59));
        jSeparator9.setForeground(new java.awt.Color(238, 56, 59));
        jSeparator9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 56, 59), 10));
        jPanel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 1160, 20));

        jTable_caixa_produtos.setTableHeader(null);
        jTable_caixa_produtos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5", "Título 6"
            }
        ));
        jTable_caixa_produtos.setGridColor(new java.awt.Color(254, 210, 64));
        jScrollPane1.setViewportView(jTable_caixa_produtos);
        if (jTable_caixa_produtos.getColumnModel().getColumnCount() > 0) {
            jTable_caixa_produtos.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 600, 450));

        jSeparator11.setBackground(new java.awt.Color(238, 56, 59));
        jSeparator11.setForeground(new java.awt.Color(238, 56, 59));
        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 56, 59), 10));
        jSeparator11.setFocusTraversalPolicyProvider(true);
        jSeparator11.setRequestFocusEnabled(false);
        jPanel2.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 20, 440));

        jLabel_caixa_total.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel_caixa_total.setForeground(new java.awt.Color(238, 56, 59));
        jLabel_caixa_total.setText("00,00");
        jPanel2.add(jLabel_caixa_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 460, -1, 40));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(238, 56, 59));
        jLabel13.setText("Total:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 470, -1, -1));

        jTabbedPane_painel.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane_painel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane_painel.setForeground(new java.awt.Color(238, 56, 59));
        jTabbedPane_painel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTabbedPane_painel.setNextFocusableComponent(jButton_cliente_Continuar);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        try {
            jFormattedTextField_cpf_do_cliente_cad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_cpf_do_cliente_cad.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jFormattedTextField_cpf_do_cliente_cadInputMethodTextChanged(evt);
            }
        });
        jFormattedTextField_cpf_do_cliente_cad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextField_cpf_do_cliente_cadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField_cpf_do_cliente_cadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextField_cpf_do_cliente_cadKeyTyped(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(254, 210, 64));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(238, 56, 59));
        jLabel12.setText("Dados do Cliente");

        jSeparator3.setForeground(new java.awt.Color(254, 210, 64));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Nome do Cliente");

        jSeparator7.setForeground(new java.awt.Color(254, 210, 64));

        jButton_cliente_cadastrar.setBackground(new java.awt.Color(204, 204, 204));
        jButton_cliente_cadastrar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_cliente_cadastrar.setText("Cadastrar Novo Cliente");
        jButton_cliente_cadastrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_cliente_cadastrar.setPreferredSize(new java.awt.Dimension(149, 25));
        jButton_cliente_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cliente_cadastrarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("CPF");

        jLabel_cliente_cnome.setForeground(new java.awt.Color(238, 56, 59));
        jLabel_cliente_cnome.setToolTipText("");

        jLabel_cliente_cpf.setForeground(new java.awt.Color(238, 56, 59));
        jLabel_cliente_cpf.setToolTipText("");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel30.setText("Email");

        jLabel_cliente_email.setForeground(new java.awt.Color(238, 56, 59));
        jLabel_cliente_email.setToolTipText("");

        jLabel32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel32.setText("Telefone");

        jLabel_cliente_telefone.setForeground(new java.awt.Color(238, 56, 59));
        jLabel_cliente_telefone.setToolTipText("");

        jButton_cliente_Continuar.setBackground(new java.awt.Color(149, 199, 52));
        jButton_cliente_Continuar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_cliente_Continuar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_cliente_Continuar.setText("Continuar");
        jButton_cliente_Continuar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_cliente_Continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cliente_ContinuarActionPerformed(evt);
            }
        });
        jButton_cliente_Continuar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_cliente_ContinuarKeyPressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("CPF");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField_cpf_do_cliente_cad, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_cliente_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel30))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_cliente_email, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(jLabel_cliente_cpf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_cliente_telefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_cliente_cnome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(170, Short.MAX_VALUE))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_cliente_Continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jButton_cliente_cadastrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jFormattedTextField_cpf_do_cliente_cad, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jLabel_cliente_cnome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel_cliente_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_cliente_email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jLabel_cliente_telefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(79, 79, 79)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_cliente_Continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jTabbedPane_painel.addTab("Clientes (F1)", jPanel4);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jTextField_produto_valor_de_venda.setAutoscrolls(false);
        jTextField_produto_valor_de_venda.setDoubleBuffered(true);
        jTextField_produto_valor_de_venda.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel4.setText("Quantidade");

        jTextField_produto_quantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_produto_quantidadeKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel5.setText("Desconto (%)*");

        jTextField_produto_desconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_produto_descontoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_produto_descontoKeyTyped(evt);
            }
        });

        jTextField_produto_buscar_cod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_produto_buscar_codKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel6.setText("Valor unitário");

        jButton_Produtos_tabela.setBackground(new java.awt.Color(204, 204, 204));
        jButton_Produtos_tabela.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_Produtos_tabela.setText("Abrir Tabela de Produtos");
        jButton_Produtos_tabela.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_Produtos_tabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Produtos_tabelaActionPerformed(evt);
            }
        });

        jTextField_valor_unitario.setSelectionColor(new java.awt.Color(254, 210, 64));
        jTextField_valor_unitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_valor_unitarioFocusGained(evt);
            }
        });
        jTextField_valor_unitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_valor_unitarioKeyPressed(evt);
            }
        });

        jButton_produto_inserir.setBackground(new java.awt.Color(149, 199, 52));
        jButton_produto_inserir.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_produto_inserir.setForeground(new java.awt.Color(255, 255, 255));
        jButton_produto_inserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_add.png"))); // NOI18N
        jButton_produto_inserir.setText("Inserir");
        jButton_produto_inserir.setToolTipText("");
        jButton_produto_inserir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_produto_inserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_produto_inserirActionPerformed(evt);
            }
        });
        jButton_produto_inserir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_produto_inserirKeyPressed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_lupa.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel3.setText("Valor de Venda");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(149, 199, 52));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_confirmar.png"))); // NOI18N
        jLabel19.setText("Enter para inserir");

        jLabel_produtos_nome2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel_produtos_nome2.setText("Nome");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel20.setText("Código do produto");

        jSeparator12.setForeground(new java.awt.Color(254, 210, 64));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(238, 56, 59));
        jLabel14.setText("Produtos");

        jSeparator13.setForeground(new java.awt.Color(254, 210, 64));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextField_produto_quantidade)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel_produtos_nome2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel_produtos_nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField_produto_valor_de_venda, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_produto_desconto, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton_produto_inserir, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addComponent(jTextField_valor_unitario, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_produto_buscar_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Produtos_tabela, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jTextField_produto_buscar_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_Produtos_tabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(1, 1, 1)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel_produtos_nome2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_produtos_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_produto_valor_de_venda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_produto_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_produto_desconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_valor_unitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(jButton_produto_inserir)
                        .addGap(116, 116, 116))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jTabbedPane_painel.addTab("Produtos (F2)", jPanel7);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator6.setForeground(new java.awt.Color(254, 210, 64));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(238, 56, 59));
        jLabel15.setText("Dinheiro");

        jSeparator4.setForeground(new java.awt.Color(254, 210, 64));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel22.setText("Valor Recebido por Débito");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel16.setText("Valor Recebido");

        jTextField_metodo_debito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_metodo_debitoActionPerformed(evt);
            }
        });
        jTextField_metodo_debito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_metodo_debitoKeyPressed(evt);
            }
        });

        jTextField_metodo_dinheiro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_metodo_dinheiroFocusGained(evt);
            }
        });
        jTextField_metodo_dinheiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_metodo_dinheiroKeyPressed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel24.setText("Valor Recebido por Crédito ");

        jTextField_metodo_credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_metodo_creditoActionPerformed(evt);
            }
        });
        jTextField_metodo_credito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_metodo_creditoKeyPressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(238, 56, 59));
        jLabel18.setText("Cartão");

        jLabel27.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(238, 56, 59));
        jLabel27.setText("Total Cartão");

        jSeparator10.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));

        jLabel_metodo_total.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel_metodo_total.setText("00.00");

        jSeparator14.setForeground(new java.awt.Color(254, 210, 64));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField_metodo_debito, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                            .addComponent(jTextField_metodo_credito, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField_metodo_dinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 139, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_metodo_total)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator14, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
                            .addComponent(jLabel15)
                            .addComponent(jLabel18))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField_metodo_dinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField_metodo_debito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField_metodo_credito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_metodo_total)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane_painel.addTab("Forma de Pagamento (F3)", jPanel5);

        jPanel2.add(jTabbedPane_painel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 450));

        jButton_finalizar_venda.setBackground(new java.awt.Color(236, 56, 59));
        jButton_finalizar_venda.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_finalizar_venda.setForeground(new java.awt.Color(255, 255, 255));
        jButton_finalizar_venda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_confirmar.png"))); // NOI18N
        jButton_finalizar_venda.setText("Finalizar Venda");
        jButton_finalizar_venda.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
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
        jPanel2.add(jButton_finalizar_venda, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 470, 240, 30));

        jButton_cancelar_vendas.setBackground(new java.awt.Color(204, 204, 204));
        jButton_cancelar_vendas.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_cancelar_vendas.setForeground(new java.awt.Color(102, 102, 102));
        jButton_cancelar_vendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_cancelar.png"))); // NOI18N
        jButton_cancelar_vendas.setText("Cancelar Venda");
        jButton_cancelar_vendas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_cancelar_vendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_vendasActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_cancelar_vendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 220, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 1140, 510));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/images/24-fast-food-icons.png"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, -1, 760));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_finalizar_vendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_finalizar_vendaActionPerformed
        try {
            _abirFinalizar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(screen_caixa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(screen_caixa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_finalizar_vendaActionPerformed

    private void jButton_cliente_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cliente_cadastrarActionPerformed
        
        
        _abrirCadastraCliente("");
        
        
    }//GEN-LAST:event_jButton_cliente_cadastrarActionPerformed

    private void jFormattedTextField_cpf_do_cliente_cadInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jFormattedTextField_cpf_do_cliente_cadInputMethodTextChanged

    }//GEN-LAST:event_jFormattedTextField_cpf_do_cliente_cadInputMethodTextChanged

    private void jFormattedTextField_cpf_do_cliente_cadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField_cpf_do_cliente_cadKeyReleased

        
        try 
        {                                                                          
            _textchange(jFormattedTextField_cpf_do_cliente_cad.getText());            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(screen_caixa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jFormattedTextField_cpf_do_cliente_cadKeyReleased

    private void jFormattedTextField_cpf_do_cliente_cadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField_cpf_do_cliente_cadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField_cpf_do_cliente_cadKeyTyped

    private void jTextField_produto_buscar_codKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_produto_buscar_codKeyPressed
        
        //<BACK>
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            try {
               if(jTextField_produto_buscar_cod.getText().equals(""))
               {
                   if(_total>0)
                   {
                       _abrirFormaspag();
                   }
               }
               else
               {
                String _dd[] = _adicionarProduto(jTextField_produto_buscar_cod.getText());
                    
                produto[0]=_dd[0];//nome
                produto[1]=_dd[1];//valor
                produto[2]=_dd[2];//codigo
                
                _setTextlabelproduto(_dd);
                
                
               }
            } catch (SQLException ex) {
                Logger.getLogger(screen_caixa.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(screen_caixa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jTextField_produto_buscar_codKeyPressed

    private void jTextField_produto_descontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_produto_descontoKeyTyped

            //erro aqui
    }//GEN-LAST:event_jTextField_produto_descontoKeyTyped

    private void jTextField_produto_descontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_produto_descontoKeyPressed
        
        //<BACK>
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            if(jTextField_produto_desconto.getText().equals(""))
            {
                //guarda o numero de desconto na string
                produto[4] = 0+"";
                jTextField_produto_desconto.setText("0");
            }
            else
            {
                //pega o valor da caixa de texto;
                
                produto[4] = Integer.parseInt(jTextField_produto_desconto.getText())+"";
                
            }
            
            jTextField_valor_unitario.requestFocus();
        }
     
    }//GEN-LAST:event_jTextField_produto_descontoKeyPressed

    private void jTextField_produto_quantidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_produto_quantidadeKeyPressed

        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
           if(jTextField_produto_quantidade.getText().equals(""))
           {  
               produto[3]= 1 + "";     
               jTextField_produto_quantidade.setText("1");
           }
           else
           {
               produto[3]= Integer.parseInt(jTextField_produto_quantidade.getText())+"";
           }
           
           jTextField_produto_desconto.requestFocus();
           
        }
                
    }//GEN-LAST:event_jTextField_produto_quantidadeKeyPressed

    private void jButton_produto_inserirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_produto_inserirKeyPressed

        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            //agora que comeca a treta
           _addprodutotabela();

        }
    }//GEN-LAST:event_jButton_produto_inserirKeyPressed

    private void jButton_produto_inserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_produto_inserirActionPerformed
        
        if(jTextField_valor_unitario.getText().equals(""))
        {
            produto[4]=produto[1];
        }
            //agora que comeca a treta
        _addprodutotabela();

    }//GEN-LAST:event_jButton_produto_inserirActionPerformed

    private void jButton_cliente_ContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cliente_ContinuarActionPerformed
        _abrirProdutos();
    }//GEN-LAST:event_jButton_cliente_ContinuarActionPerformed

    private void jButton_cliente_ContinuarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_cliente_ContinuarKeyPressed
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            //agora que comeca a treta
           _abrirProdutos();
        }
        
    }//GEN-LAST:event_jButton_cliente_ContinuarKeyPressed

    private void jTextField_valor_unitarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_valor_unitarioKeyPressed
      
        
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            
            jButton_produto_inserir.requestFocus();
        }
        
    }//GEN-LAST:event_jTextField_valor_unitarioKeyPressed

    private void jTextField_metodo_creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_metodo_creditoActionPerformed

    }//GEN-LAST:event_jTextField_metodo_creditoActionPerformed

    private void jTextField_metodo_dinheiroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_metodo_dinheiroKeyPressed

        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            if(jTextField_metodo_dinheiro.getText().equals(""))
            {
                jTextField_metodo_dinheiro.setText("00.00");
            }
            
               jTextField_metodo_debito.requestFocus();
        }

    }//GEN-LAST:event_jTextField_metodo_dinheiroKeyPressed

    private void jTextField_metodo_debitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_metodo_debitoActionPerformed

    }//GEN-LAST:event_jTextField_metodo_debitoActionPerformed

    private void jTextField_metodo_debitoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_metodo_debitoKeyPressed
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            if(jTextField_metodo_debito.getText().equals(""))
            {
                jTextField_metodo_debito.setText("00.00");
            }
            
            jTextField_metodo_credito.requestFocus();
        }

    }//GEN-LAST:event_jTextField_metodo_debitoKeyPressed

    private void jTextField_metodo_creditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_metodo_creditoKeyPressed
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
             if(jTextField_metodo_credito.getText().equals(""))
            {
                jTextField_metodo_credito.setText("00.00");
            }
            _calcularValores();
        }
    }//GEN-LAST:event_jTextField_metodo_creditoKeyPressed

    private void jButton_Produtos_tabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Produtos_tabelaActionPerformed
        
        try {
            _abrirTabelaProds();
            
            // abrir a tabela de produtos para selecao;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(screen_caixa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(screen_caixa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }//GEN-LAST:event_jButton_Produtos_tabelaActionPerformed

    private void jTextField_valor_unitarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_valor_unitarioFocusGained
        jTextField_valor_unitario.setText(jTextField_produto_valor_de_venda.getText());
    }//GEN-LAST:event_jTextField_valor_unitarioFocusGained

    private void jTextField_metodo_dinheiroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_metodo_dinheiroFocusGained
        jTextField_metodo_dinheiro.setText("");
    }//GEN-LAST:event_jTextField_metodo_dinheiroFocusGained

    private void jButton_finalizar_vendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_finalizar_vendaKeyPressed

        if(evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            try {
                _abirFinalizar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(screen_caixa.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(screen_caixa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton_finalizar_vendaKeyPressed

    private void jButton_cancelar_vendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelar_vendasActionPerformed
        _limparTUDO();
    }//GEN-LAST:event_jButton_cancelar_vendasActionPerformed

    private void jFormattedTextField_cpf_do_cliente_cadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField_cpf_do_cliente_cadKeyPressed
       if(evt.getKeyChar() == KeyEvent.VK_ENTER)
       {
               _abrirProdutos();
       }
    }//GEN-LAST:event_jFormattedTextField_cpf_do_cliente_cadKeyPressed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        //voltar
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel_removerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_removerMouseClicked
       _removerProduto();
       _calcularTotal();
    }//GEN-LAST:event_jLabel_removerMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new screen_caixa().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(screen_caixa.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(screen_caixa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton_Produtos_tabela;
    public javax.swing.JButton jButton_cancelar_vendas;
    public javax.swing.JButton jButton_cliente_Continuar;
    public javax.swing.JButton jButton_cliente_cadastrar;
    public javax.swing.JButton jButton_finalizar_venda;
    public javax.swing.JButton jButton_produto_inserir;
    public javax.swing.JFormattedTextField jFormattedTextField_cpf_do_cliente_cad;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel23;
    public javax.swing.JLabel jLabel24;
    public javax.swing.JLabel jLabel25;
    public javax.swing.JLabel jLabel26;
    public javax.swing.JLabel jLabel27;
    public javax.swing.JLabel jLabel28;
    public javax.swing.JLabel jLabel29;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel30;
    public javax.swing.JLabel jLabel32;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabel_caixa_total;
    public javax.swing.JLabel jLabel_cliente_cnome;
    public javax.swing.JLabel jLabel_cliente_cpf;
    public javax.swing.JLabel jLabel_cliente_email;
    public javax.swing.JLabel jLabel_cliente_telefone;
    public javax.swing.JLabel jLabel_metodo_total;
    public javax.swing.JLabel jLabel_produtos_nome;
    public javax.swing.JLabel jLabel_produtos_nome2;
    public javax.swing.JLabel jLabel_remover;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel7;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JSeparator jSeparator10;
    public javax.swing.JSeparator jSeparator11;
    public javax.swing.JSeparator jSeparator12;
    public javax.swing.JSeparator jSeparator13;
    public javax.swing.JSeparator jSeparator14;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JSeparator jSeparator3;
    public javax.swing.JSeparator jSeparator4;
    public javax.swing.JSeparator jSeparator5;
    public javax.swing.JSeparator jSeparator6;
    public javax.swing.JSeparator jSeparator7;
    public javax.swing.JSeparator jSeparator9;
    public javax.swing.JTabbedPane jTabbedPane_painel;
    public javax.swing.JTable jTable_caixa_produtos;
    public javax.swing.JTextField jTextField6;
    public javax.swing.JTextField jTextField_metodo_credito;
    public javax.swing.JTextField jTextField_metodo_debito;
    public javax.swing.JTextField jTextField_metodo_dinheiro;
    public javax.swing.JTextField jTextField_produto_buscar_cod;
    public javax.swing.JTextField jTextField_produto_desconto;
    public javax.swing.JTextField jTextField_produto_quantidade;
    public javax.swing.JTextField jTextField_produto_valor_de_venda;
    public javax.swing.JTextField jTextField_valor_unitario;
    // End of variables declaration//GEN-END:variables
}
