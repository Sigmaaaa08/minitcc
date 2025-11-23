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

    public void cadastrar(Servicos servico) {
        if (!servico.isValid()) {
            JOptionPane.showMessageDialog(null, "Os campos obrigatórios (Vaga, Veículo, Funcionário, Data Inicial e Hora de Entrada) devem ser preenchidos.");
            return;
        }
        String sql = "INSERT INTO TBSERVICO(datafinal,datainicial,horaentrada,horasaida, codvaga,codfunci,codveiculo)"
                + "VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, servico.getDatafinal());
            psmt.setString(2, servico.getDatainicial());
            psmt.setString(3, servico.getHoraentrada());
            psmt.setString(4, servico.getHorasaida());
            psmt.setInt(5, servico.getCodvaga());
            psmt.setInt(6, servico.getCodfuncionario());
            psmt.setInt(7, servico.getCodveiculo());
            psmt.executeUpdate();

            conexao.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um ERRO: " + ex);
        }
    }

    public Vector listar() {
        Vector lista = new Vector();
        String sql = "Select idservico,codvaga,codveiculo,codfunci,datainicial,datafinal,horaentrada,horasaida from TBSERVICO";
        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            //percorre os resultados obtidos na consulta sql
            while (rs.next()) {
                Servicos servico = new Servicos();
                servico.setCodigo(rs.getInt("idservico"));
                servico.setCodvaga(rs.getInt("codvaga"));
                servico.setCodveiculo(rs.getInt("codveiculo"));
                servico.setCodfuncionario(rs.getInt("codfunci"));
                servico.setDatainicial(rs.getString("datainicial"));
                servico.setDatafinal(rs.getString("datafinal"));
                servico.setHoraentrada(rs.getString("horaentrada"));
                servico.setHorasaida(rs.getString("horasaida"));
                //Cada Linha será um cliente encontrado
                Vector novalinha = new Vector();
                novalinha.addElement(servico.getCodigo());
                novalinha.addElement(servico.getCodvaga());
                novalinha.addElement(servico.getCodveiculo());
                novalinha.addElement(servico.getCodfuncionario());
                novalinha.addElement(servico.getDatainicial());
                novalinha.addElement(servico.getDatafinal());
                novalinha.addElement(servico.getHoraentrada());
                novalinha.addElement(servico.getHorasaida());

                lista.add(novalinha);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return lista;
    }

    public Servicos pesquisar(int idservico) {
        String sql = "Select * from TBSERVICO where idservico= ? ";
        Servicos servicos = null; // Inicializa como null

        try {
            PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
            pstmt.setInt(1, idservico);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                servicos = new Servicos();
                servicos.setCodigo(rs.getInt("idservico"));
                servicos.setCodfuncionario(rs.getInt("codfunci"));
                servicos.setCodvaga(rs.getInt("codvaga"));
                servicos.setCodveiculo(rs.getInt("codveiculo"));
                servicos.setHoraentrada(rs.getString("horaentrada"));
                servicos.setHorasaida(rs.getString("horasaida"));
                servicos.setDatainicial(rs.getString("datainicial"));
                servicos.setDatafinal(rs.getString("datafinal"));
            }

            conexao.desconectar();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na pesquisa: " + ex.getMessage());
        }
        return servicos;
    }

    public void editar(Servicos servico) {
        String sql = "UPDATE TBSERVICO SET datafinal=?, horasaida=? WHERE idservico=?";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, servico.getDatafinal());
            psmt.setString(2, servico.getHorasaida());
            psmt.setInt(3, servico.getCodigo());
            psmt.executeUpdate();

            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "Serviço atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um ERRO ao atualizar o serviço: " + ex);
        }
    }

    public void excluir(int idservico) {
        String sql = "DELETE FROM TBSERVICO WHERE idservico=?";

        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setInt(1, idservico);
            psmt.executeUpdate();

            conexao.desconectar();
            JOptionPane.showMessageDialog(null, "Serviço excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um ERRO ao excluir o serviço: " + ex);
        }
    }

}