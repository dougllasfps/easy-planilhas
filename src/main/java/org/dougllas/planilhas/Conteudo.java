package org.dougllas.planilhas;

import java.util.Collection;

public class Conteudo {

	private Collection<?> data;

	public Conteudo(Collection<?> data) {
		super();
		this.data = data;
	}

	public Collection<?> getData() {
		return data;
	}

	public void setData(Collection<?> data) {
		this.data = data;
	}
}