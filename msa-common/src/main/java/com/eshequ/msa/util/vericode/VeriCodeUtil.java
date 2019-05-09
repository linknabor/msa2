package com.eshequ.msa.util.vericode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VeriCodeUtil {

	/**
	 * 生成图片
	 */
	public static VeriCodeVO generateVeriCode() {

		// 创建一个随机数生成器类
		Random random = new Random();
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(VericodeImgCfg.WIDTH, VericodeImgCfg.HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D gd = buffImg.createGraphics();
		// 将图像填充为白色
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, VericodeImgCfg.WIDTH, VericodeImgCfg.HEIGHT);
		// 创建字体，字体的大小应该根据图片的高度来定。
		// 设置字体。
		gd.setFont(VericodeImgCfg.FONT);
		// 画边框。
		gd.setColor(VericodeImgCfg.COLOR);
		gd.drawRect(0, 0, VericodeImgCfg.WIDTH - 1, VericodeImgCfg.HEIGHT - 1);

		// 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。
		for (int i = 0; i < 10; i++) {
			int x = random.nextInt(VericodeImgCfg.WIDTH);
			int y = random.nextInt(VericodeImgCfg.HEIGHT);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;
		// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
		red = random.nextInt(255);
		green = 100;
		blue = random.nextInt(255);
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < VericodeImgCfg.CODE_COUNT; i++) {
			// 得到随机产生的验证码数字。
			String strRand = String.valueOf(VericodeImgCfg.codeSequence[random.nextInt(36)]);

			// 用随机产生的颜色将验证码绘制到图像中。
			gd.setColor(new Color(red, green, blue));
			// gd.setColor(Color.BLACK);
			gd.drawString(strRand, (i + 1) * VericodeImgCfg.LOCATION_X, VericodeImgCfg.LOCATION_Y);

			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		
		VeriCodeVO vo = new VeriCodeVO();
		vo.setVeriCode(randomCode.toString());
		vo.setBufferedImage(buffImg);
		return vo;

	}

}
