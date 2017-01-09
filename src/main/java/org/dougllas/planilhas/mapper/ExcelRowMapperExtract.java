package org.dougllas.planilhas.mapper;

import java.util.ArrayList;
import java.util.List;

public class ExcelRowMapperExtract {

	private ExcelRowMapper rowMapper;
	
	public ExcelRowMapperExtract(ExcelRowMapper rowMapper) {
		if(rowMapper == null){
			throw new NullPointerException();
		}
		this.rowMapper = rowMapper;
	}
	
	public List<Object[]> extract(List<?> list){
		return extract(this.rowMapper , list);
	}
	
	public static List<Object[]> extract(final ExcelRowMapper rowMapper, final List<?> list){
		List<Object[]> returnList = new ArrayList<Object[]>();
		
		for (Object object : list) {
			Object[] obj = rowMapper.mapRow(object);
			returnList.add(obj);
		}
		
		return returnList;
	}
}