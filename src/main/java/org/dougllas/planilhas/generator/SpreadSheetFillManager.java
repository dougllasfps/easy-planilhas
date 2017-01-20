package org.dougllas.planilhas.generator;

import org.dougllas.planilhas.datasource.SpreadSheetDataSource;
import org.dougllas.planilhas.datasource.impl.AnnotationDataSource;
import org.dougllas.planilhas.datasource.manager.AnnotationDataSourceManager;
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
        AnnotationDataSourceManager manager = new AnnotationDataSourceManager();

        List<Column> columns = manager.populateColumns((AnnotationDataSource) this.dataSource);
        List<Row> rows = manager.populateRows((AnnotationDataSource) this.dataSource);

        spreadSheet.setColumns(columns);
        spreadSheet.setRows(rows);

        return spreadSheet;
    }
}