package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    Connection conn = null;

    public Connection conectar() {
        String url = "jdbc:mysql://localhost/BDESTACIONAMENTO";
        String usuario = "root";
        String senha = "";

        try {
            //Iniciando um novo caminho entre a aplicação e o banco
            Class.forName("com.mysql.cj.jdbc.Driver");
            //passando as informações do banco
            //Instanciando o objeto do tipo Connection
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão criada com sucesso!!");
            return conn;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e);
            return null;
        }
    }

    public void desconectar() {
        try {
            conn.close();
            System.out.println("Banco Sem Conexão");
        } catch (SQLException ex) {
            System.out.println("Erro ao desconectar: " + ex);
        }
    }
}
