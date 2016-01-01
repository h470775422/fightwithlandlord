package com.xjtu.serialize;

import java.util.ArrayList;
import java.util.List;

import com.xjtu.gamestate.GameState;
import com.xjtu.player.Player;
import com.xjtu.poke.Card;

public class SocketData {
	
	private Player[] players;//三个玩家的信息
	private List<Card> lastThreePokers;//三张底牌
	private GameState gameState;//游戏状态
	private int first;//东家
	private int current;//当前轮到谁出牌
	private int winner;
	private int yourIndex;
	private List<Card> lastCardsOnDesk;
	
	
	
}
