package com.xjtu.player;

import java.util.ArrayList;
import java.util.List;

import com.xjtu.gamestate.GameState;
import com.xjtu.poke.Card;

public abstract class Player {

	protected List<Card> hand = null;// ����
	protected int score = 0;// ����
	protected int isCallLandlord = 0;
	// protected boolean isCallLandlord = false;
	protected boolean isReady = false;

	/**************
	 * ���캯��
	 */
	public Player() {
		hand = new ArrayList<>();
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

	public int getIsCallLandlord() {
		return isCallLandlord;
	}

	public void setIsCallLandlord(int isCallLandlord) {
		this.isCallLandlord = isCallLandlord;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

}
