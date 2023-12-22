// Pacote que contém a classe
package br.com.pazzini.dao.generic.jdbc.domain;

// Importação necessária
import br.com.calleb.dao.generic.jdbc.dao.ProdutoDAO;

import java.util.Objects;

/**
 * Descrição: Classe que representa a entidade Produto.
 * Criada por calle em 20/09/2023.
 */
public class Produto {

    // Atributos da classe
    private Long id;
    private String codigo;
    private String nome;
    private String estadoSigla;

    // Método getter para obter o ID
    public Long getId() {
        return id;
    }

    // Método setter para definir o ID
    public void setId(Long id) {
        this.id = id;
    }

    // Método getter para obter o código
    public String getCodigo() {
        return codigo;
    }

    // Método setter para definir o código
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Método getter para obter o nome
    public String getNome() {
        return nome;
    }

    // Método setter para definir o nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método getter para obter a sigla do estado
    public String getEstadoSigla() {
        return estadoSigla;
    }

    // Método setter para definir a sigla do estado
    public void setEstadoSigla(String estadoSigla) {
        this.estadoSigla = estadoSigla;
    }

    // Sobrescrita do método equals para comparar objetos Produto
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(codigo, produto.codigo) && Objects.equals(nome, produto.nome) && Objects.equals(estadoSigla, produto.estadoSigla);
    }

    // Sobrescrita do método hashCode para gerar código de hash baseado nos atributos do produto
    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nome, estadoSigla);
    }
}