package org.dougllas.planilhas.model;

import org.dougllas.planilhas.datasource.SpreadSheetDataSource;
import org.dougllas.planilhas.datasource.impl.AnnotationDataSource;
import org.dougllas.planilhas.datasource.impl.RowMapperDataSource;
import org.dougllas.planilhas.generator.SpreadSheetFillManager;
import org.dougllas.planilhas.mapper.ExcelRowMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public class SSheetCreationTest {

    private ExcelRowMapper<ModelClass> rowMapper;
    private List<ModelClass> source;

    @Before
    public void setUp(){
        ModelClass model = new ModelClass();
        model.setData(new Date());
        model.setIdade(10);
        model.setNome("Model 1");

        ModelClass model1 = new ModelClass();
        model1.setData(new Date());
        model1.setIdade(20);
        model1.setNome("Model 2");

        source = Arrays.asList(model, model1);

        rowMapper = new ExcelRowMapper<ModelClass>() {
            @Override
            public Object[] mapRow(ModelClass obj) {
                return new Object[]{
                        obj.getData(),
                        obj.getIdade(),
                        obj.getNome()
                };
            }

            @Override
            public String[] getColumnHeader() {
                return new String[]{"data", "idade", "nome"};
            }
        };
    }

    @Test
    public void annotationTest(){

        SpreadSheetDataSource<ModelClass> dataSource = new AnnotationDataSource<>(source);
        SpreadSheetFillManager<ModelClass> fillManager = new SpreadSheetFillManager<>(dataSource);
        SpreadSheet sp = fillManager.fill( new SpreadSheet() );

        Assert.assertEquals(sp.getColumns().size(), 3);
        Assert.assertEquals(sp.getRows().size(), 2);
    }

    @Test
    public void mapperTest(){

        SpreadSheetDataSource<ModelClass> dataSource = new RowMapperDataSource<>(source, rowMapper);

        SpreadSheetFillManager<ModelClass> fillManager = new SpreadSheetFillManager<>(dataSource);
        SpreadSheet sp = fillManager.fill( new SpreadSheet() );

        Assert.assertEquals(sp.getColumns().size(), 3);
        Assert.assertEquals(sp.getRows().size(), 2);
    }
}
