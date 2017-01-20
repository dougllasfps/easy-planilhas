package org.dougllas.planilhas.model;

import org.dougllas.planilhas.datasource.impl.AnnotationDataSource;
import org.dougllas.planilhas.generator.SpreadSheetFillManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public class SSheetCreationTest {

    @Test
    public void test(){

        AnnotationDataSource<ModelClass> dataSource = new AnnotationDataSource<>(Arrays.asList(new ModelClass(), new ModelClass()));
        SpreadSheetFillManager<ModelClass> fillManager = new SpreadSheetFillManager<>(dataSource);
        SpreadSheet sp = fillManager.fill( new SpreadSheet() );

        Assert.assertEquals(sp.getColumns().size(), 3);
        Assert.assertEquals(sp.getRows().size(), 2);
    }
}
