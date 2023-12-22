// Pacote que contém a classe
package br.com.pazzini.dao.generic.jdbc;

// Importações necessárias
import br.com.pazzini.dao.generic.jdbc.dao.IProdutoDAO;
import br.com.pazzini.dao.generic.jdbc.dao.ProdutoDAO;
import br.com.pazzini.dao.generic.jdbc.domain.Produto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Descrição: Classe de teste para a classe ProdutoDAO, que realiza operações de teste sobre as operações CRUD de Produto.
 * Criada por calle em 20/09/2023.
 */
public class ProdutoTest {

    // Instâncias necessárias para os testes
    private IProdutoDAO produtoDAO;
    private Connection connection;

    // Configuração executada antes de cada teste
    @Before
    public void setUp() throws SQLException {
        connection = ConnectionFactory.getConnection();
        produtoDAO = new ProdutoDAO();
    }

    // Limpeza executada após cada teste
    @After
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Teste para o método cadastrarProduto da classe ProdutoDAO
    @Test
    public void cadastrarProdutoTest() throws Exception {
        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Produto 01");
        produto.setEstadoSigla("GO");
        Integer cadrst1 = produtoDAO.cadastrarProduto(produto);
        assertTrue(cadrst1 == 1);

        Produto produto1 = new Produto();
        produto1.setCodigo("02");
        produto1.setNome("Produto 02");
        produto1.setEstadoSigla("SP");
        Integer cadrst2 = produtoDAO.cadastrarProduto(produto1);
        assertTrue(cadrst2 == 1);

        Produto produto2 = new Produto();
        produto2.setCodigo("03");
        produto2.setNome("Produto 03");
        produto2.setEstadoSigla("MG");
        Integer cadrst3 = produtoDAO.cadastrarProduto(produto2);
        assertTrue(cadrst3 == 1);
    }

    // Teste para o método atualizarProduto da classe ProdutoDAO
    @Test
    public void atualizarProdutoTest() throws Exception {
        Produto produto = new Produto();
        produto.setCodigo("06");
        produto.setNome("Produto 06");
        produto.setEstadoSigla("CE");
        Integer cadrst1 = produtoDAO.cadastrarProduto(produto);
        assertEquals(Integer.valueOf(1), cadrst1);

        Produto produtoCadastrado = produtoDAO.consultarProduto(produto.getCodigo());
        assertNotNull(produtoCadastrado);
        Long idProduto = produtoCadastrado.getId();

        produto.setCodigo("06");
        produto.setNome("Produto 05");
        produto.setEstadoSigla("MT");
        produto.setId(idProduto);

        Integer atualizarProdutoResult = produtoDAO.atalizarProduto(produto);
        assertEquals(Integer.valueOf(1), atualizarProdutoResult);

        Produto produtoAtualizado = produtoDAO.consultarProduto(produto.getCodigo());
        assertNotNull(produtoAtualizado);
        assertEquals(produto.getCodigo(), produtoAtualizado.getCodigo());
        assertEquals(produto.getNome(), produtoAtualizado.getNome());
        assertEquals(produto.getEstadoSigla(), produtoAtualizado.getEstadoSigla());
    }

    // Teste para o método consultarProduto da classe ProdutoDAO
    @Test
    public void consultarProdutoTest() throws Exception {
        Produto produto = new Produto();
        produto.setCodigo("04");
        produto.setNome("Produto 04");
        produto.setEstadoSigla("ES");
        Integer cadrst1 = produtoDAO.cadastrarProduto(produto);
        assertEquals(Integer.valueOf(1), cadrst1);

        Produto produtoConsultado = produtoDAO.consultarProduto(produto.getCodigo());
        assertNotNull(produtoConsultado);
        assertEquals(produto.getCodigo(), produtoConsultado.getCodigo());
        assertEquals(produto.getNome(), produtoConsultado.getNome());
        assertEquals(produto.getEstadoSigla(), produtoConsultado.getEstadoSigla());
    }

    // Teste para o método excluirProduto da classe ProdutoDAO
    @Test
    public void excluirProdutoTest() throws Exception {
        Produto produto = new Produto();
        produto.setCodigo("07");
        produto.setNome("Produto 07");
        produto.setEstadoSigla("RS");
        Integer cadrst1 = produtoDAO.cadastrarProduto(produto);
        assertEquals(Integer.valueOf(1), cadrst1);

        Integer excluirProdutoResult = produtoDAO.excluirProduto(produto);
        assertEquals(Integer.valueOf(1), excluirProdutoResult);

        Produto produtoExcluido = produtoDAO.consultarProduto(produto.getCodigo());
        assertNull(produtoExcluido);
    }

    // Teste para o método buscarTodosProdutos da classe ProdutoDAO
    @Test
    public void buscarTodosProdutosTest() throws Exception {
        Produto produto7 = new Produto();
        produto7.setCodigo("10");
        produto7.setNome("Produto 10");
        produto7.setEstadoSigla("AM");
        produtoDAO.cadastrarProduto(produto7);

        Produto produto9 = new Produto();
        produto9.setCodigo("9");
        produto9.setNome("Produto 9");
        produto9.setEstadoSigla("BA");
        produtoDAO.cadastrarProduto(produto9);

        Produto produto8 = new Produto();
        produto8.setCodigo("8");
        produto8.setNome("Produto 8");
        produto8.setEstadoSigla("PI");
        produtoDAO.cadastrarProduto(produto8);

        List<Produto> produtos = produtoDAO.buscarTodosProdutos();
        assertNotNull(produtos);
        assertEquals(4, produtos.size());

        assertTrue(produtos.stream().anyMatch(c -> c.getCodigo().equals(produto7.getCodigo())));
        assertTrue(produtos.stream().anyMatch(c -> c.getCodigo().equals(produto8.getCodigo())));
        assertTrue(produtos.stream().anyMatch(c -> c.getCodigo().equals(produto9.getCodigo())));
    }
}
