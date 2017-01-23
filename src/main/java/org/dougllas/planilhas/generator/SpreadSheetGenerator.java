package org.dougllas.planilhas.generator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dougllas.planilhas.generator.helper.SpreadSheetGeneratorHelper;
import org.dougllas.planilhas.model.Column;
import org.dougllas.planilhas.model.Row;
import org.dougllas.planilhas.model.SpreadSheet;
import org.dougllas.planilhas.util.RowColumnOrganization;

import java.io.IOException;
import java.util.List;

/**
 * Criado por dougllas.sousa em 20/01/2017.
 */
public class SpreadSheetGenerator {

    private SpreadSheet sheet;
    private SpreadSheetGeneratorHelper helper;
    private boolean rowsEncoded;
    private boolean columnsEncoded;

    public SpreadSheetGenerator(SpreadSheet sheet){
        this.sheet = sheet;

        if(sheet == null){
            throw new IllegalArgumentException("Sheet can't be null!");
        }

        helper = SpreadSheetGeneratorHelper.createSheet(sheet.getDescription());
    }

    public HSSFWorkbook generateWorkBook(){

        if(!columnsEncoded) {
            encodeColumns();
        }

        if(!rowsEncoded) {
            encodeRows();
        }

        return helper.getWorkbook();
    }

    public byte[] exportToBytes() throws IOException{

        if(!columnsEncoded) {
            encodeColumns();
        }

        if(!rowsEncoded) {
            encodeRows();
        }

        return helper.exportToBytes();
    }

    private void encodeColumns(){
        List<Column> columns = this.sheet.getColumns();
        RowColumnOrganization.sortColumnsByIndex(columns);
        String[] header = extractHeader(columns);
        helper.addColumnHeader(header);
        columnsEncoded = true;
    }

    private void encodeRows(){
        List<Row> rows = this.sheet.getRows();

        RowColumnOrganization.sortRowsByIndex(rows);

        for(Row row : rows){
            helper.addRow(row.getData());
        }

        rowsEncoded = true;
    }

    private String[] extractHeader(List<Column> columns){
        int size = RowColumnOrganization.getHigherIndex(columns) + 1;

        String[] header = new String[size];

        for (Column column : columns) {
            header[column.getIndex()] = column.getHeader();
        }

        for(int x = 0 ; x < size ; x++){
            String h = header[x];

            if(h == null){
                header[x] = "";
            }
        }

        return header;
    }
}