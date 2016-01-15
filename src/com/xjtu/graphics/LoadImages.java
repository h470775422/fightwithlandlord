package com.xjtu.graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LoadImages {

	private int imageMaxCount = 54;
	private Toolkit toolkit = null;
	private MediaTracker tracker = null;
	private Image[] pics = null;

	public LoadImages(JPanel panel) {
		this.toolkit = Toolkit.getDefaultToolkit();
		this.tracker = new MediaTracker(panel);
		this.pics = new Image[this.imageMaxCount];
	}

	/*********************
	 * 加载牌背图片
	 * 
	 * @return
	 */
	public Image getBackImage() {
		Image image = null;
		String path = "bin/back/back02.jpg";
		image = toolkit.getImage(path);
		tracker.addImage(image, 1);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return image;
	}

	/***********************
	 * 加载扑克牌图片
	 * 
	 * @return
	 */
	public Image[] getPokeImages() {
		// pics[0] = toolkit.getImage(System.getProperty("user.dir") +
		// "\\resources\\pic01.png");
		String fileName = "";
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j++) {
				fileName = (i + 1) * 10 + (j + 1) + "";
				int index = i * 4 + j;
				String path = "bin/poke/" + fileName + ".jpg";
				pics[index] = toolkit.getImage(path);
				tracker.addImage(pics[index], 1);
			}
		}
		pics[52] = toolkit.getImage("bin/poke/141.jpg");
		tracker.addImage(pics[52], 1);
		pics[53] = toolkit.getImage("bin/poke/151.jpg");
		tracker.addImage(pics[53], 1);
		// tracker.checkAll(true);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return pics;
	}

	/*************************
	 * 获取桌面图片
	 * 
	 * @return
	 */
	public Image getTable() {
		Image image = null;
		String path = "bin/table/table02.jpg";
		image = toolkit.getImage(path);
		tracker.addImage(image, 1);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return image;
	}

	/*********************
	 * 加载开始界面图片
	 * 
	 * @return
	 */
	public Image getStart() {
		Image image = null;
		String path = "bin/start/start.jpg";
		image = toolkit.getImage(path);
		tracker.addImage(image, 1);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return image;
	}
}
