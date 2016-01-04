package com.xjtu.graphics;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.xjtu.controller.PlayerController;
import com.xjtu.poke.Card;
import com.xjtu.poke.PokeController;


public class RenderController implements MouseListener,MouseMotionListener{
	private PokeController pokeCtrl;
	private PlayerController playerCtrl= null;
	private Render render = null;
	private int index = -1,next,last;
	private boolean mousePressed = false;
	private List<Card> draggedCards = new ArrayList<>();
	
	
	public RenderController(PlayerController playerCtrl, JPanel panel){
		this.playerCtrl = playerCtrl;
		render = new Render(panel);
		index = playerCtrl.getMyindex();
		next = (index + 1) % 3;
		last = (next + 1) % 3;
	}
	
	public void initialPokePanel(){
		
	}

	public void drawAll(Graphics g){
		switch(playerCtrl.getGameState()){
		case CHOSE:
			break;
		case WAITCLIENT:
			break;
		case SHUFFLE:
			break;
		case DEAL:
			break;
		case CALLING:
			break;
		case WAITCALL:
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
	
	public void drawTable(Graphics g){
		render.drawTable(g);
	}
	public void drawPoke(Graphics g){
		render.drawMyself(g, playerCtrl.getPlayer(index).getHand());
		render.drawRight(g, playerCtrl.getPlayer(next).getHand());
		render.drawLeft(g, playerCtrl.getPlayer(last).getHand());
	}
	public void drawPokeOnDesktop(Graphics g){
		
	}
	public void drawThreeLandlordPokes(Graphics g){
		
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
		mousePressed = false;
		if(draggedCards.size() > 0){	
			draggedCards.clear();
			return;
		}
		int x = arg0.getX(),y = arg0.getY();
		int num = render.checkPokeClick(x, y, playerCtrl.getPlayer(index).getHand());
		if(num != -1){
			boolean sel = playerCtrl.getPlayer(index).getHand().get(num).isSelected();
			playerCtrl.getPlayer(index).getHand().get(num).setSelected(!sel);
		}else{
			for(Card c:playerCtrl.getPlayer(index).getHand()){
				c.setSelected(false);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		int x = arg0.getX(),y = arg0.getY();
		if(mousePressed){
			//System.out.println("鼠标按下了");
			int num = render.checkPokeClick(x, y, playerCtrl.getPlayer(index).getHand());
			int size = draggedCards.size();
			if(num == -1)
				return;
			boolean sel = playerCtrl.getPlayer(index).getHand().get(num).isSelected();
			if(size == 0){
				draggedCards.add(playerCtrl.getPlayer(index).getHand().get(num));
				
			}else{
				for(Card c:draggedCards){
					if(c.equals(playerCtrl.getPlayer(index).getHand().get(num))){
						return;
					}
				}
				draggedCards.add(playerCtrl.getPlayer(index).getHand().get(num));
			}
			playerCtrl.getPlayer(index).getHand().get(num).setSelected(!sel);
		}else{
			System.out.println("鼠标松开了");
		}
		
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
		
}



//List<Card> cards = new ArrayList<>();
//Card c = new Card();
//c = new Card();c.setKey(1);c.setValue(12);c.setColor(PokerColor.HEART);cards.add(c);
//c = new Card();c.setKey(2);c.setValue(13);c.setColor(PokerColor.HEART);cards.add(c);	
//c = new Card();c.setKey(3);c.setValue(1);c.setColor(PokerColor.HEART);cards.add(c);
//c = new Card();c.setKey(4);c.setValue(2);c.setColor(PokerColor.HEART);cards.add(c);
//c = new Card();c.setKey(5);c.setValue(3);c.setColor(PokerColor.HEART);cards.add(c);
//c = new Card();c.setKey(6);c.setValue(4);c.setColor(PokerColor.HEART);cards.add(c);
//c = new Card();c.setKey(7);c.setValue(5);c.setColor(PokerColor.HEART);cards.add(c);	
//c = new Card();c.setKey(8);c.setValue(6);c.setColor(PokerColor.HEART);cards.add(c);
//c = new Card();c.setKey(9);c.setValue(7);c.setColor(PokerColor.HEART);cards.add(c);
//c = new Card();c.setKey(10);c.setValue(8);c.setColor(PokerColor.HEART);cards.add(c);
//c = new Card();c.setKey(11);c.setValue(9);c.setColor(PokerColor.HEART);cards.add(c);
//c = new Card();c.setKey(12);c.setValue(10);c.setColor(PokerColor.HEART);cards.add(c);
//c = new Card();c.setKey(13);c.setValue(11);c.setColor(PokerColor.HEART);cards.add(c);
//c = new Card();c.setKey(14);c.setValue(14);c.setColor(PokerColor.JOKER_SMALL);cards.add(c);
//c = new Card();c.setKey(15);c.setValue(15);c.setColor(PokerColor.JOKER_BIG);cards.add(c);


