package w_food.db;

import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class mysql {
    
public static Connection con;
    
    // CONEXÕES
    
    public void startConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/wfood", "root", "");
        //abre conexão
    }
    
    public void closeConnection() throws SQLException{
        con.close();
        //fecha conexão
    }
    
    //USUARIO
    
    public void registerUser(String usuario, String senha, int tipo) throws SQLException{
        
        PreparedStatement ps = con.prepareStatement("INSERT INTO `wf_users`(ID, usuario, senha, tipo, valido) VALUES (null, ?, ?, ?, ?);");
        
        ps.setString(1, usuario);
        ps.setString(2, senha);
        ps.setInt(3, tipo);
        ps.setBoolean(4, true);
        
        ps.executeUpdate();
        
        //registrou com sucesso!
    }
        
    public boolean validateUser(String usuario, String senha) throws SQLException{
        Statement stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM wf_users WHERE usuario = '" + usuario + "' AND senha = '" + senha + "';");
        return res.next();
        //valida combinação de login e senha
    }
    
    public int checkPermission(String usuario) throws SQLException{
        Statement stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM wf_users WHERE usuario = '" + usuario + "';");
        res.next();
        if(res.getInt("tipo") == 0) {
            return 0;
            //usuario normal
        } else {
            //usuario admin
            return 1;
        }
    }
    
    public boolean checkUser(String usuario) throws SQLException{
        Statement stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM wf_users WHERE usuario = '" + usuario + "';");
        return res.next();
        //checa se o usuario existe
    }
    
    public void deactiveUser(String usuario) throws SQLException{
        Statement stm = con.createStatement();
        if (checkUser(usuario)){
            if (stm.executeUpdate("UPDATE wf_users SET valido = 'FALSE' WHERE usuario = '" + usuario + "';")!=0){
                // desativado com sucesso!
            }
        }else{
            //usuario nao existe
        }
    }
     
    public void changePW(String usuario, String novasenha) throws SQLException{
        Statement stm = con.createStatement();
        if (checkUser(usuario)){
            if (stm.executeUpdate("UPDATE wf_users SET senha = '"+ novasenha +"' WHERE usuario = '" + usuario + "';")!=0){
                // trocada com sucesso!
            }
        }else{
            //usuario nao existe
        }
    }
    
    //FORNECEDORES

    public void registerForn(String nome, String cnpj, String categoria, String produtos) throws SQLException{
        PreparedStatement ps = con.prepareStatement("INSERT INTO `wf_forn`(ID, nome, cnpj, categoria, produtos, valido) VALUES (null, ?, ?, ?, ?, ?);");
        ps.setString(1, nome);
        ps.setString(2, cnpj);
        ps.setString(3, categoria);
        ps.setString(4, produtos);
        ps.setBoolean(5, true);
        ps.executeUpdate();
        //registrou com sucesso!
    }
        
    public void changeForn(String cnpj, String campo, String dados) throws SQLException{
        PreparedStatement ps = con.prepareStatement("UPDATE wf_forn SET " + campo + " = '"+ dados +"' WHERE cnpj = '" + cnpj + "';");
        ps.executeUpdate();
        
        //campo = campo da tabela que quer mudar 
        //dados = o que vai inserir no campo que quer editar
        //troca qualquer dado da tabela com um sql!
    }
    public boolean checkForn(String cnpj) throws SQLException{
        Statement stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM wf_forn WHERE cnpj = '" + cnpj + "';");
        return res.next();
        //checa se o fornecedor existe
    }
    
    public void deactiveForn(String cnpj) throws SQLException{
        Statement stm = con.createStatement();
        if (checkForn(cnpj)){
            if (stm.executeUpdate("UPDATE wf_forn SET valido = 'FALSE' WHERE cnpj = '" + cnpj + "';")!=0){
                // desativado com sucesso!
            }
        }else{
            //fornecedor nao existe
        }
    }
    
    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
    return new java.sql.Date(date.getTime());
}
    
   // ESTOQUE
        
    public void registerEstoque(String nome, String quantidade, String fornecedor, String data_validade, String valor_custo) throws SQLException, ParseException{
        PreparedStatement ps = con.prepareStatement("INSERT INTO `wf_estoque`(ID, nome, quantidade, fornecedor, data_val, valor_custo, valido, data) VALUES (null, ?, ?, ?, ?, ?, ?, ?);");
        
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setCalendar(cal); 
        
        ps.setString(1, nome);
        ps.setString(2, quantidade);
        ps.setString(3, fornecedor);
        ps.setString(4, _arrumardata(data_validade));
        ps.setString(5, valor_custo);
        ps.setBoolean(6, true);
        ps.setDate(7, convertJavaDateToSqlDate(dateFormat.getCalendar().getTime()));
        ps.executeUpdate();
        //registrou com sucesso!
    }
    
    public void changeEstoque(int codigo, String campo, String dados) throws SQLException{
        PreparedStatement ps = con.prepareStatement("UPDATE wf_estoque SET " + campo + " = '"+ dados +"' WHERE codigo = '" + codigo + "';");
        ps.executeUpdate();
        
        //campo = campo da tabela que quer mudar 
        //dados = o que vai inserir no campo que quer editar
        //troca qualquer dado da tabela com um sql!
    }
    
    public boolean checkEstoque(int codigo) throws SQLException{
        Statement stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM wf_estoque WHERE codigo = '" + codigo + "';");
        return res.next();
        //checa se o produto existe no estoque existe
    }
    
    public void deactiveEstoque(int codigo) throws SQLException{
        Statement stm = con.createStatement();
        if (checkEstoque(codigo)){
            if (stm.executeUpdate("UPDATE wf_estoque SET valido = 'FALSE' WHERE codigo = '" + codigo + "';")!=0){
                // desativado com sucesso!
            }
        }else{
            //produto nao existe em estoque
        }
    }
   

    // VENDAS
    public String _arrumardata(String data)
    {
    String aru="";
    String aru2[] = data.split("/");
    
    aru= aru2[2]+"-"+aru2[1]+"-"+aru2[0];
    
    return aru;
    
    }
    public String _desarrumardata(String data)
    {
    String aru="";
    String aru2[] = data.split("-");
    
    aru= aru2[0]+"/"+aru2[1]+"/"+aru2[2];
    
    return aru;
    }
            
    public void registerVenda(String produto,String quatidade,String valor_unit, String data_venda, String cpf_cliente, String resp_venda) throws SQLException{
        PreparedStatement ps = con.prepareStatement("INSERT INTO `wf_vendas`(ID, produto,quantidade, valor_unit, data_venda, cpf_cliente, resp_venda, valido) VALUES (null, ?, ?, ?, ?, ?, ?, ?);");
       
        ps.setString(1, produto);
        ps.setString(2, quatidade);
        ps.setString(3, valor_unit);
        ps.setString(4, _arrumardata(data_venda));
        ps.setString(5, cpf_cliente);
        ps.setString(6,resp_venda);
        ps.setInt(7, 1);
        
        ps.executeUpdate();
        //registrou com sucesso!
    }
    
    public void changeVendas(int id, String campo, String dados) throws SQLException{
        PreparedStatement ps = con.prepareStatement("UPDATE wf_vendas SET " + campo + " = '"+ dados +"' WHERE ID = '" + id + "';");
        ps.executeUpdate();
        
        //campo = campo da tabela que quer mudar 
        //dados = o que vai inserir no campo que quer editar
        //troca qualquer dado da tabela com um sql!
    }
    
    public boolean checkVendas(int id) throws SQLException{
        Statement stm = con.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM wf_vendas WHERE codigo = '" + id + "';");
        return res.next();
        //checa se o produto existe no estoque existe
    }
    
    public void deactiveVenda(int id) throws SQLException{
        Statement stm = con.createStatement();
        if (checkEstoque(id)){
            if (stm.executeUpdate("UPDATE wf_vendas SET valido = 'FALSE' WHERE ID = '" + id + "';")!=0){
                // desativado com sucesso!
            }
        }else{
            //produto nao existe em estoque
        }
    }
    
    // CLIENTES
    
        public void registerCliente(String nome, String endereco, String cpf, String telefone, String email) throws SQLException{
            Date d = new Date();
            String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
            PreparedStatement ps = con.prepareStatement("INSERT INTO `wf_cliente`(ID, nome, endereco, cpf, telefone, email, data_insc, valido) VALUES (null, ?, ?, ?, ?, ?, ?, ?);");
           
            ps.setString(1, nome);
            ps.setString(2, endereco);
            ps.setString(3, cpf);
            ps.setString(4, telefone);
            ps.setString(5, email);
            ps.setString(6, _arrumardata(dStr));
            ps.setBoolean(7, true);
            
            ps.executeUpdate();
            //registrou com sucesso!
    }
        
        public void changeCliente(String cpf, String campo, String dados) throws SQLException{
            PreparedStatement ps = con.prepareStatement("UPDATE wf_cliente SET " + campo + " = '"+ dados +"' WHERE cpf = '" + cpf + "';");
            ps.executeUpdate();
        
            //campo = campo da tabela que quer mudar 
            //dados = o que vai inserir no campo que quer editar
            //troca qualquer dado da tabela com um sql!
    }
        
        public boolean checkCliente(String cpf) throws SQLException{
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM wf_cliente WHERE cpf = '" + cpf + "';");
            return res.next();
        //checa se o produto <m>"produto"?</m> existe no estoque existe
    }
    
        public void deactiveCliente(String cpf) throws SQLException{
            Statement stm = con.createStatement();
            if (checkCliente(cpf)){
                if (stm.executeUpdate("UPDATE wf_cliente SET valido = 'FALSE' WHERE cpf = '" + cpf + "';")!=0){
                    // desativado com sucesso!
                }
            }else{
                //produto nao existe em estoque
            }
        }
        
    // CARDAPIO
        
        public void registerCardapio(String cod_int, String nome, String v_custo, String v_venda, String ingredientes, String categoria, int sit_prod, String quant_estoq) throws SQLException{
            Date d = new Date();
            String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
            
            PreparedStatement ps = con.prepareStatement("INSERT INTO `wf_card`(ID, cod_int, nome, valor_custo, valor_venda, ingredientes, categoria, sit_prod, data_insc, quant_estoq, valido) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, cod_int);
            ps.setString(2, nome);
            ps.setString(3, v_custo);
            ps.setString(4, v_venda);
            ps.setString(5, ingredientes);
            ps.setString(6, categoria);
            ps.setInt(7, sit_prod);
            ps.setString(8, dStr);
            ps.setString(9, quant_estoq);
            ps.setBoolean(10, true);
            ps.executeUpdate();
            //registrou com sucesso!
    }
        
        public void changeCardapio(String cod_int, String campo, String dados) throws SQLException{
            PreparedStatement ps = con.prepareStatement("UPDATE wf_card SET " + campo + " = '"+ dados +"' WHERE cod_int = '" + cod_int + "';");
            ps.executeUpdate();
        
            //campo = campo da tabela que quer mudar 
            //dados = o que vai inserir no campo que quer editar
            //troca qualquer dado da tabela com um sql!
    }
        
        public boolean checkCardapio(String cod_int) throws SQLException{
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM wf_card WHERE cod_int = '" + cod_int + "';");
            return res.next();
        //checa se o produto existe no estoque existe
    }
    
        public void deactiveCardapio(String cod_int) throws SQLException{
            Statement stm = con.createStatement();
            if (checkCardapio(cod_int)){
                if (stm.executeUpdate("UPDATE wf_card SET valido = 'FALSE' WHERE cod_int = '" + cod_int + "';")!=0){
                    // desativado com sucesso!
                }
            }else{
                //produto nao existe em estoque
            }
        }
        
        // FUNCIONARIOS
        
        public void registerFunc(String nome, String sexo, String cpf, String rg, String ctps, String data_nas, String endereco, String estadocivil, String telefone, String email, String cargo, int permissao, String data_adm, String salario, String vale, String vale_rf, String vale_tr, int estagiario, int sit_emp) throws SQLException{
            Date d = new Date();
            String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
            PreparedStatement ps = con.prepareStatement("INSERT INTO `wf_func`(ID, nome, sexo, cpf, rg, ctps, data_nas, endereco, estadocivil, telefone, email, cargo, permissao, data_adm, salario, vale, vale_rf, vale_tr, estagiario, sit_emp, valido) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, nome);
            ps.setString(2, sexo);
            ps.setString(3, cpf);
            ps.setString(4, rg);
            ps.setString(5, ctps);
            ps.setString(6, _arrumardata(data_nas));
            ps.setString(7, endereco);
            ps.setString(8, estadocivil);
            ps.setString(9, telefone);
            ps.setString(10, email);
            ps.setString(11, cargo);
            ps.setInt(12, permissao);
            ps.setString(13, _arrumardata(data_adm));
            ps.setString(14, salario);
            ps.setString(15, vale);
            ps.setString(16, vale_rf);
            ps.setString(17, vale_tr);
            ps.setInt(18, estagiario);
            ps.setInt(19, sit_emp);
            ps.setBoolean(20, true);
            ps.executeUpdate();
            //registrou com sucesso!
    } 
        
        public void changeFunc(String cpf, String campo, String dados) throws SQLException{
            PreparedStatement ps = con.prepareStatement("UPDATE wf_func SET " + campo + " = '"+ dados +"' WHERE cpf = '" + cpf + "';");
            ps.executeUpdate();
        
            //campo = campo da tabela que quer mudar 
            //dados = o que vai inserir no campo que quer editar
            //troca qualquer dado da tabela com um sql!
    }
        
        public boolean checkFunc(String cpf) throws SQLException{
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM wf_func WHERE cpf = '" + cpf + "';");
            return res.next();
        //checa se o produto existe no estoque existe
    }
    
        public void deactiveFunc(String cpf) throws SQLException{
            Statement stm = con.createStatement();
            if (checkFunc(cpf)){
                if (stm.executeUpdate("UPDATE wf_func SET valido = 'FALSE' WHERE cpf = '" + cpf + "';")!=0){
                    // desativado com sucesso!
                }
            }else{
                //produto nao existe em estoque
            }
        }
        
        public int getIDIngrediente(String ingrediente) throws SQLException{
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM wf_estoque WHERE nome = '" + ingrediente + "';");
            while (res.next()){
            int r = res.getInt("id");
            return r;
            }
            return 0;
        }   
        //Relatorios:
        
        public String _buscarPerido(String _dtInicial, String _dtFinal, String _bancoDados, String _campoDado) throws SQLException
        {
        String _Campos="";
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM X WHERE Y between x and y';");
            
            while (res.next())
            {
            _Campos = _Campos + "";
            
            }
        
        return _Campos;
        }
        
        public java.util.List<_objectEmpty> read(String _table, String _campoId, String _campoNome) throws ClassNotFoundException, SQLException
        {
           startConnection();
            
           List<_objectEmpty> retonos = new ArrayList<>();
           
            try 
            {
                Statement stm = con.createStatement();
                ResultSet res = stm.executeQuery("SELECT * FROM "+_table+";");   
                
                while (res.next()){
                    _objectEmpty coisa = new _objectEmpty();
                                   
                    coisa.setId(res.getInt("id")+"");
                    coisa.setNome(res.getString(_campoNome));
                    
                    retonos.add(coisa);
                }
            } 
            catch (Exception e)
            {
                Logger.getLogger(_objectEmpty.class.getName()).log(Level.SEVERE, null, e);
            }
            finally
            {
               closeConnection();
            }
            
           return retonos;
        }
        
        public List _buscaAvancada(String _table, String _campo, String _palavraChave) throws SQLException, ClassNotFoundException
        {
            startConnection();
            
            List Retorno = new ArrayList();

            try 
            {
                
            } 
            catch (Exception e)
            {
                
            }
            
            closeConnection();
            
            return Retorno;
        }
        public String _iniciarTabela(String _table,int _numeroColunas) throws ClassNotFoundException, SQLException
        {
        startConnection();
        
        String _retorno = ""; 

        String[] _colunas = new String[_numeroColunas];
        try 
        {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SHOW COLUMNS FROM "+_table+";");
            
            int cout=0;
            while(res.next())
            {
                _colunas[cout]=res.getString("Field");
                cout=cout+1;              
            }
            
        } 
        catch (Exception e) 
        {
            Logger.getLogger(_objectEmpty.class.getName()).log(Level.SEVERE, null, e);
            _retorno=e.toString();
        }

        try 
        {          
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM "+_table+";");   

                while (res.next()){
                    
                    for (int i = 0; i < _numeroColunas; i++) {
                        
                        _retorno=_retorno+""+res.getString(_colunas[i])+";";  
                        
                    }
                    _retorno=_retorno+"<A>";
                }
        } 
        catch (Exception e) 
        {
            Logger.getLogger(_objectEmpty.class.getName()).log(Level.SEVERE, null, e);
            _retorno=e.toString();
        }
        
        closeConnection();
        
        return _retorno;
        }
        public String[] _checarNomeCliente(String cpf) throws SQLException{
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM wf_cliente WHERE cpf = '" + cpf + "';");
            
            String dado[] = new String[4];
            
            if(checkCliente(cpf))
            {
            while(res.next())
            {
            dado[0] = res.getString("nome");
            dado[1] = res.getString("endereco");
            dado[2] = res.getString("telefone");
            dado[3] = res.getString("email");     
            }
            return dado;
            }
            
            dado[0] = "Cliente Desativo";
            return dado;
        }
        
        public String[] _checarProduto(String _codPro) throws SQLException
        {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM wf_card WHERE cod_int = '" + _codPro + "';");
            
            String dado[] = new String[10];
            
            if(checkCardapio(_codPro))
            {
                while(res.next())
                {
                    dado[0] = res.getString("cod_int");
                    dado[1] = res.getString("nome");
                    dado[2] = res.getString("valor_custo");
                    dado[3] = res.getString("valor_venda");
                    dado[4] = res.getString("ingredientes");
                    dado[5] = res.getString("categoria");
                    dado[6] = res.getInt("sit_prod")+"";
                    dado[7] = res.getString("data_insc");
                    dado[8] = res.getString("quant_estoq");
                    dado[9] = res.getInt("valido")+""; 
                }
                return dado;
            }
            
            dado[0] = "Cliente Desativo";
            return dado;
        }
        
        public String[] _buscarFun(String cpf) throws SQLException
        {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM wf_func WHERE cpf = '" + cpf + "';");
            
            String dado[] = new String[19];
            
            if(checkFunc(cpf))
            {
                while(res.next())
                {
                    dado[0] = res.getString("nome");
                    dado[1] = res.getString("sexo");
                    dado[2] = res.getString("cpf");
                    dado[3] = res.getString("rg");
                    dado[4] = res.getString("ctps");
                    dado[5] = res.getString("data_nas");
                    dado[6] = res.getString("endereco")+"";
                    dado[7] = res.getString("estadocivil");
                    dado[8] = res.getString("telefone");
                    dado[9] = res.getString("email")+""; 
                    dado[10] = res.getString("cargo")+"";
                    dado[11] = res.getInt("permissao")+"";
                    dado[12] = res.getString("data_adm");
                    dado[13] = res.getString("salario")+"";
                    dado[14] = res.getString("vale");
                    dado[15] = res.getString("vale_rf")+""; 
                    dado[16] = res.getString("vale_tr")+"";
                    dado[17] = res.getString("estagiario");
                    dado[18] = res.getString("sit_emp");
                }
                
                return dado;
            }
            
            dado[0] = "Cliente Desativo";
            return dado;
        }
    public void _addmovimentoCaixa(String resp_venda,String valor,String data,String tipo) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement("INSERT INTO `wf_movicaixa`(`id`, `resp_venda`, `valor`, `data`, `tipo`) VALUES (null, ?, ?, ?, ?);");
       
        ps.setString(1, resp_venda);
        ps.setString(2, valor);
        ps.setString(3, _arrumardata(data));
        ps.setString(4, tipo);
        
        ps.executeUpdate();
    }
    public String[] _checarAbruturaCaixa(String cpf) throws SQLException
    {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM `wf_caixa` WHERE `resp_venda` = '" + cpf + "' AND `valido` = '1';");
            
            String dado[] = new String[7];
            
                while(res.next())
                {
                    if(res.getString("cc").equals("1"))
                    {
 
                    }
                    else
                    {
                    dado[0]=res.getInt("id")+"";
                    dado[1]=res.getString("valor_inicial");
                    dado[2]=res.getString("valor_final");
                    dado[3]=res.getString("data");
                    dado[4]=res.getString("resp_venda");
                    dado[5]=res.getInt("valido")+"";
                    dado[6]=res.getString("cc");
                    
                    return dado;
                    }
                }
            dado[0] = "000";   
            return dado;
    }
    public void _abrircaixa(String cpf,String data,String valor_inicial) throws SQLException
    {
        PreparedStatement ps = con.prepareStatement("INSERT INTO `wf_caixa` (`id`, `valor_inicial`, `valor_final`, `data`, `resp_venda`, `valido`, `cc`) VALUES (NULL, ?, ?, ?, ?, ?, ?);");
       
        ps.setString(1, valor_inicial);
        ps.setString(2,"");
        ps.setString(3,_arrumardata(data));
        ps.setString(4, cpf);
        ps.setString(5,"1");
        ps.setString(6, "0");
        
        ps.executeUpdate();
        
    }
        public void _fecharcaixa(String id,String valor_final) throws SQLException
    {
        Statement stm = con.createStatement();
        if (stm.executeUpdate("UPDATE `wf_caixa` SET `cc` = '1', `valor_final` = '"+valor_final+"' WHERE `wf_caixa`.`id` = "+id+" AND `wf_caixa`.`cc`=0;")!=0){
            // fechado??
            
        }

    }
        public String[] _buscarFornecedor(String cod) throws SQLException
        {
                    Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM `wf_forn` WHERE `id`= '"+cod+"' and 'valido' = '1';");
            
            String dado[] = new String[7];
            
                while(res.next())
                {
                    dado[0]=res.getInt("id")+"";
                    dado[1]=res.getString("nome");
                    dado[2]=res.getString("cnpj");
                    dado[3]=res.getString("categoria");
                    dado[4]=res.getString("produtos");
                    dado[5]=res.getString("produtos");                    
                    
                    return dado;
                }
            dado[0] = "000";   
            return dado;
        
        }
        public String _setDataAtual()
    {
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        
       return (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+month+"/"+(Calendar.getInstance().get(Calendar.YEAR)));
    }
        public String _buscarNumeroVendasFun(String cod_fun) throws SQLException
        {
            //pegar data atual
            int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        
            String dataAtual=(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+month+"/"+(Calendar.getInstance().get(Calendar.YEAR)));
            
            int total=0;
            String dataI=_arrumardata((Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-7)+"/"+month+"/"+(Calendar.getInstance().get(Calendar.YEAR)));
            String dataF= _arrumardata(dataAtual);
            
            //pegar data
            
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM `wf_vendas` WHERE `data_venda` BETWEEN '"+dataI+"' AND '"+dataF+"' AND `valido` = 1 AND 'resp_venda' = '"+cod_fun+"';");
            
            while (res.next()) {                
                total=total+1;
            }
        
            return total+"";
        }
        public int _contarRe() throws SQLException
        {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT COUNT(*) FROM wf_users;");
            
            while(res.next())
            {
                return res.getInt("COUNT(*)");
            }
            
            return 0; 
        }
        public int _contarVEndasF(String cpf) throws SQLException
        {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT COUNT(`resp_venda`) AS _saida FROM wf_vendas WHERE `resp_venda`='"+cpf+"';");
            
            while(res.next())
            {
                return res.getInt("_saida");
            }
             
            return 0;
        }
        
}