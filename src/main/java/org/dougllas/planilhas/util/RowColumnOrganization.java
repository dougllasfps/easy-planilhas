package org.dougllas.planilhas.util;

import org.dougllas.planilhas.model.Column;
import org.dougllas.planilhas.model.Row;

import java.util.List;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public class RowColumnOrganization {

    public static void sortColumnsByIndex(List<Column> columns){
        columns.sort( (o1 , o2) -> Integer.valueOf(o1.getIndex()).compareTo(Integer.valueOf(o2.getIndex())));
    }

    public static void sortRowsByIndex(List<Row> rows){
        rows.sort( (o1 , o2) -> Integer.valueOf(o1.getIndex()).compareTo(Integer.valueOf(o2.getIndex())));
    }
}