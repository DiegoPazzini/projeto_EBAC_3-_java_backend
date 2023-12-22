// Pacote que contém a interface
package br.com.pazzini.dao.generic.jdbc.dao;

// Importação necessária
import br.com.pazzini.dao.generic.jdbc.domain.Produto;

import java.util.List;

/**
 * Descrição: Interface que define operações de acesso a dados (DAO) para a entidade Produto.
 */
public interface IProdutoDAO {

    // Método para cadastrar um produto no banco de dados
    Integer cadastrarProduto(Produto produto) throws Exception;

    // Método para atualizar os dados de um produto no banco de dados
    Integer atalizarProduto(Produto produto) throws Exception;

    // Método para consultar um produto no banco de dados por código
    Produto consultarProduto(String codigo) throws Exception;

    // Método para excluir um produto do banco de dados
    Integer excluirProduto(Produto produto) throws Exception;

    // Método para buscar todos os produtos no banco de dados
    List<Produto> buscarTodosProdutos() throws Exception;
}
