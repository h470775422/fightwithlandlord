package com.xjtu.poke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokeController 
{
	private Pokers pokers = new Pokers();
	private Random random = new Random();
	
	private List<Card> lastThreePokers = new ArrayList<Card>();
	
	
	public PokeController()
	{
		//构造
		System.out.println("PokeController得到的总牌数："+pokers.getPokerList().size());
	}
	
	public void shuffle()//洗牌
	{	
		List<Card> pokerList = new ArrayList<Card>();
		pokerList = pokers.getPokerList();
		
		for(int index=0; index<pokerList.size(); index++)
		{
			int pos = random.nextInt(53);
			Card card = new Card();
			card = pokerList.get(index);
			pokerList.set(index, pokerList.get(pos));
			pokerList.set(pos, card);
		}
		
		pokers.setPokerList(pokerList);
//		for(int i=0;i<pokers.getPokerList().size();++i)
//		{
//			Card card = new Card();
//			card = pokerList.get(i);
//			System.out.println("牌值："+card.getValue()+"花色："+card.getColor());
//		}
	}
	
	public void deal(List<Card> player_1,List<Card> player_2,List<Card> player_3)//发牌
	{
		List<Card> pokerList = new ArrayList();
		pokerList = pokers.getPokerList();
		for(int index=0; index<pokerList.size(); index++)
		{
			if(index>=51)
			{
				lastThreePokers.add(pokerList.get(index));
				continue;
			}
			Card card = new Card();
			player_1.add(pokerList.get(index++));
			player_2.add(pokerList.get(index++));
			player_3.add(pokerList.get(index++));	
		}	
	}

	
	
	public Pokers getPokers() {
		return pokers;
	}

	public void setPokers(Pokers pokers) {
		this.pokers = pokers;
	}

	public List<Card> getLastThreePokers() {
		return lastThreePokers;
	}
	
}
