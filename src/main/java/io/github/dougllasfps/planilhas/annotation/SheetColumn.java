package io.github.dougllasfps.planilhas.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */

@Retention( RetentionPolicy.RUNTIME )
@Target( value = {ElementType.FIELD, ElementType.METHOD} )
public @interface SheetColumn {

    String header() default "";

    int width() default 10;

    int index() default -1;
}