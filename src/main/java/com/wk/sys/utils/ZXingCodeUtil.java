package com.wk.sys.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import jdk.internal.util.xml.impl.Input;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码生成工具类
 */
public class ZXingCodeUtil {

	// 二维码颜色
	private static final int QRCOLOR = 0xFF000000;
	// 背景色
	private static final int BGCOLOR = 0xFFFFFFFF;
	// 二维码宽度
	private static final int width = 150;
	// 二维码高度
	private static final int height = 150;

	/**
	 * @Description: 生成普通的二维码
	 * @Date:
	 */
	public static BufferedImage createCode(String qrContent) {
		MultiFormatWriter multiFormatWriter = null;
		BitMatrix bm = null;
		BufferedImage image = null;
		Map<EncodeHintType, Object> hints = getDecodeHintType();
		try {
			multiFormatWriter = new MultiFormatWriter();
			// 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
			bm = multiFormatWriter.encode(qrContent, BarcodeFormat.QR_CODE, width, height, hints);
			//得到生成的二维码高度和宽度
			int w = bm.getWidth();
			int h = bm.getHeight();
			//创建图片 参数：图片宽、高、颜色类型
			image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

			// 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {
					image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGCOLOR);
				}
			}
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * 根据本地图片生成带logo的二维码
	 * @param qrContent	二维码内容
	 * @param logoPath	文件路径
	 * @return
	 */
	public static BufferedImage createCodeWithLogo(String qrContent, String logoPath) {
		//先生成一个普通的二维码
		BufferedImage bim = createCode(qrContent);
		try {
			// 读取二维码图片，并构建绘图对象
			BufferedImage image = bim;
			//拿到可以操作当前图片的画笔
			Graphics2D g = image.createGraphics();

			// 读取Logo图片
			BufferedImage logo = ImageIO.read(new File(logoPath));
			//设置logo的大小,这里设置为二维码图片的20%,过大会盖掉二维码
			int widthLogo = logo.getWidth(null) > image.getWidth() * 3 / 10 ? (image.getWidth() * 3 / 10) : logo.getWidth(null),
					heightLogo = logo.getHeight(null) > image.getHeight() * 3 / 10 ? (image.getHeight() * 3 / 10) : logo.getWidth(null);

			// logo放在中心
			int x = (image.getWidth() - widthLogo) / 2;
			int y = (image.getHeight() - heightLogo) / 2;
			// logo放在右下角
			// int x = (image.getWidth() - widthLogo);
			// int y = (image.getHeight() - heightLogo);

			//开始绘制图片，参数：logo图片对象，X轴坐标，Y轴坐标，logo宽度，logo高度
			g.drawImage(logo, x, y, widthLogo, heightLogo, null);
			//边框大小
			/*g.setStroke(new BasicStroke(2));
			g.setColor(Color.white);
			//白色圆角边框
			g.drawRoundRect(x,y,widthLogo,heightLogo,15,15);*/
			g.dispose();
			logo.flush();
			image.flush();
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description: 生成带logo和文字的二维码
	 * @Date:
	 */
	public static BufferedImage createCodeWithLogoAndText(String qrContent, String logoPath, String text) {
		BufferedImage image = createCodeWithLogo(qrContent, logoPath);
		//把文字添加上去，文字不要太长，这里最多支持两行。太长就会自动截取啦
		try {
			if (text != null && !text.equals("")) {
				//新的图片，把带logo的二维码下面加上文字
				BufferedImage outImage = new BufferedImage(400, 445, BufferedImage.TYPE_4BYTE_ABGR);
				Graphics2D outg = outImage.createGraphics();
				//画二维码到新的面板
				outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
				//画文字到新的面板
				outg.setColor(Color.BLACK);
				outg.setFont(new Font("宋体", Font.BOLD, 30)); //字体、字型、字号
				int strWidth = outg.getFontMetrics().stringWidth(text);
				if (strWidth > 399) {
//                  //长度过长就截取前面部分
//                  outg.drawString(productName, 0, image.getHeight() + (outImage.getHeight() - image.getHeight())/2 + 5 ); //画文字
					//长度过长就换行
					String productName1 = text.substring(0, text.length() / 2);
					String productName2 = text.substring(text.length() / 2, text.length());
					int strWidth1 = outg.getFontMetrics().stringWidth(productName1);
					int strWidth2 = outg.getFontMetrics().stringWidth(productName2);
					outg.drawString(productName1, 200 - strWidth1 / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 12);
					BufferedImage outImage2 = new BufferedImage(400, 485, BufferedImage.TYPE_4BYTE_ABGR);
					Graphics2D outg2 = outImage2.createGraphics();
					outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
					outg2.setColor(Color.BLACK);
					outg2.setFont(new Font("宋体", Font.BOLD, 30)); //字体、字型、字号
					outg2.drawString(productName2, 200 - strWidth2 / 2, outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight()) / 2 + 5);
					outg2.dispose();
					outImage2.flush();
					outImage = outImage2;
				} else {
					outg.drawString(text, 200 - strWidth / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 12); //画文字
				}
				outg.dispose();
				outImage.flush();
				image = outImage;
				image.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}


	/**
	 * 设置二维码的格式参数
	 * @return
	 */
	private static Map<EncodeHintType, Object> getDecodeHintType() {
		// 用于设置QR二维码参数
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		/* 设置QR二维码的容错级别（H为最高级别）H:30% Q:25% M:15% L:7%
		*  容错信息存储在二维码中，容错级别越高 信息占用空间越多 能储存的有用信息越少
		*  logo也占一些比例 所以logo最大不能超过整个二维码的30%*/
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 设置放入字符的编码方式
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		//二维码边界空白大小
		hints.put(EncodeHintType.MARGIN, 0);
		hints.put(EncodeHintType.MAX_SIZE, 350);
		hints.put(EncodeHintType.MIN_SIZE, 100);
		return hints;
	}

	/**
	 * 解析二维码文件
	 */
	public static String decodeByFile(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			try {
				return decodeByFileStream(new FileInputStream(file));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}

	/**
	 * 解析二维码文件流
	 */
	public static String decodeByFileStream(InputStream stream){
		if(stream!=null){
			try {
				BufferedImage image = ImageIO.read(stream);
				BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				HashMap hints = new HashMap<DecodeHintType, Object>();
				hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
				Result result = null;
				result = new MultiFormatReader().decode(bitmap, hints);
				String resultStr = result.getText();
				return resultStr;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
