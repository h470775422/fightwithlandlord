package com.xjtu.server;

import org.junit.Test;

import com.xjtu.controller.PlayerController;
import com.xjtu.gamestate.GameState;
import com.xjtu.monitor.MonitorThread;
import com.xjtu.player.Player;
import com.xjtu.player.RealPlayer;

public class ServerPlayerController extends PlayerController {

	// 1.����serversocket
	// 2.wait�ȴ����Ӳ����ж��ǲ���Ϊ3��
	//
	private Server server;
	private int port = 9989;

	public ServerPlayerController() {
		new Thread(new MonitorThread(this)).start();
		server = new Server(this);
		startServer();
	}

	public void startServer() {
		// ������Ϸ״̬��Ϊ�ȴ�
		gameState = GameState.WAITCLIENT;
		// ʵ������ʼֵ �ѱ�������Ϊ��һ����� ����countֵ����Ϊ1 isReady ����Ϊtrue
		// this.getPlayers()[this.playerCount]=this.firstPlayer();
		// this.setPlayers(this.getPlayers());
		// initialPlayers();
		// players[playerCount++]=new RealPlayer();
		players[0].setReady(true);
		myindex = 0;
		playerCount++;
		// ���������� �ȴ��ͻ�������
		server.startServer(port);
		new Thread() {
			public void run() {
				runMain();
			}
		}.start();
	}
}

// ��ѯ�е��� first current landlord abstract
// ��ѯ���� abstract
// ����Server Socket ���ȴ����� �� ����GameStateִ�в�ͬ�߼�
/************
 * while() { if(gamestate.WAITCLIENT){ if(count < 2){ accept() } else{
 * 
 * } ���͸��ͻ���Ϣ�� ��ǰ��״̬ waticlient } else if(){ }
 * 
 * 
 * }
 */