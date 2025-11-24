/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Operacional;
import Model.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Documentos
 */
public class ConOperacional {
    Conexao conexao = new Conexao();
    
    public Operacional precos() {
        String sql = "Select * from TBCATEGORIA where idcat = 1";
        try {
            PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            Operacional operacional = new Operacional();
            //percorre os resultados obtidos na consulta sql
            while (rs.next()) {
                operacional.setCodigo(rs.getInt("idcat"));
                operacional.setPrecoPrimeiraHora(rs.getDouble("PRECO_PRIMEIRA_HORA"));
                operacional.setPrecoHorasAdicionais(rs.getDouble("PRECO_HORAS_ADICIONAIS"));
                operacional.setPrecoDiaria(rs.getDouble("PRECO_DIARIA"));
            }
            return operacional;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
    public void editar(Operacional operacional) {
        if (!operacional.isValid()) {
            JOptionPane.showMessageDialog(null, "Todos os preços devem ser maiores que zero.");
            return;
        }
        String sql = "UPDATE TBCATEGORIA set PRECO_PRIMEIRA_HORA=?,PRECO_HORAS_ADICIONAIS=?,PRECO_DIARIA=?"
                + " where idcat = 1";
        try {
            PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
            psmt.setString(1, String.valueOf(operacional.getPrecoPrimeiraHora()));
            psmt.setString(2, String.valueOf(operacional.getPrecoHorasAdicionais()));
            psmt.setString(3, String.valueOf(operacional.getPrecoDiaria()));
            psmt.executeUpdate();
            conexao.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
        }
    }
        public String getDate() { 
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
	Date date = new Date(); 
	return dateFormat.format(date); 
        }
        
        public String getTime() { 
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); 
	Date date = new Date(); 
	return dateFormat.format(date); 
        }
        
        
    /*public long diferencaEmDias(String data1, String data2) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate d1 = LocalDate.parse(data1, fmt);
        LocalDate d2 = LocalDate.parse(data2, fmt);
        return ChronoUnit.DAYS.between(d1, d2);
    }
    public long diferencaEmHoras(String data1, String data2, String hora1, String hora2){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime d1 = LocalDateTime.parse(data1 + " " + hora1, fmt);
        LocalDateTime d2 = LocalDateTime.parse(data1 + " " + hora1, fmt);

        long horas = Duration.between(d1, d2).toHours();

        System.out.println("Diferença em horas: " + horas);
    }
    }*/
}
