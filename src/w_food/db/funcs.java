package w_food.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class funcs {
    mysql sql = new mysql();
    public String errorlogin = "";
    
    public boolean login(String user, String senha) throws SQLException
    {
        if (sql.checkUser(user))
        {
            if (sql.validateUser(user, senha))
            {          
                    return true;
            }
                errorlogin = "Senha incorreta!";
                return false;
        }
        errorlogin = "Usuário não existe!";
        return false;
    }
    
    public void novoFuncionario(){
        
    }  
    
    public String gerarLista(){
        return null;
    }

    public String _buscarPerido(String _dtInicial, String _dtFinal, String campo)
    {
        String _dados="";
    
        
        
        
        
        return _dados;
    }
    
    public List _abrirTabela(String _table,int _numeroColunas) throws ClassNotFoundException, SQLException
    {
        List _retorno = new ArrayList();
        
        String exit = sql._iniciarTabela(_table,_numeroColunas);
        JOptionPane.showMessageDialog(null, exit, "ERRO", JOptionPane.ERROR_MESSAGE);
        
        return _retorno;
    }
}