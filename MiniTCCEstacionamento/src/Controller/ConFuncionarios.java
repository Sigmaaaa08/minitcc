package Controller;

import Model.Conexao;
import Model.Funcionarios;
import Model.PasswordHasher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
        
public class ConFuncionarios {
    Conexao conexao = new Conexao();
    
    public void cadastrar (Funcionarios funcionario){
            String sql = "INSERT INTO TBFUNCIONARIO(senhafunci,telefonefunci,emailfunci,nomefunci,cpffunci,statusfunci,datacontfunci)"
                        + "VALUES (?,?,?,?,?,?,?);";
            
                        try {
                PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
                // Aplica o hashing na senha antes de salvar
            //    String hashedPassword = PasswordHasher.hashPassword(funcionario.getSenha());
                psmt.setString(1, funcionario.getSenha());
                psmt.setString(2, funcionario.getTelefone());
                psmt.setString(3, funcionario.getEmail());
                psmt.setString(4, funcionario.getNome());
                psmt.setString(5, funcionario.getCpf());
                psmt.setString(6, funcionario.getStatus());
                psmt.setString(7, funcionario.getDatacont());
                psmt.executeUpdate();
                          
                conexao.desconectar();
                JOptionPane.showMessageDialog(null, "Serviço Cadastrado com sucesso!");
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:"+ ex);
            }
        }
         public Vector listar(){
        Vector lista = new Vector();
        String sql = "Select idfunci, senhafunci, telefonefunci, emailfunci, cpffunci, nomefunci, statusfunci, datacontfunci from TBFUNCIONARIO";
        try{
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            //percorre os resultados obtidos na consulta sql
            while(rs.next()){
                Funcionarios funcionario = new Funcionarios();
                funcionario.setCodigo(rs.getInt("idfunci"));
                funcionario.setNome(rs.getString("nomefunci"));
                funcionario.setCpf(rs.getString("cpffunci"));
                funcionario.setTelefone(rs.getString("telefonefunci"));
                funcionario.setEmail(rs.getString("emailfunci"));
                funcionario.setSenha(rs.getString("senhafunci"));
                funcionario.setStatus(rs.getString("statusfunci"));
                funcionario.setDatacont(rs.getString("datacontfunci"));
                //Cada Linha será um veiculo encontrado
                Vector novalinha = new Vector();
                novalinha.addElement(funcionario.getCodigo());
                novalinha.addElement(funcionario.getNome());
                novalinha.addElement(funcionario.getCpf());
                novalinha.addElement(funcionario.getTelefone());
                novalinha.addElement(funcionario.getEmail());
                novalinha.addElement(funcionario.getSenha());
                novalinha.addElement(funcionario.getStatus());
                novalinha.addElement(funcionario.getDatacont());
                
                lista.add(novalinha);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return lista;
    }
         
    public Vector listar(String status){
        Vector lista = new Vector();
        String sql = "Select idfunci, senhafunci, telefonefunci, emailfunci, cpffunci, nomefunci, statusfunci, datacontfunci from TBFUNCIONARIO" +
                " Where statusfunci=?";
        try{
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, status);
            ResultSet rs = psmt.executeQuery();
            //percorre os resultados obtidos na consulta sql
            while(rs.next()){
                Funcionarios funcionario = new Funcionarios();
                funcionario.setCodigo(rs.getInt("idfunci"));
                funcionario.setNome(rs.getString("nomefunci"));
                funcionario.setCpf(rs.getString("cpffunci"));
                funcionario.setTelefone(rs.getString("telefonefunci"));
                funcionario.setEmail(rs.getString("emailfunci"));
                funcionario.setSenha(rs.getString("senhafunci"));
                funcionario.setStatus(rs.getString("statusfunci"));
                funcionario.setDatacont(rs.getString("datacontfunci"));
                //Cada Linha será um veiculo encontrado
                Vector novalinha = new Vector();
                novalinha.addElement(funcionario.getCodigo());
                novalinha.addElement(funcionario.getNome());
                novalinha.addElement(funcionario.getCpf());
                novalinha.addElement(funcionario.getTelefone());
                novalinha.addElement(funcionario.getEmail());
                novalinha.addElement(funcionario.getSenha());
                novalinha.addElement(funcionario.getStatus());
                novalinha.addElement(funcionario.getDatacont());
                
                lista.add(novalinha);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return lista;
    }
         
	          public Funcionarios logar(String senha, String email) {
	        // Aplica o hashing na senha fornecida para comparação
	        //String hashedPassword = PasswordHasher.hashPassword(senha);
	        String sql = "Select * from TBFUNCIONARIO where senhafunci = ? and emailfunci = ?";
	        try {
	            PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
	            pstmt.setString(1, senha);
                    pstmt.setString(2, email);
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
    
	          public Funcionarios pesquisar(String cpf) {
	        String sql = "Select idfunci, senhafunci, telefonefunci, emailfunci, cpffunci, nomefunci, statusfunci, datacontfunci from TBFUNCIONARIO where cpffunci = ?";
        try {
            PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();

            Funcionarios funcionario = new Funcionarios();
            //percorre os resultados obtidos na consulta sql
            while (rs.next()) {
                funcionario.setCodigo(rs.getInt("idfunci"));
                funcionario.setNome(rs.getString("nomefunci"));
                funcionario.setCpf(rs.getString("cpffunci"));
                funcionario.setTelefone(rs.getString("telefonefunci"));
                funcionario.setEmail(rs.getString("emailfunci"));
                funcionario.setSenha(rs.getString("senhafunci"));
                funcionario.setStatus(rs.getString("STATUSFUNCI"));
                funcionario.setDatacont(rs.getString("datacontfunci"));
            }
            return funcionario;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
          
	          public boolean editar(Funcionarios funcionario) {
        String sql = "UPDATE TBFUNCIONARIO set nomefunci=?,cpffunci=?,telefonefunci=?,senhafunci=?,emailfunci=?, statusfunci=?"
                + " where idfunci = ?";
        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, funcionario.getNome());
            psmt.setString(2, funcionario.getCpf());
            psmt.setString(3, funcionario.getTelefone());
            psmt.setString(4, funcionario.getSenha());
            psmt.setString(5, funcionario.getEmail());
            psmt.setString(6, funcionario.getStatus());
            psmt.setInt(7, funcionario.getCodigo());
            int linhasAfetadas = psmt.executeUpdate();
            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "Serviço alterado com sucesso!");
            return linhasAfetadas > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
            return false;
        }
    }
          
          public void excluir(int codigo) {
        String sql = "DELETE from TBFUNCIONARIO where idfunci = ?";
        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, codigo);
            psmt.executeUpdate();
            conexao.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
        }
    }
}
