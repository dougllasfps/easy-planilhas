package io.github.dougllasfps.planilhas.datasource.impl;

import io.github.dougllasfps.planilhas.datasource.SpreadSheetDataSource;

import java.util.List;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public class AnnotationDataSource<T> extends AbstractDataSource<T> implements SpreadSheetDataSource<T>{

    private Class<T> model;

    public AnnotationDataSource(List<T> source) {
        super(source);

        if(source != null && !source.isEmpty()){
            this.model = (Class<T>) source.get(0).getClass();
        }

    }

    public Class<T> getModel() {
        return model;
    }
}