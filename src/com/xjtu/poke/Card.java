package com.xjtu.poke;

import java.io.Serializable;

import org.junit.Test;

public class Card implements Comparable<Card>,Serializable{
	public  enum PokerColor
	{
		HEART,SPADE,CLUB,DIAMOND,JOKER_SMALL,JOKER_BIG
	}
	
	private PokerColor color;//花色
	private int key;//牌值
	private int value;//大小
	private boolean selected = false;

	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
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
		if(this.getValue()<arg0.getValue()){
			return 1;
		}
		if(this.getValue()>arg0.getValue()){
			return -1;
		}
		return 0;
	}
	

	@Override
	public String toString(){
		return "花色:" + color + ",牌面:" + key;
	}
}
