package org.dougllas.planilhas.model;

/**
 * Criado por dougllas.sousa em 13/01/2017.
 */
public class SpreadSheetConfig {

    private boolean enableStickHeader;
    private boolean enableFilterOnColumns;

    public boolean isEnableStickHeader() {
        return enableStickHeader;
    }

    public void setEnableStickHeader(boolean enableStickHeader) {
        this.enableStickHeader = enableStickHeader;
    }

    public boolean isEnableFilterOnColumns() {
        return enableFilterOnColumns;
    }

    public void setEnableFilterOnColumns(boolean enableFilterOnColumns) {
        this.enableFilterOnColumns = enableFilterOnColumns;
    }
}