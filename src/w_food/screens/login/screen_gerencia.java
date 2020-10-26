package w_food.screens.login;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import static w_food.db.mysql.con;

public class screen_gerencia extends javax.swing.JFrame {

    public screen_gerencia() throws ClassNotFoundException, SQLException {
        initComponents();
        
                ImageIcon icon = new ImageIcon(this.getClass().getResource("/w_food/images/logo_whatsfood_icone.png"));  
        setIconImage(icon.getImage());
        
        setLocationRelativeTo( null );
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        sql.startConnection();
    }
 //<back>
    String cpf;
    w_food.db.mysql sql = new w_food.db.mysql();
    public void _setcpf(String cpf)
    {
    this.cpf=cpf;
    
    jLabel_cpf.setText(cpf);
    }
    public void _setDataAtual()
    {
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;

        jLabel_data.setText(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+month+"/"+(Calendar.getInstance().get(Calendar.YEAR)));
    }
    void _abrirCardapio() throws SQLException
    {     
        jTabbedPane1.setSelectedIndex(4);    
       _addCardapiotabela();   
    }
    void _abrirCaixa()
    {     
        jTabbedPane1.setSelectedIndex(3);        
    }
         
    void _abrirRelatorio()
    {     
        jTabbedPane1.setSelectedIndex(2);        
    }
     void _abrirEstoque()
    {     
        jTabbedPane1.setSelectedIndex(1);        
    }
     
          void _abrirFuncionarios()
    {     
        jTabbedPane1.setSelectedIndex(0);   
    }
         void _abrirInicio()
    {     
        jTabbedPane1.setSelectedIndex(5);        
    }
    void _addFoncionarioTabale() throws SQLException
    {
        DefaultTableModel jtablecaixa = (DefaultTableModel) jTable_funcionario_demos.getModel();
        
        Statement stm;
        stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM `wf_func` WHERE `valido` = 1;");
        
        while (res.next()){
 
            String id = res.getInt("id")+"";
            //String = res.getString("cod_int");
            String nome = res.getString("nome");
            String cpf = res.getString("cpf");
            String cargo = res.getString("cargo");
            String salario = res.getString("salario");
            //String valor = res.getString("valor_custo");
            //String data = res.getString("data_insc");
            //String quantidade = res.getString("quant_estoq");
            String sit = res.getString("sit_emp");
                    
            if(sit.equals("1"))
            {
            sit="Ativo";
            }
            else
            {
            sit="Desativado";
            }
            //buscar numero de vendas do funcionario....
            //pegar dia da semana - 7...
            String Qvendas=sql._contarVEndasF(cpf)+"";
            
            jtablecaixa.addRow(new Object[]{id,nome,cpf,cargo,salario,Qvendas,sit});
        }   
        
    }
    void _addEstoqueTabela() throws SQLException
    {
        DefaultTableModel jtablecaixa = (DefaultTableModel) jTable_estoque_demos.getModel();
        
        Statement stm;
        stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM `wf_estoque` WHERE `valido` = 1;");
        
        while (res.next()){
            
        String id = res.getInt("id")+"";
        //String = res.getString("cod_int");
        String nome = res.getString("nome");
        String quantidade = res.getString("quantidade");
        String fornecedor = res.getString("fornecedor");
        String tempodevalidade = res.getString("data_val");
        String valor = res.getString("valor_custo");
        //String data = res.getString("data_insc");
        //String quantidade = res.getString("quant_estoq");
        
        jtablecaixa.addRow(new Object[]{id,nome,quantidade,sql._buscarFornecedor(fornecedor)[1],tempodevalidade,"R$"+valor});
        }
    }
    void _addCardapiotabela() throws SQLException
    {
        DefaultTableModel jtablecaixa = (DefaultTableModel) jTable_cardapio.getModel();
        
        Statement stm;
        stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM `wf_card` WHERE `valido` = 1;");
        
        while (res.next()){
            
        String id = res.getInt("id")+"";
        String codigo = res.getString("cod_int");
        String nome = res.getString("nome");
        String valor_c = res.getString("valor_custo");
        String valor_v = res.getString("valor_venda");
        String categoria = res.getString("categoria");
        String disponibilide = res.getString("sit_prod");
        String data = res.getString("data_insc");
        //String quantidade = res.getString("quant_estoq");

        if(disponibilide.equals("1"))
        {
            disponibilide= "Disponivel";
        }
        else
        {
            disponibilide = "Indisponivel";
        }
        
        jtablecaixa.addRow(new Object[]{id,codigo,nome,valor_c,valor_v,categoria,disponibilide,data});
        }
        
    }
         
    public void fillTableFunc() throws ClassNotFoundException, SQLException{
        DefaultTableModel tabelaClientes = (DefaultTableModel) jTable4.getModel();
        Statement stm;
        stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM `wf_func` WHERE `valido` = 1;");
        while (res.next()){
        String id = res.getInt("ID")+"";
        String nome = res.getString("nome");
        String cpf = res.getString("cpf");
        String cargo = res.getString("cargo");
        String salario ="R$" + res.getString("salario");
        String ativo = res.getString("sit_emp");

        tabelaClientes.addRow(new Object[]{id, nome, cpf, cargo, salario, ativo});
        }
    }
    
    public void fillClientes() throws ClassNotFoundException, SQLException{
        DefaultTableModel tabelaClientes = (DefaultTableModel) jTable4.getModel();
        tabelaClientes.getDataVector().removeAllElements();
        tabelaClientes.fireTableDataChanged();
        Statement stm;
        stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM wf_cliente WHERE `valido` = 1;");
        while (res.next()){
        String id = res.getInt("id")+"";
        String nome = res.getString("nome");
        String cpf = res.getString("cpf");
        String data = res.getString("data_insc");
            
        tabelaClientes.addRow(new Object[]{id, nome, 1, cpf, data, 0, 0});
        }
    }
    
    public void fillProdutos() throws ClassNotFoundException, SQLException{
        DefaultTableModel tabelaClientes = (DefaultTableModel) jTable4.getModel();
        tabelaClientes.getDataVector().removeAllElements();
        tabelaClientes.fireTableDataChanged();
        tabelaClientes.setRowCount(0);
        Statement stm;
        stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM wf_estoque WHERE `valido` = 1;");
        while (res.next()){
        String id = res.getInt("id")+"";
        String nome = res.getString("nome");
        String quantidade = res.getString("quantidade");
        int codigo = res.getInt("id");
        String data = res.getString("data_val");
        String valordecusto = res.getString("valor_custo");
            
        tabelaClientes.addRow(new Object[]{id, nome, quantidade, codigo, data, valordecusto, 0});
        }
    }
    
    public void fillFuncs() throws ClassNotFoundException, SQLException{
        DefaultTableModel tabelaClientes = (DefaultTableModel) jTable_funcionario_demos.getModel();
        tabelaClientes.getDataVector().removeAllElements();
        tabelaClientes.fireTableDataChanged();
        Statement stm;
        stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM wf_func WHERE `valido` = 1;");
        while (res.next()){
        String id = res.getInt("id")+"";
        String nome = res.getString("nome");
        String cpf = res.getString("cpf");
        String data = res.getString("data_adm");
            
        tabelaClientes.addRow(new Object[]{id, nome, 0, cpf, data, 0, 0});
        }
    }
    
    public void fillVendas() throws ClassNotFoundException, SQLException{
        DefaultTableModel tabelaClientes = (DefaultTableModel) jTable4.getModel();
        tabelaClientes.getDataVector().removeAllElements();
        tabelaClientes.fireTableDataChanged();
        
        Statement stm;
        stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM wf_vendas WHERE `valido` = 1;");
        
        while (res.next()){
            
        String id = res.getInt("id")+"";
        String nome = res.getString("produto");
        String quantidade = res.getString("quantidade");
        String cpf = res.getString("cpf_cliente");
        String data = res.getString("data_venda");
        String valor = res.getString("valor_unit");
        
        double quant = Double.parseDouble(quantidade);
        double valoru = Double.parseDouble(valor);
        double result = quant*valoru;
            
        tabelaClientes.addRow(new Object[]{id,sql._checarProduto(nome)[1], 0, cpf, data, 0, result});
        }
    }
    
    public void sw() throws ClassNotFoundException, SQLException{
        int i = jComboBox1.getSelectedIndex();
   
        if (i == 0){
            fillClientes();
        }
        if (i == 1){
            fillProdutos();
        }
        if (i == 2){
            fillFuncs();
        }
        if (i == 3){
            fillVendas();
        }
    }
       //<back>
    
    public void _abrirCadastroFun() throws ClassNotFoundException, SQLException
    {
        w_food.screens.funcionarios.screen_cadastrar_funcionario Cfun = new  w_food.screens.funcionarios.screen_cadastrar_funcionario();
        
        Cfun.setVisible(true);
    }
    public void _abrirMostraFun()
    {
        //Não TEM ?:
        
    }
    public void _abrirAlterarFun() throws ClassNotFoundException, SQLException
    {
       w_food.screens.funcionarios.screen_alterar_funcionarios Afun = new w_food.screens.funcionarios.screen_alterar_funcionarios();
       
       Afun.setVisible(true);
    }
    public void _abrirCadastrarPro() throws SQLException, ClassNotFoundException
    {
    
        w_food.screens.produtos.screen_cadastrar_produto Cpro = new w_food.screens.produtos.screen_cadastrar_produto();
        
        Cpro.setVisible(true);
    }
    public void _abrirAlterarPro()
    {
        w_food.screens.produtos.screen_alterar_produtos Apro = new w_food.screens.produtos.screen_alterar_produtos();
        
        Apro.setVisible(true);
    }
    public void _abrirGerarRelatorio()
    {
        //Nop Nada aqui ainda
        //this.setVisible(false);
    }
    public void _abrirCadastarCad() throws ClassNotFoundException, SQLException
    {
        w_food.screens.cardapio.screen_adicionar_cardapio Ccad = new w_food.screens.cardapio.screen_adicionar_cardapio();
        
        Ccad.setVisible(true);
    }
    public void _abrirAlterarCad()
    {
        w_food.screens.cardapio.screen_alterar_cardapio Acad = new w_food.screens.cardapio.screen_alterar_cardapio();
        
        Acad.setVisible(true);
    }    
    String _arrumardata(String data)
    {
        String dataN[]= data.split("/");
        
        if(Integer.parseInt(dataN[0]) < 10)
        {
        dataN[0]=dataN[0].replace("0", "");
        }
        
        return dataN[0]+"/"+dataN[1]+"/"+dataN[2];
        
    }
    void _buscarcaixas(String dataI, String dataF) throws SQLException
    {
        if(dataI.equals("") || dataF.equals(""))
        {
            
            dataI="1/01/1111";
            dataF="1/01/3000";
            
        }
        else
        {
            dataI=_arrumardata(dataI);
            dataF=_arrumardata(dataF);
        }
        
        
        DefaultTableModel jtablecaixa = (DefaultTableModel) jTable_caixa_desmos.getModel();
        
        Statement stm;
        stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM `wf_caixa` WHERE `data` BETWEEN '"+dataI+"' AND '"+dataF+"' AND `valido` = 1");
        
        while (res.next()){
            
        String id = res.getInt("id")+"";
        String valor_inicial = res.getString("valor_inicial");
        String valor_final = res.getString("valor_final");
        String data = res.getString("data");
        String resp = res.getString("resp_venda");

        float diferenca = Float.parseFloat(valor_inicial.replace(",","."))-Float.parseFloat(valor_final.replace(",","."));
        
        jtablecaixa.addRow(new Object[]{id,data,valor_inicial,valor_final,diferenca,resp});
        }
        
        jFormattedTextField_caixa_dataF.setText("");
        jFormattedTextField_caixa_dataI.setText("");
    }
    
    //</back>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel_menu = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jPanel_menu_lateral = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel_funcionarios = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_funcionario_demos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel_estoque = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_estoque_demos = new javax.swing.JTable();
        jTextField_pesquisar_estoque = new javax.swing.JTextField();
        jButton_pesquisar_estoque = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel_relatorios = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jFormattedTextField_dataadm_cardapio = new javax.swing.JFormattedTextField();
        jFormattedTextField_dataadm_cardapiofim = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel_caixa = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_caixa_desmos = new javax.swing.JTable();
        jButton_caixa_gerar = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jFormattedTextField_caixa_dataI = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jFormattedTextField_caixa_dataF = new javax.swing.JFormattedTextField();
        jComboBox_caixa_caixa = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jPanel_cardapio = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_cardapio = new javax.swing.JTable();
        jTextField_cardapio = new javax.swing.JTextField();
        jButton_pesquisar_cardapio = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel_inicio = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel_cpf = new javax.swing.JLabel();
        jLabel_data = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel40 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(236, 56, 59));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 670, 960, 10));

        jPanel_menu.setBackground(new java.awt.Color(238, 56, 59));
        jPanel_menu.setPreferredSize(new java.awt.Dimension(960, 49));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setToolTipText("");
        jSeparator5.setAlignmentX(1.0F);
        jSeparator5.setAlignmentY(1.0F);
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_desligar_white.png"))); // NOI18N
        jLabel18.setText("Sair");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_menuLayout = new javax.swing.GroupLayout(jPanel_menu);
        jPanel_menu.setLayout(jPanel_menuLayout);
        jPanel_menuLayout.setHorizontalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addGroup(jPanel_menuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 832, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addContainerGap())))
        );
        jPanel_menuLayout.setVerticalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 960, 50));

        jPanel_menu_lateral.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_menu_lateral.setForeground(new java.awt.Color(254, 210, 64));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(189, 36, 39));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_home_red.png"))); // NOI18N
        jLabel4.setText("Início");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(189, 36, 39));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_caixa_red.png"))); // NOI18N
        jLabel5.setText("Caixa");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(189, 36, 39));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_cardapio_red.png"))); // NOI18N
        jLabel6.setText("Cardápio");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(189, 36, 39));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_funcionarios.png"))); // NOI18N
        jLabel12.setText("Funcionários");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(189, 36, 39));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_historico_red.png"))); // NOI18N
        jLabel13.setText("Relatórios");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(189, 36, 39));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_estoque_red.png"))); // NOI18N
        jLabel14.setText("Estoque");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(238, 56, 59));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(238, 56, 59), 10));

        jSeparator2.setBackground(new java.awt.Color(254, 210, 64));
        jSeparator2.setForeground(new java.awt.Color(254, 210, 64));

        jSeparator3.setBackground(new java.awt.Color(254, 210, 64));
        jSeparator3.setForeground(new java.awt.Color(254, 210, 64));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_engrenagem_red.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_menu_lateralLayout = new javax.swing.GroupLayout(jPanel_menu_lateral);
        jPanel_menu_lateral.setLayout(jPanel_menu_lateralLayout);
        jPanel_menu_lateralLayout.setHorizontalGroup(
            jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_menu_lateralLayout.createSequentialGroup()
                .addGroup(jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_menu_lateralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_menu_lateralLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17))
                    .addGroup(jPanel_menu_lateralLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_menu_lateralLayout.setVerticalGroup(
            jPanel_menu_lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menu_lateralLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addGroup(jPanel_menu_lateralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1))
        );

        getContentPane().add(jPanel_menu_lateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 220, 540));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_funcionarios.setBackground(new java.awt.Color(255, 255, 255));

        jTable_funcionario_demos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "CPF", "Cargo", "Salario", "N. V. Semanais", "Ativo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_funcionario_demos.setGridColor(new java.awt.Color(254, 210, 64));
        jTable_funcionario_demos.setSelectionBackground(new java.awt.Color(254, 210, 64));
        jScrollPane2.setViewportView(jTable_funcionario_demos);

        jPanel2.setBackground(new java.awt.Color(254, 210, 64));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_adicionar_funcionarios.png"))); // NOI18N
        jLabel2.setText("Cadastrar Novo Funcionário");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_alterar.png"))); // NOI18N
        jLabel7.setText("Alterar Informações do Funcionário");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_cancelar.png"))); // NOI18N
        jLabel19.setText("Enviar Aviso");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_funcionariosLayout = new javax.swing.GroupLayout(jPanel_funcionarios);
        jPanel_funcionarios.setLayout(jPanel_funcionariosLayout);
        jPanel_funcionariosLayout.setHorizontalGroup(
            jPanel_funcionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_funcionariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_funcionariosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_funcionariosLayout.setVerticalGroup(
            jPanel_funcionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_funcionariosLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Funcionários", jPanel_funcionarios);

        jPanel_estoque.setBackground(new java.awt.Color(255, 255, 255));

        jTable_estoque_demos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Quantidade", "Fornecedor", "Tempo de Validade", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_estoque_demos.setGridColor(new java.awt.Color(254, 210, 64));
        jTable_estoque_demos.setSelectionBackground(new java.awt.Color(254, 210, 64));
        jScrollPane1.setViewportView(jTable_estoque_demos);

        jButton_pesquisar_estoque.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_pesquisar_estoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_lupa.png"))); // NOI18N
        jButton_pesquisar_estoque.setText("Pesquisar Produto");
        jButton_pesquisar_estoque.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel7.setBackground(new java.awt.Color(254, 210, 64));

        jLabel32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_add.png"))); // NOI18N
        jLabel32.setText("Cadastrar Produto");
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_alterar.png"))); // NOI18N
        jLabel33.setText("Alterar Produto ");
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_lixeira.png"))); // NOI18N
        jLabel34.setText("Remover Produto");
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33)
                .addGap(30, 30, 30)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_estoqueLayout = new javax.swing.GroupLayout(jPanel_estoque);
        jPanel_estoque.setLayout(jPanel_estoqueLayout);
        jPanel_estoqueLayout.setHorizontalGroup(
            jPanel_estoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_estoqueLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_estoqueLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel_estoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_estoqueLayout.createSequentialGroup()
                        .addComponent(jButton_pesquisar_estoque)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_pesquisar_estoque, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        jPanel_estoqueLayout.setVerticalGroup(
            jPanel_estoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_estoqueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_estoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_pesquisar_estoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_pesquisar_estoque, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Estoque", jPanel_estoque);

        jPanel_relatorios.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clientes", "Produtos", "Funcionários", "Estoque", "Vendas", " " }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        try {
            jFormattedTextField_dataadm_cardapio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_dataadm_cardapio.setSelectionColor(new java.awt.Color(254, 210, 64));

        try {
            jFormattedTextField_dataadm_cardapiofim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_dataadm_cardapiofim.setSelectionColor(new java.awt.Color(254, 210, 64));

        jButton1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_lupa.png"))); // NOI18N
        jButton1.setText("Pesquisar Período");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel8.setText("à");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", " ", "", ""
            }
        ));
        jScrollPane5.setViewportView(jTable4);

        jPanel8.setBackground(new java.awt.Color(254, 210, 64));

        jLabel35.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_imprimir.png"))); // NOI18N
        jLabel35.setText("Imprimir");
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_relatoriosLayout = new javax.swing.GroupLayout(jPanel_relatorios);
        jPanel_relatorios.setLayout(jPanel_relatoriosLayout);
        jPanel_relatoriosLayout.setHorizontalGroup(
            jPanel_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_relatoriosLayout.createSequentialGroup()
                .addGroup(jPanel_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_relatoriosLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jFormattedTextField_dataadm_cardapio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextField_dataadm_cardapiofim, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(42, 42, 42)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_relatoriosLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_relatoriosLayout.setVerticalGroup(
            jPanel_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_relatoriosLayout.createSequentialGroup()
                .addGroup(jPanel_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_relatoriosLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField_dataadm_cardapio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFormattedTextField_dataadm_cardapiofim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_relatoriosLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Relatórios", jPanel_relatorios);

        jPanel_caixa.setBackground(new java.awt.Color(255, 255, 255));

        jTable_caixa_desmos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data", "Valor Inicial", "Valor Final", "Diferença", "Funcionario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable_caixa_desmos);

        jButton_caixa_gerar.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_caixa_gerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_lupa.png"))); // NOI18N
        jButton_caixa_gerar.setText("Gerar");
        jButton_caixa_gerar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_caixa_gerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_caixa_gerarActionPerformed(evt);
            }
        });

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Buscar saldo incial do arquivo");

        jTextField2.setText("0,0");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel9.setText("Saldo Inicial");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel10.setText("Data Inicial");

        try {
            jFormattedTextField_caixa_dataI.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel11.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel11.setText("Data Final");

        try {
            jFormattedTextField_caixa_dataF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jComboBox_caixa_caixa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Caixa Principal", "Caixa Funcionário" }));
        jComboBox_caixa_caixa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jComboBox_caixa_caixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_caixa_caixaActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(254, 210, 64));

        jLabel36.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_imprimir.png"))); // NOI18N
        jLabel36.setText("Imprimir");
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(234, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_caixaLayout = new javax.swing.GroupLayout(jPanel_caixa);
        jPanel_caixa.setLayout(jPanel_caixaLayout);
        jPanel_caixaLayout.setHorizontalGroup(
            jPanel_caixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_caixaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel_caixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_caixaLayout.createSequentialGroup()
                        .addGroup(jPanel_caixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_caixa_caixa, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(129, 129, 129)
                        .addGroup(jPanel_caixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_caixaLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField_caixa_dataI, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField_caixa_dataF, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_caixaLayout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_caixa_gerar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_caixaLayout.setVerticalGroup(
            jPanel_caixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_caixaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel_caixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_caixa_caixa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_caixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jFormattedTextField_caixa_dataI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jFormattedTextField_caixa_dataF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_caixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jButton_caixa_gerar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );

        jTabbedPane1.addTab("Caixa", jPanel_caixa);

        jPanel_cardapio.setBackground(new java.awt.Color(255, 255, 255));

        jTable_cardapio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Código Interno", "Nome", "Valor de Custo", "Valor de Venda", "Categoria", "Disponibilidade", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable_cardapio);

        jButton_pesquisar_cardapio.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton_pesquisar_cardapio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_lupa.png"))); // NOI18N
        jButton_pesquisar_cardapio.setText("Pesquisar");
        jButton_pesquisar_cardapio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel10.setBackground(new java.awt.Color(254, 210, 64));

        jLabel37.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_add.png"))); // NOI18N
        jLabel37.setText("Cadastrar Produto");
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_alterar.png"))); // NOI18N
        jLabel38.setText("Alterar Produto ");
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/icon_lixeira.png"))); // NOI18N
        jLabel39.setText("Remover Produto");
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel38)
                .addGap(30, 30, 30)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_cardapioLayout = new javax.swing.GroupLayout(jPanel_cardapio);
        jPanel_cardapio.setLayout(jPanel_cardapioLayout);
        jPanel_cardapioLayout.setHorizontalGroup(
            jPanel_cardapioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_cardapioLayout.createSequentialGroup()
                .addGroup(jPanel_cardapioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_cardapioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_cardapioLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jButton_pesquisar_cardapio, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField_cardapio, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel_cardapioLayout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_cardapioLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_cardapioLayout.setVerticalGroup(
            jPanel_cardapioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_cardapioLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_cardapioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_cardapio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_pesquisar_cardapio, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cardápio", jPanel_cardapio);

        jPanel_inicio.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_inicio.setPreferredSize(new java.awt.Dimension(736, 520));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(238, 56, 59));
        jLabel1.setText("Bem vindo:");

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(238, 56, 59));
        jLabel3.setText("Administrador");

        jLabel_cpf.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel_cpf.setForeground(new java.awt.Color(238, 56, 59));
        jLabel_cpf.setText("CPF");

        jLabel_data.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel_data.setForeground(new java.awt.Color(238, 56, 59));
        jLabel_data.setText("DATA");

        jSeparator4.setBackground(new java.awt.Color(254, 210, 64));
        jSeparator4.setForeground(new java.awt.Color(254, 210, 64));

        jLabel40.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/logo_whatsfood_200.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_inicioLayout = new javax.swing.GroupLayout(jPanel_inicio);
        jPanel_inicio.setLayout(jPanel_inicioLayout);
        jPanel_inicioLayout.setHorizontalGroup(
            jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_inicioLayout.createSequentialGroup()
                .addGroup(jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_inicioLayout.createSequentialGroup()
                        .addGroup(jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel_inicioLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel_inicioLayout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel3)))
                            .addGroup(jPanel_inicioLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_data, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel_cpf, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_inicioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel40)))
                .addContainerGap())
        );
        jPanel_inicioLayout.setVerticalGroup(
            jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_inicioLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_cpf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_data)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addComponent(jLabel40)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Início", jPanel_inicio);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 740, 550));

        jLabel15.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/icons/logo_whatsfood_50.png"))); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/w_food/images/24-fast-food-icons.png"))); // NOI18N
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, -20, 1010, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
     _abrirInicio();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
     _abrirCaixa();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        try {
            _abrirCardapio();
        } catch (SQLException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
     _abrirEstoque();
        try {
            _addEstoqueTabela();
        } catch (SQLException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
     _abrirRelatorio();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
     _abrirFuncionarios();
        try {
            _addFoncionarioTabale();
        } catch (SQLException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            sw();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox_caixa_caixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_caixa_caixaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_caixa_caixaActionPerformed

    private void jButton_caixa_gerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_caixa_gerarActionPerformed
        try {
            _buscarcaixas(jFormattedTextField_caixa_dataI.getText(), jFormattedTextField_caixa_dataF.getText());
        } catch (SQLException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_caixa_gerarActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
            try {
            _abrirCadastroFun();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
             try {
            _abrirAlterarFun();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel32MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
           _abrirAlterarPro();
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel36MouseClicked

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        try {
            _abrirCadastarCad();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
      _abrirAlterarCad();
    }//GEN-LAST:event_jLabel38MouseClicked

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel39MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        screen_login login;
        try {
            login = new screen_login();
            login.setVisible(true);

            this.dispose();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jLabel18MouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new screen_gerencia().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(screen_gerencia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_caixa_gerar;
    private javax.swing.JButton jButton_pesquisar_cardapio;
    private javax.swing.JButton jButton_pesquisar_estoque;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox_caixa_caixa;
    private javax.swing.JFormattedTextField jFormattedTextField_caixa_dataF;
    private javax.swing.JFormattedTextField jFormattedTextField_caixa_dataI;
    private javax.swing.JFormattedTextField jFormattedTextField_dataadm_cardapio;
    private javax.swing.JFormattedTextField jFormattedTextField_dataadm_cardapiofim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_cpf;
    private javax.swing.JLabel jLabel_data;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_caixa;
    private javax.swing.JPanel jPanel_cardapio;
    private javax.swing.JPanel jPanel_estoque;
    private javax.swing.JPanel jPanel_funcionarios;
    private javax.swing.JPanel jPanel_inicio;
    private javax.swing.JPanel jPanel_menu;
    private javax.swing.JPanel jPanel_menu_lateral;
    private javax.swing.JPanel jPanel_relatorios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable_caixa_desmos;
    private javax.swing.JTable jTable_cardapio;
    private javax.swing.JTable jTable_estoque_demos;
    private javax.swing.JTable jTable_funcionario_demos;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField_cardapio;
    private javax.swing.JTextField jTextField_pesquisar_estoque;
    // End of variables declaration//GEN-END:variables
}
