// Pacote que contém a classe
package br.com.pazzini.dao.generic.jdbc;

// Importações necessárias
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Descrição: Classe responsável por fornecer conexões com o banco de dados.
 */
public class ConnectionFactory {

    // Atributo estático para armazenar a única instância da conexão
    private static Connection connection;

    // Construtor privado para impedir a criação de instâncias da fábrica de conexões
    private ConnectionFactory(Connection connection) {

    }

    // Método estático para obter uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        // Verifica se a conexão ainda não foi inicializada ou está fechada
        if (connection == null || connection.isClosed()) {
            // Inicializa uma nova conexão
            connection = initConnection();
        }
        // Retorna a conexão existente ou recém-inicializada
        return connection;
    }

    // Método privado para inicializar uma nova conexão com o banco de dados
    private static Connection initConnection() {
        try {
            // Retorna uma nova conexão usando o driver JDBC do PostgreSQL
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:15432/cliente1", "postgres", "admin");
        } catch (SQLException e) {
            // Lança uma exceção de tempo de execução em caso de falha na conexão
            throw new RuntimeException(e);
        }
    }
}

