package com.wk;

import com.wk.sys.utils.ZXingCodeUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class NormalTest {

	@Test
	public void qrcodeTest() throws IOException {
		String qrUrl = "http://www.bilibili.com";
		String destFile = "D:\\qrcode\\" + new Date().getTime() + ".png";
		String logoPath = "D:\\qrcode\\cat.jpg";
		String text = "哔哩哔哩";

		//生成普通二维码，文件夹必须存在
		BufferedImage bim = ZXingCodeUtil.createCode(qrUrl);
		/*ImageIO.write(bim, "png", new File(destFile));

		bim = ZXingCodeUtil.createCodeWithLogo(qrUrl,logoPath);
		ImageIO.write(bim, "png", new File(destFile));*/

		bim = ZXingCodeUtil.createCodeWithLogoAndText(qrUrl,logoPath,text);
		ImageIO.write(bim, "png", new File(destFile));

		System.out.println("done");
	}

}
