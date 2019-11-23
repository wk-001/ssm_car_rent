package com.wk;

import com.wk.sys.utils.ZXingCodeUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class NormalTest {

	@Test
	public void createQrcode() throws IOException {
		String qrContent = "http://www.bilibili.com";
		File destFile = new File("D:\\qrcode\\" + new Date().getTime() + ".png");
		String logoPath = "D:\\qrcode\\cat.jpg";
		String text = "哔哩哔哩";

		if (!destFile.exists()) {
			destFile.mkdirs();
		}

		//生成普通二维码，文件夹必须存在
		BufferedImage bim = ZXingCodeUtil.createCode(qrContent);
//		ImageIO.write(bim, "png", destFile);


		//带图片的二维码
		bim = ZXingCodeUtil.createCodeWithLogo(qrContent,logoPath);
		ImageIO.write(bim, "png", destFile);

		/*bim = ZXingCodeUtil.createCodeWithLogoAndText(qrContent,logoPath,text);
		ImageIO.write(bim, "png", destFile);*/

		System.out.println("done");
	}

	//解析二维码
	@Test
	public void analyzeQrcode(){
		String result = ZXingCodeUtil.decodeByFile("D:\\qrcode\\1574390773739.png");
		System.out.println("result = " + result);
	}

	@Test
	public void streamTest(){

	}

}
