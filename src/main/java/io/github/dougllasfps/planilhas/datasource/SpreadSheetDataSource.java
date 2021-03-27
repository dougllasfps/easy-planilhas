package io.github.dougllasfps.planilhas.datasource;

import io.github.dougllasfps.planilhas.datasource.impl.AnnotationDataSource;
import io.github.dougllasfps.planilhas.datasource.impl.RowMapperDataSource;
import io.github.dougllasfps.planilhas.datasource.manager.AnnotationDataSourceManager;
import io.github.dougllasfps.planilhas.datasource.manager.MapperDataSourceManager;

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