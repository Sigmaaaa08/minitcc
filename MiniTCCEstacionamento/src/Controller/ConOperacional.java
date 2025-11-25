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
        
        public String formatarDataParaBrasileira(String dataAmericana){
            // 2. Definir o formatador para o formato de entrada
        DateTimeFormatter formatoAmericano = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 3. Converter a String americana para um objeto LocalDate
        LocalDate data = LocalDate.parse(dataAmericana, formatoAmericano);

        // 4. Definir o formatador para o formato de saída (brasileiro)
        DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // 5. Formatar o objeto LocalDate para a String no formato brasileiro
        String dataBrasileira = data.format(formatoBrasileiro);
        return dataBrasileira;
        }
        
        public String formatarDataParaAmericana(String dataBrasileira){
            // 2. Definir o formatador para o formato de entrada
        DateTimeFormatter formatoAmericano = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // 3. Converter a String americana para um objeto LocalDate
        LocalDate data = LocalDate.parse(dataBrasileira, formatoAmericano);

        // 4. Definir o formatador para o formato de saída (brasileiro)
        DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 5. Formatar o objeto LocalDate para a String no formato brasileiro
        String dataAmericana = data.format(formatoBrasileiro);
        return dataAmericana;
        }
        
        
    public long diferencaEmDias(String data1, String data2) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate d1 = LocalDate.parse(data1, fmt);
        LocalDate d2 = LocalDate.parse(data2, fmt);
        
        return ChronoUnit.DAYS.between(d1, d2);
    }
    public long diferencaEmHoras(String data1, String data2, String hora1, String hora2){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        LocalDateTime d1 = LocalDateTime.parse(data1 + " " + hora1, fmt);
        LocalDateTime d2 = LocalDateTime.parse(data2 + " " + hora2, fmt);

        long horas = Duration.between(d1, d2).toHours();

        return horas;
    }
    
    public Double ValorTotal(String data1, String data2, String hora1, String hora2){
    try{
        // 1. Otimização: Chama precos() apenas UMA vez
        Operacional operacional = precos();
        Double diaria = operacional.getPrecoDiaria();
        Double primeiraHora = operacional.getPrecoPrimeiraHora();
        Double horasAdicionais = operacional.getPrecoHorasAdicionais();
        
        // 2. Assumindo que diferencaEmHoras e diferencaEmDias estão corrigidas.
        long horasTotais = diferencaEmHoras(data1, data2, hora1, hora2);
        long diasCompletos = diferencaEmDias(data1, data2); // Reutiliza a função de dias
        
        // 3. Lógica: Verifica a condição MAIOR primeiro (Diária)
        if(horasTotais > 24){
            
            // Calcula as horas restantes após subtrair os dias completos
            // Exemplo: 26h -> 1 dia completo e 2h restantes
            long horasRestantes = horasTotais % 24;
            
            // Aplica a tarifa diária para os dias completos
            Double valorDiarias = diasCompletos * diaria;
            
            // Aplica a tarifa horária para as horas restantes
            Double valorRestante;
            if (horasRestantes <= 1) {
                // Se sobrou 1h ou menos, usa o preço da primeira hora
                valorRestante = primeiraHora;
            } else {
                // Se sobrou mais de 1h, usa a primeira hora + adicionais
                valorRestante = primeiraHora + (horasRestantes - 1) * horasAdicionais;
            }
            
            return valorDiarias + valorRestante;
            
        } 
        // 4. Se o tempo for maior que 1 hora, mas menor ou igual a 24 horas
        else if(horasTotais > 1){
            // Cobra a primeira hora + as horas adicionais (horasTotais - 1)
            return primeiraHora + (horasTotais - 1) * horasAdicionais;
        }
        // 5. Se o tempo for 1 hora ou menos
        else {
            return primeiraHora;
        }
        
    }catch(Exception e){
        // 6. Correção: Tratamento de Exceção (NUNCA DEIXE VAZIO!)
        System.err.println("Erro ao calcular ValorTotal: " + e.getMessage());
        return 0.0; // Retorna 0.0 ou outro valor de erro, em vez de deixar a função retornar 'null'
    }
}
}
