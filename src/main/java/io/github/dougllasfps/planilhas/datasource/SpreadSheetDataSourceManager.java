package io.github.dougllasfps.planilhas.datasource;

import io.github.dougllasfps.planilhas.model.Column;
import io.github.dougllasfps.planilhas.model.Row;

import java.util.List;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public interface SpreadSheetDataSourceManager<DS extends SpreadSheetDataSource> {

    List<Row> populateRows(DS dataSource);

    List<Column> populateColumns(DS dataSource);
}