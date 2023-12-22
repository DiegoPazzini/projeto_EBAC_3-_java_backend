// Pacote que contém a classe
package br.com.pazzini.dao.generic.jdbc.domain;

// Importação necessária
import java.util.Objects;

/**
 * Descrição: Classe que representa a entidade Cliente.
 * Criada por calle em 19/09/2023.
 */
public class Cliente {

    // Atributos da classe
    private Long id;
    private String codigo;
    private String nome;

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

    // Sobrescrita do método equals para comparar objetos Cliente
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(codigo, cliente.codigo);
    }

    // Sobrescrita do método hashCode para gerar código de hash baseado no código do cliente
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}