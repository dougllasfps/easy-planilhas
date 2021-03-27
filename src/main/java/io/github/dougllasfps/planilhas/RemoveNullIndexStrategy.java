package io.github.dougllasfps.planilhas;

import io.github.dougllasfps.planilhas.model.Column;

/**
 * Criado por dougllas.sousa em 23/01/2017.
 */
public class RemoveNullIndexStrategy implements PrintColumnStrategy<ModelClass> {

    @Override
    public int print(Column column, ModelClass model) {
        return 0;
    }
}
