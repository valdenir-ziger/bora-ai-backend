import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private final String url 	  = "jdbc:postgresql://localhost:5432/boraai";
    private final String user 	  = "postgres";
    private final String password = "pgsql";
    
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("conectado com sucesso.");
            } else {
                System.out.println("conexão falhou!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
