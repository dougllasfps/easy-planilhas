package org.dougllas.planilhas.generator;

import org.dougllas.planilhas.datasource.SpreadSheetDataSource;
import org.dougllas.planilhas.datasource.SpreadSheetDataSourceManager;
import org.dougllas.planilhas.model.Column;
import org.dougllas.planilhas.model.Row;
import org.dougllas.planilhas.model.SpreadSheet;

import java.util.List;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public class SpreadSheetFillManager<T> {

    private SpreadSheetDataSource<T> dataSource;

    public SpreadSheetFillManager(SpreadSheetDataSource<T> dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(SpreadSheetDataSource<T> dataSource) {
        this.dataSource = dataSource;
    }

    public SpreadSheetDataSource<T> getDataSource() {
        return dataSource;
    }

    public SpreadSheet fill(SpreadSheet spreadSheet) {
        SpreadSheetDataSourceManager manager = SpreadSheetDataSource.of(this.dataSource);

        List<Column> columns = manager.populateColumns(this.dataSource);
        List<Row> rows = manager.populateRows(this.dataSource);

        spreadSheet.setColumns(columns);
        spreadSheet.setRows(rows);

        return spreadSheet;
    }

}