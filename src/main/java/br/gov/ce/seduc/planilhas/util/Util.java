package br.gov.ce.seduc.planilhas.util;

public class Util {
	public static void notNull(Object obj){
		if(obj == null){
			throw new IllegalArgumentException();
		}
	}
}
