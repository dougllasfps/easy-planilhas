package io.github.dougllasfps.planilhas.model;

import java.util.List;

/**
 * Criado por dougllas.sousa em 13/01/2017.
 */
public class SpreadSheet {

    private List<Column> columns;
    private List<Row> rows;
    private SpreadSheetConfig config;
    private String description;

    public SpreadSheetConfig getConfig() {
        return config;
    }

    public void setConfig(SpreadSheetConfig config) {
        this.config = config;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}