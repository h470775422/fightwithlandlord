package com.xjtu.poke;

public class Card implements Comparable<Card>{
	public  enum PokerColor
	{
		HEART,SPADE,CLUB,DIAMOND,JOKER_SMALL,JOKER_BIG
	}
	
	private PokerColor color;//��ɫ
	private int key;//��ֵ
	private int value;//��С
	
	public PokerColor getColor() {
		return color;
	}
	public void setColor(PokerColor color) {
		this.color = color;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	
	public boolean equals(Card card){
		if(card != null && color == card.getColor() && key ==card.getKey()){
			return true;
		}
		return false;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public int compareTo(Card arg0) {
		if(this.getKey()<arg0.getKey()){
			return -1;
		}
		if(this.getKey()>arg0.getKey()){
			return 1;
		}
		return 0;
	}
	

}
