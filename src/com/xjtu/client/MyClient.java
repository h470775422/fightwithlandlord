package com.xjtu.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.xjtu.controller.PlayerController;
import com.xjtu.gamestate.GameState;
import com.xjtu.player.Player;
import com.xjtu.poke.Card;
import com.xjtu.poke.Card.PokerColor;
import com.xjtu.serialize.SocketData;
import com.xjtu.serialize.SocketData2;

public class MyClient {
	private static final String IP = "196.168.10.43";
	private static final int PORT = 9989;
	private Socket client;
	private PlayerController playerController = null;// 不是new，是引用

	public MyClient(PlayerController playerController) {
		this.playerController = playerController;
	}

	public void connect(String ip) {
		try {
			System.out.println("客户端-----连接服务器");
			client = new Socket(ip, PORT);
			System.out.println("客户端-----连接成功");
			// 开启两个线程，一个接受，一个发送
			new Thread(new SendMessage(client, playerController)).start();
			new Thread(new GetMessage(client, playerController)).start();
			System.out.println("客户端-----连接完成，pre开始游戏");
		} catch (IOException e) {
			e.printStackTrace();
		}
		runMainThread();
	}

	public void runMainThread() {
		System.out.println("客户端-----连接完成，开始游戏");
	}

	class GetMessage implements Runnable// 接收消息
	{
		private Socket client;
		private PlayerController pc = null;
		private SocketData sd;
		private ObjectInputStream ois = null;

		public GetMessage(Socket client, PlayerController playerController) {
			System.out.println("客户端-----接收线程");
			this.pc = playerController;
			this.client = client;	
			System.out.println("客户端-----接收线程end");
		}

		@Override
		public void run() {
			//sd = new SocketData();
			try {
				ois = new ObjectInputStream(client.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (true) {
				try {
					sd = (SocketData) ois.readObject();
					// 用接收到的数据初始化playerController数据
					// System.out.println("客户端接受线程");
					int myIndex = sd.getYourIndex();
					pc.setPlayerCount(sd.getPlayerCount());
					pc.setFirst(sd.getFirst());
					pc.setCurrent(sd.getCurrent());
					pc.setWinner(sd.getWinner());
					pc.setGameState(sd.getGameState());
					pc.setMyindex(myIndex);
					for(int i = 0; i < 3; i++){
						if(i != myIndex)
							pc.setPlayer(i, sd.getPlayers()[i]);
					}
					Thread.sleep(30);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	class SendMessage implements Runnable// 发送消息
	{
		private Socket client;
		private PlayerController pc = null;
		private SocketData2 sd;
		private ObjectOutputStream oos = null;

		public SendMessage(Socket client, PlayerController playerController) {
			System.out.println("客户端-----发送线程");	
			this.pc = playerController;
			this.client = client;
			System.out.println("客户端-----发送线程end");
		}

		@Override
		public void run() {
			sd = new SocketData2();
			try {
				oos = new ObjectOutputStream(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (true) {
				try {
					// 封装socketData2准备发送
					// System.out.println("客户端发送线程");
					if(pc.getMyindex() != -1){
						//
						sd.setIsCallLandlord(pc.getPlayer(pc.getMyindex()).getIsCallLandlord());
						sd.setPlayingCards(pc.getPlayingCards());
						sd.setReady(pc.getPlayer(pc.getMyindex()).isReady());
						sd.setPassCard(pc.isPassCard());
						oos.writeObject(sd);
						oos.flush();
						oos.reset();
					}
					Thread.sleep(30);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
