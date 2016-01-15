package com.xjtu.Panel;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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

public class MainPanel extends JPanel implements Runnable {

	private RenderController renderCtrl = null;
	private PlayerController playerCtrl = null;

	public MainPanel() {
		//initialMainFunc();
	}

	/*******************
	 * 初始化游戏主线程
	 */
	private void initialMainFunc() {
		playerCtrl = new ServerPlayerController();
		playerCtrl.setFirst(0);
		playerCtrl.setMyindex(1);
		playerCtrl.initialPlayers();
		playerCtrl.shuffle();
		playerCtrl.deal();
		playerCtrl.setGameState(GameState.PLAYING);
		renderCtrl = new RenderController(playerCtrl, this);
		this.addMouseListener(renderCtrl);
		this.addMouseMotionListener(renderCtrl);
		Thread t = new Thread(this);
		t.start();
	}
	
	private void initialOther(){
		renderCtrl = new RenderController(playerCtrl, this);
		this.addMouseListener(renderCtrl);
		this.addMouseMotionListener(renderCtrl);
		Thread t = new Thread(this);
		t.start();
	}

	public void initialClientFunc(){
		playerCtrl = new ClientPlayerController();
		initialOther();
	}
	public void initialServerFunc(){
		playerCtrl = new ServerPlayerController();
		initialOther();
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		renderCtrl.drawAll(g);

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

}
