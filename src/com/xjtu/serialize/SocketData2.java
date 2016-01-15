package com.xjtu.serialize;

import java.io.Serializable;
import java.util.List;

import com.xjtu.poke.Card;

public class SocketData2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 911L;

	private boolean isReady = false;
	private int isCallLandlord = 0;
	private List<Card> playingCards = null;
	private boolean isPassCard = false;

	public boolean isPassCard() {
		return isPassCard;
	}

	public void setPassCard(boolean isPassCard) {
		this.isPassCard = isPassCard;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public int getIsCallLandlord() {
		return isCallLandlord;
	}

	public void setIsCallLandlord(int isCallLandlord) {
		this.isCallLandlord = isCallLandlord;
	}

	public List<Card> getPlayingCards() {
		return playingCards;
	}

	public void setPlayingCards(List<Card> playingCards) {
		this.playingCards = playingCards;
	}

}
