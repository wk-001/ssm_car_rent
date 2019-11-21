package com.wk.sys.controller;

import com.wk.sys.constast.SysConstast;
import com.wk.sys.utils.AppFileUtils;
import com.wk.sys.utils.DataGrid;
import com.wk.sys.utils.RandomUtils;
import com.wk.sys.utils.ZXingCodeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

}
