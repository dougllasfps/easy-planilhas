package org.dougllas.planilhas.util;

public class Util {
	public static void notNull(Object obj){
		if(obj == null){
			throw new IllegalArgumentException();
		}
	}
}
