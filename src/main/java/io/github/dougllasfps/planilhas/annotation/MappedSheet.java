package io.github.dougllasfps.planilhas.annotation;

import io.github.dougllasfps.planilhas.mapper.ExcelRowMapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MappedSheet {

    Class<? extends ExcelRowMapper> mapper();

}