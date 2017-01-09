package br.gov.ce.seduc.planilhas;

import java.util.Date;

public class Sumario {
	
	private String aplicacao;
	private Date data;
	private String autor;
	private Integer revisao;
	private String assunto;
	private String titulo;
	private String palavrasChave;
	
	//propriedades devem ser iniciadas
	//pois no codigo da poi d� exception se faltar alguma informa��o (null)
	public Sumario() {
		revisao = 0;
		aplicacao = autor = assunto = titulo = palavrasChave = "";
	}
	
	public String getAplicacao() {
		return aplicacao;
	}
	public Sumario aplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
		return this;
	}
	public Date getData() {
		return data;
	}
	public Sumario data(Date data) {
		this.data = data;
		return this;
	}
	public String getAutor() {
		return autor;
	}
	public Sumario autor(String autor) {
		this.autor = autor;
		return this;
	}
	public Integer getRevisao() {
		return revisao;
	}
	public Sumario revisao(Integer revisao) {
		this.revisao = revisao;
		return this;
	}
	public String getAssunto() {
		return assunto;
	}
	public Sumario assunto(String assunto) {
		this.assunto = assunto;
		return this;
	}
	public String getTitulo() {
		return titulo;
	}
	public Sumario titulo(String titulo) {
		this.titulo = titulo;
		return this;
	}
	public String getPalavrasChave() {
		return palavrasChave;
	}
	public Sumario palavrasChave(String palavrasChave) {
		this.palavrasChave = palavrasChave;
		return this;
	}
}