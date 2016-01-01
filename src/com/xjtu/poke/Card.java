package com.xjtu.poke;

public class Card {
	public  enum PokerColor
	{
		heart,spade,club,diamond,joker_small,joker_big
	}
	
	private PokerColor color;//»¨É«
	private int value;//ÅÆÖµ
	
	
	public PokerColor getColor() {
		return color;
	}
	public void setColor(PokerColor color) {
		this.color = color;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	

}
