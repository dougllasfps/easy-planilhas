package io.github.dougllasfps.planilhas.datasource.manager;

import io.github.dougllasfps.planilhas.datasource.SpreadSheetDataSourceManager;
import io.github.dougllasfps.planilhas.datasource.impl.RowMapperDataSource;
import io.github.dougllasfps.planilhas.mapper.ExcelRowMapper;
import io.github.dougllasfps.planilhas.model.Column;
import io.github.dougllasfps.planilhas.model.Row;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Criado por dougllas.sousa em 20/01/2017.
 */
public class MapperDataSourceManager implements SpreadSheetDataSourceManager<RowMapperDataSource> {

    @Override
    public List<Row> populateRows(RowMapperDataSource dataSource) {
        ExcelRowMapper rowMapper = dataSource.getRowMapper();
        List source = dataSource.getSource();

        List<Row> rows = new ArrayList<>();

        int index = 0;

        for (Object o : source) {
            Object[] extract = rowMapper.mapRow(o);

            Row row = new Row();
            row.setData(extract);
            row.setIndex(index++);

            rows.add(row);
        }

        return rows;
    }

    @Override
    public List<Column> populateColumns(RowMapperDataSource dataSource) {

        ExcelRowMapper rowMapper = dataSource.getRowMapper();
        String[] columnHeader = rowMapper.getColumnHeader();

        if(columnHeader == null){
            return Collections.EMPTY_LIST;
        }

        List<Column> columns = new ArrayList<>();

        for (int index = 0; index < columnHeader.length ; index ++) {
            String header = columnHeader[index];
            Column column = new Column();
            column.setIndex(index);
            column.setHeader(header);

            columns.add(column);
        }

        return columns;
    }
}
