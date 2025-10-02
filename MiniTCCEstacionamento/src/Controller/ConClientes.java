/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Clientes;
import Model.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author LABINFO
 */
public class ConClientes {
           
    Conexao conexao = new Conexao();
    
    public void cadastrar(Clientes cliente){
        String sql = "INSERT INTO TBCLIENTE(nomecliente,credencliente,telefonecliente,cpfcliente)" + 
                "VALUES (?,?,?,?)";
        
        try{
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1,cliente.getNome());
            psmt.setString(2,cliente.getCredencial());
            psmt.setString(3,cliente.getTelefone());
            psmt.setString(4,cliente.getCpf());
            psmt.executeUpdate();
            
            conexao.desconectar();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Ocorreu um ERRO: "+ ex);
        }
    }
    
    public Vector listar(){
        Vector lista = new Vector();
        String sql = "Select idcliente,nomecliente,telefonecliente,cpfcliente,credencliente from CLIENTE";
        try{
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            //percorre os resultados obtidos na consulta sql
            while(rs.next()){
                Clientes cliente = new Clientes();
                cliente.setCodigo(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nomecliente"));
                cliente.setTelefone(rs.getString("telefonecliente"));
                cliente.setCpf(rs.getString("cpfcliente"));
                cliente.setCredencial(rs.getString("credencliente"));
                //Cada Linha será um cliente encontrado
                Vector novalinha = new Vector();
                novalinha.addElement(cliente.getCodigo());
                novalinha.addElement(cliente.getNome());
                novalinha.addElement(cliente.getTelefone());
                novalinha.addElement(cliente.getCpf());
                novalinha.addElement(cliente.getCredencial());
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return lista;
    }
}
