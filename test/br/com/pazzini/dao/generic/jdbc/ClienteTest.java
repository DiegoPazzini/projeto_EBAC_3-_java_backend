// Pacote que contém a classe
package br.com.pazzini.dao.generic.jdbc;

// Importações necessárias
import br.com.pazzini.dao.generic.jdbc.dao.ClienteDAO;
import br.com.pazzini.dao.generic.jdbc.dao.IClienteDAO;
import br.com.pazzini.dao.generic.jdbc.domain.Cliente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Descrição: Classe de teste para a classe ClienteDAO, que realiza operações de teste sobre as operações CRUD de Cliente.
 */
public class ClienteTest {
    
    // Instâncias necessárias para os testes
    private IClienteDAO clienteDAO;
    private Connection connection;

    // Configuração executada antes de cada teste
    @Before
    public void setUp() throws SQLException {
        connection = ConnectionFactory.getConnection();
        clienteDAO = new ClienteDAO();
    }

    // Limpeza executada após cada teste
    @After
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Teste para o método cadastrar da classe ClienteDAO
    @Test
    public void cadastrarTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Cliente 01");
        Integer qtd = clienteDAO.cadastrar(cliente);
        assertTrue(qtd == 1);

        Cliente cliente1 = new Cliente();
        cliente1.setCodigo("02");
        cliente1.setNome("Cliente 02");
        Integer qtd1 = clienteDAO.cadastrar(cliente1);
        assertTrue(qtd1 == 1);

        Cliente cliente2 = new Cliente();
        cliente2.setCodigo("03");
        cliente2.setNome("Cliente 03");
        Integer qtd2 = clienteDAO.cadastrar(cliente2);
        assertTrue(qtd2 == 1);
    }

    // Teste para o método atualizar da classe ClienteDAO
    @Test
    public void atualizarTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("06");
        cliente.setNome("Cliente 06");
        Integer cadastroResult = clienteDAO.cadastrar(cliente);
        assertEquals(Integer.valueOf(1), cadastroResult);

        Cliente clienteCadastrado = clienteDAO.consultar(cliente.getCodigo());
        assertNotNull(clienteCadastrado);
        Long idCliente = clienteCadastrado.getId();

        cliente.setCodigo("06");
        cliente.setNome("Cliente 05");
        cliente.setId(idCliente);

        Integer atualizacaoResult = clienteDAO.atualizar(cliente);
        assertEquals(Integer.valueOf(1), atualizacaoResult);

        Cliente clienteAtualizado = clienteDAO.consultar(cliente.getCodigo());
        assertNotNull(clienteAtualizado);
        assertEquals(cliente.getCodigo(), clienteAtualizado.getCodigo());
        assertEquals(cliente.getNome(), clienteAtualizado.getNome());
    }

    // Teste para o método consultar da classe ClienteDAO
    @Test
    public void consultarTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("04");
        cliente.setNome("Cliente 04");
        Integer cadastroResult = clienteDAO.cadastrar(cliente);
        assertEquals(Integer.valueOf(1), cadastroResult);

        Cliente clienteConsultado = clienteDAO.consultar(cliente.getCodigo());
        assertNotNull(clienteConsultado);
        assertEquals(cliente.getCodigo(), clienteConsultado.getCodigo());
        assertEquals(cliente.getNome(), clienteConsultado.getNome());
    }

    // Teste para o método excluir da classe ClienteDAO
    @Test
    public void excluirTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("07");
        cliente.setNome("Cliente 07");
        Integer cadastroResult = clienteDAO.cadastrar(cliente);
        assertEquals(Integer.valueOf(1), cadastroResult);

        Integer exclusaoResult = clienteDAO.excluir(cliente);
        assertEquals(Integer.valueOf(1), exclusaoResult);

        Cliente clienteExcluido = clienteDAO.consultar(cliente.getCodigo());
        assertNull(clienteExcluido);
    }

    // Teste para o método buscarTodos da classe ClienteDAO
    @Test
    public void buscarTodosTest() throws Exception {
        Cliente cliente7 = new Cliente();
        cliente7.setCodigo("10");
        cliente7.setNome("Cliente 10");
        clienteDAO.cadastrar(cliente7);

        Cliente cliente8 = new Cliente();
        cliente8.setCodigo("08");
        cliente8.setNome("Cliente 08");
        clienteDAO.cadastrar(cliente8);

        Cliente cliente9 = new Cliente();
        cliente9.setCodigo("09");
        cliente9.setNome("Cliente 09");
        clienteDAO.cadastrar(cliente9);

        List<Cliente> clientes = clienteDAO.buscarTodos();
        assertNotNull(clientes);
        assertEquals(8, clientes.size());

        assertTrue(clientes.stream().anyMatch(c -> c.getCodigo().equals(cliente7.getCodigo())));
        assertTrue(clientes.stream().anyMatch(c -> c.getCodigo().equals(cliente8.getCodigo())));
        assertTrue(clientes.stream().anyMatch(c -> c.getCodigo().equals(cliente9.getCodigo())));
    }
}
