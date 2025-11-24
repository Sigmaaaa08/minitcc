package Controller;

import Model.Veiculos;
import Model.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
        
public class ConVeiculos {
    Conexao conexao = new Conexao();
    
    public void cadastrar (Veiculos veiculo){
        if (!veiculo.isValid()) {
            JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios devem ser preenchidos.");
            return;
        }
            String sql = "INSERT INTO TBVEICULO(placaveiculo, modeloveiculo, tipoveiculo, codcliente)"
                        + "VALUES (?,?,?,?);";
            
                        try {
                PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
                psmt.setString(1, veiculo.getPlaca());
                psmt.setString(2, veiculo.getModelo());
                psmt.setString(3, veiculo.getTipo());
                psmt.setInt(4, veiculo.getCodcliente());
                psmt.executeUpdate();
                          
                conexao.desconectar();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:"+ ex);
            }
        }
    public Vector listar(){
        Vector lista = new Vector();
        String sql = "Select idveiculo, codcliente, placaveiculo, modeloveiculo,tipoveiculo from TBVEICULO";
        try{
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            //percorre os resultados obtidos na consulta sql
            while(rs.next()){
                Veiculos veiculo = new Veiculos();
                veiculo.setCodigo(rs.getInt("idveiculo"));
                veiculo.setCodcliente(rs.getInt("codcliente"));
                veiculo.setPlaca(rs.getString("placaveiculo"));
                veiculo.setModelo(rs.getString("modeloveiculo"));
                veiculo.setTipo(rs.getString("tipoveiculo"));
                //Cada Linha será um veiculo encontrado
                Vector novalinha = new Vector();
                novalinha.addElement(veiculo.getCodigo());
                novalinha.addElement(veiculo.getCodcliente());
                novalinha.addElement(veiculo.getPlaca());
                novalinha.addElement(veiculo.getModelo());
                novalinha.addElement(veiculo.getTipo());
                
                lista.add(novalinha);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return lista;
    }
    
    public Vector listarVeiculoCliente(){
        Vector lista = new Vector();
        String sql = "Select c.idcliente, v.idveiculo, v.codcliente, c.nomecliente, c.cpfcliente, c.telefonecliente, v.placaveiculo, v.modeloveiculo, v.tipoveiculo"
                + " From tbcliente c Inner Join tbveiculo v "
                + "on v.codcliente=c.idcliente";
        try{
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            //percorre os resultados obtidos na consulta sql
            while(rs.next()){
                Veiculos veiculo = new Veiculos();
                Model.Clientes cliente = new Model.Clientes();
                
                veiculo.setCodigo(rs.getInt("idveiculo"));
                veiculo.setCodcliente(rs.getInt("codcliente"));
                veiculo.setPlaca(rs.getString("placaveiculo"));
                veiculo.setModelo(rs.getString("modeloveiculo"));
                veiculo.setTipo(rs.getString("tipoveiculo"));
                
                cliente.setCodigo(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nomecliente"));
                cliente.setTelefone(rs.getString("telefonecliente"));
                cliente.setCpf(rs.getString("cpfcliente"));
                
                //Cada Linha será um veiculo encontrado
                Vector novalinha = new Vector();
                novalinha.addElement(cliente.getNome());
                novalinha.addElement(cliente.getCpf());
                novalinha.addElement(cliente.getTelefone());
                novalinha.addElement(veiculo.getPlaca());
                novalinha.addElement(veiculo.getModelo());
                novalinha.addElement(veiculo.getTipo());
                
                
                lista.add(novalinha);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return lista;
    }
    
    public Veiculos pesquisar(String placa){
        String sql = "Select * from TBVEICULO where PLACAVEICULO = ?";
         
        try{
           PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
           pstmt.setString(1, placa);
           ResultSet rs = pstmt.executeQuery();
            
           Veiculos veiculo = new Veiculos();
            
           if(rs.next()){
               veiculo.setCodigo(rs.getInt("idveiculo"));
               veiculo.setCodcliente(rs.getInt("codcliente"));
               veiculo.setPlaca(rs.getString("placaveiculo"));
               veiculo.setModelo(rs.getString("modeloveiculo"));
               veiculo.setTipo(rs.getString("tipoveiculo"));
           return veiculo;
            }else{
               return null;
           }
        }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex);
           return null;
        }
    }
    

    
    public Vector listarSourcePlaca(){
        Vector lista = new Vector();
        String sql = "Select placaveiculo, modeloveiculo from TBVEICULO";
        try{
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            //percorre os resultados obtidos na consulta sql
            while(rs.next()){
                Veiculos veiculo = new Veiculos();
                veiculo.setPlaca(rs.getString("placaveiculo"));
                veiculo.setModelo(rs.getString("modeloveiculo"));
                //Cada Linha será um veiculo encontrado
                Vector novalinha = new Vector();
                novalinha.addElement(veiculo.getPlaca());
                novalinha.addElement(veiculo.getModelo());
                
                lista.add(novalinha);
            }
            psmt.close();
            rs.close();
            conexao.desconectar();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return lista;
    }
    
    public Vector listarSourcePlaca(String Placa){
        Vector lista = new Vector();
        String sql = "Select placaveiculo, modeloveiculo from TBVEICULO" +
                " Where placaveiculo like ?";
        try{
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, "%" + Placa + "%");
            ResultSet rs = psmt.executeQuery();
            //percorre os resultados obtidos na consulta sql
            while(rs.next()){
                Veiculos veiculo = new Veiculos();
                veiculo.setPlaca(rs.getString("placaveiculo"));
                veiculo.setModelo(rs.getString("modeloveiculo"));
                //Cada Linha será um veiculo encontrado
                Vector novalinha = new Vector();
                novalinha.addElement(veiculo.getPlaca());
                novalinha.addElement(veiculo.getModelo());
                
                lista.add(novalinha);
            }
            psmt.close();
            rs.close();
            conexao.desconectar();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return lista;
    }
    
    public Veiculos pesquisarVeiculoServico (int codVeiculo){
        String sql = "Select * from TBVEICULO where idveiculo = ? order by idveiculo desc";
         
        try{
           PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
           pstmt.setInt(1, codVeiculo);
           ResultSet rs = pstmt.executeQuery();
            
           Veiculos veiculo = new Veiculos();
            
           if (rs.next()){
               veiculo.setCodigo(rs.getInt("idveiculo"));
               veiculo.setCodcliente(rs.getInt("codcliente"));
               veiculo.setPlaca(rs.getString("placaveiculo"));
               veiculo.setModelo(rs.getString("modeloveiculo"));
               veiculo.setTipo(rs.getString("tipoveiculo"));
           return veiculo;
            }else{
           return null;
           }
        }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex);
           return null;
        }
    }
    
   public boolean editar(Veiculos veiculo){
        String sql = "UPDATE TBVEICULO set codcliente=?, placaveiculo=?, modeloveiculo=?, tipoveiculo=?"
                + " where idveiculo=?";
        
        try{
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            
            psmt.setInt(1, veiculo.getCodcliente());
            psmt.setString(2, veiculo.getPlaca());
            psmt.setString(3, veiculo.getModelo());
            psmt.setString(4, veiculo.getTipo());
            psmt.setInt(5, veiculo.getCodigo());
            int linhasAfetadas = psmt.executeUpdate();
            conexao.desconectar();
            return linhasAfetadas > 0;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Ocorreu um ERRO:"+ex);
            return false;
        }
    }
   
    public void excluir(int codigo){
        String sql = "DELETE from TBVEICULO where idveiculo = ?";
        try{
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, codigo);
            psmt.executeUpdate();
            conexao.desconectar();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Ocorreu um ERRO:"+ex);
        }
    }
}
