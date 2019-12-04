/*
 * Conexão com BD PostgreSQL
 */
package control;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseSQL
{

    private Connection connection;
    private static DataBaseSQL db = null;

    public Connection getConnection()
    {
        // conecta ao BD, se necessário
        if(connection == null)
            conecta();
        
        return connection;
    }

    private DataBaseSQL()
    {
        connection = null;
    }
    
    public static DataBaseSQL getDBS() {
        if (db == null) {
            db = new DataBaseSQL();
        }
        return db;
    }
    
    public boolean conecta()
    {
        connection = null;

        // verifica se drive JDBC está acessível
        try
        {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC PostgreSQL não foi encontrado.");
            System.out.println(e.getMessage());
            return false;
        }

        // string de conexão com banco de dados “livraria”
        String conexao = "jdbc:postgresql://localhost:5432/projeto_integrador";
        
        try
        {
            // conecta com BD usando usuário “postgres” e senha “root”
            connection = DriverManager.getConnection(conexao, "postgres", "pgs91001200");
        } catch (SQLException e)
        {
            System.out.println("Não foi possível conectar com Banco de Dados!");
            System.out.println(e.getMessage());
            connection = null;
            return false;
        }

        return true;
    }
    
    public void encerra()
    {
        if (connection != null)
        {
            try
            {
            connection.close();
            connection = null;
            } catch (SQLException e)
            {
                System.out.println("Erro no encerramento da conexão com BD.");
                System.out.println(e.getMessage());
            }
        }
    }
}
