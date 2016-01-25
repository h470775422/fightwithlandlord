package com.xjtu.graphics;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import org.junit.Test;

import com.xjtu.Panel.MainPanel;
import com.xjtu.controller.PlayerController;
import com.xjtu.gamestate.GameState;
import com.xjtu.poke.Card;
import com.xjtu.poke.PokeController;

public class RenderController implements MouseListener, MouseMotionListener {
	private MainPanel panel = null;
	private PokeController pokeCtrl;
	private PlayerController playerCtrl = null;
	private Render render = null;
	private int index = -1, next, last;
	private boolean mousePressed = false;
	private List<Card> draggedCards = new ArrayList<>();
	private boolean[] selected = new boolean[20];
	
	private int width;
	private int height;

	private Date date;
	private long lastTime;

	private int pointCount = 0;
	private final String shuffleStrFinal = "洗牌中 请耐心等待";
	private String shuffleStr = shuffleStrFinal;
	private final String dealStrFinal = "发牌中";
	private String dealStr = dealStrFinal;

	public RenderController(PlayerController playerCtrl, MainPanel panel, int width, int height) {
		this.playerCtrl = playerCtrl;
		this.panel = panel;
		render = new Render(panel, width, height);
		index = playerCtrl.getMyindex();
		next = (index + 1) % 3;
		last = (index + 2) % 3;
		this.width = width;
		this.height = height;
		date = new Date();
		lastTime = date.getTime();
		
		
	}

	public void initialPokePanel() {

	}

	public void drawAll(Graphics g) {
		switch (playerCtrl.getGameState()) {
		case CHOSE:
			break;
		case WAITCLIENT:
			drawWaitClient(g);
			break;
		case SHUFFLE:
			drawShuffle(g);
			break;
		case DEAL:
			drawDeal(g);
			break;
		case CALLING:
			unloadReadyButton();
			drawTable(g);
			drawPoke(g);
			break;
		case WAITCALL:
			drawTable(g);
			drawPoke(g);
			break;
		case PLAYING:
			drawTable(g);
			drawPoke(g);
			break;
		case WAITPLAY:
			break;
		case END:
			break;
		case WAITNEXT:
			break;
		}
	}

	/************************
	 * 绘制等待玩家
	 * 
	 * @param g
	 */
	private void drawWaitClient(Graphics g) {
		String str = "当前玩家数:" + (playerCtrl.getPlayerCount());
		int readyCount = 0;
		if (playerCtrl.getPlayer(0).isReady())
			readyCount++;
		if (playerCtrl.getPlayer(1).isReady())
			readyCount++;
		if (playerCtrl.getPlayer(2).isReady())
			readyCount++;
		str += "...." + readyCount + "个玩家已准备";
		str += "..............我的编号" + playerCtrl.getMyindex();
		g.drawString(str, width / 2 - 200, height / 2 - 50);
	}

	/******************
	 * 绘制洗牌
	 * 
	 * @param g
	 */
	private void drawShuffle(Graphics g) {
		if (calTimeSpan(500)) {
			shuffleStr += ".";
			pointCount = (pointCount + 1) % 3;
			if (pointCount == 0)
				shuffleStr = shuffleStrFinal;
		}
		g.drawString(shuffleStr, width / 2 - 200, height / 2 - 50);
	}

	/********************
	 * 绘制发牌
	 * 
	 * @param g
	 */
	private void drawDeal(Graphics g) {
		if (calTimeSpan(500)) {
			dealStr += ".";
			pointCount = (pointCount + 1) % 3;
			if (pointCount == 0)
				dealStr = dealStrFinal;
		}
		g.drawString(dealStr, width / 2 - 200, height / 2 - 50);
	}

	public void unloadReadyButton() {
		panel.unloadReadyBtn();
	}

	/******************
	 * 绘制桌面背景
	 * 
	 * @param g
	 */
	private void drawTable(Graphics g) {
		render.drawTable(g);
	}

	/******************
	 * 绘制三方的牌
	 * 
	 * @param g
	 */
	private void drawPoke(Graphics g) {
		g.drawString(index +":"+playerCtrl.getPlayer(index).getHand().size(), width/2, 400);
		render.drawMyself(g, playerCtrl.getPlayer(index).getHand(),selected);
		render.drawRight(g, playerCtrl.getPlayer(next).getHand());
		render.drawLeft(g, playerCtrl.getPlayer(last).getHand());

	}

	private void drawPokeOnDesktop(Graphics g) {

	}

	private void drawThreeLandlordPokes(Graphics g) {

	}

	/******************
	 * 计算时间差
	 * 
	 * @param timeSpan
	 * @return
	 */
	public boolean calTimeSpan(long timeSpan) {
		date = new Date();
		long currentTime = date.getTime();
		// System.out.println("cur" + currentTime + "," + "last" + lastTime);
		if (currentTime - lastTime > timeSpan) {
			lastTime = currentTime;
			return true;
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mousePressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (playerCtrl.getGameState() == GameState.WAITPLAY || 
				playerCtrl.getGameState() == GameState.WAITCALL) {
			mousePressed = false;
			if (draggedCards.size() > 0) {
				draggedCards.clear();
				return;
			}
			int x = arg0.getX(), y = arg0.getY();
			int num = render.checkPokeClick(x, y, playerCtrl.getPlayer(index).getHand());
			if (num != -1) {
				boolean sel = playerCtrl.getPlayer(index).getHand().get(num).isSelected();
				playerCtrl.getPlayer(index).getHand().get(num).setSelected(!sel);
				selected[num] = !selected[num];
			} else {
				int i = 0;
				for (Card c : playerCtrl.getPlayer(index).getHand()) {
					c.setSelected(false);
					selected[i++] = false;
				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (playerCtrl.getGameState() == GameState.WAITPLAY) {
			int x = arg0.getX(), y = arg0.getY();
			if (mousePressed) {
				// System.out.println("鼠标按下了");
				int num = render.checkPokeClick(x, y, playerCtrl.getPlayer(index).getHand());
				int size = draggedCards.size();
				if (num == -1)
					return;
				boolean sel = playerCtrl.getPlayer(index).getHand().get(num).isSelected();
				if (size == 0) {
					draggedCards.add(playerCtrl.getPlayer(index).getHand().get(num));

				} else {
					for (Card c : draggedCards) {
						if (c.equals(playerCtrl.getPlayer(index).getHand().get(num))) {
							return;
						}
					}
					draggedCards.add(playerCtrl.getPlayer(index).getHand().get(num));
				}
				playerCtrl.getPlayer(index).getHand().get(num).setSelected(!sel);
				selected[num] = !selected[num];
			} else {
				System.out.println("鼠标松开了");
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		//System.out.println("X:" + arg0.getX() + ",Y:" + arg0.getY());
	}

}

// List<Card> cards = new ArrayList<>();
// Card c = new Card();
// c = new
// Card();c.setKey(1);c.setValue(12);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(2);c.setValue(13);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(3);c.setValue(1);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(4);c.setValue(2);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(5);c.setValue(3);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(6);c.setValue(4);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(7);c.setValue(5);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(8);c.setValue(6);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(9);c.setValue(7);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(10);c.setValue(8);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(11);c.setValue(9);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(12);c.setValue(10);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(13);c.setValue(11);c.setColor(PokerColor.HEART);cards.add(c);
// c = new
// Card();c.setKey(14);c.setValue(14);c.setColor(PokerColor.JOKER_SMALL);cards.add(c);
// c = new
// Card();c.setKey(15);c.setValue(15);c.setColor(PokerColor.JOKER_BIG);cards.add(c);
