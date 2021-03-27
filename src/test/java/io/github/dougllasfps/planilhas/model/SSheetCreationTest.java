package io.github.dougllasfps.planilhas.model;

import io.github.dougllasfps.planilhas.ModelClass;
import io.github.dougllasfps.planilhas.datasource.SpreadSheetDataSource;
import io.github.dougllasfps.planilhas.datasource.impl.AnnotationDataSource;
import io.github.dougllasfps.planilhas.datasource.impl.RowMapperDataSource;
import io.github.dougllasfps.planilhas.generator.SpreadSheetFillManager;
import io.github.dougllasfps.planilhas.generator.SpreadSheetGenerator;
import io.github.dougllasfps.planilhas.mapper.ExcelRowMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public class SSheetCreationTest {

    private ExcelRowMapper<ModelClass> rowMapper;
    private List<ModelClass> source;
    private FileService fileService;

    @Before
    public void setUp(){

        fileService = new FileService();

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
    @Ignore
    public void annotationTest() throws IOException {
        SpreadSheetDataSource<ModelClass> dataSource = new AnnotationDataSource<>(source);
        SpreadSheetFillManager<ModelClass> fillManager = new SpreadSheetFillManager<>(dataSource);
        SpreadSheet sp = fillManager.fill( new SpreadSheet() );

        SpreadSheetGenerator generator = new SpreadSheetGenerator(sp);
        byte[] bytes = generator.exportToBytes();

        fileService.save("generated.xls", bytes);

        Assert.assertEquals(sp.getColumns().size(), 3);
        Assert.assertEquals(sp.getRows().size(), 2);
    }

    @Test
    public void mapperTest() throws IOException{

        SpreadSheetDataSource<ModelClass> dataSource = new RowMapperDataSource<>(source, rowMapper);

        SpreadSheetFillManager<ModelClass> fillManager = new SpreadSheetFillManager<>(dataSource);
        SpreadSheet sp = fillManager.fill( new SpreadSheet() );

        SpreadSheetGenerator generator = new SpreadSheetGenerator(sp);
        byte[] bytes = generator.exportToBytes();

        fileService.save("generated.xls", bytes);

        Assert.assertEquals(sp.getColumns().size(), 3);
        Assert.assertEquals(sp.getRows().size(), 2);
    }
}
