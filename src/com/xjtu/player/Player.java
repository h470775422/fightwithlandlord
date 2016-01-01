package com.xjtu.player;

import java.util.List;
import com.xjtu.poke.Card;

public abstract class Player {

	private List<Card> hand = null;//ÊÖÅÆ
	private int score = 0;//»ý·Ö
	private boolean isCallLandlord = false;
	private boolean isRedy = false;
	
	
	public boolean isCallLandlord() {
		return isCallLandlord;
	}
	public void setCallLandlord(boolean isCallLandlord) {
		this.isCallLandlord = isCallLandlord;
	}
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
	public boolean isRedy() {
		return isRedy;
	}
	public void setRedy(boolean isRedy) {
		this.isRedy = isRedy;
	}

	
	
	
	
	
}
