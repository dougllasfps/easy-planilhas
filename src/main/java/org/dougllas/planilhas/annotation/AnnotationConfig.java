package org.dougllas.planilhas.annotation;

import org.dougllas.planilhas.format.Alignment;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public class AnnotationConfig {

    private String header;
    private int width;
    private Alignment cellTextAlignment;
    private Alignment columnHeaderTextAlignment;
    private int index;
    private String fieldName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

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

    public Alignment getCellTextAlignment() {
        return cellTextAlignment;
    }

    public void setCellTextAlignment(Alignment cellTextAlignment) {
        this.cellTextAlignment = cellTextAlignment;
    }

    public Alignment getColumnHeaderTextAlignment() {
        return columnHeaderTextAlignment;
    }

    public void setColumnHeaderTextAlignment(Alignment columnHeaderTextAlignment) {
        this.columnHeaderTextAlignment = columnHeaderTextAlignment;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}