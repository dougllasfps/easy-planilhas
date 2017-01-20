package org.dougllas.planilhas.datasource;

import java.util.Collection;

/**
 * Criado por dougllas.sousa em 16/01/2017.
 */
public interface SpreadSheetDataSource<T> {
    Collection<T> getSource();
}