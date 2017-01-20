package org.dougllas.planilhas.datasource.impl;

import org.dougllas.planilhas.datasource.SpreadSheetDataSource;
import org.dougllas.planilhas.mapper.ExcelRowMapper;

import java.util.Collection;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public class RowMapperDataSource<T> extends AbstractDataSource<T> implements SpreadSheetDataSource<T> {

    private ExcelRowMapper<T> rowMapper;

    public RowMapperDataSource(Collection<T> source, ExcelRowMapper<T> rowMapper) {
        super(source);
        this.rowMapper = rowMapper;
    }

    public ExcelRowMapper<T> getRowMapper() {
        return rowMapper;
    }
}