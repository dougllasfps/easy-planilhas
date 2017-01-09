package br.gov.ce.seduc.planilhas;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import br.gov.ce.seduc.planilhas.annotation.ColunaPlanilha;
import br.gov.ce.seduc.planilhas.generator.GeradorPlanilha;
import br.gov.ce.seduc.planilhas.util.ReflectionUtil;
import br.gov.ce.seduc.planilhas.util.Util;

public class Planilha {
	
	private String 	dataPattern;
	private String 	name;
	private Sumario sumario;
	private String 	titulo;
	private CabecalhoColunas cabecalhoColunas = new CabecalhoColunas();
	private List<?> conteudo;
	
	private boolean readOnly;
	
	public Planilha() {}
	
	public Planilha(String name) {
		super();
		this.name = name;
	}

	public Planilha(String name, List<?> conteudo) {
		this(conteudo);
		this.name = name;
	}
	
	public Planilha(List<?> conteudo) {
		super();
		this.conteudo = conteudo;
		gerarTitulosCabecalho();
		gerarLarguraColunas();
	}

	Planilha(EstacaoTrabalho estacaoTrabalho) {
		Util.notNull(estacaoTrabalho);
		this.cabecalhoColunas = new CabecalhoColunas();
	}
	
	public String getDataPattern() {
		return dataPattern;
	}

	public void setDataPattern(String dataPattern) {
		this.dataPattern = dataPattern;
	}

	public CabecalhoColunas getCabecalhoColunas() {
		return cabecalhoColunas;
	}

	public void setCabecalhoColunas(CabecalhoColunas cabecalhoColunas) {
		this.cabecalhoColunas = cabecalhoColunas;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<?> getConteudo() {
		return conteudo;
	}

	public void setConteudo(List<?> conteudo) {
		this.conteudo = conteudo;
		gerarTitulosCabecalho();
		gerarLarguraColunas();
	}

	private void gerarTitulosCabecalho() {
		if(conteudo != null && !conteudo.isEmpty()){
			Object obj = conteudo.get(0);
			String[] cabecalhosColunas = ReflectionUtil.gerarCabecalhosColunas( obj.getClass() );
			getCabecalhoColunas().setTitulos(cabecalhosColunas);
		}
	}
	
	private void gerarLarguraColunas() {
		if(conteudo != null && !conteudo.isEmpty()){
			Object obj = conteudo.get(0);
			Integer[] cabecalhosColunas = ReflectionUtil.gerarLargurasColunas( obj.getClass() );
			getCabecalhoColunas().setSizes(cabecalhosColunas);
		}
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public Sumario getSumario() {
		return sumario;
	}

	public void setSumario(Sumario sumario) {
		this.sumario = sumario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}