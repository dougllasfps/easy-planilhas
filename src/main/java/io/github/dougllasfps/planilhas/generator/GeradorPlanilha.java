package io.github.dougllasfps.planilhas.generator;

import java.io.IOException;
import java.util.List;

import io.github.dougllasfps.planilhas.generator.helper.SpreadSheetGeneratorHelper;
import io.github.dougllasfps.planilhas.CabecalhoColunas;
import io.github.dougllasfps.planilhas.Planilha;
import io.github.dougllasfps.planilhas.Sumario;
import io.github.dougllasfps.planilhas.mapper.ExcelRowMapper;

/**
 * @author dougllas.sousa
 * @since 17-08-2016
 */
public class GeradorPlanilha {

	public static byte[] exportToBytes(Planilha planilha) throws IOException{
		return exportToBytes(planilha, null);
	}

    public static byte[] exportToBytes(Planilha planilha, ExcelRowMapper mapper) throws IOException{
        SpreadSheetGeneratorHelper helper = gerar(planilha, mapper);
        return helper.exportToBytes();
    }

	private static SpreadSheetGeneratorHelper gerar(Planilha planilha, ExcelRowMapper mapper) {
        if(planilha == null || planilha.getConteudo() == null){
            throw new IllegalArgumentException("Planilha vazia.");
        }

		SpreadSheetGeneratorHelper helper = SpreadSheetGeneratorHelper.createSheet(planilha.getName());
		CabecalhoColunas cabecalhoColunas = planilha.getCabecalhoColunas();
		
		configurarTitulo(planilha, helper);
		configuraCabecalho(helper, cabecalhoColunas);
		configuraSumario(planilha, helper);
		
		if(planilha.getDataPattern() != null){
			helper.setDatePattern(planilha.getDataPattern());
		}

        if(!planilha.getConteudo().isEmpty()) {
            helper.addList(planilha.getConteudo(), mapper);
        }

		helper.setProtectSheet(planilha.isReadOnly());
		
		return helper;
	}

	private static void configurarTitulo(Planilha planilha, SpreadSheetGeneratorHelper helper) {
		if(planilha.getTitulo() != null){
			helper.addRow(planilha.getTitulo());
			helper.addBlankRow(2);
			helper.mergeCells(0, 1, 0, planilha.getCabecalhoColunas().getTitulos().size() - 1);
		}
	}

	private static void configuraSumario(Planilha planilha, SpreadSheetGeneratorHelper helper) {
		Sumario sumario = planilha.getSumario();
		
		if(sumario == null){
			return;
		}
		
		String revisao = String.valueOf(sumario.getRevisao() == null ? 0 : sumario.getRevisao());
	
		helper.addSummaryInfo(
				sumario.getAplicacao(), 
				sumario.getData(), 
				sumario.getAutor(), 
				revisao, 
				sumario.getAssunto(), 
				sumario.getTitulo(), 
				sumario.getPalavrasChave()
		);
	}

	private static void configuraCabecalho(SpreadSheetGeneratorHelper helper, CabecalhoColunas cabecalhoColunas) {
		if(cabecalhoColunas == null){
			return;
		}
		
		List<String> titulos = cabecalhoColunas.getTitulos();

		if(titulos != null){
			String[] cabecalho = titulos.toArray(new String[titulos.size()]);
			helper.addColumnHeader(cabecalho);
		}
		
		List<Integer> sizes = cabecalhoColunas.getSizes();
		
		if(sizes != null){
			Integer columnSizes[] = sizes.toArray(new Integer[sizes.size()]);
			helper.setColumnsSizes(columnSizes);
		}
		
		helper.setAddDefaultStyleColumnHeader(cabecalhoColunas.isAdicionarEstiloPadrao());
		helper.setAddFilterColumnHeader(cabecalhoColunas.isAdicionarFiltroColunas());
		helper.setAddStickHeader(cabecalhoColunas.isHabilitarStickHeader());
	}
}
