package com.xjtu.controller;

import java.util.List;

import com.xjtu.Landlord.RuleOfLandlord;
import com.xjtu.gamestate.GameState;
import com.xjtu.player.Player;
import com.xjtu.player.RealPlayer;
import com.xjtu.poke.Card;
import com.xjtu.poke.PokeController;

public class PlayerController {
	
	protected GameState gameState = GameState.CHOSE;
	//protected int playerCount = 1;
	protected int playerCount = 0;
	final protected int playerCountMax = 3;
	protected Player[] players = null;
	protected int first = -1;//����
	protected int current = -1;//��ǰ�ֵ�˭����
	protected int winner = -1;
	protected List<Card> playingCards = null;
	
	//��ʱ���̣߳�����ɱ��δ������Timer�߳�
	protected Thread timerThread = null;
	private TimerThread tt = null;
	protected int timeout=25;
	protected PokeController pokeCtrl = new PokeController();
	//index
	protected int myindex = -1;
	
	protected RuleOfLandlord rule = new RuleOfLandlord();
	
	
	
	public Player getPlayer(int index){
		return players[index];
	}
	
	public int getMyindex() {
		return myindex;
	}
	public void setMyindex(int myindex) {
		this.myindex = myindex;
	}
	
	public PlayerController() {
		tt = new TimerThread();
		timerThread = new Thread(tt);
	}
	public List<Card> getPlayingCards() {
		return playingCards;
	}
	public void setPlayingCards(List<Card> playingCards) {
		this.playingCards = playingCards;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	public int getPlayerCount() {
		return playerCount;
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	public Player[] getPlayers() {
		return players;
	}
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public PokeController getPokeCtrl() {
		return pokeCtrl;
	}
	public void setPokeCtrl(PokeController pokeCtrl) {
		this.pokeCtrl = pokeCtrl;
	}
	public int getPlayerCountMax() {
		return playerCountMax;
	}
	public Player nextPlayer(){
		current = (current + 1)%3;
		return players[current];
	}
	public Player firstPlayer(){
		return players[first];
	}
	
	public Player prePlayer(){
		current = (current + 2) % 3;
		return players[current];
	}
	// ϴ�� ����
	public void shuffle(){
		
		pokeCtrl.shuffle();
	}
	
	public void deal(){
		pokeCtrl.deal(players[0].getHand(), players[1].getHand(), players[2].getHand());
		rule.sort(players[0].getHand());
		rule.sort(players[1].getHand());
		rule.sort(players[2].getHand());
	}
	
	//��ʼ���������
	public void initialPlayers(){
		players = new Player[3];
		for(int i = 0; i < 3; i++){
			players[i] = new RealPlayer();
		}
	}
	
	//��ĳ������˭����   ���Ƚе���
	public void findLandlord(){
		Card lCard = pokeCtrl.getLandlordCard();
		for(int i = 0 ; i < 3; i++){
			for(Card c:players[i].getHand()){
				if(c.equals(lCard)){
					first = current = i;
				}
			}
		}
		
	}
	
	//��ѯ�е���  first   current   landlord        abstract
	
	//��ѯ����   abstract
	
	//�жϳ����Ƿ����
	
	//��Ϸ����    �������
	public void end(){
		if(winner == first){
			players[first].setScore(players[first].getScore() + 10000);
		}else{
			current = first;
			Player player = nextPlayer();
			player.setScore(player.getScore() + 10000);
			player = nextPlayer();
			player.setScore(player.getScore() + 10000);
		}
		gameState = GameState.WAITNEXT;
		
	}
	
	//�е����Լ����Ƶļ�ʱ���������playerController�У�����ʱ��ļ�ʱ����������һ���߳�ʵ�֣�����
	class TimerThread implements Runnable
	{	
		
		public void run() {
			while(timeout>0)
			{
				try {
					Thread.sleep(1000);
					timeout -= 1;
					//System.out.println(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
