package org.dougllas.planilhas.generator;

import java.io.IOException;
import java.util.List;

import org.dougllas.planilhas.CabecalhoColunas;
import org.dougllas.planilhas.Planilha;
import org.dougllas.planilhas.Sumario;
import org.dougllas.planilhas.generator.helper.PlanilhaGeneratorHelper;

/**
 * @author dougllas.sousa
 * @since 17-08-2016
 */
public class GeradorPlanilha {

	public static byte[] exportToBytes(Planilha planilha) throws IOException{
		PlanilhaGeneratorHelper helper = gerar(planilha);
		return helper.exportToBytes();
	}

	private static PlanilhaGeneratorHelper gerar(Planilha planilha) {
        if(planilha == null || planilha.getConteudo() == null || planilha.getConteudo().isEmpty()){
            throw new IllegalArgumentException("Planilha vazia.");
        }

		PlanilhaGeneratorHelper helper = PlanilhaGeneratorHelper.createPlanilha(planilha.getName());
		CabecalhoColunas cabecalhoColunas = planilha.getCabecalhoColunas();
		
		configurarTitulo(planilha, helper);
		configuraCabecalho(helper, cabecalhoColunas);
		configuraSumario(planilha, helper);
		
		if(planilha.getDataPattern() != null){
			helper.setDatePattern(planilha.getDataPattern());
		}
		
		helper.addList(planilha.getConteudo());
		helper.setProtegerPlanilha(planilha.isReadOnly());
		
		return helper;
	}

	private static void configurarTitulo(Planilha planilha, PlanilhaGeneratorHelper helper) {
		if(planilha.getTitulo() != null){
			helper.adicionaLinha(planilha.getTitulo());
			helper.adicionaLinhaEmBranco(2);
			helper.mesclarCelulas(0, 1, 0, planilha.getCabecalhoColunas().getTitulos().size() - 1);
		}
	}

	private static void configuraSumario(Planilha planilha, PlanilhaGeneratorHelper helper) {
		Sumario sumario = planilha.getSumario();
		
		if(sumario == null){
			return;
		}
		
		String revisao = String.valueOf(sumario.getRevisao() == null ? 0 : sumario.getRevisao());
	
		helper.adicionaInformacaoAoSumario(
				sumario.getAplicacao(), 
				sumario.getData(), 
				sumario.getAutor(), 
				revisao, 
				sumario.getAssunto(), 
				sumario.getTitulo(), 
				sumario.getPalavrasChave()
		);
	}

	private static void configuraCabecalho(PlanilhaGeneratorHelper helper, CabecalhoColunas cabecalhoColunas) {
		if(cabecalhoColunas == null){
			return;
		}
		
		List<String> titulos = cabecalhoColunas.getTitulos();

		if(titulos != null){
			String[] cabecalho = titulos.toArray(new String[titulos.size()]);
			helper.adicionaCabecalho(cabecalho);
		}
		
		List<Integer> sizes = cabecalhoColunas.getSizes();
		
		if(sizes != null){
			Integer columnSizes[] = sizes.toArray(new Integer[sizes.size()]);
			helper.setColumnsSizes(columnSizes);
		}
		
		helper.setAdicionarEstiloPadraoCabecalho(cabecalhoColunas.isAdicionarEstiloPadrao());
		helper.setAdicionarFiltroColunasCabecalho(cabecalhoColunas.isAdicionarFiltroColunas());
		helper.setCongelarCabecalho(cabecalhoColunas.isHabilitarStickHeader());
	}
}
