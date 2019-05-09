package com.eshequ.msa.util.vericode;

import java.awt.Color;
import java.awt.Font;

public class VericodeImgCfg {
	
	public static final int WIDTH = 70;
	public static final int HEIGHT = 20; 
	public static final int FONTHEIGHT = HEIGHT-2;
	public static final int CODE_COUNT = 4;	//产生的验证码个数
	public static final int LOCATION_X = WIDTH / (CODE_COUNT + 1);
	public static final int LOCATION_Y = HEIGHT - 4;
	public static Font FONT = new Font("Fixedsys", Font.PLAIN, HEIGHT-2);
	public static Color COLOR = new Color(155, 155, 155);
	
	/**
	 * codeSequence
	 */
	public static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	

}
