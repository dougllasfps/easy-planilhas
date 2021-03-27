package io.github.dougllasfps.planilhas.mapper;

public interface ExcelRowMapper<T> {
	Object[] mapRow(T obj);

	String[] getColumnHeader();
}