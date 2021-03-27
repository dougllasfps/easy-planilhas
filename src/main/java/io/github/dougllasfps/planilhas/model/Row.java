package io.github.dougllasfps.planilhas.model;

/**
 * Criado por dougllas.sousa em 13/01/2017.
 */
public class Row {

    private int index;
    private Object[] data;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }
}