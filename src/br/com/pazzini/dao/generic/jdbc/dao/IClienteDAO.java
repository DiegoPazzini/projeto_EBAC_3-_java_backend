// Pacote que contém a interface
package br.com.calleb.dao.generic.jdbc.dao;

// Importação da classe Cliente
import br.com.calleb.dao.generic.jdbc.domain.Cliente;

// Importação da interface List
import java.util.List;

/**
 * Descrição: Interface que define operações de acesso a dados para a entidade Cliente.
 * Criada por calle em 19/09/2023.
 */
public interface IClienteDAO {

    // Método para cadastrar um novo cliente no banco de dados
    public Integer cadastrar(Cliente cliente) throws Exception;

    // Método para atualizar os dados de um cliente no banco de dados
    Integer atualizar(Cliente cliente) throws Exception;

    // Método para consultar um cliente pelo código
    Cliente consultar(String codigo) throws Exception;

    // Método para buscar todos os clientes no banco de dados
    List<Cliente> buscarTodos() throws Exception;

    // Método para excluir um cliente do banco de dados
    Integer excluir(Cliente cliente) throws Exception;
}