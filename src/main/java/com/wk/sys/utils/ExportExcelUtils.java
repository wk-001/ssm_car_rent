package com.wk.sys.utils;

import com.wk.car.pojo.BusCustomer;
import com.wk.car.pojo.BusRent;
import com.wk.sys.pojo.SysUser;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExportExcelUtils {

	/**
	 * 导出客户信息
	 * @param list
	 * @param sheetName
	 * @return
	 */
	public static ByteArrayOutputStream exportCustomer(List<BusCustomer> list, String sheetName) {
		//组装excel文档
		//创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//大标题
		HSSFCellStyle titleStyle = ExcelUtils.titleStyle(workbook);
		//二级标题
		HSSFCellStyle subTitleStyle = ExcelUtils.subTitleStyle(workbook);
		//表头
		HSSFCellStyle tableTitleStyle = ExcelUtils.tableTitleStyle(workbook);
		
		HSSFCellStyle baseStyle = ExcelUtils.baseStyle(workbook);
		
		//在工作簿中创建sheet
		HSSFSheet sheet = workbook.createSheet(sheetName);

		//设置sheet
		sheet.setDefaultColumnWidth(15);

		//合并
		CellRangeAddress region1 = new CellRangeAddress(0,0,0,6);
		CellRangeAddress region2 = new CellRangeAddress(1,1,0,6);
		sheet.addMergedRegion(region1);
		sheet.addMergedRegion(region2);

		int row = 0;
		//第一行 大标题
		HSSFRow row1 = sheet.createRow(row);
		//在第一行创建单元格
		HSSFCell row1_cell1 = row1.createCell(0);
		//设置第一行的样式
		row1_cell1.setCellStyle(titleStyle);
		//设置标题内容
		row1_cell1.setCellValue("客户数据");

		//第二行 二级标题
		row++;
		HSSFRow row2 = sheet.createRow(row);
		HSSFCell row2_cell1 = row2.createCell(0);
		row2_cell1.setCellStyle(subTitleStyle);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		row2_cell1.setCellValue("数据总条数："+list.size()+" 导出时间："+df.format(new Date()));

		//第三行 表头
		String[] titles = {"身份证","姓名","性别","地址","电话","职位","注册时间"};
		row++;
		HSSFRow row3 = sheet.createRow(row);
		for (int i = 0; i < titles.length; i++) {
			//创建单元格
			HSSFCell cell = row3.createCell(i);
			//设置表头单元格内容
			cell.setCellValue(titles[i]);
			cell.setCellStyle(tableTitleStyle);
		}


		//第四行表格数据
		for (BusCustomer customer : list) {
			row++;
			HSSFRow rowx = sheet.createRow(row);
			HSSFCell cell1 = rowx.createCell(0);
			cell1.setCellValue(customer.getIdentity());
			cell1.setCellStyle(baseStyle);

			HSSFCell cell2 = rowx.createCell(1);
			cell2.setCellValue(customer.getCustname());
			cell2.setCellStyle(baseStyle);

			HSSFCell cell3 = rowx.createCell(2);
			cell3.setCellValue(customer.getSex() == 1 ? "男" : "女");
			cell3.setCellStyle(baseStyle);

			HSSFCell cell4 = rowx.createCell(3);
			cell4.setCellValue(customer.getAddress());
			cell4.setCellStyle(baseStyle);

			HSSFCell cell5 = rowx.createCell(4);
			cell5.setCellValue(customer.getPhone());
			cell5.setCellStyle(baseStyle);

			HSSFCell cell6 = rowx.createCell(5);
			cell6.setCellValue(customer.getCareer());
			cell6.setCellStyle(baseStyle);

			HSSFCell cell7 = rowx.createCell(6);
			cell7.setCellValue(customer.getCreatetime());
			cell7.setCellStyle(baseStyle);
		}
		//excel表格数据组装完毕

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		//把workbook中的内容写到stream中
		try {
			workbook.write(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream;
	}

	/**
	 * 导出出租单信息
	 * @param rent
	 * @param customer
	 * @param sheetName
	 * @return
	 */
	public static ByteArrayOutputStream exportRent(BusRent rent, BusCustomer customer, String sheetName) {
		//组装excel文档
		//创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//大标题
		HSSFCellStyle titleStyle = ExcelUtils.titleStyle(workbook);
		//垂直居中 水平居中
		HSSFCellStyle baseStyle = ExcelUtils.baseStyle(workbook);

		//在工作簿中创建sheet
		HSSFSheet sheet = workbook.createSheet(sheetName);

		//设置sheet
		sheet.setDefaultColumnWidth(30);
		//设置第二列的列宽，从0开始计数
		sheet.setColumnWidth(1,50*256);

		//合并
		CellRangeAddress region1 = new CellRangeAddress(0,0,0,3);
		sheet.addMergedRegion(region1);

		int row = 0;
		//第一行 大标题
		HSSFRow row1 = sheet.createRow(row);
		//在第一行创建单元格
		HSSFCell row1_cell1 = row1.createCell(0);
		//设置第一行的样式
		row1_cell1.setCellStyle(titleStyle);
		//设置标题内容
		row1_cell1.setCellValue(customer.getCustname()+"的出租单信息");

		ZXingCodeUtil.createCodeWithLogo(rent.getRentid(),rent.getCarnumber());

		//第二行 
		row++;
		HSSFRow row2 = sheet.createRow(row);
		//设置行高
		row2.setHeightInPoints(150);
		//第二行 第一个格子
		HSSFCell row2_cell1 = row2.createCell(0);
		row2_cell1.setCellStyle(baseStyle);
		row2_cell1.setCellValue("出租单号：");
		//第二行 第二个格子
		HSSFCell row2_cell2 = row2.createCell(1);
		row2_cell2.setCellStyle(baseStyle);
		row2_cell2.setCellValue(rent.getRentid());
		//第二行 第三个格子
		HSSFCell row2_cell3 = row2.createCell(2);
		row2_cell3.setCellStyle(baseStyle);
		row2_cell3.setCellValue("二维码：");
		//第二行 第四个格子
		HSSFCell row2_cell4 = row2.createCell(3);
		row2_cell4.setCellStyle(baseStyle);
		row2_cell4.setCellValue("");

		//画图片
		BufferedImage image = ZXingCodeUtil.createCode(rent.getRentid());

		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "JPEG", bos);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

		/**
		 * 参数4 设置图片的平铺程度 最大值是255  255代表铺满当前单元格  小于255就铺不满
		 * 参数5 列的开始坐标
		 * 参数6 行的开始坐标
		 * 参数7 列的结束坐标
		 * 参数8 行的结束坐标
		 */
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 255,(short) 3, 1, (short) 4, 1);
		anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
		patriarch.createPicture(anchor, workbook.addPicture(bos.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

		//第三行 
		row++;
		HSSFRow row3 = sheet.createRow(row);
		//第三行 第一个格子
		HSSFCell row3_cell1 = row3.createCell(0);
		row3_cell1.setCellStyle(baseStyle);
		row3_cell1.setCellValue("客户姓名：");
		//第三行 第二个格子
		HSSFCell row3_cell2 = row3.createCell(1);
		row3_cell2.setCellStyle(baseStyle);
		row3_cell2.setCellValue(customer.getCustname());
		//第三行 第三个格子
		HSSFCell row3_cell3 = row3.createCell(2);
		row3_cell3.setCellStyle(baseStyle);
		row3_cell3.setCellValue("客户身份证号：");
		//第三行 第四个格子
		HSSFCell row3_cell4 = row3.createCell(3);
		row3_cell4.setCellStyle(baseStyle);
		row3_cell4.setCellValue(rent.getIdentity());

		//第四行 
		row++;
		HSSFRow row4 = sheet.createRow(row);
		//第四行 第一个格子
		HSSFCell row4_cell1 = row4.createCell(0);
		row4_cell1.setCellStyle(baseStyle);
		row4_cell1.setCellValue("起租时间：");
		//第四行 第二个格子
		HSSFCell row4_cell2 = row4.createCell(1);
		row4_cell2.setCellStyle(baseStyle);
		row4_cell2.setCellValue(rent.getBegindate().toLocaleString());
		//第四行 第三个格子
		HSSFCell row4_cell3 = row4.createCell(2);
		row4_cell3.setCellStyle(baseStyle);
		row4_cell3.setCellValue("还车时间：");
		//第四行 第四个格子
		HSSFCell row4_cell4 = row4.createCell(3);
		row4_cell4.setCellStyle(baseStyle);
		row4_cell4.setCellValue(rent.getReturndate().toLocaleString());

		//第五行 
		row++;
		HSSFRow row5 = sheet.createRow(row);
		//第五行 第一个格子
		HSSFCell row5_cell1 = row5.createCell(0);
		row5_cell1.setCellStyle(baseStyle);
		row5_cell1.setCellValue("车辆编号：");
		//第五行 第二个格子
		HSSFCell row5_cell2 = row5.createCell(1);
		row5_cell2.setCellStyle(baseStyle);
		row5_cell2.setCellValue(rent.getCarnumber());
		//第五行 第三个格子
		HSSFCell row5_cell3 = row5.createCell(2);
		row5_cell3.setCellStyle(baseStyle);
		row5_cell3.setCellValue("出租价格：");
		//第五行 第四个格子
		HSSFCell row5_cell4 = row5.createCell(3);
		row5_cell4.setCellStyle(baseStyle);
		row5_cell4.setCellValue(rent.getPrice());

		//第六行 空行
		row++;

		//第七行 
		row++;
		HSSFRow row7 = sheet.createRow(row);
		//第七行 第三个格子
		HSSFCell row7_cell3 = row7.createCell(2);
		row7_cell3.setCellStyle(baseStyle);
		row7_cell3.setCellValue("打印时间：");
		//第七行 第四个格子
		HSSFCell row7_cell4 = row7.createCell(3);
		row7_cell4.setCellStyle(baseStyle);
		row7_cell4.setCellValue(new Date().toLocaleString());

		//第八行 
		row++;
		HSSFRow row8 = sheet.createRow(row);
		//第八行 第三个格子
		HSSFCell row8_cell3 = row8.createCell(2);
		row8_cell3.setCellStyle(baseStyle);
		row8_cell3.setCellValue("操作员：");
		//第八行 第四个格子
		HSSFCell row8_cell4 = row8.createCell(3);
		row8_cell4.setCellStyle(baseStyle);
		row8_cell4.setCellValue(rent.getOpername());


		//第九行 
		row++;
		HSSFRow row9 = sheet.createRow(row);
		//第九行 第三个格子
		HSSFCell row9_cell3 = row9.createCell(2);
		row9_cell3.setCellStyle(baseStyle);
		row9_cell3.setCellValue("客户签名：");

		//excel组装完毕

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		//把workbook中的内容写到stream中
		try {
			workbook.write(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream;
	}
}
