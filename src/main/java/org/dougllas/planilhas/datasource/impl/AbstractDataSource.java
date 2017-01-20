package org.dougllas.planilhas.datasource.impl;

import org.dougllas.planilhas.datasource.SpreadSheetDataSource;

import java.util.Collection;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public abstract class AbstractDataSource<T> implements SpreadSheetDataSource<T> {

    private Collection<T> source;

    public AbstractDataSource(Collection<T> source) {
        this.source = source;
    }

    @Override
    public Collection<T> getSource() {
        return this.source;
    }
}