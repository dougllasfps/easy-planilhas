package io.github.dougllasfps.planilhas;

import java.util.ArrayList;
import java.util.List;

public class CabecalhoColunas {

	private boolean adicionarEstiloPadrao;
	private boolean adicionarFiltroColunas;
	private boolean habilitarStickHeader;

	private List<String> titulos;
	private List<Integer> sizes;

	public CabecalhoColunas adicionaTitulo(String tituloColuna) {
		if (titulos == null)
			titulos = new ArrayList<String>();
		titulos.add(tituloColuna);
		return this;
	}

	public CabecalhoColunas setTitulos(String... titulosColunas) {
		if (titulosColunas == null) {
			return this;
		}

		for (String string : titulosColunas) {
			adicionaTitulo(string);
		}

		return this;
	}
	
	public void setSizes(Integer... titulosColumnsSizes) {
		if (titulosColumnsSizes == null) {
			return;
		}
		
		if(sizes == null){
			sizes = new ArrayList<Integer>();
		}

		for (int i : titulosColumnsSizes) {
			sizes.add(i);
		}
	}
	
	public boolean isAdicionarEstiloPadrao() {
		return adicionarEstiloPadrao;
	}

	public void setAdicionarEstiloPadrao(boolean adicionarEstiloPadrao) {
		this.adicionarEstiloPadrao = adicionarEstiloPadrao;
	}

	public boolean isAdicionarFiltroColunas() {
		return adicionarFiltroColunas;
	}

	public void setAdicionarFiltroColunas(boolean adicionarFiltroColunas) {
		this.adicionarFiltroColunas = adicionarFiltroColunas;
	}

	public boolean isHabilitarStickHeader() {
		return habilitarStickHeader;
	}

	public void setHabilitarStickHeader(boolean habilitarStickHeader) {
		this.habilitarStickHeader = habilitarStickHeader;
	}
	
	public List<String> getTitulos() {
		return titulos;
	}

	public List<Integer> getSizes() {
		return sizes;
	}
}