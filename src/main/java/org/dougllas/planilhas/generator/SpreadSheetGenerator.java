package org.dougllas.planilhas.generator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dougllas.planilhas.generator.helper.SpreadSheetGeneratorHelper;
import org.dougllas.planilhas.model.Column;
import org.dougllas.planilhas.model.Row;
import org.dougllas.planilhas.model.SpreadSheet;

import java.io.IOException;
import java.util.List;

/**
 * Criado por dougllas.sousa em 20/01/2017.
 */
public class SpreadSheetGenerator {

    private SpreadSheet sheet;
    private SpreadSheetGeneratorHelper helper;

    public SpreadSheetGenerator(SpreadSheet sheet){
        this.sheet = sheet;

        if(sheet == null){
            throw new IllegalArgumentException("Sheet can't be null!");
        }

        helper = SpreadSheetGeneratorHelper.createPlanilha(sheet.getDescription());
    }

    public HSSFWorkbook generateWorkBook(){
        encodeColumns();
        encodeRows();
        return helper.getWorkbook();
    }

    public byte[] exportToBytes() throws IOException{
        return helper.exportToBytes();
    }

    private void encodeColumns(){
        List<Column> columns = this.sheet.getColumns();
        String[] header = extractHeader(columns);
        helper.adicionaCabecalho(header);
    }

    private void encodeRows(){
        List<Row> rows = this.sheet.getRows();

        for(Row row : rows){
            helper.adicionaLinha(row.getIndex(), row.getData());
        }
    }

    private String[] extractHeader(List<Column> columns){
        int size = columns.size();
        String[] header = new String[size];

        for (int x = 0 ; x < size ; x++) {
            header[x] = columns.get(x).getHeader();
        }

        return header;
    }
}