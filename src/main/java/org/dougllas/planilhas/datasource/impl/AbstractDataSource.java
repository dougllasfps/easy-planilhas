package org.dougllas.planilhas.datasource.impl;

import org.dougllas.planilhas.datasource.SpreadSheetDataSource;

import java.util.List;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public abstract class AbstractDataSource<T> implements SpreadSheetDataSource<T> {

    private List<T> source;

    public AbstractDataSource(List<T> source) {
        this.source = source;
    }

    @Override
    public List<T> getSource() {
        return this.source;
    }
}