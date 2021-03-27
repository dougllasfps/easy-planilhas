package io.github.dougllasfps.planilhas.datasource.impl;

import io.github.dougllasfps.planilhas.datasource.SpreadSheetDataSource;
import io.github.dougllasfps.planilhas.mapper.ExcelRowMapper;

import java.util.List;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public class RowMapperDataSource<T> extends AbstractDataSource<T> implements SpreadSheetDataSource<T> {

    private ExcelRowMapper<T> rowMapper;

    public RowMapperDataSource(List<T> source, ExcelRowMapper<T> rowMapper) {
        super(source);

        if(rowMapper == null){
            throw new IllegalStateException("Row mapper não pode ser null");
        }

        this.rowMapper = rowMapper;
    }

    public ExcelRowMapper<T> getRowMapper() {
        return rowMapper;
    }
}