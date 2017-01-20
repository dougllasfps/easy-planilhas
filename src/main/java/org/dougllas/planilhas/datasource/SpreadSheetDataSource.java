package org.dougllas.planilhas.datasource;

import org.dougllas.planilhas.datasource.impl.AnnotationDataSource;
import org.dougllas.planilhas.datasource.impl.RowMapperDataSource;
import org.dougllas.planilhas.datasource.manager.AnnotationDataSourceManager;
import org.dougllas.planilhas.datasource.manager.MapperDataSourceManager;

import java.util.List;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public interface SpreadSheetDataSource<T> {

    List<T> getSource();

    public static SpreadSheetDataSourceManager of(SpreadSheetDataSource ds){
        if(ds instanceof AnnotationDataSource){
            return new AnnotationDataSourceManager();
        }

        if(ds instanceof RowMapperDataSource){
            return new MapperDataSourceManager();
        }

        return null;
    }
}