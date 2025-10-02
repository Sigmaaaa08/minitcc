package Controller;

import Model.Veiculo;
import Model.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
        
public class ConVeiculo {
    Conexao conexao = new Conexao();
    
    public void cadastrar (Veiculo veiculo){
            String sql = "INSERT INTO TBVEICULO(idveiculo, placaveiculo, modeloveiculo, tipoveiculo"
                        + "VALUES (?,?,?,?);";
            
                        try {
                PreparedStatement vei = conexao.conectar().prepareStatement(sql);
                
                vei.setString(1, veiculo.getPlacaveiculo());
                vei.setString(2, veiculo.getModeloveiculo());
                vei.setString(3, veiculo.getTipoveiculo());
                vei.setString(4, veiculo.getEndereco());
                
                vei.executeUpdate();
                
                conexao.desconectar();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:"+ ex);
            }
        }
            
}
