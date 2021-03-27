package io.github.dougllasfps.planilhas;

import io.github.dougllasfps.planilhas.model.Column;

/**
 * Criado por dougllas.sousa em 23/01/2017.
 */
public interface PrintColumnStrategy<T> {

    int print(Column column, T objeto);

}
