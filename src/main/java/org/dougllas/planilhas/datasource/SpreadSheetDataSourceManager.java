package org.dougllas.planilhas.datasource;

import org.dougllas.planilhas.model.Column;
import org.dougllas.planilhas.model.Row;

import java.util.Collection;
import java.util.List;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public interface SpreadSheetDataSourceManager<DS extends SpreadSheetDataSource> {

    List<Row> populateRows(DS dataSource);

    List<Column> populateColumns(DS dataSource);
}