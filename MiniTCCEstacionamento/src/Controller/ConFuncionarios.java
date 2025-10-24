package Controller;

import Model.Conexao;
import Model.Funcionarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JOptionPane;
        
public class ConFuncionarios {
    Conexao conexao = new Conexao();
    
    public void cadastrar (Funcionarios funcionario){
            String sql = "INSERT INTO TBFUNCIONARIO(senhafunci,telefonefunci,emailfunci,nomefunci,cpffunci)"
                        + "VALUES (?,?,?,?,?,?);";
            
                        try {
                PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
                psmt.setString(1, funcionario.getSenha());
                psmt.setString(2, funcionario.getTelefone());
                psmt.setString(3, funcionario.getEmail());
                psmt.setString(4, funcionario.getNome());
                psmt.setString(5, funcionario.getCpf());
                psmt.executeUpdate();
                          
                conexao.desconectar();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:"+ ex);
            }
        }
         public Vector listar(){
        Vector lista = new Vector();
        String sql = "Select idfunci, datacont, senhafunci, telefonefunci,cpffunci,nomefunci from TBFUNCIONARIO";
        try{
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            //percorre os resultados obtidos na consulta sql
            while(rs.next()){
                Funcionarios funcionario = new Funcionarios();
                funcionario.setCodigo(rs.getInt("idfunci"));
                funcionario.setDatacont(rs.getString("datacont"));
                funcionario.setSenha(rs.getString("senhafunci"));
                funcionario.setTelefone(rs.getString("telefonefunci"));
                funcionario.setCpf(rs.getString("cpffunci"));
                funcionario.setNome(rs.getString("nomefunci"));
                //Cada Linha será um veiculo encontrado
                Vector novalinha = new Vector();
                novalinha.addElement(funcionario.getCodigo());
                novalinha.addElement(funcionario.getNome());
                novalinha.addElement(funcionario.getTelefone());
                novalinha.addElement(funcionario.getDatacont());
                novalinha.addElement(funcionario.getEmail());
                novalinha.addElement(funcionario.getSenha());
                
                lista.add(novalinha);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return lista;
    }    
         
          public Funcionarios logar(String senha) {
        String sql = "Select * from TBFUNCIONARIO where senhafunci = ?";
        try {
            PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
            pstmt.setString(1, senha);
            ResultSet rs = pstmt.executeQuery();

            //percorre os resultados obtidos na consulta sql
            if (rs.next()) {
            Funcionarios funcionario = new Funcionarios();
                funcionario.setCodigo(rs.getInt("idfunci"));
                funcionario.setNome(rs.getString("nomefunci"));
                funcionario.setTelefone(rs.getString("telefonefunci"));
                funcionario.setCpf(rs.getString("cpffunci"));
                funcionario.setEmail(rs.getString("emailfunci"));
                funcionario.setStatus(rs.getString("statusfunci")); 
            return funcionario;
            }else return null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
}
