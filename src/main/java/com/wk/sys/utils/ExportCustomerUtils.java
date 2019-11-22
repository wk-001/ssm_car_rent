package com.wk.sys.utils;

import com.wk.car.pojo.BusCustomer;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ExportCustomerUtils {

	/**
	 * 导出客户信息
	 * @param list
	 * @param fileName
	 * @param sheetName
	 * @param response
	 * @return
	 */
	public static ResponseEntity<Object> export(List<BusCustomer> list, String fileName, String sheetName, HttpServletResponse response) {
		//组装excel文档
		//创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//大标题
		HSSFCellStyle titleStyle = ExcelUtils.titleStyle(workbook);
		//二级标题
		HSSFCellStyle subTitleStyle = ExcelUtils.subTitleStyle(workbook);
		//表头
		HSSFCellStyle tableTitleStyle = ExcelUtils.tableTitleStyle(workbook);


		//下载

		return null;
	}
}
