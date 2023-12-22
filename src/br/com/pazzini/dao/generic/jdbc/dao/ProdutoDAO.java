// Pacote que contém a classe
package br.com.calleb.dao.generic.jdbc.dao;

// Importações necessárias
import br.com.calleb.dao.generic.jdbc.ConnectionFactory;
import br.com.calleb.dao.generic.jdbc.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Descrição: Classe que implementa a interface IProdutoDAO para operações de acesso a dados relacionadas a Produto.
 * Criada por calle em 20/09/2023.
 */
public class ProdutoDAO implements IProdutoDAO {

    // Método para cadastrar um produto no banco de dados
    @Override
    public Integer cadastrarProduto(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO tbProduto (codigo, nome, estadoSigla) VALUES (?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, produto.getCodigo());
            stm.setString(2, produto.getNome());
            stm.setString(3, produto.getEstadoSigla());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    // Método para atualizar os dados de um produto no banco de dados
    @Override
    public Integer atalizarProduto(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE tbProduto SET nome = ?, estadoSigla = ? WHERE codigo = ? ";
            stm = connection.prepareStatement(sql);
            stm.setString(1, produto.getNome());
            stm.setString(2, produto.getEstadoSigla());
            stm.setString(3, produto.getCodigo());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para consultar um produto no banco de dados por código
    @Override
    public Produto consultarProduto(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM tbProduto WHERE codigo = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, codigo);
            rs = stm.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setCodigo(rs.getString("codigo"));
                produto.setNome(rs.getString("nome"));
                produto.setEstadoSigla(rs.getString("estadoSigla"));
            }
            return produto;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    // Método para excluir um produto do banco de dados
    @Override
    public Integer excluirProduto(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM tbProduto WHERE codigo = ? ";
            stm = connection.prepareStatement(sql);
            stm.setString(1, produto.getCodigo());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para buscar todos os produtos no banco de dados
    @Override
    public List<Produto> buscarTodosProdutos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Produto> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "Select * from tbProduto";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                String nome = rs.getString("NOME");
                String codigo = rs.getString("CODIGO");
                String estadoSigla = rs.getString("ESTADOSIGLA");
                Long id = rs.getLong("ID");
                produto.setNome(nome);
                produto.setCodigo(codigo);
                produto.setEstadoSigla(estadoSigla);
                produto.setId(id);
                list.add(produto);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return list;
    }
}

