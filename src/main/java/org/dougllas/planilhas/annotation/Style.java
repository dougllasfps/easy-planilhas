package org.dougllas.planilhas.annotation;

import org.dougllas.planilhas.format.Alignment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Criado por dougllas.sousa em 20/01/2017.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Style {

    Alignment cellAlignment() default Alignment.LEFT;

    Alignment columnHeaderAlignment() default Alignment.LEFT;

}