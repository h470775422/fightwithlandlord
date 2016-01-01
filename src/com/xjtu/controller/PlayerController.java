package com.xjtu.controller;

import com.xjtu.player.Player;
import com.xjtu.poke.Card;
import com.xjtu.poke.PokeController;

public class PlayerController {
	
	
	final private int playerCount = 3;
	private Player[] players = null;
	private int first = -1;//东家
	private int current = -1;//当前轮到谁出牌
	
	private PokeController pokeCtrl = new PokeController();
	
	public void initialPlayers(){
		
	}
	
	public void addPlayer(Player player){
		
	}
	public Player nextPlayer(){
		return players[(current + 1)%3];
	}
	public Player firstPlayer(){
		return players[first];
	}
	
	public Player PrePlayer(){
		return players[(current + 2) % 3];
	}
	// 洗牌 发牌
	public void suffle(){
		pokeCtrl.shuffle();
	}
	
	public void deal(){
		pokeCtrl.deal(players[0].getHand(), players[1].getHand(), players[2].getHand());
	}
	
	//看某张牌在谁手里   优先叫地主
	public void findLandlord(){
		Card lCard = pokeCtrl.getLandlordCard();
		for(int i = 0 ; i < 3; i++){
			for(Card c:players[i].getHand()){
				if(c.getColor() == lCard.getColor() && c.getValue() == lCard.getValue()){
					first = current = i;
				}
			}
		}
	}
	
	//轮询叫地主  first   current   landlord        abstract
	
	//轮询出牌   abstract
	
	//判断出牌是否合理
	
}
