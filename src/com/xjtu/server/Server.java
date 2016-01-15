package com.xjtu.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.xjtu.controller.PlayerController;
import com.xjtu.gamestate.GameState;
import com.xjtu.serialize.SocketData;
import com.xjtu.serialize.SocketData2;

public class Server {

	private ServerSocket serversocket;
	private Socket socket;
	private PlayerController pc = null;
	private int index;

	public Server(PlayerController pc) {
		this.pc = pc;
	}

	public void startServer(int port) {
		// TODO Auto-generated method stub
		try {

			serversocket = new ServerSocket(port);

			new Thread() {
				public void run() {
					try {

						if (pc.getPlayerCount() < pc.getPlayerCountMax()) {

							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							socket = serversocket.accept();

							if (socket != null) {
								new Thread(new receiveFormClient(socket, pc, pc.getPlayerCount())).start();
								new Thread(new sendToClient(socket, pc, pc.getPlayerCount())).start();
							}
							pc.setPlayerCount(pc.getPlayerCount() + 1);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				};
			}.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// inside class how to implements???
	// server send to client message
	class sendToClient implements Runnable {

		private Socket socket;
		private int index;
		private ObjectOutputStream oos;
		private SocketData socketdata;

		public sendToClient(Socket socket, PlayerController pc, int index) {
			this.index = index;
			this.socket = socket;
			socketdata = new SocketData();
			try {
				oos = new ObjectOutputStream(this.socket.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (true) {
				try {
					// 有客户端连接，开始发送消息给客户端 -->内容：1.对应index
					socketdata.setYourIndex(index);
					socketdata.setLastThreePokers(pc.getPokeCtrl().getLastThreePokers());
					socketdata.setPlayers(pc.getPlayers());
					socketdata.setFirst(pc.getFirst());
					socketdata.setCurrent(pc.getCurrent());
					socketdata.setWinner(pc.getWinner());
					socketdata.setGameState(pc.getGameState());
					socketdata.setLastCardsOnDesk(pc.getPlayingCards());

					oos.writeObject(socketdata);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// client send to server message
	class receiveFormClient implements Runnable {

		private Socket socket;
		private ObjectInputStream ois;
		private PlayerController pc;
		private SocketData2 socketdata2;
		private int index;

		public receiveFormClient(Socket socket, PlayerController pc, int index) {
			this.socket = socket;
			this.pc = pc;
			this.index = index;
			try {
				ois = new ObjectInputStream(this.socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (true) {
				try {
					socketdata2 = (SocketData2) ois.readObject();
					if (pc.getGameState() == GameState.WAITCLIENT) {
						pc.getPlayer(index).setReady(socketdata2.isReady());
					} else if (pc.getGameState() == GameState.WAITCALL) {
						pc.getPlayer(index).setIsCallLandlord(0);
					} else if (pc.getGameState() == GameState.WAITPLAY) {
						pc.setPassCard(socketdata2.isPassCard());
						pc.setWaitPlayingCards(socketdata2.getPlayingCards());
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}
}
