package com.xjtu.controller;

import java.util.List;

import com.xjtu.Landlord.LandlordType;
import com.xjtu.Landlord.RuleOfLandlord;
import com.xjtu.gamestate.GameState;
import com.xjtu.player.Player;
import com.xjtu.player.RealPlayer;
import com.xjtu.poke.Card;
import com.xjtu.poke.PokeController;

public class PlayerController {

	protected GameState gameState = GameState.CHOSE;
	// protected int playerCount = 1;
	protected int playerCount = 0;
	final protected int playerCountMax = 3;
	protected Player[] players = null;
	protected int first = -1;// 东家
	protected int current = -1;// 当前轮到谁出牌
	protected int winner = -1;
	protected List<Card> playingCards = null;
	protected int playingCardsValue = -1;
	protected LandlordType playingCardsType = LandlordType.NONE;
	protected List<Card> waitPlayingCards = null;
	protected boolean flag = false;
	protected boolean isPassCard = false;

	// 计时器线程，用来杀死未结束的Timer线程
	protected Thread timerThread = null;
	private TimerThread tt = null;
	protected int timeout = 25;
	protected PokeController pokeCtrl = new PokeController();
	// rule
	RuleOfLandlord rule = new RuleOfLandlord();
	// index
	protected int myindex = -1;

	public Player getPlayer(int index) {
		return players[index];
	}

	public int getMyindex() {
		return myindex;
	}

	public void setMyindex(int myindex) {
		this.myindex = myindex;
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

	public List<Card> getWaitPlayingCards() {
		return waitPlayingCards;
	}

	public void setWaitPlayingCards(List<Card> waitPlayingCards) {
		this.waitPlayingCards = waitPlayingCards;
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

	public Player nextPlayer() {
		current = (current + 1) % 3;
		return players[current];
	}

	public Player firstPlayer() {
		return players[first];
	}

	public Player prePlayer() {
		current = (current + 2) % 3;
		return players[current];
	}

	public boolean isPassCard() {
		return isPassCard;
	}

	public void setPassCard(boolean isPassCard) {
		this.isPassCard = isPassCard;
	}

	/****************
	 * 构造函数
	 */
	public PlayerController() {
		tt = new TimerThread();
		timerThread = new Thread(tt);
	}

	/********************
	 * 运行主逻辑
	 */
	public void runMain() {
		while (true) {
			switch (gameState) {
			case WAITCLIENT:
				waitClientConnect();
				break;
			case SHUFFLE:
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				shuffle();
				gameState = GameState.DEAL;
				break;
			case DEAL:
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				deal();// 地主三张牌的保存
				gameState = GameState.CALLING;
				break;
			case CALLING:// 找到拿到地主牌的玩家设置为东家？优先叫地主 修改状态
				findLandlordCard();
				createTimer(25);
				gameState = GameState.WAITCALL;
				break;
			case WAITCALL:// 轮流叫地主 确定地主是谁？ 东家
				waitCallLandlord();
				break;
			case PLAYING:// 地主先出牌 计时器
				JudgeLandlord();
				waitPlayingCards = null;
				break;
			case WAITPLAY:
				waitPlay();
				break;
			case END:
				end();
				break;
			case WAITNEXT:

				break;
			}
		}
	}

	/****************
	 * 洗牌
	 */
	public void shuffle() {
		pokeCtrl.shuffle();
	}

	/****************
	 * 发牌
	 */
	public void deal() {
		pokeCtrl.deal(players[0].getHand(), players[1].getHand(), players[2].getHand());
	}

	/****************
	 * 初始化三个玩家
	 */
	public void initialPlayers() {
		players = new Player[3];
		for (int i = 0; i < 3; i++) {
			players[i] = new RealPlayer();
		}
	}

	/****************
	 * 查看地主牌在谁手里 优先叫地主
	 */
	public void findLandlordCard() {
		Card lCard = pokeCtrl.getLandlordCard();
		for (int i = 0; i < 3; i++) {
			for (Card c : players[i].getHand()) {
				if (c.equals(lCard)) {
					first = current = i;
				}
			}
		}
	}

	/****************
	 * 等待出牌,判断出牌合理性
	 */
	public void waitPlay() {
		if (isPassCard) {
			nextPlaying();
		} else if (waitPlayingCards != null) {
			LandlordType type = rule.judgeType(waitPlayingCards);// 判断牌型
			if (type != LandlordType.NONE) {
				int value = rule.getValue();// 获取牌的大小
				if (playingCards == null) {
					putCardsOnTheTable(value, type);
				} else {
					if (playingCardsType == type) {
						if (playingCardsValue < value) {
							putCardsOnTheTable(value, type);
						}
					}
				}
			}
		} else if (timeout < 0) {
			nextPlaying();
			timeout = 25;
		}
	}

	/********************
	 * 保存最后一次出的牌，大小，牌型
	 * 
	 * @param value
	 * @param type
	 */
	public void putCardsOnTheTable(int value, LandlordType type) {
		playingCards = waitPlayingCards;
		playingCardsValue = value;
		playingCardsType = type;
		waitPlayingCards = null;
		nextPlaying();
	}

	/*******************
	 * 下家出牌
	 */
	public void nextPlaying() {
		if (players[current].getHand().size() == 0) {
			winner = current;
			gameState = GameState.END;
		}
		current = (current + 1) % 3;
		isPassCard = false;
		timeout = 25;
	}

	/****************
	 * 
	 */
	public void checkWinner() {

	}

	/***************
	 * 游戏结束 计算分数
	 */
	public void end() {
		if (winner == first) {
			players[first].setScore(players[first].getScore() + 10000);
		} else {
			current = first;
			Player player = nextPlayer();
			player.setScore(player.getScore() + 10000);
			player = nextPlayer();
			player.setScore(player.getScore() + 10000);
		}
		gameState = GameState.WAITNEXT;

	}

	/**************
	 * 等待客户端连接
	 */
	public void waitClientConnect() {
		if (players[0].isReady() && players[1].isReady() && players[2].isReady()) {
			gameState = GameState.SHUFFLE;
		}
	}

	/*************************
	 * 启动计时器线程
	 * 
	 * @param timeout
	 */
	public void createTimer(int timeout) {
		this.timeout = timeout;
		timerThread.start();
	}

	/**********
	 * 等待三方选择是否叫地主
	 */
	public void waitCallLandlord() {
		if (timeout <= 0 || players[current].getIsCallLandlord() == 1 || players[current].getIsCallLandlord() == -1) {
			if (timeout <= 0) {
				players[current].setIsCallLandlord(-1);
				timeout = 25;
			}
			if (current == first) {
				if (flag) {
					// 叫地主结束
					gameState = GameState.PLAYING;
				} else {
					flag = true;
				}
			}
			current = (current + 1) % 3;
		}
	}

	/******************
	 * 判断地主
	 */
	public void JudgeLandlord() {
		// 判断谁是地主？
		if (firstPlayer().getIsCallLandlord() == 1) {
			current = first;
		} else if (prePlayer().getIsCallLandlord() == 1) {
			first = current;
		} else if (nextPlayer().getIsCallLandlord() == 1) {
			first = current;
		} else {
			gameState = GameState.SHUFFLE;
			return;
		}
		gameState = GameState.WAITPLAY;
		timeout = 25;
	}

	/***************
	 * 出牌
	 */
	public void playingCards() {
		// TODO Auto-generated method stub
		// 1.根据出牌规则选择手牌

	}

	/***************
	 * 叫地主以及出牌的计时器放在这个playerController中，整个时间的计时操作都用这一个线程实现！！！
	 * 
	 * @author XJTUSE-PC
	 *
	 */
	private class TimerThread implements Runnable {
		public void run() {
			while (timeout > 0) {
				try {
					Thread.sleep(1000);
					timeout -= 1;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
