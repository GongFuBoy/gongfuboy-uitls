package com.github.gongfuboy.utils.excel;

import com.github.gongfuboy.utils.DateUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Excel文件下载工具
 * 
 * @author zhoulm18864
 * @version $Id: ExcelFileDownloadUtils.java, v 0.1 2017年3月2日 下午2:44:23 zhoulm18864 Exp $
 */
public class ExcelFileDownloadUtils {

	/**
	 * 根据resource获取HSSFWorkbook,这里的List<T>里的T的所有成员变量最好都加上Descripiton的注解,
	 * 这是为了每一个成员变量(也就是每一个sheet的列名也就是中文描述，假如不添加的话，那么就会导致，相应的列没有列名)
	 * 
	 * @param resouce 需要转化为Excel文档的resource对象，
	 * @param maxSheetNumber Excel文档中每一个sheet中最大行数(除了第一行之外)
	 * @return
	 * @throws Exception
	 */
	public static <T> void createHSSFWorkbook(List<T> resouce, int maxSheetNumber, FileOutputStream fileOutputStream) throws Exception {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		// 设置Excel文档的样式
		HSSFCellStyle style = hssfWorkbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		// 创建sheet数量
		int count = getOneSheetCount(resouce,maxSheetNumber);
		
		if (resouce != null && resouce.size() > 0) {
			for (int i = 0;i < count+1; i++) {
				List<String> titles = getTitleNames(resouce.get(0));
				HSSFSheet sheet = hssfWorkbook.createSheet("sheet" + (i+1));
				HSSFRow row = sheet.createRow(0);
				// 创建每一个sheet的第一行
				setFirstRow(row, titles, style);
				// 截取每一个sheet中需要写入的List
				List<T> targetList = ListUtils.getSubListPage(resouce, i*maxSheetNumber, maxSheetNumber);
				setContentRows(targetList, sheet, style);
			}
		}
		hssfWorkbook.write(fileOutputStream);
		IOUtils.closeQuietly(fileOutputStream);
	}
	
	/**
	 * 设置每一个sheet中的content内容
	 * 
	 * @param targetList
	 * @param sheet
	 * @param style
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private static <T> void setContentRows(List<T> targetList, HSSFSheet sheet, HSSFCellStyle style) throws IllegalArgumentException, IllegalAccessException {
		if (!Objects.equal(null, style) && !Objects.equal(null, style) && targetList != null && targetList.size() > 0) {
			for (int i=0; i < targetList.size(); i++) {
				HSSFRow row = sheet.createRow(i+1);
				Object[] fields = getFields(targetList.get(i));
				for (int j=0; j < fields.length; j++) {
					Field realField = (Field) fields[j];
					HSSFCell cell = row.createCell(j);
					cell.setCellStyle(style);
					realField.setAccessible(true);
					Object values = realField.get(targetList.get(i));
					if (!Objects.equal(null, values)) {
						if (values instanceof Date) {
							String date = DateUtils.formatDate("yyyy-MM-dd HH:mm:ss", (Date)values);
							cell.setCellValue(date);
						} else {
							cell.setCellValue(values.toString());
						}
					}
				}
			}
		}
	}
	
	private static <T> Object[] getFields(T t) {
		List<Field> resultList = Lists.newArrayList();
		Field[] fields = t.getClass().getDeclaredFields();
		for (Field tempField : fields) {
			Description description = tempField.getAnnotation(Description.class);
			if (!Objects.equal(null, description)) {
				resultList.add(tempField);
			}
		}
		return resultList.toArray();
	}
	
	/**
	 * 获取每一个sheet的第一行内容
	 * 
	 * @param t
	 * @return
	 */
	private static <T> List<String> getTitleNames(T t) {
		List<String> resultList = null;
		if (!Objects.equal(null, t)) {
			resultList = Lists.newArrayList();
			Class clazz = t.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Description description = field.getAnnotation(Description.class);
				if (!Objects.equal(null, description)) {
					resultList.add(description.value());
				}
			}
		}
		return resultList;
	}
	
	/**
	 * 设置每一个sheet的第一行
	 * 
	 * @param row
	 * @param titles
	 */
	private static void setFirstRow(HSSFRow row,List<String> titles,HSSFCellStyle style) {
		if (titles != null && titles.size() > 0 && !Objects.equal(null, row)) {
			for (int i = 0; i < titles.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(titles.get(i));
				cell.setCellStyle(style);
			}
		}
	}
	
	/**
	 * 获取每一个sheet中的数量
	 * 
	 * @param resouce
	 * @param maxSheetNumber
	 * @return
	 */
	private static <T> int getOneSheetCount(List<T> resouce,int maxSheetNumber) {
		int result = 0;
		if (resouce != null && resouce.size() > 0) {
			result = resouce.size()/maxSheetNumber;
		}
		return result;
	}
	
	/*
	public static void main(String[] args) throws Exception {
		List<Student> students = getStudents();
		HSSFWorkbook hssfWorkbook = createHSSFWorkbook(students, 2);
		FileOutputStream fout = new FileOutputStream("D:/students.xls");
		hssfWorkbook.write(fout);
		fout.close();
	}
	
	private static List<Student> getStudents() {
		List<Student> students = Lists.newArrayList();
		Student user1 = new Student(1, null, 16, new Date());
		Student user2 = new Student(2, "李四", 17, new Date());
		Student user3 = new Student(3, "王五", 26, new Date());
		students.add(user1);
		students.add(user2);
		students.add(user3);
		return students;
	}
	*/
	
}
