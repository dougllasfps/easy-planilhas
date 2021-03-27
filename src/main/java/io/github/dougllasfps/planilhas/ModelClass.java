package io.github.dougllasfps.planilhas;

import io.github.dougllasfps.planilhas.annotation.IndexConfiguration;
import io.github.dougllasfps.planilhas.annotation.RenderColumnStrategy;
import io.github.dougllasfps.planilhas.annotation.SheetColumn;
import io.github.dougllasfps.planilhas.format.Order;

import java.util.Date;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */


@IndexConfiguration(order = Order.ASC)
@RenderColumnStrategy(RemoveNullIndexStrategy.class)
public class ModelClass {

    @SheetColumn(header = "Idade do Nego", width = 10, index = 0)
    private Integer idade;

    @SheetColumn(header = "Nome do Nego", width = 20, index = 1)
    private String nome;

    @SheetColumn(header = "Data de nasc. do Nego", width = 40, index = 2)
    private Date data;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}