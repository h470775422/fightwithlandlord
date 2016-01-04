package com.xjtu.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import com.xjtu.poke.Card;


public class Render {
	
	private Image[] pokeImages = null;
	private Image backImage = null;
	private Image tableImage = null;
	private JPanel panel = null;

	
//	private final int keyX = 129, keyY = 129,keyW = 30,keyH = 23;
//	private final int colorX = 218, colorY = 246,colorW = 20,colorH = 16;
//	private final int jokerX = 60, jokerY = 296, jokerW = 18, jokerH = 71;
//	private final int faceX = 0,faceY = 129,faceW = 129,faceH = 161;
//	private final int pokeSpan = 30;
//	private final int keySpanX = 10,keySpanY = 10;
//	/*********************************
//	 * 黑A: x:129   y:129   w:30    h:23
//	 * 红A: x:129   y:159   w:30    h:23
//	 * 卡面:  x:0    y:129    w:129   h:161
//	 * 方片:  x:218   y:246   w:20   h:16     顺序：方片、梅花、红桃、黑桃
//	 * 小王:  x:60     y:296   w:18   h:71
//	 */
	
	private final int myX = 480,myY = 700;
	private final int rightX = 1400,rightY = 200;
	private final int leftX = 100,leftY = 200;
	private final int pokeSpan = 30,selectedSpan = 20;
	private final int pokeWidth = 105,pokeHeight = 150;
	
	/**************
	 * 传入面板JPanel对象
	 * @param panel
	 */
	
	public Render(JPanel panel){
		this.panel = panel;

		LoadImages loadImages = new LoadImages(panel);
		pokeImages = loadImages.getPokeImages();
		backImage = loadImages.getBackImage();
		tableImage = loadImages.getTable();

	}
	
	public void drawRight(Graphics g,List<Card> cards){
		int x = rightX,y = rightY;
		for(int i = 0; i < cards.size(); i++){
			g.drawImage(backImage, x, y, panel);
			y += 20;
		}
	}
	public void drawLeft(Graphics g,List<Card> cards){
		int x = leftX,y = leftY;
		for(int i = 0; i < cards.size(); i++){
			g.drawImage(backImage, x, y, panel);
			y += 20;
		}
	}
	public void drawMyself(Graphics g,List<Card> cards){
		int x = myX,y = myY;
		int a = 0;
		for(Card c:cards){
			a = (c.getKey() - 1) * 4;
			switch(c.getColor()){
			case DIAMOND: break;//方片
			case CLUB: a +=1; break;//梅花
			case HEART: a += 2; break;//红桃  
			case SPADE: a += 3; break;//黑桃
			case JOKER_SMALL: a = 52; break;
			case JOKER_BIG: a = 53; break;
			}
			if(c.isSelected()){
				g.drawImage(pokeImages[a],x,y - selectedSpan,panel);
			}else{
				g.drawImage(pokeImages[a],x,y,panel);
			}	
			x += pokeSpan;
		}
	}
	//绘制等待玩家界面
	public void drawWaitClient(Graphics g){
		
	}

	//绘制桌面
	public void drawTable(Graphics g){
		g.drawImage(tableImage, 0, 0, panel);
	}
	
	//绘制人物
	public void drawPlayer(Graphics g){
		
	}
	
	public int checkPokeClick(int x,int y,List<Card> cards){
		int x1 = myX,x2 = x1 + pokeSpan;
		int y1 = myY,y2 = myY + pokeHeight;
		int sy1 = y1 - selectedSpan, sy2 = y2 - selectedSpan;
		int size = cards.size();
		if(x < myX || y < myY - selectedSpan)
			return -1;
		for(int i = 0; i < size; i++){
			if(cards.get(i).isSelected()){
				//System.out.println("选中");
				if((x >= x1 && x <= x2 && y >= sy1 && y <= sy2) || 
						x >= (x1 + pokeSpan) && x <= (x1 + pokeWidth) && (y >= sy1) && y <= (sy1 + selectedSpan)){
					//System.out.println("命中1");
					return i;
				}
			}else{
				if(x >= x1 && x <= x2 && y >= y1 && y <= y2){
					//System.out.println("命中2");
					return i;
				}
			}
			x1 += pokeSpan;
			x2 += pokeSpan;
		}
		if(cards.get(size - 1).isSelected()){
			if(x >= x1 && x <= (x2 + pokeWidth - pokeSpan) && y >= (y1 - selectedSpan) && y <= (y2 - selectedSpan)){
				return size - 1;
			}
		}else{
			if(x >= x1 && x <= (x2 + pokeWidth - pokeSpan) && y >= y1 && y <= y2){
				return size - 1;
			}
		}
		return -1;
	}
}
