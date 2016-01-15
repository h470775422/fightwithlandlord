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
	private PlayerController playerController = null;// ����new��������
	private int yourIndex = -1;

	public MyClient(PlayerController playerController) {
		this.playerController = playerController;
	}

	public void connect() {
		try {
			client = new Socket(IP, PORT);
			// ���������̣߳�һ�����ܣ�һ������
			new Thread(new GetMessage(client, playerController)).start();
			new Thread(new SendMessage(client, playerController)).start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		runMainThread();

	}

	public void runMainThread() {

	}

	class GetMessage implements Runnable// ������Ϣ
	{
		private Socket client;
		private PlayerController pc = null;
		private SocketData sd;

		public GetMessage(Socket client, PlayerController playerController) {
			sd = new SocketData();
			this.pc = playerController;
			this.client = client;
		}

		@Override
		public void run() {
			ObjectInputStream ois = null;
			while (true) {
				try {
					ois = new ObjectInputStream(client.getInputStream());
					sd = (SocketData) ois.readObject();
					// �ý��յ������ݳ�ʼ��playerController����
					// System.out.println("getMessage------��������-----------");
					pc.setPlayers(sd.getPlayers());
					pc.setFirst(sd.getFirst());
					pc.setCurrent(sd.getCurrent());
					pc.setWinner(sd.getWinner());
					pc.setGameState(sd.getGameState());
					yourIndex = sd.getYourIndex();

					Thread.sleep(30);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	class SendMessage implements Runnable// ������Ϣ
	{
		private Socket client;
		private PlayerController pc = null;
		private SocketData2 sd;

		public SendMessage(Socket client, PlayerController playerController) {
			sd = new SocketData2();
			this.pc = playerController;
			this.client = client;
		}

		@Override
		public void run() {
			ObjectOutputStream oos = null;
			while (true) {
				try {
					// ��װsocketData2׼������
					if (yourIndex != -1) {
						// System.out.println("sendMessage--------��������--------");
						sd.setIsCallLandlord(pc.getPlayer(yourIndex).getIsCallLandlord());
						sd.setPlayingCards(pc.getPlayingCards());
						sd.setReady(pc.getPlayer(yourIndex).isReady());
						sd.setPassCard(pc.isPassCard());
					}

					oos = new ObjectOutputStream(client.getOutputStream());
					oos.writeObject(sd);

					Thread.sleep(30);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
