package org.dougllas.planilhas.model;

import org.dougllas.planilhas.annotation.SheetColumn;
import org.dougllas.planilhas.annotation.Style;
import org.dougllas.planilhas.format.Alignment;

import java.util.Date;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public class ModelClass {

    @SheetColumn(header = "Nome do Nego", width = 20, index = 2)
    @Style(columnHeaderTextAlignment = Alignment.CENTER, cellTextAlignment = CellTextAlignment.CENTER)
    private String nome;

    @SheetColumn(header = "Idade do Nego", width = 10, index = 0)
    private Integer idade;

    @SheetColumn(header = "Data de nasc. do Nego", width = 40, index = 1)
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