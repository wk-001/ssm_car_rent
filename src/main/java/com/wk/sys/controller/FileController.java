package com.wk.sys.controller;

import com.wk.car.pojo.BusCustomer;
import com.wk.car.pojo.BusRent;
import com.wk.car.service.BusCustomerService;
import com.wk.car.service.BusRentService;
import com.wk.sys.constast.SysConstast;
import com.wk.sys.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传下载的控制器
 * 
 * @author LJH
 *
 */
@Controller
@RequestMapping("file")
public class FileController {

	@Autowired
	private BusCustomerService customerService;

	@Autowired
	private BusRentService rentService;

	/**
	 * 上传文件，文件自动上传后的默认文件名后缀是".jpg_temp"，表示临时文件，
	 * 如果这时关闭弹窗取消上传，以后也可以根据文件后缀进行清理
	 * 点击表单的提交按钮，把文件后缀的_temp去掉就可以
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("uploadFile")
	@ResponseBody
	public DataGrid uploadFile(MultipartFile mf) throws IllegalStateException, IOException {
		// 文件上传的父目录
		String parentPath = AppFileUtils.PATH;
		// 得到当前日期作为文件夹名称
		String dirName = RandomUtils.getCurrentDateForString();
		// 构造文件夹对象
		File dirFile = new File(parentPath, dirName);
		if (!dirFile.exists()) {
			dirFile.mkdirs();// 创建文件夹
		}
		// 得到文件原名
		String oldName = mf.getOriginalFilename();
		// 根据文件原名得到新名
		String newName = RandomUtils.createFileNameUseTime(oldName, SysConstast.FILE_UPLOAD_TEMP);
		File dest = new File(dirFile, newName);
		mf.transferTo(dest);
		
		Map<String,Object> map=new HashMap<>();
		//将文件路径返回到前台
		map.put("src", dirName+"/"+newName);
		return new DataGrid(map);
		
	}
	/**
	 * 不下载只显示
	 */
	@RequestMapping("downloadShowFile")
	public ResponseEntity<Object> downloadShowFile(String path, HttpServletResponse response) {
		return AppFileUtils.downloadFile(response, path, "");	
	}
	
	/**
	 * 下载图片
	 * @param path
	 * @param response
	 * @return
	 */
	@RequestMapping("downloadFile")
	public ResponseEntity<Object> downloadFile(String path, HttpServletResponse response) {
		String oldName="";
		return AppFileUtils.downloadFile(response, path, oldName);	
	}

	/**
	 * 生成二维码
	 * @param content
	 * @param response
	 */
	@RequestMapping("createQrCode")
	public void createQrCode(String content, HttpServletResponse response) throws IOException {
		BufferedImage bim = ZXingCodeUtil.createCode(content);
		ServletOutputStream outputStream = response.getOutputStream();
		ImageIO.write(bim,"png",outputStream);
		outputStream.close();
	}

	/**
	 * 解析二维码
	 * @param mf
	 * @return
	 */
	@RequestMapping("analyzeQrcode")
	@ResponseBody
	public String analyzeQrcode(MultipartFile mf){
		try {
			String result = ZXingCodeUtil.decodeByFileStream(mf.getInputStream());
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 导出客户信息
	 * @param busCustomer
	 * @return
	 */
	@RequestMapping("exportCustomer")
	public ResponseEntity<Object> exportCustomer(BusCustomer busCustomer){
		List<BusCustomer> list = customerService.queryCustomerList(busCustomer);
		String fileName = "客户数据.xls";
		String sheetName = "客户数据";

		ByteArrayOutputStream stream = ExportExcelUtils.exportCustomer(list,sheetName);

		//创建封装响应头信息的对象
		HttpHeaders headers = getHttpHeaders(fileName);
		return new ResponseEntity<Object>(stream.toByteArray(),headers, HttpStatus.CREATED);
	}

	/**
	 * 导出出租单信息
	 * @param rentid
	 * @return
	 */
	@RequestMapping("exportRent")
	public ResponseEntity<Object> exportRent(String rentid){
		//根据出租单号查询出租单信息
		BusRent rent = rentService.getById(rentid);
		//根据身份证号查询客户信息
		BusCustomer customer = customerService.getById(rent.getIdentity());
		String fileName = customer.getCustname()+"的出租单.xls";
		String sheetName = customer.getCustname()+"的出租单数据";

		ByteArrayOutputStream stream = ExportExcelUtils.exportRent(rent,customer,sheetName);
		HttpHeaders headers = getHttpHeaders(fileName);

		return new ResponseEntity<Object>(stream.toByteArray(),headers, HttpStatus.CREATED);
	}

	//设置响应头信息
	private HttpHeaders getHttpHeaders(String fileName) {
		//创建封装响应头信息的对象
		HttpHeaders headers = new HttpHeaders();
		//封装响应内容类型 APPLICATION_OCTET_STREAM：响应的内容不限定
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//设置下载文件名
		headers.setContentDispositionFormData("attachment", fileName, Charset.forName("utf-8"));
		return headers;
	}
}
