package com.xjtu.Panel;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.xjtu.Landlord.RuleOfLandlord;
import com.xjtu.client.ClientPlayerController;
import com.xjtu.controller.PlayerController;
import com.xjtu.gamestate.GameState;
import com.xjtu.graphics.Render;
import com.xjtu.graphics.RenderController;
import com.xjtu.poke.Card;
import com.xjtu.poke.Card.PokerColor;
import com.xjtu.poke.PokeController;
import com.xjtu.server.ServerPlayerController;

public class MainPanel extends JPanel implements Runnable, ActionListener {

	private RenderController renderCtrl = null;
	private PlayerController playerCtrl = null;

	private JButton readyBtn = null;

	private int width;
	private int height;
	
	private int gameModel;

	public MainPanel(int index, String ip, int width, int height) {
		this.width = width;
		this.height = height;
		this.gameModel = index;
		if (index == 1) {// server
			initialServerFunc();
		} else if (index == 2) {// client
			initialClientFunc(ip);
		} else if (index == 3) {// AI

		}
		// repaint();
	}

	/*******************
	 * 初始化游戏主线程
	 */
	// private void initialMainFunc() {
	// playerCtrl = new ServerPlayerController();
	// playerCtrl.setFirst(0);
	// playerCtrl.setMyindex(1);
	// playerCtrl.initialPlayers();
	// playerCtrl.shuffle();
	// playerCtrl.deal();
	// playerCtrl.setGameState(GameState.PLAYING);
	// renderCtrl = new RenderController(playerCtrl, this);
	// this.addMouseListener(renderCtrl);
	// this.addMouseMotionListener(renderCtrl);
	//
	// Thread t = new Thread(this);
	// t.start();
	// }

	private void initialOther() {
		renderCtrl = new RenderController(playerCtrl, this, width, height);
		this.addMouseListener(renderCtrl);
		this.addMouseMotionListener(renderCtrl);
		Thread t = new Thread(this);
		t.start();
	}

	public void initialClientFunc(String ip) {
		System.out.println("客户端启动");
		playerCtrl = new ClientPlayerController(ip);

		setLayout(null);
		readyBtn = new JButton("准备");
		readyBtn.setBounds(width / 2 - 200, height / 2, 70, 30);
		readyBtn.setActionCommand("ready");
		readyBtn.addActionListener(this);
		add(readyBtn);
		repaint();
		initialOther();
	}

	public void initialServerFunc() {
		System.out.println("服务器启动");
		playerCtrl = new ServerPlayerController();
		initialOther();
	}

	@Override
	public void paint(Graphics g) {
		// g.clearRect(0, 0, this.getWidth(), this.getHeight());
		super.paint(g);
		renderCtrl.drawAll(g);
		/*
		 * if(readyBtn != null) readyBtn.repaint();
		 */

	}
	
	public void unloadReadyBtn(){
		if(gameModel == 2){
			this.remove(readyBtn);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("ready")) {
			System.out.println("我要准备" + playerCtrl.getMyindex() + "号");
			playerCtrl.getPlayer(playerCtrl.getMyindex()).setReady(true);
			readyBtn.setEnabled(false);
		}

	}

}
