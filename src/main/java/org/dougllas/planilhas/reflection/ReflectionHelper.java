package org.dougllas.planilhas.reflection;

import org.dougllas.planilhas.annotation.AnnotationConfig;
import org.dougllas.planilhas.annotation.SheetColumn;
import org.dougllas.planilhas.annotation.Transient;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public class ReflectionHelper {

    public static List<AnnotationConfig> extractConfigurationfromClass(Class clazz){
        Field[] declaredFields = clazz.getDeclaredFields();
        List<AnnotationConfig> configs = new ArrayList<>();

        int defaultIndex = 0;

        for(Field f : declaredFields){
            f.setAccessible(true);

            if(f.isAnnotationPresent(Transient.class) || f.getName().equalsIgnoreCase("serialversionuid")){
                continue;
            }

            AnnotationConfig config = new AnnotationConfig();

            if(f.isAnnotationPresent(SheetColumn.class)){
                SheetColumn annotation = f.getAnnotation(SheetColumn.class);
                config.setHeader(annotation.header());
                config.setIndex(annotation.index());
                config.setWidth(annotation.width());
            }else{
                config.setWidth(10);
                config.setIndex(defaultIndex++);
                config.setHeader(f.getName().toUpperCase());
            }

            config.setFieldName(f.getName());
            configs.add(config);

        }

        return configs;
    }
}