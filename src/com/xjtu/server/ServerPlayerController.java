package com.xjtu.server;

import org.junit.Test;

import com.xjtu.controller.PlayerController;
import com.xjtu.gamestate.GameState;
import com.xjtu.monitor.MonitorThread;
import com.xjtu.player.Player;
import com.xjtu.player.RealPlayer;

public class ServerPlayerController extends PlayerController {

	// 1.创建serversocket
	// 2.wait等待连接并且判断是不是为3个
	//
	private Server server;
	private int port = 9989;

	public ServerPlayerController() {
		new Thread(new MonitorThread(this)).start();
		server = new Server(this);
		startServer();
	}

	public void startServer() {
		// 并将游戏状态设为等待
		gameState = GameState.WAITCLIENT;
		// 实例化初始值 把本机设置为第一个玩家 并将count值设置为1 isReady 设置为true
		// this.getPlayers()[this.playerCount]=this.firstPlayer();
		// this.setPlayers(this.getPlayers());
		// initialPlayers();
		// players[playerCount++]=new RealPlayer();
		players[0].setReady(true);
		myindex = 0;
		playerCount++;
		// 开启服务器 等待客户端连接
		server.startServer(port);
		new Thread() {
			public void run() {
				runMain();
			}
		}.start();
	}
}

// 轮询叫地主 first current landlord abstract
// 轮询出牌 abstract
// 创建Server Socket ，等待连接 ， 根据GameState执行不同逻辑
/************
 * while() { if(gamestate.WAITCLIENT){ if(count < 2){ accept() } else{
 * 
 * } 发送给客户消息包 当前的状态 waticlient } else if(){ }
 * 
 * 
 * }
 */