package org.dougllas.planilhas;

import org.dougllas.planilhas.model.Column;

/**
 * Criado por dougllas.sousa em 23/01/2017.
 */
public interface PrintColumnStrategy<T> {

    int print(Column column, T objeto);

}
