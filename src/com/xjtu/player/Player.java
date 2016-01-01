package com.xjtu.player;

import java.util.List;
import com.xjtu.poke.Card;

public abstract class Player {

	private List<Card> hand = null;//手牌
	private int score = 0;//积分
	private boolean isLandlord = false;//是否地主
	
	
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isLandlord() {
		return isLandlord;
	}
	public void setLandlord(boolean isLandlord) {
		this.isLandlord = isLandlord;
	}
	
	
	
	
	
}
