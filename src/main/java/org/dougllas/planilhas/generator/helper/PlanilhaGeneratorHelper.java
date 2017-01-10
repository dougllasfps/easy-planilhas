package org.dougllas.planilhas.generator.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import org.dougllas.planilhas.annotation.ColunaPlanilha;
import org.dougllas.planilhas.mapper.ExcelRowMapper;
import org.dougllas.planilhas.mapper.ExcelRowMapperExtract;
import org.dougllas.planilhas.util.ReflectionUtil;

/**
 * @author dougllas.sousa
 * @since 17/06/2015
 */
public class PlanilhaGeneratorHelper {
	
	private static final String DEFAULT_DATE_TIME_PATTERN = new String("dd/MM/yyyy HH:mm:ss");
	
	private HSSFWorkbook workbook;
	private HSSFRow currentLine;
	private HSSFSheet currentSheet;
	private HSSFCell currentCell;
	
	private HSSFCellStyle currencyCellFormatStyle;
	
	private List<HSSFSheet> sheetList;
	private Integer rowCount;
	private Integer columnsHeaderRowNumber;
	private Integer[] sizes;
	
	private boolean adicionarFiltroColunasCabecalho;
	private boolean protegerPlanilha;
	private boolean adicionarEstiloPadraoCabecalho;
	private boolean congelarCabecalho;
	
	private SimpleDateFormat dateFormatter;
	private String datePattern;
	
	private PlanilhaGeneratorHelper( HSSFWorkbook workbook) {
		this.workbook = workbook;
		this.rowCount = 0;
		
		this.datePattern = DEFAULT_DATE_TIME_PATTERN;
		this.dateFormatter = new SimpleDateFormat(datePattern);
	}
	
	/**
	 * @param firstSheetName
	 * @return instancia com workbook e 1 folha (sheet)
	 */
	public static PlanilhaGeneratorHelper createPlanilha(String firstSheetName){
		PlanilhaGeneratorHelper obj = new PlanilhaGeneratorHelper(new HSSFWorkbook());
		obj.adicionaSheet(firstSheetName);
		return obj;
	}
	
	/**
	 * @return instancia com workbook e 1 folha (sheet) com o nome default 'planilha1', 
	 * que poder� ser alterado posteriormente.
	 */
	public static PlanilhaGeneratorHelper createPlanilha(){
		return createPlanilha("Planilha1");
	}
	
	/**
	 * adiciona mais uma planilha (folha/sheet) com o nome especificado ao workbook 
	 * @param name
	 */
	public void adicionaSheet(String name){
		if(this.sheetList == null){
			this.sheetList = new ArrayList<HSSFSheet>();
		}
		this.currentSheet = this.workbook.createSheet(name == null ? "planilha" : name);
		sheetList.add(currentSheet);
	}
	
	/**
	 * @param index
	 * @return a folha/planilha do index passado
	 * @throws IllegalArgumentException
	 */
	public HSSFSheet getSheet(int index) throws IllegalArgumentException{
		if(sheetList == null || sheetList.isEmpty()){
			throw new IllegalArgumentException("Nenhuma folha(Sheet) encontrado.");
		}
		return sheetList.get(index);
	}
	
	/**
	 * @param index
	 * adiciona uma celula no index especificado na linha corrente 
	 * caso a linha nao exista, esta ser� criada.
	 */
	public void adicionaCelulaBranco(int index){
		
		if(getCurrentLine() == null){
			adicionaLinhaEmBranco();
		}
		adicionaCelulaBranco(getCurrentLine().getRowNum(), index);
	}

	/**
	 * @param index
	 * adiciona uma celula no index especificado na linha passada 
	 */
	public void adicionaCelulaBranco(int rowIndex, int index){
		HSSFRow row = getCurrentSheet().getRow(rowIndex);
		if(row == null){
			throw new IllegalArgumentException("Linha passada n�o existe");
		}
		this.currentCell = row.createCell(index);
	}
	
	/**
	 * @param index
	 * @param value
	 * adiciona uma celula na linha corrente no index passado
	 */
	public void adicionaCelula(int index, Object value){
		if(getCurrentLine() == null){
			adicionaLinhaEmBranco();
		}
		int rowIndex = getCurrentLine().getRowNum();
		adicionaCelula(rowIndex, index, value);
	}
	
	/**
	 * @param row 
	 * 	linha onde ser� inserida a celula
	 * @param index
	 *  index da celula
	 * @param value
	 *  valor que ir� receber a c�lula
	 * adiciona celula na linha especificada
	 */
	public void adicionaCelula(int rowIndex, int index, Object value){
		HSSFRow row = getCurrentSheet().getRow(rowIndex);
		HSSFCell celula = null;
		
		if(value instanceof BigDecimal){
			BigDecimal bigDValue = (BigDecimal) value;
			value = bigDValue.doubleValue();
			celula = row.createCell(index);
			celula.setCellValue((Double) value);
			adicionaCurrencyStyle(celula);
		}else if(value instanceof Date){
			Date data = (Date) value;
			celula = row.createCell(index);
			String dateStr = dateFormatter.format(data);
			celula.setCellValue(dateStr);
	    }else if(value instanceof Calendar){
	    	Calendar data = (Calendar) value;
			celula = row.createCell(index);
			String dateStr = dateFormatter.format(data.getTime());
			celula.setCellValue(dateStr);
	    }else{
			celula = row.createCell(index);
			celula.setCellValue(value == null ? null : value.toString());
		}
		currentCell = celula;
	}
	
	/**
	 * @param objects
	 * adiciona o cabecalho das tabelas.
	 */
	public void adicionaCabecalho( String[] columnHeaderNames ){
		adicionaCabecalhoColunas( columnHeaderNames, null);
	}
	
	/**
	 * @param objects
	 * adiciona o cabecalho das tabelas.
	 * guarda a referencia para a linha onde encontra-se o cabecalho
	 */
	public void adicionaCabecalhoColunas( String[] columnHeaderNames, Integer[] columnSizes ){
		this.columnsHeaderRowNumber = rowCount.intValue();
		adicionaLinha((Object[]) columnHeaderNames);
		
		sizes = columnSizes;
		
		if(sizes != null && sizes.length < 1){
			setColumnsSizes(sizes);
		}
	}

	/**
	 * adiciona uma linha em branco, 
	 * a linha corrente passa a ser esta.
	 */
	public void adicionaLinhaEmBranco(int num) throws IllegalArgumentException{
		for(int x = 0 ; x < num ; x++){
			currentLine = getCurrentSheet().createRow(rowCount);
			rowCount ++;
		}
	}
	
	/**
	 * adiciona a quantidade de linhas em branco passada por parametro, 
	 * a linha corrente passa a ser esta.
	 */
	public void adicionaLinhaEmBranco() throws IllegalArgumentException{
		adicionaLinhaEmBranco(1);
	}
	
	/**
	 * @param objects
	 * adiciona a lista de objetos passado na linha corrente,
	 * caso esta nao exista, ser� criada.
	 */
	public void adicionaLinha( Object...objects ){
		adicionaLinha(rowCount, objects);
	}
	
	public void adicionaLinha(int rowIndex, Object...objects ){
		currentLine = getCurrentSheet().createRow(rowIndex);
		for (int i = 0; i < objects.length; i++) {
			Object obj = objects[i];
			adicionaCelula(rowIndex, i, obj);
		}
		rowCount ++;
	}
	
	/**
	 * @param values (MAP<integer,integer>)
	 * key do map define a coluna e o value define a quantidade de caracteres
	 * definidora do tamanho da coluna
	 */
	public void setColumnsSize(Map<Integer, Integer> values){
		for(Integer key : values.keySet()){
			getCurrentSheet().setColumnWidth(key, values.get(key) * 256);
		}
	}
	
	/**
	 * @param values 
	 * adiciona na ordem passada no array, a largura das colunas
	 */
	public void setColumnsSizes(Integer... columnSizes) {
		Map<Integer, Integer> sizes = new HashMap<Integer, Integer>();
		for (int i = 0; i < columnSizes.length; i++) {
			sizes.put(i, columnSizes[i]);
		}
		setColumnsSize(sizes);
	}
	
	/**
	 * @param list
	 * pega os valores dos campos via reflection e joga na tabela do excel.
	 * caso o object passado na lista tenha referencia a outros objetos,
	 * ao passar o valor para a coluna, ser� chamado o <code>toString()</code>, 
	 * para especificar valores utilizar o m�todo
	 * <code>addList(List<?> list, ExcelRowMapper rowMapper)</code>
	 */
	public void addList(List<?> list){
		try {
			List<Object[]> objects = ReflectionUtil.extractValuesWithAnnotation(list, ColunaPlanilha.class);
			
			for (Object[] obj : objects) {
				adicionaLinha(obj);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	/**
	 * pega a lista e retira as linhas baseado no mapeamento realizado pela classe
	 * implementadora de <code>ExcelRowMapper</code>
	 * @param list
	 *  a lista de objetos a ser jogado nas linhas da planilha
	 * @param rowMapper
	 *  o mapeamento da linha de acordo com o objeto.
	 */
	public void addList(List<?> list, ExcelRowMapper rowMapper){
        if(rowMapper != null) {
            ExcelRowMapperExtract extract = new ExcelRowMapperExtract(rowMapper);
            List<Object[]> objects = extract.extract(list);
            for (Object[] obj : objects) {
                adicionaLinha(obj);
            }
        }else{
            addList(list);
        }
	}
	
	/**
	 * estiliza o cabe�alho das colunas
	 */
	private void adicionaEstiloPadraoCabecalho() {
		CellStyle estilo = getTitleStyle();
		
		int lastColumnIndex = getColumnsHeaderLine().getLastCellNum();
		
		for(int x = 0 ; x < lastColumnIndex; x++){
			getColumnsHeaderLine().getCell(x).setCellStyle(estilo);
		}
	}

	private CellStyle getTitleStyle() {
		CellStyle estilo = workbook.createCellStyle();
		estilo.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		estilo.setFillForegroundColor(IndexedColors.WHITE.index);  
		estilo.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.index);
		estilo.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);  
		
		Font boldFont = workbook.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldFont.setFontHeight((short) 210);
		estilo.setFont(boldFont);
		return estilo;
	}
	
	/**
	 * adiciona filtro nas colunas de acordo com 
	 * o cellrange especificado
	 * @param cellRange
	 * o intervalo de linhas/colunas onde o filtro ir� operar <br>
	 *
	 */
	public void adicionaFiltroColunas(String cellRange){
		CellRangeAddress filterCellRange = CellRangeAddress.valueOf(cellRange);
		getCurrentSheet().setAutoFilter(filterCellRange);
	}
	
	private void adicionaFiltroColunasCabecalho(){
		int lastCellNum = getColumnsHeaderLine().getLastCellNum() - 1;
		adicionaFiltroColunas(lastCellNum);
	}
	
	public void adicionaFiltroColunas(int lastColumnFilterIndex){
		String lastColumnName = ColumnName.findLetterByIndex(lastColumnFilterIndex);
		String cellRange = "A" + (getColumnsHeaderRowNumber() + 1) + ":" + lastColumnName + rowCount;
		adicionaFiltroColunas(cellRange);
	}
	
	/**
	 * Adiciona informa��es ao sum�rio da planilha.
	 * @param appName
	 * 	Nome do Sistema
	 * @param date
	 *  Data Gera��o
	 * @param author
	 *  Usu�rio que gerou
	 * @param revisionNumber
	 *  N�mero da revis�o
	 * @param subject
	 * Assunto
	 * @param title
	 * T�tulo
	 * @param keywords
	 * Palavras Chave
	 */
	public void adicionaInformacaoAoSumario(String appName, Date date, String author, String revisionNumber, String subject, String title, String keywords){
		this.workbook.createInformationProperties();
		SummaryInformation si = workbook.getSummaryInformation();
		si.setApplicationName(appName);
		si.setCreateDateTime(date);
		si.setAuthor(author);
		si.setRevNumber(revisionNumber);
		si.setSubject(subject);		
		si.setTitle(title);
		si.setKeywords(keywords);
	}
	
	/**
	 * Finaliza a planilha e gera os bytes
	 * @return
	 * @throws IOException
	 */
	public byte[] exportToBytes() throws IOException{
		finalizarPlanilha();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.workbook.write(baos);
		return baos.toByteArray();
	}
	
	/**
	 * protege todas as celulas da folha atual
	 */
	private void protectCurrentSheet(){
		protectSheet(getSheetList().indexOf(getCurrentSheet()));
	}
	
	/**
	 * protege todas as celulas da folha do index passado
	 * @param index o index da folha na lista
	 */
	public void protectSheet(int index){
		SecureRandom passwordGenerator = new SecureRandom();
		String password = new BigInteger(130, passwordGenerator).toString(32);
		HSSFSheet sheet = getSheet(index);
		sheet.protectSheet(password);
	}
	
	private void adicionaCurrencyStyle(HSSFCell cell){
		if(currencyCellFormatStyle == null){
			currencyCellFormatStyle = createCurrencyStyle();
		}
		cell.setCellStyle(currencyCellFormatStyle);
	}
	
	private HSSFCellStyle createCurrencyStyle(){
		HSSFCellStyle currencyCellFormatStyle = workbook.createCellStyle();
		CreationHelper ch = workbook.getCreationHelper();
		currencyCellFormatStyle.setDataFormat(ch.createDataFormat().getFormat("#,##0.00;\\-#,##0.00"));
		return currencyCellFormatStyle;
	}

	private void congelarCabecalho(){
		int index = getSheetList().indexOf(getCurrentSheet());
		congelarLinhas(index, getColumnsHeaderRowNumber() + 1);
	}
	
	public void congelarLinhas(Integer sheetIndex, Integer lastRowIndex){
		HSSFSheet sheet = getSheetList().get(sheetIndex);
		if(sheet != null ){
			sheet.createFreezePane(0, lastRowIndex);
		}
	}
	
	public void mesclarCelulas(int firstRow, int lastRow , int firstColumn,   int lastColumn ){
		CellRangeAddress cellRange = new CellRangeAddress(firstRow, lastRow, firstColumn, lastColumn);
		getCurrentSheet().addMergedRegion( cellRange  );
	}
	
	private void finalizarPlanilha(){
		if(isAdicionarFiltroColunasCabecalho()){
			adicionaFiltroColunasCabecalho();
		}
		if(isProtegerPlanilha()){
			protectCurrentSheet();
		}
		if(isAdicionarEstiloPadraoCabecalho()){
			adicionaEstiloPadraoCabecalho();
		}
		if(isCongelarCabecalho()){
			congelarCabecalho();
		}
	}
	
	/**
	 * @return linha onde encontra-se o cabecalho das colunas
	 */
	public HSSFRow getColumnsHeaderLine(){
		return getCurrentSheet().getRow(getColumnsHeaderRowNumber());
	}
	
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public List<HSSFSheet> getSheetList() {
		return sheetList;
	}

	public Integer getRowCount() {
		return rowCount;
	}
	
	public Integer getColumnCount(){
		return getCurrentSheet().getLastRowNum();
	}

	public HSSFRow getCurrentLine() {
		return currentLine;
	}
	
	public void setCurrentSheet(HSSFSheet currentSheet) {
		this.currentSheet = currentSheet;
	}
	
	public HSSFSheet getCurrentSheet() {
		return currentSheet;
	}

	public HSSFCell getCurrentCell() {
		return currentCell;
	}

	public void setCurrentCell(HSSFCell currentCell) {
		this.currentCell = currentCell;
	}
	
	public Integer getColumnsHeaderRowNumber() {
		return columnsHeaderRowNumber;
	}
	
	public boolean isAdicionarFiltroColunasCabecalho() {
		return adicionarFiltroColunasCabecalho;
	}

	public void setAdicionarFiltroColunasCabecalho(boolean addFilterToHeaderColumns) {
		this.adicionarFiltroColunasCabecalho = addFilterToHeaderColumns;
	}

	public boolean isProtegerPlanilha() {
		return protegerPlanilha;
	}

	public void setProtegerPlanilha(boolean protegerPlanilha) {
		this.protegerPlanilha = protegerPlanilha;
	}

	public boolean isAdicionarEstiloPadraoCabecalho() {
		return adicionarEstiloPadraoCabecalho;
	}

	public void setAdicionarEstiloPadraoCabecalho(
			boolean adicionaEstiloPadraoCabecalho) {
		this.adicionarEstiloPadraoCabecalho = adicionaEstiloPadraoCabecalho;
	}

	public boolean isCongelarCabecalho() {
		return congelarCabecalho;
	}

	public void setCongelarCabecalho(boolean congelarCabecalho) {
		this.congelarCabecalho = congelarCabecalho;
	}

	public HSSFCellStyle getCurrencyCellFormatStyle() {
		return currencyCellFormatStyle;
	}

	public void setCurrencyCellFormatStyle(HSSFCellStyle currencyCellFormatStyle) {
		this.currencyCellFormatStyle = currencyCellFormatStyle;
	}

	public void setDatePattern(String datePattern) {
		String oldPattern = dateFormatter.toPattern();
		try {
			dateFormatter.applyPattern(datePattern);
		} catch(IllegalArgumentException e){
			e.printStackTrace();
			dateFormatter.applyPattern(oldPattern);
			return;
		}
		
		this.datePattern = datePattern;
	}
	
	public String getDatePattern() {
		return datePattern;
	}
	
	/**
	 * Pegar a letra do t�tulo da folha ou o index
	 * @author dougllas.sousa
	 */
	public enum ColumnName{
		
		A(0,"A"),		B(1,"B"),
		C(2,"C"),		D(3,"D"),
		E(4,"E"),		F(5,"F"),
		G(6,"G"),		H(7,"H"),
		I(8,"I"),		J(9,"J"),
		K(10,"K"),		L(11,"L"),
		M(12,"M"),		N(13,"N"),
		O(14,"O"),		P(15,"P"),
		Q(16,"Q"),		R(17,"R"),
		S(18,"S"),		T(19,"T"),
		U(20,"U"),		V(21,"V"),
		W(22,"W"),		X(23,"X"),
		Y(24,"Y"),		Z(25,"Z");
		
		private Integer index;
		private String letter;
		
		private ColumnName(Integer index, String letter) {
			this.index = index;
			this.letter = letter;
		}
		public Integer getIndex() {
			return index;
		}
		public String getLetter() {
			return letter;
		}
		public static String findLetterByIndex(Integer index){
			return getColumn(index);
		}
		
		private static String getColumn(int column){
			if (column >= 0 && column < 26) {
				return values()[column].getLetter();
			} else if (column > 25) {
				return values()[(column / 26) - 1].getLetter() + 
						values()[column % 26].getLetter();
			} else
				return null;
		}
	}
}