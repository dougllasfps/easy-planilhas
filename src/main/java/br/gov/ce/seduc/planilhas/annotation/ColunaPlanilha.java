package br.gov.ce.seduc.planilhas.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.gov.ce.seduc.planilhas.CellTextAlignment;
import br.gov.ce.seduc.planilhas.ColumnHeaderTextAlignment;

/**
 * @author dougllas.sousa
 * @since 17-08-2016
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColunaPlanilha {

	String cabecalho() default "";
	
	int tamanho() default 10;
	
	CellTextAlignment cellTextAlignment() default CellTextAlignment.LEFT;
	
	ColumnHeaderTextAlignment columnHeaderTextAlignment() default ColumnHeaderTextAlignment.LEFT;
	
	int columnPosition() default -1;
	
}