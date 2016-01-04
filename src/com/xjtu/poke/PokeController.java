package com.xjtu.poke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.xjtu.Landlord.RuleOfLandlord;

public class PokeController 
{
	private Pokers pokers = new Pokers();
	private Random random = new Random();
	
	private List<Card> lastThreePokers = new ArrayList<Card>();
	private Card landlordCard = null;
	
	List<Card> pokerList = new ArrayList();
	
	public List<Card> getPokerList() {
		return pokerList;
	}

	public void setPokerList(List<Card> pokerList) {
		this.pokerList = pokerList;
	}

	public void setLastThreePokers(List<Card> lastThreePokers) {
		this.lastThreePokers = lastThreePokers;
	}

	public PokeController()
	{
		//构造
		System.out.println("PokeController得到的总牌数："+pokers.getPokerList().size());
		
		pokerList = pokers.getPokerList();
	}
	
	public Card getLandlordCard() {
		return landlordCard;
	}

	public void setLandlordCard(Card landlordCard) {
		this.landlordCard = landlordCard;
	}

	public void shuffle()//洗牌
	{	
		for(int index=0; index<pokerList.size(); index++)
		{
			int pos = random.nextInt(53);
			Card card = pokerList.get(index);
			pokerList.set(index, pokerList.get(pos));
			pokerList.set(pos, card);
		}
		
		pokers.setPokerList(pokerList);
	}
	
	
	public void deal(List<Card> player_1,List<Card> player_2,List<Card> player_3)//发牌
	{
		int a1 = random.nextInt(50);
		landlordCard = pokerList.get(a1);
		for(int index=0; index<pokerList.size()-3; index++)
		{
			player_1.add(pokerList.get(index++));
			player_2.add(pokerList.get(index++));
			player_3.add(pokerList.get(index));	
		}	
		lastThreePokers.add(pokerList.get(51));
		lastThreePokers.add(pokerList.get(52));
		lastThreePokers.add(pokerList.get(53));
	}

	public List<Card> getRandomCards(){
		
		List<Card> cards = new ArrayList<>();
		for(int i = 0; i < 17; i++){
			int a1 = random.nextInt(53);
			cards.add(this.pokerList.get(a1));
		}
		return cards;
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
