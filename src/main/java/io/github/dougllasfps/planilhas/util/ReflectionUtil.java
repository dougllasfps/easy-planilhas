package io.github.dougllasfps.planilhas.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.github.dougllasfps.planilhas.annotation.ColunaPlanilha;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.ReflectionUtils;

public class ReflectionUtil {

	private static final String SERIAL_VERSION_UID = "serialVersionUID";

	/**
	 * @param obj
	 * @return cabecalho das colunas dos campos que possuem a annotation  @ColunaPlanilha
	 */ 
	public static String[] gerarCabecalhosColunas(Class<?> obj){
		Util.notNull(obj);
		
		List<String> cabecalhos = new ArrayList<String>();
		Field[] fields = obj.getDeclaredFields();
		
		for (Field field : fields) {
			boolean annotationPresent = field.isAnnotationPresent(ColunaPlanilha.class);
			if(!annotationPresent){
				continue;
			}
			cabecalhos.add(field.getAnnotation(ColunaPlanilha.class).cabecalho());
		}
		
		Method[] declaredMethods = obj.getDeclaredMethods();
		
		for (Method method : declaredMethods) {
			boolean annotationPresent = method.isAnnotationPresent(ColunaPlanilha.class);
			if(!annotationPresent){
				continue;
			}
			cabecalhos.add(method.getAnnotation(ColunaPlanilha.class).cabecalho());
		}
		
		return cabecalhos.toArray(new String[cabecalhos.size()]);
	}
	
	/**
	 * @param obj
	 * @return cabecalho das colunas dos campos que possuem a annotation  @ColunaPlanilha
	 */ 
	public static Integer[] gerarLargurasColunas(Class<?> obj){
		Util.notNull(obj);
		
		List<Integer> cabecalhos = new ArrayList<Integer>();
		Field[] fields = obj.getDeclaredFields();
		
		for (Field field : fields) {
			boolean annotationPresent = field.isAnnotationPresent(ColunaPlanilha.class);
			if(!annotationPresent){
				continue;
			}
			cabecalhos.add(field.getAnnotation(ColunaPlanilha.class).tamanho());
		}
		
		Method[] declaredMethods = obj.getDeclaredMethods();
		
		for (Method method : declaredMethods) {
			boolean annotationPresent = method.isAnnotationPresent(ColunaPlanilha.class);
			if(!annotationPresent){
				continue;
			}
			cabecalhos.add(method.getAnnotation(ColunaPlanilha.class).tamanho());
		}
		return cabecalhos.toArray(new Integer[cabecalhos.size()]);
	}
	
	/**
	 * @param object
	 * @return
	 * 		um array de object com todos os valores de todos os campos de um objeto, exceto o serialVersionUID
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object[] getFieldValues(Object object) throws IllegalArgumentException, IllegalAccessException{
		Field[] fields = object.getClass().getDeclaredFields();
		Object[] fieldValues = new Object[fields.length];
		for (int x = 0 ; x < fields.length; x++) {
			Field field = fields[x];
			field.setAccessible(true);
			if(!field.getName().equals(SERIAL_VERSION_UID) ){
				fieldValues[x - 1 ] = field.get(object);
			}
		}
		return fieldValues;
	}
	
	/**
	 * @param object
	 * @return
	 * 		um array de object com todos os valores de todos os campos de um objeto, exceto o serialVersionUID
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object[] getMethodValues(Object object) throws IllegalArgumentException, IllegalAccessException{
		Field[] fields = object.getClass().getDeclaredFields();
		Object[] fieldValues = new Object[fields.length];
		for (int x = 0 ; x < fields.length; x++) {
			Field field = fields[x];
			field.setAccessible(true);
			if(!field.getName().equals(SERIAL_VERSION_UID) ){
				fieldValues[x - 1 ] = field.get(object);
			}
		}
		return fieldValues;
	}
	
	/**
	 * @param objects
	 * @return
	 * 	uma lista de array de object com todos os valores de todos os campos de um objeto, exceto o serialVersionUID
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static List<Object[]> extractValues(List<?> objects) throws IllegalArgumentException, IllegalAccessException{
		List<Object[]> list = new ArrayList<Object[]>();
		for (Object object : objects) {
			Object[] fields = getFieldValues(object);
			list.add(fields);
		}
		return list;
	}
	
	/**
	 * @param objects
	 * @param annotation
	 * @return
	 * 	 uma lista de array de object com todos os valores de todos os campos de um objeto contendo a anotacao passada
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException 
	 */
	@SuppressWarnings("rawtypes")
	public static List<Object[]> extractValuesWithAnnotation(List<?> objects,  Class annotation) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		List<Object[]> list = new ArrayList<Object[]>();
		
		for (Object object : objects) {
			
			Object[] fields = null;
			if(annotation != null){
				fields = getFieldValuesWithAnnotation(object, annotation);
				Object[] methodValuesWithAnnotation = getMethodValuesWithAnnotation(object, annotation);
				fields = ArrayUtils.addAll(methodValuesWithAnnotation, fields);
			}else{
				fields = getFieldValues(object);
			}
			
			list.add(fields);
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Object[]> extractValuesFromMethodsWithAnnotation(List<?> objects,  Class annotation) throws IllegalArgumentException, IllegalAccessException{
		List<Object[]> list = new ArrayList<Object[]>();
		
		for (Object object : objects) {
			
			Object[] fields = null;
			if(annotation != null){
				fields = getFieldValuesWithAnnotation(object, annotation);
			}else{
				fields = getFieldValues(object);
			}
			
			list.add(fields);
		}
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object[] getFieldValuesWithAnnotation(Object object, Class annotation) throws IllegalArgumentException, IllegalAccessException{
		Field[] fields = object.getClass().getDeclaredFields();
		int size = extractSizeWithAnnotation(annotation, fields);
		
		Object[] fieldValues = new Object[size];
		
		int countFieldValues = 0;
		
		for (int objectFieldsIndex = 0 ; objectFieldsIndex < size; objectFieldsIndex++) {
			
			Field field = fields[objectFieldsIndex];
			field.setAccessible(true);
			
			if(annotation !=null && field.isAnnotationPresent(annotation)){
				fieldValues[countFieldValues] = field.get(object);
				countFieldValues++;
			}
		}
		return fieldValues;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object[] getMethodValuesWithAnnotation(Object object, Class annotation) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Method[] methods = object.getClass().getDeclaredMethods();
		int size = extractSizeWithAnnotation(annotation, methods);
		
		Object[] fieldValues = new Object[size];
		
		int countFieldValues = 0;
		
		for (int objectFieldsIndex = 0 ; countFieldValues < size; objectFieldsIndex++) {
			
			Method m = methods[objectFieldsIndex];
			m.setAccessible(true);
			
			if(annotation !=null && m.isAnnotationPresent(annotation)){
				fieldValues[countFieldValues] = ReflectionUtils.invokeMethod(m, object);
				countFieldValues++;
			}
		}
		return fieldValues;
	}

	@SuppressWarnings("unchecked")
	private static int extractSizeWithAnnotation(@SuppressWarnings("rawtypes") Class annotation, Field[] fields) {
		int size = 0;
		
		for (int x = 0 ; x < fields.length; x++) {
			
			Field field = fields[x];
			field.setAccessible(true);
			
			if(annotation != null && field.isAnnotationPresent(annotation)){
				size++;
			}
		}
		return size;
	}
	
	private static int extractSizeWithAnnotation(@SuppressWarnings("rawtypes") Class annotation, Method[] methods) {
		int size = 0;
		
		for (int x = 0 ; x < methods.length; x++) {
			
			Method m = methods[x];
			m.setAccessible(true);
			
			if(annotation != null && m.isAnnotationPresent(annotation)){
				size++;
			}
		}
		return size;
	}
}
