/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Conexao;
import Model.Servicos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author LABINFO
 */
public class ConServicos {

    Conexao conexao = new Conexao();

   /* public void cadastrar(Servicos servico) {
        String sql = "INSERT INTO TBSERVICO(datafinal,datainicial,horaentrada,horasaida,codfunci_entrada,codfunci_saida,codveiculo, statusservico, valortotal, codcat)"
                + "VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, servico.getDatafinal());
            psmt.setString(2, servico.getDatainicial());
            psmt.setString(3, servico.getHoraentrada());
            psmt.setString(4, servico.getHorasaida());
            psmt.setInt(5, servico.getCodfuncionario_entrada());
            psmt.setInt(6, servico.getCodfuncionario_saida());
            psmt.setInt(7, servico.getCodveiculo());
            psmt.setString(8, servico.getStatus());
            psmt.setDouble(9, servico.getValorTotal());
            psmt.setInt(10, servico.getCodCat());
            psmt.executeUpdate();

            conexao.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um ERRO: " + ex);
        }
    }*/

    public Vector listar() {
        Vector lista = new Vector();
        String sql = "Select s.idservico,v.PLACAVEICULO,c.nomecliente,s.datainicial,s.datafinal,s.horaentrada,s.horasaida,s.VALORTOTAL,s.STATUSSERVICO"
                + " from TBSERVICO s Inner Join tbveiculo v "
                + "on v.idveiculo=s.codveiculo Inner Join tbcliente c"
                + " on c.idcliente=v.codcliente";
        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            //percorre os resultados obtidos na consulta sql
            while (rs.next()) {
                Servicos servico = new Servicos();
                Model.Veiculos veiculo = new Model.Veiculos();
                Model.Clientes cliente = new Model.Clientes();
                servico.setCodigo(rs.getInt("idservico"));
                veiculo.setPlaca(rs.getString("PLACAVEICULO"));
                cliente.setNome(rs.getString("nomecliente"));
                servico.setDatainicial(rs.getString("datainicial"));
                servico.setDatafinal(rs.getString("datafinal"));
                servico.setHoraentrada(rs.getString("horaentrada"));
                servico.setHorasaida(rs.getString("horasaida"));
                servico.setStatus(rs.getString("STATUSSERVICO"));
                servico.setValorTotal(rs.getDouble("valortotal"));
                //Cada Linha será um cliente encontrado
                Vector novalinha = new Vector();
                novalinha.addElement(servico.getCodigo());
                novalinha.addElement(veiculo.getPlaca());
                novalinha.addElement(cliente.getNome());
                novalinha.addElement(servico.getDatainicial());
                novalinha.addElement(servico.getDatafinal());
                novalinha.addElement(servico.getHoraentrada());
                novalinha.addElement(servico.getHorasaida());
                novalinha.addElement(servico.getStatus());
                novalinha.addElement(servico.getValorTotal());

                lista.add(novalinha);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return lista;
    }
    
    public Vector listar(String Status) {
        Vector lista = new Vector();
        String sql = "Select s.idservico,v.PLACAVEICULO,c.nomecliente,s.datainicial,s.datafinal,s.horaentrada,s.horasaida,s.VALORTOTAL,s.STATUSSERVICO"
                + " from TBSERVICO s Inner Join tbveiculo v "
                + "on v.idveiculo=s.codveiculo Inner Join tbcliente c"
                + " on c.idcliente=v.codcliente"
                + " Where statusservico = ?";
        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, Status);
            ResultSet rs = psmt.executeQuery();
            //percorre os resultados obtidos na consulta sql
            while (rs.next()) {
                Servicos servico = new Servicos();
                Model.Veiculos veiculo = new Model.Veiculos();
                Model.Clientes cliente = new Model.Clientes();
                servico.setCodigo(rs.getInt("idservico"));
                veiculo.setPlaca(rs.getString("PLACAVEICULO"));
                cliente.setNome(rs.getString("nomecliente"));
                servico.setDatainicial(rs.getString("datainicial"));
                servico.setDatafinal(rs.getString("datafinal"));
                servico.setHoraentrada(rs.getString("horaentrada"));
                servico.setHorasaida(rs.getString("horasaida"));
                servico.setStatus(rs.getString("STATUSSERVICO"));
                servico.setValorTotal(rs.getDouble("valortotal"));
                //Cada Linha será um cliente encontrado
                Vector novalinha = new Vector();
                novalinha.addElement(servico.getCodigo());
                novalinha.addElement(veiculo.getPlaca());
                novalinha.addElement(cliente.getNome());
                novalinha.addElement(servico.getDatainicial());
                novalinha.addElement(servico.getDatafinal());
                novalinha.addElement(servico.getHoraentrada());
                novalinha.addElement(servico.getHorasaida());
                novalinha.addElement(servico.getStatus());
                novalinha.addElement(servico.getValorTotal());

                lista.add(novalinha);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return lista;
    }

    public Servicos pesquisar(int idservico) {
        String sql = "Select * from TBSERVICO where idservico= ? ";

        try {
            PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
            pstmt.setInt(1, idservico);
            ResultSet rs = pstmt.executeQuery();
            Servicos servico = new Servicos();
            if (rs.next()) {
                servico.setCodigo(rs.getInt("idservico"));
                servico.setCodfuncionario_entrada(rs.getInt("codfunci_entrada"));
                servico.setCodfuncionario_saida(rs.getInt("codfunci_saida"));
                servico.setCodveiculo(rs.getInt("codveiculo"));
                servico.setHoraentrada(rs.getString("horaentrada"));
                servico.setHorasaida(rs.getString("horasaida"));
                servico.setDatainicial(rs.getString("datainicial"));
                servico.setDatafinal(rs.getString("datafinal"));
                servico.setCodveiculo(rs.getInt("codveiculo"));
                servico.setStatus(rs.getString("StatusServico"));
                servico.setValorTotal(rs.getDouble("valortotal"));
            return servico;
            }else{
             return null;   
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
    
    public void editar(Servicos servico){
        String sql = "UPDATE TBSERVICO set DATAFINAL=?, DATAINICIAL=?, HORAENTRADA=?, HORASAIDA=?, CODVEICULO=?"
                + " where IDSERVICO=?";
        
        try{
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            
            psmt.setString(1, servico.getDatafinal());
            psmt.setString(2, servico.getDatainicial());
            psmt.setString(3, servico.getHoraentrada());
            psmt.setString(4, servico.getHorasaida());
            psmt.setInt(5, servico.getCodveiculo());
            psmt.setInt(5, servico.getCodigo());
            psmt.executeUpdate();
            conexao.desconectar();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Ocorreu um ERRO:"+ex);
        }
    }

}
