package com.xjtu.monitor;

import java.util.List;

import com.xjtu.controller.PlayerController;
import com.xjtu.poke.Card;

public class MonitorThread implements Runnable{
	private PlayerController pc = null;

	public MonitorThread(PlayerController pc) {
		this.pc = pc;
	}

	@Override
	public void run() {
		while (true) {
			showState();
			//showPokes();
			sleep(2000);
		}
	}
	
	private void showState(){
		System.out.println(pc.getGameState() + " " + pc.getPlayer(0).isReady() + " " + pc.getPlayer(1).isReady()
				+ " " + pc.getPlayer(2).isReady());
	}
	
	private void showPokes(){
		for(int i = 0; i < 3; i++){
			System.out.println("player" + i);
			List<Card> cards = pc.getPlayer(i).getHand();
			for(Card c:cards){
				System.out.println(c);
			}
		}
	}
	
	
	/********************
	 * sleep
	 * 
	 * @param mil
	 */
	protected void sleep(long mil) {
		try {
			Thread.sleep(mil);
		} catch (InterruptedException e) {
			System.err.println("han:sleep thread error£¡");
			e.printStackTrace();
		}
	}
}
