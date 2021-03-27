package io.github.dougllasfps.planilhas.annotation;

import io.github.dougllasfps.planilhas.PrintColumnStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Criado por dougllas.sousa em 23/01/2017.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RenderColumnStrategy {

    Class<? extends PrintColumnStrategy> value();
}
