package com.xjtu.playercontroller;

import com.xjtu.player.Player;

public class PlayerController {
	
	final private int playerCount = 3;
	private Player[] players = null;
	private int first = -1;//东家
	private int current = -1;//当前轮到谁出牌
	private int landlordCard = -1;//拿到地主牌的人
	private int landlord = -1;//地主
	
	public void initialPlayers(){
		
	}
	
	public void addPlayer(Player player){
		
	}
	public Player NextPlayer(){
		return players[(current + 1)%3];
	}
	public Player FirstPlayer(){
		return players[first];
	}
	
	// 洗牌 发牌
	
	//看某张牌在谁手里   优先叫地主
	
	//轮询叫地主  first   current   landlord        abstract
	
	//轮询出牌   abstract
	
	//判断出牌是否合理
	
}
