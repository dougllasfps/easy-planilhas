package org.dougllas.planilhas.model;

/**
 * Criado por dougllas.sousa em 13/01/2017.
 */
public class Column {

    private String header;
    private int width;
    private int index;
    private String valueBinding;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValueBinding() {
        return valueBinding;
    }

    public void setValueBinding(String valueBinding) {
        this.valueBinding = valueBinding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Column)) return false;

        Column column = (Column) o;

        if (getWidth() != column.getWidth()) return false;
        if (getIndex() != column.getIndex()) return false;
        if (getHeader() != null ? !getHeader().equals(column.getHeader()) : column.getHeader() != null) return false;
        return !(getValueBinding() != null ? !getValueBinding().equals(column.getValueBinding()) : column.getValueBinding() != null);

    }

    @Override
    public int hashCode() {
        int result = getHeader() != null ? getHeader().hashCode() : 0;
        result = 31 * result + getWidth();
        result = 31 * result + getIndex();
        result = 31 * result + (getValueBinding() != null ? getValueBinding().hashCode() : 0);
        return result;
    }
}