package com.wk.sys.utils;

import com.wk.sys.pojo.SysUser;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtils {

	/**
	 *  Excel导出
	 * @param list	要导出的数据
	 * @param path	文件路径
	 */
	public static void export(List list,String path){
		//1.创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//2.在工作簿中创建一个sheet并取名，如果不加参数使用默认名字
		HSSFSheet sheet = workbook.createSheet("数据");
		//3.sheet的相关设置
		//sheet.setColumnHidden(1,true);	//设置某一列是否隐藏
		//sheet.setColumnWidth(4,20*256);	//设置某一列的宽度
		sheet.setDefaultColumnWidth(15);					//设置默认列宽
		//sheet.setDefaultRowHeight((short)(20*20));			//设置默认行高

		//合并单元格 参数：合并开始行、结束行、开始列、结束列
		CellRangeAddress cellAddresses = new CellRangeAddress(0,0,0,4);
		sheet.addMergedRegion(cellAddresses);
		CellRangeAddress cellAddresses1 = new CellRangeAddress(1,1,0,4);
		sheet.addMergedRegion(cellAddresses1);

		//表格标题样式
		HSSFCellStyle tableTitleStyle = titleStyle(workbook);

		//4.在sheet中创建行
		int row = 0;
		HSSFRow row1 = sheet.createRow(row);
		//在这一行里面创建单元格
		HSSFCell row1_cell1 = row1.createCell(0);
		//往单元格中添加数据
		row1_cell1.setCellValue("用户数据");
		row1_cell1.setCellStyle(tableTitleStyle);

		//创建小标题样式
		HSSFCellStyle subTitleStyle = subTitleStyle(workbook);

		//表头第二行
		row++;
		HSSFRow row2 = sheet.createRow(row);
		HSSFCell row2_cell2 = row2.createCell(0);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		row2_cell2.setCellValue("数据总条数："+list.size()+" 导出时间："+df.format(new Date()));
		row2_cell2.setCellStyle(subTitleStyle);

		//创建表头样式
		HSSFCellStyle titleStyle = tableTitleStyle(workbook);

		//表头第三行
		String[] titles = {"编号","姓名","性别","电话","密码"};
		row++;
		HSSFRow row3 = sheet.createRow(row);
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row3.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(titleStyle);
		}

		//创建基础样式
		HSSFCellStyle style = baseStyle(workbook);

		//表格数据
		for (int i = 0; i < list.size(); i++) {
			SysUser user = (SysUser) list.get(i);
			row++;
			HSSFRow rowx = sheet.createRow(row);
			HSSFCell cell1 = rowx.createCell(0);
			cell1.setCellValue(user.getUserid());
			cell1.setCellStyle(style);

			HSSFCell cell2 = rowx.createCell(1);
			cell2.setCellValue(user.getRealname());
			cell2.setCellStyle(style);

			HSSFCell cell3 = rowx.createCell(2);
			cell3.setCellValue(user.getSex()==1?"男":"女");
			cell3.setCellStyle(style);

			HSSFCell cell4 = rowx.createCell(3);
			cell4.setCellValue(user.getPhone());
			cell4.setCellStyle(style);

			HSSFCell cell5 = rowx.createCell(4);
			cell5.setCellValue(user.getPwd());
			cell5.setCellStyle(style);

		}

		//导出 保存到本地硬盘
		File file = new File(path);
		try {
			File fileParent = file.getParentFile();
			if(!fileParent.exists()){
				fileParent.mkdirs();
			}
			file.createNewFile();
			workbook.write(file);
			System.out.println("导出成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建基础样式：水平居中、垂直居中
	 * @param workbook
	 */
	public static HSSFCellStyle baseStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		//设置水平居中
		style.setAlignment(HorizontalAlignment.CENTER);
		//设置垂直居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		return style;
	}

	/**
	 * 数据表格头的样式
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle tableTitleStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = baseStyle(workbook);
		//创建字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());	//字体颜色
		font.setBold(true);				//加粗
		font.setItalic(true);			//倾斜
		font.setFontHeightInPoints((short)20);		//字体高度
		font.setFontName("黑体");			//字体
		style.setFont(font);
		return style;
	}

	/**
	 * 小标题样式
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle subTitleStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = baseStyle(workbook);
		//创建字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());	//字体颜色
		font.setBold(true);				//加粗
		font.setFontHeightInPoints((short)18);		//字体高度
		font.setFontName("黑体");			//字体
		style.setFont(font);
		return style;
	}

	/**
	 * 大标题样式
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle titleStyle(HSSFWorkbook workbook){
		HSSFCellStyle style = baseStyle(workbook);
		//创建字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());	//字体颜色
		font.setBold(true);				//加粗
		font.setFontHeightInPoints((short)25);		//字体高度
		font.setFontName("黑体");			//字体
		style.setFont(font);
		return style;
	}

	public static void main(String[] args) {
		List<SysUser> list = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			SysUser user = new SysUser();
			user.setUserid(i);
			user.setRealname("张三"+i);
			user.setSex(i%2);
			user.setPhone("123456"+i);
			user.setPwd("123456"+i);
			list.add(user);
		}

		export(list,"D:/excel/test1.xls");
	}

}
