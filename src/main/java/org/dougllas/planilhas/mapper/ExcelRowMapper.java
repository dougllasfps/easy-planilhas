package org.dougllas.planilhas.mapper;

public interface ExcelRowMapper<T> {
	Object[] mapRow(T obj);
}