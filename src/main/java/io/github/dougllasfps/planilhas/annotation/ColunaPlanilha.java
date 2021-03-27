package io.github.dougllasfps.planilhas.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.dougllasfps.planilhas.format.Alignment;

/**
 * @author dougllas.sousa
 * @since 17-08-2016
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColunaPlanilha {

	String cabecalho() default "";
	
	int tamanho() default 10;
	
	Alignment cellTextAlignment() default Alignment.LEFT;
	
	Alignment columnHeaderTextAlignment() default Alignment.LEFT;
	
	int columnPosition() default -1;
	
}